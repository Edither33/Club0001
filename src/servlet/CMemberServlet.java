package servlet;

import com.alibaba.fastjson.JSONObject;
import dao.Dao;
import entity.CMember;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;



public class CMemberServlet extends HttpServlet {
    private String action;
    private String mname, maddress, mphone, mbirth, mssex, munit;
    private String[] mhobby;
    private int isHead;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("action") != null) {
            this.action = request.getParameter("action");
            if (action.equals("delete")) {
                if (deleteCMember(request, response)) {
                    request.getRequestDispatcher("/news.jsp").forward(request, response);
                    return;
                } else {
                    request.getRequestDispatcher("/news.jsp").forward(request, response);
                    return;
                }
            }
            if (action.equals("update")) {
                if (updateCMember(request,response)) {
                    request.getRequestDispatcher("/news.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/news.jsp").forward(request, response);
                }
            }
            if(action.equals("preUpdate")){
                selectByMno(request,response);
            }
        }
    }

    private void selectByMno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mno = request.getParameter("mno");
        CMember cMember = new CMember();
        cMember = Dao.selectCMembrtById(Integer.parseInt(mno));
        JSONObject json = new JSONObject();
        json.put("cMembers",cMember);
        response.getWriter().print(json.toJSONString());
    }


    private boolean deleteCMember(HttpServletRequest request, HttpServletResponse response) {
        String mno = request.getParameter("mno");
        boolean flag = Dao.deleteCMember(Integer.parseInt(mno));
        return flag;
    }

    private boolean updateCMember(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");

        String mno = request.getParameter("mno");
        CMember cMember = new CMember();
        mname = request.getParameter("upmname");
        maddress = request.getParameter("upmaddress");
        mphone = request.getParameter("upmphone");
        mbirth = request.getParameter("upmbirth");
        mssex = request.getParameter("mssex");
        munit = request.getParameter("upmunit");
        mhobby = request.getParameterValues("upmhobby");
        isHead = Integer.parseInt(request.getParameter("upisHead"));
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
        cMember.setMno(Integer.parseInt(mno));
        boolean flag = Dao.updaeCMember(cMember);
        return flag;
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
