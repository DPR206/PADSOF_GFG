import store.*;
import exchange.*;
import product.*;
import user.*;

// NOTE: Técnicamente, usaría store como fachada / base de datos
// NOTE: Es sin buscador ni notificaciones por ahora

public class ExchangeDemo {
    Store s = Store.getInstance();

    /* s.addManager */
    Manager manager = Manager.getInstance();

    /* s.addRegisteredClient */
    RegisteredClient rc1 = new RegisteredClient("client1", "12345678A", "password");
    RegisteredClient rc2 = new RegisteredClient("client2", "12345678A", "password");

    /* s.addEmployee <- con exchangePermission */
    Employee emp = new Employee("password", "exchangeEmployee", Permission.EXCHANGE);

    /*----------------------------------------------- Inicio simulación ----------------------------------------------*/

    /* Cliente 1 sube un producto a su cartera */
    

    /* Cliente 2 sube un producto a su cartera */

    /* Cliente 1 "busca" productos de segunda mano y hace una oferta a cliente 2 */

    /* Cliente 2 la rechaza */

    /* Cliente 1 "busca" productos de segunda mano y hace una oferta a cliente 2 */

    /* Se pasa el tiempo de la oferta y caduca */

    /* Cliente 1 "busca" productos de segunda mano y hace una oferta a cliente 2 */

    /* Cliente 2 acepta la oferta -> nuevo intercambio disponible */

    /* Empleado de Intercambios lo gestiona (lo marca como intercambiado) */

    /*------------------------------------------------------ Fin -----------------------------------------------------*/
}