package store;

import exchange.Exchange;
import exchange.Offer;
import order.*;
import product.*;
import user.User;

import java.io.*;
import java.time.*;
import java.util.*;

/**
 * Class name: SaverLoader
 * <p>
 * Description: It implements the store's saver and loader
 * @author Ana O.R. and Sofía C.L.
 * @version 1.5
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
     * @param offersFilename            the name of the offers' backup
     * @param exchangesFilename         the name of the exchanges' backup
     * @param ordersFilename            the name of the orders' backup
     * @param packsFilename             the name of the packs' backup
     * @param categoriesFilename        the name of the categories' backup
     * @param storeProductFilename      the name of the store products' backup
     * @param secondHandProductFilename the name of the hand products' filename
     * @param userFilename              the name of the users' backup
     * @throws IOException something went wrong when writing
     */
    public void saveStore(String parameterFilename, String discountsFilename, String offersFilename,
                          String exchangesFilename, String ordersFilename, String packsFilename,
                          String categoriesFilename, String storeProductFilename, String secondHandProductFilename,
                          String userFilename) throws IOException {
        try {
            saveParameters(parameterFilename);
            saveCategories(categoriesFilename);
            saveStoreProducts(storeProductFilename);
            saveSecondHandProducts(secondHandProductFilename);
            saveDiscounts(discountsFilename);
            saveOffers(offersFilename);
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
     * @throws IOException something went wrong when writing
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
     * @throws IOException something went wrong when writing
     */
    private void saveCategories(String categoriesFilename) throws IOException {
        BufferedWriter buffer;
        HashMap<String, Category> categories = Store.getInstance().getCategories();
        Set<String> keys = Store.getInstance().getCategories().keySet();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + categoriesFilename + ".csv")));

            buffer.write("NAME;REVENUE");
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
     * @throws IOException something went wrong when writing
     */
    private void saveStoreProducts(String storeProductFilename) throws IOException {
        BufferedWriter buffer;
        HashMap<String, StoreProduct> products = Store.getInstance().getStoreProducts();
        Set<String> keys = Store.getInstance().getStoreProducts().keySet();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + storeProductFilename + ".csv")));

            buffer.write("TYPE;ID;NAME;DESCRIPTION;PRICE;STOCK;CATEGORIES;PAGES;AUTHOR;EDITORIAL;YEAR;PLAYER;AGE;" +
                         "STYLE;BRAND;MATERIAL;DIMENSION\n");
            buffer.write(Product.totalId); /* Global ID */
            for (String key : keys) {
                buffer.write(products.get(key).toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's second hand products to be saved
     * @param secondHandProductFilename the desired filename for the store's second hand product's backup
     * @throws IOException something went wrong when writing
     */
    private void saveSecondHandProducts(String secondHandProductFilename) throws IOException {
        BufferedWriter buffer;
        HashMap<String, SecondHandProduct> products = Store.getInstance().getSecondHandProducts();
        Set<String> keys = Store.getInstance().getStoreProducts().keySet();

        try {
            buffer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(".\\resources\\" + secondHandProductFilename + ".csv")));

            buffer.write("TYPE;ID;PRICE;NAME;DESC;PHOTO;VAL_DATE;AVAILABLE;PAID_VAL;STATUS");
            buffer.write(Product.totalId); /* Global ID */
            for (String key : keys) {
                buffer.write(products.get(key).toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's discounts to be saved
     * @param discountsFilename the desired filename for the store's discounts' backup
     * @throws IOException something went wrong when writing
     */
    private void saveDiscounts(String discountsFilename) throws IOException {
        BufferedWriter buffer;
        List<Discount> discounts = Store.getInstance().getDiscounts();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + discountsFilename + ".csv")));

            buffer.write(" TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE "); // DUE
            buffer.write(Discount.totalId); /* Global ID */
            for (Discount discount : discounts) {
                buffer.write(discount.toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's offers to be saved
     * @param offersFilename the desired filename for the store's offers' backup
     * @throws IOException something went wrong when writing
     */
    private void saveOffers(String offersFilename) throws IOException {
        BufferedWriter buffer;
        List<Offer> offers = Store.getInstance().getOffers();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + offersFilename + ".csv")));

            buffer.write(""); // DUE
            buffer.write(Offer.totalId); /* Global ID */
            for (Offer offer : offers) {
                buffer.write(offer.toString() + "\n");
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's exchanges to be saved
     * @param exchangesFilename the desired filename for the store's exchanges' backup
     * @throws IOException something went wrong when writing
     */
    private void saveExchanges(String exchangesFilename) throws IOException {
        BufferedWriter buffer;
        List<Exchange> exchanges = Store.getInstance().getExchanges();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + exchangesFilename + ".csv")));

            buffer.write(""); // DUE
            buffer.write(Exchange.totalId); /* Global ID */
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
     * @throws IOException something went wrong when writing
     */
    private void saveOrders(String ordersFilename) throws IOException {
        BufferedWriter buffer;
        List<Order> orders = Store.getInstance().getOrders();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + ordersFilename + ".csv")));

            buffer.write(""); // DUE
            buffer.write(Order.totalId); /* Global ID */
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
     * @throws IOException something went wrong when writing
     */
    private void savePacks(String packsFilename) throws IOException {
        BufferedWriter buffer;
        List<Pack> packs = Store.getInstance().getPacks();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + packsFilename + ".csv")));

            buffer.write(""); // DUE
            buffer.write(Pack.totalId); /* Global ID */
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
     * @throws IOException something went wrong when writing
     */
    private void saveUsers(String userFilename) throws IOException {
        BufferedWriter buffer;
        Map<String, User> users = Store.getInstance().getUsers();
        Set<String> keys = Store.getInstance().getUsers().keySet();

        try {
            buffer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(".\\resources\\" + userFilename + ".csv")));

            buffer.write(""); // DUE
            buffer.write(User.totalId); /* Global ID */
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
     * @param offersFilename            the name of the offers' backup
     * @param exchangesFilename         the name of the exchanges' backup
     * @param ordersFilename            the name of the orders' backup
     * @param packsFilename             the name of the packs' backup
     * @param categoriesFilename        the name of the categories' backup
     * @param storeProductFilename      the name of the store products' backup
     * @param secondHandProductFilename the name of the hand products' filename
     * @param userFilename              the name of the users' backup
     * @throws IOException something went wrong when reading
     */
    public void loadStore(String parameterFilename, String discountsFilename, String offersFilename,
                          String exchangesFilename, String ordersFilename, String packsFilename,
                          String categoriesFilename, String storeProductFilename, String secondHandProductFilename,
                          String userFilename) throws IOException {
        try {
            loadParameters(parameterFilename);
            loadCategories(categoriesFilename);
            loadStoreProducts(storeProductFilename);
            loadSecondHandProducts(secondHandProductFilename);
            loadDiscounts(discountsFilename);
            loadOffers(offersFilename);
            loadExchanges(exchangesFilename);
            loadOrders(ordersFilename);
            loadPacks(packsFilename);
            loadUsers(userFilename);

        } catch (IOException exception) {
            throw new IOException(exception.getMessage());
        }
    }

    /**
     * It allows for the store's parameters to be loaded
     * @param parameterFilename the filename of the store's parameters' backup
     * @throws IOException something went wrong when reading
     */
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

            buffer.readLine(); /* OFFER_TIME;ORDER_TIME;VAL_COST */
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

    /**
     * It allows for the store's categories to be loaded
     * @param categoriesFilename the filename of the store's categories' backup
     * @throws IOException something went wrong when reading
     */
    private void loadCategories(String categoriesFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;
        String name;
        double revenue;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + categoriesFilename + ".csv")));

            buffer.readLine(); /* NAME;REVENUE */
            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                name = words[0];
                revenue = Double.parseDouble(words[1]);
                new Category(name, revenue);
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's store products to be loaded
     * @param storeProductFilename the filename of the store's store products' backup
     * @throws IOException something went wrong when reading
     */
    private void loadStoreProducts(String storeProductFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;
        int i = 0;
        String categoryName;
        List<Category> categories = new ArrayList<>();

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + storeProductFilename + ".csv")));

            buffer.readLine(); /* TYPE;ID;NAME;DESCRIPTION;PHOTO;PRICE;STOCK;CATEGORIES;PAGES;AUTHOR;EDITORIAL;YEAR;
            PLAYER;AGE;STYLE;BRAND;MATERIAL;DIMENSION */
            Product.totalId = Integer.parseInt(buffer.readLine()); /* Global ID */
            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                ProductType type = ProductType.valueOf(words[0]);
                String id = words[1];
                String name = words[2];
                String description = words[3];
                String photo = words[4];
                double price = Double.parseDouble(words[5]);
                int stock = Integer.parseInt(words[6]);
                String categoriesString = words[7]; // DUE: Parse this
                int numPages = Integer.parseInt(words[8]);
                String author = words[9];
                String editorial = words[10];
                Year year = Year.parse(words[11]);
                int numPlayers = Integer.parseInt(words[12]);
                String ageRange = words[13];
                GameStyle gameStyle = GameStyle.valueOf(words[14]);
                String brand = words[15];
                String material = words[16];
                String dimension = words[17];

                words = categoriesString.split(",");
                while (words[i] != null) {
                    categoryName = words[i];
                    categories.add(Store.getInstance().getCategoryFromName(categoryName));
                    i++;
                }

                switch (type) {
                    case ProductType.COMIC:
                        new Comic(id, price, name, description, photo, stock, numPages, year, author, editorial,
                                categories.toArray(new Category[0]));
                        break;
                    case ProductType.FIGURINE:
                        new Figurine(id, price, name, description, photo, stock, dimension, brand, material,
                                categories.toArray(new Category[0]));
                        break;
                    case ProductType.GAME:
                        new Game(id, price, name, description, photo, stock, numPlayers, ageRange, gameStyle,
                                categories.toArray(new Category[0]));
                        break;
                }
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's second-hand products to be loaded
     * @param secondHandProductFilename the filename of the store's second-hand products' backup
     * @throws IOException something went wrong when reading
     */
    private void loadSecondHandProducts(String secondHandProductFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + secondHandProductFilename + ".csv")));

            buffer.readLine(); /* TYPE;ID;PRICE;NAME;DESC;PHOTO;VAL_DATE;AVAILABLE;PAID_VAL;STATUS */
            int totalId = Integer.parseInt(buffer.readLine()); /* Global ID, SP se carga antes que SHP */
            if (totalId > Product.totalId) {
                Product.totalId = Integer.parseInt(buffer.readLine());
            }
            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                ProductType type = ProductType.valueOf(words[0]);
                String id = words[1];
                double price = Double.parseDouble(words[2]);
                String name = words[3];
                String description = words[4];
                String photo = words[5];
                LocalDate valuationDate = LocalDate.parse(words[6]);
                boolean available = Boolean.parseBoolean(words[7]);
                boolean paidValuation = Boolean.parseBoolean(words[8]);
                ConservationStatus status = ConservationStatus.valueOf(words[9]);

                new SecondHandProduct(id, price, name, description, photo, type, valuationDate, available,
                        paidValuation, status);
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's discounts to be loaded
     * @param discountsFilename the filename of the store's discounts' backup
     * @throws IOException something went wrong when reading
     */
    private void loadDiscounts(String discountsFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;
        int i = 0;
        String productId;
        List<StoreProduct> products = new ArrayList<>();

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + discountsFilename + ".csv")));

            buffer.readLine(); /* TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE */
            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                DiscountType type = DiscountType.valueOf(words[0]);
                String id = words[1];
                LocalDate startDate = LocalDate.parse(words[2]);
                LocalDate endDate = LocalDate.parse(words[3]);
                String productsString = words[4];
                boolean overWhole = Boolean.parseBoolean(words[5]);

                words = productsString.split(",");
                while (words[i] != null) {
                    productId = words[i];
                    products.add(Store.getInstance().getProductFromId(productId));
                    i++;
                }
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's offers to be loaded
     * @param offersFilename the filename of the store's offers' backup
     * @throws IOException something went wrong when reading
     */
    private void loadOffers(String offersFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + offersFilename + ".csv")));

            buffer.readLine(); /*  */
            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's exchanges to be loaded
     * @param exchangesFilename the filename of the store's exchanges' backup
     * @throws IOException something went wrong when reading
     */
    private void loadExchanges(String exchangesFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + exchangesFilename + ".csv")));

            buffer.readLine(); /*  */
            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's orders to be loaded
     * @param ordersFilename the filename of the store's orders' backup
     * @throws IOException something went wrong when reading
     */
    private void loadOrders(String ordersFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + ordersFilename + ".csv")));

            buffer.readLine(); /*  */
            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's packs to be loaded
     * @param packsFilename the filename of the store's packs' backup
     * @throws IOException something went wrong when reading
     */
    private void loadPacks(String packsFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + packsFilename + ".csv")));

            buffer.readLine(); /*  */
            while ((line = buffer.readLine()) != null) {
                words = line.split(";");
                // DUE
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * It allows for the store's users to be loaded
     * @param userFilename the filename of the store's users' backup
     * @throws IOException something went wrong when reading
     */
    private void loadUsers(String userFilename) throws IOException {
        BufferedReader buffer;
        String[] words;
        String line;

        try {
            buffer = new BufferedReader(
                    new InputStreamReader(new FileInputStream(".\\resources\\" + userFilename + ".csv")));

            buffer.readLine(); /*  */
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