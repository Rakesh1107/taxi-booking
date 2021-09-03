package taxibooking;

import java.util.ArrayList;
import java.util.List;

public class ObjectCreator {

    public static List<Taxi> createTaxis(int count) {
        List<Taxi> taxis = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            taxis.add(new Taxi());
        }

        return taxis;
    }

    public static Ride createRide(int customerId, char pickupPoint, char dropPoint, int pickupTime, int rideCost) {
        Ride ride = new Ride(customerId);
        ride.setPickupPoint(pickupPoint);
        ride.setDropPoint(dropPoint);
        ride.setPickupTime(pickupTime);
        ride.setRideCost(rideCost);
        return ride;
    }
}
