package com.example.citizensreportapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminPage extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000/";
    private ListView logListView;
    private ArrayAdapter<User> logAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);
        Log.d("Fortnite", "This is working");
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface jsonPlaceHolderApi = retrofit.create(RetrofitInterface.class);
        displayUsers();
        findViewById(R.id.adminLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPage.this, MainActivity.class));
                Log.d("Markassbrownlee", "This is working");
            }
        });
    }

    private void displayUsers() {
        String user = "lol", pass = "andrew";
        Call<List<User>> call = retrofitInterface.retrieveUsers(user,pass);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.code() == 200) {
                    List<User> lol = response.body();
                    logListView = findViewById(R.id.transactions);
                    for(User x: lol) {
                        Log.d("AndrewMei", x.toString());
                    }
                    List<List<User>> targetList = Arrays.asList(lol);
                    for(List y: targetList) {
                        Log.d("alexis", String.valueOf(y));
                    }
                    if(logAdapter == null) {
                        logAdapter = new ArrayAdapter<>(AdminPage.this, R.layout.log, R.id.log, lol);
                        logListView.setAdapter(logAdapter);
                    }
                    else {
                        logAdapter.clear();
                        logAdapter.addAll(lol);
                        logAdapter.notifyDataSetChanged();
                    }
                    // split string at every comma and then we can do substring of title and text
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(AdminPage.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

