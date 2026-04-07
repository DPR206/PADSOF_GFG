package main;

import java.io.IOException;

/**
 * The type Main app.
 */
public class MainApp {
    private static final MainLoop ml = MainLoop.getInstance();

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Main method for this class
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public static void main() throws IOException, IllegalArgumentException, NullPointerException {
        try {
            ml.main();
        } catch (IOException io) {
            throw new IOException(io.getMessage());
        } catch (IllegalArgumentException ia) {
            throw new IllegalArgumentException(ia.getMessage());
        } catch (NullPointerException np) {
            throw new NullPointerException(np.getMessage());
        }
    }
}