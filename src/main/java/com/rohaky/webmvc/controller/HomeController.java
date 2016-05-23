package com.rohaky.webmvc.controller;

import com.rohaky.webmvc.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CustomService service;

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/public")
    public @ResponseBody String publicPage() {
        return "Public Page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/logoutForm", method = RequestMethod.GET)
    public String logoutFormPage() {
        return "logoutForm";
    }

    @RequestMapping("/security")
    public void securityPage(HttpServletResponse response) throws IOException, SQLException {
        response.getWriter().println("Security Page");
    }

    @RequestMapping("/admin")
    public void adminPage(HttpServletResponse response, @AuthenticationPrincipal User user) throws IOException, SQLException {
        response.getWriter().println("Admin Page");
        response.getWriter().println("Username: " + user.getUsername());
        response.getWriter().println("Authorities: ");
        user.getAuthorities().forEach(response.getWriter()::println);
    }

    @RequestMapping("/list")
    public void listPage(HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        List<String> posts = service.listPost();
        posts.forEach(writer::println);

    }


}
