package view.miniPanels;

import model.user.RegisteredClient;
import model.user.User;
import view.ImageAdder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static main.Main.brownColour;

/**
 * The type User mini p.
 * @author Ana O.R.
 * @version 1.0
 */
public class UserMiniP extends AbstractMiniP {
    private final JButton button;
    private final JLabel image;
    private final User user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     * @param user       the user to be displayed
     * @param buttonName the name of the button for this panel (must match the controller's)
     * @param iconPath   the icon path
     */
    public UserMiniP(User user, String buttonName, String iconPath) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.user = user;

        JPanel imageAndName = new JPanel();
        image = ImageAdder.getImageLabel(iconPath, 50, 50);
        imageAndName.add(image);
        imageAndName.add(new JLabel(user.getUserName()));
        this.add(imageAndName);
        this.button = new JButton(buttonName);
        this.add(button);

        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, brownColour));
    }

    /**
     * It gets the button
     * @return the button
     */
    public JButton getButton() {
        return this.button;
    }

    /**
     * It gets the user
     * @return the user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * It gets the user image
     * @return the user image
     */
    public Component getUserImage() {
        return image;
    }

    /**
     * It gets the wallet owner
     * @return the wallet owner
     */
    public RegisteredClient getWalletOwner() {
        return (RegisteredClient) user;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        button.addActionListener(c);
    }
}