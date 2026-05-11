package controller.managerControllers;

import controller.Controller;
import model.store.Store;
import model.user.Employee;
import model.user.Permission;
import view.App;
import view.managerPanels.ManagerGestionarEmpleados;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerGestionarEmpleadosC implements Controller {
    private App frame;
    private ManagerGestionarEmpleados gest;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerGestionarEmpleadosC(ManagerGestionarEmpleados gest, App frame) {
        this.frame = frame;
        this.gest = gest;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        System.out.println(Store.getInstance().getEmployeeList());
        gest.getConfirmar().addActionListener(e -> {
            String userName = gest.getUserName().getText();
            char[] pwd = gest.getPwd().getPassword();

            if (userName.isBlank() || pwd.length == 0) {
                return; //si están vacíos, no hacen nada
            }
            String password = new String(pwd);

            List<Permission> perms = new ArrayList<>();

            if (gest.getStoreP().isSelected()) {
                perms.add(Permission.STORE);
            }
            if (gest.getOrderP().isSelected()) {
                perms.add(Permission.ORDER);
            }
            if (gest.getExchangeP().isSelected()) {
                perms.add(Permission.EXCHANGE);
            }

            Permission[] p = perms.toArray(new Permission[0]);

            Employee emp = new Employee(password, userName, false, p);
            System.out.println(emp.getId());
            JOptionPane.showMessageDialog(frame, gest, "CREADO CORRECTAMENTE", JOptionPane.PLAIN_MESSAGE);

            Store.getInstance().addEmployee(emp);
            gest.refresh();
            System.out.println(emp);
            System.out.println(Store.getInstance().getEmployeeList());
        });
    }
}