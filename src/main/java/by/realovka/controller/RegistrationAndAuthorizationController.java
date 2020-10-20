package by.realovka.controller;

import by.realovka.dto.user.UserAuthDTO;
import by.realovka.dto.user.UserRegDTO;
import by.realovka.entity.Post;
import by.realovka.entity.User;
import by.realovka.service.PostService;
import by.realovka.service.UserService;
import by.realovka.service.exception.NoSuchUserException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/reg-auth")
public class RegistrationAndAuthorizationController {

    private UserService userService;
    private PostService postService;

    public RegistrationAndAuthorizationController(UserService userService, PostService postService) {

        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping(path = "/reg")
    public ModelAndView getReg(ModelAndView modelAndView) {
        modelAndView.addObject("userReg", new UserRegDTO());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(path = "/reg")
    public ModelAndView postReg(@Valid @ModelAttribute("userReg") UserRegDTO userRegDTO,
                                BindingResult bindingResult, ModelAndView modelAndView) {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
        } else {
            userService.createUser(userRegDTO);
            modelAndView.addObject("userReg", new UserRegDTO());
            List<Post> postsOnFirstPage=postService.postViewOnTheFirstPage();
            modelAndView.addObject("postsList", postsOnFirstPage);
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }

    @GetMapping(path = "/auth")
    public ModelAndView getAuth(ModelAndView modelAndView) {
        modelAndView.addObject("userAuth", new UserAuthDTO());
        modelAndView.setViewName("authorization");
        return modelAndView;
    }

    @PostMapping(path = "/auth")
    public ModelAndView postAuth(@Valid @ModelAttribute("userAuth") UserAuthDTO userAuthDTO, BindingResult bindingResult, HttpSession httpSession,
                                 ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("authorization");
        } else {
            if (userService.authorizeUser(userAuthDTO)) {
                User authUser = userService.getAuthUserIdAndName(userAuthDTO);
                httpSession.setAttribute("userAuth", authUser);
                modelAndView.addObject("userAuth", new UserAuthDTO());
                List<Post> postsOnFirstPage=postService.postViewOnTheFirstPage();
                modelAndView.addObject("postsList", postsOnFirstPage);
                modelAndView.setViewName("home");
            } else throw new NoSuchUserException();
        }
        return modelAndView;
    }

}

