package com.example.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class results extends AppCompatActivity {

    MediaPlayer back;
    MediaPlayer win;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();
        display();
        win=MediaPlayer.create(this,R.raw.win3);
        win.start();
        back=MediaPlayer.create(this,R.raw.gamemusic);
        win.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                win.release();
                back.start();
            }
        });
        back.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                back.stop();
                back.release();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.stop();
                back.release();
                rest=MediaPlayer.create(results.this,R.raw.begin);
                rest.start();
                rest.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        rest.release();
                    }
                });
                restart();
            }
        });
        mmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.stop();
                back.release();
                mmp=MediaPlayer.create(results.this,R.raw.selecting);
                mmp.start();
                mmp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mmp.release();
                    }
                });
                finish();
            }
        });
    }

    public void restart()
    {
        Intent in1=new Intent(results.this,gamescreen.class);
        Bundle bundle=new Bundle();
        bundle.putString("name1",name1);
        bundle.putString("name2",name2);
        bundle.putString("mode",mode);
        bundle.putInt("chances",chances);
        bundle.putBoolean("modetype",mode_type);
        //Log.d("checking123","reached here"+bundle);
        in1.putExtra("bundle",bundle);
        startActivity(in1);
        back.release();
        finish();
    }
    int score1,score2,chances;
    String name1,name2,mode;
    boolean mode_type;
    public void display()
    {
        TextView player_won=(TextView) findViewById(R.id.playerwinning);
        TextView result=(TextView) findViewById(R.id.result);
        String s;
        if(score1>score2)
        {
            player_won.setText(name1);
            result.setText("WON!");
        }
        else if(score2>score1)
        {
            player_won.setText(name2);
            result.setText("WON!");
        }
        else if (score1==score2)
        {
            result.setText("Draw!");
        }
        TextView pl1=(TextView) findViewById(R.id.pl1);
        TextView pl2=(TextView) findViewById(R.id.pl2);
        TextView sc1=(TextView) findViewById(R.id.sc1);
        TextView sc2=(TextView) findViewById(R.id.sc2);
        pl1.setText(name1);
        pl2.setText(name2);
        s=" : "+score1+"/"+chances;
        sc1.setText(s);
        s=" : "+score2+"/"+chances;
        sc2.setText(s);
    }
    Intent intent;
    Bundle bun;
    Button restart;
    Button mmenu;
    MediaPlayer rest;
    MediaPlayer mmp;
    public void initialize()
    {
        restart=(Button) findViewById(R.id.restart);
        mmenu=(Button) findViewById(R.id.mmenu);
        intent=getIntent();
        bun=intent.getBundleExtra("bun");
        score1=bun.getInt("score1");
        score2=bun.getInt("score2");
        name1=bun.getString("name1");
        name2=bun.getString("name2");
        chances=bun.getInt("chances");
        mode=bun.getString("mode");
        mode_type=bun.getBoolean("modetype");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        back.release();
        win.release();
    }

    @Override
    protected void onStop() {
        super.onStop();
        back.release();
        win.release();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //win=MediaPlayer.create(this,R.raw.win3);
        back=MediaPlayer.create(this,R.raw.gamemusic);
        back.start();
    }
}