package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

@Controller
public class PostControl {
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    public PostControl(PostService postService, CommentService commentService,
                       UserService userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String editPost(@RequestParam("id") int id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("user",
                SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "edit";
    }

    @GetMapping("/addPost")
    public String addPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "edit";
    }

    @GetMapping("/post")
    public String viewPost(@RequestParam("id") int id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.getCommentsByPostId(id));
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "post";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User user = userService.findByUsername(username);
        post.setUser(user);
        postService.savePost(post);
        return "redirect:/index";
    }

    @PostMapping("/addComm")
    public String addComm(@RequestParam("id") int id,
                          @ModelAttribute Comment comment, Model model) {
        commentService.addCommentToPost(id, comment);
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.getCommentsByPostId(id));
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "post";
    }
}