package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.bvengers.user.member.model.service.MemberService;

@WebServlet("/findPwd.me")
public class FindPwdServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

    public FindPwdServlet() {
        super();
    }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("UTF-8");
	   response.setContentType("text/html; charset=UTF-8");
      String memberId = (String) request.getParameter("userId");
      String password = request.getSession().getAttribute("password").toString();
      try {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] bytes = password.getBytes(Charset.forName("UTF-8"));
		md.update(bytes);
		String newPwd = Base64.getEncoder().encodeToString(md.digest());
      int result = new MemberService().temporaryPwd(memberId,newPwd);
      String page = "";
      
      PrintWriter out = response.getWriter();
      if(result>0) {
    	  System.out.println("session 에 왜 안올라가죠"+memberId);
      request.getSession().invalidate();
      HttpSession ss = request.getSession();
      ss.setAttribute("sessionId", memberId);
      ss.setAttribute("pass", newPwd);
      page = "views/user/login/login.jsp";
      request.getRequestDispatcher(page).forward(request, response);
      }else {
    	 out.append("fail");
      }
      } catch (NoSuchAlgorithmException e) {
    	  e.printStackTrace();
      }
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
