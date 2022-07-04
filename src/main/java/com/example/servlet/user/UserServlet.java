package com.example.servlet.user;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.pojo.User;
import com.example.service.user.UserService;
import com.example.service.user.UserServiceImpl;
import com.example.util.Constants;
import com.mysql.cj.util.StringUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserServlet",value = "/jsp/user.do")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");

        if (method.equals("savepwd")&&method!=null){
            updatePwd(req,resp);


        } else if (method.equals("pwdmodify")&& method!=null) {
            this.pwdModify(req,resp);
            
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Boolean flag = false;
        Object user = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");

        System.out.println(newpassword);

        System.out.println(user);
        System.out.println(newpassword);
        if (user!=null && !StringUtils.isNullOrEmpty(newpassword)){
            UserServiceImpl userService = new UserServiceImpl();
            flag = userService.updateUserPasswd(((User)user).getId(),newpassword);
            if (flag) {
                req.setAttribute("message", "修改成功，请退出登陆");
                req.getSession().removeAttribute(Constants.USER_SESSION);
                resp.sendRedirect(req.getContextPath() + "/");
            }else {
                req.setAttribute("message","密码修改失败");

            }

        }else {
            req.setAttribute("message","密码有问题");
        }
//        req.getRequestDispatcher("/index.jsp").forward(req,resp);



    }

    //session get old passwd
    public void pwdModify(HttpServletRequest req,HttpServletResponse resp){
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpwd = req.getParameter("oldpassword");


        Map<String,String> ResultMap = new HashMap<String,String>();
        if (o == null) {
            ResultMap.put("ressult","sessionerror");
            
        } else if (StringUtils.isNullOrEmpty(oldpwd)) {
            ResultMap.put("result","error");
            
        }else{
            String oldpassword = ((User)o).getUserPassword();
            if (oldpassword.equals(oldpwd)){
                ResultMap.put("result","true");

            }else {
                ResultMap.put("result","false");
            }
        }
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(new JSONObject(ResultMap).toJSONString());
            writer.flush();
            writer.close();

        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
