package client.startup;

import client.view.*;
import shared.Variables;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame jf = new JFrame("Pong");
        PongBoard pongBoard = new PongBoard();

        jf.add(pongBoard);
        jf.pack();
        jf.setSize(Variables.BOARD_WIDTH+22,Variables.BOARD_HEIGHT+56);
        jf.setLocation((int)Variables.X_POSITION,(int)Variables.Y_POSITION);
        jf.setVisible(true);

        pongBoard.init();
        
        JFrame jf2 = new JFrame("Pong2");
        PongBoard pongBoard2 = new PongBoard();

        jf2.add(pongBoard2);
        jf2.pack();
        jf2.setSize(Variables.BOARD_WIDTH+22,Variables.BOARD_HEIGHT+56);
        jf2.setLocation((int)Variables.X_POSITION,(int)Variables.Y_POSITION);
        jf2.setVisible(true);

        pongBoard2.init();
	}

}
