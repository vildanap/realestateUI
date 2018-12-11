package com.draos.app.nekretnine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.tv1);
        final Button button = (Button) findViewById(R.id.button);
        //za create
        final Spinner spinner = (Spinner) findViewById(R.id.cities);
        final EditText etAddress= (EditText) findViewById(R.id.etAddress);
        final EditText etSettlment= (EditText) findViewById(R.id.etSettlment);
        final Button createBtn = (Button) findViewById(R.id.button3);
        //upload
        final Button btnUpload = (Button) findViewById(R.id.button4);





        //TODO popuniti listu iz baze
        List<City> cities = new ArrayList<City>();
        City c = new City(1,"Sarajevo");
        cities.add(c);

        ArrayAdapter<City> cityArrayAdapter = new ArrayAdapter<City>(this,android.R.layout.simple_spinner_item,cities);
        cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(cityArrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Call<List<Location>> createCall = service.all();
                createCall.enqueue(new Callback<List<Location>>() {
                    @Override
                    public void onResponse(Call<List<Location>> _, Response<List<Location>> resp) {
                        textView.setText("All locations:\n");

                        for (Location b : resp.body()) {
                            textView.append(b.address + "\n");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Location>> _, Throwable t) {
                        t.printStackTrace();
                        textView.setText(t.getMessage());
                    }
                });*/

               LocationService service = RealEstateServiceGenerator.createService(LocationService.class);
               final Call<Location> call = service.getLocation(1);
                call.enqueue(new Callback<Location>() {
                    @Override
                    public void onResponse(Call<Location> call, Response<Location> response) {
                        Log.d("r", "onResponse: "+ response.body().toString());
                        if(response.isSuccessful()) {
                            textView.setText("Location by id:\n");
                            textView.append(response.body().city.name + "\n");


                        } else {
                            System.out.println(response.errorBody());
                        }
                    }
                    @Override
                    public void onFailure(Call<Location> call, Throwable t) {
                        textView.setText("Something went wrong: " + t.getMessage());
                    }
                });
            }
        });

        //create
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = etAddress.getText().toString().trim();
                String settelment = etSettlment.getText().toString().trim();
                City c = new City();
                c = (City) spinner.getSelectedItem();
                Log.d("spinner", "onClick: "+ c.toString());
                Location l = new Location (address,settelment,c);
                LocationService service = RealEstateServiceGenerator.createService(LocationService.class);

    /*            final Call<ResponseBody> call = service.postUser(l);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code()==201) {
                            Context context = getApplicationContext();
                            CharSequence text = "Created!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                        } else {
                            System.out.println(response.errorBody());
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        textView.setText("Something went wrong: " + t.getMessage());
                    }
                });*/
    // edit
                final Call<ResponseBody> call = service.updateLocation(1, l);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code()==200) {
                            Context context = getApplicationContext();
                            CharSequence text = "Location succesfully updated!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                        } else {
                            System.out.println(response.errorBody());
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        textView.setText("Something went wrong: " + t.getMessage());
                    }
                });

            }});

        //otvara novi activity za upload
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(intent);
            }});

    }


}
