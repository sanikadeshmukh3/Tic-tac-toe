package dev.lpa;

import java.util.Arrays;

public class Board {

    // constants (their values do not change throughout the entirety of the program)
    final int ROWS = 5;
    final int COLUMNS = 5;
    final int MAX_MOVES = 9;
    final int X_OR_O = 2;

    private String[][] board = {{"   ", "|", "   ", "|", "   "},
            {"---", "-", "---", "-", "---"},
            {"   ", "|", "   ", "|", "   "},
            {"---", "-", "---", "-", "---"},
            {"   ", "|", "   ", "|", "   "}}; // already set up
    private String[][] numberBoard = {{" 1 ", "|", " 2 ", "|", " 3 "},
            {"---", "-", "---", "-", "---"},
            {" 4 ", "|", " 5 ", "|", " 6 "},
            {"---", "-", "---", "-", "---"},
            {" 7 ", "|", " 8 ", "|", " 9 "}};
    private boolean[] usedSpots = {false, false, false,
                                    false, false, false,
                                    false, false, false};

    private String[] moves = {"X", "O"};

    public Board(){
        // board is already initialized

    }

    public int getROWS() {
        return ROWS;
    }

    public int getMAX_MOVES() {
        return MAX_MOVES;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }



    public String[][] getBoard() {
        return board;
    }

    public boolean[] getUsedSpots() { return usedSpots; }

    public String[][] getNumberBoard(){return numberBoard;}

    public String[] getMoves(){
        return moves;
    }

    public void printUsedSpots(){
        System.out.println(Arrays.toString(usedSpots));
    }
    public void printNumberedBoard(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                System.out.print(numberBoard[i][j]);
            }
            System.out.println();
        }
    }


    public void printBoard(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
