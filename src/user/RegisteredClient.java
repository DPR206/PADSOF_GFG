/**
 *
 */
package user;

import order.Cart;

import java.time.LocalDate;

/**
 * Class name: RegisteredClient
 * <p>
 * Description: It implements the registered client
 * @author Duna P.R.
 * @version 1.0
 * @see User
 */
public class RegisteredClient extends User {
    private LocalDate registerDate;
    private String dni;
    private Cart c;
    //cartera
    //searcher
    //sugestioner
    //exchangehistory
    //orderhistory

    /**
     * Creates a new RegisteredClient
     * @param userName     the user's name
     * @param registerDate the registration date
     * @param dni          the user's dni
     * @param password     the user's password
     */
    public RegisteredClient(String userName, LocalDate registerDate, String dni, String password) {
        super(userName, password);
        this.registerDate = registerDate;
        this.dni = dni;
    }

    /**
     * Creates a new RegisteredCliente with a set registration date(today)
     * @param userName the user's name
     * @param dni      the user's dni
     * @param password the user's password
     */
    public RegisteredClient(String userName, String dni, String password) {
        this(userName, LocalDate.now(), dni, password);
    }

    /**
     * Obtains the client's registration date
     * @return the registerDate the registration date
     */
    public LocalDate getRegisterDate() {
        return registerDate;
    }

    /**
     * It sets the registration date
     * @param registerDate the registerDate to set
     */
    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * Obtains the client's dni
     * @return the dni the client's dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * It sets the dni
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * It changes the client's password
     * @param psswd the new password
     */
    public void changePassword(String psswd) {
        //Tengo que hacer la comprobación de que la contraseña es segura
        super.changePassword(psswd);
    }

    //public boolean buy() {}
    //removeFromWallet(Product)
    //requestValuation
    //makeAnOffer
    //browseNotifications
    //addCart
    //deleteCart

    @Override
    public String toString() {
        return super.toString();
    }
}