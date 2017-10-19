import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Event class
 * @author Ronan Watkins
 *
 */

public class Event {

    private Coordinate coordinate;
    private int eventId;
    private int ticketNum;
    private List<Double> tickets = new ArrayList<>();

    /**
     * Creates an Event object
     * Randomly generates ticket prices and amount of tickets
     * Minimum amount of tickets is 0, Maxmimum amount is 300
     * Minumum ticket price is at least $10, Maximum price is no more than Minimum price+$30
     * @param eventId
     * @param x
     * @param y
     */
    public Event(int eventId, int x, int y) {
        Random rand = new Random();

        this.eventId = eventId;
        this.coordinate = new Coordinate(x, y);
        this.ticketNum = rand.nextInt(300);

        double minPrice = 10 + rand.nextDouble()*100;
        double price;
        for (int i = 0; i < ticketNum; i++) {
            price = minPrice+rand.nextDouble()*30; //Randomly generate price for every ticket
            tickets.add(i, price);
        }

        Collections.sort(tickets); //Sort tickets in ascending order
    }

    /**
     *
     * Getters
     */

    public double getMinPrice() {
        return tickets.get(0);
    }

    public double getMaxPrice() {
        return tickets.get(tickets.size()-1);
    }

    public int getEventId() {
        return eventId;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public String getLocation() {
        return coordinate.toString();
    }

    /**
     * Overriding the toString method
     */
    @Override
    public String toString() {
        return "ID: " + eventId + " Num of tickets: " + ticketNum + " Max price: " + getMaxPrice() + " Min price: " + getMinPrice() + " Location: " + getLocation();
    }
}
