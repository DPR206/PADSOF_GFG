package main;

import java.io.IOException;

/**
 * The type Main app.
 */
public class MainApp {
    private static final MainLoop ml = MainLoop.getInstance();

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main() throws IOException, IllegalArgumentException, NullPointerException {
        try {
            ml.main();
        } catch (IOException io) {

        } catch (IllegalArgumentException ia) {

        } catch (NullPointerException np) {

        }
    }
}