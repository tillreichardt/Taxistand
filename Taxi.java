import greenfoot.*;
import java.util.*;

public class Taxi extends Actor {
    private String kennzeichen;

    // Positionen für die Animation
    private Position targetPosition = new Position();
    private boolean move = false;

    public Taxi() {
        // Setze Kennzeichen
        this.kennzeichen = generiereKennzeichen();

        // Generiere zufälliges Aussehen
        int randomNumber = Greenfoot.getRandomNumber(10);
        String fileName = "Taxi-" + randomNumber + ".png";
        setImage(fileName);

        // Setze default Position in Welt
        this.targetPosition.setPosition(0,0);
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
        this.move = true;
        this.targetPosition.setPosition(x, y);
    }

    public void act(){

        // Prüfe ob unsere aktuelle Position von der Zielposition abweicht
        if(this.move == true && (getX() != this.targetPosition.getX() || getY() != this.targetPosition.getY())) {
            System.out.println("Wir müssen unsere Position updaten!");
            // Berechne die Schrittgröße für die Bewegung (hier wird angenommen, dass 1 Pixel pro Aktualisierungsschritt bewegt werden soll)
            int deltaX = this.targetPosition.getX() - getX();
            int deltaY = this.targetPosition.getY() - getY();
            
            if (deltaX == 0 && deltaY == 0) {
                this.move = false;
            }

            // Prüfe, ob die Zielposition in der X-Richtung erreicht werden kann
            if (deltaX != 0) {
                int stepX = (deltaX > 0) ? 1 : -1; // Bestimme die Richtung in X
                int newX = getX() + stepX; // Berechne die neue X-Position um einen Pixel
                this.setLocation(newX, getY()); // Setze die neue X-Position
            }

            // Prüfe, ob die Zielposition in der Y-Richtung erreicht werden kann
            if (deltaY != 0) {
                int stepY = (deltaY > 0) ? 1 : -1; // Bestimme die Richtung in Y
                int newY = getY() + stepY; // Berechne die neue Y-Position um einen Pixel
                this.setLocation(getX(), newY); // Setze die neue Y-Position
            }
        }            
        // Hier kommt die Logik, dass wir nur 1pixel pro "Act-Aufruf uns bewegen dürfen
        // this.setLocation(this.targetPosition.getX(),this.targetPosition.getY());
    }
}
