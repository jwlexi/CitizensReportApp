package com.example.citizensreportapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.appcompat.app.AlertDialog;

public class Home extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000/";
    private ListView logListView;
    private ArrayAdapter<Post> logAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        findViewById(R.id.create_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePost();
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface jsonPlaceHolderApi = retrofit.create(RetrofitInterface.class);
        displayPosts();
    }

    private void handlePost() {
        View view = getLayoutInflater().inflate(R.layout.post, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog show = builder.show();
        Button createPostButton = view.findViewById(R.id.createP);
        EditText userTitle = view.findViewById(R.id.cin_title);
        EditText userText = view.findViewById(R.id.cin_text);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("title", userTitle.getText().toString());
                map.put("text", userText.getText().toString());

                Call<Void> call = retrofitInterface.executeCreate(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200) {
                            Toast.makeText(Home.this, "Successful creation", Toast.LENGTH_LONG).show();
                            show.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Home.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void displayPosts() {
        String title = "lol", text = "andrew";
        Call<List<Post>> call = retrofitInterface.retrievePosts(title,text);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.code() == 200) {
                    List<Post> lol = response.body();
                    logListView = findViewById(R.id.transactions);
                    for(Post x: lol) {
                        Log.d("AndrewMei", x.toString());
                    }
                    List<List<Post>> targetList = Arrays.asList(lol);
                    for(List y: targetList) {
                        Log.d("alexis", String.valueOf(y));
                    }
                    if(logAdapter == null) {
                        logAdapter = new ArrayAdapter<>(Home.this, R.layout.log, R.id.log, lol);
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
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(Home.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
