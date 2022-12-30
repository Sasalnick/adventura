package util;


 /**
 *  Rozhraní ObserverZmenyBatohu - pužíváno pro aktualizaci pozorovatele.
 *
 *@author     Ksenia Pimkina
 *@version    1.0
 *@created    ZS 2022
 */

public interface ObserverZmenyBatohu {

    /**
    * Metoda, ve které proběhne aktualizace pozorovatele,
    * je volána prostřednictvím metody upozorniPozorovatele z rozhraní SubjektZmenyProstoru
    * 
    */
    
    public void aktualizuj();
    
}
