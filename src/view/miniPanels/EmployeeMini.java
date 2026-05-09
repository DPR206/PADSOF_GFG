package view.miniPanels;

import model.user.Employee;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

public class EmployeeMini extends MiniPanel {
    private final JButton gestionar = new JButton("Gestionar");
    private final Employee emp;
    private final JTextPane employeeInfo = new JTextPane();
    private final JLabel employeeIcon;

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public EmployeeMini(Employee emp, int index) throws BadLocationException {
        super();

        this.emp = emp;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        this.employeeIcon = getImageLabel(".\\resources\\app\\default_user.png", height, width);
        gestionar.setPreferredSize(new Dimension(125, height));
        gestionar.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\cart.png"), height / 4, height / 4));

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        employeeInfo.setCharacterAttributes(attributes, true);
        employeeInfo.setText(emp.getId() + "\n");

        attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);

        Document doc = employeeInfo.getStyledDocument();
        doc.insertString(doc.getLength(), // DUE Añadir descuento
                ("Name: " + this.emp.getUserName()), attributes);

        employeeInfo.setPreferredSize(new Dimension(width, height));

        JTextPane indexNum = new JTextPane();
        indexNum.setEditable(false);
        indexNum.setFocusable(false);

        SimpleAttributeSet attributes2 = new SimpleAttributeSet();
        StyleConstants.setBold(attributes2, true);
        indexNum.setCharacterAttributes(attributes2, true);
        indexNum.setText("\n" + index + ".");
        indexNum.setPreferredSize(new Dimension(25, height));

        this.add(indexNum);
        this.add(employeeIcon);
        this.add(employeeInfo);
        this.add(gestionar);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    public Employee getEmployee() {
        return this.emp;
    }

    public JTextPane getPackInfo() {
        return this.employeeInfo;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        gestionar.addActionListener(c);
    }
}