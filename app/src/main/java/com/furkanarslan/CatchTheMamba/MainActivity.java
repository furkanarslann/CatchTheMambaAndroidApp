package com.furkanarslan.CatchTheMamba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtTimer, txtScore, txtThanks, txtThanks2, txtThanks3;
    ImageView img;
    Button btnQuit;
    int point;
    int screenX,screenY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay(); // ekran size ı tutucu
        Point size = new Point(); // koordinat tutucu
        display.getSize(size);
        screenX = size.x;
        screenY = size.y;

        txtTimer = findViewById(R.id.txtTimer);
        img = findViewById(R.id.imgKobe);
        txtScore = findViewById(R.id.txtScore);
        txtThanks = findViewById(R.id.txtThanks);
        txtThanks2 = findViewById(R.id.txtThanks2);
        txtThanks3 = findViewById(R.id.txtThanks3);
        btnQuit = findViewById(R.id.btnExit);
        startGame();
    }

     public void startGame() {
        point = 0;

         new CountDownTimer(15000, 1000) {
             @Override
             public void onTick(long millisUntilFinished) {
                    txtTimer.setText("Time: "+millisUntilFinished/1000);
                 float randomX = new Random().nextInt(screenX-img.getMeasuredWidth());
                 float randomY = new Random().nextInt( screenY- 2 * img.getMeasuredHeight());
                 img.setX(randomX);
                 img.setY(randomY);
             }

             @Override
             public void onFinish() {
                    img.setEnabled(false);
                    txtTimer.setText("Time's up!");
                    img.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Time's up",Toast.LENGTH_LONG ).show();

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("Your Score: "+point+"\nWould you like to restart ?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        img.setEnabled(true);
                        img.setVisibility(View.VISIBLE);
                        startGame();
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over!",Toast.LENGTH_LONG).show();
                        txtThanks.setVisibility(View.VISIBLE);
                        txtThanks2.setVisibility(View.VISIBLE);
                        txtThanks3.setVisibility(View.VISIBLE);
                        btnQuit.setVisibility(View.VISIBLE);
                    }
                });

                alert.show();
             }
         }.start();


     }


    public void score(View view) {
        point++;
        txtScore.setText("Score: "+point);

         }

    public void exitGame(View view){
        finish();
    }

}