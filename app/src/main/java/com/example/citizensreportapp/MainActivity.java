package com.example.citizensreportapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:3000";
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        //retrofitInterface = retrofit.create(RetrofitInterface.class);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegister();
            }
        });

        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("hhtps://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface jsonPlaceHolderApi = retrofit.create(RetrofitInterface.class);

        /*Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();

                for (Post post : posts) {
                    String s = "";
                    s += "User Id: " + post.getUserId() + "\n";
                    s += "Id: " + post.getId() + "\n";
                    s += "Title: " + post.getTitle() + "\n";
                    s += "Text: " + post.getText() + "\n";
                    textViewResult.append(s);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });*/
    }

    private void handleLogin() {
        View view = getLayoutInflater().inflate(R.layout.login, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();
        Button loginBtn = view.findViewById(R.id.login);
        EditText userEdit = view.findViewById(R.id.cin_user);
        EditText passwordEdit = view.findViewById(R.id.cin_pass);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("username", userEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());
                Call<Login> call = retrofitInterface.executeLogin(map);
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if(response.code() == 200) {

//                            Login res = response.body();
//
//                            AlertDialog.Builder b1 = new AlertDialog.Builder(MainActivity.this);
//                            b1.setTitle(res.getUsername());
//                            b1.setMessage("Welcome, thank you for logging in");
//                            b1.show();
                            startActivity(new Intent(MainActivity.this, Home.class));
                        }
                        else if(response.code() == 404) {
                            Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    private void handleRegister() {
        View view = getLayoutInflater().inflate(R.layout.register, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button registerBtn = view.findViewById(R.id.register);
        EditText userEdit = view.findViewById(R.id.cin_user);
        EditText passwordEdit = view.findViewById(R.id.cin_pass);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("username", userEdit.getText().toString());
                map.put("password", passwordEdit.getText().toString());

                Call<Void> call = retrofitInterface.executeRegister(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200) {
                            Toast.makeText(MainActivity.this, "Successful sign up", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 400) {
                            Toast.makeText(MainActivity.this, "User already registered", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
//                Toast.makeText(MainActivity.this, "This button works", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.view_my_posts) {
            return true; //add activity
        }
        else if(id == R.id.create_post){
            return true; //add activity
        }
        else if(id == R.id.about){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*private void createPost() {
        Post post = new Post(userId: 5, title: "Title", text: "Text");
    }*/
}