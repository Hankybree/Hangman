package com.example.labb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity
{
    private Random rnd;
    private int counter = 0;

    private ImageView[] tryImages = new ImageView[9];
    private String[] hiddenWords = new String[10];

    private String hiddenWord;
    private String guessedLetter;
    private ArrayList<String> lettersGuessed;

    private EditText guessInput;
    private TextView displayedWord;

    private Button guessButton;
    private Button playAgainButton;
    private Button mainMenuButton;

    ImageView youLose;
    ImageView youWin;

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
        setContentView(R.layout.activity_game);

        hiddenWord = initialize();

        printDisplayedWord();
    }

    public void attempt(View view)
    {
        boolean correctGuess = false;
        String wordToDisplay = "";

        guessedLetter = guessInput.getText().toString().toUpperCase();

        if (!guessedLetter.equals(""))
        {
            if (!isLetterGuessed(guessedLetter))
            {
                for(int i = 0; i < hiddenWord.length(); i++)
                {
                    if(hiddenWord.charAt(i) == guessedLetter.charAt(0))
                    {
                        if (displayedWord.getText().toString().charAt(i) == '-')
                        {
                            wordToDisplay += guessedLetter.charAt(0);

                            correctGuess = true;
                        }
                    }
                    else
                    {
                        if(displayedWord.getText().toString().charAt(i) != '-')
                        {
                            wordToDisplay += displayedWord.getText().toString().charAt(i);
                        }
                        else
                        {
                            wordToDisplay += "-";
                        }
                    }

                }

                lettersGuessed.add(guessedLetter);

                displayedWord.setText(wordToDisplay);

                if(displayedWord.getText().toString().equals(hiddenWord))
                {
                    for (int i = 0; i < tryImages.length; i++)
                    {
                        tryImages[i].setVisibility(View.INVISIBLE);
                    }

                    ImageView winScreen = findViewById(R.id.hangmangwin);

                    winScreen.setVisibility(View.VISIBLE);

                    guessInput.setVisibility(View.INVISIBLE);
                    guessButton.setVisibility(View.INVISIBLE);

                    playAgainButton.setVisibility(View.VISIBLE);
                    mainMenuButton.setVisibility(View.VISIBLE);

                    youWin.setVisibility(View.VISIBLE);
                }
                else if (!correctGuess)
                {
                    for(int i = 0; i < tryImages.length; i++)
                    {
                        if (tryImages[i].getVisibility() == View.VISIBLE)
                        {
                            tryImages[i].setVisibility(View.INVISIBLE);
                        }
                    }

                    counter++;

                    if (counter <= 8)
                    {
                        tryImages[counter].setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        VideoView videoView = findViewById(R.id.hangmangtry10);

                        runVideo(videoView);

                        guessInput.setVisibility(View.INVISIBLE);
                        guessButton.setVisibility(View.INVISIBLE);

                        playAgainButton.setVisibility(View.VISIBLE);
                        mainMenuButton.setVisibility(View.VISIBLE);

                        youLose.setVisibility(View.VISIBLE);

                        displayedWord.setText(hiddenWord);
                    }

                    printGuessedLetters();
                }
            }
            else
            {
                Context context = getApplicationContext();
                CharSequence text = "You can't guess the same letter twice";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "Enter a letter";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void playAgain(View view)
    {
        finish();

        Intent gameIntent = new Intent(this, GameActivity.class);

        gameIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(gameIntent);
    }

    public void mainMenu(View view)
    {
        Intent menuIntent = new Intent(this, MainActivity.class);

        startActivity(menuIntent);
    }

    private String initialize()
    {
        setupImageArr();
        setupHiddenWords();
        lettersGuessed = new ArrayList<>();

        guessInput = findViewById(R.id.guess_input);

        guessButton = findViewById(R.id.guess_button);
        playAgainButton = findViewById(R.id.play_again_button);
        mainMenuButton = findViewById(R.id.main_menu_button);

        youLose = findViewById(R.id.you_lose);
        youWin = findViewById(R.id.you_win);

        rnd = new Random();
        int randomWord = rnd.nextInt(10);

        hiddenWord = hiddenWords[randomWord];

        return hiddenWord;
    }

    private void runVideo(VideoView videoView)
    {
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.hangmantry10;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.setVisibility(View.VISIBLE);

        videoView.start();
    }

    private void setupImageArr()
    {
        tryImages[0] = findViewById(R.id.hangmangtry1);
        tryImages[1] = findViewById(R.id.hangmangtry2);
        tryImages[2] = findViewById(R.id.hangmangtry3);
        tryImages[3] = findViewById(R.id.hangmangtry4);
        tryImages[4] = findViewById(R.id.hangmangtry5);
        tryImages[5] = findViewById(R.id.hangmangtry6);
        tryImages[6] = findViewById(R.id.hangmangtry7);
        tryImages[7] = findViewById(R.id.hangmangtry8);
        tryImages[8] = findViewById(R.id.hangmangtry9);
    }

    private void setupHiddenWords()
    {
        hiddenWords[0] = getString(R.string.hidden_word_1);
        hiddenWords[1] = getString(R.string.hidden_word_2);
        hiddenWords[2] = getString(R.string.hidden_word_3);
        hiddenWords[3] = getString(R.string.hidden_word_4);
        hiddenWords[4] = getString(R.string.hidden_word_5);
        hiddenWords[5] = getString(R.string.hidden_word_6);
        hiddenWords[6] = getString(R.string.hidden_word_7);
        hiddenWords[7] = getString(R.string.hidden_word_8);
        hiddenWords[8] = getString(R.string.hidden_word_9);
        hiddenWords[9] = getString(R.string.hidden_word_10);
    }

    private void printDisplayedWord()
    {
        displayedWord = findViewById(R.id.hidden_word);

        String wordToDisplay = "";

        for(int i = 0; i < hiddenWord.length(); i++)
        {
            wordToDisplay += "-";
        }

        displayedWord.setText(wordToDisplay);
    }

    private boolean isLetterGuessed(String guessedLetter)
    {
        boolean letterGuessed = false;

        for(int i = 0; i < lettersGuessed.size(); i++)
        {
            if(lettersGuessed.get(i).equals(guessedLetter))
            {
                letterGuessed = true;
            }
        }

        return letterGuessed;
    }

    private void printGuessedLetters()
    {
        String strToPrint = "";

        for(int i = 0; i < lettersGuessed.size(); i++)
        {
            if (!hiddenWord.contains(lettersGuessed.get(i)))
            {
                strToPrint += lettersGuessed.get(i) + " ";
            }
        }

        TextView lettersGuessed = findViewById(R.id.guessed_letters);

        lettersGuessed.setText(strToPrint);
    }
}
