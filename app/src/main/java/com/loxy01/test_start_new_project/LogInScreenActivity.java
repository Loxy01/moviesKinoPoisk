package com.loxy01.test_start_new_project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class LogInScreenActivity extends AppCompatActivity {

    EditText email_EditTxt, password_EditTxt;
    ImageButton button_continue, button_go_regActivity;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    //Utils
    int backButtonClicks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(LogInScreenActivity.this, MainMoviesActivity.class));
        }

        email_EditTxt = findViewById(R.id.Email_editText);
        password_EditTxt = findViewById(R.id.Password_editText);
        button_continue = findViewById(R.id.Button_continue);
        button_go_regActivity = findViewById(R.id.Button_registration);

        View.OnFocusChangeListener focusChangeListener = (v, hasFocus) -> {
            if(hasFocus){
                if (v == email_EditTxt) {
                    email_EditTxt.setCompoundDrawables(null,null,null,null);
                } else {
                    password_EditTxt.setCompoundDrawables(null,null,null,null);
                }
            }else{
                if (v == email_EditTxt) {
                    if(email_EditTxt.getText().length()==0){
                        email_EditTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.svg_email_text, 0, 0, 0);
                    }
                } else {
                    if(password_EditTxt.getText().length()==0){
                        password_EditTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.svg_password_text, 0, 0, 0);
                    }
                }
            }
        };
        email_EditTxt.setOnFocusChangeListener(focusChangeListener);
        password_EditTxt.setOnFocusChangeListener(focusChangeListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        button_continue.setOnClickListener(v -> {
            if(email_EditTxt.getText().toString().isEmpty()||password_EditTxt.getText().toString().isEmpty()){
                Toast.makeText(LogInScreenActivity.this, "Поля пустые!", Toast.LENGTH_SHORT).show();
            }
            //E-mail validate use Apache class
            else if(!(!email_EditTxt.getText().toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email_EditTxt.getText().toString()).matches())){
                Toast.makeText(LogInScreenActivity.this, "Некорректный email!", Toast.LENGTH_SHORT).show();
            }
            else if(password_EditTxt.getText().length()<8){
                Toast.makeText(LogInScreenActivity.this, "Пароль слишком мал по длине", Toast.LENGTH_SHORT).show();
            }
            else{
                logInUser(email_EditTxt.getText().toString().trim(), password_EditTxt.getText().toString().trim());
            }
        });
        button_go_regActivity.setOnClickListener(v -> {
            startActivity(new Intent(LogInScreenActivity.this, RegisterActivity.class));
        });
    }

    private void logInUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("signInActivity", "signInWithEmail:success");
                Intent i = new Intent(LogInScreenActivity.this, MainMoviesActivity.class);
                //i.putExtra("userName", mAuth.getCurrentUser().getDisplayName());
                startActivity(i);
            } else {
                // If sign in fails, display a message to the user.
                Log.w("signInActivity", "signInWithEmail:failure", task.getException());
                Toast.makeText(LogInScreenActivity.this, "Имя пользователя или пароль введены неверно",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){}
        else {
            Log.e("errors", "onBackPressed error");
        }
        backButtonClicks++;
        if(backButtonClicks == 2){
            Toast.makeText(this, "Нажмите еще раз, чтобы выйти из приложения", Toast.LENGTH_SHORT).show();
        } else if(backButtonClicks == 3){
            ActivityCompat.finishAffinity(this);
        }
    }
}