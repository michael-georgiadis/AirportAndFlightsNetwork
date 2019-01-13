package com.company;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

import javax.swing.*;
import java.awt.*;


class VisualFrame extends JFrame {


    VisualFrame(){
        JPanel networkPanel = new JPanel();
        NetworkGraph networkGraph = new NetworkGraph();
        Layout<Node, Link> layout = new CircleLayout<Node, Link>(networkGraph.graph);
        layout.setSize(new Dimension(300,300));
        BasicVisualizationServer<Node,Link> server = new BasicVisualizationServer<Node, Link>(layout);
        server.setPreferredSize(new Dimension(300,300));
        this.setTitle("Network Visualization");
        this.getContentPane().add(server);
        this.pack();
        this.setVisible(true);
    }
}
