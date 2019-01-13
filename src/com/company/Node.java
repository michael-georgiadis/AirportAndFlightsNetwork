package com.company;

 class Node {
     private Airport airport;
     Node (Airport anAirport){
         this.airport = anAirport;
         this.airport.updateNode(this);
     }

     Airport getAirport() {
         return this.airport;
     }

     @Override
     public String toString() {
         return this.airport.getName();
     }
 }

 class Link {
     private double capacity;
     private double weight;

     public Link(double aCapacity, double aWeight) {
         this.capacity = aCapacity;
         this.weight = aWeight;
     }
 }
