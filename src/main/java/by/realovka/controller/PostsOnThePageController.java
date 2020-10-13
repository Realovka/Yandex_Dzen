package by.realovka.controller;

import by.realovka.dto.comment.CommentDTO;
import by.realovka.dto.post.PostUserAddDTO;
import by.realovka.dto.post.PostViewOnPageDTO;
import by.realovka.service.CommentService;
import by.realovka.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/posts")
public class PostsOnThePageController {

    private PostService postService;
    private CommentService commentService;

    public PostsOnThePageController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping(path = "/post-posts")
    public ModelAndView getPostsPage(ModelAndView modelAndView){
        ArrayList<PostViewOnPageDTO> postUserAddDTOList = postService.postViewOnTheFirstPageDTOS();
        modelAndView.addObject("comment", new CommentDTO());
        modelAndView.addObject("postsOnTheFirstPage", postUserAddDTOList);
        modelAndView.setViewName("postTape");
        return modelAndView;
    }
    @PostMapping(path = "/post-posts")
    public ModelAndView postPostsPage(@ModelAttribute("comment") CommentDTO commentDTO, ModelAndView modelAndView, HttpSession httpSession){
        if(!commentDTO.getText().equals("")){
           commentService.addComment(commentDTO, httpSession);
        }
        modelAndView.setViewName("postTape");
        return modelAndView;
    }
}
