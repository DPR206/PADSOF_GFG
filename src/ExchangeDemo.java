import store.*;

import java.time.*;
import java.util.*;

import exchange.*;
import product.*;
import user.*;

// NOTE: Técnicamente, usaría store como fachada / base de datos
// NOTE: Es sin buscador ni notificaciones por ahora

public class ExchangeDemo {
    
	public static void main(String[] args) {
		Store s = Store.getInstance();
		SecondHandProduct products1[] = new SecondHandProduct[] {};
		SecondHandProduct products2[] = new SecondHandProduct[] {};

	    /* s.addManager */
	    //Manager manager = Manager.getInstance();

	    /* s.addRegisteredClient */
	    RegisteredClient rc1 = new RegisteredClient("client1", "12345678A", "password");
	    System.out.println(rc1);
	    RegisteredClient rc2 = new RegisteredClient("client2", "12345678A", "password");
	    System.out.println(rc2);

	    /* s.addEmployee <- con exchangePermission */
	    Employee emp = new Employee("password", "exchangeEmployee", Permission.EXCHANGE);
	    System.out.println(emp);

	    /*----------------------------------------------- Inicio simulación ----------------------------------------------*/

	    /* Cliente 1 sube un producto a su cartera */
	    SecondHandProduct product1 = new SecondHandProduct("Funko", "Figura Aladin", "1234.jpg", 
	    													ProductType.FIGURINE, true, true, ConservationStatus.VERY_GOOD);
	    System.out.println(product1);
	    
	    rc1.addProductWallet(product1);
	    
	    /* Cliente 2 sube un producto a su cartera */
	    SecondHandProduct product2 = new SecondHandProduct("Figura acción", "Figura Spiderman", "1254.jpg", 
				ProductType.FIGURINE, true, true, ConservationStatus.SLIGHTLY_USED);
	    System.out.println(product2);
	    
	    rc2.addProductWallet(product2);
	    
	    Offer offer = new Offer(rc1, rc2, products1, products2); 
	    
	    products1 = offer.chooseMyProducts(product1);
	    products2 = offer.chooseTheirProducts(product2);
	   
	    offer.chooseMyProducts(products1);
	    offer.chooseTheirProducts(products2);
	    System.out.println(offer);
	    
	    Exchange exchange = offer.acceptOffer();
	    System.out.println(offer);
	    
	    rc1.getExchangeHistory().addExchange(exchange);
	    System.out.println(rc1.getExchangeHistory());
	    rc2.getExchangeHistory().addExchange(exchange);
	    System.out.println(rc2.getExchangeHistory());
	    
	    emp.manageExchange(exchange, true);
	    System.out.println(rc2.getExchangeHistory());
	}
    
    

    /* Cliente 1 "busca" productos de segunda mano y hace una oferta a cliente 2 */

    /* Cliente 2 la rechaza */

    /* Cliente 1 "busca" productos de segunda mano y hace una oferta a cliente 2 */

    /* Se pasa el tiempo de la oferta y caduca */

    /* Cliente 1 "busca" productos de segunda mano y hace una oferta a cliente 2 */

    /* Cliente 2 acepta la oferta -> nuevo intercambio disponible */

    /* Empleado de Intercambios lo gestiona (lo marca como intercambiado) */

    /*------------------------------------------------------ Fin -----------------------------------------------------*/
}