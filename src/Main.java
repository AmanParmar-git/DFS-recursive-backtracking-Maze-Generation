package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Main {

    private static final int RectSize = 40;
    private static int h,w;
    private static Node[][] arr;
    private static JFrame f;
    private static Random random;
    private static Set<Node> visited , currently;
    private static final int delay = 1;


    public static void main(String[] args) {
        f = new JFrame();
        random = new Random();
        f.setSize(1500,800);
        h = f.getHeight() - 40;
        w = f.getWidth() - 20;
        arr = new Node[w/RectSize][h/RectSize];
        for(int i = 0; i < w /RectSize; i++){
            for(int j = 0; j < h/RectSize ; j++){
                arr[i][j] = new Node(i,j,true,true,true,true);
            }
        }

        visited = new HashSet<>();
        currently = new HashSet<>();

        Canvas canvas = new Canvas();
        f.add(canvas);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyChar());
                if(e.getKeyChar() == 'a') {
                        new Thread(() -> {
                            try {
                                Main.stackMaze();
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }).start();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public static void stackMaze() throws InterruptedException {
        Stack<Node> stack = new Stack<>();

        stack.push(arr[0][0]);
        visited.add(arr[0][0]);

        while (!stack.isEmpty()){

            var current = stack.pop();
            currently.add(current);
            f.repaint();
            Thread.sleep(delay);

            ArrayList<Node> children = getChildren(current);
            if(children.size() == 0) {
                currently.remove(current);
                continue;
            }

            stack.push(current);


            int childindex = random.nextInt(children.size());

            var child = children.get(childindex);

            removeWall(child,current);

            visited.add(child);
            stack.push(child);


        }
        currently.clear();
        f.repaint();
    }

    private static void removeWall(Node child, Node current) throws InterruptedException {

        int x = child.getX() - current.getX();
        int y = child.getY() - current.getY();

        if(y > 0)
        {
            child.setHasTop(false);
            current.setHasBottom(false);
        }

        else if(y < 0){
            child.setHasBottom(false);
            current.setHasTop(false);
        }

        else if(x > 0){
            child.setHasLeft(false);
            current.setHasRight(false);
        }
        else if(x < 0){
            child.setHasRight(false);
            current.setHasLeft(false);
        }

            Thread.sleep(delay);
        currently.remove(current);

        f.repaint();
    }


    private static ArrayList<Node> getChildren(Node current) {

        ArrayList<Node> ans = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                    if ((j == 0 && i == 0) || (i == -1 && j == -1) || (i == -1 && j == 1) || (i == 1 && j == -1) || (i == 1 && j == 1))
                        continue;
                int dx = current.getX() + i;
                int dy = current.getY() + j;


                if (dx < 0 || dy < 0 || dx > arr.length - 1 || dy > arr[0].length - 1) {
                    continue;
                }
                if(!visited.contains(arr[dx][dy]))
                ans.add(arr[dx][dy]);

            }
        }
        return ans;
    }

    static class Canvas extends JComponent{
        @Override
        public void paint(Graphics g2) {
            Graphics2D g = (Graphics2D) g2;
            g.setStroke(new BasicStroke(2));
            for(int i = 1; i < w ; i+=RectSize){
                for(int j = 1; j < h ; j+=RectSize){
                    if(currently.contains(arr[i/RectSize][j/RectSize])) {
                        g.setColor(Color.green);
                        g.fillRect(i, j, RectSize, RectSize);
                    }
                    else if(visited.contains(arr[i/RectSize][j/RectSize]))
                    {
                        g.setColor(Color.WHITE);
                        if(arr[i/RectSize][j/RectSize].isHasTop())
                            g.drawLine(i,j,i+RectSize,j);
                        if(arr[i/RectSize][j/RectSize].isHasRight())
                            g.drawLine(i+RectSize,j,i+RectSize,j+RectSize);
                        if(arr[i/RectSize][j/RectSize].isHasBottom())
                            g.drawLine(i,j+RectSize,i+RectSize,j+RectSize);
                        if(arr[i/RectSize][j/RectSize].isHasLeft())
                            g.drawLine(i,j,i,j+RectSize);

                        g.setColor(Color.BLACK);
                        g.fillRect(i,j,RectSize,RectSize);
                    }
                }
            }
        }
    }

}
