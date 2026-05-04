package view.miniPanels;

import model.user.User;
import view.ImageAdder;

import javax.swing.*;
import java.awt.event.ActionListener;

public class UserMiniP extends JPanel {
    JButton button;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    UserMiniP(User user, String buttonName) {
        this.setLayout(new BoxLayout(null, BoxLayout.Y_AXIS));

        this.add(ImageAdder.getImageLabel(".\\resources\\default_user.png", 50, 50));
        this.add(new JLabel(user.getUserName()));
        button = new JButton(buttonName);
        this.add(button);
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        button.addActionListener(c);
    }
}