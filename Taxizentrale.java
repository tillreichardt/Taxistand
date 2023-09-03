import java.util.Random; 
import greenfoot.*;

public class Taxizentrale extends Actor{
    private Taxi[] taxistand;
    private int gSize = MyWorld.gridSize;
    // Konstruktor, initialisiert einen neuen Taxistand mit einer maximalen Anzahl von Taxis
    public Taxizentrale(int maxTaxis) {
        taxistand = new Taxi[maxTaxis];
        
    }
    
    // Gibt die aktuelle Anzahl der Taxis im Stand zurück
    public int getAnzahlWartenderTaxis() {
        int anzahl = 0; 
        for (int i= 0; i < taxistand.length; i++) {
            if (taxistand[i] != null)  {
                anzahl +=1;
            }
        }
        return anzahl;
    }

    // Lässt das erste Taxi im Stand abfahren und gibt es zurück
    public Taxi fahreAb() {
        Taxi abfahrendesTaxi = taxistand[0];
        if (getAnzahlWartenderTaxis() <= 0) {
            System.out.println("\nKein Taxi zum Abfahren vorhanden.");
            return abfahrendesTaxi;
        }
        System.out.println("\nTaxi mit folgendem Kennzeichen fährt ab: " + abfahrendesTaxi.getKennzeichen());
        getWorld().removeObject(abfahrendesTaxi);
        rueckeAuf();
        
        return abfahrendesTaxi;           
    }
    
    // Verschiebt alle Taxis eine Position nach vorn im Array, um Platz für neue Taxis zu schaffen
    private void rueckeAuf() {

        for (int i = 0; i < getAnzahlWartenderTaxis() - 1; i++) {
            taxistand[i] = taxistand[i + 1];
            taxistand[i].setLocation(i* gSize + gSize/2, gSize/2);
        }
        taxistand[getAnzahlWartenderTaxis() - 1] = null; //setzt letzes Element des taxistandes auf null, um Speicherplatz des zuletzt besetzten Elements im Array freizugeben, nachdem alle Taxis aufgerückt sind. 
    }
        
    // Reicht ein neues Taxi in den Taxistand ein
    public boolean reiheEin(Taxi pTaxi) {
        if (getAnzahlWartenderTaxis() >= taxistand.length) {
            System.out.println("\nKein Platz mehr im Taxistand!");
            return false;
        }
        // Die neue Position wird durch die Koordinaten (lastTaxiIndex, 2) festgelegt.
        System.out.println("\nTaxi mit folgendem Kennzeichen reiht sich ein: " + pTaxi.getKennzeichen());
        pTaxi.setPosition(taxistand.length * gSize + gSize/2, gSize/2);
        pTaxi.moveToPosition(getAnzahlWartenderTaxis() * gSize + gSize/2, gSize/2);
        taxistand[getAnzahlWartenderTaxis()] = pTaxi;
    
        return true;
    }
    
    // Gibt eine Liste der aktuellen Taxis im Stand aus
    public void aktuelleTaxisImStand(){
        System.out.print("Aktuelle Taxis im Stand: ");
        for (int i = 0; i < getAnzahlWartenderTaxis(); i++) {
            System.out.print(taxistand[i].getKennzeichen() + "; ");
        }
        System.out.println();
    }

    
    // Gibt ein Taxi an einem bestimmten Index im Array zurück
    public Taxi getTaxi(int index) {
        if (index >= 0 && index < getAnzahlWartenderTaxis()) {
            return taxistand[index];
        } else {
            System.out.println("Index außerhalb des gültigen Bereichs");
            return null;
        }
    }
    
    

}