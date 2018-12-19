package com.example.zc.mainactivity;

import org.litepal.LitePalApplication;
import org.litepal.crud.LitePalSupport;
import org.litepal.util.Const;

public class People extends LitePalSupport {
    private String name;
    private String telphone;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public String getTel(){
        return this.telphone;
    }
    public void setTel(String tel){
        this.telphone = tel;
    }

}
