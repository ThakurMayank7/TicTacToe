package com.example.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


MediaPlayer mediaPlayer;
MediaPlayer background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        background=MediaPlayer.create(this,R.raw.background1);
        background.setLooping(true);
        background.start();

        Button pvp=(Button) findViewById(R.id.pvp);
        Button vsai=(Button) findViewById(R.id.vsai);

        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.begin);

        pvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();


                Intent intent1=new Intent(MainActivity.this, pvp_selection.class);
                //Toast.makeText(MainActivity.this, "PVP", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
            }
        });
        vsai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();

                Intent intent2=new Intent(MainActivity.this, vsai_selection.class);
                startActivity(intent2);
            }
        });
        /*mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
                Toast.makeText(MainActivity.this,"released",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Toast.makeText(MainActivity.this,"released",Toast.LENGTH_SHORT).show();

        mediaPlayer.release();
        background.release();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
        //Toast.makeText(MainActivity.this,"stopped",Toast.LENGTH_SHORT).show();
        background.stop();
        background.release();
    }

    @Override
    protected void onRestart() {
        //Toast.makeText(this,"restarted",Toast.LENGTH_SHORT).show();
        super.onRestart();
        mediaPlayer=MediaPlayer.create(this,R.raw.begin);
        background=MediaPlayer.create(this,R.raw.background1);
    }
    /*
    */
}