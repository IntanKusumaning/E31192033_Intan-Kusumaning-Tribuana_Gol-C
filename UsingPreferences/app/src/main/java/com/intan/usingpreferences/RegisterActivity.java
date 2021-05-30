package com.intan.usingpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private EditText mViewUser, mViewPassword, mViewRepassword; //pendeklarasian variabel yang digunakan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /* Menginisialisasi variabel dengan form User, form Password, dan form Repassword dari layout RegisterActivity */
        mViewUser = findViewById(R.id.et_emailSignUp);
        mViewPassword = findViewById(R.id.et_passwordSignUp);
        mViewRepassword = findViewById(R.id.et_passwordSignUp2);

        /* Menjalankan method razia() jika tombol SignUp di keyboard ditekan */
        mViewRepassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia();
                    return true;
                }
                return false;
            }
        });

        /* Digunakan untuk menjalankan method razia() jika tombol SignUp ditekan */
        findViewById(R.id.button_signupSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });
    }

    /*Digunakan untuk men-check inputan User dan Password dan memberikan akses ke halaman MainActivity */
    private void razia(){
        /* Mereset semua error dan fokus menjadi default */
        mViewUser.setError(null);
        mViewPassword.setError(null);
        mViewRepassword.setError(null);
        View fokus = null;
        boolean cancel =false;

        /* Untuk mengambil text dari form User, Password, Repassword dengan variabel baru bertipe string */
        String repassword = mViewRepassword.getText().toString();
        String user = mViewUser.getText().toString();
        String password = mViewPassword.getText().toString();

        /* Digunakan untuk men-check jika form User kosong atau data user yang belum terdaftar, maka akan memunculkan sebuah pesan */
        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            fokus = mViewUser;
            cancel = true;
        }else if (cekUser(user)){
            mViewUser.setError("This Username is already exist");
            fokus = mViewUser;
            cancel = true;
        }

        /* Jika form password kosong dan memenuhi kriteria di method cekPassword maka,
        * reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword */
        if (TextUtils.isEmpty(password)) {
            mViewPassword.setError("This field is required");
            fokus = mViewPassword;
            cancel = true;
        }else if (!cekPassword(password,repassword)) {
            mViewRepassword.setError("This password is incorrect");
            fokus = mViewRepassword;
            cancel = true;
        }

        /* Jika cancel true, variabel fokus mendapatkan fokus. Jika false, maka
         * kembali ke halaman LoginActivity dengan User dan Password yang telah terdaftar */
        if (cancel) {
            fokus.requestFocus();
        }else {
            Preferences.setRegisteredUser(getBaseContext(),user);
            Preferences.setRegisteredPass(getBaseContext(),password);
            finish();
        }
    }

    /* Data bernilai True jika parameter password sama dengan parameter repassword */
    private boolean cekPassword(String password, String repassword) {
        return password.equals(repassword);
    }

    /* Data bernilai True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}