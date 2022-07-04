
package testing;


public class Board {

    //dimensions of the board
    public final int WIDTH = 7, HEIGHT = 6;

    //height array for heigh of each column
    int [] height = new int[WIDTH];

    //hash of the game board: '4455' means there are two checkers on column 4 and 2 checks on column 5
    private String hash = "", reverseHash = "";

    //numbers of moves that has been played on this board
    private int moves;

    //actual board
    private int board[][] = new int[WIDTH][HEIGHT];

    static int dx [] = {0,1,1,-1};
    static int dy [] = {1,1,0,1};
    public int checkerColumn;
    public int checkerRow; 
    public Board(String pos)  {
        //takes hash to generate game board, moves and reverse hash
        hash = pos;
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < hash.length(); i++) {
            int dis = Math.abs(4 - (hash.charAt(i) - '0'));
            if (hash.charAt(i) - '0' <= 4) b.append(4 + dis);
            else b.append(4 - dis);
        }
        reverseHash = b.toString();
        for (int i = 0; i < hash.length(); i++) {
            int column = hash.charAt(i) - '0' -1;
            height[column]++;
            board[column][getY(column)] = 1+moves%2;
            moves++;
        }
    }

    //returns y coordinate of the first empty cell in a column
    public int getY (int column) {
        int y = 0;
        for (; y < HEIGHT; y++) {
            if (board[column][y] == 0 && (y == HEIGHT-1 || board[column][y+1] != 0)) break;
        }
        return y;
    }

    //places a checker at column and updates all variables
    public void play (int column) {
        hash = hash.concat(String.valueOf(column+1));
        int dis = Math.abs(3 - column);
        if (column <= 3) reverseHash = reverseHash.concat(String.valueOf(4 + dis));
        else reverseHash = reverseHash.concat(String.valueOf(4 - dis));
        checkerColumn = column;
        if (column < 0) {
            System.out.println(1);
        }
        checkerRow = height[column];
        height[column]++;
        board[column][getY(column)] = 1 + moves%2;
        moves++;
    }
    //checks if a move is a winning move
    public boolean isWinningMove (int column) {
        int currentPlayer = 1 + moves%2;
        int y = getY(column);
        board[column][y] = currentPlayer;
        if (checkWin()) {
            board[column][y] = 0;
            return true;
        }
        else {
            board[column][y] = 0;
            return false;
        }
    }
    //checks if the board has a winner
    public boolean checkWin () {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (board[x][y] != 0) {
                    for (int i = 0; i < dx.length; i++) {
                        int ny = y + dy[i] * 3, nx = x + dx[i] * 3;
                        if (ny < 0 || nx < 0 || ny >= 6 || nx >= 7) continue;
                        boolean flag = true;
                        if (board[x][y] == 0) break;
                        for (int j = 0; j < dy.length; j++) {
                            if (board[x][y] != (board[x + dx[i] * j][y + dy[i] * j])) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) return true;
                    }
                }
            }
        }
        return false;
    }
    //check if a column is full or not
    public boolean canPlay (int column) {
        return height[column] < 6;
    }

    //returns hash
    public String getHash () {
        return hash;
    }

    //returns reverse hash
    public String getReverseHash() {
        return reverseHash;
    }

    //returns how many moves have past
    public int getMoves () {
        return moves;
    }

    //returns 2D board
    public int [][] getBoard() {
        return board;
    }

    //prints board
    public void printBoard(){
     for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 7; j++) {
          System.out.print(board[j][i]);
        }
        System.out.println(); 
      }
  }
}
