package controller.browserControllers;

import java.awt.Color;
import java.time.LocalDateTime;
import javax.swing.text.BadLocationException;

import controller.Controller;
import model.order.Order;
import model.order.OrderState;
import view.employeePanels.ManageIndividualOrderP;

public class ManageIndividualOrderC implements Controller{

	private final Order o;
	private final ManageIndividualOrderP panel;
	private final Color activeColor = new Color(76, 175, 80); // green
	private final Color defaultColor = new Color(30, 30, 30); // black

	public ManageIndividualOrderC(Order item, ManageIndividualOrderP toDo) {
		this.panel = toDo;
		this.o = item;
		try {
			initializeActions();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initializeActions() throws BadLocationException {
		new BrowseInOrderController(this.panel.getBrowser(), this.o);
		configureButtonActions();
		refreshView();
	}

	private void configureButtonActions() {
		panel.getPreparado().addActionListener(e -> {
			if (o.getState() == OrderState.PAID) {
				o.setState(OrderState.IN_PREPARATION);
				refreshView();
			}
		});
		panel.getList().addActionListener(e -> {
			if (o.getState() == OrderState.IN_PREPARATION) {
				o.setState(OrderState.READY_TO_PICKUP);
				refreshView();
			}
		});
		panel.getRecogido().addActionListener(e -> {
			if (o.getState() == OrderState.READY_TO_PICKUP) {
				o.setState(OrderState.PICKED_UP);
				o.setPickedUpDate(LocalDateTime.now());
				refreshView();
			}
		});
	}

	private void refreshView() {
		String stateText = switch (o.getState()) {
			case PAID -> "Pagado";
			case IN_PREPARATION -> "En preparación";
			case READY_TO_PICKUP -> "Listo para recoger";
			case PICKED_UP -> "Recogido";
		};
		panel.getState().setText("Estado del Pedido: " + stateText);

		boolean paid = o.getState() == OrderState.PAID;
		boolean preparation = o.getState() == OrderState.IN_PREPARATION;
		boolean ready = o.getState() == OrderState.READY_TO_PICKUP;
		boolean picked = o.getState() == OrderState.PICKED_UP;

		panel.getPreparado().setEnabled(paid);
		panel.getList().setEnabled(preparation);
		panel.getRecogido().setEnabled(ready);

		styleButton(panel.getPreparado(), paid, preparation || ready || picked);
		styleButton(panel.getList(), preparation, ready || picked);
		styleButton(panel.getRecogido(), ready, picked);
	}

	private void styleButton(javax.swing.JButton button, boolean isCurrentStep, boolean isCompleted) {
		if (isCompleted) {
			button.setBackground(activeColor);
			button.setForeground(Color.WHITE);
		} else {
			button.setBackground(defaultColor);
			button.setForeground(Color.WHITE);
		}
		button.setOpaque(true);
	}
}
