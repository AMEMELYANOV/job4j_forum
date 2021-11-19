package ru.job4j.forum.model;

import java.util.Date;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String description;
    private String username;
    private Date created = new Date(System.currentTimeMillis());

    public static Post of(int id, String name, String description, String username) {
        Post post = new Post();
        post.id = id;
        post.name = name;
        post.description = description;
        post.username = username;
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, username, created);
    }
}