package com.loxy01.test_start_new_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText name_EditTxt, surname_EditTxt, email_EditTxt, password_EditTxt, repeat_password_EditTxt;
    ImageButton button_continue_reg, button_already_have_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name_EditTxt = findViewById(R.id.Name_editText);
        surname_EditTxt = findViewById(R.id.SurName_editText);
        email_EditTxt = findViewById(R.id.Email_editText);
        password_EditTxt = findViewById(R.id.Password_editText);
        repeat_password_EditTxt = findViewById(R.id.Repeat_password_editText);

        button_continue_reg = findViewById(R.id.Button_continue_registration);
        button_already_have_account = findViewById(R.id.Button_already_have_account);

        mAuth = FirebaseAuth.getInstance();

        View.OnFocusChangeListener focusChangeListener = (v, hasFocus) -> {
            EditText editText = (EditText) v;
            int drawableResId = 0;
            if (!hasFocus && editText.getText().length() == 0) {
                if (v == name_EditTxt) {
                    drawableResId = R.drawable.svg_name_text;
                } else if (v == surname_EditTxt) {
                    drawableResId = R.drawable.svg_surname_text;
                } else if (v == email_EditTxt) {
                    drawableResId = R.drawable.svg_email_text;
                } else if (v == password_EditTxt) {
                    drawableResId = R.drawable.svg_password_text;
                } else {
                    drawableResId = R.drawable.svg_repeat_password_text;
                }
            }
            editText.setCompoundDrawablesWithIntrinsicBounds(drawableResId, 0, 0, 0);
        };

        name_EditTxt.setOnFocusChangeListener(focusChangeListener);
        surname_EditTxt.setOnFocusChangeListener(focusChangeListener);
        email_EditTxt.setOnFocusChangeListener(focusChangeListener);
        password_EditTxt.setOnFocusChangeListener(focusChangeListener);
        repeat_password_EditTxt.setOnFocusChangeListener(focusChangeListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        button_continue_reg.setOnClickListener(v -> {
            if(email_EditTxt.getText().toString().isEmpty()||password_EditTxt.getText().toString().isEmpty()){
                Toast.makeText(RegisterActivity.this, "Поля или поле ввода пустые!", Toast.LENGTH_SHORT).show();
            }
            //E-mail validate use Apache class
            else if(!(!email_EditTxt.getText().toString().isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email_EditTxt.getText().toString()).matches())){
                Toast.makeText(RegisterActivity.this, "Некорректный email!", Toast.LENGTH_SHORT).show();
            }
            else if(password_EditTxt.getText().length()<8){
                Toast.makeText(RegisterActivity.this, "Пароль слишком мал по длине", Toast.LENGTH_SHORT).show();
            }
            else if (!(password_EditTxt.getText().toString().equals(repeat_password_EditTxt.getText().toString()))){
                Toast.makeText(RegisterActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            }
            else{
                createUser(email_EditTxt.getText().toString().trim(), password_EditTxt.getText().toString().trim());
            }
        });

        button_already_have_account.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LogInScreenActivity.class));
        });

    }

    private void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("PROJECT_TAG", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(RegisterActivity.this, "User Create!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainMoviesActivity.class));
                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("PROJECT_TAG", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(RegisterActivity.this, "Аккаунт не был создан, пожалуйста проверьте поля или подключение к сети", Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
            }
        });
    }
}