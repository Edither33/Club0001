package entity;

import java.sql.Date;

public class Activity {
    private Integer ano;
    private String aname;
    private Date atime;
    private String mno;
    private float aprice;

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public float getAprice() {
        return aprice;
    }

    public void setAprice(float aprice) {
        this.aprice = aprice;
    }

    public Activity() {
    }

    public Activity(String aname, Date atime, String mno, float aprice) {
        this.aname = aname;
        this.atime = atime;
        this.mno = mno;
        this.aprice = aprice;
    }

    public Activity(Integer ano, String aname, Date atime, String mno, float aprice) {
        this.ano = ano;
        this.aname = aname;
        this.atime = atime;
        this.mno = mno;
        this.aprice = aprice;
    }
}
