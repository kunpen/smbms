package com.example.servlet.user;

import com.example.pojo.User;
import com.example.service.user.UserServiceImpl;
import com.example.util.Constants;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "doLogin",value = "/login.do")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute(Constants.USER_SESSION)!=null){
            resp.sendRedirect("jsp/frame.jsp");

        }else {
            System.out.println("login start");
            String userCode = req.getParameter("userCode");
            String password = req.getParameter("userPassword");
            UserServiceImpl userService = new UserServiceImpl();
            User user= userService.Login(userCode,password);
            if (user !=null){
                //user exited
                req.getSession().setAttribute(Constants.USER_SESSION,user);
                resp.sendRedirect("jsp/frame.jsp");
            }else {
                req.setAttribute("error","用户名或密码不正确");
                req.getRequestDispatcher("login.jsp").forward(req,resp);

            }

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
