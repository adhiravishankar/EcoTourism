package edu.gatech.ecotourism.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.ecotourism.Listing;
import edu.gatech.ecotourism.R;

public class ExperienceActivity extends AppCompatActivity {

    Listing listing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);


    }
}
