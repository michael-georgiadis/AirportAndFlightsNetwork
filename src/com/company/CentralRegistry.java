package com.company;


import java.util.ArrayList;

public class CentralRegistry {
    private static ArrayList<Airport> airports = new ArrayList<Airport>();
    private static ArrayList<Flight> flights = new ArrayList<Flight>();


    static void addAirport(Airport anAirport){
        airports.add(anAirport);
    }

    //Adds a flight and the does the appropriate checks to update the lists with connected Airports and serving Airlines
    static void  addFlight(Flight aFlight){
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

    static ArrayList<Flight> getDirectlyConnectedFlights(Airport anAirport, Airport anotherAirport){
        ArrayList<Flight> directFlights = new ArrayList<Flight>();
        for (Flight flight : flights){
            if ((flight.getArrivalAirport() == anAirport && flight.getDepartureAirport() == anotherAirport) ||
                    (flight.getArrivalAirport() == anotherAirport && flight.getDepartureAirport() == anAirport)){
                directFlights.add(flight);
            }
        }
        return directFlights;
    }


    private static Airport getAirportViaName(String aText){
        for (Airport airport: airports){
            if (airport.getName().equals(aText)) return airport;
        }
        return null;
    }

    private static Airport getAirportViaCity(String aText){
        for(Airport airport:airports){
            if (airport.getCity().equals(aText)) return airport;
        }
        return null;
    }

    static Airport getAirportViaElement(String aText){
        if(getAirportViaCity(aText) == null && getAirportViaName(aText) == null){
            return null;
        }
        else if (getAirportViaName(aText) == null){
            return getAirportViaCity(aText);
        }
        else return getAirportViaName(aText);
    }

    //Getter of Airports
    static ArrayList<Airport> getAirports() {
        return airports;
    }

    //Getter of Flights
    static ArrayList<Flight> getFlights() {
        return flights;
    }

    public static Airport getAirport(String airportName) {
        for (Airport airport:airports)
            if (airport.getName().equals(airportName)) {
                return airport;
            }
        return null;
    }
}
