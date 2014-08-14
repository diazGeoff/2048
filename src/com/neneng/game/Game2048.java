package com.neneng.game;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Game2048 extends JFrame{

    public JLabel[][] labels = new JLabel[4][4];
    public Randomizer randGenerate = new Randomizer(this);
    public boolean ableToMove = true;
    public boolean moveLeftStart = true;
    public boolean moveRightStart = true;
    public boolean moveUpStart = true;
    public boolean moveDownStart = true;

    public  Game2048(){
        setTitle("2048 Game by Maiden");
        JPanel panel = new JPanel(new GridLayout(4 , 4));
        for(int i = 0 ; i < 4 ; i ++) {
            for(int j = 0 ; j < 4 ; j ++) {
                labels[i][j] = new JLabel();
                labels[i][j].setFont(labels[i][j].getFont().deriveFont(24.0f));
                labels[i][j].setBackground(Color.cyan);
                labels[i][j].setOpaque(true);
                labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                labels[i][j].setText("");
                panel.add(labels[i][j]);
            }
        }
        randGenerate.generate(true);
        randGenerate.generate(true);
        panel.setFocusable(true);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == KeyEvent.VK_LEFT ){
                    moveLeft();
                    if(moveLeftStart){
                        ableToMove = true;
                        moveLeftStart = false;
                        moveRightStart = moveDownStart = moveUpStart = true;
                    }
                    if(ableToMove)randGenerate.generate(false);
                }else if( e.getKeyCode() == KeyEvent.VK_RIGHT ){
                    moveRight();
                    if(moveRightStart){
                        ableToMove = true;
                        moveRightStart = false;
                        moveLeftStart = moveDownStart = moveUpStart = true;
                    }
                    if(ableToMove)randGenerate.generate(false);
                }else if( e.getKeyCode() == KeyEvent.VK_UP ){
                    moveUp();
                    if(moveUpStart){
                        ableToMove = true;
                        moveUpStart = false;
                        moveLeftStart = moveDownStart = moveRightStart = true;
                    }
                    if(ableToMove)randGenerate.generate(false);
                }else if( e.getKeyCode() == KeyEvent.VK_DOWN ){
                    moveDown();
                    if(moveDownStart){
                        ableToMove = true;
                        moveDownStart = false;
                        moveLeftStart = moveUpStart = moveRightStart = true;
                    }
                    if(ableToMove)randGenerate.generate(false);
                }
            }

        });
        add(panel);
        setSize(400 , 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void moveLeft(){
        LinkedList lines;
        for(int i = 0 ; i < 4 ; i ++){
            LinkedList list = new LinkedList();
            if(!labels[i][0].getText().equals("")){ list.add(labels[i][0].getText()); labels[i][0].setText("");}
            if(!labels[i][1].getText().equals("")){ list.add(labels[i][1].getText()); labels[i][1].setText("");}
            if(!labels[i][2].getText().equals("")){ list.add(labels[i][2].getText()); labels[i][2].setText("");}
            if(!labels[i][3].getText().equals("")){ list.add(labels[i][3].getText()); labels[i][3].setText("");}
            lines = merge(list);
            int size = lines.size();
            for(int j = 0 ; j < size ; j ++){
                labels[i][j].setText((String)lines.removeFirst());
            }
        }
    }

    public void moveRight(){
        LinkedList lines;
        for(int i = 0 ; i < 4 ; i ++){
            LinkedList list = new LinkedList();
            if(!labels[i][3].getText().equals("")){ list.add(labels[i][3].getText()); labels[i][3].setText("");}
            if(!labels[i][2].getText().equals("")){ list.add(labels[i][2].getText()); labels[i][2].setText("");}
            if(!labels[i][1].getText().equals("")){ list.add(labels[i][1].getText()); labels[i][1].setText("");}
            if(!labels[i][0].getText().equals("")){ list.add(labels[i][0].getText()); labels[i][0].setText("");}
            lines = merge(list);
            int size = ( 4 - lines.size() );
            for(int j = 3 ; j >= size ; j --){
                labels[i][j].setText((String)lines.removeFirst());
            }
        }
    }

    public void moveUp(){
        LinkedList lines;
        for(int i = 0 ; i < 4 ; i ++){
            LinkedList list = new LinkedList();
            if(!labels[0][i].getText().equals("")){ list.add(labels[0][i].getText()); labels[0][i].setText("");}
            if(!labels[1][i].getText().equals("")){ list.add(labels[1][i].getText()); labels[1][i].setText("");}
            if(!labels[2][i].getText().equals("")){ list.add(labels[2][i].getText()); labels[2][i].setText("");}
            if(!labels[3][i].getText().equals("")){ list.add(labels[3][i].getText()); labels[3][i].setText("");}
            lines = merge(list);
            int size = lines.size();
            for(int j = 0 ; j < size ; j ++){
                labels[j][i].setText((String)lines.removeFirst());
            }
        }
    }

    public void moveDown(){
        LinkedList lines;
        for(int i = 0 ; i < 4 ; i ++){
            LinkedList list = new LinkedList();
            if(!labels[3][i].getText().equals("")){ list.add(labels[3][i].getText()); labels[3][i].setText("");}
            if(!labels[2][i].getText().equals("")){ list.add(labels[2][i].getText()); labels[2][i].setText("");}
            if(!labels[1][i].getText().equals("")){ list.add(labels[1][i].getText()); labels[1][i].setText("");}
            if(!labels[0][i].getText().equals("")){ list.add(labels[0][i].getText()); labels[0][i].setText("");}
            lines = merge(list);
            int size = ( 4 - lines.size() );
            for(int j = 3 ; j >= size ; j --){
                labels[j][i].setText((String)lines.removeFirst());
            }
        }
    }

    public LinkedList merge(LinkedList link){
        LinkedList list = new LinkedList();
        if(link.size() == 0){
            return list;
        }else{
            String returnValue;
            boolean once = true;
            while( link.size() != 0 ) {
                if(list.size() == 0){
                    returnValue = (String) link.removeFirst();
                }else {
                    returnValue = (String) list.removeLast();
                }
                String holder = (link.size() != 0) ? (String)link.removeFirst() : "";
                if(returnValue.equals(holder) && once){
                    list.add("" + (Integer.parseInt(returnValue) + Integer.parseInt(holder)));
                    once = false;
                    ableToMove = true;
                }else{
                    if(!holder.equals("")){
                        list.add(returnValue);
                        list.add(holder);
                    }else{
                        list.add(returnValue);
                    }
                    once = true;
                    ableToMove = false;
                }
            }
//            System.out.println(list);
            return list;
        }
    }

    public static void main(String[] arg){
        Game2048 game = new Game2048();
    }

}
