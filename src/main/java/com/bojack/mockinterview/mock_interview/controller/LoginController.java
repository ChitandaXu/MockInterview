package com.bojack.mockinterview.mock_interview.controller;

import com.bojack.mockinterview.mock_interview.service.UserService;
import com.bojack.mockinterview.mock_interview.utils.MockUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;


    @RequestMapping(path = {"/register/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String reg(Model model, @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "rember", defaultValue = "0") int rememberme,
                      HttpServletResponse response) {
        try {
            Map<String, Object> map = userService.register(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if (rememberme > 0) {
                    cookie.setMaxAge(3600 * 24 * 5);
                }
                response.addCookie(cookie);
                return MockUtils.getJSONString(0, "注册成功");
            } else {
                return MockUtils.getJSONString(1, map);
            }

        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            return MockUtils.getJSONString(1, "注册异常");
        }
    }

    @RequestMapping(path = {"/login/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(@RequestParam("userName") String username,
                        @RequestParam("password") String password
    ) {
        try {
            Map<String, Object> map = userService.login(username, password);
            if (map.containsKey("userName") && map.get("userName") != null)
                return MockUtils.getJSONString(0, "成功");
            return MockUtils.getJSONString(1, "失败");
/*
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if (rememberme > 0) {
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);

                return MockUtils.getJSONString(0, "成功");
            } else {
                return MockUtils.getJSONString(1, map);
            }
*/
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            return MockUtils.getJSONString(1, "注册异常");
        }
    }
/*
    @RequestMapping(path = {"/logout/"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        return "redirect:/";

    }
*/
}
