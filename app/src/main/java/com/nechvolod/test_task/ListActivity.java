package com.nechvolod.test_task;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.nechvolod.test_task.model.ResponseObject;
import com.nechvolod.test_task.model.Result;
import com.nechvolod.test_task.web.Controller;
import com.nechvolod.test_task.web.RickAndMortyApi;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    GridView gridView;
    List<Result> resultList;
    CustomAdapter customAdapter;
    String nextUrl;
    String prevUrl;

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ActionBar actionBar = getSupportActionBar();
        gridView = findViewById(R.id.grid_view);
        load("1");
    }

    public void load(String url) {
        final String pageNumber = url.substring(url.length() - 1);
        RickAndMortyApi rickAndMortyApi = Controller.getApi();
        rickAndMortyApi.getData(pageNumber).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                Log.i("HTTP status", response.message());
                showTable(response.body().getResults());
                setResultList(response.body().getResults());
                nextUrl = response.body().getInfo().getNext();
                prevUrl = response.body().getInfo().getPrev();
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.e("Retrofit Error", t.getMessage());
            }
        });
    }

    void showTable(List<Result> r) {
        customAdapter = new CustomAdapter(this, r);
        gridView.setAdapter(customAdapter);
    }

    public void loadPrev(View v) {
        if (prevUrl != null && !prevUrl.equals("")) {
            load(prevUrl.substring(prevUrl.length() - 1));
        } else {
            Toast.makeText(this, "First page", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadNext(View v) {
        if (nextUrl != null && !nextUrl.equals("")) {
            load(nextUrl.substring(nextUrl.length() - 1));
        } else {
            Toast.makeText(this, "Last page", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                if (resultList != null) {
                    Collections.sort(resultList);
                    customAdapter.notifyDataSetChanged();
                    break;
                } else {
                    Toast.makeText(this, "Ups, something went wrong!", Toast.LENGTH_SHORT).show();
                    Log.e("Sort error", "Empty list");
                }
        }
        return true;
    }

}
