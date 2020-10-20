package by.realovka.controller;

import by.realovka.dto.comment.CommentDTO;
import by.realovka.entity.User;
import by.realovka.service.CommentService;
import by.realovka.service.LikeService;
import by.realovka.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/addLike")
public class LikeAddController {

    private PostService postService;
    private CommentService commentService;
    private LikeService likeService;

    public LikeAddController(PostService postService, CommentService commentService, LikeService likeService) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
    }

    @GetMapping (path = "/addLike/{id}")
    public ModelAndView addLike(@PathVariable int id, HttpSession httpSession, ModelAndView modelAndView){
        long authUserId =((User) httpSession.getAttribute("userAuth")).getId();
        likeService.insertLike(authUserId, id);
        long likeNumber = likeService.getLikeNumber(id);
        modelAndView.addObject("postOnPage", postService.getPostViewOnPage(id));
        modelAndView.addObject("listComments", commentService.getListCommentsToPostOnPage(id));
        modelAndView.addObject("comment", new CommentDTO());
        modelAndView.addObject("likes", likeNumber);
        modelAndView.setViewName("post");
        return modelAndView;
    }
}
