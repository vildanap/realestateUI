package com.draos.app.nekretnine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHelper db = new DatabaseHelper();
        ResultSet rs1=null;

        TextView tv = (TextView) findViewById(R.id.tv1);

        try {
            String q1 = "SELECT * FROM public.\"user\"";
            Log.d("Upit", q1);
            rs1 = db.execute(q1);
            String u =null;
            while (rs1.next()) {

                Log.d("Username: ", rs1.getString("username"));
                u = rs1.getString("username");
            }
            rs1.close();
            tv.setText(u);
            db.disconnect();
        }
        catch(Exception e){
            Log.e("Geska", e.getMessage());
        }

    }
}
