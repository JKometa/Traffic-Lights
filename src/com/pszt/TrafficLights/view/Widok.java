package com.pszt.TrafficLights.view;

import com.pszt.TrafficLights.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kometa
 * Date: 04.05.2013
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */
public class Widok extends JPanel {


    public ArrayList<Car> cars;

    public ArrayList<Crossroad> crossroads;

    public ArrayList<SpawnPoint> spawnPoints;

    private int[] horizontal;
    private int[] vertical;
    private ImageIcon lewo, prawo, gora, dol, limit;
    boolean modelPoprawiony = false;


    public Widok(Model m) {
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        crossroads = m.getCrossroads();
        spawnPoints = m.getSpawnPoints();
        cars = m.getCars();
        lewo = new ImageIcon(this.getClass().getResource("carLewo.png"));
        prawo = new ImageIcon(this.getClass().getResource("carPrawo.png"));
        gora = new ImageIcon(this.getClass().getResource("carGora.png"));
        dol = new ImageIcon(this.getClass().getResource("carDol.png"));
        limit  =                 new ImageIcon(this.getClass().getResource("limit.png"));



    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);

        for (int horizontalLine : horizontal) {
            g2d.fillRect(0, horizontalLine - Model.ROAD_WIDTH/2, Model.BOARD_WIDTH, Model.ROAD_WIDTH);
         //   g2d.drawLine(0, horizontalLine, 1000, horizontalLine);
        }
        for (int verticalLine : vertical) {
            g2d.fillRect(verticalLine - Model.ROAD_WIDTH/2, 0 , Model.ROAD_WIDTH, Model.BOARD_HEIGHT);
           // g2d.drawLine(verticalLine, 0, verticalLine, 500);
        }

        for (Crossroad tmp : crossroads) {

            Point cords = tmp.getPosition();
            g2d.setColor(Color.blue);

            g2d.drawRect(cords.x -20, cords.y-20, 40, 40);
            Point hPoint, vPoint, limitPoint, textPoint;
            hPoint = (Point)cords.clone();
            vPoint = (Point)cords.clone();
            limitPoint = (Point)cords.clone();
            textPoint  = (Point)cords.clone();
            TrafficLight.LightColor light = tmp.getTrafficLightHorizontal().getColor();
            if(tmp.isAscendingHorizontal()){
               hPoint.x -= 25;
               hPoint.y += 15;
               limitPoint.x += 25;
               limitPoint.y += 25;
               textPoint.x += 35;
               textPoint.y += 45;

            }else{
                hPoint.x += 15;
                hPoint.y -= 25;
                limitPoint.x -= 50;
                limitPoint.y -= 50;
                textPoint.x -= 40;
                textPoint.y -= 30;

            }
            g2d.drawImage(limit.getImage(), limitPoint.x, limitPoint.y, 30, 30, this);
            g2d.drawString(String.valueOf(tmp.getIdSpeed()), textPoint.x, textPoint.y);

            limitPoint = (Point)cords.clone();
            textPoint  = (Point)cords.clone();
            if(tmp.isAscendingVertical()){
                vPoint.x -= 25;
                vPoint.y -= 25;
                limitPoint.x -= 50;
                limitPoint.y += 25;
                textPoint.x -= 40;
                textPoint.y += 45;


            }else{
                vPoint.x += 15;
                vPoint.y += 15;
                limitPoint.x += 25;
                limitPoint.y -= 50;
                textPoint.x += 35;
                textPoint.y -= 30;

            }



            g2d.drawImage(limit.getImage(), limitPoint.x, limitPoint.y, 30, 30, this);
            g2d.drawString(String.valueOf(tmp.getIdSpeed()), textPoint.x, textPoint.y);
            drawLight(g2d, tmp.getTrafficLightHorizontal().getColor(),hPoint);
            drawLight(g2d, tmp.getTrafficLightVertical().getColor(),vPoint);




        }


        for (Car tmp : cars) {
            g2d.setColor(Color.ORANGE);
            g2d.draw(tmp.getHitBox());
          //  g2d.setColor(Color.green);
          //  g2d.fill(tmp.getBounds());
            if(tmp.isHorizontal() && tmp.isAscending())
                g2d.drawImage(this.prawo.getImage(), tmp.getBounds().x, tmp.getBounds().y, 30, 10, this);
            else if(tmp.isHorizontal() && !tmp.isAscending())
                g2d.drawImage(this.lewo.getImage(), tmp.getBounds().x, tmp.getBounds().y, 30, 10, this);
            else if(!tmp.isHorizontal() && tmp.isAscending())
                g2d.drawImage(this.dol.getImage(), tmp.getBounds().x, tmp.getBounds().y, 10, 30, this);
            else if(!tmp.isHorizontal() && !tmp.isAscending())
                g2d.drawImage(this.gora.getImage(), tmp.getBounds().x, tmp.getBounds().y, 10, 30, this);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    public void maziaj(ArrayList<Car> kary, ArrayList<Crossroad> krosorldy) {
        cars = kary;
        crossroads = krosorldy;
       // spawnPoints = spalny;
        repaint();
    }

    public void setHorizonalLines(int[] lines) {
        horizontal = lines;

    }

    public void setVerticalLines(int[] lines) {
        vertical = lines;

    }

    private void drawLight(Graphics2D g2d, TrafficLight.LightColor light, Point p){

        if (light == TrafficLight.LightColor.GREEN)
            g2d.setColor(Color.green);
        else if (light == TrafficLight.LightColor.RED)
            g2d.setColor(Color.red);
        else
            g2d.setColor(Color.yellow);
        g2d.fillRect(p.x, p.y, 10, 10);
    }

}