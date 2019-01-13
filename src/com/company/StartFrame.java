package com.company;

import java.awt.event.*;
import javax.swing.*;


public class StartFrame extends JFrame implements ActionListener {

    private JButton searchButton = new JButton("Search");
    private JTextField searchTextField;

    StartFrame() {
        this.setTitle("Find Airport");
        JPanel searchPanel = new JPanel();
        searchTextField = new JTextField(17);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        JButton visualizationButton = new JButton("Visualize Network");
        searchPanel.add(visualizationButton);
        this.setContentPane(searchPanel);
        searchButton.addActionListener(this);
        visualizationButton.addActionListener(this);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            if (CentralRegistry.getAirportViaElement(searchTextField.getText()) == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Airport is not listed", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                new MainFrame(CentralRegistry.getAirportViaElement(searchTextField.getText()));
                this.dispose();
            }
        }
        else{
            new VisualFrame();
        }
    }
}


