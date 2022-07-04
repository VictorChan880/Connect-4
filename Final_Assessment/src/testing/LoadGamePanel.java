package testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class LoadGamePanel extends JPanel implements MouseListener{

    /*
    --------------------
    | Back Button      |
    |       Title      |
    |     TextField    |
    |    Error Label   |
    --------------------
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
    int clicks = 0;
    static final Color COLOURS[] = {Color.red, Color.ORANGE, Color.yellow, Color.green, Color.cyan, Color.blue,  new Color(75,0,130), };
    static final Random rand = new Random();
    static int idx = 0;

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
    JButton backMenuButton = new JButton("<--");

    //rainbow background2
    JPanel backGround2 = new JPanel() {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(lightBlue);
            g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            //draw emptyCircles
            int  index = idx;
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

    //all panels
    JPanel titlePanel = new JPanel();
    JPanel fileInputPanel = new JPanel();
    JPanel errorPanel = new JPanel();

    //all labels
    JLabel titleLabel = new JLabel("Connect 4");
    JLabel errorLabel = new JLabel("File not found. Please try again.");
    JLabel instructionLabel = new JLabel("Enter File Name: ");

    //all fields
    JTextField fileField = new JTextField(15);

    //all fonts
    Font fileNameFont = new Font("DialogInput", Font.BOLD, 30);
    Font titleFont = new Font("DialogInput", Font.BOLD, 75);

    //all colours
    static final Color lightBlue = new Color(177, 225, 242);

    LoadGamePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(lightBlue);
        setFocusable(true);
        setLayout(null);
        addMouseListener(this);

        //back to menu button set up
        backMenuButton.setBorder(BorderFactory.createLineBorder(Color.black));
        backMenuButton.setFont(new Font("DialogInput", Font.BOLD, 15));
        backMenuButton.setBounds(UNIT_SIZE/4, UNIT_SIZE/2, UNIT_SIZE+20, UNIT_SIZE);
        add(backMenuButton);

        //title panel set up
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(UNIT_SIZE*2, UNIT_SIZE, UNIT_SIZE*11, UNIT_SIZE*2+10);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        add(titlePanel);

        //instructions label label set up
        instructionLabel.setFont(fileNameFont);
        fileInputPanel.add(instructionLabel);

        //file field set up
        fileField.setFont(fileNameFont);
        fileInputPanel.add(fileField);

        //file input panel set up
        fileInputPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        fileInputPanel.setBackground(Color.white);
        fileInputPanel.setBounds(UNIT_SIZE, UNIT_SIZE*4, UNIT_SIZE*13, UNIT_SIZE+6);
        add(fileInputPanel);

        //error label
        errorLabel.setFont(fileNameFont);
        errorLabel.setForeground(Color.red);
        errorPanel.add(errorLabel);

        errorPanel.setBackground(Color.white);
        errorPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        errorPanel.setBounds(UNIT_SIZE, UNIT_SIZE*6, UNIT_SIZE*13, UNIT_SIZE);
        add(errorPanel);
        errorPanel.setVisible(false);

        backGround1.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        backGround2.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        add(backGround1);


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

