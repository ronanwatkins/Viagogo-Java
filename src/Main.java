import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Driver class
 * @author Ronan Watkins
 */
public class Main {

    private static int[] coordinates;

    /**
     * Main function
     * This is the entry point to the program
     * Gets user input and creates a Coordinate object from it
     * Computes distance between user's coordinates and coordinates of all events
     * Finds 5 closest events to the user's coordinates and prints the:
     *  Event ID
     *  Minimum Price
     *  Number of Tickets
     *  Distance and Location
     * of each event
     *
     * @param args  [unused]
     */
    public static void main(String[] args) {
        EventGrid eventGrid = new EventGrid();
        Map<Coordinate, Event> grid = eventGrid.getGrid();

        //Get coordinates from user
        getInput(true);

        //Coordinate object to hold user inputted coordinates
        Coordinate inputCoordinate = new Coordinate(coordinates[0], coordinates[1]);

        //Integer list to hold distances between user coordinates and each event
        List<Integer> distance = new ArrayList<>();

        //Compute distances between user coordinates and each event and add the distance to the list
        for (Coordinate c : grid.keySet()) {
            distance.add(getDistance(inputCoordinate, c));
        }
        Collections.sort(distance); //Sort list in ascending order

        if (distance.size() == 0) { //If no events exist (rare condition)
            System.out.println("No Events Found");
        } else {
            System.out.println("\nClosest events to \"" + inputCoordinate + "\"\n");
            StringBuilder sb;
            //Event list to hold up to 5 of the closest events to the user
            List<Event> eventList = new ArrayList<>();
            Event temp;

            int i=0;
            //Allow a maximum of 5 events. Take into account the fact that there may be less than 5
            int eventNum = distance.size() < 5 ? distance.size() : 5;
            for(int dist : distance) { //Step through list of distances between user coordinates and all events
                for (Coordinate c : grid.keySet()) { //Step through coordinates of all events
                    //This will find the closest 5 events to the user, place each event in a List and print the details of the event
                    if(getDistance(inputCoordinate, c) == dist && i < eventNum) {
                        if(!eventList.contains(grid.get(c))) { //Don't select the same event twice
                            temp = grid.get(c);
                            eventList.add(temp); //Add the Event to the eventList

                            sb = new StringBuilder();
                            sb.append("Event ID : " + temp.getEventId() + "\n");
                            sb.append(String.format("Minimum Price: $%.2f\n", temp.getMinPrice()));
                            sb.append("Number of Tickets: " + temp.getTicketNum() + "\n");
                            sb.append("Distance and Location: " + dist +", (" + temp.getLocation() + ")\n");
                            System.out.println(sb.toString());

                            i++;
                        }
                    }
                }
            }
        }
    }

    /**
     * This function gets user input and places the input into the global array "coordinates"
     * Recursively calls itself while wrong coordinates are entered
     * @param isFirst
     */
    private static void getInput(boolean isFirst) {
        Scanner sc = new Scanner(System.in);
        coordinates = new int[2];

        if (isFirst) { //This will be printed on the first iteration of the function
            System.out.println("Please enter coordinates in the form \"x,y\"");
            System.out.println("Coordinates range between -10 and 10");
        } else { //This will be printed on subsequent iterations of the function
            System.out.println("You supplied incorrect coordinates");
            System.out.println("Please enter coordinates in the form \"x,y\"");
        }
        String input = sc.nextLine(); //Get user input from the console

        try {
            coordinates[0] = Integer.parseInt(input.substring(0, input.indexOf(",")));
            System.out.println(input.substring(0, input.indexOf(",")));
            coordinates[1] = Integer.parseInt(input.substring(input.indexOf(",")+1));
            System.out.println(input.substring(input.indexOf(",")+1));
        } catch (Exception e) { //An exception will occur here if the user enters a String, float etc
            getInput(false);
        } finally {
            if(coordinates[0] < -10 || coordinates[0] > 10 || coordinates[1] < -10 || coordinates[1] > 10) { //Check if coordinates are too small/large
                getInput(false);
            }
        }
    }

    /**
     * This function computes the Manhattan distance between 2 Coordinates
     * @param c1
     * @param c2
     * @return Manhattan distance between both coordinates as an integer
     */
    private static int getDistance(Coordinate c1, Coordinate c2) {
        return Math.abs(c1.getxCoord() - c2.getxCoord()) + Math.abs(c1.getyCoord() - c2.getyCoord());
    }

}
