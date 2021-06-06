// 03 Juni 2021
// 10118396
// Azzuhra
// IF-9

package com.example.uts_akb_if9_10118396.Crud;

import android.content.Context;
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

public class Create extends AppCompatActivity {
    DataHelper dbcenter;
    Button btn1, btn2;
    EditText judul, kategori, isi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        dbcenter = new DataHelper(this);

        judul = findViewById(R.id.editJudul);
        kategori = findViewById(R.id.editKategori);
        isi = findViewById(R.id.editIsi);

        btn1 = findViewById(R.id.btn_submit);

        nim.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbcenter.getWritableDatabase();
                db.execSQL("INSERT INTO catatan (judul, kategori, isi) values ('" +
                        judul.getText().toString() + "','" +
                        kategori.getText().toString() + "','" +
                        isi.getText().toString() + "','" + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                ActivityDaftar.fd.RefreshList();
                finish();
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

