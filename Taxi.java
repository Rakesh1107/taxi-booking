package taxibooking;

import java.util.ArrayList;
import java.util.List;

public class Taxi implements Runnable {
    private static int count = 0;
    private int id;
    private char currentPoint;
    private boolean free;
    private int currentRide;
    private char destination;
    private int totalEarnings;
    private List<Ride> rides = new ArrayList<>();

    Taxi() {
        this.id = ++count;
        this.currentPoint = 'A';
        this.free = true;
    }

    // Getters
    public int getId() {
        return id;
    }

    public char getCurrentPoint() {
        return currentPoint;
    }

    public boolean isFree() {
        return free;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public char getDestination() {
        return destination;
    }

    public int getCurrentRide() {
        return currentRide;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentPoint(char currentPoint) {
        this.currentPoint = currentPoint;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public void setCurrentRide(int currentRide) {
        this.currentRide = currentRide;
    }

    public void setDestination(char destination) {
        this.destination = destination;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    @Override
    public String toString() {
        return "Taxi-" +
                "id=" + id +
                ", currentPoint=" + currentPoint +
                ", free=" + free +
                ", destination=" + destination ;
    }

    @Override
    public synchronized void run() {
        this.free = false;
        try {
            System.out.println(currentRide * 60 * 1000);
            Thread.sleep(currentRide * 60 * 1000);
            this.free = true;
            this.currentPoint = destination;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
