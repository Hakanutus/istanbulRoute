/*Program to create stations object to use in subway route drawing
@author Hakan Utus, Student ID: 041901018
@since date : 17/11/2020

 */

public class Stations {
    /**
     * Creates a class to create object stations
     */

    public String metroLines; // public data field for metro lines
    public String stationName; //public data field for station names
    public double x;  // public data field for x coordinate of station
    public double y; // public data field for y coordinate of station
    // Creating default constructor
    public Stations() {

    }

    /**
     * Creates 4 input constructor to use later
     * @param metroLines uses data field metro line
     * @param stationName uses data field station name
     * @param x   uses data field x
     * @param y   uses data field y
     */
    public Stations(String metroLines, String stationName, double x, double y) {
        this.metroLines = metroLines;  // declares that this metro line is equal to data field metro line
        this.stationName = stationName;  //declares that this station name is equal to data field station name
        this.y = y;  // declares that this y is equal to data field y
        this.x = x;  // declares that this x is equal to data field x
    }

    /**
     * Creates 2 input constructor to use later
     * @param x  x coordinate of station
     * @param y  y coordinate of station
     */
    public Stations(double x, double y) {
        this.x = x;
        this.y = y;
    }
}