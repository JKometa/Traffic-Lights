package com.pszt.TrafficLights.view;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class Wykres extends ApplicationFrame {
     ChartPanel chartPanel;
     JFreeChart chart;
     double counter = 0;
    XYSeries bezEwolucji = new XYSeries("Bez ewolucji");
    XYSeries zEwolucja = new XYSeries("Z ewolucja");

    public Wykres(final String title) {

        super(title);

        final XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(bezEwolucji);
        dataset.addSeries(zEwolucja);
        chart = ChartFactory.createXYLineChart(
                "Sredni czas",          // chart title
                "Oczekiwanie",               // domain axis label
                "Czas",                  // range axis label
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,
                false
        );
          counter = 0;
        chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
        setContentPane(chartPanel);

    }


    public void update(double zEwolucja, double bezEwolucji){


        counter++;
        final XYSeriesCollection dataset = new XYSeriesCollection();
        this.bezEwolucji.add(counter, zEwolucja);
        this.zEwolucja.add(counter, bezEwolucji);
        dataset.addSeries(this.bezEwolucji);
        dataset.addSeries(this.zEwolucja);



        chart = ChartFactory.createXYLineChart(
                "Sredni czas",          // chart title
                "Category",               // domain axis label
                "Value",                  // range axis label
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final XYPlot plot = chart.getXYPlot();
        final NumberAxis domainAxis = new NumberAxis("t");
        final NumberAxis rangeAxis = new NumberAxis("Sredni czas");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
        chartPanel.setChart(chart);
        chart.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(Color.black);

    }

    }

