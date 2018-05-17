package com.example.nbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText password;
    private EditText account;
    private Button register;
    private Button back;
    private boolean isSuccessful=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        password=(EditText)findViewById(R.id.password);
        account=(EditText)findViewById(R.id.user_phone);
        register=(Button)findViewById(R.id.register);
        back=(Button)findViewById(R.id.back);

        final List<User> userList= DataSupport.findAll(User.class);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               for(User user:userList){
                   if(user.getPhone().equals(account.getText().toString())){
                       Toast.makeText(RegisterActivity.this,"该用户名已被注册",
                               Toast.LENGTH_SHORT).show();
                       isSuccessful=false;
                       break;
                   }
               }
                if(isSuccessful){
                    User user=new User();
                    user.setPhone(account.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.save();

                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
