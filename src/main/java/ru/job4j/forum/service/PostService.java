package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {
    private final AtomicInteger counter = new AtomicInteger(2);
    private final Map<Integer, Post> posts = new HashMap<>();

    public PostService() {
        User user = User.of("user", "123", "USER");
        posts.put(1, Post.of(1, "Продаю машину",
                "Продаю машину ладу 01. Цена 150 000", user.getUsername()));
        posts.put(2, Post.of(2, "Меняю машину на видеокарту",
                "Меняю машину  ладу на видеокарту RTX3070", user.getUsername()));
    }

    public List<Post> getAll() {
        return new ArrayList<>(posts.values());
    }

    public Post savePost(Post post) {
        if (post.getId() == 0) {
            post.setId(counter.incrementAndGet());
        }
        posts.put(post.getId(), post);
        return post;
    }

    public Post getPost(int id) {
        return posts.get(id);
    }
}