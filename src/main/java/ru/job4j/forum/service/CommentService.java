package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    private final Map<Integer, List<Comment>> comments = new HashMap<>();

    public CommentService() {
        Comment comment1 = Comment.of("Интересное предложение", "user");
        Comment comment2 = Comment.of("Какое состояние?", "user");
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
        comments.put(1, commentList);
    }

    public void addCommentToPost(int id, Comment comment) {
        if (!comments.containsKey(id)) {
            comments.put(id, new ArrayList<>());
        }
        List<Comment> currentComments = comments.get(id);
        currentComments.add(comment);
    }

    public List<Comment> getCommentsByPostId(int id) {
        if (!comments.containsKey(id)) {
            comments.put(id, new ArrayList<>());
        }
        return comments.get(id);
    }
}
