package com.libaoming.demoexce.demoexce.test;

import com.baselibrary.network.BaseResponse;

/**
 * Created by Libaoming on 28/4/2018.
 * 16 hour 57 minute
 * project_name : DemoExce
 */

public class UserBean extends BaseResponse {
    private Integer id;
    private String name;
    private int age;
    private String sex;
    private String password;
    public String msg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
