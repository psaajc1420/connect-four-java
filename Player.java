public class Player {

  private String name;
  private char piece;

  public Player(){
    name = null;
  }

  public Player(String name){
    setName(name);
  }

  public Player(String name, char piece){
    setName(name);
    setPiece(piece);
  }

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public void setPiece(char piece){
    this.piece = piece;
  }

  public char getPiece(){
    return piece;
  }

}
