package main;

import java.io.IOException;

public class RegisteredClientLoop extends Loop {
    /** This loop's instance */
    private static RegisteredClientLoop INSTANCE;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Registered client loop's constructor
     */
    RegisteredClientLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Registered client loop's instance
     * @return the Registered client loop's instance
     */
    protected static RegisteredClientLoop getInstance() {
        if (RegisteredClientLoop.INSTANCE == null) {
            RegisteredClientLoop.INSTANCE = new RegisteredClientLoop();
        }
        return INSTANCE;
    }

    /**
     * The registered client's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    void registeredClientLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- registeredClientLoop ---- \n"); // Es para debug, borrar
        // DUE
    }
}