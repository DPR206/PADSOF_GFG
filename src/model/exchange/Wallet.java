package model.exchange;

import model.product.SecondHandProduct;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * It implements the wallet
 * @author Ana O.R.
 * @version 1.2
 * @see SecondHandProduct
 */
public class Wallet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    /** The list of products that belong to this wallet */
    private HashMap<String, SecondHandProduct> products;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The wallet's constructor
     */
    public Wallet() {
        products = new HashMap<>();
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It allows a registered client to add products to his wallet
     * @param newProducts the desired products
     * @throws NullPointerException the products weren't provided
     */
    public void addProducts(SecondHandProduct... newProducts) throws NullPointerException {
        if (newProducts == null) {
            throw new NullPointerException("The products weren't provided");
        }

        for (SecondHandProduct newProduct : newProducts) {
            if (!this.products.containsKey(newProduct.getId())) {
                this.products.put(newProduct.getId(), newProduct);
            }
        }
    }

    /**
     * It allows a registered client to remove products from his wallet
     * @param products the list of products to be removed
     * @throws NullPointerException the products weren't provided
     */
    public void removeProducts(SecondHandProduct... products) throws NullPointerException {
        if (products == null) {
            throw new NullPointerException("The products weren't provided");
        }

        for (SecondHandProduct product : products) {
            this.products.remove(product.getId());
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    public List<SecondHandProduct> getAvailableProducts() {
        List<SecondHandProduct> allProducts =
                new ArrayList<>(List.of(products.values().toArray(new SecondHandProduct[0])));
        allProducts.removeIf(SecondHandProduct::isRemoved);
        allProducts.removeIf(product -> !product.isAvailable());
        return allProducts;
    }

    /**
     * Gets the wallet's products
     * @return the wallet's products
     */
    public List<SecondHandProduct> getProducts() {
        List<SecondHandProduct> allProducts =
                new ArrayList<>(List.of(products.values().toArray(new SecondHandProduct[0])));
        allProducts.removeIf(SecondHandProduct::isRemoved);
        return allProducts;
    }

    /**
     * It sets the wallet's products
     * @param newProducts the new products
     */
    public void setProducts(HashMap<String, SecondHandProduct> newProducts) {
        this.products = newProducts;
    }

    /**
     * Gets the wallet's visible products
     * @return the wallet's visible products
     */
    public List<SecondHandProduct> getVisibleProducts() {
        List<SecondHandProduct> allProducts =
                new ArrayList<>(List.of(products.values().toArray(new SecondHandProduct[0])));
        allProducts.removeIf(SecondHandProduct::isRemoved);
        allProducts.removeIf(product -> !product.isAvailable());
        return allProducts;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        return "Wallet";
    }

}