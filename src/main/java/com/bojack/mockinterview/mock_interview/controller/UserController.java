package com.bojack.mockinterview.mock_interview.controller;

import com.bojack.mockinterview.mock_interview.entity.Account;
import com.bojack.mockinterview.mock_interview.entity.ResponseWrapper;
import com.bojack.mockinterview.mock_interview.entity.ReturnCode;
import com.bojack.mockinterview.mock_interview.entity.User;
import com.bojack.mockinterview.mock_interview.service.UserService;
import com.bojack.mockinterview.mock_interview.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/users", method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
    @Autowired
    private UserService userservice;



    @RequestMapping("/listAll")
    @ResponseBody
    public List<User> ListUser() {
        return userservice.listUser();
    }

    @RequestMapping("/ListUserByname")
    @ResponseBody
    public ResponseWrapper listUserByName(@RequestParam("userName") String userName) {
        User user = userservice.getUser(userName);
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setData(user);
        return responseWrapper;

    }

    public ResponseWrapper listFriends(@PathVariable("userId") String userId) {
        return null;
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public String delete(@PathVariable int userId) {
        int result = userservice.delete(userId);
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
    public User insert(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setAge(10);
        user.setGender("male");
        user.setIsManager(0);
        user.setCreateDate(new Date());
        return userservice.insertUser(user);
    }


}
