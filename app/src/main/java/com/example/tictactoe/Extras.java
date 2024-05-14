package com.example.tictactoe;

public class Extras{

    public boolean is_corner(int row,int column)
    {
        if((row==0 && column==0) || (row==0 && column==2) || (row==2 && column==0) || (row==2 && column==2))
        {
            return true;
        }
        return false;
    }
    //        constructor
    Extras()
    {

    }
    int[][] board=new int[3][3];
    Extras(int[][] b)
    {
        board=b;
    }
    public int can_win()
    {
        //d1--> principal diagonal
        int r1 = 0, r2 = 0, r3 = 0, c1 = 0, c2 = 0, c3 = 0, d1 = 0, d2 = 0;
        //these count empty blocks
        int r1_=0,r2_=0,r3_=0, c1_ = 0, c2_ = 0, c3_ = 0, d1_ = 0, d2_ = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int a = board[i][j];
                if (i == 0) {
                    if (a == 1) {
                        r1++;
                    }
                    if(a==0)
                    {
                        r1_++;
                    }
                }
                if (i == 1) {
                    if (a == 1) {
                        r2++;
                    }
                    if(a==0)
                    {
                        r2_++;
                    }
                }
                if (i == 2) {
                    if (a == 1) {
                        r3++;
                    }
                    if(a==0)
                    {
                        r3_++;
                    }
                }
                if (j == 0) {
                    if (a == 1) {
                        c1++;
                    }
                    if(a==0)
                    {
                        c1_++;
                    }
                }
                if (j == 1) {
                    if (a == 1) {
                        c2++;
                    }
                    if(a==0)
                    {
                        c2_++;
                    }
                }
                if (j == 2) {
                    if (a == 1) {
                        c3++;
                    }
                    if(a==0)
                    {
                        c3_++;
                    }
                }
                if (i == j) {
                    //d1
                    if (a == 1) {
                        d1++;
                    }
                    if(a==0)
                    {
                        d1_++;
                    }
                }
                if (i + j == 2) {
                    //d2
                    if (a == 1) {
                        d2++;
                    }
                    if(a==0)
                    {
                        d2_++;
                    }
                }
            }
        }
        if(r1==2 && r1_==1)
        {
            return 1;
        }
        if(r2==2 && r2_==1)
        {
            return 2;
        }
        if(r3==2&& r3_==1)
        {
            return 3;
        }
        if(c1==2&& c1_==1)
        {
            return 4;
        }
        if(c2==2 && c2_==1)
        {
            return 5;
        }
        if(c3==2 && c3_==1)
        {
            return 6;
        }
        if(d1==2 && d1_==1)
        {
            return 7;
        }
        if(d2==2 && d2_==1)
        {
            return 8;
        }
        return 0;
    }
    public int can_lose()
    {
        //d1--> principal diagonal
        int r1 = 0, r2 = 0, r3 = 0, c1 = 0, c2 = 0, c3 = 0, d1 = 0, d2 = 0;
        //these count empty blocks
        int r1_=0,r2_=0,r3_=0, c1_ = 0, c2_ = 0, c3_ = 0, d1_ = 0, d2_ = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int a = board[i][j];
                if (i == 0) {
                    if (a == -1) {
                        r1++;
                    }
                    if(a==0)
                    {
                        r1_++;
                    }
                }
                if (i == 1) {
                    if (a == -1) {
                        r2++;
                    }
                    if(a==0)
                    {
                        r2_++;
                    }
                }
                if (i == 2) {
                    if (a == -1) {
                        r3++;
                    }
                    if(a==0)
                    {
                        r3_++;
                    }
                }
                if (j == 0) {
                    if (a == -1) {
                        c1++;
                    }
                    if(a==0)
                    {
                        c1_++;
                    }
                }
                if (j == 1) {
                    if (a == -1) {
                        c2++;
                    }
                    if(a==0)
                    {
                        c2_++;
                    }
                }
                if (j == 2) {
                    if (a == -1) {
                        c3++;
                    }
                    if(a==0)
                    {
                        c3_++;
                    }
                }
                if (i == j) {
                    //d1
                    if (a == -1) {
                        d1++;
                    }
                    if(a==0)
                    {
                        d1_++;
                    }
                }
                if (i + j == 2) {
                    //d2
                    if (a == -1) {
                        d2++;
                    }
                    if(a==0)
                    {
                        d2_++;
                    }
                }
            }
        }
        if(r1==2 && r1_==1)
        {
            return 1;
        }
        if(r2==2 && r2_==1)
        {
            return 2;
        }
        if(r3==2&& r3_==1)
        {
            return 3;
        }
        if(c1==2&& c1_==1)
        {
            return 4;
        }
        if(c2==2 && c2_==1)
        {
            return 5;
        }
        if(c3==2 && c3_==1)
        {
            return 6;
        }
        if(d1==2 && d1_==1)
        {
            return 7;
        }
        if(d2==2 && d2_==1)
        {
            return 8;
        }
        return 0;
    }
    public int get_leftrow(int r1,int r2)
    {
        int r=69;
        for(int i=0;i<3;i++)
        {
            if(i!=r1 && i!=r2)
            {
                r=i;
                break;
            }
        }
        return r;
    }
    public int get_leftcolumn(int c1,int c2)
    {
        int c=69;
        for(int i=0;i<3;i++)
        {
            if(i!=c1 && i!=c2)
            {
                c=i;
                break;
            }
        }
        return c;
    }

}
