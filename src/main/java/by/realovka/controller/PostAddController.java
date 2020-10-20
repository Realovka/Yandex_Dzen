package by.realovka.controller;

import by.realovka.dto.post.PostUserAddDTO;
import by.realovka.entity.Post;
import by.realovka.entity.User;
import by.realovka.service.PostService;
import by.realovka.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/add")
public class PostAddController {
    private PostService postService;
    private UserService userService;

    public PostAddController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping(path = "/post")
    public ModelAndView getPostToPage(ModelAndView modelAndView) {
        modelAndView.addObject("postOnThePage", new PostUserAddDTO());
        modelAndView.setViewName("addPost");
        return modelAndView;
    }

    @PostMapping(path = "/post") //нужно получить id авторизированного юзера, чтобы засетить его в таблицу постов
    public ModelAndView postPostToPage(@ModelAttribute("postOnThePage") PostUserAddDTO postUserAddDTO, HttpSession httpSession, ModelAndView modelAndView) {
        User userAuth = (User) httpSession.getAttribute("userAuth");
        postService.addPost(postUserAddDTO, userAuth.getId());
        List<Post> postsList = postService.postViewOnTheFirstPage();
        modelAndView.addObject("postsList", postsList);
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
