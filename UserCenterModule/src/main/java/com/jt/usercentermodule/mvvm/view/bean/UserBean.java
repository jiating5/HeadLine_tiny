package com.jt.usercentermodule.mvvm.view.bean;

/**
 * 用户实体bean类
 */
public class UserBean {
    private int id;
    private String username;
    private String pwd;
    private String sex;
    private String birthday;

    public UserBean(int id, String username, String pwd, String sex, String birthday) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.sex = sex;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
