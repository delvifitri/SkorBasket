package com.delphi.skorbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HasilActivity extends AppCompatActivity {
    private TextView tvKet, tvHasil;
    private ImageView imgHasil, imgTroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        tvKet = findViewById(R.id.hasil_tvKet);
        tvHasil = findViewById(R.id.hasil_tvHasil);
        imgHasil = findViewById(R.id.hasil_imgHasil);
        imgTroll = findViewById(R.id.imgTroll);

        if (getIntent().getBooleanExtra("seri", false) == true) {
            tvKet.setVisibility(View.INVISIBLE);
            imgHasil.setVisibility(View.INVISIBLE);
            imgTroll.setVisibility(View.VISIBLE);
            tvHasil.setText("Hasilnya Seri");
        } else {
            tvKet.setVisibility(View.VISIBLE);
            imgHasil.setVisibility(View.VISIBLE);
            imgTroll.setVisibility(View.INVISIBLE);
            tvHasil.setText(getIntent().getStringExtra("timMenang"));
            imgHasil.setImageURI(Uri.parse(getIntent().getStringExtra("imgTimMenang")));
        }
    }
}