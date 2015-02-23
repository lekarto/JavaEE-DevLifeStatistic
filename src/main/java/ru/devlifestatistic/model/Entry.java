package ru.devlifestatistic.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Entry implements Serializable {
    private static final long serialVersionID = 8201337883209943936L;

    private Integer id;
    private String description;
    private Integer votes;
    private String author;
    private Date date;
    private String gifURL;
    private String previewURL;
    private String embedId;
    private String type;

    public String toString() {
        return this.id + ": " + this.author + " " + date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = changeWindows1251ToUTF8(description);
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = changeWindows1251ToUTF8(author);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
//        Jun 10, 2014 8:16:27 PM
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy H:mm:ss a", Locale.UK);
        try {
            this.date = formatter.parse(date);
        } catch (ParseException e) {
            System.out.println("format of Date was changed :-(");
        }
    }

    public String getGifURL() {
        return gifURL;
    }

    public void setGifURL(String gifURL) {
        this.gifURL = gifURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmbedId() {
        return embedId;
    }

    public void setEmbedId(String embedId) {
        this.embedId = embedId;
    }

    private String changeWindows1251ToUTF8(String cp1251) {
        try {
            return new String(cp1251.getBytes("windows-1251"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return cp1251;
        }
    }
}
