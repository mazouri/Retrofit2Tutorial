package com.mazouri.retrofit2tutorial;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.mazouri.retrofit2tutorial.adapter.UserAdapter;
import com.mazouri.retrofit2tutorial.models.GitResult;
import com.mazouri.retrofit2tutorial.models.Item;
import com.mazouri.retrofit2tutorial.rest.GitApiInterface;
import com.mazouri.retrofit2tutorial.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserAdapter adapter;
    List<Item> Users ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = (ListView) findViewById(R.id.listView);
        Users = new ArrayList<Item>();


        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        GitApiInterface gitApiInterface = RestClient.getClient();
        Call<GitResult> call = gitApiInterface.getUsersNamedTom("tom");

        call.enqueue(new Callback<GitResult>() {
            @Override
            public void onResponse(Call<GitResult> call, Response<GitResult> response) {
                dialog.dismiss();
                Log.d("MainActivity", "onResponse Status Code = " + response.code());
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    GitResult result = response.body();
                    Log.d("MainActivity", "response = " + new Gson().toJson(result));
                    Users = result.getItems();
                    Log.d("MainActivity", "Items = " + Users.size());
                    adapter = new UserAdapter(MainActivity.this, Users);
                    listView.setAdapter(adapter);
                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.d("MainActivity", "onResponse not success Status Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<GitResult> call, Throwable t) {
                Log.d("MainActivity", "onFailure throwable = " + t.getMessage());
            }
        });
    }
}
