public class Position{
    private int x;
    private int y;
    
    public Position(){
        this.x = 0;
        this.y = 0;
    }
    
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setX(int pX){
        this.x = pX;
    }
    
    public void setY(int pY){
        this.y = pY;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}