package com.diazna.simptacts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.myViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList id, nama, noTelp, email;


    customAdapter(Activity activity,
                  Context context,
                  ArrayList id,
                  ArrayList nama,
                  ArrayList noTelp,
                  ArrayList email)
    {
            this.activity = activity;
            this.context = context;
            this.id = id;
            this.nama = nama;
            this.noTelp = noTelp;
            this.email = email;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.baris_kontak, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.nama_txt.setText(String.valueOf(nama.get(position)));
        holder.noTelp_txt.setText(String.valueOf(noTelp.get(position)));
        holder.email_txt.setText(String.valueOf(email.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, updateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("nama", String.valueOf(nama.get(position)));
                intent.putExtra("noTelp", String.valueOf(noTelp.get(position)));
                intent.putExtra("email", String.valueOf(email.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return id.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, nama_txt, noTelp_txt, email_txt;
        RelativeLayout mainLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            //id_txt = itemView.findViewById(R.id.txtId);
            nama_txt = itemView.findViewById(R.id.txtNama);
            noTelp_txt = itemView.findViewById(R.id.txtNoTelp);
            email_txt = itemView.findViewById(R.id.txtEmail);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
