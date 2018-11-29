package com.interpixel.twentyfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ImageView[] handCards = new ImageView[8];
    private ImageView[] playerCards = new ImageView[4];
    private ImageView selectedCard;
    private int[] handCardsValue = new int [10];
    private int[] playerCardsValue = new int [4];
    private int selectedCardValue;

    private LinearLayout operatorButtons;
    private Button buttonPlus, buttonMinus, buttonKali, buttonBagi, buttonMod;
    private Button reset, pass, solve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        handCards[0] = findViewById(R.id.kartu1);
        handCards[1] = findViewById(R.id.kartu2);
        handCards[2] = findViewById(R.id.kartu3);
        handCards[3] = findViewById(R.id.kartu4);
        handCards[4] = findViewById(R.id.kartu5);
        handCards[5] = findViewById(R.id.kartu6);
        handCards[6] = findViewById(R.id.kartu7);
        handCards[7] = findViewById(R.id.kartu8);

        playerCards[0] = findViewById(R.id.kartuPemain1);
        playerCards[1] = findViewById(R.id.kartuPemain2);
        playerCards[2] = findViewById(R.id.kartuPemain3);
        playerCards[3] = findViewById(R.id.kartuPemain4);

        selectedCard = findViewById(R.id.selectedCard);

        selectedCard.setVisibility(View.INVISIBLE);
        playerCards[0].setVisibility(View.INVISIBLE);
        playerCards[1].setVisibility(View.INVISIBLE);
        playerCards[2].setVisibility(View.INVISIBLE);
        playerCards[3].setVisibility(View.INVISIBLE);

        operatorButtons = findViewById(R.id.operatorButtons);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonKali = findViewById(R.id.buttonKali);
        buttonBagi = findViewById(R.id.buttonBagi);
        buttonMod = findViewById(R.id.buttonMod);
        reset = findViewById(R.id.buttonReset);
        pass = findViewById(R.id.buttonPass);
        solve = findViewById(R.id.buttonSolve);

        operatorButtons.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.INVISIBLE);
        pass.setVisibility(View.INVISIBLE);
        solve.setVisibility(View.INVISIBLE);

        Random random = new Random();

        for(int i = 0; i < 8; i++){
            handCardsValue[i] = random.nextInt(9) + 1;
            pasangKartu(handCards[i], handCardsValue[i]);
            handCards[i].setOnClickListener(handCardListener);
        }

        for(int i = 0; i < 4; i++){
            playerCardsValue[i] = random.nextInt(9) + 1;
            pasangKartu(playerCards[i], playerCardsValue[i]);
        }

    }

    private View.OnClickListener handCardListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            for(int i = 0; i < 8; i++){
                handCards[i].setOnClickListener(null);
            }

            switch(v.getId()){
                case R.id.kartu1:
                    selectedCardValue = handCardsValue[0];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[0]);
                    break;
                case R.id.kartu2:
                    selectedCardValue = handCardsValue[1];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[1]);
                    break;
                case R.id.kartu3:
                    selectedCardValue = handCardsValue[2];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[2]);
                    break;
                case R.id.kartu4:
                    selectedCardValue = handCardsValue[3];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[3]);
                    break;
                case R.id.kartu5:
                    selectedCardValue = handCardsValue[4];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[4]);
                    break;
                case R.id.kartu6:
                    selectedCardValue = handCardsValue[5];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[5]);
                    break;
                case R.id.kartu7:
                    selectedCardValue = handCardsValue[6];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[6]);
                    break;
                case R.id.kartu8:
                    selectedCardValue = handCardsValue[7];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[7]);
                    break;
            }

            //preview selected cards
            selectedCard.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectedCard.setVisibility(View.INVISIBLE);

                    //hilangkan handcards dan tampilkan options beserta kartu table
                    for(int i = 0; i < 8; i++){
                        handCards[i].setVisibility(View.INVISIBLE);
                    }

                    for(int i = 0; i < 4; i++){
                        playerCards[i].setVisibility(View.VISIBLE);
                    }

                    //kita selalu player 1
                    playerCardsValue[0] = selectedCardValue;
                    pasangKartu(playerCards[0], selectedCardValue);

                    operatorButtons.setVisibility(View.VISIBLE);
                    reset.setVisibility(View.VISIBLE);
                    pass.setVisibility(View.VISIBLE);
                    solve.setVisibility(View.VISIBLE);

                }
            }, 1000);

        }
    };

    private void pasangKartu(ImageView kartu, int angka){
        switch (angka){
            case 1:
                kartu.setImageResource(R.drawable.kartu1);
                break;
            case 2:
                kartu.setImageResource(R.drawable.kartu2);
                break;
            case 3:
                kartu.setImageResource(R.drawable.kartu3);
                break;
            case 4:
                kartu.setImageResource(R.drawable.kartu4);
                break;
            case 5:
                kartu.setImageResource(R.drawable.kartu5);
                break;
            case 6:
                kartu.setImageResource(R.drawable.kartu6);
                break;
            case 7:
                kartu.setImageResource(R.drawable.kartu7);
                break;
            case 8:
                kartu.setImageResource(R.drawable.kartu8);
                break;
            case 9:
                kartu.setImageResource(R.drawable.kartu9);
                break;
            case 10:
                kartu.setImageResource(R.drawable.kartu10);
                break;
        }
    }
}
