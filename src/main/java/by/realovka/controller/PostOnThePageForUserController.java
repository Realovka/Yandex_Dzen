package by.realovka.controller;

import by.realovka.dto.comment.CommentDTO;
import by.realovka.dto.post.PostViewOnPageDTO;
import by.realovka.entity.User;
import by.realovka.service.CommentService;
import by.realovka.service.LikeService;
import by.realovka.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(path = "/posts")
public class PostOnThePageForUserController {

    private PostService postService;
    private CommentService commentService;
    private LikeService likeService;

    public PostOnThePageForUserController(PostService postService, CommentService commentService, LikeService likeService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
    }

    @GetMapping(path = "/viewPost/{id}")
    public ModelAndView getPostsPage(@PathVariable int id, HttpSession httpSession, ModelAndView modelAndView) {
        postService.insertView(id);
        PostViewOnPageDTO postOnPage = postService.getPostViewOnPage(id);
        List<CommentDTO> comments = commentService.getListCommentsToPostOnPage(id);
        long likeNumber = likeService.getLikeNumber(id);
        modelAndView.addObject("postOnPage", postOnPage);
        modelAndView.addObject("comment", new CommentDTO());
        modelAndView.addObject("listComments", comments);
        modelAndView.addObject("likes", likeNumber);
        httpSession.setAttribute("postId", id);//TODO
        modelAndView.setViewName("post");
        return modelAndView;
    }

    @PostMapping(path = "/viewPost/")
    public ModelAndView postPostsPage(@ModelAttribute("comment") CommentDTO commentDTO, HttpSession httpSession, ModelAndView modelAndView) {
        User userAuth = (User) httpSession.getAttribute("userAuth");
        long postId = (Integer) httpSession.getAttribute("postId");
        long likeNumber = likeService.getLikeNumber(postId);
        commentService.addComment(commentDTO, postId, userAuth.getId());
        List<CommentDTO> comments = commentService.getListCommentsToPostOnPage(postId);
        modelAndView.addObject("postOnPage", postService.getPostViewOnPage(postId));
        modelAndView.addObject("listComments", commentService.getListCommentsToPostOnPage(postId));
        modelAndView.addObject("comment", new CommentDTO());
        modelAndView.addObject("likes", likeNumber);
        modelAndView.setViewName("post");
        return modelAndView;
    }

}
