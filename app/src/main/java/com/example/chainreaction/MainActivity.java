package com.example.chainreaction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    private int p1=-1,p2=1;
    private boolean end=false;
    private int jj=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout l = findViewById(R.id.con);
        for (int i = 0; i <= 5; i++)
            for (int j = 0; j <= 5; j++) {
                final Button b = new Button(this);
                b.setX((j * 160) + 50);
                b.setY((i * 160) + 200);
                b.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
                b.setId((i * 10) + j);
                b.setText("0");
                l.addView(b);
                b.setTextSize(30);
            }

        Button r = findViewById(R.id.ref);
        Button e = findViewById(R.id.exit);
        refresh();
        change1();
        {
            for (int i = 0; i <= 5; i++)
                for (int j = 0; j <= 5; j++) {
                    final Button b = findViewById((i * 10) + j);
                        b.setOnClickListener(new View.OnClickListener() {
                            @SuppressLint("DefaultLocale")
                            @Override
                            public void onClick(View v) {



                                jj++;
                                if(jj==2)
                                    end=true;
                                if((b.getCurrentTextColor()==RED && p2==1 )||(b.getCurrentTextColor()==GREEN && p1==1) || b.getCurrentTextColor()==BLACK)
                                {
                                    change2();
                                b.setText(String.format("%d", (Integer.parseInt(b.getText().toString())) + 1));
                                if (p1 == 1)
                                    b.setTextColor(GREEN);
                                else if (p2 == 1)
                                    b.setTextColor(RED);
                                check();
                                change1();
                            }
                            }
                        });
                    }

        }
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                change1();

            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });

    }
    @SuppressLint("SetTextI18n")
    public void change1()
    {
        for (int i = 0; i <= 5; i++)
            for (int j = 0; j <= 5; j++) {
                Button b = findViewById((i * 10) + j);
                String s1 = b.getText().toString();
                b.setBackgroundColor(WHITE);
                switch (s1) {
                    case "0":
                        b.setText("");
                        b.setTextColor(BLACK);
                        break;
                    case "1":
                        b.setText("" + "\u25CF");
                        break;
                    case "2":
                        b.setText("" + "\u25CF" + "\u25CF");
                        break;
                    case "3":
                        b.setText("" + "\u25CF" + "\u25CF" + "\u25CF");
                        break;
                }

            }
    }
    public void change2()
    {
        for (int i = 0; i <= 5; i++)
            for (int j = 0; j <= 5; j++) {
                Button b = findViewById((i * 10) + j);
                b.setBackgroundColor(WHITE);
                String s1 = b.getText().toString();
                switch (s1) {
                    case "" + "\u25CF":
                        b.setText("1");
                        break;
                    case "":
                        b.setText("0");
                        break;
                    case "" + "\u25CF" + "\u25CF":
                        b.setText("2");
                        break;
                    case "" + "\u25CF" + "\u25CF" + "\u25CF":
                        b.setText("3");
                        break;
                }
            }
    }



    public void end()
    {

        final Handler h=new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int i1=0,i2=0;
                    for (int i = 0; i <= 5; i++)
                        for (int j = 0; j <= 5; j++) {
                            Button b = findViewById((i * 10) + j);
                            //if(((ColorDrawable)b.getBackground()).getColor()==GREEN)
                             if(b.getCurrentTextColor()==GREEN)
                                i1=1;
                            //if(((ColorDrawable)b.getBackground()).getColor()==RED)
                              if(b.getCurrentTextColor()==RED)
                                i2=1;

                        }
                    if(i1==1 && i2==0)
                    {
                        Toast.makeText(MainActivity.this,"PLAYER GREEN WINS",Toast.LENGTH_LONG).show();
                        refresh();
                        change1();
                    }
                    if(i1==0 && i2==1)
                    {
                        Toast.makeText(MainActivity.this,"PLAYER RED WINS",Toast.LENGTH_LONG).show();
                        refresh();
                        change1();
                    }
                }
            },200);

    }
    public void alert()
    {
        AlertDialog.Builder alt=new AlertDialog.Builder(this);
        alt.setTitle("Attention!")
                .setCancelable(false)
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog a=alt.create();
        a.show();
    }
    public void refresh()
    {
        jj=0;
        end=false;
        for (int i = 0; i <= 5; i++)
            for (int j = 0; j <= 5; j++) {
                Button b = findViewById((i*10)+j);
                b.setText("0");
                b.setBackgroundColor(Color.WHITE);
            }
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alt=new AlertDialog.Builder(this);
        alt.setTitle("Attention!")
                .setCancelable(false)
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog a=alt.create();
        a.show();
    }

    public void check() {
        int fl = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j <= 5; j++) {
                int id = (i * 10) + j;
                Button a, b = findViewById(id);
                if((Integer.parseInt("" + b.getText()))==0) {
                        b.setBackgroundColor(Color.WHITE);
                }

                if (id == 0 && Integer.parseInt("" + b.getText()) >1) {

                    a = findViewById(id + 1);
                    a.setText(String.format("%d", Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%2));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}

                    check();
                }
                if (id == 05 && Integer.parseInt("" + b.getText()) >1) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%2));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 50 && Integer.parseInt("" + b.getText()) >1) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%2));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 55 && Integer.parseInt("" + b.getText()) >1) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%2));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 01 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 02 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 03 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 04 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 51 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 52 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 53 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 54 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 10 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 20 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 30 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 40 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 15 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 25 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 35 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 45 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 44 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 43 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 42 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 41 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 34 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 33 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 32 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 31 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 24 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 23 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 22 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    fl = 1;
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 21 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 14 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 13 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 12 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
                if (id == 11 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                    else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    fl = 1;
                            if(p1==1)
                    { b.setTextColor(GREEN);
                        a.setTextColor(GREEN);}
                            else if(p2==1)
                    { b.setTextColor(RED);
                        a.setTextColor(RED);}
                    check();
                }
            }

        }
        if(fl==1)
        {
            fl=0;
            check();
        }

         {
            p1 = p1 * -1;
            p2 = p2 * -1;
        }
        if(end) {
            end();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
    }
}
