package servlet;

import dao.Dao;
import entity.APlace;
import entity.Activity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class addAPlaceServlet extends HttpServlet {
    private String pname,pphone;
    private Integer phead;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String flag = (String)session.getAttribute("flag");
        String f = request.getParameter("flag");
        System.out.println(flag+f);
        if(f.equals(flag)) {
            APlace aPlace =new APlace();
            pname = request.getParameter("pname");
            pphone = request.getParameter("pphone");
            phead = Integer.parseInt(request.getParameter("phead"));

            System.out.println(pname + pphone  + phead );
            aPlace.setPname(pname);
            aPlace.setPhead(phead);
            aPlace.setPphone(pphone);
            session.removeAttribute("flag");
            if (Dao.insertAPlace(aPlace)) {
                String message="提交成功";
                session.setAttribute("message", message);
            }
            request.getRequestDispatcher("/aplace.jsp").forward(request, response);
        }else{
            session.removeAttribute("flag");
            String message="请勿重复提交";
            session.setAttribute("message", message);
            request.getRequestDispatcher("/aplace.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
