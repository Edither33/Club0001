package servlet;

import dao.Dao;
import entity.CMember;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class addCMemberServlet extends HttpServlet {
    private String mname,maddress,mphone,mbirth,mssex,munit;
    private String[] mhobby;
    private int isHead;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String flag = (String)session.getAttribute("flag");
        String f = request.getParameter("flag");
        System.out.println(flag+f);
        if(f.equals(flag)) {
            CMember cMember = new CMember();
            mname = request.getParameter("mname");
            maddress = request.getParameter("maddress");
            mphone = request.getParameter("mphone");
            mbirth = request.getParameter("mbirth");
            mssex = request.getParameter("mssex");
            munit = request.getParameter("munit");
            mhobby = request.getParameterValues("mhobby");
            isHead = Integer.parseInt(request.getParameter("isHead"));
            String temp = Arrays.toString(mhobby);
            System.out.println(mname + maddress + mbirth + mphone + mssex + temp + munit);
            cMember.setMname(mname);
            cMember.setMaddress(maddress);
            cMember.setMphone(mphone);
            cMember.setMbirth(mbirth);
            cMember.setMssex(mssex);
            cMember.setMhobby(temp);
            cMember.setMunit(munit);
            cMember.setIshead(isHead);
            session.removeAttribute("flag");
            if (Dao.insertCMember(cMember)) {
                String message="提交成功";
                session.setAttribute("message", message);
            }
            request.getRequestDispatcher("/news.jsp").forward(request, response);
        }else{
            session.removeAttribute("flag");
            String message="请勿重复提交";
            session.setAttribute("message", message);
            request.getRequestDispatcher("/news.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
