package com.example.androidgezginapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidgezginapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPassword;
    private EditText userConfirmPassword;
    private String email;
    private String password;
    private String confirmPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = (EditText) findViewById(R.id.tvEmail);
        userPassword = (EditText) findViewById(R.id.password);
        userConfirmPassword = (EditText) findViewById(R.id.reg_password);
        mAuth = FirebaseAuth.getInstance();
        Button btnSign = (Button) findViewById(R.id.sing_in);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserRegister();
            }
        });
    }

    private void getUserRegister() {
        email = userEmail.getText().toString().trim();
        password = userPassword.getText().toString().trim();
        confirmPassword = userConfirmPassword.getText().toString().trim();

        if(!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
            if(password.contains(confirmPassword)) {
                register();
            }else {
                Toast.makeText(getApplicationContext(), "Kayıt için parola ve parola onay aynı olmalıdır.", Toast.LENGTH_LONG).show();

            }
        }else
            Toast.makeText(getApplicationContext(), "Kayıt için tüm alanları doldurunuz.", Toast.LENGTH_LONG).show();
    }

    private void register() {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    private static final String TAG = "RegisterActivity";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Kayıt Başarılı", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);

                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

