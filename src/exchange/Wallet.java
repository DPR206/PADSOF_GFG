package exchange;

import product.SecondHandProduct;

import java.util.HashMap;

/**
 * Class name: Wallet
 * <p>
 * Description: It implements the wallet
 * @author Ana O.R.
 * @version 1.1
 * @see SecondHandProduct
 */
public class Wallet {
    /* The list of products that belong to this wallet */
    private HashMap<Integer, SecondHandProduct> products;

    /**
     * The wallet's constructor
     */
    Wallet() {
        products = new HashMap<>();
    }

    /**
     * It allows for a registered client to have a wallet
     * @return the new wallet
     */
    private Wallet createWallet() {
        return new Wallet();
    }

    /**
     * It allows a registered client to add products to his wallet
     * @param newProducts the desired products
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
     */
    public void removeProducts(SecondHandProduct... products) throws NullPointerException {
        if (products == null) {
            throw new NullPointerException("The products weren't provided");
        }

        for (SecondHandProduct product : products) {
            this.products.remove(product.getId());
        }
    }
}