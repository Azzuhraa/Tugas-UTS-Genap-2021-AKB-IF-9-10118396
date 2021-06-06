// 03 Juni 2021
// 10118396
// Azzuhra
// IF-9

package com.example.uts_akb_if9_10118396.Crud;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uts_akb_if9_10118396.ActivityDaftar;
import com.example.uts_akb_if9_10118396.Database.DataHelper;

public class Update extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbcenter;
    Button btn1, btn2;
    EditText judul, kategori, isi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbcenter = new DataHelper(this);

        judul = findViewById(R.id.updateText1);
        kategori = findViewById(R.id.updateText2);
        isi = findViewById(R.id.updateText3);

        judul.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM catatan WHERE judul='"+getIntent().getStringExtra("judul") +"';", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            judul.setText(cursor.getString(0));
            kategori.setText(cursor.getString(1));
            isi.setText(cursor.getString(2));
        }

        btn1 = findViewById(R.id.btn_update1);
        btn2 = findViewById(R.id.btn_update2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbcenter.getWritableDatabase();
                db.execSQL("UPDATE catatan SET judul='"+ judul.getText().toString() +
                        "', kategori='"+ kategori.getText().toString() +
                        "', isi='"+ isi.getText().toString() +
                        "' WHERE judul='" + judul.getText().toString()+"';");
                Toast.makeText(getApplicationContext(), "Update", Toast.LENGTH_SHORT).show();
                ActivityDaftar.fd.RefreshList();
                finish();

                // pangggil method di fragmentDaftar
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

