package com.example.nbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DetailActivity extends AppCompatActivity {
    private ImageButton detail;
    private ImageButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        detail=(ImageButton)findViewById(R.id.btn_menu_detail);
        detail.setImageResource(R.drawable.detail);

        add=(ImageButton)findViewById(R.id.btn_menu_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this,AddExpendActivity.class);
                startActivity(intent);
            }
        });
    }
}
