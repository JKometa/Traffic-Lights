package com.pszt.TrafficLights.view;

import com.pszt.TrafficLights.Controller.Controller;
import com.pszt.TrafficLights.Ewolucja.Populacja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;

/**
 * Created with IntelliJ IDEA.
 * User: kometa
 * Date: 11.05.2013
 * Time: 19:18
 * To change this template use File | Settings | File Templates.
 */
public class OknoGlowne {

    private Controller controller;
    private JSpinner hCount;
    private JSpinner vCount;
    private JComboBox populacje;
    private Button rududududu;
    boolean running = false;

    public OknoGlowne(Controller controller) {
        this.controller = controller;
    }

    public void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);


        JFrame frame = new JFrame("Super hiper Swiatla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GridLayout experimentLayout = new GridLayout(0, 2);
        int i = 4;
        int j = 2;
        JPanel[][] panelHolder = new JPanel[i][j];


        for (int m = 0; m < i; m++) {
            for (int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                frame.add(panelHolder[m][n]);
            }
        }

        hCount = new JSpinner();
        vCount = new JSpinner();
        String[] opcje = {"Najlepszy", "Ranking", "Ruletka"};
        populacje = new JComboBox(opcje);


        populacje.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                   int selected =  populacje.getSelectedIndex();
                    Populacja.Wybor wynik;
                   if(selected == 0){
                       wynik = Populacja.Wybor.NAJLEPSZY;
                   }else if(selected == 1){
                       wynik = Populacja.Wybor.RANKING;

                   }else
                       wynik = Populacja.Wybor.RULETKA;
                       controller.setWyborPopulacji(wynik);
                }
            }
        });
        rududududu = new Button("Rududu");

        frame.setLayout(experimentLayout);
        panelHolder[0][0].add(new JLabel("Ilość w poziomie:"));
        panelHolder[0][1].add(hCount);
        panelHolder[1][0].add(new JLabel("Ilość w pionie:"));
        panelHolder[1][1].add(vCount);
        panelHolder[2][0].add(new JLabel("Coś nie wiem co:"));
        panelHolder[2][1].add(populacje);
        panelHolder[3][0].add(rududududu);

        rududududu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!running) {
                    running = true;
                    controller.display((Integer) hCount.getValue(), (Integer) vCount.getValue());
                } else {
                    running = false;
                    controller.stopSimulation();
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
