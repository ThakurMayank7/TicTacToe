package com.example.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class vsai_selection extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vsai_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        background=MediaPlayer.create(this,R.raw.background2);
        background.setLooping(true);
        background.start();

        Button easy_button=(Button) findViewById(R.id.easy);
        Button hard_button=(Button) findViewById(R.id.hard);
        Button start=(Button) findViewById(R.id.startgamevsai);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();

            }
        });

        LinearLayout easy=(LinearLayout) findViewById(R.id.easy_select);
        LinearLayout hard=(LinearLayout) findViewById(R.id.hard_select);
        easy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_selection=MediaPlayer.create(vsai_selection.this,R.raw.selecting);
                m_selection.start();
                m_selection.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        m_selection.release();
                    }
                });
                //if not selected
                if(!select)
                {
                    select=true;
                    selected_mode=true;
                    easy.setBackgroundColor(getColor(R.color.select));
                }
                //selected
                else if(!selected_mode){
                    selected_mode=true;
                    easy.setBackgroundColor(getColor(R.color.select));
                    hard.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        hard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_selection=MediaPlayer.create(vsai_selection.this,R.raw.selecting);
                m_selection.start();
                m_selection.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        m_selection.release();
                    }
                });
                //not selected
                if(!select)
                {
                    select=true;
                    selected_mode=false;
                    hard.setBackgroundColor(getColor(R.color.select));
                }
                //selected
                else if(selected_mode){
                    selected_mode=false;
                    hard.setBackgroundColor(getColor(R.color.select));
                    easy.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
    }

    public void check()
    {
        EditText chance=(EditText)findViewById(R.id.chances_);
        //if any mode is selected
        if(select)
        {
            Log.d("checking","checking"+chance.getText().toString());
            //if no. of chances
            if(chance.getText().toString().isEmpty())
            {
                Log.d("data","chances not entered");
                c=6;
                begin();
            }
            else {
                if(Integer.parseInt(chance.getText().toString())>=2)
                {
                    c=Integer.parseInt(chance.getText().toString());
                    begin();
                }
                else {
                    Toast.makeText(vsai_selection.this,"Select at least 2 chances",Toast.LENGTH_LONG).show();
                }
            }


        }
        else {
            Toast.makeText(vsai_selection.this,"Select difficulty level first",Toast.LENGTH_SHORT).show();
        }
    }
    boolean select=false;
    int c=0;



    MediaPlayer m_selection;

    boolean selected_mode=true;
//true-->easy    false -->hard




    MediaPlayer background;

    public void begin()
    {
        EditText name_=(EditText) findViewById(R.id.nameofplayer);
        String name=name_.getText().toString();
        if(name.isEmpty())
        {
            name="You";
        }
        MediaPlayer m=MediaPlayer.create(this,R.raw.begin);
        m.start();
        Intent intent=new Intent(vsai_selection.this, gamescreen.class);
        Bundle bundle=new Bundle();
        bundle.putString("name2",name);
        bundle.putString("name1","BOT");
        bundle.putString("mode","vsai");
        bundle.putInt("chances",c);
        bundle.putBoolean("modetype",selected_mode);
        intent.putExtra("bundle",bundle);
        startActivity(intent);
        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                m.release();
            }
        });
        background.release();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        background.release();
    }
}