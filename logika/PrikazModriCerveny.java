/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
/*******************************************************************************
 * Instance třídy PrikazSeber představují příkazy pro sebrání věci z místnosti
 *
 * @author    Ksenia Pimkina
 * @version   1.1
 */
public class PrikazModriCerveny implements IPrikaz
{
    private static final String NAZEV = "tlacitko";
    private HerniPlan plan;
//    private Batoh batoh;

    /***************************************************************************
     *  Konstruktor
     */
    public PrikazModriCerveny(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     * Pokud je to možné sebere požadovanou věc a vrátí text o svém počínání
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat? Musíš zadat název věci ;)";
        }

        String nazevVybraneVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (aktualniProstor.obsahujeVec(nazevVybraneVeci)) {
            Vec vybranaVec = aktualniProstor.odeberVec(nazevVybraneVeci);
            if(vybranaVec == null) {
                return "Vyber si neco";
            }
            else {
                if(vybranaVec.getNazev() == "modre"){
                    return "Nyní můžete sebrat prsten_stesti";
                }
                else{
                    plan.setProhra(true);
                    return null;
                }
            }
        }else {
            return "Taková věc se tady nenachází :(";
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}

