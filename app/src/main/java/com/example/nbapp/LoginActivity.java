package com.example.nbapp;

import android.accounts.Account;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private String TAG="LoginActivity";

    //登录界面的控件
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private  Button register;
    private CheckBox remeberPass;

    //使用sharedpreferences对象完成记住密码功能
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    static private String account;
    static private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: ");

        LitePal.getDatabase();


        //初始化登录界面控件
        accountEdit=(EditText)findViewById(R.id.user_phone);
        passwordEdit=(EditText)findViewById(R.id.password);
        remeberPass=(CheckBox) findViewById(R.id.remeber_pass);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);

        //记住密码功能 p209 6.3
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemeber=pref.getBoolean("remeber_password",false);
        if (isRemeber){
            //将账号和密码都设置到文本中
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            remeberPass.setChecked(true);
        }

        //点击登录按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSucessful=false;
                account=accountEdit.getText().toString();
                password=passwordEdit.getText().toString();
                List<User> users=DataSupport.findAll(User.class);

                for(User user:users){


                    if(password.equals(user.getPassword())&& account.equals(user.getPhone())){//用户名和密码正确
                        editor=pref.edit();
                        if(remeberPass.isChecked()){//检查复选框是否被选中
                            editor.putBoolean("remeber_password",true);
                            editor.putString("account",account);
                            editor.putString("password",password);

                        }
                        else {
                            editor.clear();
                        }
                        editor.apply();

                        Intent intent=new Intent(LoginActivity.this,UserActivity.class);
                        intent.putExtra("account",account);
                        startActivity(intent);
                        isSucessful=true;
                    }
                }
                if(!isSucessful){
                    //用户名或密码错误
                    Toast.makeText(LoginActivity.this,"用户名或密码错误！",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

        //点击注册按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}


/* DataSupport.deleteAll(ExpendType_Icon.class);
        DataSupport.deleteAll(IncomeType_Icon.class);

        IncomeType_Icon salary=new IncomeType_Icon();
        IncomeType_Icon partjob=new IncomeType_Icon();
        IncomeType_Icon investin=new IncomeType_Icon();
        IncomeType_Icon redpacket=new IncomeType_Icon();
        IncomeType_Icon other_i=new IncomeType_Icon();
        salary.setType("工资");
        partjob.setType("兼职");
        investin.setType("理财");
        redpacket.setType("红包");
        other_i.setType("其他");

        salary.setIconid(R.drawable.salary);
        partjob.setIconid(R.drawable.partjob);
        investin.setIconid(R.drawable.investin);
        redpacket.setIconid(R.drawable.redpacket);
        other_i.setIconid(R.drawable.other);

        salary.save();
        partjob.save();
        investin.save();
        redpacket.save();
        other_i.save();

        ExpendType_Icon cater=new ExpendType_Icon();
        ExpendType_Icon beaty=new ExpendType_Icon();
        ExpendType_Icon clothes=new ExpendType_Icon();
        ExpendType_Icon communi=new ExpendType_Icon();
        ExpendType_Icon digtal=new ExpendType_Icon();
        ExpendType_Icon donate=new ExpendType_Icon();
        ExpendType_Icon food=new ExpendType_Icon();
        ExpendType_Icon gifts=new ExpendType_Icon();
        ExpendType_Icon investout=new ExpendType_Icon();
        ExpendType_Icon medical=new ExpendType_Icon();
        ExpendType_Icon pet=new ExpendType_Icon();
        ExpendType_Icon play=new ExpendType_Icon();
        ExpendType_Icon travel=new ExpendType_Icon();
        ExpendType_Icon study=new ExpendType_Icon();
        ExpendType_Icon other_e=new ExpendType_Icon();

        cater.setType("餐饮");
        clothes.setType("衣服");
        beaty.setType("丽人");
        communi.setType("通讯");
        digtal.setType("数码");
        donate.setType("捐赠");
        food.setType("食物");
        investout.setType("理财");
        gifts.setType("礼物");
        medical.setType("医疗");
        pet.setType("宠物");
        play.setType("娱乐");
        travel.setType("旅行");
        study.setType("学习");
        other_e.setType("其他");

        cater.setIconid(R.drawable.cater);
        clothes.setIconid(R.drawable.clothes);
        beaty.setIconid(R.drawable.beaty);
        communi.setIconid(R.drawable.communi);
        digtal.setIconid(R.drawable.digital);
        donate.setIconid(R.drawable.donate);
        food.setIconid(R.drawable.food);
        investout.setIconid(R.drawable.investout);
        gifts.setIconid(R.drawable.gifts);
        medical.setIconid(R.drawable.medical);
        pet.setIconid(R.drawable.pet);
        play.setIconid(R.drawable.play);
        travel.setIconid(R.drawable.travel);
        study.setIconid(R.drawable.study);
        other_e.setIconid(R.drawable.other);

        cater.save();
        clothes.save();
        beaty.save();
        communi.save();
        digtal.save();
        donate.save();
        food.save();
        investout.save();
        gifts.save();
        medical.save();
        pet.save();
        play.save();
        travel.save();
        study.save();
        other_e.save();*/