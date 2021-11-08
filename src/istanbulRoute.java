import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.DoubleBinaryOperator;

/**
 * Program to draw subway route with a user decided route
 * @author Hakan Utus
 * @since Date : 17.11.2020
 *
 */
public class istanbulRoute {
    /**
     *Takes two string stations to read and creates a file to read
     * @param args Main input arguments are not used
     * @throws FileNotFoundException avoids file not found exception
     */

    public static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in); // scanner to read station and destination
        String metroFile = "metro.txt";  // text file
        File stationData = new File(metroFile); // create new file to initiliaze
        System.out.println("Enter first station: ");  // reads station
        String firstStat = scanner.nextLine();
        System.out.println("Enter destination: ");  //reads destination
        String lastDest = scanner.nextLine();
        ArrayList<Stations> stationsDatabase = new ArrayList<>(); // creates a list to store text data
        stationsDatabase = readingStations(stationData);
        routeDrawer(stationsDatabase);
        userTrip(stationsDatabase, firstStat, lastDest);
        checkExstra(stationsDatabase, firstStat, lastDest);
    }


    /**
     * Program to read stations
     * @param stationData File to read and store
     * @return to list stored with text file data
     * @throws FileNotFoundException
     */
    public static ArrayList<Stations> readingStations(File stationData) throws FileNotFoundException {


        ArrayList<Stations> inputStation = new ArrayList<>(); // creates a list to read then store


        Scanner scanner = new Scanner(stationData); // scanner to read file

        while (scanner.hasNextLine()) {  // loop through the end of file
            String allLines = scanner.nextLine();  // string to take all file texts
            String[] allLinesArray = allLines.split(";"); // .split method to split them
            String metroLine = allLinesArray[0]; // array to store elements in different places
            String stationNames = allLinesArray[1];
            double xCoordinate = Double.parseDouble(allLinesArray[2]);
            double yCoordinate = Double.parseDouble(allLinesArray[3]);

            Stations createdStation = new Stations(metroLine, stationNames, xCoordinate, yCoordinate); //create a station to store one station
            inputStation.add(createdStation); // add station to list


        }


        scanner.close();
        return inputStation;

    }

    /**
     * program to calculate distance
     * @param stationOne first station
     * @param stationTwo second station
     * @return calculated station
     */

    public static double evaluateDistance(Stations stationOne, Stations stationTwo) {
        double xFirst = stationOne.x; // first station's x
        double yFirst = stationOne.y; // first station's y
        double xSecond = stationTwo.x; //first station's x
        double ySecond = stationTwo.y; // first station's y
        double distance = Math.sqrt((Math.pow(stationTwo.x - stationOne.x, 2)) + Math.pow((stationTwo.y - stationOne.y), 2));
        // calculating distance
        return distance;
    }

    /**
     * program to draw the rough route
     * @param sortedStations list that holds the stations
     */

    public static void routeDrawer(ArrayList<Stations> sortedStations) {
        double totalDistance = 0; // double to use later
        StdDraw.setCanvasSize(1024, 768); // setting canvas size
        StdDraw.setXscale(0, 1500); // setting x scale
        StdDraw.setYscale(0, 1500); // setting y scale
        for (int i = 0; i <= sortedStations.size()-2 ; i++) { // for loop to iterate through stations
            StdDraw.setPenColor(Color.black);
            StdDraw.filledCircle(sortedStations.get(i + 1).x, sortedStations.get(i + 1).y, 5);
            // points out the stations
            StdDraw.text(sortedStations.get(i + 1).x, sortedStations.get(i + 1).y + 10, sortedStations.get(i + 1).stationName);
            // writes down the station names
        }
        for (int i = 0; i <= sortedStations.size()-2; i++) {
            if (sortedStations.get(i).metroLines.contains("Marmaray")) { // checks if the line is marmaray
                if (sortedStations.get(i + 1).metroLines.contains("Marmaray")) { // checks if they are in the same line
                    StdDraw.setPenColor(Color.orange);
                    StdDraw.line(sortedStations.get(i).x, sortedStations.get(i).y, sortedStations.get(i + 1).x, sortedStations.get(i + 1).y);
                    //draws the marmaray line
                }

            } else if (sortedStations.get(i).metroLines.contains("M4")) { // checks if the line is M4
                if (sortedStations.get(i + 1).metroLines.contains("M4")) { // checks if they are in the same line
                    StdDraw.setPenColor(Color.LIGHT_GRAY);
                    StdDraw.line(sortedStations.get(i).x, sortedStations.get(i).y, sortedStations.get(i + 1).x, sortedStations.get(i + 1).y);
                    //draws the M4 line
                }
            } else if (sortedStations.get(i).metroLines.contains("M1")) { // checks if the line is M1
                if (sortedStations.get(i + 1).metroLines.contains("M1")) { // checks if they are in the same line
                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.line(sortedStations.get(i).x, sortedStations.get(i).y, sortedStations.get(i + 1).x, sortedStations.get(i + 1).y);
                    //draws the M1 line
                }

            } else if (sortedStations.get(i).metroLines.contains("M2")) { // checks if the line is M2
                if (sortedStations.get(i+1).metroLines.contains("M2")) { // checks if they are in the same line
                    StdDraw.setPenColor(Color.green);
                    StdDraw.line(sortedStations.get(i).x, sortedStations.get(i).y, sortedStations.get(i + 1).x, sortedStations.get(i + 1).y);
                    // draws the M2 line
                }
            } else if (sortedStations.get(i).metroLines.contains("M5")) { // checks if the line is M5
                if (sortedStations.get(i+1).metroLines.contains("M5")) { // checks if they are in the same line

                    StdDraw.setPenColor(Color.pink);
                    StdDraw.line(sortedStations.get(i).x, sortedStations.get(i).y, sortedStations.get(i + 1).x, sortedStations.get(i + 1).y);
                    // draws the M5 line
                }

            }


        }
    }

    /**
     * Program to draw users trip
     * @param stationsLast station's list
     * @param inputStat users first station
     * @param inputDest users destination
     */
    public static void userTrip(ArrayList<Stations> stationsLast, String inputStat, String inputDest) {
        double totalDistance = 0; //double to use later
        double time = 0; // double to use later

        int stationIndex=0;
        int destinationIndex=0;
        double velocity = 30; // constant velocity

        for (int i = 0; i < stationsLast.size(); i++) {
            if (stationsLast.get(i).stationName.contains(inputStat)) { //checks if the station exists
                stationIndex = i; // sets the stations index into a number
            }
        }
        for (int j = 0; j < stationsLast.size(); j++) {
            if (stationsLast.get(j).stationName.contains(inputDest)) { //checks if the destination exists
                destinationIndex = j; // sets the destination's index into an integer
            }
        }
        if (stationsLast.get(stationIndex).metroLines.equals(stationsLast.get(destinationIndex).metroLines) ) { //checks if metro lines are the same
            if (stationIndex < destinationIndex) { //checks going forward
                for (int i = stationIndex; i <= destinationIndex ; i++) { // goes the route
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.line(stationsLast.get(i).x, stationsLast.get(i).y, stationsLast.get(i+1).x, stationsLast.get(i+1).y);
                    //draws the route
                    totalDistance = totalDistance + evaluateDistance(stationsLast.get(i), stationsLast.get(i+1));
                    //increments the distance
                    StdDraw.line(stationsLast.get(i).x, stationsLast.get(i).y, stationsLast.get(i+1).x, stationsLast.get(i-1).y);


                }
            }
            else if (stationIndex > destinationIndex) { // goes the route backwards
                for (int i = stationIndex; i >= destinationIndex ; i--) {
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.line(stationsLast.get(i).x, stationsLast.get(i).y, stationsLast.get(i-1).x, stationsLast.get(i-1).y);
                    // goes back
                    totalDistance = totalDistance + evaluateDistance(stationsLast.get(i), stationsLast.get(i+1));
                    //increments the distance
                    StdDraw.line(stationsLast.get(i).x, stationsLast.get(i).y, stationsLast.get(i-1).x, stationsLast.get(i-1).y);
                    // goes again
                }


            }

        }
        StdDraw.show(); // shows the drawing
        time = totalDistance / velocity; // calculates the trip duration
        System.out.println("Distance: " + totalDistance);
        System.out.println("Trip duration: " + time);



    }

    /**
     * checks the extra situations
     * @param inputList3 station's list
     * @param inputStat2 users station
     * @param inputDest2 users destination
     */
    public static void checkExstra(ArrayList<Stations> inputList3, String inputStat2, String inputDest2) {
        int stationIndex = 0;
        int destinationIndex = 0;

        for (int i = 0; i < inputList3.size(); i++) {
            if (inputList3.get(i).stationName.contains(inputStat2)) {
                stationIndex = i; // takes the station's index
            }
            else {System.out.println("Station does not exists");
            }
        }
        for (int j = 0; j < inputList3.size(); j++){
            if (inputList3.get(j).stationName.contains(inputDest2)) {
                destinationIndex = j;
                if (destinationIndex == stationIndex) { // checks if the station and destination is the same
                    System.out.println("Stations are the same"); // prints out
                }
            }
        }if (!inputList3.get(stationIndex).metroLines.equals(inputList3.get(destinationIndex).metroLines) ) {
            //checks if the lines are the same
            System.out.println("Stations are not on the same line"); //prints out
        }



    }
}