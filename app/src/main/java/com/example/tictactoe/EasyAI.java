package com.example.tictactoe;

import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;

public class EasyAI
{
    int[][] board_=new int[3][3];
    EasyAI(int[][] board){
        board_=board;
    }

    int ra,ca;
    //AI is o by default
    boolean win=false;
    public void play()
    {
        //check win situations
        Extras ex=new Extras(board_);
        int f=ex.can_win();
        //Log.d("xyz",f+"");
        win_if_can(f);

        //if not winning
        if(!checker)
        {
            //random between 0 to 2
            int r_=(int) (Math.random()*3);
            int c_=(int) (Math.random()*3);

            //Log.d("check123",r_+""+c_);
            while(true)
            {
                if(board_[r_][c_]==0)
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
    boolean checker=false;
    public  void win_if_can(int f)
    {
        if(f!=0)
        {
            //row1
            if(f==1)
            {
                for(int i=0;i<3;i++)
                {
                    if(board_[0][i]==0)
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
                    if(board_[1][i]==0)
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
                    if(board_[2][i]==0)
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
                    if(board_[i][0]==0)
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
                    if(board_[i][1]==0)
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
                    if(board_[i][2]==0)
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
                    if(board_[i][i]==0)
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
                    if(board_[i][2-i]==0)
                    {
                        ra=i;
                        ca=2-i;
                        break;
                    }
                }
            }
            //if winning set true to checker
            checker=true;
        }

    }

}
