package com.example.crud176;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import static android.content.res.Configuration.SCREENLAYOUT_SIZE_XLARGE;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    static MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration config = getResources().getConfiguration();

        if ((config.screenLayout &  Configuration.SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_XLARGE)  //tablet
            Log.d("Tela grande", "onCreate: tela grande.");

        setContentView(R.layout.activity_main);

        myAppDatabase = Room.databaseBuilder(getApplicationContext(),
                MyAppDatabase.class, "userdb").
                allowMainThreadQueries().
                build();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().
                add(R.id.frameLayout, new HomeFragment()).
                commit();

        String caminhoBanco = getDatabasePath("userdb").getAbsolutePath();

        Log.d("caminho", "onCreate: " + caminhoBanco);


    }
}

