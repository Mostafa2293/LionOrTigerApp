package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum player {
        ONE, TWO, NO
    }

    player currentPlayer = player.ONE;
    player[] playerChoices = new player[9];
    int[][] winnerRowsColumns = {{0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}};

    boolean gameOver = false;

    private Button btnReset;

    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for (int i = 0; i < playerChoices.length; i++) {
            playerChoices[i] = player.NO;
        }

        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
            }
        });


    }

    public void imageViewIsTapped(View imageView) {

        ImageView tappedImageView = (ImageView) imageView;

        int tappedImageTag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[tappedImageTag] == player.NO && gameOver == false) {

            tappedImageView.setTranslationX(-2000);

            playerChoices[tappedImageTag] = currentPlayer;


            if (currentPlayer == player.ONE) {

                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = player.TWO;

            } else if (currentPlayer == player.TWO) {

                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = player.ONE;

            }


            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(750);

            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] && playerChoices[winnerColumns[0]] != player.NO) {

//                    btnReset.setVisibility(View.VISIBLE);
                    gameOver = true;
                    String winnerOfGame = "";

                    if (currentPlayer == player.ONE) {

                        winnerOfGame = "Player Two";

                    } else if (currentPlayer == player.TWO) {
                        winnerOfGame = "Player One";

                    }

                    Toast.makeText(getApplicationContext(), winnerOfGame + " Is The Winner !", Toast.LENGTH_SHORT).show();


                }
            }

        }

    }

    private void resetTheGame() {

        for (int index = 0; index < gridLayout.getChildCount(); index++) {

            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }

        currentPlayer = player.ONE;

        for (int i = 0; i < playerChoices.length; i++) {
            playerChoices[i] = player.NO;
        }

        gameOver = false;

//        btnReset.setVisibility(View.INVISIBLE);


    }
}














