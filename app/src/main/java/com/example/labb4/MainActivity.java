package com.example.labb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity
{
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
