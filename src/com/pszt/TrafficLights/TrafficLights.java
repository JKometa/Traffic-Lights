package com.pszt.TrafficLights;

import com.pszt.TrafficLights.Controller.Controller;
import com.pszt.TrafficLights.model.Car;
import com.pszt.TrafficLights.model.Model;
import com.pszt.TrafficLights.view.Widok;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kometa
 * Date: 04.05.2013
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
public class TrafficLights extends JFrame {
    Widok w;
    Model model;
    public TrafficLights() {


        // @TODO eee po co tu się podaje te wartości dla modelu?
        model = new Model(0,0);
        ArrayList<Car> cars =  model.getCars();
        for (Car tmp : cars){
             System.out.println(tmp.getPositionX() + " " + tmp.getPositionY());


        }
        w = new Widok(model);
        add(w);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(model.getBounds().getSize());
        setLocationRelativeTo(null);
        setTitle("TrafficLights");
        setResizable(false);
        setVisible(true);
    }
    public void init(){
        Controller kontroler = new Controller(w);
        kontroler.setModel(model);
        kontroler.start();

    }

    public static void main(String[] args) {
        TrafficLights  tl = new TrafficLights();

        tl.init();

    }
}