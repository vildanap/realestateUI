package com.draos.app.nekretnine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LocationService service = retrofit.create(LocationService.class);

        final TextView textView = (TextView) findViewById(R.id.tv1);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Location>> createCall = service.all();
                createCall.enqueue(new Callback<List<Location>>() {
                    @Override
                    public void onResponse(Call<List<Location>> _, Response<List<Location>> resp) {
                        textView.setText("ALL BOOKS by Address:\n");
                        for (Location b : resp.body()) {
                            textView.append(b.address + "\n");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Location>> _, Throwable t) {
                        t.printStackTrace();
                        textView.setText(t.getMessage());
                    }
                });
            }
        });
    }


}
