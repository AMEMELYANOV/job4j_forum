package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostRepository;

import java.util.*;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        postRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public Post savePost(Post post) {
        Post oldPost = getPostById(post.getId());
        if (oldPost != null) {
            post.setCreated(oldPost.getCreated());
        }
        return postRepository.save(post);
    }

    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }
}