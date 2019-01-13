package com.company;


import java.util.ArrayList;

public class Airport {
    private String name, codeName, city, country;
    private Node node;
    private ArrayList<String> airlines = new ArrayList<String>();
    private ArrayList<Airport> directlyConnectedAirports = new ArrayList<Airport>();



    Airport(String name, String codeName, String city, String country){
        this.name = name;
        this.codeName = codeName;
        this.city = city;
        this.country = country;
        this.node = null;
    }


    //Checks if the list of directly connected Airports of this Airport contains the input Airport
    private boolean isDirectlyConnectedTo(Airport anAirport){
        return this.getDirectlyConnectedAirports().contains(anAirport);
    }

    @Override
    public String toString() {
        return city + ", " + codeName + " Airport";
    }

    //Checks for every flight if this airport is the departure Airport and then checks if the arrival Airport is directly
    //connected to input Airport and vice versa
    public boolean isInDirectlyConnectedTo(Airport anAirport){
        ArrayList<Flight> flightArrayList = CentralRegistry.getFlights();
        boolean flag = false;
        for (Flight flight : flightArrayList) {
            if (this == flight.getArrivalAirport()) {
                if (flight.getDepartureAirport().isDirectlyConnectedTo(anAirport))
                    flag = true;
            } else if (this == flight.getDepartureAirport()) {
                if (flight.getArrivalAirport().isDirectlyConnectedTo(anAirport))
                    flag = true;
            }
        }
        return flag;
    }


    //Returns the common connections of the two airports while keeping in mind not to add themselves
    ArrayList<Airport> getCommonConnections(Airport anAirport){
        ArrayList<Airport> airportArrayList = CentralRegistry.getAirports();
        ArrayList<Airport> commonConnections = new ArrayList<Airport>();
        for (Airport anAirportArrayList : airportArrayList) {
            if (this != anAirportArrayList && anAirport != anAirportArrayList) {
                if (this.isDirectlyConnectedTo(anAirportArrayList) &&
                        anAirport.isDirectlyConnectedTo(anAirportArrayList))
                    commonConnections.add(anAirportArrayList);
            }
        }
        return commonConnections;
    }


    ArrayList<Airport> getDirectlyConnectedAirports() {
        return directlyConnectedAirports;
    }


    public void printCompanies(){
        for (String airline : airlines) {
            System.out.println(airline);
        }
    }

    void updateNode(Node aNode){
        this.node = aNode;
    }

    Node getNode() {return this.node;}

    String getName() {
        return name;
    }

    ArrayList<String> getAirlines() {
        return airlines;
    }

    String getCity() {
        return city;
    }


    String getCodeName() {
        return codeName;
    }

    String getCountry() {
        return country;
    }
}


