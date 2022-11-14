package com.delphi.skorbasket;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int TIM1_REQUEST_CODE = 1001;
    private static final int TIM2_REQUEST_CODE = 1002;
    private static final String TAG = MainActivity.class.getCanonicalName();

    private EditText etTim1, etTim2;
    private ImageView imgTim1, imgTim2;
    private Button btnNext;

    String imgTim1UriString, imgTim2UriString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTim1 = findViewById(R.id.main_etTim1);
        etTim2 = findViewById(R.id.main_etTim2);
        imgTim1 = findViewById(R.id.main_imgTim1);
        imgTim2 = findViewById(R.id.main_imgTim2);
        btnNext = findViewById(R.id.main_btnNext);

        imgTim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), TIM1_REQUEST_CODE);
            }
        });

        imgTim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), TIM2_REQUEST_CODE);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SkorActivity.class);
                i.putExtra("namaTim1", etTim1.getText().toString());
                i.putExtra("namaTim2", etTim2.getText().toString());
                i.putExtra("imgTim1", imgTim1UriString);
                i.putExtra("imgTim2", imgTim2UriString);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, data.getDataString());
        if(resultCode == RESULT_CANCELED){
            Log.d(TAG, "Pilih gambar dicancel");
            return;
        }
        else if(requestCode == TIM1_REQUEST_CODE){
            if(data != null){
                try {
                    Uri imageUri = data.getData();
                    imgTim1UriString = imageUri.toString();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imgTim1.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
        else if(requestCode == TIM2_REQUEST_CODE){
            if(data != null){
                try {
                    Uri imageUri = data.getData();
                    imgTim2UriString = imageUri.toString();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imgTim2.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
    }
}