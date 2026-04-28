package model.app;

import model.utilities.exceptions.*;

import java.io.IOException;

/**
 * It implements the main app
 * @author Sofía C.L.
 * @version 1.0
 */
public class MainApp {
    private static final MainLoop ml = MainLoop.getInstance();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Private constructor
     */
    private MainApp() {
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * main.Main method for this class
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public static void main(String[] args) throws IOException, IllegalArgumentException, NullPointerException {
        try {
            ml.main();
        } catch (IOException io) {
            throw new IOException(io.getMessage());
        } catch (IllegalArgumentException ia) {
            throw new IllegalArgumentException(ia.getMessage());
        } catch (NullPointerException np) {
            throw new NullPointerException(np.getMessage());
        } catch (InvalidId | UsernameTaken | PasswordNotValid e) {
            throw new RuntimeException(e);
        }
    }
}