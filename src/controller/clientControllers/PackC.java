package controller.clientControllers;

import controller.Controller;
import model.product.Pack;
import model.product.StoreProduct;
import model.user.*;
import view.App;
import view.clientPanels.PackP;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * The type Pack c.
 * @author Duna P.R.
 * @version 1.0
 */
public class PackC implements Controller {

    private final App frame;
    private final PackP view;
    private final Pack pack;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Pack c.
     * @param frame the frame
     * @param view  the view
     * @param pack  the pack
     */
    public PackC(App frame, PackP view, Pack pack) {
        this.frame = frame;
        this.view = view;
        this.pack = pack;

        initializeActions();
    }

    @Override
    public void initializeActions() {

        this.view.setName(pack.getId());
        this.view.setPrice(pack.getPrice());
        this.view.setImage(pack.getPhoto());

        int stockReal = calcularStockDisponiblePack(pack);
        this.view.setStock(stockReal);
        this.view.setMaxStock(stockReal);

        ArrayList<StoreProduct> products = pack.getProducts();
        if (products != null && !products.isEmpty()) {
            this.view.setProductsInPack(products);
        }

        HashSet<Pack> packs = pack.getPacks();
        if (packs != null && !packs.isEmpty()) {
            this.view.setPackssInPack(packs);
        }

        this.view.getBtnReturn().addActionListener(e -> {
            frame.goBack();
        });

        this.view.getBtnaddCart().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).getC().addPackUds(pack, (int) view.getUnitSpinner().getValue());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart()
                                                      .addPackUds(pack, (int) view.getUnitSpinner().getValue());
            }
            JOptionPane.showMessageDialog(frame, pack.getId() + " was added to Cart", "Added To Cart",
                    JOptionPane.INFORMATION_MESSAGE);
            updateInterface();
        });
    }

    /**
     * Update interface.
     */
    public void updateInterface() {
        PackP packVista = new PackP();
        new PackC(frame, packVista, pack);
        frame.addCard(packVista, "PACK");
        frame.changeVisibleCard("PACK");
    }

    /**
     * It calculates a pack's available stock
     * @param p the desired pack
     * @return the pack's available stock
     */
    private int calcularStockDisponiblePack(Pack p) {
        int stockMinimo = Integer.MAX_VALUE;

        if (p.getProducts() != null) {
            for (StoreProduct prod : p.getProducts()) {
                if (prod.getStock() < stockMinimo) {
                    stockMinimo = prod.getStock();
                }
            }
        }

        if (p.getPacks() != null) {
            for (Pack subPack : p.getPacks()) {
                int stockSubPack = calcularStockDisponiblePack(subPack);
                if (stockSubPack < stockMinimo) {
                    stockMinimo = stockSubPack;
                }
            }
        }

        return (stockMinimo == Integer.MAX_VALUE) ? 0 : stockMinimo;
    }
}