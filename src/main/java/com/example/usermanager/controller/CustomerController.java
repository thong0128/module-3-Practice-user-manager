package com.example.usermanager.controller;

import com.example.usermanager.service.IUserDAO;
import com.example.usermanager.service.UserDAO;
import com.example.usermanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired IUserDAO userDAO;
    @GetMapping()
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
        ModelAndView mav = new ModelAndView("list");
        List<User> users = userDAO.selectAllUsers();
        mav.addObject("users", users);
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView showInformation(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        User customer = userDAO.selectUser(id);
        modelAndView.addObject("user", customer);
        return modelAndView;
    }
    @PostMapping("/{id}")
    public String update(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam String country ) throws SQLException {
        User customer = new User(id, name, email, country);
        userDAO.updateUser(customer);
        return "redirect:/customers";
    }
}
