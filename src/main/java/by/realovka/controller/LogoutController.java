package by.realovka.controller;

import by.realovka.entity.Post;
import by.realovka.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/logout")
public class LogoutController {

    private PostService postService;

    public LogoutController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping (path = "/logout")
    public ModelAndView getLogout(ModelAndView modelAndView, HttpSession httpSession){
        httpSession.invalidate();
        modelAndView.setViewName("home");
        List<Post> postsOnFirstPage=postService.postViewOnTheFirstPage();
        modelAndView.addObject("postsList", postsOnFirstPage);
        return modelAndView;
    }
}
