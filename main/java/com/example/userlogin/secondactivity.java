package com.example.userlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class secondactivity extends AppCompatActivity {

     private FirebaseAuth firebaseAuth;
     private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);


        TextView tv1 =(TextView) findViewById(R.id.tv1);
        tv1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView tv2 =(TextView) findViewById(R.id.tv2);
        tv2.setMovementMethod(LinkMovementMethod.getInstance());
        TextView tv3 =(TextView) findViewById(R.id.tv3);
        tv3.setMovementMethod(LinkMovementMethod.getInstance());
        TextView tv4 =(TextView) findViewById(R.id.tv4);
        tv4.setMovementMethod(LinkMovementMethod.getInstance());
        TextView tv5 =(TextView) findViewById(R.id.tv5);
        tv5.setMovementMethod(LinkMovementMethod.getInstance());
        TextView tv6 =(TextView) findViewById(R.id.tv6);
        tv6.setMovementMethod(LinkMovementMethod.getInstance());
        TextView tv7 =(TextView) findViewById(R.id.tv7);
        tv7.setMovementMethod(LinkMovementMethod.getInstance());
        TextView tv8 =(TextView) findViewById(R.id.tv8);
        tv8.setMovementMethod(LinkMovementMethod.getInstance());
        TextView tv9 =(TextView) findViewById(R.id.tv9);
        tv9.setMovementMethod(LinkMovementMethod.getInstance());
        TextView tv10 =(TextView) findViewById(R.id.tv10);
        tv10.setMovementMethod(LinkMovementMethod.getInstance());


        firebaseAuth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(secondactivity.this, MainActivity.class));
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}