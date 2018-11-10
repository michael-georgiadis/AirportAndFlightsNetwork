package com.company;

public class Flight {
    private Airport departureAirport, arrivalAirport;
    private String airline;
    private int duration;


    //Constructor of Flights
    public Flight(Airport departureAirport, Airport arrivalAirport, int duration, String airline){
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.duration = duration;
        this.airline = airline;
    }

    //Getter of Departure Airport (Airport A)
    public Airport getDepartureAirport() {
        return departureAirport;
    }

    //Getter of Arrival Airport (Airport B)
    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    //Getter of Airline of Flight
    public String getAirline() {
        return airline;
    }

    //Getter of Duration of Flight
    public int getDuration() {
        return duration;
    }
}
