package com.neneng.game;

/**
 * Created by root on 8/14/14.
 */
public class Randomizer {

    public Game2048 game;

    public Randomizer(Game2048 g){
        game = g;
    }

    public int[] rand(){
        int[] rands = {
                (int)(Math.random() * 4),
                (int)(Math.random() * 4)
        };
        return rands;
    }

    public int numberGenerate(){
        int[] choices = { 2 ,  2 , 2 , 2 , 4 , 4 };
        return choices[(int)(Math.random() * 6)];
    }

    public void generate(boolean force){
        do {
            int[] rands = rand();
            if (game.labels[rands[0]][rands[1]].getText().equals("")) {
                game.labels[rands[0]][rands[1]].setText("" + ((force) ? "2" : numberGenerate()));
                break;
            }
        }while (true);
    }

}
