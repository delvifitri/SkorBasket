package com.delphi.skorbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SkorActivity extends AppCompatActivity {
    private TextView tvTim1, tvTim2, tvSkor1, tvSkor2;
    private ImageView imgTim1, imgTim2;
    private Button btn1plus1, btn1plus2, btn1plus3;
    private Button btn2plus1, btn2plus2, btn2plus3;
    private Button btnReset, btnHasil;
    private Integer skor1, skor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor);

        tvTim1 = findViewById(R.id.skor_tvTim1);
        tvTim2 = findViewById(R.id.skor_tvTim2);
        tvSkor1 = findViewById(R.id.skor_tvSkor1);
        tvSkor2 = findViewById(R.id.skor_tvSkor2);
        imgTim1 = findViewById(R.id.skor_imgTim1);
        imgTim2 = findViewById(R.id.skor_imgTim2);
        btn1plus1 = findViewById(R.id.skor_btn1plus1);
        btn1plus2 = findViewById(R.id.skor_btn1plus2);
        btn1plus3 = findViewById(R.id.skor_btn1plus3);
        btn2plus1 = findViewById(R.id.skor_btn2plus1);
        btn2plus2 = findViewById(R.id.skor_btn2plus2);
        btn2plus3 = findViewById(R.id.skor_btn2plus3);
        btnReset = findViewById(R.id.skor_btnReset);
        btnHasil = findViewById(R.id.skor_btnHasil);

        String tim1 = getIntent().getStringExtra("namaTim1");
        String tim2 = getIntent().getStringExtra("namaTim2");

        String imgTim1UriString = getIntent().getStringExtra("imgTim1");
        String imgTim2UriString = getIntent().getStringExtra("imgTim2");
        imgTim1.setImageURI(Uri.parse(imgTim1UriString));
        imgTim2.setImageURI(Uri.parse(imgTim2UriString));

        skor1 = 0;
        skor2 = 0;
        tvSkor1.setText(skor1.toString());
        tvSkor2.setText(skor2.toString());

        btn1plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor1 += 1;
                tvSkor1.setText(skor1.toString());
            }
        });
        btn1plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor1 += 2;
                tvSkor1.setText(skor1.toString());
            }
        });
        btn1plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor1 += 3;
                tvSkor1.setText(skor1.toString());
            }
        });

        btn2plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor2 += 1;
                tvSkor2.setText(skor2.toString());
            }
        });
        btn2plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor2 += 2;
                tvSkor2.setText(skor2.toString());
            }
        });
        btn2plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor2 += 3;
                tvSkor2.setText(skor2.toString());
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skor1 = 0;
                skor2 = 0;
                tvSkor1.setText(skor1.toString());
                tvSkor2.setText(skor2.toString());
            }
        });

        btnHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean seri = false;
                String timMenang = null;
                String imgTimMenang = null;
                if (skor1 == skor2) {
                    seri = true;
                } else if (skor1 > skor2) {
                    timMenang = tim1;
                    imgTimMenang = imgTim1UriString;
                } else {
                    timMenang = tim2;
                    imgTimMenang = imgTim2UriString;
                }

                Intent i = new Intent(SkorActivity.this, HasilActivity.class);
                i.putExtra("seri", seri);
                i.putExtra("timMenang", timMenang);
                i.putExtra("imgTimMenang", imgTimMenang);
                startActivity(i);
            }
        });
    }
}