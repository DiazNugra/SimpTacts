package com.diazna.simptacts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateActivity extends AppCompatActivity {

    EditText editNama, editNoTelp, editEmail;
    Button btnUpdate, btnHapus;

    String id, nama, noTelp, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editNama = findViewById(R.id.editNama2);
        editNoTelp = findViewById(R.id.editNoTelp2);
        editEmail = findViewById(R.id.editEmail2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnHapus = findViewById(R.id.btnHapus);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null)
        {
            ab.setTitle("Edit Kontak " + nama);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper myDB = new dbHelper(updateActivity.this);
                nama = editNama.getText().toString().trim();
                noTelp = editNoTelp.getText().toString().trim();
                email = editEmail.getText().toString().trim();
                myDB.updateData(id, nama, noTelp, email);
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                konfirmasi();
            }
        });

    }

    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nama") &&
                getIntent().hasExtra("noTelp") && getIntent().hasExtra("email")){

            //ambil data dari intent
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama");
            noTelp = getIntent().getStringExtra("noTelp");
            email = getIntent().getStringExtra("email");

            //set data dari intent
            editNama.setText(nama);
            editNoTelp.setText(noTelp);
            editEmail.setText(email);
        }
        else
            Toast.makeText(this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
    }

    void konfirmasi()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Data Kontak " + nama + " ?");
        builder.setMessage("Apakah Anda Yakin Akan Menghapus Data Kontak "+ nama + " ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper myDb = new dbHelper(updateActivity.this);
                myDb.deleteSatuData(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}