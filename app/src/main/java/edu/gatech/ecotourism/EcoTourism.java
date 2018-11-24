package edu.gatech.ecotourism;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class EcoTourism extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);
    }
}
