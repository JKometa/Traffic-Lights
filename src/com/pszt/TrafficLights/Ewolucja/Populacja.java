package com.pszt.TrafficLights.Ewolucja;

import com.pszt.TrafficLights.Controller.Controller;
import com.pszt.TrafficLights.model.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Created with IntelliJ IDEA.
 * User: marcin
 * Date: 02.05.13
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public class Populacja implements  Runnable {
    /**
     * kopia modelu na której będzie badan populacja
     */
    Model model;

    Controller controller;

    Thread watek;

    /**
     * minimalna wartość przy losowaniu rodzicow
     */
     final long MIN_LOSUJ = 300;

    /**
     * maksymalna wartość przy losowaniu rodziców
     */
     final long MAX_LOSUJ = 5000;

    /**
     * generator do losowania licz i rozkładu
     */
    private Random generator;

    /**
     * lista z osobnikami należącymi do populacji
     */
    private ArrayList <Osobnik> osobnicy;

    /**
     * sekwencja rodzicow do rozmnażania
     */
    private ArrayList< Osobnik > sekwencjaRodzicow;

    /**
     * zarodki powstałe z krzyżowania
     */
    private ArrayList< Osobnik > zarodki;


    /**
     * ilość osobników w populacji
     */
    static private final int iloscOsobnikow = 10;


    /**
     * ilosc pokoleń
     */
    private int iloscPokolen;

    /**
     * ilość cech każdego osobnika
     */
    private int iloscCech;

    /**
     * najlepszy osobnik w pokoleniu
     */
    private Osobnik najlepszy;

    private double tau;
    private double tau2;

    public enum Wybor{
        NAJLEPSZY, RULETKA, RANKING;
    }

    Wybor wybor;

    public Populacja(Controller controller, Wybor wybor) {
        this.wybor = wybor;
        this.controller = controller;
        try {
            this.model = (Model)controller.getCopyModel().clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        this.iloscCech = model.getNumberOfCrossroads() * 2;
        tau = 1 / (Math.sqrt(2 * Math.sqrt(iloscOsobnikow)));
        tau2 = 1 / (Math.sqrt(2 * iloscOsobnikow));
        generator = new Random();

        osobnicy = new ArrayList<Osobnik>();
        sekwencjaRodzicow = new ArrayList<Osobnik>();
        zarodki = new ArrayList<Osobnik>();

    }

    /**
     * losuje pierwsze pokolenie rodzicow
     */
    private void generujRodzicow(){
        for(int i = 0; i < iloscOsobnikow; ++i){
            Osobnik nowyRodzic = new Osobnik(iloscCech);

            long[] cechyNowegoRodzica = nowyRodzic.getCechy();
            double[] rozkladyNowegoOsobnika = nowyRodzic.getRozklady();
            for (int j = 0; j < iloscCech; ++j){
                cechyNowegoRodzica[j] = losuj();
                rozkladyNowegoOsobnika[j] = generator.nextGaussian();
//                System.out.println("rozklad: " + rozkladyNowegoOsobnika[j]);


            }

            osobnicy.add(nowyRodzic);
        }

        ocenOsobnikow(osobnicy);
        KomparatorOsobnikow komparator = new KomparatorOsobnikow();
        Collections.sort(osobnicy, komparator);

    }

    /**
     * losuje sekwencje rodzicow do krzyżowania
     */
    private void tworzSekwencje(){
        int indeks;
        int warunek = 2 * iloscOsobnikow;

        for (int i = 0;i < warunek; ++i){
            indeks = (int)losuj(0, iloscOsobnikow);

//            System.out.println("Indeks: " + indeks);

            sekwencjaRodzicow.add(osobnicy.get(indeks));
        }
    }

    /**
     * krzyżuje osobników wg sekwencji rodzicow
     */
    private void krzyzuj(){
        int warunek = sekwencjaRodzicow.size() - 1;
        for (int i = 0; i < warunek; ++i){
            zarodki.add(new Osobnik (sekwencjaRodzicow.get(i), sekwencjaRodzicow.get(i+1)));

        }
    }

    /**
     * mutuje wszystkie zarodki
     */
    private void mutuj(){
        for(Osobnik zarodek : zarodki){
            zarodek.mutuj(tau, tau2, generator);
        }
    }

    /**
     * Bada tablice osobnikow i wszystkim odpowiednio ustawia parametr wynik
     * @param celOceny tablica osobnikow których trzeba ocenić
     */
    private void ocenOsobnikow(ArrayList <Osobnik> celOceny){
        int ilosc = celOceny.size();
        Badanie badania[] = new Badanie[ilosc];
        for(int i = 0 ; i < ilosc ; ++i){
            Model kopia;
            try {
                kopia = (Model) model.clone();
            } catch (CloneNotSupportedException e) {
                kopia = null;
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            kopia.setIntervalsOnCrossroads(celOceny.get(i).getCechy());
            badania[i] = new Badanie(kopia);
            badania[i].start();
        }

        for(int i = 0 ; i < ilosc ; ++i){
            while (!badania[i].isBadanieZakonczone()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
            celOceny.get(i).setWynik(badania[i].getWynik());
        }

    }

    /**
     * wybiera następne pokolenie rodziców z rodziców + potomkowie wg rankingu wybiera najlepszych
     */
    public void wybierzNajlepszych(){
        ArrayList< Osobnik > tmp = new ArrayList<Osobnik>();

        for (Osobnik rodzic : osobnicy){
            tmp.add(rodzic);
        }

        for (Osobnik potomek : zarodki){
            tmp.add(potomek);
        }

        System.out.println("-----------------------------------------------------------   KURWA "  + tmp.size());
        KomparatorOsobnikow komparator = new KomparatorOsobnikow();
        Collections.sort(tmp, komparator);
        osobnicy.clear();



        System.out.println("-----------------------------------------------------------");
        for (int i = 0; i < iloscOsobnikow; ++i){
            osobnicy.add(tmp.get(i));
        }

        najlepszy = osobnicy.get(0);

        for(Osobnik x : osobnicy){
            System.out.println("Wynik: " + x.getWynik());
        }

    }

    /**
     * wybiera następne pokolenie rodziców z rodziców + potomkowie wg metody ruletki -> patrz skrypt
     */
    public void wybierzRuletka(){
        ArrayList< Osobnik > tmp = new ArrayList<Osobnik>();

        for (Osobnik rodzic : osobnicy){
            tmp.add(rodzic);
        }

        for (Osobnik potomek : zarodki){
            tmp.add(potomek);
        }

        ArrayList< Osobnik > tablicaLosujaca = new ArrayList<Osobnik>();

        for (Osobnik osobnik : tmp){
            int priorytet = 10 - ((int)osobnik.getWynik() / 1000);
            if (priorytet <= 0)
                priorytet = 0;

            for (int i = 0; i <= priorytet; ++i){
                tablicaLosujaca.add(osobnik);
            }

        }
        osobnicy.clear();
        while(osobnicy.size() != iloscOsobnikow){
            int losuj = new Random().nextInt(tablicaLosujaca.size())  ;
            Osobnik wylosowany =  tablicaLosujaca.get(losuj);
            osobnicy.add(wylosowany);
            while(tablicaLosujaca.remove(wylosowany)) ;
        }

        KomparatorOsobnikow komparator = new KomparatorOsobnikow();
        Collections.sort(osobnicy, komparator);

       najlepszy = osobnicy.get(0);

    }


    /**
     * wybiera następne pokolenie rodziców z rodziców + potomkowie wg metody rankingowej -> patrz skrypt
     */
    public void wybierzRanking(){
        int prawdo;
        Random generator = new Random();
        ArrayList< Osobnik > tmp = new ArrayList<Osobnik>();

        for (Osobnik rodzic : osobnicy){
            tmp.add(rodzic);
        }

        for (Osobnik potomek : zarodki){
            tmp.add(potomek);
        }

        KomparatorOsobnikow komparator = new KomparatorOsobnikow();
        Collections.sort(tmp, komparator);
        osobnicy.clear();

        for (int i = 0; i < iloscOsobnikow; ++i){
            prawdo = generator.nextInt(435);
            osobnicy.add(chooseRandomRank(prawdo, tmp));

        }
        tmp = osobnicy;
        Collections.sort(tmp, komparator);
        najlepszy = tmp.get(0);

        for(Osobnik x : osobnicy){
            System.out.println("Wynik: " + x.getWynik());
        }

    }

    private Osobnik chooseRandomRank(int prawdo, ArrayList<Osobnik> tmp) {
        int add = 29, index =0;

        for(int i = 0;i <= 435;){
           if(prawdo >= i && prawdo <= i+add && !checkIfExists(tmp.get(index)))
               return tmp.get(index);
            i += add;
            add--;
            index++;
        }


        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    private boolean checkIfExists(Osobnik osobnik) {
        for(Osobnik o: osobnicy){
            if(o.equals(osobnik))
                return true;
        }

        return false;
    }


    /**
     * zwraca tablice najlepszych okresów obecnego pokolenia
     * @return tablice okresów od najlepszego osobnika
     */
    public long[] getNajlepszeOkresy(){

        return najlepszy.getCechy();
    }

    public int getIloscPokolen() {
        return iloscPokolen;
    }

    @Override
    public void run() {
       generujRodzicow();

        while(true){
            zarodki.clear();
            sekwencjaRodzicow.clear();

            tworzSekwencje();
            krzyzuj();
            mutuj();
            ocenOsobnikow(zarodki);

            switch (wybor){
                case NAJLEPSZY:
                    wybierzNajlepszych();
                    break;

                case RULETKA:
                      wybierzRuletka();
                    break;

                case RANKING:
                      wybierzRanking();
                     break;
            }




            ++iloscPokolen;

            try {
                model = (Model)controller.getCopyModel().clone();
                ocenOsobnikow(osobnicy);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * startuje ewolucje
     */
    public void start(){
        watek = new Thread(this);
        watek.start();
    }

    private long losuj(){
        long los =  losuj(MIN_LOSUJ, MAX_LOSUJ);
//        System.out.println("wylosowano: " + los);

        return los;
    }

    private long losuj(long min, long max){
        long los =  (Math.abs(generator.nextLong())) % (max - min) + min;
//        System.out.println("wylosowano: " + los);
        return los;
    }
}
