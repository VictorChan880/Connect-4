package testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NewGamePanel extends JPanel implements MouseListener{
    /*
    ------------------------------------
    |              Title              |
    |  EasyAI Button MediumAI Button  |
    |   MediumAI Button PVP Button    |
    -----------------------------------
     */

    static int divisor = 4;

    //Constants for panel layout
    static final int SCREEN_WIDTH = 1500/2;
    static final int SCREEN_HEIGHT= 1000/2;
    static final int BOARD_WIDTH = 700/divisor;
    static final int BOARD_HEIGHT = 600/divisor;
    static final int UNIT_SIZE = 100/2;
    static final int BOARD_X = 125;
    static final int BOARD_Y = 0;
    static final int GAME_UNITS = (BOARD_WIDTH * BOARD_HEIGHT) / UNIT_SIZE;
    static int MULTIPLIER = 2;
    static final int OFFSET = 35;
    static int clicks = 0;
    static final Color COLOURS[] = {Color.red, Color.ORANGE, Color.yellow, Color.green, Color.cyan, Color.blue,  new Color(75,0,130), };
    static final Random rand = new Random();
    static int idx = 0;
    JPanel backGround1 = new JPanel() {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(lightBlue);
            g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            //draw emptyCircles
            int index = idx;
            for (int j = 0; j < SCREEN_HEIGHT / UNIT_SIZE; j++) {
                for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                    index = (index+1)% COLOURS.length;
                    Color colour = COLOURS[index];
                    g.setColor(colour);
                    g.fillOval(i * UNIT_SIZE * MULTIPLIER-OFFSET, j * UNIT_SIZE * MULTIPLIER-OFFSET, UNIT_SIZE*MULTIPLIER, UNIT_SIZE*MULTIPLIER);
                    g.setColor(Color.BLACK);
                    g.drawOval(i * UNIT_SIZE * MULTIPLIER-OFFSET, j * UNIT_SIZE * MULTIPLIER-OFFSET, UNIT_SIZE*MULTIPLIER, UNIT_SIZE*MULTIPLIER);
                }
            }
            idx+=6;
        }
    };
    JPanel backGround2 = new JPanel() {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(lightBlue);
            g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            //draw emptyCircles
            int index = idx;
            for (int j = 0; j < SCREEN_HEIGHT / UNIT_SIZE; j++) {
                for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                    index = (index+1)% COLOURS.length;
                    Color colour = COLOURS[index];
                    //Color colour = (tokenCount%2 == 0? Color.red: Color.yellow);
                    g.setColor(colour);
                    g.fillOval(i * UNIT_SIZE * MULTIPLIER-OFFSET, j * UNIT_SIZE * MULTIPLIER-OFFSET, UNIT_SIZE*MULTIPLIER, UNIT_SIZE*MULTIPLIER);
                    g.setColor(Color.BLACK);
                    g.drawOval(i * UNIT_SIZE * MULTIPLIER-OFFSET, j * UNIT_SIZE * MULTIPLIER-OFFSET, UNIT_SIZE*MULTIPLIER, UNIT_SIZE*MULTIPLIER);
                }
            }
            idx+=6;
        }
    };
    JPanel titlePanel = new JPanel();

    JLabel titleLabel = new JLabel("Connect 4");

    Font fileNameFont = new Font("DialogInput", Font.BOLD, 30);
    Font titleFont = new Font("DialogInput", Font.BOLD, 75);

    JButton backMenuButton = new JButton("<--");
    JButton hardAIButton = new JButton("Hard AI");
    JButton easyAIButton = new JButton("Easy AI");
    JButton mediumAIButton = new JButton("Medium AI");
    JButton pvpButton = new JButton("PvP");

    static final Color lightBlue = new Color(177, 225, 242);

    NewGamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(lightBlue);
        setFocusable(true);
        setLayout(null);
        addKeyListener( new MyKeyAdapter());
        addMouseListener(this);
        backMenuButton.addKeyListener( new MyKeyAdapter());

        backMenuButton.setBorder(BorderFactory.createLineBorder(Color.black));
        backMenuButton.setFont(new Font("DialogInput", Font.BOLD, 15));
        backMenuButton.setBounds(UNIT_SIZE/4, UNIT_SIZE/2, UNIT_SIZE+20, UNIT_SIZE);
        add(backMenuButton);

        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(UNIT_SIZE*2, UNIT_SIZE, UNIT_SIZE*11, UNIT_SIZE*2+10);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        add(titlePanel);

        easyAIButton.addKeyListener( new MyKeyAdapter());
        easyAIButton.setBorder(BorderFactory.createLineBorder(Color.black));
        easyAIButton.setFont(fileNameFont);
        easyAIButton.setBounds(UNIT_SIZE * 3, UNIT_SIZE*4, UNIT_SIZE*4, UNIT_SIZE*2);
        add(easyAIButton);

        mediumAIButton.addKeyListener( new MyKeyAdapter());
        mediumAIButton.setBorder(BorderFactory.createLineBorder(Color.black));
        mediumAIButton.setFont(fileNameFont);
        mediumAIButton.setBounds(UNIT_SIZE * 8, UNIT_SIZE*4, UNIT_SIZE*4, UNIT_SIZE*2);
        add(mediumAIButton);

        hardAIButton.addKeyListener( new MyKeyAdapter());
        hardAIButton.setBorder(BorderFactory.createLineBorder(Color.black));
        hardAIButton.setFont(fileNameFont);
        hardAIButton.setBounds(UNIT_SIZE * 3, UNIT_SIZE*7, UNIT_SIZE*4, UNIT_SIZE*2);
        add(hardAIButton);

        pvpButton.addKeyListener( new MyKeyAdapter());
        pvpButton.setBorder(BorderFactory.createLineBorder(Color.black));
        pvpButton.setFont(fileNameFont);
        pvpButton.setBounds(UNIT_SIZE * 8, UNIT_SIZE*7, UNIT_SIZE*4, UNIT_SIZE*2);
        add(pvpButton);

        backGround1.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        backGround2.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        add(backGround1);

    }
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){//fix
            if (clicks%2 == 0) {
                remove(backGround1);
                add(backGround2);
            }
            else {
                add(backGround1);
                remove(backGround2);
            }
            clicks++;
            repaint();
            revalidate();
        }
    }
    public void mouseClicked(MouseEvent e) {
        if (clicks%2 == 0) {
            remove(backGround1);
            add(backGround2);
        }
        else {
            add(backGround1);
            remove(backGround2);
        }
        clicks++;
        repaint();
        revalidate();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}

