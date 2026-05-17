package view.miniPanels;

import model.user.Employee;

import javax.swing.*;
import java.awt.*;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

/**
 * The type Employee mini p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class EmployeeMiniP extends AbstractMiniP {

    private final Employee emp;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Employee mini p.
     * @param emp   the emp
     * @param index the index
     */
    public EmployeeMiniP(Employee emp, int index) {

        super();

        this.emp = emp;

        int width = 350;
        int height = 60;

        // PANEL
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));

        // ---------------- ICONO ----------------
        // IMPORTANTE: cuadrado
        JLabel employeeIcon = getImageLabel(".\\resources\\app\\default_user.png", 50, 50);

        // ---------------- INFO ----------------
        JTextPane employeeInfo = new JTextPane();
        employeeInfo.setEditable(false);
        employeeInfo.setFocusable(false);
        employeeInfo.setOpaque(false);
        employeeInfo.setContentType("text/html");

        employeeInfo.setText("<html><b>ID: " + emp.getId() + "</b><br>Name: " + emp.getUserName() + "</html>");

        employeeInfo.setPreferredSize(new Dimension(120, 50));

        // ---------------- ÍNDICE ----------------
        JTextPane indexNum = new JTextPane();

        indexNum.setEditable(false);
        indexNum.setFocusable(false);
        indexNum.setOpaque(false);
        indexNum.setContentType("text/html");

        indexNum.setText("<html><b>" + index + ".</b></html>");

        indexNum.setPreferredSize(new Dimension(25, 50));

        // ---------------- BOTÓN ----------------
        JButton gestionar = new JButton("Gestionar");
        gestionar.setPreferredSize(new Dimension(110, 45));

        gestionar.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\cart.png"), 20, 20));

        // ---------------- AÑADIR COMPONENTES ----------------
        this.add(indexNum);
        this.add(employeeIcon);
        this.add(employeeInfo);
        this.add(gestionar);

        // BORDE
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    /**
     * It gets the employee
     * @return the employee
     */
    public Employee getEmployee() {
        return this.emp;
    }
}