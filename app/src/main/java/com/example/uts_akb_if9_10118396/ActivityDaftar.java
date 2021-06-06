// 03 Juni 2021
// 10118396
// Azzuhra
// IF-9

package com.example.uts_akb_if9_10118396;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uts_akb_if9_10118396.Crud.Create;
import com.example.uts_akb_if9_10118396.Crud.Read;
import com.example.uts_akb_if9_10118396.Crud.Update;
import com.example.uts_akb_if9_10118396.Database.DataHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityDaftar extends AppCompatActivity {

    String[] daftar;
    DataHelper dbcenter;
    protected Cursor cursor;
    ListView listView01;
    public static ActivityDaftar fd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDaftar.this, Create.class);
                startActivity(intent);
            }
        });


        fd = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }


    public void RefreshList() {
        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM catatan ORDER BY judul", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1);
        }

        listView01 = findViewById(R.id.listCatatan);
        listView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView01.setSelected(true);
        listView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Catatan", "Update Catatan", "Hapus Catatan"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDaftar.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), Read.class);
                                i.putExtra("judul", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent intent = new Intent(getApplicationContext(), Update.class);
                                intent.putExtra("judul", selection);
                                startActivity(intent);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("DELETE FROM catatan WHERE judul='" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listView01.getAdapter()).notifyDataSetInvalidated();

    }

}

