package taxibooking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Runner {
     private static final int DISTANCE_BETWEEN_POINTS = 15;
     private static final int TRAVEL_TIME_BETWEEN_POINTS = 60;

     private static final char[] points = {'A', 'B', 'C', 'D', 'E', 'F'};
     private static final int MINIMUM_COST = 100;
     private static final int COST_PER_KM = 10;
     private static final List<Taxi> taxis = ObjectCreator.createTaxis(4);

     private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

     public static void main(String[] args) {

          boolean running = true;

          while (running) {
               int option = 0;
               try {
                    System.out.println("1. Book a Taxi");
                    System.out.println("2. Show Taxi Details");
                    System.out.println("Enter 0 to exit");
                    option = getInt();
               }
               catch (TaxiException e) {
                    System.out.println(e.getMessage());
               }

               switch (option) {
                    case 1:
                         bookTaxi();
                         break;
                    case 2:
                         showTaxiDetails();
                         break;
                    case 0:
                         System.out.println("Exiting application");
                         running = false;
                         break;
                    default:
                         System.out.println("Enter a valid option");
               }
          }

          try {
               input.close();
          }
          catch (IOException e) {
               e.printStackTrace();
          }
     }

     private static void bookTaxi() {
          try {
               System.out.println("Enter customer id");
               int customerId = getInt();
               System.out.println("Enter pickup point");
               char pickupPoint = getChar();
               System.out.println("Enter drop point");
               char dropPoint = getChar();
               System.out.println("Enter pickup time");
               int pickupTime = getInt();

               if (getFreeTaxis().isEmpty()) {
                    System.out.println("There are no free taxis at the moment");
                    return;
               }

               for (Taxi taxi: getFreeTaxis()) {
                    if (taxi.getCurrentPoint() == pickupPoint) {
                         int distance = DISTANCE_BETWEEN_POINTS * (Math.abs(pickupPoint - dropPoint)); // kilometres
                         int rideCost = 100 + ((distance-5) * 10);
                         int travelTime = (TRAVEL_TIME_BETWEEN_POINTS * (Math.abs(pickupPoint - dropPoint))); // minutes

                         taxi.setDestination(dropPoint);
                         taxi.setCurrentRide(travelTime);

                         Ride ride = ObjectCreator.createRide(customerId, pickupPoint, dropPoint, pickupTime, rideCost);
                         taxi.getRides().add(ride);

                         Thread thread = new Thread(taxi);
                         thread.start();

                         System.out.println("Taxi-" + taxi.getId() + " is allocated.");
                         System.out.println("Your ride cost is " + rideCost);
                         System.out.println("Travel time is " + travelTime);
                         return;
                    }
               }

               if (getNearbyTaxis(pickupPoint).isEmpty()) {
                    System.out.println("There are no free taxis at the moment");
                    return;
               }

               int minEarning = Integer.MAX_VALUE;
               Taxi taxi = null;
               for (Taxi temp: taxis) {
                    if (temp.getTotalEarnings() < minEarning) {
                         minEarning = temp.getTotalEarnings();
                         taxi = temp;
                    }
               }

               int distance = DISTANCE_BETWEEN_POINTS * (Math.abs(pickupPoint - dropPoint));
               int rideCost = 100 + ((distance-5) * 10);
               int travelTime = (TRAVEL_TIME_BETWEEN_POINTS * (Math.abs(pickupPoint - dropPoint))); // minutes

               taxi.setDestination(dropPoint);
               taxi.setCurrentRide(travelTime);

               Ride ride = ObjectCreator.createRide(customerId, pickupPoint, dropPoint, pickupTime, rideCost);
               taxi.getRides().add(ride);

               Thread thread = new Thread(taxi);
               thread.start();

               System.out.println("Taxi-" + taxi.getId() + " is allocated.");
               System.out.println("Your ride cost is " + rideCost);
               System.out.println("Travel time is " + travelTime);
          }
          catch (TaxiException e) {
               System.out.println(e.getMessage());
          }
     }

     private static List<Taxi> getFreeTaxis() {
          List<Taxi> freeTaxis = new ArrayList<>();
          for (Taxi taxi : taxis) {
               if (taxi.isFree()) {
                    freeTaxis.add(taxi);
               }
          }
          return freeTaxis;
     }

     private static List<Taxi> getNearbyTaxis(char point) {
          List<Taxi> nearByTaxis = new ArrayList<>();
          int min = 10;
          for (Taxi taxi : taxis) {
               if (taxi.isFree() && Math.abs(taxi.getCurrentPoint() - point) < min) {
                    min = Math.abs(taxi.getCurrentPoint() - point);
                    nearByTaxis.clear();
                    nearByTaxis.add(taxi);
               }
               else {
                    nearByTaxis.add(taxi);
               }
          }
          return nearByTaxis;
     }

     private static void showTaxiDetails() {
          for (Taxi taxi: taxis) {
               System.out.println(taxi);
               System.out.println("-------------------");
               for (Ride ride: taxi.getRides()) {
                    System.out.println(ride);
               }
               System.out.println("-------------------");
          }
     }

     // Input Methods with validation
      private static int getInt() throws TaxiException {
          int number = 0;
          try {
               number = Integer.parseInt(input.readLine());
          }
          catch (NumberFormatException | IOException e) {
               throw new TaxiException("Enter a valid number");
          }
          return number;
      }

      private static char getChar() throws TaxiException {
           char point = ' ';
           try {
                point = input.readLine().charAt(0);
           }
           catch (IOException e) {
                throw new TaxiException("Enter a valid point");
           }
           return point;
      }
}
