package model.store;

import model.discount.Discount;
import model.exchange.Exchange;
import model.exchange.Offer;
import model.order.Order;
import model.product.*;
import model.user.User;

import java.io.*;
import java.util.*;

/**
 * It implements the store's saver and loader
 * @author Ana O.R. and Sofía C.L.
 * @version 1.8
 */
public class SaverLoader {
    private static final SaverLoader INSTANCE = new SaverLoader();
    private Store s = Store.getInstance();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The saver loader's constructor
     */
    public SaverLoader() {
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the saver-loader's instance
     * @return the saver-loader's instance
     */
    public static SaverLoader getInstance() {
        return SaverLoader.INSTANCE;
    }

    /*---------------------------------------------------- SAVES -----------------------------------------------------*/

    /**
     * It saves the store
     * @param parameterFilename         the name of the parameter's backup
     * @param categoriesFilename        the name of the categories' backup
     * @param reviewsFilename           the name of the store reviews' backup
     * @param storeProductFilename      the name of the store products' backup
     * @param secondHandProductFilename the name of the hand products' filename
     * @param packsFilename             the name of the packs' backup
     * @param discountsFilename         the name of the discounts' backup
     * @param offersFilename            the name of the offers' backup
     * @param exchangesFilename         the name of the exchanges' backup
     * @param ordersFilename            the name of the orders' backup
     * @param userFilename              the name of the users' backup
     * @throws IOException something went wrong when writing
     */
    public void saveStore(String parameterFilename, String categoriesFilename, String reviewsFilename,
                          String storeProductFilename, String secondHandProductFilename, String packsFilename,
                          String discountsFilename, String offersFilename, String exchangesFilename,
                          String ordersFilename, String userFilename) throws IOException {

        /*saveParameters(parameterFilename);
        saveCategories(categoriesFilename);
        saveReviews(reviewsFilename);
        saveStoreProducts(storeProductFilename);
        saveSecondHandProducts(secondHandProductFilename);
        savePacks(packsFilename);
        saveDiscounts(discountsFilename);
        saveOffers(offersFilename);
        saveExchanges(exchangesFilename);
        saveOrders(ordersFilename);
        saveUsers(userFilename);*/
        //saveStatics(staticFilename);

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + "data" + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(Store.getInstance());

        objectOutputStream.flush();
        objectOutputStream.close();

    }

    /**
     * It allows for the store's parameters to be saved
     * @param parameterFilename the desired filename for the store's parameters' backup
     * @throws IOException something went wrong when writing
     */
    private void saveParameters(String parameterFilename) throws IOException {
        Parameter parameters = Store.getInstance().getParameters();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + parameterFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(parameters);

        objectOutputStream.flush();
        objectOutputStream.close();

    }

    /**
     * It allows for the store's categories to be saved
     * @param categoriesFilename the desired filename for the store's categories' backup
     * @throws IOException something went wrong when writing
     */
    private void saveCategories(String categoriesFilename) throws IOException {
        HashMap<String, Category> categories = Store.getInstance().getCategories();
        Set<String> keys = Store.getInstance().getCategories().keySet();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + categoriesFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (String key : keys) {
            objectOutputStream.writeObject(categories.get(key));
        }

        objectOutputStream.flush();
        objectOutputStream.close();

    }

    /**
     * It allows for the store's reviews to be saved
     * @param reviewsFilename the desired filename for the store's reviews' backup
     * @throws IOException something went wrong when writing
     */
    private void saveReviews(String reviewsFilename) throws IOException {
        List<Review> reviews = Store.getInstance().getReviews();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + reviewsFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (Review review : reviews) {
            objectOutputStream.writeObject(review);
        }

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * It allows for the store's store products to be saved
     * @param storeProductFilename the desired filename for the store's store product's backup
     * @throws IOException something went wrong when writing
     */
    private void saveStoreProducts(String storeProductFilename) throws IOException {
        HashMap<String, StoreProduct> products = Store.getInstance().getStoreProducts();
        Set<String> keys = Store.getInstance().getStoreProducts().keySet();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + storeProductFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (String key : keys) {
            objectOutputStream.writeObject(products.get(key));
        }

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * It allows for the store's second hand products to be saved
     * @param secondHandProductFilename the desired filename for the store's second hand product's backup
     * @throws IOException something went wrong when writing
     */
    private void saveSecondHandProducts(String secondHandProductFilename) throws IOException {
        HashMap<String, SecondHandProduct> products = Store.getInstance().getSecondHandProducts();
        Set<String> keys = Store.getInstance().getStoreProducts().keySet();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + secondHandProductFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (String key : keys) {
            objectOutputStream.writeObject(products.get(key));
        }

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * It allows for the store's packs to be saved
     * @param packsFilename the desired filename for the store's packs' backup
     * @throws IOException something went wrong when writing
     */
    private void savePacks(String packsFilename) throws IOException {
        List<Pack> packs = Store.getInstance().getPacks();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + packsFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (Pack pack : packs) {
            objectOutputStream.writeObject(pack);
        }

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * It allows for the store's discounts to be saved
     * @param discountsFilename the desired filename for the store's discounts' backup
     * @throws IOException something went wrong when writing
     */
    private void saveDiscounts(String discountsFilename) throws IOException {
        List<Discount> discounts = Store.getInstance().getDiscounts();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + discountsFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (Discount discount : discounts) {
            objectOutputStream.writeObject(discount);
        }

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * It allows for the store's offers to be saved
     * @param offersFilename the desired filename for the store's offers' backup
     * @throws IOException something went wrong when writing
     */
    private void saveOffers(String offersFilename) throws IOException {
        List<Offer> offers = Store.getInstance().getOffers();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + offersFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (Offer offer : offers) {
            objectOutputStream.writeObject(offer);
        }

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * It allows for the store's exchanges to be saved
     * @param exchangesFilename the desired filename for the store's exchanges' backup
     * @throws IOException something went wrong when writing
     */
    private void saveExchanges(String exchangesFilename) throws IOException {
        List<Exchange> exchanges = Store.getInstance().getExchanges();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + exchangesFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (Exchange exchange : exchanges) {
            objectOutputStream.writeObject(exchange);
        }

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * It allows for the store's orders to be saved
     * @param ordersFilename the desired filename for the store's orders' backup
     * @throws IOException something went wrong when writing
     */
    private void saveOrders(String ordersFilename) throws IOException {
        List<Order> orders = Store.getInstance().getOrders();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + ordersFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (Order order : orders) {
            objectOutputStream.writeObject(order);
        }

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * It allows for the store's users to be saved
     * @param userFilename the desired filename for the store's users' backup
     * @throws IOException something went wrong when writing
     */
    private void saveUsers(String userFilename) throws IOException {
        Map<String, User> users = Store.getInstance().getUsers();

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + userFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (User u : users.values()) {
            objectOutputStream.writeObject(u);
        }

        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /*---------------------------------------------------- LOADS -----------------------------------------------------*/

    /**
     * It loads an initialized store (the store should be empty!!)
     * @param parameterFilename         the name of the parameter's backup
     * @param categoriesFilename        the name of the categories' backup
     * @param reviewsFilename           the name of the store reviews' backup
     * @param storeProductFilename      the name of the store products' backup
     * @param secondHandProductFilename the name of the hand products' filename
     * @param packsFilename             the name of the packs' backup
     * @param discountsFilename         the name of the discounts' backup
     * @param offersFilename            the name of the offers' backup
     * @param exchangesFilename         the name of the exchanges' backup
     * @param ordersFilename            the name of the orders' backup
     * @param userFilename              the name of the users' backup
     * @throws IOException something went wrong when reading
     */
    public void loadStore(String parameterFilename, String categoriesFilename, String reviewsFilename,
                          String storeProductFilename, String secondHandProductFilename, String packsFilename,
                          String discountsFilename, String offersFilename, String exchangesFilename,
                          String ordersFilename, String userFilename) throws IOException {

        try {
            /*loadParameters(parameterFilename);
            loadCategories(categoriesFilename);
            loadReviews(reviewsFilename);
            loadStoreProducts(storeProductFilename);
            loadSecondHandProducts(secondHandProductFilename);
            loadPacks(packsFilename);
            loadDiscounts(discountsFilename);
            loadOffers(offersFilename);
            loadExchanges(exchangesFilename);
            loadOrders(ordersFilename);
            loadUsers(userFilename);*/

            FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + "data" + ".txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Store.setInstance((Store) objectInputStream.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException exception) {
            throw new IOException(exception.getMessage());
        }
    }

    /**
     * It allows for the store's parameters to be loaded
     * @param parameterFilename the filename of the store's parameters' backup
     * @throws IOException something went wrong when reading
     */
    private void loadParameters(String parameterFilename) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + parameterFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Parameter.setParam((Parameter) objectInputStream.readObject());
        objectInputStream.close();
    }

    /**
     * It allows for the store's categories to be loaded
     * @param categoriesFilename the filename of the store's categories' backup
     * @throws IOException something went wrong when reading
     */
    private void loadCategories(String categoriesFilename) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + categoriesFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                Category c = (Category) objectInputStream.readObject();
            } catch (EOFException e) {
                break;
            }
        }
        objectInputStream.close();
    }

    /**
     * It allows for the store's reviews to be loaded
     * @param reviewsFilename the filename of the store's reviews' backup
     * @throws IOException something went wrong when reading
     */
    private void loadReviews(String reviewsFilename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + reviewsFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                Review r = (Review) objectInputStream.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream.close();
    }

    /**
     * It allows for the store's store products to be loaded
     * @param storeProductFilename the filename of the store's store products' backup
     * @throws IOException something went wrong when reading
     */
    private void loadStoreProducts(String storeProductFilename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + storeProductFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                StoreProduct p = (StoreProduct) objectInputStream.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream.close();
    }

    /**
     * It allows for the store's second-hand products to be loaded
     * @param secondHandProductFilename the filename of the store's second-hand products' backup
     * @throws IOException something went wrong when reading
     */
    private void loadSecondHandProducts(String secondHandProductFilename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + secondHandProductFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                SecondHandProduct p = (SecondHandProduct) objectInputStream.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream.close();
    }

    /**
     * It allows for the store's packs to be loaded
     * @param packsFilename the filename of the store's packs' backup
     * @throws IOException something went wrong when reading
     */
    private void loadPacks(String packsFilename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + packsFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                Pack p = (Pack) objectInputStream.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream.close();
    }

    /**
     * It allows for the store's discounts to be loaded
     * @param discountsFilename the filename of the store's discounts' backup
     * @throws IOException something went wrong when reading
     */
    private void loadDiscounts(String discountsFilename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + discountsFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                Discount p = (Discount) objectInputStream.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream.close();
    }

    /**
     * It allows for the store's offers to be loaded
     * @param offersFilename the filename of the store's offers' backup
     * @throws IOException something went wrong when reading
     */
    private void loadOffers(String offersFilename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + offersFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                Offer o = (Offer) objectInputStream.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream.close();
    }

    /**
     * It allows for the store's exchanges to be loaded
     * @param exchangesFilename the filename of the store's exchanges' backup
     * @throws IOException something went wrong when reading
     */
    private void loadExchanges(String exchangesFilename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + exchangesFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                Exchange e = (Exchange) objectInputStream.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream.close();
    }

    /**
     * It allows for the store's orders to be loaded
     * @param ordersFilename the filename of the store's orders' backup
     * @throws IOException something went wrong when reading
     */
    private void loadOrders(String ordersFilename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + ordersFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                Order o = (Order) objectInputStream.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream.close();
    }

    /**
     * It allows for the store's users to be loaded
     * @param userFilename the filename of the store's users' backup
     * @throws IOException something went wrong when reading
     */
    private void loadUsers(String userFilename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + userFilename + ".txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        while (true) {
            try {
                User u = (User) objectInputStream.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        objectInputStream.close();
    }
}