package com.intan.usingpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText mViewUser, mViewPassword; //pendeklarasian variabel yang digunakan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Menginisialisasikan variabel dengan form User dan form Password dari Layout LoginActivity */
        mViewUser = findViewById(R.id.et_emailSignIn);
        mViewPassword = findViewById(R.id.et_passwordSignIn);

        /* Digunakan untuk menjalankan method razia() jika tombol SignIn di keyboard ditekan */
        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia();
                    return true;
                }
                return false;
            }
        });

        /* Digunakan untuk menjalankan method razia() jika merasa tombol SignIn ditekan */
        findViewById(R.id.button_signinSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });

        /* Digunakan untuk berpidah ke halaman RegisterActivity jika tombol SignUp ditekan */
        findViewById(R.id.button_signupSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
    }

    /* Digunakan untuk berpidah ke halaman MainActivity jika data Status Login dari data Preferences bernilai true */
    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }
    }

    /* Digunakan untuk men-check inputan User dan Password dan memberikan akses ke halaman MainActivity */
    private void razia() {
        /* Mereset semua error dan fokus menjadi default */
        mViewUser.setError(null);
        mViewPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Untuk mengambil text dari form User dan form Password dengan variabel baru bertipe string */
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        /* Digunakan untuk men-check jika form User kosong atau data user yang belum terdaftar, maka akan memunculkan sebuah pesan */
        if (TextUtils.isEmpty(user)) {
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        }else if (!cekUser(user)) {
            mViewUser.setError("This Username is not found");
            fokus = mViewUser;
            cancel = true;
        }

        /* Digunakan untuk men-check jika form Password kosong atau data user yang belum terdaftar, maka akan memunculkan sebuah pesan */
        if (TextUtils.isEmpty(password)) {
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        }else if (!cekPassword(password)) {
            mViewPassword.setError("This password is incorrect");
            fokus = mViewPassword;
            cancel = true;
        }

        /* Jika cancel true, variabel fokus mendapatkan fokus */
        if (cancel) fokus.requestFocus();
        else masuk();
    }

    /* Menuju ke halaman MainActivity dan Set User dan Status sedang login , di Preferences */
    private void masuk() {
        Preferences.setLoggedInUser(getBaseContext(), Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(), true);
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }

    /* Data bernilai True jika parameter password sama dengan data password yang terdaftar dari Preferences */
    private boolean cekPassword (String password) {
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    /* Data bernilai True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}