package com.pszt.TrafficLights.view;

import com.pszt.TrafficLights.Controller.Controller;
import com.pszt.TrafficLights.Ewolucja.Populacja;
import com.pszt.TrafficLights.model.Car;
import com.pszt.TrafficLights.model.Crossroad;
import com.pszt.TrafficLights.model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kometa
 * Date: 11.05.2013
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class WidokMain extends JFrame{
    Widok w;
    Widok widokBezSymulancji;
    Controller controller;
    JFrame bezEwolucji;

    private int[] horizontal;
    private int[] vertical;
    public void setController(Controller controller) {
        this.controller = controller;
        controller.setWyborPopulacji(Populacja.Wybor.NAJLEPSZY);

    }

    public void init(){
        new OknoGlowne(controller).createAndShowGUI();

    }
    public void showSimulation() {

        w = new Widok(controller);
        w.setLines(horizontal, vertical);
        add(w);
        bezEwolucji = new JFrame("Bez ewolucji");

        widokBezSymulancji = new Widok(controller);
        widokBezSymulancji.setLines(horizontal, vertical);
        bezEwolucji.add(widokBezSymulancji);
        bezEwolucji.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bezEwolucji.setSize(Model.BOARD_WIDTH, Model.BOARD_HEIGHT);
        bezEwolucji.setLocationRelativeTo(null);
        bezEwolucji.setResizable(false);
        bezEwolucji.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Model.BOARD_WIDTH, Model.BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("TrafficLights");
        setResizable(false);
        setVisible(true);
    }


    public Widok getWidok(){
         return w;

    }
    public Widok getWidokBezSymulancji(){
        return widokBezSymulancji;

    }

    public void setHorizonalLines(int[] lines) {
        horizontal = lines;

    }

    public void setVerticalLines(int[] lines) {
        vertical = lines;

    }


}
