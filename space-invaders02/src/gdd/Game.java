package gdd;

import gdd.scene.Scene1;
import gdd.scene.Title;

import javax.swing.JFrame;

public class Game extends JFrame  {

    private Title title;
    private Scene1 scene1;

    public Game() {
        initUI();

        // preload the scene
        title = new Title(this);
        scene1 = new Scene1();
        loadTitle();
    }

    private void initUI() {
        setTitle("Space Invaders");
        setSize(Global.BOARD_WIDTH, Global.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void loadTitle() {
        getContentPane().removeAll(); // Remove all scene out
        add(title);
        title.start();
        revalidate();
        repaint();
    }

    public void loadScene1() {
        getContentPane().removeAll(); // Remove all scene out
        add(scene1);
        scene1.start();
        revalidate();
        repaint();
    }
}