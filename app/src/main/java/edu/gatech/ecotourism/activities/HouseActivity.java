package edu.gatech.ecotourism.activities;

import android.os.Bundle;
import android.app.Activity;

import edu.gatech.ecotourism.Listing;
import edu.gatech.ecotourism.R;

public class HouseActivity extends Activity {

    Listing listing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);

        listing = getIntent().getParcelableExtra("listing");
    }
}
