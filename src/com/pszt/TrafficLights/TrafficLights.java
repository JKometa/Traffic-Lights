package com.pszt.TrafficLights;

import com.pszt.TrafficLights.Controller.Controller;
import com.pszt.TrafficLights.model.Car;
import com.pszt.TrafficLights.model.Model;
import com.pszt.TrafficLights.view.Widok;
import com.pszt.TrafficLights.view.WidokMain;

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
    WidokMain widokMain;
    Model model;
    public TrafficLights() {

        widokMain = new WidokMain();
        // @TODO eee po co tu się podaje te wartości dla modelu?

//        ArrayList<Car> cars =  model.getCars();
//        for (Car tmp : cars){
//             System.out.println(tmp.getPositionX() + " " + tmp.getPositionY());
//
//
//        }

    }
    public void init(){
        Controller kontroler = new Controller(widokMain);
        widokMain.setController(kontroler);
        //kontroler.setModel(model);
                                           widokMain.init();

    }


    public static void main(String[] args) {
        TrafficLights  tl = new TrafficLights();


        tl.init();

    }
}