package by.realovka.controller;

import by.realovka.dto.post.PostUserAddDTO;
import by.realovka.entity.User;
import by.realovka.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/add")
public class PostAddController {
    private PostService postService;

    public PostAddController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/post")
    public ModelAndView getHello(ModelAndView modelAndView) {
        modelAndView.addObject("postOnThePage", new PostUserAddDTO());
        modelAndView.setViewName("post");
        return modelAndView;
    }

    @PostMapping(path = "/post")
    public ModelAndView postHello(@ModelAttribute("postOnThePage") PostUserAddDTO postUserAddDTO, HttpSession httpSession, ModelAndView modelAndView) {
        int userId = ((User) httpSession.getAttribute("userAuth")).getId();
        postService.addPost(postUserAddDTO, userId);
        modelAndView.setViewName("postTape");
        return modelAndView;
    }
}
