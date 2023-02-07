package com.example.newnest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ResetPassActivity extends AppCompatActivity {
    Button SendREQ;
    EditText Email;
    TextView GOBACK;
    Intent DoneWPage = new Intent(ResetPassActivity.this, MainActivity.class);

    FirebaseServices services;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        SendREQ=findViewById(R.id.SendResetREQBTN);
        Email=findViewById(R.id.ResetEmailET);
        GOBACK=findViewById(R.id.GoBack2TV);
        services=new FirebaseServices();
        GOBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(DoneWPage);
            }
        });
        SendREQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                services.getAuth().sendPasswordResetEmail(Email.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ResetPassActivity.this, "Reset Request sent", Toast.LENGTH_SHORT).show();

                            startActivity(DoneWPage);
                        }
                        else  Toast.makeText(ResetPassActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}