package store;

import exchange.Exchange;
import order.*;
import product.*;
import user.User;

import java.io.*;
import java.util.*;

/**
 * Class name: SaverLoader
 * <p>
 * Description: It implements the store's saver and loader
 * @author Ana O.R.
 * @version 1.0
 */
public class SaverLoader {
    /** The store to be saved or loaded */
    private final Store store;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The saver loader's constructor
     * @param store the saver loader's store
     */
    public SaverLoader(Store store) {
        this.store = store;
    }

    /*---------------------------------------------------- SAVES -----------------------------------------------------*/

    /**
     * Save store.
     * @param parameterFilename  the name of the parameter's backup
     * @param discountsFilename  the name of the discounts' backup
     * @param exchangesFilename  the name of the exchanges' backup
     * @param ordersFilename     the name of the orders' backup
     * @param packsFilename      the name of the packs' backup
     * @param categoriesFilename the name of the categories' backup
     * @param productFilename    the name of the products' backup
     * @param userFilename       the name of the users' backup
     * @throws IOException something went wrong when writing or reading
     */
    public void saveStore(String parameterFilename, String discountsFilename, String exchangesFilename,
                          String ordersFilename, String packsFilename, String categoriesFilename,
                          String productFilename, String userFilename) throws IOException {
        try {
            saveParameters(parameterFilename);
            saveDiscounts(discountsFilename);
            saveExchanges(exchangesFilename);
            saveOrders(ordersFilename);
            savePacks(packsFilename);
            saveCategories(categoriesFilename);
            saveStoreProducts(productFilename);
            saveUsers(userFilename);

        } catch (IOException exception) {
            throw new IOException(exception.getMessage());
        }
    }

    /**
     * It allows for the store's parameters to be saved
     * @param parameterFilename the desired filename for the store's parameters' backup
     * @throws IOException something went wrong when writing or reading
     */
    public void saveParameters(String parameterFilename) throws IOException {
        BufferedWriter buffer;
        Parameter parameters = store.getParameters();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + parameterFilename + ".txt")));

            buffer.write(parameters.toString() + "\n");

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's discounts to be saved
     * @param discountsFilename the desired filename for the store's discounts' backup
     * @throws IOException something went wrong when writing or reading
     */
    public void saveDiscounts(String discountsFilename) throws IOException {
        BufferedWriter buffer;
        List<Discount> discounts = store.getDiscounts();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + discountsFilename + ".txt")));

            for (Discount discount : discounts) {
                buffer.write(discount.toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's exchanges to be saved
     * @param exchangesFilename the desired filename for the store's exchanges' backup
     * @throws IOException something went wrong when writing or reading
     */
    public void saveExchanges(String exchangesFilename) throws IOException {
        BufferedWriter buffer;
        List<Exchange> exchanges = store.getExchanges();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + exchangesFilename + ".txt")));

            for (Exchange exchange : exchanges) {
                buffer.write(exchange.toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's orders to be saved
     * @param ordersFilename the desired filename for the store's orders' backup
     * @throws IOException something went wrong when writing or reading
     */
    public void saveOrders(String ordersFilename) throws IOException {
        BufferedWriter buffer;
        List<Order> orders = store.getOrders();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + ordersFilename + ".txt")));

            for (Order order : orders) {
                buffer.write(order.toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's packs to be saved
     * @param packsFilename the desired filename for the store's packs' backup
     * @throws IOException something went wrong when writing or reading
     */
    public void savePacks(String packsFilename) throws IOException {
        BufferedWriter buffer;
        List<Pack> packs = store.getPacks();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + packsFilename + ".txt")));

            for (Pack pack : packs) {
                buffer.write(pack.toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's categories to be saved
     * @param categoriesFilename the desired filename for the store's categories' backup
     * @throws IOException something went wrong when writing or reading
     */
    public void saveCategories(String categoriesFilename) throws IOException {
        BufferedWriter buffer;
        HashMap<String, Category> categories = store.getCategories();
        Set<String> keys = store.getCategories().keySet();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + categoriesFilename + ".txt")));

            for (String key : keys) {
                buffer.write(categories.get(key).toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's products to be saved
     * @param productFilename the desired filename for the store's product's backup
     * @throws IOException something went wrong when writing or reading
     */
    public void saveStoreProducts(String productFilename) throws IOException {
        BufferedWriter buffer;
        List<Product> products = store.getProducts();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + productFilename + ".txt")));

            for (Product product : products) {
                buffer.write(product.toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's users to be saved
     * @param userFilename the desired filename for the store's users' backup
     * @throws IOException something went wrong when writing or reading
     */
    public void saveUsers(String userFilename) throws IOException {
        BufferedWriter buffer;
        Map<String, User> users = store.getUsers();
        Set<String> keys = store.getUsers().keySet();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + userFilename + ".txt")));
            for (String key : keys) {
                buffer.write(users.get(key).toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}