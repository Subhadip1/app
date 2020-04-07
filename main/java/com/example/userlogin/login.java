package com.example.userlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private int counter = 5;
    ImageView backbutton;
    TextView actionReg2;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;
    private TextView Info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Name = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.button);
        forgotPassword = (TextView) findViewById(R.id.tvForgetPassword);
        Info.setText("No of attempts remaining: 5");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            finish();
            startActivity(new Intent(login.this, secondactivity.class));

        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Name.getText().toString().length()<= 0 && (Password.getText().toString().length()<= 0)){
                    Toast.makeText(login.this, "Enter Login Credentials", Toast.LENGTH_LONG).show();
                }
                else if ((Name.getText().toString().length()<= 0))
                {Toast.makeText(login.this, "Enter Email to Login", Toast.LENGTH_LONG).show();
                }
                else if ((Password.getText().toString().length()<= 0)){
                    Toast.makeText(login.this, "Enter Password to Login", Toast.LENGTH_LONG).show();
                }
                else{
                validate(Name.getText().toString(), Password.getText().toString());
            }}
        });
        backbutton = findViewById(R.id.imageView6);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        actionReg2 = findViewById(R.id.action);
        actionReg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, registration.class);
                startActivity(intent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, PasswordActivity.class));
            }
        });
    }

    private void validate(String Name,String Password) {
            progressDialog.setMessage("Processing");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(Name, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        checkEmailVerification();
                    } else {
                        Toast.makeText(login.this, "Login Failed", Toast.LENGTH_LONG).show();
                        counter--;
                        Info.setText("No of attempts remaining: " + counter);
                        progressDialog.dismiss();
                        if (counter == 0) {
                            Login.setEnabled(false);
                        }
                    }

                }
            });

        }
        private void checkEmailVerification(){
            FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
            Boolean emailflag = firebaseUser.isEmailVerified();

            if (emailflag) {
                startActivity(new Intent(login.this, secondactivity.class));
            } else {
                Toast.makeText(login.this, "Verify your Email First to Login", Toast.LENGTH_LONG).show();
                firebaseAuth.signOut();
            }


        }
    }

