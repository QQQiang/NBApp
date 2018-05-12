package com.example.nbapp;

import android.accounts.Account;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                String  account=accountEdit.getText().toString();
                String  password=passwordEdit.getText().toString();

                if(password.equals("12345")&&account.equals("12345")){//用户名和密码正确
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

                    Intent intent=new Intent(LoginActivity.this, com.example.nbapp.DetailActivity.class);
                    startActivity(intent);
                }
                else {//用户名或密码错误
                    Toast.makeText(LoginActivity.this,"用户名或密码错误！",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //点击注册按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterPhoneActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
