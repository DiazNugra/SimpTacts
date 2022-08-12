package com.diazna.simptacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class tambahActivity extends AppCompatActivity {

    EditText editNama, editNoTelp, editEmail;
    Button btnSubmit;

    FloatingActionButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        editNama    = findViewById(R.id.editNama);
        editNoTelp  = findViewById(R.id.editNoTelp);
        editEmail   = findViewById(R.id.editEmail);
        btnSubmit   = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper myDB = new dbHelper(tambahActivity.this);
                myDB.addContact(editNama.getText().toString().trim(),
                                editNoTelp.getText().toString().trim(),
                                editEmail.getText().toString().trim());
            }
        });

//        btnBack = findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(tambahActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}