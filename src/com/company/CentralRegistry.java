package com.company;


import java.util.ArrayList;

public class CentralRegistry {
    private static ArrayList<Airport> airports = new ArrayList<Airport>();
    private static ArrayList<Flight> flights = new ArrayList<Flight>();


    public static void addAirport(Airport anAirport){
        airports.add(anAirport);
    }

    //Adds a flight and the does the appropriate checks to update the lists with connected Airports and serving Airlines
    public static void  addFlight(Flight aFlight){
        flights.add(aFlight);
        if (!aFlight.getArrivalAirport().getAirlines().contains(aFlight.getAirline()))
            aFlight.getArrivalAirport().getAirlines().add(aFlight.getAirline());
        if (!aFlight.getDepartureAirport().getAirlines().contains(aFlight.getAirline()))
            aFlight.getDepartureAirport().getAirlines().add(aFlight.getAirline());
        if (!aFlight.getArrivalAirport().getDirectlyConnectedAirports().contains(aFlight.getDepartureAirport()))
            aFlight.getArrivalAirport().getDirectlyConnectedAirports().add(aFlight.getDepartureAirport());
        if (!aFlight.getDepartureAirport().getDirectlyConnectedAirports().contains(aFlight.getArrivalAirport()))
            aFlight.getDepartureAirport().getDirectlyConnectedAirports().add(aFlight.getArrivalAirport());
    }

    //Returns the Airport with the most directly connected Airports
    public static Airport getLargestHub(){
        int max = Integer.MIN_VALUE;
        int maxPos = -1;
        for (int i=0;i<airports.size();i++){
            if (airports.get(i).getDirectlyConnectedAirports().size()>max){
                max = airports.get(i).getDirectlyConnectedAirports().size();
                maxPos = i;
            }
        }
        return airports.get(maxPos);
    }

    //Returns the Flight with the longest duration
    public static Flight getLongestFlight(){
        int max = Integer.MIN_VALUE;
        int maxPos = -1;
        for (int i=0;i<flights.size();i++){
            if (flights.get(i).getDuration()>max){
                max = flights.get(i).getDuration();
                maxPos = i;
            }
        }
        return flights.get(maxPos);
    }

    //Getter of Airports
    public static ArrayList<Airport> getAirports() {
        return airports;
    }

    //Getter of Flights
    public static ArrayList<Flight> getFlights() {
        return flights;
    }
}
