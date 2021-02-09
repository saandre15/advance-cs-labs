import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MazeCell
{
    int status;     // 0 = never visited
    // 1 = on current path
    // 2 = on dead-end path
    int setNumber;  // used when constructing maze

    boolean leftHedge;
    boolean rightHedge;
    boolean topHedge;
    boolean bottomHedge;

    public MazeCell(int sn)
    {
        status = 0;
        setNumber = sn;
        leftHedge = rightHedge = topHedge = bottomHedge = true;
    }

    public int getStatus()
    {
        return status;
    }
    public void setStatus( int st )
    {
        status = st;
    }

    public int getSetNumber()
    {
        return setNumber;
    }
    public void setSetNumber( int n )
    {
        setNumber = n;
    }

    public void setRightHedge( boolean value )
    {
        rightHedge = value;
    }
    public void setLeftHedge( boolean value )
    {
        leftHedge = value;
    }
    public void setTopHedge( boolean value )
    {
        topHedge = value;
    }
    public void setBottomHedge( boolean value )
    {
        bottomHedge = value;
    }

    public boolean getRightHedge( )
    {
        return rightHedge;
    }
    public boolean getLeftHedge( )
    {
        return leftHedge;
    }
    public boolean getTopHedge( )
    {
        return topHedge;
    }
    public boolean getBottomHedge( )
    {
        return bottomHedge;
    }
}

