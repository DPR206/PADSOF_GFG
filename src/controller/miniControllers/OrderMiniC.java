package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractBrowserC;
import model.order.Order;
import model.order.OrderState;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractBrowserP;
import view.miniPanels.OrderMini;

import java.util.List;

/**
 * The type Order mini c.
 * @author Duna P.R.
 * @version 1.0
 */
public class OrderMiniC implements Controller {

    private final OrderMini view;
    private final App frame;
    private final Store model;
    private final Order order;
    private final AbstractBrowserC<Order> abstractBrowserC;
    private final AbstractBrowserP<Order> abstractBrowserP;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Order mini c.
     * @param view             the view
     * @param frame            the frame
     * @param model            the model
     * @param order            the order
     * @param abstractBrowserC the abstract browser c
     * @param abstractBrowserP the abstract browser p
     */
    public OrderMiniC(OrderMini view, App frame, Store model, Order order, AbstractBrowserC<Order> abstractBrowserC,
                      AbstractBrowserP<Order> abstractBrowserP) {
        this.view = view;
        this.frame = frame;
        this.model = model;
        this.order = order;
        this.abstractBrowserC = abstractBrowserC;
        this.abstractBrowserP = abstractBrowserP;

        initializeActions();
    }

    @Override
    public void initializeActions() {

        cargarProductos();

        view.setOrderState(order.getState().getString());
    }

    private void cargarProductos() {
        StringBuilder sb = new StringBuilder();
        List<StoreProduct> productos = order.getSp();
        List<Pack> packs = order.getP();

        if (productos != null && !productos.isEmpty()) {
            for (int i = 0; i < productos.size(); i++) {
                sb.append(productos.get(i).getName());
                if (i < productos.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        if (sb.length() > 0 && packs != null && !packs.isEmpty()) {
            sb.append(", ");
        }

        if (packs != null && !packs.isEmpty()) {
            for (int i = 0; i < packs.size(); i++) {
                sb.append("Pack " + (i + 1));
                if (i < packs.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        view.setOrderDetails(sb.toString(), order.getPrice());
    }

}