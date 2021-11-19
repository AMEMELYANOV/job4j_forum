package ru.job4j.forum.model;

import java.util.Objects;

public class User {
    private int id;
    private String password;
    private String username;
    private boolean enabled;
    private String authority;

    public static User of(String username, String password, String authority) {
        User user = new User();
        user.username = username;
        user.password = password;
        user.authority = authority;
        user.enabled = true;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && enabled == user.enabled
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password)
                && Objects.equals(authority, user.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, enabled, authority);
    }
}