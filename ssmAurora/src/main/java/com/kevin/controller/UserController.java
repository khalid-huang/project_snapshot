package com.kevin.controller;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevin.model.User;
import com.kevin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    //用于响应ajax的
    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        long userId = Long.parseLong(request.getParameter("id"));
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }
    //用于返回页面的
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public ModelAndView handleRequest(){
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUsername("kaka");
        user1.setPassword("kakaka");

        User user2 = new User();
        user2.setUsername("mimi");
        user2.setPassword("mimimi");

        userList.add(user1);
        userList.add(user2);

        ModelAndView modelAndView = new ModelAndView();
        System.out.println(userList);
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("/users/userList");//在spring-mvc里面已经配置了prefix和suffix
        return modelAndView;

    }

}
