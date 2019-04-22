package com.nechvolod.test_task;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nechvolod.test_task.model.Result;
import com.squareup.picasso.Picasso;

public class CharacterInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info);
        Bundle bundle = getIntent().getExtras();

        final Result result;
        if (bundle != null) {
            result = (Result) bundle.getSerializable(Result.class.getSimpleName());

            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(result.getName());

            ImageView imageView = findViewById(R.id.image_view);
            TextView textName = findViewById(R.id.name);
            TextView textSpecies = findViewById(R.id.species);
            TextView textStatus = findViewById(R.id.status);
            TextView textGender = findViewById(R.id.gender);
            TextView textOrigin = findViewById(R.id.origin);
            TextView textLocation = findViewById(R.id.location);

            Picasso.get().load(result.getImage()).into(imageView);
            textName.setText(result.getName());
            textSpecies.setText(result.getSpecies());
            textStatus.setText(result.getStatus());
            textGender.setText(result.getGender());
            textOrigin.setText(result.getOrigin().getName());
            textLocation.setText(result.getLocation().getName());
        }

    }
}
