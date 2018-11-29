package com.interpixel.twentyfour;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ImageView[] handCards = new ImageView[8];
    private ImageView[] playerCards = new ImageView[4];
    private ImageView selectedCard;
    private static int[] handCardsValue = new int [10];
    private int[] playerCardsValue = new int [4];
    private int selectedCardValue;

    private LinearLayout operatorButtons;
    private Button buttonPlus, buttonMinus, buttonKali, buttonBagi, buttonMod;
    private Button reset, pass, solve;
    private TextView operator1, operator2, operator3;
    private int operatorSign1, operatorSign2, operatorSign3;

    private int operatorKe;

    private boolean masihKuranginPoin;
    private int poin;

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

        operator1 = findViewById(R.id.operator1);
        operator2 = findViewById(R.id.operator2);
        operator3 = findViewById(R.id.operator3);

        operatorKe = 1;

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (operatorKe){
                    case 1:
                        operator1.setText("+");
                        operatorSign1 = 0;
                        break;
                    case 2:
                        operator2.setText("+");
                        operatorSign2 = 0;
                        break;
                    case 3:
                        operator3.setText("+");
                        operatorSign3 = 0;
                        break;
                }
                operatorKe++;
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (operatorKe){
                    case 1:
                        operator1.setText("-");
                        operatorSign1 = 1;
                        break;
                    case 2:
                        operator2.setText("-");
                        operatorSign2 = 1;
                        break;
                    case 3:
                        operator3.setText("-");
                        operatorSign3 = 1;
                        break;
                }
                operatorKe++;
            }
        });

        buttonKali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (operatorKe){
                    case 1:
                        operator1.setText("x");
                        operatorSign1 = 2;
                        break;
                    case 2:
                        operator2.setText("x");
                        operatorSign2 = 2;
                        break;
                    case 3:
                        operator3.setText("x");
                        operatorSign3 = 2;
                        break;
                }
                operatorKe++;
            }
        });

        buttonBagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (operatorKe){
                    case 1:
                        operator1.setText(":");
                        operatorSign1 = 3;
                        break;
                    case 2:
                        operator2.setText(":");
                        operatorSign2 = 3;
                        break;
                    case 3:
                        operator3.setText(":");
                        operatorSign3 = 3;
                        break;
                }
                operatorKe++;
            }
        });

        buttonMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (operatorKe){
                    case 1:
                        operator1.setText("%");
                        operatorSign1 = 4;
                        break;
                    case 2:
                        operator2.setText("%");
                        operatorSign2 = 4;
                        break;
                    case 3:
                        operator3.setText("%");
                        operatorSign3 = 4;
                        break;
                }
                operatorKe++;
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator1.setText("");
                operator2.setText("");
                operator3.setText("");
                operatorKe = 1;
            }
        });

        final Intent intent = new Intent(this, ScoreActivity.class);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masihKuranginPoin = false;
                if(checkAns(playerCardsValue[0], playerCardsValue[1],
                        playerCardsValue[2], playerCardsValue[3])){
                    //kalau ternyata bisa di solve dapet 0
                    intent.putExtra("skor", 0);
                }else{
                    intent.putExtra("skor", poin);
                }

                startActivity(intent);
                finish();
            }
        });

        solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operatorKe >= 3){
                    masihKuranginPoin = false;
                    int hasil = Komputasi(Komputasi(
                            Komputasi(playerCardsValue[0], operatorSign1, playerCardsValue[1]),
                            operatorSign2, playerCardsValue[2]),
                            operatorSign3, playerCardsValue[3]);

                    if(hasil == 24){
                        intent.putExtra("skor", poin);
                    }else{
                        intent.putExtra("skor", 0);
                    }

                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(GameActivity.this, "Fill in all the operators first",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        Random random = new Random();

        for(int i = 0; i < 8; i++){
            if(RoundActivity.ronde == 1){
                //game baru jadi di inisialisasi
                handCardsValue[i] = random.nextInt(9) + 1;
            }
            pasangKartu(handCards[i], handCardsValue[i]);
            handCards[i].setOnClickListener(handCardListener);
        }

        for(int i = 0; i < 4; i++){
            playerCardsValue[i] = random.nextInt(9) + 1;
            pasangKartu(playerCards[i], playerCardsValue[i]);
        }

        masihKuranginPoin = false;
        poin = 100;

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
                    handCardsValue[0] = 100;
                    break;
                case R.id.kartu2:
                    selectedCardValue = handCardsValue[1];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[1]);
                    handCardsValue[1] = 100;
                    break;
                case R.id.kartu3:
                    selectedCardValue = handCardsValue[2];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[2]);
                    handCardsValue[2] = 100;
                    break;
                case R.id.kartu4:
                    selectedCardValue = handCardsValue[3];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[3]);
                    handCardsValue[3] = 100;
                    break;
                case R.id.kartu5:
                    selectedCardValue = handCardsValue[4];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[4]);
                    handCardsValue[4] = 100;
                    break;
                case R.id.kartu6:
                    selectedCardValue = handCardsValue[5];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[5]);
                    handCardsValue[5] = 100;
                    break;
                case R.id.kartu7:
                    selectedCardValue = handCardsValue[6];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[6]);
                    handCardsValue[6] = 100;
                    break;
                case R.id.kartu8:
                    selectedCardValue = handCardsValue[7];
                    selectedCard.setVisibility(View.VISIBLE);
                    pasangKartu(selectedCard, handCardsValue[7]);
                    handCardsValue[7] = 100;
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

                    masihKuranginPoin = true;
                    handler.postDelayed(kurangPoin, 2000);

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
            case 100:   //kalau 100 berarti udh pernah dipilih di ronde sebelumnya
                kartu.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private int Komputasi (int Total, int Operator, int X) {
        switch (Operator) {
            case 0:
                Total += X;
                break;
            case 1:
                Total -= X;
                break;
            case 2:
                Total *= X;
                break;
            case 3:
                Total /= X;
                break;
            case 4:
                Total = Total % X;
                break;
            default:
                break;
        }
        return Total;
    }

    private boolean checkAns (int a, int b, int c, int d){
        int target = 24;
        for(int i =0; i < 5; i++){
            int temp1 = Komputasi(a, i, b);

            for(int j =0; j < 5; j++){
                int temp2 = Komputasi(temp1, j, c);

                for(int k =0; k < 5; k++){
                    int temp3 = temp2;
                    temp3 = Komputasi(temp2, k, d);
                    if (temp3 == target){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {

    }

    Handler handler = new Handler();

    Runnable kurangPoin = new Runnable() {
        @Override
        public void run() {

            if(poin <= 50){
                masihKuranginPoin = false;
            }

            if(masihKuranginPoin){
                poin--;
                handler.postDelayed(this, 2000);
            }
        }
    };

}
