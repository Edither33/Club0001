package dao;

import entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    public static Connection conn = null;

    static {
        if(conn == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/club"
                        ,"root","123456");
                System.out.println("连接成功！");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private Dao(){
    }

    /**
     * 查找社员表中所有的数据
     * @return List
     */
    public static List cMemberFindAll(){
        List<CMember> cMembers = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from c_member";
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                CMember cMember = new CMember();
                cMember.setMname(resultSet.getString("mname"));
                cMember.setMssex(resultSet.getString("mssex"));
                cMember.setMbirth(resultSet.getString("mbirth"));
                cMember.setMaddress(resultSet.getString("maddress"));
                cMember.setIshead(resultSet.getInt("ishead"));
                cMember.setMphone(resultSet.getString("mphone"));
                cMember.setMhobby(resultSet.getString("mhobby"));
                cMember.setMunit(resultSet.getString("munit"));
                cMember.setMno(resultSet.getInt("mno"));
                cMembers.add(cMember);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cMembers;
    }

    /**
     * 插入社员数据
     */
    public static boolean insertCMember(CMember cm){
        PreparedStatement ps = null;
        boolean flag = false;
        //ResultSet rs = null;
        String sql = "insert into c_member(mname,mssex,mbirth,maddress,mphone,ishead,mhobby,munit) " +
                "values (?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,cm.getMname());
            ps.setString(2,cm.getMssex());
            ps.setString(3,cm.getMbirth());
            ps.setString(4,cm.getMaddress());
            ps.setString(5,cm.getMphone());
            ps.setInt(6,cm.getIshead());
            ps.setString(7,cm.getMhobby());
            ps.setString(8,cm.getMunit());
            flag = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改社员数据
     * @param cm
     */
    public  static boolean updaeCMember(CMember cm){
        PreparedStatement ps = null;
        //ResultSet rs = null;
        boolean flag = false;
        String sql = "update c_member set mname=?,mssex=?,mbirth=?,maddress=?,mphone=?,ishead=?,mhobby=?,munit=? where mno = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,cm.getMname());
            ps.setString(2,cm.getMssex());
            ps.setString(3,cm.getMbirth());
            ps.setString(4,cm.getMaddress());
            ps.setString(5,cm.getMphone());
            ps.setInt(6,cm.getIshead());
            ps.setString(7,cm.getMhobby());
            ps.setString(8,cm.getMunit());
            ps.setInt(9,cm.getMno());
            flag=ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除社员信息
     * @param a
     */
    public  static boolean deleteCMember(Integer a){
        PreparedStatement ps = null;
        //ResultSet rs = null;
        String sql = "delete from c_member where mno = ?";
        boolean flag = delete(ps,a,sql);
        return flag;
    }

    /**
     * 查找活动信息
     * @return
     */
    public static List ActivityFindAll(){
        List<Activity> activities = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from activity";
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Activity activity = new Activity();
                activity.setAname(resultSet.getString("aname"));
                activity.setAprice(resultSet.getFloat("aprice"));
                activity.setAtime(resultSet.getDate("atime"));
                activity.setMno(resultSet.getString("mno"));
                activity.setAno(resultSet.getInt("ano"));
                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    /**
     * 插入活动信息
     * @param activity
     */
    public static boolean insertActivity(Activity activity){
        PreparedStatement ps = null;
        boolean flag = false;
        //ResultSet rs = null;
        String sql = "insert into activity(aname,atime,mno,aprice) " +
                "values (?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,activity.getAname());
            ps.setDate(2,activity.getAtime());
            ps.setString(3,activity.getMno());
            ps.setFloat(4,activity.getAprice());
            flag = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  flag;
    }

    /**
     * 修改活动数据
     * @param activity
     */
    public  static boolean updaeActivity(Activity activity){
        PreparedStatement ps = null;
        //ResultSet rs = null;
        boolean flag = false;
        String sql = "update activity set aname=?,atime=?,mno=?,aprice=? where ano = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,activity.getAname());
            ps.setDate(2,activity.getAtime());
            ps.setString(3,activity.getMno());
            ps.setFloat(4,activity.getAprice());
            ps.setFloat(5,activity.getAno());
            flag = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除活动信息
     * @param a
     */
    public  static boolean deleteActivity(Integer a){
        PreparedStatement ps = null;
        boolean flag = false;
        //ResultSet rs = null;
        String sql = "delete from activity where ano = ?";
        flag = delete(ps,a,sql);
        return flag;
    }

    /**
     * 公用删除方法
     * @param ps
     * @param a
     * @param sql
     */
    private static boolean delete(PreparedStatement ps , Integer a ,String sql){
        int k=0 ;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,a);
            k = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(k>0)
            return true;
        else
            return false;
    }

    /**
     * 查找活动地点所有信息
     * @return
     */
    public static List APlaceFindAll(){
        List<APlace> aPlaces = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from a_place";
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                APlace aPlace = new APlace();
                aPlace.setPname(resultSet.getString("pname"));
                aPlace.setPhead(resultSet.getInt("phead"));
                aPlace.setPno(resultSet.getInt("pno"));
                aPlace.setPphone(resultSet.getString("pphone"));
                aPlaces.add(aPlace);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aPlaces;
    }

    /**
     * 插入活动地点信息
     * @param aPlace
     */
    public static boolean insertAPlace(APlace aPlace){
        PreparedStatement ps = null;
        boolean flag = false;
        //ResultSet rs = null;
        String sql = "insert into a_place(pname,phead,pphone) " +
                "values (?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,aPlace.getPname());
            ps.setInt(2,aPlace.getPhead());
            ps.setString(3,aPlace.getPphone());
            flag = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改活动地点数据
     * @param aPlace
     */
    public  static boolean updateAPlace(APlace aPlace){
        PreparedStatement ps = null;
        boolean flag = false;
        //ResultSet rs = null;
        String sql = "update a_place set pname=?,phead=?,pphone=? where pno = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,aPlace.getPname());
            ps.setInt(2,aPlace.getPhead());
            ps.setString(3,aPlace.getPphone());
            ps.setInt(4,aPlace.getPno());
            flag = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除活动地点信息
     * @param a
     */
    public  static boolean deleteAPlace(Integer a){
        PreparedStatement ps = null;
        boolean flag = false;
        //ResultSet rs = null;
        String sql = "delete from a_place where pno = ?";
        flag = delete(ps,a,sql);
        return flag;
    }

    /**
     * 查找所有活动评价信息
     * @return
     */
    public static List AEvaluationFindAll(){
        List<AEvaluation> aEvaluations = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from aevaluation";
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                AEvaluation aEvaluation = new AEvaluation();
                aEvaluation.setEstate(resultSet.getString("estate"));
                aEvaluation.setEffect(resultSet.getString("effect"));
                aEvaluation.setEscore(resultSet.getFloat("escore"));
                aEvaluation.setAno(resultSet.getInt("ano"));
                aEvaluation.setEno(resultSet.getInt("eno"));
                aEvaluations.add(aEvaluation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aEvaluations;
    }

    /**
     * 插入活动地点信息
     * @param aEvaluation
     * @param a
     */
    public static void insertAEvaluation(AEvaluation aEvaluation,Integer a){
        PreparedStatement ps = null;
        //ResultSet rs = null;
        String sql = "insert into aevaluation(estate,effect,escore,ano) " +
                "values (?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,aEvaluation.getEstate());
            ps.setString(2,aEvaluation.getEffect());
            ps.setFloat(3,aEvaluation.getEscore());
            ps.setInt(4,a);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//活动评价不允许修改
    /**
     * 删除活动评价信息
     * @param a
     */
    public  static void deleteAEvaluation(Integer a){
        PreparedStatement ps = null;
        //ResultSet rs = null;
        String sql = "delete from aevaluation where eno = ?";
        delete(ps,a,sql);
    }

    /**
     * 查找所有地点-活动信息
     * @return
     */
    public static List APFindAll(){
        List<AP> aps = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from ap";
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                AP ap = new AP();
                ap.setAno(resultSet.getInt("ano"));
                ap.setPno(resultSet.getInt("pno"));
                aps.add(ap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aps;
    }

    /**
     *
     * @return
     */
    public static List MAFindAll(){
        List<MA> mas = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from ma";
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                MA ma = new MA();
                ma.setMno(resultSet.getInt("mno"));
                ma.setAno(resultSet.getInt("ano"));
                ma.setPno(resultSet.getInt("pno"));
                mas.add(ma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mas;
    }

    /**
     *
     * @param ma
     */
    public static void insertMA(MA ma){
        PreparedStatement ps = null;
        //ResultSet rs = null;
        String sql = "insert into ma(mno,ano,pno) " +
                "values (?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ma.getMno());
            ps.setInt(2,ma.getAno());
            ps.setInt(3,ma.getPno());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param ma
     */
    public  static void updaeMA(MA ma){
        PreparedStatement ps = null;
        //ResultSet rs = null;
        String sql = "update ma set mno=?,ano=?,pno=? where mno = ? and ano=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ma.getMno());
            ps.setInt(2,ma.getAno());
            ps.setInt(3,ma.getPno());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param ma
     */
    public  static void deleteMA(MA ma){
        PreparedStatement ps = null;
        //ResultSet rs = null;
        String sql = "delete from ma where mno = ? and ano=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ma.getMno());
            ps.setInt(2,ma.getAno());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static CMember selectCMembrtById(Integer mno){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from c_member where mno = ?";
        CMember cMember = new CMember();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,mno);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                cMember.setMname(resultSet.getString("mname"));
                cMember.setMssex(resultSet.getString("mssex"));
                cMember.setMbirth(resultSet.getString("mbirth"));
                cMember.setMaddress(resultSet.getString("maddress"));
                cMember.setIshead(resultSet.getInt("ishead"));
                cMember.setMphone(resultSet.getString("mphone"));
                cMember.setMhobby(resultSet.getString("mhobby"));
                cMember.setMunit(resultSet.getString("munit"));
                cMember.setMno(resultSet.getInt("mno"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cMember;
    }


    public static boolean checkPasswordAndUsername(String username,String password){
        boolean flag = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from c_member where mno = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,Integer.parseInt(username));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String p = resultSet.getString("mname");
                if(password.equals(p)){
                    flag = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Activity selectActivityById(int parseInt) {
        PreparedStatement preparedStatement = null;
        Activity activity = new Activity();
        ResultSet resultSet = null;
        String sql = "select * from activity where ano=?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,parseInt);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                activity.setAname(resultSet.getString("aname"));
                activity.setAprice(resultSet.getFloat("aprice"));
                activity.setAtime(resultSet.getDate("atime"));
                activity.setMno(resultSet.getString("mno"));
                activity.setAno(resultSet.getInt("ano"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }

    public static APlace selectAPlaceById(int parseInt) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        APlace aPlace = new APlace();
        String sql = "select * from aplace where pno = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,parseInt);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                aPlace.setPname(resultSet.getString("pname"));
                aPlace.setPhead(resultSet.getInt("phead"));
                aPlace.setPphone(resultSet.getString("pphone"));
                aPlace.setPno(resultSet.getInt("pno"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aPlace;
    }
}
