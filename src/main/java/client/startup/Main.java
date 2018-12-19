package client.startup;

import client.view.PongBoard;

public class Main {
    public static void main(String[] args) {
        System.out.println("I'm alive!");
        new PongBoard().init();

    }
}
