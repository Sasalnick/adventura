package util;

 /**
 *  Rozhraní ObserverZmenyProstoru - pužíváno pro aktualizaci pozorovatele.
 *
 *@author     Ksenia Pimkina
 *@version    1.0
 *@created    ZS 2022
 */

public interface ObserverZmenyProstoru {
    
    /**
    * Metoda, ve které proběhne aktualizace pozorovatele,
    * je volána prostřednictvím metody upozorniPozorovatele z rozhraní SubjektZmenyProstoru
    * 
    * @param aktualniProstor
    */
    
    public void aktualizuj();

}