package com.company;

public class Flight {
    private Airport departureAirport, arrivalAirport;
    private String airline;
    private int duration;


    //Constructor of Flights
    Flight(Airport departureAirport, Airport arrivalAirport, int duration, String airline){
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.duration = duration;
        this.airline = airline;
    }

    //Getter of Departure Airport (Airport A)
    Airport getDepartureAirport() {
        return departureAirport;
    }

    //Getter of Arrival Airport (Airport B)
    Airport getArrivalAirport() {
        return arrivalAirport;
    }

    //Getter of Airline of Flight
    String getAirline() {
        return airline;
    }

    //Getter of Duration of Flight
    int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Flight operated by " + this.airline + ", with duration " + this.duration + " minutes";
    }
}
