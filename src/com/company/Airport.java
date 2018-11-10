package com.company;


import java.util.ArrayList;

public class Airport {
    private String name, codeName, city, country;
    private ArrayList<String> airlines = new ArrayList<String>();
    private ArrayList<Airport> directlyConnectedAirports = new ArrayList<Airport>();


    public Airport(String name, String codeName, String city, String country){

        this.name = name;
        this.codeName = codeName;
        this.city = city;
        this.country = country;

    }

    //Checks if the list of directly connected Airports of this Airport contains the input Airport
    public boolean isDirectlyConnectedTo(Airport anAirport){
        return this.getDirectlyConnectedAirports().contains(anAirport);
    }


    //Checks for every flight if this airport is the departure Airport and then checks if the arrival Airport is directly
    //connected to input Airport and vice versa
    public boolean isInDirectlyConnectedTo(Airport anAirport){
        ArrayList<Flight> flightArrayList = CentralRegistry.getFlights();
        boolean flag = false;
        for (int i=0;i<flightArrayList.size();i++){
            if (this == flightArrayList.get(i).getArrivalAirport()){
                if (flightArrayList.get(i).getDepartureAirport().isDirectlyConnectedTo(anAirport))
                    flag = true;
            }
            else if (this == flightArrayList.get(i).getDepartureAirport()){
                if (flightArrayList.get(i).getArrivalAirport().isDirectlyConnectedTo(anAirport))
                    flag = true;
            }
        }
        return flag;
    }

    //Returns the common connections of the two airports while keeping in mind not to add themselves
    public ArrayList<Airport> getCommonConnections(Airport anAirport){
        ArrayList<Airport> airportArrayList = CentralRegistry.getAirports();
        ArrayList<Airport> commonConnections = new ArrayList<Airport>();
        for (int i=0;i<airportArrayList.size();i++) {
            if (this != airportArrayList.get(i) && anAirport != airportArrayList.get(i)) {
                if (this.isDirectlyConnectedTo(airportArrayList.get(i)) &&
                        anAirport.isDirectlyConnectedTo(airportArrayList.get(i)))
                    commonConnections.add(airportArrayList.get(i));
            }
        }
        return commonConnections;
    }


    public ArrayList<Airport> getDirectlyConnectedAirports() {
        return directlyConnectedAirports;
    }


    public void printCompanies(){
        for (int i=0;i<airlines.size();i++){
            System.out.println(airlines.get(i));
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAirlines() {
        return airlines;
    }
}


