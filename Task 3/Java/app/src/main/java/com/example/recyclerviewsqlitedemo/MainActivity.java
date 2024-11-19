package com.example.recyclerviewsqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText et_name, et_email, et_age;
    Button btn_insert, btn_view;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_age = findViewById(R.id.et_age);
        btn_insert = findViewById(R.id.btn_insert);
        btn_view = findViewById(R.id.btn_view);

        databaseHelper = new DatabaseHelper(this);
        
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserList.class));
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String age = et_age.getText().toString();

                Boolean checkInsertTable = databaseHelper.insertUserData(name, email, age);
                if (checkInsertTable) {
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    et_name.setText("");
                    et_email.setText("");
                    et_age.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}