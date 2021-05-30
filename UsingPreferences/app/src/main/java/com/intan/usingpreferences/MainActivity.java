package com.intan.usingpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Deklarasi dan menginisialisasi variabel nama dengan label nama dari layout MainActivity */
        TextView nama = findViewById(R.id.tv_namaMain);

        /* Men-set label nama dengan data user sedang login dari Preferences */
        nama.setText(Preferences.getLoggedInUser(getBaseContext()));

        /* Men-set Status dan User yang sedang login menjadi default atau kosong di
        * data Preferences. Kemudian menuju ke LoginActivity */
        findViewById(R.id.button_logoutMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Menghapus Status login dan kembali ke Login Activity
                Preferences.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });
    }
}