package com.example.chainreaction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.icu.lang.UCharacter.JoiningGroup.FE;

public class MainActivity extends AppCompatActivity {

    int p1=1,p2=-1;
    Button r,e;
    boolean end=false;
    int jj=0;
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
            }

        r=findViewById(R.id.ref);
        e=findViewById(R.id.exit);
        refresh();
        //while(true)
        {
            for (int i = 0; i <= 5; i++)
                for (int j = 0; j <= 5; j++) {
                    final Button b = findViewById((i * 10) + j);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jj++;
                                if(jj==2)
                                    end=true;
                                if((((ColorDrawable)b.getBackground()).getColor()==RED && p2==1 )||(((ColorDrawable)b.getBackground()).getColor()==GREEN && p1==1) || ((ColorDrawable)b.getBackground()).getColor()==WHITE)
                                {
                                b.setText("" + ((Integer.parseInt("" + b.getText())) + 1));
                                if (p1 == 1)
                                    b.setBackgroundColor(GREEN);
                                else if (p2 == 1)
                                    b.setBackgroundColor(RED);
                                check();
                            }
                            }
                        });
                    }

        }
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();

            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });

    }
    public void end()
    {
        int i1=0,i2=0;
        for (int i = 0; i <= 5; i++)
            for (int j = 0; j <= 5; j++) {
                Button b = findViewById((i * 10) + j);
                if(((ColorDrawable)b.getBackground()).getColor()==GREEN)
                    i1=1;
                if(((ColorDrawable)b.getBackground()).getColor()==RED)
                    i2=1;

            }
        if(i1==1 && i2==0)
        {
            Toast.makeText(this,"PLAYER GREEN WINS",Toast.LENGTH_LONG).show();
            refresh();
        }
        if(i1==0 && i2==1)
        {
            Toast.makeText(this,"PLAYER RED WINS",Toast.LENGTH_LONG).show();
            refresh();
        }
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
        p1=p1*-1;
        p2=p2*-1;
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
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%2));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}

                    check();
                }
                if (id == 05 && Integer.parseInt("" + b.getText()) >1) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%2));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 50 && Integer.parseInt("" + b.getText()) >1) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%2));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 55 && Integer.parseInt("" + b.getText()) >1) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%2));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 01 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 02 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 03 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 04 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 51 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 52 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 53 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 54 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 10 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 20 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 30 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 40 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 15 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 25 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 35 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 45 && Integer.parseInt("" + b.getText()) >2) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%3));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 44 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 43 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 42 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 41 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 34 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 33 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 32 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 31 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 24 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 23 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 22 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    fl = 1;
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 21 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 14 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 13 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 12 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
                if (id == 11 && Integer.parseInt("" + b.getText()) >3) {
                    a = findViewById(id - 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 1);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id - 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    if(p1==1)
                    { b.setBackgroundColor(GREEN);
                        a.setBackgroundColor(GREEN);}
                    else if(p2==1)
                    { b.setBackgroundColor(RED);
                        a.setBackgroundColor(RED);}
                    a = findViewById(id + 10);
                    a.setText("" + (Integer.parseInt("" + a.getText()) + 1));
                    b.setText("" + ((Integer.parseInt("" + b.getText()))%4));
                    fl = 1;
                            if(p1==1)
                                { b.setBackgroundColor(GREEN);
a.setBackgroundColor(GREEN);}
                            else if(p2==1)
                                { b.setBackgroundColor(RED);
a.setBackgroundColor(RED);}
                    check();
                }
            }

        }
        if(fl==1)
        {
            fl=0;
            check();
        }
        else {
            p1 = p1 * -1;
            p2 = p2 * -1;
        }
        if(end) {
            end();
        }
    }

}