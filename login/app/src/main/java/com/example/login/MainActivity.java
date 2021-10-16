package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password,repassword;
    Button btnsignin, btnsignup;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password =(EditText) findViewById(R.id.username);
        repassword=(EditText) findViewById(R.id.repassword);

        btnsignin =(Button)findViewById(R.id.btnsignin);
        btnsignup=(Button)findViewById(R.id.btnsignup);

        myDB = new DBHelper(this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if( user.equals("")|| pass.equals("")||repass.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Fill all the fields",Toast.LENGTH_LONG).show();
                }

                else
                {
                    if(pass.equals(repass))
                    {

                        Boolean usercheckResult= myDB.checkusername(user);
                        if(usercheckResult==false)
                        {
                            Boolean regResult = myDB.insertData(user,pass);
                            if(regResult==true)
                            {
                                Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                           else
                            {
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                            
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "User already Exists.\n Please Sign in", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Password not Maching", Toast.LENGTH_SHORT).show();
                    }

                }
            }        });
    }
}