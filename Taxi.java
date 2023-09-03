import greenfoot.*;
import java.util.*;

public class Taxi extends Actor {
    private String licensePlate;

    // Positions for the animation
    private Position targetPosition = new Position();
    private int stepSize = 7;
    private int moveDelay = 0;
    private Position nextTargetPosition = new Position(); // for delay

    public Taxi() {
        // Set license plate
        this.licensePlate = generateLicensePlate();

        // Generate random appearance
        int randomNumber = Greenfoot.getRandomNumber(10);
        String fileName = "Taxi-" + randomNumber + ".png";
        setImage(fileName);
    }

    // Generates a license plate for the taxi
    private String generateLicensePlate() {
        Random rand = new Random();
        String[] cityCodes = {"NE", "D", "M", "K", "HH", "S", "F", "B"};
        StringBuilder licensePlate = new StringBuilder();

        // City code
        licensePlate.append(cityCodes[rand.nextInt(cityCodes.length)]);
        licensePlate.append("-");

        // One or two letters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int letterCount = 1 + rand.nextInt(2);
        for (int j = 0; j < letterCount; j++) {
            licensePlate.append(alphabet.charAt(rand.nextInt(alphabet.length())));
        }
        licensePlate.append("-");

        // A number combination from 1 to 9999
        int randomNumber = 1 + rand.nextInt(9999);
        licensePlate.append(randomNumber);
        return licensePlate.toString(); 
    }

    // Getter for the license plate
    public String getLicensePlate() {
        return licensePlate;
    }

    // Setter for the position
    public void setPosition(int x, int y) {
        this.targetPosition.setPosition(x, y);
        this.setLocation(x, y);
    }

    // Method to move the taxi to the target position
    public void moveToPosition(int x, int y) {
        this.targetPosition.setPosition(x, y);
    }

    // Method to move the taxi to the target position with a delay
    public void moveToPosition(int x, int y, int delay) {
        this.moveDelay = delay;
        this.nextTargetPosition.setPosition(x, y);
    }

    // Main action method for the game loop
    public void act() {
        // Check for a delayed move
        if (moveDelay == 1) {
            this.targetPosition.setPosition(nextTargetPosition.getX(), nextTargetPosition.getY());
        }
        if (moveDelay > 0) {
            moveDelay -= 1;
        }

        // Check if the current position differs from the target position
        if (getX() != targetPosition.getX() || getY() != targetPosition.getY()) {
            // Calculate the difference between the current position and the target position
            int deltaX = targetPosition.getX() - getX();
            int deltaY = targetPosition.getY() - getY();

            // Calculate the direction factors for the steps
            int stepX = (deltaX > 0) ? 1 : (deltaX < 0) ? -1 : 0;
            int stepY = (deltaY > 0) ? 1 : (deltaY < 0) ? -1 : 0;

            // Check if a step in the X direction is possible
            if (deltaX != 0) {
                int newX = getX() + stepSize * stepX;
                // Check if the next step goes beyond the target
                if ((stepX > 0 && newX > targetPosition.getX()) || (stepX < 0 && newX < targetPosition.getX())) {
                    newX = targetPosition.getX(); // Set to the target X position
                }
                setLocation(newX, getY());
            }

            // Check if a step in the Y direction is possible
            if (deltaY != 0) {
                int newY = getY() + stepSize * stepY;
                // Check if the next step goes beyond the target
                if ((stepY > 0 && newY > targetPosition.getY()) || (stepY < 0 && newY < targetPosition.getY())) {
                    newY = targetPosition.getY(); // Set to the target Y position
                }
                setLocation(getX(), newY);
            }
        }
    }
}
