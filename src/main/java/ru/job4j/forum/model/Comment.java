package ru.job4j.forum.model;

import java.util.Date;
import java.util.Objects;

public class Comment {
    private String text;
    private String username;
    private final Date created = new Date(System.currentTimeMillis());

    public static Comment of(String text, String username) {
        Comment comment = new Comment();
        comment.text = text;
        comment.username = username;
        return comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated() {
        return created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(text, comment.text)
                && Objects.equals(username, comment.username)
                && Objects.equals(created, comment.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, username, created);
    }
}