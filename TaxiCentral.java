import java.util.*;
import greenfoot.*;

public class TaxiCentral extends Actor {
    private Taxi[] taxiStand;
    private int gridSize = MyWorld.gridSize;

    // Constructor, initializes a new taxi stand with a maximum number of taxis
    public TaxiCentral(int maxTaxis) {
        taxiStand = new Taxi[maxTaxis];
    }

    // Returns the current number of taxis waiting in the stand
    public int getNumberOfWaitingTaxis() {
        int count = 0;
        for (Taxi taxi : taxiStand) {
            if (taxi != null) {
                count++;
            }
        }
        return count;
    }

    // Allows the first taxi in the stand to depart and returns it
    public Taxi depart() {
        Taxi departingTaxi = taxiStand[0];
        if (getNumberOfWaitingTaxis() <= 0) {
            System.out.println("\nNo taxi available for departure.");
            return departingTaxi;
        }
        System.out.println("\nTaxi with the following license plate is departing: " + departingTaxi.getLicensePlate());
        getWorld().removeObject(departingTaxi);
        moveForward();
        return departingTaxi;
    }

    // Shifts all taxis one position forward in the array to make room for new taxis
    private void moveForward() {
        for (int i = 0; i < getNumberOfWaitingTaxis() - 1; i++) {
            taxiStand[i] = taxiStand[i + 1];
            taxiStand[i].moveToPosition(i * gridSize + gridSize / 2, gridSize / 2, 10 * (i + 1));
        }
        taxiStand[getNumberOfWaitingTaxis() - 1] = null;  // Free up the last occupied array position after all taxis have moved forward
    }

    // Enqueues a new taxi into the taxi stand
    public boolean enqueue(Taxi newTaxi) {
        if (getNumberOfWaitingTaxis() >= taxiStand.length) {
            System.out.println("\nNo more space in the taxi stand!");
            return false;
        }
        System.out.println("\nTaxi with the following license plate is enqueued: " + newTaxi.getLicensePlate());
        newTaxi.setPosition(taxiStand.length * gridSize + gridSize / 2, gridSize / 2);
        newTaxi.moveToPosition(getNumberOfWaitingTaxis() * gridSize + gridSize / 2, gridSize / 2);
        taxiStand[getNumberOfWaitingTaxis()] = newTaxi;
        return true;
    }

    // Displays a list of the current taxis in the stand
    public void currentTaxisInStand() {
        System.out.print("Current taxis in the stand: ");
        for (int i = 0; i < getNumberOfWaitingTaxis(); i++) {
            System.out.print(taxiStand[i].getLicensePlate() + "; ");
        }
        System.out.println();
    }

    // Returns a taxi at a specific index in the array
    public Taxi getTaxi(int index) {
        if (index >= 0 && index < getNumberOfWaitingTaxis()) {
            return taxiStand[index];
        } else {
            System.out.println("Index out of valid range");
            return null;
        }
    }
}
