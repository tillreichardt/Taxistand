import greenfoot.*;

public class MyWorld extends World {
    // Objekt der Klasse Taxizentrale, verwaltet alle Taxis
    private Taxizentrale taxizentrale;
    public static int gridSize = 50;
    // Konstruktor der Welt. Setzt den Hintergrund und initialisiert die Taxizentrale
    public MyWorld(int pLength){
        
        super(pLength*gridSize,1*gridSize, 1);
        clearConsole();
        setBackground("images/boden.png");
        taxizentrale = new Taxizentrale(pLength); //maximale Taxis = pLength = Länge des Feldes
        addObject(taxizentrale,0,0);
        System.out.println("Drücke a, um Taxis abfahren zu lassen");
        System.out.println("Drücke g, um Taxis generieren zu lassen");
    }
    public void clearConsole(){
        for (int i = 0; i < 50; i++){
            System.out.println("\n");
        }
    }
    public void act(){
        // System.out.println("ICH BIN GM4 IN OW");
        String key = Greenfoot.getKey();
        // Überprüfe, ob eine Taste gedrückt wurde
        if (key != null) {
            switch(key) {
                case "a":
                    Taxi abfahrendesTaxi = taxizentrale.fahreAb();
                    // Zeige die verbleibenden Taxis im Stand
                    taxizentrale.aktuelleTaxisImStand();
                    break;
                case "g":
                    Taxi neuesTaxi = new Taxi();
                    //addObject(pTaxi, getAnzahlWartenderTaxis() * gSize + gSize/2, gSize/2);
                    addObject(neuesTaxi,0,0);
                    
                    if (!taxizentrale.reiheEin(neuesTaxi)) {
                       removeObject(neuesTaxi);
                    }
                    // Zeige die verbleibenden Taxis im Stand
                    taxizentrale.aktuelleTaxisImStand();
                    break;
            }
        }
    }
}
