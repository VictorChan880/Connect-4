package testing;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Scanner;

public class GameFrame extends JFrame implements ActionListener {
    MenuPanel menuPanel = new MenuPanel();
    GamePanel gamePanel = new GamePanel();
    LoadGamePanel loadGamePanel = new LoadGamePanel();
    NewGamePanel newGamePanel = new NewGamePanel();
    SaveGamePanel saveGamePanel = new SaveGamePanel();
    TurnPanel turnPanel = new TurnPanel();
    GameFrame() throws Exception{


        menuPanel.newGameButton.addActionListener(this);
        menuPanel.loadGameButton.addActionListener(this);

        gamePanel.returnMenuButton.addActionListener(this);
        gamePanel.saveGameButton.addActionListener(this);
        gamePanel.clearBoardButton.addActionListener(this);

        newGamePanel.backMenuButton.addActionListener(this);
        newGamePanel.hardAIButton.addActionListener(this);
        newGamePanel.easyAIButton.addActionListener(this);
        newGamePanel.mediumAIButton.addActionListener(this);
        newGamePanel.pvpButton.addActionListener(this);

        loadGamePanel.backMenuButton.addActionListener(this);
        loadGamePanel.fileField.addKeyListener(new MyKeyAdapter());

        turnPanel.backMenuButton.addActionListener(this);
        turnPanel.AIFirstButton.addActionListener(this);
        turnPanel.playerFirstButton.addActionListener(this);

        saveGamePanel.returnGameButton.addActionListener(this);
        saveGamePanel.enterButton.addActionListener(this);

        this.add(menuPanel);
        this.setTitle("Connect 4");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
    public void actionPerformed (ActionEvent event){
        String command = event.getActionCommand();
        if (command.equals("New Game")) {
            System.out.println("creating new game");
            this.remove(menuPanel);
            this.add(newGamePanel);
        }
        else if (command.equals("<--")) {
            if (event.getSource().equals(newGamePanel.backMenuButton)) {
                this.remove(newGamePanel);
                this.add(menuPanel);

            }
            else if (event.getSource().equals(loadGamePanel.backMenuButton)) {
                this.remove(loadGamePanel);
                loadGamePanel.errorPanel.setVisible(false);
                this.add(menuPanel);
            }
            else {
                this.remove(turnPanel);
                this.add(newGamePanel);
            }
        }
        else if (command.equals("Load Game")) {
            this.remove(menuPanel);
            this.add(loadGamePanel);
        }
        else if (command.equals("Hard AI")) {
            gamePanel.be.game.gameMode =3;
            this.remove(newGamePanel);
            this.add(turnPanel);
        }
        else if (command.equals("Medium AI")) {
            gamePanel.be.game.gameMode =2;
            this.remove(newGamePanel);
            this.add(turnPanel);
        }
        else if (command.equals("Easy AI")) {
            gamePanel.be.game.gameMode =1;
            this.remove(newGamePanel);
            this.add(turnPanel);
        }
        else if (command.equals("PvP")) {
            gamePanel.be.game.gameMode = 0;
            gamePanel.be.game.playerOrder =0;
            gamePanel.player=1;
            this.remove(newGamePanel);
            this.add(gamePanel);
        }
        else if (command.equals("AI First")) {
            gamePanel.player =2;
            gamePanel.be.game.playerOrder =2;
            this.remove(turnPanel);
            gamePanel.repaint();
            gamePanel.revalidate();
            this.add(gamePanel);
        }
        else if (command.equals("Player First")) {
            gamePanel.player =1;
            gamePanel.be.game.playerOrder =1;
            this.remove(turnPanel);
            gamePanel.repaint();
            gamePanel.revalidate();
            this.add(gamePanel);
        }

        else if (command.equals("Return to Menu")){
            System.out.println("returning to menu");
            this.remove(gamePanel);
            this.add(menuPanel);
            try {
                gamePanel.newGame();
            }
            catch(Exception ex)
            {
            }
        } else if (command.equals("Save Game")){
            this.remove(gamePanel);
            this.add(saveGamePanel);

        } else if(command.equals("Enter")){
            try{
                gamePanel.be.game.saveGame(saveGamePanel.textField.getText());
            }catch(Exception ex){

            }
            this.remove(saveGamePanel);
            this.add(gamePanel);
        } else if(command.equals("Return to Game")){
            this.remove(saveGamePanel);
            this.add(gamePanel);
        }

        else if(command.equals("Clear Board")){
            System.out.println("Clearing board");
            try {
                gamePanel.clearBoard();
            }
            catch(Exception ex)
            {
            }
            this.remove(gamePanel);
            this.add(gamePanel);
        }
        repaint();
        revalidate();
    }
    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e){//fix
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String fileName = loadGamePanel.fileField.getText();

                try {
                    Scanner sc = new Scanner(new FileReader(fileName));
                    String name = sc.next();
                    int gameMode = Integer.parseInt(sc.next());
                    int playerOrder = Integer.parseInt(sc.next());
                    String hash = sc.next();
                    Board b = new Board(hash);
                    gamePanel.be.game.playerOrder = playerOrder;
                    gamePanel.player = playerOrder;
                    gamePanel.be.game.gameMode = gameMode;
                    gamePanel.be.game.board = b;
                    gamePanel.loadPrevGame();
                    if (gamePanel.be.game.board.checkWin() || gamePanel.be.game.board.getMoves() == 42) gamePanel.gameState = 2;
                    remove(loadGamePanel);
                    add(gamePanel);

                } catch (Exception exception) {
                    loadGamePanel.errorPanel.setVisible(true);
                }
                if (loadGamePanel.clicks%2 == 0) {
                    loadGamePanel.remove(loadGamePanel.backGround1);
                    loadGamePanel.add(loadGamePanel.backGround2);
                }
                else {
                    loadGamePanel.add(loadGamePanel.backGround1);
                    remove(loadGamePanel.backGround2);
                }
                loadGamePanel.clicks++;
                repaint();
                revalidate();
            }
        }
    }
}
