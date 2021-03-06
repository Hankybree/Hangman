package com.fhs.labb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity
{
    private AdView mAdView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_bar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.main_menu_bar:
                Intent menuIntent = new Intent(this, MainActivity.class);

                menuIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(menuIntent);
                break;
            case R.id.play_game_bar:
                Intent gameIntent = new Intent(this, GameActivity.class);

                gameIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(gameIntent);
                break;
            case R.id.about_bar:
                Intent aboutIntent = new Intent(this, AboutActivity.class);

                aboutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(aboutIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-6927854487345181~4064750163");

        mAdView = findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void playGameButton(View view)
    {
        Intent gameIntent = new Intent(this, GameActivity.class);

        gameIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(gameIntent);
    }

    public void aboutButton(View view)
    {
        Intent aboutIntent = new Intent(this, AboutActivity.class);

        aboutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(aboutIntent);
    }
}
