package startup;

import client.view.*;
import shared.Variables;

import javax.swing.*;
import java.awt.*;

public class Main
{

    public static void main(String[] args)
    {

        JFrame jf = new JFrame("Pong");
        PongBoard pongBoard = new PongBoard();

        jf.add(pongBoard);
        jf.pack();
        jf.setSize(Variables.BOARD_WIDTH+22,Variables.BOARD_HEIGHT+56);
        jf.setLocation((int)Variables.X_POSITION,(int)Variables.Y_POSITION);
        jf.setVisible(true);

        pongBoard.init();
        

    }
}
