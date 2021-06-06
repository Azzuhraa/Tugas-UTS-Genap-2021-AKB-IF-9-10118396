// 03 Juni 2021
// 10118396
// Azzuhra
// IF-9

package com.example.uts_akb_if9_10118396;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentProfil extends Fragment {
    TextView nim, nama, kelas, email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profil, container, false);
        nim = view.findViewById(R.id.txtNIM);
        nama = view.findViewById(R.id.txtNama);
        kelas = view.findViewById(R.id.txtKelas);
        email = view.findViewById(R.id.txtEmail);

        nim.setText("NIM: 10118396");
        nama.setText("Nama: Azzuhra");
        kelas.setText("Kelas: IF-9");
        email.setText("Deskripsi: azzuhra28@gmail.com");
        return view;
    }
}

