package com.example.nbapp;

import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 强仔 on 2018/5/2.
 */

public class User extends DataSupport{

    private String userid;
    private String phone;
    private String password;
    private String name;
    private String gender;
    private Date birthday;
    private String statu;
    private Date createtime;
    private Date modifytime;

    public String getId(){
            return userid;
        }
    public void setId(){
        Random random=new Random();
        userid="SoRich"+random.nextInt(100000);
        }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getPassword(){
            return password;
        }
    public void setPassword(String password){
        this.password=password;
    }

    public void setPhone(String phone){
        this.phone=phone;
        }
    public String getPhone(){
            return phone;
        }

    public void setGender(String gender){
            this.gender=gender;
        }
    public String getGender(){return gender;}

    public void setBirthday(Date birthday){
        this.birthday=birthday;}
    public Date getBirthday(){
        return birthday;}

    public String getStatu(){
        return statu;
    }

    public void setStatu(){
        this.statu=statu;
    }

    public void setCreatetime(Date createtime){
        this.createtime=createtime;}
    public Date getCreatetime(){
        return createtime;}

    public void setModifytime(Date modifytime){
        this.modifytime=modifytime;}
    public Date getModifytime(){
        return modifytime;}

}
