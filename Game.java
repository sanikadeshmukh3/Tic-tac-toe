package dev.lpa;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Board board;
    private int playerNum;
    private int computerNum;

    public Game(int playerNum, int computerNum, Board board){
        this.playerNum = playerNum - 1;
        this.computerNum = computerNum - 1;
        this.board = board;

    }

    public boolean playingGame(){
        // both playerNum and computerNum are already initialized in the constructor
        boolean gameOver = false;
        boolean playerTurn = false;
        int square = 0;

        if(playerNum == 0){ // player starts first
            playerTurn = true;
        }

        while(!gameOver){
             // while the game has NOT ended

            if(playerTurn) {

                square = playerMove(playerNum); // gets the spot that the user chooses
                while(duplicateMove(square - 1)) {
                    System.out.println("That spot's already taken. Please try again.");
                    square = playerMove(playerNum);
                }
                updateBoard(playerNum, square);
                updateUsedSpots(square - 1);
                if(checkForWin() || allSquaresUsed()){
                    gameOver = true;
                }
                board.printBoard(); // prints the board to show what has changed
                System.out.println();
                playerTurn = !playerTurn; // switches players

            }
            else{

                square = computerMove(); // random num from 1-9
                while(duplicateMove(square - 1)){
                    square = computerMove();
                }
                System.out.println("The computer moves.");
                updateBoard(computerNum, square);
                updateUsedSpots(square - 1);
                if(allSquaresUsed() || checkForWin()){
                    gameOver = true;
                }
                board.printBoard();
                System.out.println("-".repeat(30));
                System.out.println();
                playerTurn = !playerTurn;

            }

        }

        return playerTurn;


    }

    public int playerMove(int playerNum){
        Scanner scanner = new Scanner(System.in);
        int spot = 0;
        System.out.println("It's your turn!");
        board.printBoard();
        System.out.println();
        board.printNumberedBoard();
        System.out.println("Which spot would you like to put your " + board.getMoves()[playerNum]);
        spot = scanner.nextInt();
        return spot; // this is the spot that the user chooses

    }

    public int computerMove(){
        Random rand = new Random();
        return rand.nextInt(1, 10); // the computer can pick a num between 1-9
    }

    public boolean checkForWin(){

        boolean over = false;
        // checking for each win (rows, columns, and diagonal)
        // first horizontal
        for(int i = 0; i < board.getROWS(); i += 2){ // skipping by two because of the spaces in between
            if ((!over)  && (board.getBoard()[i][0].equals(board.getBoard()[i][2])) && (board.getBoard()[i][2].equals(
                    board.getBoard()[i][4]))){
                if((!Objects.equals(board.getBoard()[i][0], "  ")) && (!board.getBoard()[i][2].equals("  "))
                        && (!board.getBoard()[i][4].equals("   "))){
                    over = true;
                }

            }
        }
        // then vertical
        for(int i = 0; i < board.getCOLUMNS(); i += 2){

            if((!over) && (board.getBoard()[0][i].equals(board.getBoard()[2][i])) &&
                    (board.getBoard()[2][i].equals(board.getBoard()[4][i]))){
                if((!Objects.equals(board.getBoard()[0][i], "  ")) && (!board.getBoard()[2][i].equals("  "))
                        && (!board.getBoard()[4][i].equals("   "))){
                    over = true;
                }
            }


        }
        // the diagonal (a bit more difficult)
        // no need for a loop (it can be from left to right, or from right to left)
       if((!over) && ((board.getBoard()[0][0].equals(board.getBoard()[2][2])) &&
                (board.getBoard()[2][2].equals(board.getBoard()[4][4]))) ||
                ((board.getBoard()[4][0].equals(board.getBoard()[2][2]))
                && (board.getBoard()[2][2].equals(board.getBoard()[0][4])))){
           if((!Objects.equals(board.getBoard()[0][0], "  ")) && (!board.getBoard()[2][2].equals("  "))
                   && (!board.getBoard()[4][4].equals("   "))){
               over = true;
           }

        }

        return over;


    }
    public boolean allSquaresUsed(){

        int usedSpots = 0;

        for(boolean spot : board.getUsedSpots()){

            if(spot){
                usedSpots++;
            }

        }

        if(usedSpots == 9){
            return true;
        }

        return false;
    }

    public void updateUsedSpots(int playerNum){

        int spot = playerNum; // local variable
        board.getUsedSpots()[spot] = true; // shows that the spot is taken (helps in identifying used spots)
    }


    public void updateBoard(int xOrO, int num){

        String stringOnBoard = " " + num + " ";
        int square = num;
        for(int i = 0; i < board.getROWS(); i++){

            for(int j = 0; j < board.getCOLUMNS(); j++){

                if(board.getNumberBoard()[i][j].equals(stringOnBoard)){

                    board.getBoard()[i][j] = " " + board.getMoves()[xOrO] + " "; // replaces the board with the X or O
                    break;

                }

            }

        }

        /*if(allSquaresUsed()){
            return true;
        }

        return checkForWin(); // change this later, returns true if there is a win or if all squares are filled*/

    }

    public boolean duplicateMove(int num){

        int square = num; // local variable
        if(board.getUsedSpots()[square]){
            return true; // this means that there is a duplicate (the spot's already been picked)
        }

        return false; // else, the user can pick this spot
        
    }

    public void resetBoard(){

        for(int i = 0; i < board.getROWS(); i += 2){
            for(int j = 0; j < board.getCOLUMNS(); j += 2){
                board.getBoard()[i][j] = "   ";
            }
        }

    }

    public void resetUsedSpots(){

        for (int i = 0; i < board.getMAX_MOVES(); i++){
            board.getUsedSpots()[i] = false;
        }

    }

}
