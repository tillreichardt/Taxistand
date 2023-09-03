import greenfoot.*;
import java.util.*;

public class Taxi extends Actor {
    private String kennzeichen;

    // Positionen für die Animation
    private Position targetPosition = new Position();
    private int stepSize = 7;
    private int moveDelay = 0;
    private Position nextTargetPosition = new Position(); // for delay

    public Taxi() {
        // Setze Kennzeichen
        this.kennzeichen = generiereKennzeichen();

        // Generiere zufälliges Aussehen
        int randomNumber = Greenfoot.getRandomNumber(10);
        String fileName = "Taxi-" + randomNumber + ".png";
        setImage(fileName);
    }

    private String generiereKennzeichen() {
        Random rand = new Random();
        String[] stadtkuerzel = {"NE", "D", "M", "K", "HH", "S", "F", "B"};
        // Generiere Kennzeichen
        StringBuilder kennzeichen = new StringBuilder();

        // Stadtkürzel
        kennzeichen.append(stadtkuerzel[rand.nextInt(stadtkuerzel.length)]);
        kennzeichen.append("-");

        // Ein oder zwei Buchstaben
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int buchstabenAnzahl = 1 + rand.nextInt(2); // 1 oder 2 Buchstaben
        for (int j = 0; j < buchstabenAnzahl; j++) {
            kennzeichen.append(alphabet.charAt(rand.nextInt(alphabet.length())));
        }
        kennzeichen.append("-");

        // Eine Zahlenkombination von 1 bis 9999
        int zufallsZahl = 1 + rand.nextInt(9999);
        kennzeichen.append(zufallsZahl);
        return kennzeichen.toString(); 
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setPosition(int x, int y) {
        this.targetPosition.setPosition(x, y);
        this.setLocation(x, y);
    }

    public void moveToPosition(int x, int y) {
        this.targetPosition.setPosition(x, y);
    }
    
     public void moveToPosition(int x, int y, int delay) {
         this.moveDelay = delay;
         this.nextTargetPosition.setPosition(x, y);
    }

    public void act(){
        // Prüfe, ob delayed move da ist
        if (moveDelay == 1) {
            this.targetPosition.setPosition(nextTargetPosition.getX(), nextTargetPosition.getY());
        }
        if (moveDelay > 0) {
            moveDelay -= 1;
        }
        
        // Prüfe ob unsere aktuelle Position von der Zielposition abweicht
        if (getX() != targetPosition.getX() || getY() != targetPosition.getY()) {
            // Berechne die Differenz zwischen der aktuellen Position und der Zielposition
            int deltaX = targetPosition.getX() - getX();
            int deltaY = targetPosition.getY() - getY();

            // Berechne die Richtungsfaktoren für die Schritte
            int stepX = (deltaX > 0) ? 1 : (deltaX < 0) ? -1 : 0;
            int stepY = (deltaY > 0) ? 1 : (deltaY < 0) ? -1 : 0;

            // Überprüfe, ob ein Schritt in X-Richtung möglich ist
            if (deltaX != 0) {
                int newX = getX() + stepSize * stepX;
                
                // Überprüfe, ob der nächste Schritt über das Ziel hinausgeht
                if ((stepX > 0 && newX > targetPosition.getX()) || (stepX < 0 && newX < targetPosition.getX())) {
                    newX = targetPosition.getX(); // Setze auf die Ziel-X-Position
                }
                
                setLocation(newX, getY());
            }

            // Überprüfe, ob ein Schritt in Y-Richtung möglich ist
            if (deltaY != 0) {
                int newY = getY() + stepSize * stepY;
                
                // Überprüfe, ob der nächste Schritt über das Ziel hinausgeht
                if ((stepY > 0 && newY > targetPosition.getY()) || (stepY < 0 && newY < targetPosition.getY())) {
                    newY = targetPosition.getY(); // Setze auf die Ziel-Y-Position
                }
                
                setLocation(getX(), newY);
            }
        }        
        // Hier kommt die Logik, dass wir nur 1pixel pro "Act-Aufruf uns bewegen dürfen
        // this.setLocation(this.targetPosition.getX(),this.targetPosition.getY());
    }
}
