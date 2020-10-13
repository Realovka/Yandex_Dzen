package by.realovka.controller;

import by.realovka.dto.post.PostUserAddDTO;
import by.realovka.dto.post.PostViewOnPageDTO;
import by.realovka.dto.user.UserAuthDTO;
import by.realovka.dto.user.UserRegDTO;
import by.realovka.service.PostService;
import by.realovka.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/home")
public class RegistrationAndAuthorizationController {

    private UserService userService;
    private PostService postService;

    public RegistrationAndAuthorizationController(UserService userService, PostService postService) {

        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping(path = "/reg")
    public ModelAndView getReg(ModelAndView modelAndView) {
        ArrayList<PostViewOnPageDTO> postUserAddDTOList = postService.postViewOnTheFirstPageDTOS();
        modelAndView.addObject("userReg", new UserRegDTO());
        modelAndView.addObject("userAuth", new UserAuthDTO());
        modelAndView.addObject("postsOnTheFirstPage", postUserAddDTOList);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping(path = "/reg")
    public ModelAndView postReg(@Valid @ModelAttribute("userReg") UserRegDTO userRegDTO, UserAuthDTO userAuthDTO,
                                BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("index");

        } else {
            if (!userRegDTO.getNameUserDTO().equals("") && !userRegDTO.getNameUserDTO().equals("")
                    && !userRegDTO.getPasswordUserDTO().equals("")) {
                userService.createUser(userRegDTO);
                ArrayList<PostViewOnPageDTO> postUserAddDTOList = postService.postViewOnTheFirstPageDTOS();
                modelAndView.addObject("userAuth",userAuthDTO);
                modelAndView.addObject("userReg", new UserRegDTO());
                modelAndView.addObject("postsOnTheFirstPage", postUserAddDTOList);
                modelAndView.setViewName("index");
            }

        }
        return modelAndView;
    }
    @PostMapping(path = "/auth")
    public ModelAndView postAuth(@Valid @ModelAttribute("userAuth") UserAuthDTO userAuthDTO,UserRegDTO userRegDTO, BindingResult bindingResult, HttpSession httpSession,
                                 ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("index");
        } else {
            if (!userAuthDTO.getLoginAuthUser().equals("") && !userAuthDTO.getPasswordAuthUser().equals("")) {
                if (userService.getUserFromDB(userAuthDTO)) {
                    httpSession.setAttribute("userAuth", userAuthDTO);
                    ArrayList<PostViewOnPageDTO> postUserAddDTOList = postService.postViewOnTheFirstPageDTOS();
                    modelAndView.addObject("userAuth", new UserAuthDTO());
                    modelAndView.addObject("userReg", userRegDTO);
                    modelAndView.addObject("postsOnTheFirstPage", postUserAddDTOList);
                    modelAndView.setViewName("index");
                }
            }
        }
        return modelAndView;
    }

}

