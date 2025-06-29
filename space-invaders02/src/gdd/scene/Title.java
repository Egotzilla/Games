package gdd.scene;

import static gdd.Global.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import gdd.AudioPlayer;
import gdd.Game;

public class Title extends JPanel {
    private boolean inGame = true;
    private String message = "Game Over";

    private final Dimension d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    private final Random randomizer = new Random();

    private Timer timer;
    private Image image;
    private int frame = 0;

    private String name = "Gulizara Benjapalaporn";
    private AudioPlayer audioplayer;

    private Game game;

    public Title(Game game) {
        this.game = game;

    }

    public void start() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);

        timer = new Timer(DELAY, new GameCycle());
        timer.start();

        gameInit();
    }

    private void stop() {
        timer.stop();
        try {
            if (audioplayer != null)
                audioplayer.stop();
        } catch (Exception e) {
            System.err.println("Error stopping audio: " + e.getMessage());
        }
    }

    private void gameInit() {
        ImageIcon ii = new ImageIcon("./images/title.png");
        image = ii.getImage();

        try {
            audioplayer = new AudioPlayer("./audio/title.wav");
            audioplayer.play();
        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);

        g.drawImage(image, 0, -40, BOARD_WIDTH, BOARD_HEIGHT, null);

        if (frame % 60 < 30) {
            g.setColor(Color.white);
            g.setFont(getFont().deriveFont(Font.BOLD, 30));
            String text = "Press SPACE to Start";
            int stringWidth = g.getFontMetrics().stringWidth(text);
            int x = (d.width - stringWidth) / 2;
            g.drawString(text, x, 600);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    /* private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                BOARD_WIDTH / 2);
    } */

    private void update() {
        frame++;
    }

    private void doGameCycle() {
        update();
        repaint();
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                System.out.println("Detected SPACE key press, starting game...");
                stop();
                game.loadScene1();
            }
        }
    }
}
