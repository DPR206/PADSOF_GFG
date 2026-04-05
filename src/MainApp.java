import java.io.IOException;

/**
 * The type Main app.
 */
public class MainApp {
    private static MainLoop ml = new MainLoop();

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main() {
        try {
            ml.main();
        } catch (IOException io) {

        } catch (IllegalArgumentException ia) {

        } catch (NullPointerException np) {

        }
    }
}