package com.test;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register_Screen extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText pass_second;


    Button creatAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        username = (EditText) findViewById(R.id.userName);

        password = (EditText) findViewById(R.id.pass_first_time);

        creatAccount = (Button) findViewById(R.id.create_account);
        pass_second = (EditText) findViewById(R.id.pass_second_time);

        creatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass1 = password.getText().toString();
                String pass2 = pass_second.getText().toString();

                if (pass1.compareTo(pass2) == 0)
                {

                    btnConn();

                }

                else
                {
                    Toast.makeText(Register_Screen.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void btnConn() {

        String[] S = new String[]{username.getText().toString(), password.getText().toString()};
        Send obsedn = (Send) new Send().execute(S);


    }





    private class Send extends AsyncTask<String, Void, Void> {
        String msg;
        public boolean check = false;

        protected Void doInBackground(String... pParams) {
            String name, password;
            name = pParams[0];
            password = pParams[1];


           password =  BCrypt.hashpw(password, BCrypt.gensalt()); //password get hashed with salt

            Connection connection =null;

            try {


                Class.forName("com.mysql.jdbc.Driver");
                 connection = DriverManager.getConnection("url of your database", "DB_Username", "DB_Password");

                if (connection == null) {
                  // Connection goes wrong
                    return null;

                } else {

                   String sql =  "Select * from main_table Where Username='" +name+"'";
                    Statement stmt1 = connection.createStatement();
                    ResultSet rs = stmt1.executeQuery(sql);


                    if(rs.next())
                    {
                        check =true;// user already exists

                    }

                   else
                       {
                        String query = "INSERT INTO main_table (Username , password ) VALUES ('" + name + "' , '" + password + "')";
                        Statement stmt = connection.createStatement();
                        stmt.executeUpdate(query);
                       }

                }


                connection.close();


            } catch (SQLException e) {

                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                e.printStackTrace();
            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }


            return null;
        }



        @Override
        public void onPostExecute(Void param)
        {
            if(check == false)
            {

                Intent intent = new Intent(Register_Screen.this, Login_Screen.class);
                Toast.makeText(Register_Screen.this, "Account created", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }

            else
            Toast.makeText(Register_Screen.this, "User already exists", Toast.LENGTH_LONG).show();
        }

    }


    }
