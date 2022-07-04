package testing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JPanel;

public class SaveGamePanel extends JPanel implements MouseListener{
    static final int SCREEN_WIDTH = 1500/2;
    static final int SCREEN_HEIGHT= 1000/2;
    static final int BOARD_WIDTH = 700/2;
    static final int BOARD_HEIGHT = 600/2;
    static final int UNIT_SIZE = 100/2;
    static final int BOARD_X = 600/2;
    static final int BOARD_Y = 300/2;
    static final int GAME_UNITS = (BOARD_WIDTH*BOARD_HEIGHT)/UNIT_SIZE;

    JLabel titleLabel = new JLabel("Connect 4");
    JLabel instrLabel = new JLabel("Please enter the name of your save file below:");
    JTextField textField = new JTextField();
    JButton enterButton = new JButton("Enter");
    JButton returnGameButton = new JButton("Return to Game");

    Font titleFont = new Font("Monospaced", Font.BOLD, 75);
    Font instrFont = new Font("DialogInput", Font.BOLD, 20);
    Font buttonFont = new Font("Monospaced", Font.BOLD, 15);
    static int idx = 0;
    static int MULTIPLIER = 2;
    static final int OFFSET = 35;
    int clicks = 0;
    static final Color COLOURS[] = {Color.red, Color.ORANGE, Color.yellow, Color.green, Color.cyan, Color.blue,  new Color(75,0,130), };
    static final Color lightBlue = new Color(177, 225, 242);

    JPanel titlePanel = new JPanel();
    JPanel instructionPanel = new JPanel();
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
    JButton backMenuButton = new JButton("<--");
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

    SaveGamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.setLayout(null);
        this.addKeyListener(new MyKeyAdapter());
        returnGameButton.setBounds(10, 10, UNIT_SIZE*4, UNIT_SIZE/2);
        returnGameButton.setFont(buttonFont);
        this.add(returnGameButton);

        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(UNIT_SIZE*2, UNIT_SIZE, UNIT_SIZE*11, UNIT_SIZE*2+10);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        add(titlePanel);

        textField.setFont(instrFont);
        textField.setBounds(UNIT_SIZE*4 + UNIT_SIZE/2, UNIT_SIZE*5,UNIT_SIZE*4, UNIT_SIZE);
        this.add(textField);

        enterButton.setBounds(UNIT_SIZE*8 + UNIT_SIZE/2, UNIT_SIZE*5, UNIT_SIZE*2, UNIT_SIZE);
        enterButton.setFont(buttonFont);
        this.add(enterButton);

        instrLabel.setFont(instrFont);
        instructionPanel.add(instrLabel);

        instructionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        instructionPanel.setBounds(UNIT_SIZE+UNIT_SIZE/2, UNIT_SIZE*4, UNIT_SIZE*12, UNIT_SIZE*2 + UNIT_SIZE/2);
        this.add(instructionPanel);



        //backsground 1 and 2 set up
        backGround1.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        backGround2.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        add(backGround1);


    }
    public class MyKeyAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e){
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

    //on click, change the background.
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
