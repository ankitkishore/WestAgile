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
        ArrayList<Integer> color = new ArrayList<Integer>();
        color.add(Color.BLACK);
        color.add(Color.BLUE);
        color.add(Color.GRAY);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        Integer removeColor = sharedpreferences.getInt("color",0);
        color.remove(removeColor);
        int r = (int) ((Math.random()*10)%2);
        switch (r){
            case 0:
                rel.setBackgroundColor(color.get(0));
                editor.putInt("color",color.get(0));
                editor.apply();
                break;
            case 1:
                rel.setBackgroundColor(color.get(1));
                editor.putInt("color",color.get(1));
                editor.apply();
                break;
        }

        Log.i("yo",((Math.random()*10)%3)+""+removeColor);

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
