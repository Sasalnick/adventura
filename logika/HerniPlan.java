package logika;
import java.util.*;

import util.ObserverZmenyProstoru;
import util.SubjektZmenyProstoru;

import java.util.ArrayList;
import java.util.List;
/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Ksenia Pimkina
 *@version    1.00
 */
public class HerniPlan implements SubjektZmenyProstoru {    
    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Batoh batoh;
    private boolean prohraStav = false;
    private String vypis="";
    private List<ObserverZmenyProstoru> seznamPozorovatelu;
    
    /**
    *   Konstruktor
    */
    public HerniPlan() {
        
        zalozProstoryHry();
        batoh = new Batoh();
        seznamPozorovatelu = new ArrayList<>();
    }

    public Batoh getBatoh() {
        return batoh;
    }
    
    /**
    *  Vytváří jednotlivé prostory a propojuje je pomocí východů. Vytváří věci.
    */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor Start = new Prostor("Start"," ", 20.0, 40.0);
        Prostor Zacarovany_les = new Prostor("Zacarovany_les","Začarovanem lese, musíš dostat prsten přírody ", 200.0, 40.0);
        Prostor Magicka_chodba = new Prostor("Magicka_chodba","Magické chodbě, najdi prsten štěstí", 120.0, 300.0);
        Prostor Kvetinova_zahrada = new Prostor("Kvetinova_zahrada", "Květinové zahradě, tady musíš mít štěstí, abys dostala další prsten", 420.0, 300.0);
        Prostor Hory = new Prostor("Hory","horech, tady na vás čekají zkoušky, ale musíte je projít, abyste šli dál", 420, 70);
        Prostor Skola_kouzel = new Prostor("Skola_kouzel","Škole kouzele, potřebujete získat prsten mysli ", 600, 300);
        Prostor Hrad = new Prostor("Hrad","hradě, zbývá jen málo ...", 650, 80);

        // přiřazují se průchody mezi prostory (sousedící prostory)

        Start.setVychod(Zacarovany_les);
        Zacarovany_les.setVychod(Magicka_chodba);
        Magicka_chodba.setVychod(Kvetinova_zahrada);
        Magicka_chodba.setVychod(Zacarovany_les);
        Kvetinova_zahrada.setVychod(Magicka_chodba);
        Kvetinova_zahrada.setVychod(Hory);
        Hory.setVychod(Kvetinova_zahrada);
        Hory.setVychod(Skola_kouzel);
        Skola_kouzel.setVychod(Hory);
        Skola_kouzel.setVychod(Hrad);

        //tvorba věcí a jejich umístění do prostorů
        Vec prsten_prirody = new Vec("prsten_prirody", true, true);
        Vec prsten_stesti = new Vec("prsten_stesti", true, true);
        Vec prsten_casu = new Vec("prsten_casu", true, true);
        Vec prsten_mysli = new Vec("prsten_mysli", true, true);
        Vec modre = new Vec("modre", true, true);
        Vec cervene = new Vec("cervene", true, true);
        Vec matematik = new Vec("matematik", true, true);

        Zacarovany_les.vlozVec(prsten_prirody);
        Magicka_chodba.vlozVec(prsten_stesti);
        Magicka_chodba.vlozVec(modre);
        Magicka_chodba.vlozVec(cervene);
        Kvetinova_zahrada.vlozVec(prsten_casu);
        Skola_kouzel.vlozVec(prsten_mysli);
        Skola_kouzel.vlozVec(matematik);


