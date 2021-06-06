// 03 Juni 2021
// 10118396
// Azzuhra
// IF-9

package com.example.uts_akb_if9_10118396.Crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uts_akb_if9_10118396.Database.DataHelper;

public class Read extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbcenter;
    Button btn1;
    TextView judul, kategori, isi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        dbcenter = new DataHelper(this);
        judul = findViewById(R.id.textView1);
        kategori = findViewById(R.id.textView2);
        isi = findViewById(R.id.textView3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM catatan WHERE judul='" + getIntent().getStringExtra("judul") + "';", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            judul.setText("Judul: " + cursor.getString(0));
            kategori.setText("Kategori: " + cursor.getString(1));
            isi.setText("Isi: " + cursor.getString(2));
        }

        btn1 = findViewById(R.id.btn_lihat);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

