package dev.lpa;

import java.util.Scanner;

public class Main {

    // First tic-tac-toe game originally programmed in C++
    // using OOP instead of just everything in the main class
    // application of objects, abstract classes, arrays, arrayLists, enums (instead of pure constants),
    // COMPOSITION

    // Main class will be used to run the code(one static class asking if they want to be Xs or Os)
    // Board -- will contain the arrays representing the boards, one numbered, one with just the tic-tac-toe setup
    // as well as a 1D array to keep a track of used spots (once a spot is chosen, goes from false to true)
    // other classes will implement this class to keep a track of the spots in the arrays
    // contains an updateBoard function - resets the board every time the game is played
    // Game -- where the majority of the action actually happens
    // contains an enum class containing constants (rows, columns, max_moves, X_or_O
    // user can pick a spot based on the numbers that are avaliable from the numbered board (the numbered
    // board will be printed each time as well as the normal board filled with Xs and Os)
    // separate function to check for duplicates (if the spot is already taken, the user / computer should pick another)
    // separate function is the computer_move as it chooses a random number to go against the user
    // update_board function here just fills in a X or O depending on whose turn it was and where they picked

    public static void main(String[] args) {

        boolean whilePlaying = true;
        /*for(int i = 0; i < board.getROWS(); i++){
            for(int j = 0; j < board.getCOLUMNS(); j++){
                System.out.print(board.getBoard()[i][j]);
            }
            System.out.println();
        } // this loop prints out the board*/
        Board board = new Board();
        //int computerChoice = 1;
        //int playerChoice = 2;
        boolean winner = true;

       while(whilePlaying){
           int computerChoice = 1;
           int playerChoice = 2;
           Scanner scanner = new Scanner(System.in);
            playerChoice = startGame();
            if(playerChoice == 1){
                computerChoice = 2;
            }
            Game game = new Game(playerChoice, computerChoice, board);
            winner = game.playingGame();
           /* if(!winner){
                System.out.println("Congrats! You won!");
            }
            else if (game.allSquaresUsed()) {
                System.out.println("It's a tie!");
            }
            else{
                System.out.println("Sorry, you lost!");
            }*/

            if((game.allSquaresUsed()) && !game.checkForWin()){
                System.out.println("It's a tie!");
            }
            else if (!winner){
                System.out.println("Congrats! You won!");

            }
            else{
                System.out.println("Sorry, you lost!");
            }


            System.out.println("Would you like to play again? Type 'y' or 'n': ");
            String answer = scanner.nextLine();
            if(answer.equals("y")){
                game.resetBoard();
                game.resetUsedSpots();
            }
            else{
                whilePlaying = false;
            }

       }

    }

    private static int startGame(){

        int number = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic-tac-Toe! Would you like to be Xs or Os?");
        switch (scanner.nextLine()){
            case "X" -> number = 1;
            default -> number = 2;
        }
        return number; // this is the playerNUm (the computer num will just be the other option)


    }

}
