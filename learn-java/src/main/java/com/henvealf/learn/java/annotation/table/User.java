package com.henvealf.learn.java.annotation.table;


import com.henvealf.learn.java.annotation.tablean.IntegerCol;
import com.henvealf.learn.java.annotation.tablean.StringCol;
import com.henvealf.learn.java.annotation.tablean.Table;

/**
 * Created by hongliang.yin/Henvealf on 2017/7/22.
 */
@Table
public class User {

    @IntegerCol("uid")
    public int id;

    @StringCol(length = 10)
    public String name;

    @StringCol(length = 60)
    public String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                "}\n";
    }
}
