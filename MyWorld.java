import greenfoot.*;

public class MyWorld extends World {
    // Object of the TaxiCentral class, manages all taxis
    private TaxiCentral taxiCentral;
    public static int gridSize = 50;

    // Constructor for the world. Sets the background and initializes the TaxiCentral
    public MyWorld(int fieldLength) {
        super(fieldLength * gridSize, 1 * gridSize, 1);
        clearConsole();
        setBackground("images/boden.png");
        taxiCentral = new TaxiCentral(fieldLength); // maximum taxis = fieldLength = length of the field
        addObject(taxiCentral, 0, 0);
        System.out.println("Press 'a' to make a taxi depart");
        System.out.println("Press 'g' to generate taxis");
    }

    // Method to clear the console
    public void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }
    }

    public void act() {
        String key = Greenfoot.getKey();
        // Check if a key has been pressed
        if (key != null) {
            switch (key) {
                case "a":
                    Taxi departingTaxi = taxiCentral.depart();
                    // Show the remaining taxis in the stand
                    taxiCentral.currentTaxisInStand();
                    break;
                case "g":
                    Taxi newTaxi = new Taxi();
                    // Add the new Taxi object to the world at position (0, 0)
                    addObject(newTaxi, 0, 0);
                    // Attempt to enqueue the new Taxi into the taxi stand
                    // The enqueue() method returns a boolean value indicating whether the Taxi was successfully enqueued
                    if (!taxiCentral.enqueue(newTaxi)) {
                        // If the Taxi was not successfully enqueued (i.e., the stand is full),remove the Taxi object from the world
                        removeObject(newTaxi);
                    }
                    // Show the remaining taxis in the stand
                    taxiCentral.currentTaxisInStand();
                    break;
            }
        }
    }
}
