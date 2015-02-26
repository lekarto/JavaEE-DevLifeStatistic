package ru.devlifestatistic.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Comment implements Serializable {
    private static final long serialVersionID = -699907702571062615L;

    private Integer id;
    private String text;
    private Date date;
    private Integer voteCount;
    private Integer authorId;
    private String authorName;
    private Integer parentId;
    private Integer entryId;
    private Boolean deleted;
    private Boolean voted;
    private Boolean editable;

    public String toString () {
        return authorName+" - "+text+" ("+date+")";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = changeWindows1251ToUTF8(text);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.UK);
        try {
            this.date = formatter.parse(date);
        } catch (ParseException e) {
            System.out.println("format of Date was changed :-(");
        }
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getVoted() {
        return voted;
    }

    public void setVoted(Boolean voted) {
        this.voted = voted;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    private String changeWindows1251ToUTF8(String cp1251) {
        try {
            return new String(cp1251.getBytes("windows-1251"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return cp1251;
        }
    }
}