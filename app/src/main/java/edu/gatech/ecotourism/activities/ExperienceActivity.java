package edu.gatech.ecotourism.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import edu.gatech.ecotourism.Listing;
import edu.gatech.ecotourism.R;

public class ExperienceActivity extends AppCompatActivity {

    Listing listing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        listing = getIntent().getParcelableExtra("listing");
        TextView type = findViewById(R.id.type);
        type.setText(listing.getType());

        TextView title = findViewById(R.id.title);
        title.setText(listing.getTitle());

        ImageView backdrop = findViewById(R.id.backdrop);
        backdrop.setImageResource(listing.getImage());
    }
}
