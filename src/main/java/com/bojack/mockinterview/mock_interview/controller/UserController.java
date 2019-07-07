package com.bojack.mockinterview.mock_interview.controller;

import com.bojack.mockinterview.mock_interview.entity.User;
import com.bojack.mockinterview.mock_interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {

    @Autowired
    private UserService userservice;

    @RequestMapping("/listAll")
    @ResponseBody
    public List<User> ListUser(){
        return userservice.listUser();
    }

    @RequestMapping("/ListUserByname")
    @ResponseBody
    public User ListUserByname(String userName){
        return userservice.getUser(userName);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = userservice.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
/*
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        int result = userservice.Update(user);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }
*/
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public User insert(User user) {

        return userservice.insertUser(user);
    }


}
