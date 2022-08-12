package com.diazna.simptacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton btnTambah;
    ImageView imgNodata;
    TextView txtNodata;

    dbHelper myDB;
    ArrayList<String> id, nama, noTelp, email;
    customAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnTambah = findViewById(R.id.btnTambah);
        imgNodata = findViewById(R.id.imgNodata);
        txtNodata = findViewById(R.id.txtNodata);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, tambahActivity.class);
                startActivity(intent);
            }
        });

        myDB = new dbHelper(MainActivity.this);
        id = new ArrayList<>();
        nama = new ArrayList<>();
        noTelp = new ArrayList<>();
        email = new ArrayList<>();

        storeDataInArrays();
        ca = new customAdapter(MainActivity.this, this, id, nama, noTelp, email);
        recyclerView.setAdapter(ca);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    protected void onActivityResult(int reqestCode, int resultCOde, @Nullable Intent data)
    {
        super.onActivityResult(reqestCode, resultCOde, data);
        if(reqestCode == 1)
        {
            recreate();
        }
    }

    void storeDataInArrays()
    {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0)
        {
            imgNodata.setVisibility(View.VISIBLE);
            txtNodata.setVisibility(View.VISIBLE);
        }
        else
        {
            while (cursor.moveToNext())
            {
                id.add(cursor.getString(0));
                nama.add(cursor.getString(1));
                noTelp.add(cursor.getString(2));
                email.add(cursor.getString(3));
            }
            imgNodata.setVisibility(View.GONE);
            txtNodata.setVisibility(View.GONE);
        }
    }
}