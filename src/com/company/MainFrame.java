package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {
    private JButton findButton = new JButton("Find Flights");
    private JButton backButton = new JButton("Back to Search Screen");
    private JTextField toCity;
    private JTextArea airlineNames;
    private JTextArea directlyConnectedFlights, indirectConnections;
    private Airport anAirport;
    MainFrame(Airport anAirport){
        this.anAirport = anAirport;
        this.setTitle("Airport Page");
        JPanel mainPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout(10,10));
        infoPanel.setLayout(new FlowLayout());

        JTextField airportName = new JTextField(anAirport.getName(), 10);
        JTextField codeName = new JTextField(anAirport.getCodeName(), 10);
        JTextField cityName = new JTextField(anAirport.getCity(), 10);
        JTextField countryName = new JTextField(anAirport.getCountry(), 10);
        airlineNames = new JTextArea(5,10);
        printAirlineNames(anAirport.getAirlines());

        infoPanel.add(airportName);
        infoPanel.add(codeName);
        infoPanel.add(cityName);
        infoPanel.add(countryName);
        infoPanel.add(airlineNames);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Airport Info"));

        JPanel findFlightsPanel = new JPanel();
        toCity = new JTextField(15);
        findFlightsPanel.add(toCity);
        findFlightsPanel.add(findButton);
        findButton.addActionListener(this);

        JPanel connectionPanel = new JPanel();
        directlyConnectedFlights = new JTextArea(10,30);
        indirectConnections = new JTextArea(10,30);
        connectionPanel.add(directlyConnectedFlights);
        connectionPanel.add(indirectConnections);

        JPanel backPanel = new JPanel();
        backPanel.add(backButton);
        backButton.addActionListener(this);

        mainPanel.add(infoPanel);
        mainPanel.add(findFlightsPanel);
        mainPanel.add(connectionPanel);
        mainPanel.add(backPanel);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findButton){
            if (CentralRegistry.getAirportViaElement(toCity.getText())== null)
                JOptionPane.showMessageDialog(new JFrame(), "Airport or City not listed", "Error", JOptionPane.ERROR_MESSAGE);
            else if (CentralRegistry.getAirportViaElement(toCity.getText())==anAirport) {
                JOptionPane.showMessageDialog(new JFrame(), "Airport cannot be the same with origin airport", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                Airport anotherAirport = CentralRegistry.getAirportViaElement(toCity.getText());
                printDirectlyConnectedFlights(CentralRegistry.getDirectlyConnectedFlights(anAirport, anotherAirport));
                printCommonConnections(anAirport, anotherAirport);
                makeTxtFile(anAirport, anotherAirport);
            }
        }
        else if (e.getSource() == backButton){
            new StartFrame();
            dispose();
        }
    }
    private void printAirlineNames(ArrayList<String> airlines){
        for (String airline: airlines){
            airlineNames.append(airline + "\n");
        }
    }

    private void makeTxtFile(Airport anAirport, Airport anotherAirport){
        try {
            PrintWriter writer = new PrintWriter(anAirport.getCity() + "-to-" + anotherAirport.getCity()+".txt", "UTF-8");
            writer.println("CITY: " + anAirport.getCity() +  ", " + anAirport.getCountry());
            writer.println("Airport: " + anAirport.getName());
            writer.println("DESTINATION: " + anotherAirport.getCity() + "\n");
            writer.println("DIRECT FLIGHT DETAILS");
            ArrayList<Flight> directFlights = CentralRegistry.getDirectlyConnectedFlights(anAirport, anotherAirport);
            if (directFlights.size() == 0){
                writer.println("No direct flights available");
            }
            else {
                int index = 1;
                for (Flight flight : directFlights){
                    writer.println("[" + index++ + "] " + flight.toString());
                }
            }
            writer.println();
            writer.println("INDIRECT FLIGHTS THROUGH: ");
            if (anAirport.getCommonConnections(anotherAirport).size()==0)
                writer.println("No indirect flights available...");
            else{
                ArrayList<Airport> indirectAirports = anAirport.getCommonConnections(anotherAirport);
                int index = 1;
                for (Airport airport: indirectAirports)
                    writer.println("[" + index++ + "] " + airport.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    private void printDirectlyConnectedFlights(ArrayList<Flight> flights){
        directlyConnectedFlights.setText(null);
        directlyConnectedFlights.append("DIRECT FLIGHTS DETAILS\n");
        if (flights.size()==0){
            directlyConnectedFlights.append("No direct flights available");
        }
        else{
            int index = 1;
            for (Flight flight:flights)
                directlyConnectedFlights.append("[" + index++ +"] " + flight.toString() + "\n");
        }
    }

    private void printCommonConnections(Airport anAirport, Airport anotherAirport){
        indirectConnections.setText(null);
        indirectConnections.append("INDIRECT FLIGHTS THROUGH: \n");
        if (anAirport.getCommonConnections(anotherAirport).size()==0)
            indirectConnections.append("No indirect flights available...");
        else{
            ArrayList<Airport> indirectAirports = anAirport.getCommonConnections(anotherAirport);
            int index = 1;
            for (Airport airport: indirectAirports)
                indirectConnections.append("[" + index++ + "] " + airport.toString() + "\n");
        }
    }
}
