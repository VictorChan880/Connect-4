package testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MenuPanel extends JPanel implements MouseListener {

    //constants for game panel
    static int divisor = 4;
    static final int SCREEN_WIDTH = 1500/2;
    static final int SCREEN_HEIGHT= 1000/2;
    static final int BOARD_WIDTH = 700/divisor;
    static final int BOARD_HEIGHT = 600/divisor;
    static final int UNIT_SIZE = 100/2;
    static int MULTIPLIER = 2;
    static final int OFFSET = 35;
    int clicks = 0;
    static final Color COLOURS[] = {Color.red, Color.ORANGE, Color.yellow, Color.green, Color.cyan, Color.blue,  new Color(75,0,130), };
    static int idx = 0;

    //All panels used
    //rainbow background1
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

    //rainbow background2
    JPanel backGround2 = new JPanel() {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(lightBlue);
            g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            //draw emptyCircles
            int tokenCount = 1, index = idx;
            for (int j = 0; j < SCREEN_HEIGHT / UNIT_SIZE; j++) {
                for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
                    index = (index+1)% COLOURS.length;
                    Color colour = COLOURS[index];
                    //Color colour = (tokenCount%2 == 0? Color.red: Color.yellow);
                    g.setColor(colour);
                    g.fillOval(i * UNIT_SIZE * MULTIPLIER-OFFSET, j * UNIT_SIZE * MULTIPLIER-OFFSET, UNIT_SIZE*MULTIPLIER, UNIT_SIZE*MULTIPLIER);
                    g.setColor(Color.BLACK);
                    g.drawOval(i * UNIT_SIZE * MULTIPLIER-OFFSET, j * UNIT_SIZE * MULTIPLIER-OFFSET, UNIT_SIZE*MULTIPLIER, UNIT_SIZE*MULTIPLIER);
                    tokenCount++;
                }
            }
            idx+=6;
        }
    };
    JPanel titlePanel = new JPanel();
    JPanel creditPanel = new JPanel();

    //Buttons
    JButton newGameButton = new JButton("New Game");
    JButton loadGameButton = new JButton("Load Game");

    //Labels
    JLabel titleLabel = new JLabel("Connect 4");
    JLabel creditLabel = new JLabel("By: Maggie Chen, Victor Chan, and James Yu");

    //Fonts
    Font fileNameFont = new Font("DialogInput", Font.BOLD, 30);
    Font titleFont = new Font("DialogInput", Font.BOLD, 75);
    Font creditFont = new Font("DialogInput", Font.BOLD, 20);

    //Colours
    static final Color lightBlue = new Color(177, 225, 242);

    MenuPanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(lightBlue);
        setFocusable(true);
        setLayout(null);
        addKeyListener(new MyKeyAdapter());
        addMouseListener(this);

        //title panels set up
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(UNIT_SIZE*2, UNIT_SIZE, UNIT_SIZE*11, UNIT_SIZE*2+10);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        add(titlePanel);


        //new game button set up
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.black));
        newGameButton.setFont(fileNameFont);
        newGameButton.setBounds(UNIT_SIZE * 2, UNIT_SIZE*4, UNIT_SIZE*5, UNIT_SIZE*2);
        add(newGameButton);

        //load game button set up
        loadGameButton.setBorder(BorderFactory.createLineBorder(Color.black));
        loadGameButton.setFont(fileNameFont);
        loadGameButton.setBounds(UNIT_SIZE * 8, UNIT_SIZE*4, UNIT_SIZE*5, UNIT_SIZE*2);
        add(loadGameButton);

        //credit label set up
        creditLabel.setFont(creditFont);
        creditPanel.add(creditLabel);

        //credit panel set up
        creditPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        creditPanel.setBounds(UNIT_SIZE, UNIT_SIZE*7, UNIT_SIZE*13, UNIT_SIZE-10);
        add(creditPanel);

        //rainbow backgrounds set up
        backGround1.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        backGround2.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        add(backGround1);

    }

    @Override
    //when mouse is click, switch between background1 and background2
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

    public class MyKeyAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e){//fix
            repaint();
            revalidate();
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
}



