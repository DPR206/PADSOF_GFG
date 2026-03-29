package store;

import exchange.Exchange;
import order.Discount;
import order.Order;
import product.*;
import user.User;

import java.io.*;
import java.time.Period;
import java.util.*;

/**
 * Class name: SaverLoader
 * <p>
 * Description: It implements the store's saver and loader
 * @author Ana O.R. and Sofía C.L.
 * @version 1.2
 */
public class SaverLoader {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The saver loader's constructor
     */
    public SaverLoader() {
    }

    /*---------------------------------------------------- SAVES -----------------------------------------------------*/

    /**
     * It saves the store
     * @param parameterFilename         the name of the parameter's backup
     * @param discountsFilename         the name of the discounts' backup
     * @param exchangesFilename         the name of the exchanges' backup
     * @param ordersFilename            the name of the orders' backup
     * @param packsFilename             the name of the packs' backup
     * @param categoriesFilename        the name of the categories' backup
     * @param storeProductFilename      the name of the store products' backup
     * @param secondHandProductFilename the name of the hand products' filename
     * @param userFilename              the name of the users' backup
     * @throws IOException something went wrong when writing or reading
     */
    public void saveStore(String parameterFilename, String discountsFilename, String exchangesFilename,
                          String ordersFilename, String packsFilename, String categoriesFilename,
                          String storeProductFilename, String secondHandProductFilename, String userFilename)
            throws IOException {
        try {
            saveParameters(parameterFilename);
            saveCategories(categoriesFilename);
            saveStoreProducts(storeProductFilename);
            saveSecondHandProducts(secondHandProductFilename);
            saveDiscounts(discountsFilename);
            saveExchanges(exchangesFilename);
            saveOrders(ordersFilename);
            savePacks(packsFilename);
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
    private void saveParameters(String parameterFilename) throws IOException {
        BufferedWriter buffer;
        Parameter parameters = Store.getInstance().getParameters();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + parameterFilename + ".csv")));

            buffer.write("OFFER_TIME;ORDER_TIME;VAL_COST\n");
            buffer.write(parameters.toString() + "\n");

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
    private void saveCategories(String categoriesFilename) throws IOException {
        BufferedWriter buffer;
        HashMap<String, Category> categories = Store.getInstance().getCategories();
        Set<String> keys = Store.getInstance().getCategories().keySet();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + categoriesFilename + ".csv")));

            for (String key : keys) {
                buffer.write(categories.get(key).toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's store products to be saved
     * @param storeProductFilename the desired filename for the store's store product's backup
     * @throws IOException something went wrong when writing or reading
     */
    private void saveStoreProducts(String storeProductFilename) throws IOException {
        BufferedWriter buffer;
        List<StoreProduct> products = Store.getInstance().getStoreProducts();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + storeProductFilename + ".csv")));

            buffer.write("TYPE(C/G/F);ID;NAME;DESCRIPTION;PRICE;STOCK;CATEGORIES;PAGES;AUTHOR;EDITORIAL;YEAR;" +
                         "PLAYER;AGE;STYLE(Cards/Dice/GameBoard/Miniature);BRAND;MATERIAL;DIMENSION\n");
            for (StoreProduct product : products) {
                buffer.write(product.toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's second hand products to be saved
     * @param secondHandProductFilename the desired filename for the store's second hand product's backup
     * @throws IOException something went wrong when writing or reading
     */
    private void saveSecondHandProducts(String secondHandProductFilename) throws IOException {
        BufferedWriter buffer;
        List<SecondHandProduct> products = Store.getInstance().getSecondHandProducts();

        try {
            buffer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(".\\resources\\" + secondHandProductFilename + ".csv")));

            for (SecondHandProduct product : products) {
                buffer.write(product.toString() + "\n");
            }

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
    private void saveDiscounts(String discountsFilename) throws IOException {
        BufferedWriter buffer;
        List<Discount> discounts = Store.getInstance().getDiscounts();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + discountsFilename + ".csv")));

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
    private void saveExchanges(String exchangesFilename) throws IOException {
        BufferedWriter buffer;
        List<Exchange> exchanges = Store.getInstance().getExchanges();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + exchangesFilename + ".csv")));

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
    private void saveOrders(String ordersFilename) throws IOException {
        BufferedWriter buffer;
        List<Order> orders = Store.getInstance().getOrders();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + ordersFilename + ".csv")));

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
    private void savePacks(String packsFilename) throws IOException {
        BufferedWriter buffer;
        List<Pack> packs = Store.getInstance().getPacks();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + packsFilename + ".csv")));

            for (Pack pack : packs) {
                buffer.write(pack.toString() + "\n");
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
    private void saveUsers(String userFilename) throws IOException {
        BufferedWriter buffer;
        Map<String, User> users = Store.getInstance().getUsers();
        Set<String> keys = Store.getInstance().getUsers().keySet();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + userFilename + ".csv")));
            for (String key : keys) {
                buffer.write(users.get(key).toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /*---------------------------------------------------- LOADS -----------------------------------------------------*/


    /**
     * It loads an initialized store (the store should be empty!!)
     * @param parameterFilename         the name of the parameter's backup
     * @param discountsFilename         the name of the discounts' backup
     * @param exchangesFilename         the name of the exchanges' backup
     * @param ordersFilename            the name of the orders' backup
     * @param packsFilename             the name of the packs' backup
     * @param categoriesFilename        the name of the categories' backup
     * @param storeProductFilename      the name of the store products' backup
     * @param secondHandProductFilename the name of the hand products' filename
     * @param userFilename              the name of the users' backup
     * @throws IOException something went wrong when writing or reading
     */
    public void loadStore(String parameterFilename, String discountsFilename, String exchangesFilename,
                          String ordersFilename, String packsFilename, String categoriesFilename,
                          String storeProductFilename, String secondHandProductFilename, String userFilename)
            throws IOException {
        try {
            loadParameters(parameterFilename);
            loadCategories(categoriesFilename);
            loadStoreProducts(storeProductFilename);
            loadSecondHandProducts(secondHandProductFilename);
            loadDiscounts(discountsFilename);
            loadExchanges(exchangesFilename);
            loadOrders(ordersFilename);
            loadPacks(packsFilename);
            loadUsers(userFilename);

        } catch (IOException exception) {
            throw new IOException(exception.getMessage());
        }
    }

    private void loadParameters(String parameterFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;
        Period OfferTime;
        Period OrderTime;
        double valuationCost;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + parameterFilename + ".csv")));

            /* OFFER_TIME;ORDER_TIME;VAL_COST */
            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                OfferTime = Period.parse(words[0]);
                Store.getInstance().getParameters().changeOfferTime(OfferTime);
                OrderTime = Period.parse(words[1]);
                Store.getInstance().getParameters().changeOrderTime(OrderTime);
                valuationCost = Double.parseDouble(words[2]);
                Store.getInstance().getParameters().changeValuationCost(valuationCost);
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void loadCategories(String categoriesFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + categoriesFilename + ".csv")));

            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void loadStoreProducts(String storeProductFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + storeProductFilename + ".csv")));

            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void loadSecondHandProducts(String secondHandProductFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + secondHandProductFilename + ".csv")));

            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void loadDiscounts(String discountsFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + discountsFilename + ".csv")));

            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void loadExchanges(String exchangesFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + exchangesFilename + ".csv")));

            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void loadOrders(String ordersFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + ordersFilename + ".csv")));

            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void loadPacks(String packsFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + packsFilename + ".csv")));

            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void loadUsers(String userFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + userFilename + ".csv")));

            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

}