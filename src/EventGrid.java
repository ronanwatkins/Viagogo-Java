import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * EventGrid class
 * Contains a hashMap with a Coordinate object as the key and an Event object as the value
 * @author Ronan Watkins
 */
public class EventGrid {

    private Map<Coordinate, Event> grid;

    /**
     * Creates an EventGrid object
     * Creates a maximum of 400 events and places each event at a unique coordinate
     */
    public EventGrid()
    {
        grid = new HashMap<>();
        Random rand = new Random();
        //Number of events is a random integer between 0 and 400
        int eventNum = rand.nextInt(400);
        int x,y;

        for(int i = 0; i < eventNum;i++)
        {
            Coordinate coordinate;
            do {
                x = 10 - rand.nextInt(20); //X is a random integer between -10 and 10
                y = 10 - rand.nextInt(20); //Y is a random integer between -10 and 10
                coordinate = new Coordinate(x, y);
            } while (grid.containsKey(coordinate)); //Only one event can be at a single coordinate

            //Create new Event object passing i as the Event ID and x,y as the coordinates
            Event event = new Event(i, x, y);
            //Add the coordinate and event to the grid hashMap
            grid.put(coordinate, event);
        }
    }

    /**
     * Getter for the hashMap
     * @return hashMap of <Coordinate, Event>
     */
    public Map<Coordinate, Event> getGrid() {
        return grid;
    }
}
