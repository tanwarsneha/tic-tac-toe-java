import java.util.Random;
import java.util.Scanner;
public class TicTacToe {
    public void printBoard(char board[][]){
        // printing the board 
        System.out.println();
        for(int i=0; i< board.length; i++){
            for(int j=0; j< board[i].length; j++){
                System.out.print( " " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println("-----------");
        } 
    }

    public boolean checkForWin(char board[][],char player){
        // Winning Conditions : 
        // Check for each row
        for(int i=0; i<board.length; i++){
            if(board[i][0] == player && board[i][1] == player && board[i][2] == player){
                return true;
            }
        }
        // Check for each column 
        for(int j=0; j<board.length; j++){
            if(board[0][j] == player && board[1][j] == player && board[2][j] == player){
                return true;
            }
        }
        // check for diagonals :
        if(board[0][0] == player && board[1][1] == player && board[2][2] == player){
            return true;
        }
        if(board[0][2] == player && board[1][1] == player && board[2][0] == player){
            return true;
        }
        return false;
    }

    boolean isDraw(char board[][]){
        // if every board is filled(X or O --> draw condition)
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j] != 'X' && board[i][j] != 'O'){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        char board[][] = new char[3][3];
        TicTacToe obj = new TicTacToe();
        
        // Creation and initialisation of board
        char box_number = 'a';
        System.out.println();
        for(int i=0; i< board.length; i++){
            for(int j=0; j< board[i].length; j++){
                board[i][j] = box_number++;
            }
        }
        obj.printBoard(board);
        char user_choice;
        char comp_choice;
        while(true){
            System.out.println("Choose 'X' or 'O' ");
            user_choice = sc.next().charAt(0); 
            if(user_choice == 'X') {
                comp_choice = 'O';
                break;
            }
            else if(user_choice == 'O'){
                comp_choice = 'X'; 
                break;
            } 
            else System.out.println("Wrong input. Choose between X and O.");
        }
        System.out.println("User Choics : " + user_choice);
        System.out.println("Computer's Choice : " + comp_choice);


        System.out.println("Do you want to play the first move? Y/N");
        char firstMove = sc.next().toUpperCase().charAt(0);
        char turn = (firstMove == 'Y' ? 'U' : 'C');
        char col = 'a';
        boolean gameOver = false;
        while(true){
            boolean placed = false;
            if(turn == 'U'){
                System.out.println("Choose your column:");
                col = sc.next().charAt(0);
                for(int i=0; i < board.length; i++){
                    for(int j=0; j<board[i].length; j++){
                        if(board[i][j] == col && board[i][j] != 'X' && board[i][j] != 'O'){
                            board[i][j] = user_choice;
                            placed = true;
                            if(obj.checkForWin(board, user_choice)){
                                System.out.println("You Win 🏆🏅");
                                gameOver = true;
                                break;
                            }else{
                                if(obj.isDraw(board)){
                                    System.out.println("Game is Draw.");
                                    gameOver = true;
                                    break;
                                }
                            }
                            turn = 'C';
                            break; // comes out of for loop
                        }
                    }
                }
                if(placed){
                    obj.printBoard(board);
                    placed = false;
                }
            }
            else{
                // turn = 'C'
                System.out.println("Computer's Turn: ");
                while(!placed){
                    col = (char)('a' + new Random().nextInt(9));
                    int index = col - 'a'; 
                    int row = index/3;
                    int c = index % 3;
                    if(board[row][c] != 'X' && board[row][c] != 'O'){
                        board[row][c] = comp_choice;
                        placed = true;
                        if(obj.checkForWin(board, comp_choice)){
                                System.out.println("Computer Wins");
                                gameOver = true;
                                break;
                        }else{
                            if(obj.isDraw(board)){
                                System.out.println("Game is Draw.");
                                gameOver = true;
                                break;
                            }
                        }
                        turn = 'U';
                        break;
                    }
                }
                if(placed){
                    obj.printBoard(board);
                    placed = false;
                }
            }
            if(gameOver){
                    break;
            }
        }
        sc.close();
    }
}
