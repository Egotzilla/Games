package gdd;

public class SpawnDetails {
    private int x;
    private int y;
    private String type;

    public SpawnDetails(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public String getType() {
        return type;
    }
}