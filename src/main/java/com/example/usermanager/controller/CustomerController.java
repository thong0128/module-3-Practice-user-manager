package com.example.usermanager.controller;

import com.example.usermanager.service.IUserDAO;
import com.example.usermanager.service.UserDAO;
import com.example.usermanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired IUserDAO userDAO;
    @GetMapping("/customers")
//    public String showList(HttpServletRequest request){
//        List<User> users = userDAO.selectAllUsers();
//        request.setAttribute("users", users);
//        return "user/list.jsp";
//    }
//    public String showList(Model model) {
//        List<User> userList = userDAO.selectAllUsers();
//        model.addAttribute("users", userList);
//        return "user/list.jsp";
//    }
    public ModelAndView showList(){
        ModelAndView mav = new ModelAndView("user/list.jsp");
        List<User> users = userDAO.selectAllUsers();
        mav.addObject("users", users);
        return mav;
    }
}
