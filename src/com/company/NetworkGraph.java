package com.company;

import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

import java.util.ArrayList;

 class NetworkGraph {

    private ArrayList<Airport> airports = CentralRegistry.getAirports();
    private ArrayList<Node> aiportNodes = new ArrayList<Node>();
    private ArrayList<Airport> usedAirports = new ArrayList<Airport>();
    UndirectedSparseMultigraph<Node, Link> graph = new UndirectedSparseMultigraph<Node, Link>();
    NetworkGraph(){
       for (Airport airport: airports){
          aiportNodes.add(new Node(airport));
       }

       for (Node node: aiportNodes){
           for (Airport directlyConnectedAirport: node.getAirport().getDirectlyConnectedAirports()){
               if (node.getAirport()!= directlyConnectedAirport){
                   graph.addEdge(new Link(48, 2.0),node, directlyConnectedAirport.getNode(), EdgeType.UNDIRECTED);
               }
           }
       }
    }
}
