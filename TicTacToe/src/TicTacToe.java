import jdk.dynalink.linker.LinkerServices;

import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPosition = new ArrayList<>();
    static ArrayList<Integer> computerPosition = new ArrayList<>();

    public static void main(String args[]){
        char[][] gameBoard = {{' ','|',' ','|',' '},
                              {'-','+','-','+','-'},
                              {' ','|',' ','|',' '},
                              {'-','+','-','+','-'},
                              {' ','|',' ','|',' '}};
        displayGameBoard(gameBoard);

        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the position (1-9) : ");
            int playerPos= scan.nextInt();

            while(playerPosition.contains(playerPos) || computerPosition.contains(playerPos)){
                System.out.println("The position is Taken. Use the valid position: ");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "Player");

            String res = checkWinner();
            if(res.length() > 0 ){
                System.out.println(res);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(computerPosition.contains(cpuPos) || playerPosition.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "Computer");
            displayGameBoard(gameBoard);
            res = checkWinner();
            if(res.length() > 0){
                System.out.println(res);
                break;

            }
        }
    }

    public static void displayGameBoard(char[][] gameBoard){
        for(char row[] : gameBoard){
            for(char c: row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char gameBoard[][], int pos, String user){
        char symbol = 'X';
        if(user.equals("Player")) {
            symbol = 'X';
            playerPosition.add(pos);
        }else if(user.equals("Computer")){
            symbol = 'O';
            computerPosition.add(pos);
        }

        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;

        }
    }

    public static String checkWinner(){
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List firstCol = Arrays.asList(1, 4, 7);
        List secondCol = Arrays.asList(2, 5, 8);
        List thirdCol = Arrays.asList(3, 6, 9);
        List rightDim = Arrays.asList(1, 5, 9);
        List leftDim = Arrays.asList(7, 5, 3);

        List<List> winning  = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(firstCol);
        winning.add(secondCol);
        winning.add(thirdCol);
        winning.add(rightDim);
        winning.add(leftDim);

        for(List l : winning){
            if(playerPosition.containsAll(l))
                return "Congrats! you beat the computer!!";
            else if(computerPosition.containsAll(l))
                return "Oops!! You lost :(";
            else if(playerPosition.size() + computerPosition.size() == 9){
                return "Brup!!";
            }
        }

        return "";
    }
}
