package com.company;

import java.awt.event.*;
import javax.swing.*;



public class startFrame extends JFrame implements ActionListener{

    JButton searchButton = new JButton("Search");
    JTextField searchTextField;
    startFrame() {
        this.setTitle("Find Airport");
        JPanel searchPanel = new JPanel();
        searchTextField = new JTextField(17);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        this.setContentPane(searchPanel);
        searchButton.addActionListener(this);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(300,150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (CentralRegistry.getAirportViaElement(searchTextField.getText()) == null){
            JOptionPane.showMessageDialog(new JFrame(), "Airport is not listed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            new mainFrame(CentralRegistry.getAirportViaElement(searchTextField.getText()));
            this.dispose();
        }
    }
}


