package ru.sbt.domain;

import java.util.Calendar;

public class Document {
    private final String title;
    private final Integer size;
    private final Calendar created;
    private final String author;

    public Document(String title, Integer size, Calendar created, String author) {
        this.title = title;
        this.size = size;
        this.created = created;
        this.author = author;
    }

    @Override
    public String toString() {
        return this.getTitle() + " - " + this.getSize() + " - " + this.getCreated().getTime() + " - " + this.getAuthor();
    }

    public String getTitle() {
        return title;
    }

    public Integer getSize() {
        return size;
    }

    public Calendar getCreated() {
        return created;
    }

    public String getAuthor() {
        return author;
    }
}
