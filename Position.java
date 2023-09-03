// Simple 2D point
public class Position {
    private int x;
    private int y;

    // Default constructor
    public Position() {
        this.x = 0;
        this.y = 0;
    }

    // Parameterized constructor
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters for X and Y coordinates
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    // Setters for X and Y coordinates
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Method to set both X and Y coordinates
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}