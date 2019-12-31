package servlet;

import com.alibaba.fastjson.JSONObject;
import dao.Dao;
import entity.APlace;
import entity.Activity;
import entity.CMember;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class APlaceServlet extends HttpServlet {
    private String action;
    private String pname, pphone;
    private Integer pno,phead;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("action") != null) {
            this.action = request.getParameter("action");
            if (action.equals("delete")) {
                if (deleteAPlace(request, response)) {
                    request.getRequestDispatcher("/aplace.jsp").forward(request, response);
                    return;
                } else {
                    request.getRequestDispatcher("/aplace.jsp").forward(request, response);
                    return;
                }
            }
            if(action.equals("check")){
                JSONObject jsonObject = new JSONObject();
                CMember cMember = new CMember();
                String mno = request.getParameter("mno");
                System.out.println(mno);
                cMember = Dao.selectCMembrtById(Integer.parseInt(mno));
                if(cMember.getMno()!=null){
                    jsonObject.put("cmember",cMember);
                    jsonObject.put("mes",true);
                    System.out.println(jsonObject.toJSONString());
                }else {
                    jsonObject.put("mes",false);
                    System.out.println(jsonObject.toJSONString());
                }
                response.getWriter().print(jsonObject.toJSONString());
            }
            if (action.equals("update")) {
                if (updateAPlace(request, response)) {
                    request.getRequestDispatcher("/aplace.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/aplace.jsp").forward(request, response);
                }
            }
            if (action.equals("preUpdate")) {
                selectByPno(request, response);
            }
        }
    }

    private void selectByPno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pno = request.getParameter("pno");
        APlace aPlace;
        aPlace = Dao.selectAPlaceById(Integer.parseInt(pno));
        JSONObject json = new JSONObject();
        json.put("aplace", aPlace);
        response.getWriter().print(json.toJSONString());
    }


    private boolean deleteAPlace(HttpServletRequest request, HttpServletResponse response) {
        String pno = request.getParameter("pno");
        boolean flag = Dao.deleteAPlace(Integer.parseInt(pno));
        return flag;
    }

    private boolean updateAPlace(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        String pno = request.getParameter("pno");
        APlace aPlace =new APlace();
        pname = request.getParameter("pname");
        pphone = request.getParameter("pphone");
        phead = Integer.parseInt(request.getParameter("phead"));

        System.out.println(pname + pphone  + phead +pno);
        aPlace.setPname(pname);
        aPlace.setPhead(phead);
        aPlace.setPphone(pphone);
        aPlace.setPno(Integer.parseInt(pno));
        boolean flag = Dao.updateAPlace(aPlace);
        return flag;
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
