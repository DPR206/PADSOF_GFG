package view.miniPanels;

import model.user.User;
import view.ImageAdder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserMiniP extends JPanel {
    JButton button;
    JLabel image;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     * @param user       the user to be displayed
     * @param buttonName the name of the button for this panel (must match the controller's)
     */
    public UserMiniP(User user, String buttonName) {
        this.setLayout(new BoxLayout(null, BoxLayout.Y_AXIS));

        image = ImageAdder.getImageLabel(".\\resources\\app\\default_user.png", 50, 50);
        this.add(image);
        this.add(new JLabel(user.getUserName()));
        button = new JButton(buttonName);
        this.add(button);
    }

    public Component getUserImage() {
        return image;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        button.addActionListener(c);
    }
}