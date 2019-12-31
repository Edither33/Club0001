package servlet;

import dao.Dao;
import entity.Activity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class addActivityServlet extends HttpServlet {
    private String aname,atime,mno,aprice;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String flag = (String)session.getAttribute("flag");
        String f = request.getParameter("flag");
        System.out.println(flag+f);
        if(f.equals(flag)) {
            Activity activity =new Activity();
            aname = request.getParameter("aname");
            atime = request.getParameter("atime");
            mno = request.getParameter("mno");
            aprice = request.getParameter("aprice");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            java.util.Date d = null;
            try {
                d = dateFormat.parse(atime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date date = new java.sql.Date(d.getTime());
            System.out.println(aname + atime + mno + aprice );
            activity.setAname(aname);
            activity.setMno(mno);
            activity.setAtime(date);
            activity.setAprice(Float.parseFloat(aprice));

            session.removeAttribute("flag");
            if (Dao.insertActivity(activity)) {
                String message="提交成功";
                session.setAttribute("message", message);
            }
            request.getRequestDispatcher("/activity.jsp").forward(request, response);
        }else{
            session.removeAttribute("flag");
            String message="请勿重复提交";
            session.setAttribute("message", message);
            request.getRequestDispatcher("/activity.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
