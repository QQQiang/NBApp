package com.example.nbapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class UserActivity extends AppCompatActivity {
    private ImageView iv_nickname;
    private ImageView iv_sex;
    private  ImageView iv_birth;
    private  ImageView iv_account;
    private EditText et_nickename;


    private TextView tv_nickname;
    private TextView tv_sex;
    private Button bt_birth;
    private TextView tv_account;
    private Button bt_exit;

    private ImageButton detail;
    private ImageButton add;
    private ImageButton find;
    private ImageButton my;
    private User user=new User();

  /*  private SharedPreferences.Editor editor;
    private SharedPreferences pref;*/

    private int year,month,day;

    private String account;

    @Override
    public boolean releaseInstance() {
        return super.releaseInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        tv_nickname=(TextView)findViewById(R.id.nickname);
        tv_sex=(TextView)findViewById(R.id.sex);
        bt_birth=(Button) findViewById(R.id.birth);
        tv_account=(TextView)findViewById(R.id.account);
        iv_nickname=(ImageView)findViewById(R.id.iv_nick);
        iv_birth=(ImageView)findViewById(R.id.iv_birth);
        iv_sex=(ImageView)findViewById(R.id.iv_sex);
        iv_account=(ImageView)findViewById(R.id.iv_account);
        et_nickename=new EditText(UserActivity.this);
        bt_exit=(Button)findViewById(R.id.bt_exit);

        add = (ImageButton)findViewById(R.id.btn_menu_add);
        detail = (ImageButton) findViewById(R.id.btn_menu_detail);
        find = (ImageButton) findViewById(R.id.btn_menu_find);
        my=(ImageButton)findViewById(R.id.btn_menu_my);

        Intent intent=getIntent();
        account=intent.getStringExtra("account");

        my.setImageResource(R.drawable.my);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, com.example.nbapp.DetailActivity.class);
                startActivity(intent);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, com.example.nbapp.FindActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setImageResource(R.drawable.back);
                Intent intent = new Intent(UserActivity.this, com.example.nbapp.AddExpendActivity.class);
                startActivity(intent);
            }
        });

        //加载默认值
        List<User> userList=DataSupport.findAll(User.class);
        for(User user:userList ){
            if(user.getPhone().equals(account)){
                this.user=user;
            }
        }

        tv_nickname.setText(user.getName());
        tv_sex.setText(user.getGender());
        tv_account.setText(user.getPhone());
        bt_birth.setText(user.getBirthday());

        iv_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(UserActivity.this);
                dialog.setTitle("编辑昵称")
                        .setView(et_nickename)
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               tv_nickname.setText(et_nickename.getText());
                                user.setName(et_nickename.getText().toString());
                                user.save();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

       final String[] arry_sex =new String[] {"男","女"};

        iv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(UserActivity.this);
                dialog.setTitle("请选择")
                        .setSingleChoiceItems(arry_sex, 0,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        String sex = arry_sex[which];
                                        tv_sex.setText(sex);
                                        user.setGender(sex);
                                        user.save();
                                        dialog.dismiss();
                                    }
                                }
                        )
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });


        iv_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        bt_birth.setText(year+"-"+(++month)+"-"+day);
                        user.setBirthday(year+"-"+(++month)+"-"+day);
                        user.save();
                        
                        //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(UserActivity.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();

            }
        });

        tv_account.setText(account);

      //  editor.putString("account",tv_account.getText().toString());



        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
