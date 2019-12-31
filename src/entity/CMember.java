package entity;

public class CMember {
    private Integer mno;
    private String mname;
    private String mssex;
    private String mbirth;
    private String mhobby;
    private String maddress;
    private String mphone;
    private Integer ishead;
    private String munit;

    public CMember(Integer mno, String mname, String mssex, String mbirth, String mhobby, String maddress, String mphone, Integer ishead, String munit) {
        this.mno = mno;
        this.mname = mname;
        this.mssex = mssex;
        this.mbirth = mbirth;
        this.mhobby = mhobby;
        this.maddress = maddress;
        this.mphone = mphone;
        this.ishead = ishead;
        this.munit = munit;
    }

    public String getMunit() {
        return munit;
    }

    public void setMunit(String munit) {
        this.munit = munit;
    }

    public Integer getMno() {
        return mno;
    }

    public void setMno(Integer mno) {
        this.mno = mno;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMssex() {
        return mssex;
    }

    public void setMssex(String mssex) {
        this.mssex = mssex;
    }

    public String getMbirth() {
        return mbirth;
    }

    public void setMbirth(String mbirth) {
        this.mbirth = mbirth;
    }

    public String getMhobby() {
        return mhobby;
    }

    public void setMhobby(String mhobby) {
        this.mhobby = mhobby;
    }

    public String getMaddress() {
        return maddress;
    }

    public void setMaddress(String maddress) {
        this.maddress = maddress;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public Integer getIshead() {
        return ishead;
    }

    public void setIshead(Integer ishead) {
        this.ishead = ishead;
    }

    public CMember() {
    }
}
