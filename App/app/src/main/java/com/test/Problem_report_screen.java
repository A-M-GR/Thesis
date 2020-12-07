package com.test;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

public class Problem_report_screen extends AppCompatActivity {

    double Latitude,Longitude;
    private Button Send;
    private  String address = null;
    private EditText ProblemUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_report_screen);

        Send = (Button) findViewById(R.id.send);
        ProblemUser = (EditText) findViewById(R.id.userproblem);


        Intent receiveIntent = this.getIntent(); // from Maps_Screen

       Latitude = receiveIntent.getDoubleExtra("Latitude",0.0);
       Longitude = receiveIntent.getDoubleExtra("Longitude",0.0);

        // i will save the address in my database
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(Latitude,Longitude,1);
             address = addresses.get(0).getAddressLine(0);



        } catch (IOException e) {
            e.printStackTrace();




        }







        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String text = ProblemUser.getText().toString();

                if(text.trim().length()>0) {


                    String[] S = new String[]{text, address};
                    Send obsedn = (Send) new Send().execute(S);
                }

                else
                {
                    Toast.makeText(Problem_report_screen.this, "Input box is empty" , Toast.LENGTH_LONG).show();
                }

            }

        });

    }





    private class Send extends AsyncTask<String, Void, Void> {


        protected Void doInBackground(String... pParams) {
            String User_problem, Location;
            User_problem = pParams[0];
            Location = pParams[1];

            Connection connection = null;

            try {


                Class.forName("com.mysql.jdbc.Driver");
                 connection = DriverManager.getConnection("url of your database", "DB_Username", "DB_Password");

                if (connection == null) {

                  // Connection goes wrong
                    return null;


                }

                else
                {
                    String query = "INSERT INTO User_Problem_Text (problem_text , Location ) VALUES ('" + User_problem + "' , '" + Location + "')";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(query);
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
        protected void onPostExecute(Void aVoid) {

               Intent intent = new Intent(Problem_report_screen.this,Maps_Screen.class);
                intent.putExtra("All ok",true);
               startActivity(intent);



        }


    }
}