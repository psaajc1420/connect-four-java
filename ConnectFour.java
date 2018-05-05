import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ConnectFour extends Game{

  final int numberOfPlayers = 2;
  final int WIDTH = 7;
  final int HEIGHT = 6;
  final int EMPTY_SLOT = '\0';
  private boolean isPlaying;
  private Player[] players;
  private char[][] board;
  private boolean justLoggedIn;

  public ConnectFour(){
    players = new Player[2];
    Scanner input = new Scanner(System.in);
    for(int i = 0; i < numberOfPlayers; i++){
      System.out.printf("Enter player %d's name: ", i+1);
      players[i] = new Player(input.nextLine(),
                              i == 0 ? 'X' : 'O');
    }

    board = new char[6][7];
    for(int j = 0; j < HEIGHT; j++)
      for(int k = 0; k < WIDTH; k++)
        board[j][k] = EMPTY_SLOT;

    justLoggedIn = true;
  }

  @Override
  public void play(){

    Player player = null;
    isPlaying = true;

    while(isPlaying){
      for(int i = 0; i < numberOfPlayers; i++){
        player = players[i];
        isPlaying = takeTurn(player);
        if(checkWinner()){
          System.out.printf("%s is the winner!!!\n", player.getName());
          displayBoard(player);
          isPlaying = false;
          break;
        }
      }
    }

  }

  @Override
  public void menu() {
    Scanner input = new Scanner(System.in);

    while(true){

      System.out.printf("\nHi, %s\n", welcomeMessage());
      System.out.println("1. Play Game");
      System.out.println("2. List player info");
      System.out.println("3. Rules for ConnectFour");
      System.out.println("4. Quit Program");
      System.out.println("");
      System.out.print("Enter choice: ");

      int choice = input.nextInt();
      System.out.println("");

      if(choice == 1){
        play();
      } else if(choice == 2){
        listPlayerInfo();
      } else if(choice == 3){
        getInstructions();
      } else if(choice == 4){
        System.out.println("Thanks for playing ConnectFour!");
        System.exit(0);
      } else{
        System.out.println("Not a choice try again!");
      }
    }
  }

  public String welcomeMessage(){
    if(!justLoggedIn){
      return "Welcome Back to ConnectFour!";
    }
    justLoggedIn = false;
    return "Welcome to ConnectFour!";
  }

  public void listPlayerInfo(){
    Player p = null;
    System.out.printf("%-20s%-7s\n", "Name", "Piece" );
    for(int i = 0; i < numberOfPlayers; i++){
      p = players[i];
      System.out.printf("%-20s%-7s\n", p.getName(), p.getPiece() );
    }
  }

  @Override
  public void getInstructions(){
    try {
      Scanner input = new Scanner(new File("instructions.txt"));
      while(input.hasNext()){
        System.out.println(input.nextLine());
      }
    }
    catch(FileNotFoundException e){
      System.out.println("File not Found");
    }
  }

  @Override
  public boolean takeTurn(Player player){
    Scanner input = new Scanner(System.in);
    displayBoard(player);
    System.out.printf("%s - enter column to drop piece: ", player.getName());
    int columnNumber = input.nextInt();
    dropPiece(player, columnNumber-1);
    return true;
  }

  @Override
  public boolean checkWinner(){


    for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
        for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
            char player = board[r][c];
            if (player == EMPTY_SLOT)
                continue; // don't check empty slots

            if (c + 3 < WIDTH &&
                player == board[r][c+1] && // look right
                player == board[r][c+2] &&
                player == board[r][c+3])
                return true;

            if (r + 3 < HEIGHT) {
                if (player == board[r+1][c] && // look up
                    player == board[r+2][c] &&
                    player == board[r+3][c])
                    return true;
                if (c + 3 < WIDTH &&
                    player == board[r+1][c+1] && // look up & right
                    player == board[r+2][c+2] &&
                    player == board[r+3][c+3])
                    return true;
                if (c - 3 >= 0 &&
                    player == board[r+1][c-1] && // look up & left
                    player == board[r+2][c-2] &&
                    player == board[r+3][c-3])
                    return true;
            }
        }
    }
    return false; // no winner found
  }

  public void dropPiece(Player p ,int columnNumber){
    for(int i = HEIGHT-1; i >= 0; i--){
      char piece = board[i][columnNumber];
      if(piece == EMPTY_SLOT){
        board[i][columnNumber] = p.getPiece();
        break;
      }
    }
  }

  public void displayBoard(Player player){
    StringFormat format = new StringFormat(4);

    System.out.println();
    for(int i = 0; i < WIDTH; i++)
      System.out.printf("%s", format.center(String.valueOf(i+1)));
    System.out.println();

    format.setSpace(3);

    for(int i = 0; i < HEIGHT; i++){
      for(int j = 0; j < WIDTH; j++){
        char slot = board[i][j];
        String s = format.center(String.valueOf( slot != EMPTY_SLOT ? slot : ' '));
        System.out.print(String.format("|%s", s));
      }
      System.out.println("|");
    }
    System.out.println("");
  }

}
