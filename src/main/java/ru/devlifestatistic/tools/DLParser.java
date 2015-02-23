package ru.devlifestatistic.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import ru.devlifestatistic.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.*;

public class DLParser {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String RESULT = "result";
    private static final String ID = "id";
    private static final String ERROR = "error";
    private static final String COMMENTS = "comments";

    private static String getResponseForGetRequest(String requestURL) {
        StringBuffer result = new StringBuffer();

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(requestURL);

        request.addHeader("User-Agent", USER_AGENT); //add request header

        try {
            HttpResponse response = client.execute(request);
            try {
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClientProtocolException e) {
            System.out.println("Something gone wrong :-( Are you have a Internet?");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static ArrayList<Entry> getAllEntries() {
        int pageNum = 0;
        ArrayList<Entry> entries = new ArrayList<Entry>();
        while (pageNum >= 0) {
            if ((pageNum % 20) == 0) {
                System.out.println(pageNum * 50);
            }
            String jsonString = getResponseForGetRequest("http://developerslife.ru/latest/" + pageNum + "?pageSize=50&json=true");
            pageNum++;
            try {
                if (jsonString.length() == 0) {
                    throw new Exception("developerslife.ru have unkown error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                JsonNode rootNode = objectMapper.readTree(jsonString);
                JsonNode resultNode = rootNode.findPath(RESULT);
                if (resultNode.size() > 0) {
                    for (int i = 0; i < resultNode.size(); i++) {
                        entries.add(objectMapper.readValue(resultNode.get(i).toString(), Entry.class));
                    }
                } else {
                    pageNum = -1;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return entries;
    }

    public static Integer getTotalCount() {
        Integer result = 0;
        String jsonString = getResponseForGetRequest("http://developerslife.ru/?json=true");
        try {
            if (jsonString.length() == 0) {
                throw new Exception("developerslife.ru have unkown error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readTree(jsonString).path(TOTAL_COUNT).asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Integer getMaxNumEntry() {
        Integer result = 0;
        String jsonString = getResponseForGetRequest("http://developerslife.ru/?json=true");
        try {
            if (jsonString.length() == 0) {
                throw new Exception("developerslife.ru have unkown error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readTree(jsonString).path(RESULT).get(0).path(ID).asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static class EntryThread implements Callable<CopyOnWriteArrayList<Entry>> {
        private int _rFrom;
        private int _rUntil;
        private CopyOnWriteArrayList<Entry> entries = new CopyOnWriteArrayList<Entry>();

        public EntryThread(int rFrom, int rUntil) {
            _rFrom = rFrom;
            _rUntil = rUntil;
            if (_rFrom > _rUntil) {
                int i = _rFrom;
                _rFrom = _rUntil;
                _rUntil = i;
            }
        }

        @Override
        public CopyOnWriteArrayList<Entry> call() throws Exception {
            if ((_rFrom < 0) || (_rUntil < 0)) {
                return entries;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                for (int i = _rFrom; i <= _rUntil; i++) {
//                    if ((i % 100) == 0) System.out.println(i);
                    String jsonString = getResponseForGetRequest("http://developerslife.ru/" + i + "?json=true");
                    if (jsonString.length() == 0) {
                        break;
                    }
                    JsonNode answer = objectMapper.readTree(jsonString);
                    if (!answer.has(ERROR)) {
                        entries.add(objectMapper.readValue(jsonString, Entry.class));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return entries;
        }
    }

    private static Boolean isThreadsAvaliable(ArrayList<Future<CopyOnWriteArrayList<Entry>>> futures) {
        for (Future fut : futures) {
            if (!fut.isDone()) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Entry> getEntryInRange(int rFrom, int rUntil, int threadCount) {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        int _rFrom = ((rFrom >= 0) ? rFrom : 0);
        int _rUntil = ((rUntil >= 0) ? rUntil : 0);
        if (_rFrom > _rUntil) {
            int i = _rFrom;
            _rFrom = _rUntil;
            _rUntil = i;
        }
        int threadCnt = ((threadCount > 0) ? threadCount : 1);
        ExecutorService pool = Executors.newFixedThreadPool(threadCnt);
        ArrayList<Future<CopyOnWriteArrayList<Entry>>> future = new ArrayList<Future<CopyOnWriteArrayList<Entry>>>(threadCnt);
        int step = (_rUntil - _rFrom) / threadCnt;
        for (int i = 1; i <= threadCnt; i++) {
            future.add(pool.submit(
                    new EntryThread(_rFrom + step * (i - 1),
                            ((i == threadCnt) ? _rUntil : (_rFrom + step * i - 1)))));
        }
        while (isThreadsAvaliable(future)) {
        }
        try {
            for (Future<CopyOnWriteArrayList<Entry>> fut : future) {
                entries.addAll(fut.get());
            }
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        return entries;
    }

    public static ArrayList<Entry> getEntryInRange(int rFrom, int rUntil) {
        return getEntryInRange(rFrom, rUntil, 1);
    }

    public static ArrayList<Comment> getCommentsForEntry(int entryNum) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        if (entryNum < 0) {
            return comments;
        }
        String jsonString = getResponseForGetRequest("http://developerslife.ru/comments/entry/" + entryNum);
        try {
            if (jsonString.length() == 0) {
                throw new Exception("developerslife.ru have unkown error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return comments;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode answer = objectMapper.readTree(jsonString);
            if (answer.get(ERROR) == null) {
                JsonNode comm = answer.get(COMMENTS);
                for (int i = 0; i < comm.size(); i++) {
                    comments.add(objectMapper.readValue(comm.get(i).toString(), Comment.class));
                }
            } else {
                System.out.println(entryNum + ": " + answer.get(ERROR).toString());
            }
        } catch (JsonProcessingException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static Entry getEntry(int num){
        Entry entry = new Entry();

        String jsonString = getResponseForGetRequest("http://developerslife.ru/" + num + "?json=true");
        try {
            if (jsonString.length() == 0) {
                throw new Exception("developerslife.ru have unkown error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return entry;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode answer = objectMapper.readTree(jsonString);
            if (!answer.has(ERROR)) {
                entry = (objectMapper.readValue(jsonString, Entry.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entry;
    }
}