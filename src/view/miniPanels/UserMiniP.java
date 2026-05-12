package view.miniPanels;

import model.user.RegisteredClient;
import model.user.User;
import view.ImageAdder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static main.Main.brownColour;

public class UserMiniP extends AbstractMiniP {
    private final JButton button;
    private final JLabel image;
    private final User user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     * @param user       the user to be displayed
     * @param buttonName the name of the button for this panel (must match the controller's)
     */
    public UserMiniP(User user, String buttonName, String iconPath) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.user = user;

        JPanel imageAndName = new JPanel();
        image = ImageAdder.getImageLabel(iconPath, 50, 50);
        imageAndName.add(image);
        imageAndName.add(new JLabel(user.getUserName()));
        this.add(imageAndName);
        button = new JButton(buttonName);
        this.add(button);

        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, brownColour));
    }

    public JButton getButton() {
        return button;
    }

    public User getUser() {
        return this.user;
    }

    public Component getUserImage() {
        return image;
    }

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