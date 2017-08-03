package com.comp3717.comp3717;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        TextView title = (TextView)findViewById(R.id.recipeTitle);
        TextView desc  = (TextView)findViewById(R.id.recipeDesc);
        Intent intent  = getIntent();
        Bundle bundle  = intent.getExtras();

        // Set the title and description based on what you chose on last activity
        title.setText(bundle.getString("recipeName"));
        desc.setText(bundle.getString("recipeDescription"));
    }
}