        aktualniProstor = Start;  // hra začíná v Roklince
        viteznyProstor = Hrad; //hra končí v Hoře osudu
    }
    

    
    /**
     * Metoda obsahuje příběh, který se přidává k vypsanému textu v určitých místnostech, je využívána příkazem jdi, zároveň definuje špatné konce hry
     */
    public String pribeh(){

        if(getAktualniProstor().getNazev().equals("Start")){
            vypis = "Jdi dál!";

        }
        else if(getAktualniProstor().getNazev().equals("Zacarovany_les")) {
            Random rand = new Random();
            int upperbound = 100;
            int ran = rand.nextInt(upperbound);
            System.out.println(ran);
            if (ran > 90)
            {
                setProhra(true);
                return "\n" + "prohral jsi \n" +
                        "protože ses nemohl dostat z labyrintu. \n";
            }
            vypis = "\n" + "podařilo se ti projít bludištěm \n" +
                 "tady muzes sebrat prsten_prirody ";

        }
        else if(getAktualniProstor().getNazev().equals("Magicka_chodba")) {
            vypis = "\n" +
                    "Musíte vybrat správné tlačítko \n" +
                    "abyste získali důvtipný prsten.\n" +
                    "Můžete si vybrat modré nebo červené tlačítko.\n" +
                    "\n napiš 'tlacitko' a pak napište 'cervene' nebo 'modre'";

        }
        else if(getAktualniProstor().getNazev().equals("Kvetinova_zahrada")) {
            vypis = "\n" +
                    "tady mužeš sebrát prsten_casu";
        }
        else if(getAktualniProstor().getNazev().equals("Hory")) {
            vypis = "\n" +
                    "Použijte prsten času, jinak zamrzneš \n" +
                    "aby použit prsten napiš 'pouzij' a nazev prstenu \n" +
                    "pokud se rozhodnete jít dál, \n" +
                    "budete mít velkou šanci zemřet!";
        }
        else if(getAktualniProstor().getNazev().equals("Skola_kouzel")) {
            Random rand = new Random();
            int upperbound = 100;
            int ran = rand.nextInt(upperbound);
            if (ran > 60)
            {
                if(getBatoh().obsahBatohu().contains("prsten_casu")){
                    vypis ="musel si pouzit prsten";
                    setProhra(true);
                }
            }
            vypis = "\n" +
                    "Musíte vyřešit obtížný úkol, který není ve vašich silách.\n" +
                    "K tomu musíte najít kapesního matematika.\n" +
                    "\n K tomu použijte příkaz: seber matematik\n" +
                    "\n A teď můžete sebrát prsten_mysli \n" +
                    "seber prsten_mysli\n";



        }
        else if(getAktualniProstor().getNazev().equals("Hrad")) {
            if(getBatoh().obsahBatohu().contains("matematik")){
                vypis = "\n" + "Jsi v závěrečné fázi. Musíte porazit zlé duchy,\n" +
                        "k tomu potřebujete všechny své prsteny k vítězství";
            }else{
                vypis ="musel si zavolat matematika";
                setProhra(true);
            }

        }
         return vypis;
    }

    /**
     * Ověřuje, zda jsou splněny podmínky výhry
     */
    public boolean jeVyhra() {
        if (aktualniProstor == viteznyProstor) {
            if (getBatoh().neniPlny() == false) {
                return true;
            } else {
                setProhra(true);
            }
        }
            return false;
    }
    
    /**
     * Nastavuje stav prohry, využívá příběh
     */
    public void setProhra(boolean prohraStav){
        this.prohraStav = prohraStav;
    }
    
    /**
     * Oznamuje zda nastala prohra, defaultně vrací false
     */
    public boolean jeProhra(){
        return prohraStav;
    }
    
    /**
     * Získá odkaz na místnost v které se hráč momentálně nachází
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     * Nastavuje aktuální prostor, využívá příkaz jdi
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       upozorniPozorovatele();
    }
    
    /**
     * Metoda přiřadí pozorovatele
     */
    
    public void zaregistrujPozorovatele(ObserverZmenyProstoru pozorovatel)
      {
        seznamPozorovatelu.add(pozorovatel);
      }
    
    /**
     * Opak předchozí metody
     */
    
    public void odregistrujPozorovatele(ObserverZmenyProstoru pozorovatel)
      {
        seznamPozorovatelu.remove(pozorovatel);
      }
    
    /**
     * Předá pozorovateli aktuální data
     */
    
    public void upozorniPozorovatele()
      {
        for (ObserverZmenyProstoru pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
      }
}
