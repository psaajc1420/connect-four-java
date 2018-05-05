public class StringFormat {

  private int space;

  public StringFormat(){
    space = 0;
  }

  public StringFormat(String data){
    center(data);
    space = 0;
  }
  public StringFormat(int space){
    setSpace(space);
  }
  public StringFormat(String data, int space){
    center(data);
    setSpace(space);
  }

  public void setSpace(int space){
    this.space = space;
  }

  public int getSpace(){
    return space;
  }

  public String center(String data){
    String out = String.format("%"+space+"s%s%"+space+"s", "",data,"");
    float mid = (out.length()/2);
    float start = mid - (space/2);
    float end = start + space;
    return out.substring((int)start, (int)end);
  }

  public String center(int data){
    return center(String.valueOf(data));
  }

}
