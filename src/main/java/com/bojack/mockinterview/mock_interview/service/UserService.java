package com.bojack.mockinterview.mock_interview.service;

import com.bojack.mockinterview.mock_interview.entity.User;
import com.bojack.mockinterview.mock_interview.utils.MockUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bojack.mockinterview.mock_interview.dao.UserDao;
import org.springframework.util.StringUtils;

import java.util.*;


@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDAO;

    //@Autowired
    // private LoginTicketDAO loginTicketDAO;

    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(username)) {
            map.put("msgname", "用户名不能为空");
            return map;
        }

        if (StringUtils.isEmpty(password)) {
            map.put("msgpwd", "密码不能为空");
            return map;
        }

        User user = userDAO.selectByName(username);

        if (user != null) {
            map.put("msgname", "用户名已经被注册");
            return map;
        }

        // 密码强度
        user = new User();
        user.setUserName(username);
        //user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        //String head = String.format("http://images.coder.com/head/%dt.png", new Random().nextInt(1000));
        //user.setHeadUrl(head);
        user.setPassword(MockUtils.MD5(password));
        userDAO.addUser(user);

        // 登陆
        //String ticket = addLoginTicket(user.getId());
        //map.put("ticket", ticket);
        return map;
    }


    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(username)) {
            map.put("msgname", "用户名不能为空");
            return map;
        }

        if (StringUtils.isEmpty(password)) {
            map.put("msgpwd", "密码不能为空");
            return map;
        }

        User user = userDAO.selectByName(username);

        if (user == null) {
            map.put("msgname", "用户名不存在");
            return map;
        }

        if (!password.equals(user.getPassword())) {
            map.put("msgpwd", "密码不正确");
            return map;
        }

        map.put("userName", user.getUserName());

        //String ticket = addLoginTicket(user.getId());
        //map.put("ticket", ticket);
        return map;
    }

    /*private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(ticket);
        return ticket.getTicket();
    }*/

    public User getUser(String userName) {
        return userDAO.selectByName(userName);
    }

    public int delete(int id) {
        return userDAO.delete(id);
    }

    /*public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket, 1);
    }*/
    public User insertUser(User user) {
        userDAO.insertUser(user);
        return user;
    }

    public List<User> listUser() {
        return userDAO.ListUser();
    }
}
