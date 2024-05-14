package com.example.tictactoe;

import android.util.Log;

public class HardAI {
    int[][] board=new int[3][3];
    int turns;
    boolean is_ai_first=false;
    int r_player_1,c_player_1;
    int r_player_2,c_player_2;
    HardAI(int[][] b,boolean ai,int no_of_turns,int r_of_player,int c_of_player,int r_ofp_2,int c_ofp_2,boolean simply)
    {
        turns=no_of_turns;
        is_ai_first=ai;
        board=b;
        gosimply=simply;

        // first move of player
        r_player_1=r_of_player;
        c_player_1=c_of_player;

        //second move of player
        r_player_2=r_ofp_2;
        c_player_2=c_ofp_2;
    }
    int ra,ca;
    public void play()
    {
        //Log.d("AI",""+is_ai_first);
        if(is_ai_first)
        {
            Extras e=new Extras(board);
            //  if  check corner   first move of player
            /*if(turns==2)
            {
                if(e.is_corner(r_player_1,r_player_1))
                {
                    Log.d("info","corner input when ai is first");
                    gosimply=true;
                }
            }*/

            if(gosimply)
            {
                Log.d("simple","going to use simple");
                simple();
            }
            //  if check not corner
            if(!gosimply){
                Log.d("complex","going to use complex");
                complex();
            }
        }
        else
        {
            Log.d("simple2","using simple as ai is not first");
            simple();
        }
    }
    boolean gosimply=false;
    public void complex()
    {
        /*
        if 1st move of bot--->1,1

        2nd move of bot-->random except row or column in which player has made a move


         */
        //first move of bot
        if(turns==0)
        {
            ra=1;
            ca=1;
        }

        //second move of bot
        else if(turns==2)
        {
            //if 1 row
            if(r_player_1==1)
            {
                //random between 0 to 2
                int r_=(int) (Math.random()*3);
                int c_=(int) (Math.random()*3);

                //Log.d("check123",r_+""+c_);
                while(true)
                {
                    if(board[r_][c_]==0 && r_!=1)
                    {
                        //Log.d("board2", Arrays.deepToString(board_));
                        ra=r_;
                        ca=c_;
                        break;
                    }
                    r_=(int) (Math.random()*3);
                    c_=(int) (Math.random()*3);
                }
            }
            else if(c_player_1==1)
            {
                //random between 0 to 2
                int r_=(int) (Math.random()*3);
                int c_=(int) (Math.random()*3);

                //Log.d("check123",r_+""+c_);
                while(true)
                {
                    if(board[r_][c_]==0 && c_!=1)
                    {
                        //Log.d("board2", Arrays.deepToString(board_));
                        ra=r_;
                        ca=c_;
                        break;
                    }
                    r_=(int) (Math.random()*3);
                    c_=(int) (Math.random()*3);
                }
            }
        }
        //third move and above
        if(turns>=4)
        {
            win_if_can();
            if(!over)
            {
                //3rd turn of bot
                if(turns==4)
                {
                    //if player chose on same row
                    if(r_player_1==r_player_2)
                    {
                        for(int i=0;i<3;i++)
                        {
                            if(board[r_player_1][i]==0)
                            {
                                ra=r_player_1;
                                ca=i;
                            }
                        }
                    }
                    //if player chose on same column
                    if(c_player_1==c_player_2)
                    {
                        for(int i=0;i<3;i++)
                        {
                            if(board[i][c_player_1]==0)
                            {
                                ra=i;
                                ca=c_player_1;
                            }
                        }
                    }
                    if((r_player_1!=r_player_2) && (c_player_1!=c_player_2))
                    {
                        Extras e=new Extras();
                        ra=e.get_leftrow(r_player_1,r_player_2);
                        ca=e.get_leftcolumn(c_player_1,c_player_2);
                    }
                }
            }
        }
    }
    public void simple()
    {
        /*
                check win
                check lose
                random
         */
        win_if_can();
        //if not over
        if(!over) {
            prevent_if_loss();
            //if not losing
            if(!lose)
            {
                random();
            }
        }

    }
    public void random()
    {
        //random between 0 to 2
        int r_=(int) (Math.random()*3);
        int c_=(int) (Math.random()*3);

        //Log.d("check123",r_+""+c_);
        while(true)
        {
            if(board[r_][c_]==0)
            {
                //Log.d("board2", Arrays.deepToString(board_));
                ra=r_;
                ca=c_;
                break;
            }
            r_=(int) (Math.random()*3);
            c_=(int) (Math.random()*3);
        }
    }

    boolean lose=false;
    public void prevent_if_loss() {
        Extras e=new Extras(board);
        int f=e.can_lose();
        if(f!=0)
        {
            //if losing set true to lose
            lose=true;
            if(f==1)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[0][i]==0)
                    {
                        ra=0;
                        ca=i;
                        break;
                    }
                }
            }
            //row2
            else if(f==2)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[1][i]==0)
                    {
                        ra=1;
                        ca=i;
                        break;
                    }
                }
            }
            //row3
            else if(f==3)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[2][i]==0)
                    {
                        ra=2;
                        ca=i;
                        break;
                    }
                }
            }
            //column1
            else if(f==4)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[i][0]==0)
                    {
                        ra=i;
                        ca=0;
                        break;
                    }
                }
            }
            //column2
            else if(f==5)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[i][1]==0)
                    {
                        ra=i;
                        ca=1;
                        break;
                    }
                }
            }
            //column3
            else if(f==6)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[i][2]==0)
                    {
                        ra=i;
                        ca=2;
                        break;
                    }
                }
            }
            //d1
            else if(f==7)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[i][i]==0)
                    {
                        ra=i;
                        ca=i;
                        break;
                    }
                }
            }
            //d2
            else if(f==8)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[i][2-i]==0)
                    {
                        ra=i;
                        ca=2-i;
                        break;
                    }
                }
            }
        }
    }
    boolean over=false;
    public void win_if_can() {
        Extras e=new Extras(board);
        int f=e.can_win();
        if(f!=0)
        {
            //if winning set true to over
            over=true;
            if(f==1)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[0][i]==0)
                    {
                        ra=0;
                        ca=i;
                        break;
                    }
                }
            }
            //row2
            else if(f==2)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[1][i]==0)
                    {
                        ra=1;
                        ca=i;
                        break;
                    }
                }
            }
            //row3
            else if(f==3)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[2][i]==0)
                    {
                        ra=2;
                        ca=i;
                        break;
                    }
                }
            }
            //column1
            else if(f==4)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[i][0]==0)
                    {
                        ra=i;
                        ca=0;
                        break;
                    }
                }
            }
            //column2
            else if(f==5)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[i][1]==0)
                    {
                        ra=i;
                        ca=1;
                        break;
                    }
                }
            }
            //column3
            else if(f==6)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[i][2]==0)
                    {
                        ra=i;
                        ca=2;
                        break;
                    }
                }
            }
            //d1
            else if(f==7)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[i][i]==0)
                    {
                        ra=i;
                        ca=i;
                        break;
                    }
                }
            }
            //d2
            else if(f==8)
            {
                for(int i=0;i<3;i++)
                {
                    if(board[i][2-i]==0)
                    {
                        ra=i;
                        ca=2-i;
                        break;
                    }
                }
            }
        }
    }
}
