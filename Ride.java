package taxibooking;

public class Ride {

    static int count = 0;
    int customerId;
    int bookingId;
    int rideCost;
    char pickupPoint;
    char dropPoint;
    int pickupTime;

    public Ride(int customerId) {
        this.customerId = customerId;
        this.bookingId = ++count;
    }

    // Setters
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setRideCost(int rideCost) {
        this.rideCost = rideCost;
    }

    public void setPickupPoint(char pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public void setDropPoint(char dropPoint) {
        this.dropPoint = dropPoint;
    }

    public void setPickupTime(int pickupTime) {
        this.pickupTime = pickupTime;
    }

    // Getters
    public int getCustomerId() {
        return customerId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getRideCost() {
        return rideCost;
    }

    public char getPickupPoint() {
        return pickupPoint;
    }

    public char getDropPoint() {
        return dropPoint;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    @Override
    public String toString() {
        return "customer id | booking id | pickup point | drop point | pickup time | pickup time | ride cost \n" +
                customerId + " " + bookingId + " " + pickupPoint + " " + dropPoint + " " + pickupTime + " " +  rideCost;
    }
}
