package com.example.westagile;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "Background" ;

    RelativeLayout rel;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rel = findViewById(R.id.back);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        Log.i("yo",sharedpreferences.getInt("color",0)+"");
        if(sharedpreferences.getInt("color",0)==0){
            rel.setBackgroundColor(Color.BLUE);
            editor.putInt("color",Color.BLUE);
            editor.apply();
        }else{
            randFunc();
        }

        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randFunc();
            }
        });

    }

    public void randFunc(){

        SharedPreferences.Editor editor = sharedpreferences.edit();

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        rel.setBackgroundColor(color);
        editor.putInt("color",color);
        editor.apply();

    }


    @Override
    protected void onStart() {
        randFunc();
        super.onStart();
    }

    @Override
    protected void onResume() {
        randFunc();
        super.onResume();
    }
}
