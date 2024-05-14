package com.example.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class gamescreen extends AppCompatActivity {

    Intent intent;

    MediaPlayer background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gamescreen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        background=MediaPlayer.create(this,R.raw.gamemusic);
        background.setLooping(true);
        background.start();
        intent = getIntent();
        setup();
    }
    Hard hard=new Hard();
    MediaPlayer tapping;
    public void tap(View view) {
        tapping=MediaPlayer.create(this,R.raw.tap);
        tapping.start();
        tapping.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                tapping.release();
            }
        });
        String tag = view.getTag().toString();
        r = Integer.parseInt(tag) / 10;
        c = Integer.parseInt(tag) % 10;
        //if empty block is tapped
        if (board[r][c] == 0) {
            //player vs player
            if (mode.equals("pvp"))
            {
                //true-->o false-->x
                if (turn) {
                    board[r][c] = 1;
                    set_image(r, c, 1);


                } else {
                    board[r][c] = -1;
                    set_image(r, c, -1);

                }
                turn = !turn;
                turns++;
                update_status();
                checkwin();
                //vs AI
            }
            else if (mode.equals("vsai"))
            {
                //check turns

                board[r][c]=-1;
                //Log.d("board1", Arrays.deepToString(board));
                set_image(r,c,-1);
                turns++;
                checkwin();
                //Toast.makeText(this,""+over,Toast.LENGTH_SHORT).show();
                if(hardai)
                {
                    if(turns==2)
                    {
                        hard.setPr1(r);
                        hard.setPc1(c);
                        Extras e=new Extras(board);
                        if(e.is_corner(hard.getPr1(),hard.getPc1()))
                        {
                            Log.d("info","corner input when ai is first");
                            hard.setSimply(true);
                        }
                        else
                        {
                            Log.d("info","non corner input when ai is first");
                            hard.setSimply(false);
                        }
                    }
                    if(turns==4)
                    {
                        hard.setPr2(r);
                        hard.setPc2(c);
                    }
                }
                if(!over)
                {
                    if(turns<=9)
                    {
                        //easy
                        if(mode_type)
                        {
                            EasyAI eai=new EasyAI(board);
                            eai.play();
                            board[eai.ra][eai.ca]=1;
                            set_image(eai.ra,eai.ca,1);
                            turns++;
                            checkwin();
                            if(over)
                            {
                                round_end(rend);
                            }
                        }
                        //hard
                        else
                        {

                            HardAI hai=new HardAI(board,hardai,turns,hard.getPr1(),hard.getPc1(),hard.getPr2(),hard.getPc2(), hard.getSimply());
                            hai.play();
                            board[hai.ra][hai.ca]=1;
                            set_image(hai.ra,hai.ca,1);
                            turns++;
                            checkwin();
                            if(over)
                            {
                                round_end(rend);
                            }
                        }
                    }
                }
                //if over
                else {
                    round_end(rend);
                }
            }

        }
    }
    boolean ai=true;
    //---------------------------->
    public void reset()
    {
        //set all images to nothing
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j]=0;
                set_image(i,j,0);
            }
        }
        turns=0;
        update_status();

        hard.setSimply(false);
        if(mode.equals("vsai"))
        {
            over=false;
            if(ai)
            {
                if(mode_type)
                {
                    EasyAI eai=new EasyAI(board);
                    eai.play();
                    board[eai.ra][eai.ca]=1;
                    set_image(eai.ra,eai.ca,1);
                    turns++;
                    ai=!ai;
                }
                else
                {
                    //first move of HardAI
                    HardAI hai=new HardAI(board,true,turns,hard.getPr1(),hard.getPc1(),hard.getPr2(),hard.getPc2(),hard.getSimply());
                    hai.play();
                    board[hai.ra][hai.ca]=1;
                    set_image(hai.ra,hai.ca,1);
                    turns++;
                    hardai=true;
                    //
                    ai=!ai;
                }
            }
            else {
                hardai=false;
                ai= true;
            }
        }

    }
    boolean hardai=false;
    int[][] board = new int[3][3];
    //no. of total chances input
    int chances = 0;
    //no. of chances that have progressed
    int chances_played=0;
    //no of turns played
    int turns = 0;
    //true-->o false-->x
    //whose turn it is next
    boolean turn = true;
    //mode playing
    String mode = "";
    //type of mode (difficulty)
    boolean mode_type = true;
    //scores to display
    int score1=0,score2=0;
    //names of players
    String name_1,name_2;

    public void setup() {


        Bundle bundle = intent.getBundleExtra("bundle");
        TextView name1 = (TextView) findViewById(R.id.name1);
        TextView name2 = (TextView) findViewById(R.id.name2);
        Log.d("checking1234",""+bundle);
        name_1=bundle.getString("name1");
        name_2=bundle.getString("name2");
        String s;
        s=name_1+" : ";
        name1.setText(name_1);
        s=name_2+ " : ";
        name2.setText(name_2);
        chances = bundle.getInt("chances");
        mode = bundle.getString("mode");

        TextView st=(TextView) findViewById(R.id.status);
        if(mode.equals("pvp"))
        {
            String k="It's "+name_1+"'s Turn.";
            st.setText(k);
        }
        if(mode.equals("vsai"))
        {
            String k="Your Chance";
        }

        //true--> easy    false-->hard
        mode_type = bundle.getBoolean("modetype");
        //Toast.makeText(gamescreen.this,bundle.getString("name1")+bundle.getString("name2")+turns+mode+mode_type,Toast.LENGTH_SHORT).show();
        //Log.d("INFO",bundle.getString("name1")+bundle.getString("name2")+turns+mode+mode_type);

        update_score();
    }


    //---------------------------->
    int r, c;


    int rend;
    boolean over=false;
    public void checkwin() {
        if(mode.equals("pvp"))
        {
            //if o wins
            if(fn1(1))
            {
                //Toast.makeText(this,"o wins",Toast.LENGTH_SHORT).show();

                round_end(1);

            }
            //if x wins
            else if(fn1(-1))
            {
                //Toast.makeText(this,"x wins",Toast.LENGTH_SHORT).show();

                round_end(-1);

            }
            //if no one wins
            else if (turns==9)
            {
                //Toast.makeText(this,"no one wins",Toast.LENGTH_SHORT).show();

                round_end(0);

            }
        }

        else if(mode.equals("vsai"))
        {
            //if o wins
            if(fn1(1))
            {
                //Toast.makeText(this,"o wins",Toast.LENGTH_SHORT).show();
                over=true;
                //round_end(1);

                rend=1;
            }
            //if x wins
            else if(fn1(-1))
            {
                //Toast.makeText(this,"x wins",Toast.LENGTH_SHORT).show();
                over=true;
                //round_end(-1);

                rend=-1;
            }
            //if no one wins
            else if (turns==9)
            {
                //Toast.makeText(this,"no one wins",Toast.LENGTH_SHORT).show();
                over=true;
                //round_end(0);

                rend=0;
            }
        }
    }

    public void round_end(int rst)
    {
        //if o wins
        if(rst==1)
        {
            score1++;
        }
        //if x wins
        else if(rst==-1)
        {
            score2++;
        }
        //if draw
        /*else if(rst==0)
        {

        }*/
        chances_played++;
        update_score();
        if(chances_played==chances)
        {
            //Toast.makeText(this,"finished",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,results.class);
            Bundle bun=new Bundle();
            bun.putString("name1",name_1);
            bun.putString("name2",name_2);
            bun.putInt("chances",chances);
            bun.putString("mode",mode);
            bun.putBoolean("modetype",mode_type);
            bun.putInt("score1",score1);
            bun.putInt("score2",score2);
            intent.putExtra("bun",bun);
            startActivity(intent);
            background.release();
            finish();
        }
        else
        {
            reset();
        }
    }

    //type--->what to check 1-->0 -1-->x
    public boolean fn1(int type) {
        //d1--> principal diagonal
        int r1 = 0, r2 = 0, r3 = 0, c1 = 0, c2 = 0, c3 = 0, d1 = 0, d2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int a = board[i][j];
                if (i == 0) {
                    if (a == type) {
                        r1++;
                    }
                }
                if (i == 1) {
                    if (a == type) {
                        r2++;
                    }
                }
                if (i == 2) {
                    if (a == type) {
                        r3++;
                    }
                }
                if (j == 0) {
                    if (a == type) {
                        c1++;
                    }
                }
                if (j == 1) {
                    if (a == type) {
                        c2++;
                    }
                }
                if (j == 2) {
                    if (a == type) {
                        c3++;
                    }
                }
                if (i == j) {
                    //d1
                    if (a == type) {
                        d1++;
                    }
                }
                if (i + j == 2) {
                    //d2
                    if (a == type) {
                        d2++;
                    }
                }
            }
        }
        //if someone wins return 1
        if (r1==3 || r2==3 || r3==3 ||c1==3 || c2==3 || c3==3 || d1==3 || d2==3)
        {
            return true;
        }
        return false;
    }
    //---------------------------------->
    //  -1---->x   1-->o   0-->nothing
    public void update_status()
    {
        TextView status=(TextView) findViewById(R.id.status);
        if(mode.equals("pvp"))
        {
            if(turn)
            {
                String s="It's "+name_1+"'s Turn.";
                status.setText(s);
            }
            else {
                String s="It's "+name_2+"'s Turn.";
                status.setText(s);
            }
        }
    }
    public void set_image(int row,int column,int image)
    {
        ImageView imageView=(ImageView) findViewById(R.id.board).findViewWithTag(row+""+column);
        if(image==-1)
        {
            imageView.setImageResource(R.drawable.x);
        }
        else if(image==0)
        {
            imageView.setImageResource(R.drawable.nothing);
        }
        else if(image==1)
        {
            //Toast.makeText(this,"setting o"+(row*10)+column,Toast.LENGTH_SHORT).show();
            imageView.setImageResource(R.drawable.o);
        }
    }
    //if o wins -->1
    public void update_score()
    {
        TextView sc1=(TextView) findViewById(R.id.score1);
        TextView sc2=(TextView) findViewById(R.id.score2);

        String scr1=score1+"/"+chances_played;
        sc1.setText(scr1);
        String scr2=+score2+"/"+chances_played;
        sc2.setText(scr2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        background.release();
    }
}