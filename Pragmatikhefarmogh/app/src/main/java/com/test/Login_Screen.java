package com.test;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login_Screen extends AppCompatActivity {

    private EditText Password;
    private EditText UserName;
    private Button Login;
    private Button Register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserName = (EditText) findViewById(R.id.userName);
        Password = (EditText) findViewById(R.id.password);

        Login = (Button) findViewById(R.id.Login);
        Register = (Button) findViewById(R.id.createaccount);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (googleCheck()) {
                    checkfun(UserName.getText().toString(), Password.getText().toString());

                }
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Screen.this, Register_Screen.class);
                startActivity(intent);


            }
        });

    }


    public boolean googleCheck() {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Login_Screen.this);
        if (available == ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available))

        {
            // an error occured but we can resolve it
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Login_Screen.this, available, 9001);
            dialog.show();
            return false;
        }

       else
        {
            return false;
        }
    }

   private void checkfun(String username,String password)
   {

     btnConn(username ,password);


   }











    public void btnConn(String username,String password) {

        String[] S = new String[]{username,password};
        Send obsedn = (Send) new Send().execute(S);


    }


    private class Send extends AsyncTask<String, Void, Void> {
        String msg;
        public boolean check = false;

        protected Void doInBackground(String... pParams) {
            String username, password;
            username = pParams[0];
            password = pParams[1];

            Connection connection= null;

            try {


                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://192.168.1.78:3306/test", "Sboukis", "1234");


                if (connection == null) {
                    // Connection goes wrong
                    return null;


                } else {


                    String query = "Select * from main_table Where Username='" +username+"'";
                    Statement stmt = connection.createStatement();
                   ResultSet rs = stmt.executeQuery(query);


                   if(rs.next()) // user exists
                   {


                        String hash_password =rs.getString("password");
                       if( BCrypt.checkpw(password,hash_password))
                       {
                           check = true; // username and password are correct

                        }
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
            }

            catch (ClassNotFoundException e) {


                e.printStackTrace();
            }
            return null;
        }


    @Override
    protected void onPostExecute(Void aVoid) {


        if( check == true)
        {
            Intent intent = new Intent(Login_Screen.this, Maps_Screen.class);
            startActivity(intent);
        }

        else
        {
            Toast.makeText(Login_Screen.this, "Incorrect Username or Password" , Toast.LENGTH_LONG).show();
        }
    }


}
    }






