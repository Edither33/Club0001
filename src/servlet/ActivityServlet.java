package servlet;

import com.alibaba.fastjson.JSONObject;
import dao.Dao;
import entity.Activity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ActivityServlet extends HttpServlet {
    private String action;
    private String aname, atime, mno, aprice;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("action") != null) {
            this.action = request.getParameter("action");
            if (action.equals("delete")) {
                if (deleteActivity(request, response)) {
                    request.getRequestDispatcher("/activity.jsp").forward(request, response);
                    return;
                } else {
                    request.getRequestDispatcher("/activity.jsp").forward(request, response);
                    return;
                }
            }
            if (action.equals("update")) {
                if (updateActivity(request, response)) {
                    request.getRequestDispatcher("/activity.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/activity.jsp").forward(request, response);
                }
            }
            if (action.equals("preUpdate")) {
                selectByAno(request, response);
            }
        }
    }

    private void selectByAno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ano = request.getParameter("ano");
        Activity activity = new Activity();
        activity = Dao.selectActivityById(Integer.parseInt(ano));
        System.out.println(activity.getAtime());
        JSONObject json = new JSONObject();
        json.put("activity", activity);
        System.out.println(json);
        response.getWriter().print(json.toJSONString());
    }


    private boolean deleteActivity(HttpServletRequest request, HttpServletResponse response) {
        String ano = request.getParameter("ano");
        boolean flag = Dao.deleteActivity(Integer.parseInt(ano));
        return flag;
    }

    private boolean updateActivity(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        Activity activity = new Activity();
        String ano = request.getParameter("upano");
        aname = request.getParameter("upaname");
        atime = request.getParameter("upatime");
        mno = request.getParameter("upmno");
        aprice = request.getParameter("upaprice");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date d = null;
        try {
            d = dateFormat.parse(atime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        System.out.println(aname + atime + mno + aprice);
        activity.setAname(aname);
        activity.setMno(mno);
        activity.setAtime(date);
        activity.setAprice(Float.parseFloat(aprice));
        activity.setAno(Integer.parseInt(ano));
        boolean flag = Dao.updaeActivity(activity);
        return flag;
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
