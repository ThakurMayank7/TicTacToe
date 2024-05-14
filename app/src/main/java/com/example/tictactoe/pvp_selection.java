package com.example.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class pvp_selection extends AppCompatActivity {

    MediaPlayer background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pvp_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        background=MediaPlayer.create(this,R.raw.background2);
        background.setLooping(true);
        background.start();

        Button button=(Button) findViewById(R.id.startgamepvp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                check();
            }
        });
    }

    public void check()
    {
        EditText chances=(EditText)findViewById(R.id.chances_);

        EditText player1=(EditText)findViewById(R.id.player1);
        EditText player2=(EditText)findViewById(R.id.player2);

        if(!chances.getText().toString().isEmpty())
        {

            String ch=chances.getText().toString();
            int chance=Integer.parseInt(ch);
            //Toast.makeText(this,"not null"+chance,Toast.LENGTH_SHORT).show();
            if(chance>=2)
            {
                c=chance;
                start();
            }
            else {
                //MediaPlayer m1=MediaPlayer.create(pvp_selection.this,R.raw.cannot_click);
                //m1.start();
                Toast.makeText(pvp_selection.this,"Enter at least 2 chances",Toast.LENGTH_LONG).show();
            }
        }
        else {
            //Log.w("here","nothing entered");
            c=6;
            start();
        }

    }
    int c=0;
    public void start()
    {

        EditText player1=(EditText)findViewById(R.id.player1);
        EditText player2=(EditText)findViewById(R.id.player2);
        MediaPlayer m2=MediaPlayer.create(pvp_selection.this,R.raw.begin);
        m2.start();
        String name1=player1.getText().toString();
        String name2=player2.getText().toString();
        if(name1.isEmpty())
        {
            name1="Player 1";
        }
        if(name2.isEmpty())
        {
            name2="Player 2";
        }

        Bundle bundle=new Bundle();
        bundle.putString("name1",name1);
        bundle.putString("name2",name2);
        bundle.putString("mode","pvp");
        bundle.putInt("chances",c);

        //Toast.makeText(this,name1+name2,Toast.LENGTH_SHORT).show();

        Intent intent =new Intent(pvp_selection.this,gamescreen.class);
        intent.putExtra("bundle",bundle);
        m2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //Toast.makeText(pvp_selection.this,"released",Toast.LENGTH_SHORT).show();
                m2.release();
            }
        });
        startActivity(intent);
        //Toast.makeText(this,"finished",Toast.LENGTH_SHORT).show();
        background.release();
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        background.release();
    }
}