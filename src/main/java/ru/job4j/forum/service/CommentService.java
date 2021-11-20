package ru.job4j.forum.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.CommentRepository;
import ru.job4j.forum.store.PostRepository;
import ru.job4j.forum.store.UserRepository;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> getCommentsByPostId(int id) {
        List<Comment> rsl = commentRepository.findAllByPostId(id);
        return rsl;
    }

    public void addCommentToPost(int id, Comment comment) {
        User user = userRepository.findUserByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        comment.setUser(user);
        Comment newComment = Comment.of(comment.getText(), user, postRepository.findById(id).get());
        commentRepository.save(newComment);
    }
}
