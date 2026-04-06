package main;

import product.*;
import store.*;
import user.Manager;

import java.io.IOException;
import java.time.*;
import java.util.*;

/**
 * The type Manager loop.
 */
public class ManagerLoop extends Loop {
    // DUE: Muy importante!! -> No te olvides de hacer que se puedan escoger las cosas por nº de lista o ID
    // DUE: Manager puede añadir productos subiendo archivos, creo que eso no estaba acabado

    /** This loop's instance */
    private static ManagerLoop INSTANCE;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Registered client loop's constructor
     */
    ManagerLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Manager loop's instance
     * @return the Manager loop's instance
     */
    protected static ManagerLoop getInstance() {
        if (ManagerLoop.INSTANCE == null) {
            ManagerLoop.INSTANCE = new ManagerLoop();
        }
        return INSTANCE;
    }

    /**
     * The manager's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    void managerLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- managerLoop ---- \n"); // Es para debug, borrar
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Manage packs");
        System.out.println("\t[2] Manage store products");
        System.out.println("\t[3] Add store products");
        System.out.println("\t[4] Manage employees");
        System.out.println("\t[5] Generate statistics");
        System.out.println("\t[6] Manage discounts");
        System.out.println("\t[7] Manage parameters");
        System.out.println("\t[8] See profile");
        System.out.println("\t[9] <- Go back");
        System.out.println("\t[10] <<- Go to main page");
        System.out.println("\t[11] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                managePacks();
                break;
            case 2:
                manageStoreProducts();
                break;
            case 3:
                addStoreProduct();
                break;
            case 4:
                manageEmployees();
                break;
            case 5:
                generateStatistics();
                break;
            case 6:
                manageDiscounts();
                break;
            case 7:
                manageParameters();
                break;
            case 8:
                seeProfile();
                break;
            case 9, 10:
                main();
                break;
            case 11:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                managerLoop();
                break;
        }
    }

    /**
     * It allows the manager to manage the store's packs
     */
    private void managePacks() {
        System.out.print("\n ---- managePacks ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to manage the store's store products
     */
    private void manageStoreProducts() throws IOException {
        System.out.print("\n ---- manageStoreProducts ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + currentScreenPageNum);

        List<StoreProduct> products = ((Manager) currentUser).searchStoreProduct(); // DUE: Añadir filtrado
        Pager.getInstance().printStoreProductListPage(products, currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Manage a product");
        previousPagePrinter(2);
        nextPagePrinterStoreProduct(3, products);
        System.out.println("\t[4] <- Go back");
        System.out.println("\t[5] <<- Go to main page");
        System.out.println("\t[6] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                System.out.print("\n ---- unregisteredSeeProduct ---- \n"); // Es para debug, borrar
                System.out.println("Enter the number of the desired product:");
                int productNum = scanner.nextInt();

                StoreProduct product =
                        Pager.getInstance().selectStoreProductFromPage(products, currentScreenPageNum, productNum);
                ProductType type = product.getType();
                pageNumGoForward();

                switch (type) {
                    case COMIC:
                        manageComic((Comic) product);
                    case GAME:
                        assert product instanceof Game;
                        manageGame((Game) product);
                    case FIGURINE:
                        assert product instanceof Figurine;
                        manageFigurine((Figurine) product);
                    default: // Este NO debería saltar nunca, lo pongo por si acaso
                        System.out.println("You shouldn't be able to see this :(");
                        manageStoreProducts();
                        break;
                }

                break;
            case 2:
                previousPage();
                manageStoreProducts();
                break;
            case 3:
                nextPageStoreProduct(products);
                manageStoreProducts();
                break;
            case 4:
                pageNumGoForward();
                managerLoop();
                break;
            case 5:
                pageNumGoForward();
                main();
                break;
            case 6:
                pageNumGoForward();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                manageStoreProducts();
                break;
        }
    }

    /**
     * It allows the manager to tweak a comic's information
     * @param comic the desired comic
     * @throws IOException the io exception
     */
    private void manageComic(Comic comic) throws IOException {
        System.out.print("\n ---- manageComic ---- \n"); // Es para debug, borrar
        comic.printAllInfo();

        System.out.println("What do you wish to change? (enter the nº)");
        System.out.println("\t[1] Name");
        System.out.println("\t[2] Description");
        System.out.println("\t[3] Price");
        System.out.println("\t[4] Photo");
        System.out.println("\t[5] Stock");
        System.out.println("\t[6] Categories");
        System.out.println("\t[7] Number of pages");
        System.out.println("\t[8] Author");
        System.out.println("\t[9] Editorial");
        System.out.println("\t[10] Publishing year");
        System.out.println("\t[11] <- Go back");
        System.out.println("\t[12] <<- Go to main page");
        System.out.println("\t[13] x Exit app");
        int chosenOption3 = scanner.nextInt();

        switch (chosenOption3) {
            case 1:
                System.out.println("Enter the product's new Name:");
                String newName = scanner.next();
                comic.setName(newName);
                break;
            case 2:
                System.out.println("Enter the product's new Description:");
                String newDesc = scanner.next();
                comic.setName(newDesc);
                break;
            case 3:
                System.out.println("Enter the product's new Price:");
                double newPrice = scanner.nextDouble();
                comic.setPrice(newPrice);
                break;
            case 4:
                System.out.println("Enter the product's new Photo's path:");
                String newPhoto = scanner.next();
                comic.setPhoto(newPhoto);
                break;
            case 5:
                System.out.println("Enter the product's new Stock:");
                int newStock = scanner.nextInt();
                comic.setStock(newStock);
                break;
            case 6:
                categoryChanger(comic);
                break;
            case 7:
                System.out.println("Enter the product's new Number of pages:");
                int newNumPages = scanner.nextInt();
                comic.setNumPages(newNumPages);
                break;
            case 8:
                System.out.println("Enter the product's new Author:");
                String newAuthor = scanner.next();
                comic.setAuthor(newAuthor);
                break;
            case 9:
                System.out.println("Enter the product's new Editorial:");
                String newEditorial = scanner.next();
                comic.setEditorial(newEditorial);
                break;
            case 10:
                System.out.println("Enter the product's new Publishing year:");
                Year newYear = Year.parse(scanner.next());
                comic.setYear(newYear);
                break;
            case 11:
                pageNumGoBack();
                manageStoreProducts();
                break;
            case 12:
                main();
                break;
            case 13:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                manageComic(comic);
                break;
        }
    }

    /**
     * It allows the manager to tweak a game's information
     * @param game the desired game
     * @throws IOException the io exception
     */
    private void manageGame(Game game) throws IOException {
        System.out.print("\n ---- manageGame ---- \n"); // Es para debug, borrar
        game.printAllInfo();

        System.out.println("What do you wish to change? (enter the nº)");
        System.out.println("\t[1] Name");
        System.out.println("\t[2] Description");
        System.out.println("\t[3] Price");
        System.out.println("\t[4] Photo");
        System.out.println("\t[5] Stock");
        System.out.println("\t[6] Categories");
        System.out.println("\t[7] Number of players");
        System.out.println("\t[8] Age range");
        System.out.println("\t[9] Game Style");
        System.out.println("\t[10] <- Go back");
        System.out.println("\t[11] <<- Go to main page");
        System.out.println("\t[12] x Exit app");
        int chosenOption3 = scanner.nextInt();

        switch (chosenOption3) {
            case 1:
                System.out.println("Enter the product's new Name:");
                String newName = scanner.next();
                game.setName(newName);
                break;
            case 2:
                System.out.println("Enter the product's new Description:");
                String newDesc = scanner.next();
                game.setName(newDesc);
                break;
            case 3:
                System.out.println("Enter the product's new Price:");
                double newPrice = scanner.nextDouble();
                game.setPrice(newPrice);
                break;
            case 4:
                System.out.println("Enter the product's new Photo's path:");
                String newPhoto = scanner.next();
                game.setPhoto(newPhoto);
                break;
            case 5:
                System.out.println("Enter the product's new Stock:");
                int newStock = scanner.nextInt();
                game.setStock(newStock);
                break;
            case 6:
                categoryChanger(game);
                break;
            case 7:
                System.out.println("Enter the product's new Number of players:");
                int newNumPlayers = scanner.nextInt();
                game.setNumPlayers(newNumPlayers);
                break;
            case 8:
                System.out.println("Enter the product's new Age range:");
                String newAgeRange = scanner.next();
                game.setAgeRange(newAgeRange);
                break;
            case 9:
                System.out.println("Enter the product's new Game Style (" + GameStyle.CARDS.getFormatedName() + "/" +
                                   GameStyle.DICE.getFormatedName() + "/" + GameStyle.GAMEBOARD.getFormatedName() +
                                   "/" + GameStyle.MINIATURE.getFormatedName() + "):");
                GameStyle newGameStyle = GameStyle.valueOf(scanner.next());
                game.setGameStyle(newGameStyle);
                break;
            case 10:
                pageNumGoBack();
                manageStoreProducts();
                break;
            case 11:
                main();
                break;
            case 12:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                manageGame(game);
                break;
        }
    }

    /**
     * It allows the manager to tweak a figurine's information
     * @param figurine the desired figurine
     * @throws IOException the io exception
     */
    private void manageFigurine(Figurine figurine) throws IOException {
        System.out.print("\n ---- manageFigurine ---- \n"); // Es para debug, borrar
        figurine.printAllInfo();

        System.out.println("What do you wish to change? (enter the nº)");
        System.out.println("\t[1] Name");
        System.out.println("\t[2] Description");
        System.out.println("\t[3] Price");
        System.out.println("\t[4] Photo");
        System.out.println("\t[5] Stock");
        System.out.println("\t[6] Categories");
        System.out.println("\t[7] Brand");
        System.out.println("\t[8] Material");
        System.out.println("\t[9] Dimensions");
        System.out.println("\t[10] <- Go back");
        System.out.println("\t[11] <<- Go to main page");
        System.out.println("\t[12] x Exit app");
        int chosenOption3 = scanner.nextInt();

        switch (chosenOption3) {
            case 1:
                System.out.println("Enter the product's new Name:");
                String newName = scanner.next();
                figurine.setName(newName);
                break;
            case 2:
                System.out.println("Enter the product's new Description:");
                String newDesc = scanner.next();
                figurine.setName(newDesc);
                break;
            case 3:
                System.out.println("Enter the product's new Price:");
                double newPrice = scanner.nextDouble();
                figurine.setPrice(newPrice);
                break;
            case 4:
                System.out.println("Enter the product's new Photo's path:");
                String newPhoto = scanner.next();
                figurine.setPhoto(newPhoto);
                break;
            case 5:
                System.out.println("Enter the product's new Stock:");
                int newStock = scanner.nextInt();
                figurine.setStock(newStock);
                break;
            case 6:
                categoryChanger(figurine);
                break;
            case 7:
                System.out.println("Enter the product's new Brand:");
                String newBrand = scanner.next();
                figurine.setBrand(newBrand);
                break;
            case 8:
                System.out.println("Enter the product's new Material:");
                String newMaterial = scanner.next();
                figurine.setMaterial(newMaterial);
                break;
            case 9:
                System.out.println("Enter the product's new Dimensions (in cm):");
                String newDimension = scanner.next();
                figurine.setDimension(newDimension);
                break;
            case 10:
                pageNumGoBack();
                manageStoreProducts();
                break;
            case 11:
                main();
                break;
            case 12:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                manageFigurine(figurine);
                break;
        }
    }

    /**
     * It allows a manager to tweak a store product's categories
     * @param storeProduct the desired store product
     */
    private void categoryChanger(StoreProduct storeProduct) {
        String categoryName, newCategoryName;
        Category category, newCategory;
        System.out.print("\n ---- categoryChanger ---- \n"); // Es para debug, borrar
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Replace an existing product's category");
        System.out.println("\t[2] Add a new category to the product");
        System.out.println("\t[3] Remove a product's category");
        chosenOption = scanner.nextInt();
        switch (chosenOption) {
            case 1:
                System.out.println("Which category do you want to change? (enter its name):");
                categoryName = scanner.next();
                category = Store.getInstance().getCategoryFromName(categoryName);
                if (category == null) {
                    System.out.println("A category which such a name doesn't exist, reloading...");
                    categoryChanger(storeProduct);
                    break;
                }
                System.out.println("Enter the replacement category's name:");
                newCategoryName = scanner.next();
                newCategory = Store.getInstance().getCategoryFromName(newCategoryName);
                if (newCategory == null) {
                    System.out.println("A category which such a name doesn't exist, reloading...");
                    categoryChanger(storeProduct);
                    break;
                }
                storeProduct.removeCategory(category);
                storeProduct.addCategory(newCategory);
                break;
            case 2:
                System.out.println("Which category do you want to add? (enter its name):");
                categoryName = scanner.next();
                category = Store.getInstance().getCategoryFromName(categoryName);
                if (category == null) {
                    System.out.println("A category which such a name doesn't exist, reloading...");
                    categoryChanger(storeProduct);
                    break;
                }
                storeProduct.addCategory(category);
                break;
            case 3:
                System.out.println("Which category do you want to add? (enter its name):");
                categoryName = scanner.next();
                category = Store.getInstance().getCategoryFromName(categoryName);
                if (category == null) {
                    System.out.println("A category which such a name doesn't exist, reloading...");
                    categoryChanger(storeProduct);
                    break;
                }
                storeProduct.removeCategory(category);
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                categoryChanger(storeProduct);
                break;
        }

    }

    /**
     * It allows the manager to add a store product to the store
     */
    private void addStoreProduct() {
        System.out.print("\n ---- addStoreProduct ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to manage the store's employees
     */
    private void manageEmployees() {
        System.out.print("\n ---- manageEmployees ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to generate several statistics
     * @throws IOException the io exception
     */
    private void generateStatistics() throws IOException {
        System.out.print("\n ---- generateStatistics ---- \n"); // Es para debug, borrar
        System.out.println("Which statistic do you wish to generate? (enter the nº)");
        System.out.println("\t[1] List of store products by sales");
        System.out.println("\t[2] List of clients by orders");
        System.out.println("\t[3] List of clients by exchanges");
        System.out.println("\t[4] List of revenue by month");
        System.out.println("\t[5] List of categories by revenue");
        System.out.println("\t[6] List of store products by sales with percentage regarding total revenues");
        System.out.println("\t[7] List of store products by sales with percentage regarding total revenues on a " +
                           "certain month");
        System.out.println("\t[8] Store's total revenue");
        System.out.println("\t[9] Store's total valuation's revenue");
        System.out.println("\t[10] A certain category's revenue");
        // No sé si es útil-> System.out.println("\t[11] A certain store product's revenue");
        // No sé si es útil-> System.out.println("\t[12] A certain client's number of orders");
        // No sé si es útil-> System.out.println("\t[13] A certain client's number of exchanges");
        System.out.println("\t[11] <- Go back");
        System.out.println("\t[12] <<- Go to main page");
        System.out.println("\t[13] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                productBySales();
                break;
            case 2:
                clientsByOrders();
                break;
            case 3:
                clientsByExchanges();
                break;
            case 4:
                revenueByMonth();
                break;
            case 5:
                categoriesByRevenue();
                break;
            case 6:
                productBySalesWithPercentage();
                break;
            case 7:
                productBySalesWithPercentageCertainMonth();
                break;
            case 8:
                System.out.println("The store's total revenue is " + Statistics.getTotal_revenue() + "€");
                break;
            case 9:
                System.out.println("The store's total revenue from valuations is " +
                                   Statistics.getINSTANCE().getRevenue_valuation() + "€");
                break;
            case 10:
                System.out.println("Which category do you want to see? (enter its name):");
                String categoryName = scanner.next();
                Category category = Store.getInstance().getCategoryFromName(categoryName);
                if (category == null) {
                    System.out.println("A category which such a name doesn't exist, reloading...");
                    generateStatistics();
                    break;
                }
                System.out.println("The " + categoryName + " category's total revenue is " +
                                   Statistics.getINSTANCE().getRevenueByCategory(categoryName) + "€");
                break;
            case 11:
                managerLoop();
                break;
            case 12:
                main();
                break;
            case 13:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                generateStatistics();
                break;
        }
    }

    /**
     * It allows the manager to see the store's store products by their sales
     * @throws IOException the io exception
     */
    public void productBySales() throws IOException {
        System.out.print("\n ---- productBySales ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + currentScreenPageNum);
        List<StoreProduct> products = Statistics.getINSTANCE().getProductsBySales();
        Pager.getInstance().printStoreProductListPage(products, currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        previousPagePrinter(2);
        nextPagePrinterStoreProduct(3, products);
        System.out.println("\t[3] <- Go back");
        System.out.println("\t[4] <<- Go to main page");
        System.out.println("\t[5] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                previousPage();
                productBySales();
                break;
            case 2:
                nextPageStoreProduct(products);
                productBySales();
                break;
            case 3:
                pageNumGoForward();
                generateStatistics();
                break;
            case 4:
                pageNumGoForward();
                main();
                break;
            case 5:
                pageNumGoForward();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                productBySales();
                break;
        }
    }

    /**
     * It allows the manager to see the store's registered clients by number of orders
     * @throws IOException the io exception
     */
    public void clientsByOrders() throws IOException {
        System.out.print("\n ---- clientsByOrders ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + currentScreenPageNum);
        Pager.getInstance().printRegisteredClientListPage(currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        previousPagePrinter(1);
        nextPagePrinterRegisteredClient(2);
        System.out.println("\t[3] <- Go back");
        System.out.println("\t[4] <<- Go to main page");
        System.out.println("\t[5] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                previousPage();
                clientsByOrders();
                break;
            case 2:
                nextPageRegisteredClient();
                clientsByOrders();
                break;
            case 3:
                pageNumGoForward();
                clientsByOrders();
                break;
            case 4:
                pageNumGoForward();
                main();
                break;
            case 5:
                pageNumGoForward();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                clientsByOrders();
                break;
        }
    }

    /**
     * It allows the manager to see the store's registered clients by number of exchanges
     * @throws IOException the io exception
     */
    public void clientsByExchanges() throws IOException {
        System.out.print("\n ---- clientsByExchanges ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + currentScreenPageNum);
        Pager.getInstance().printRegisteredClientListPage(currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        previousPagePrinter(1);
        if ((currentScreenPageNum + 1) < Pager.getInstance().getRegisteredClientMaxPageNum()) {
            System.out.println("\t[2] Next page >");
        } else {
            System.out.println("\t[2] Reload page");
        }
        System.out.println("\t[3] <- Go back");
        System.out.println("\t[4] <<- Go to main page");
        System.out.println("\t[5] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                previousPage();
                clientsByExchanges();
                break;
            case 2:
                nextPageRegisteredClient();
                clientsByExchanges();
                break;
            case 3:
                pageNumGoForward();
                clientsByExchanges();
                break;
            case 4:
                pageNumGoForward();
                main();
                break;
            case 5:
                pageNumGoForward();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                clientsByExchanges();
                break;
        }
    }

    /**
     * It allows the manager to see the store's revenue by month
     */
    public void revenueByMonth() {
        System.out.print("\n ---- revenueByMonth ---- \n"); // Es para debug, borrar
        HashMap<Month, Double> revenueByMonth = Statistics.getINSTANCE().getRevenueByMonth();
        for (Map.Entry<Month, Double> entry : revenueByMonth.entrySet()) {
            System.out.printf(entry.getKey() + ": " + entry.getValue() + "€");
        }
    }

    /**
     * It allows the manager to see the store's categories by revenue
     */
    public void categoriesByRevenue() {
        System.out.print("\n ---- categoriesByRevenue ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to see the store's store products by sales, with their percentage over the overall sales
     */
    public void productBySalesWithPercentage() {
        System.out.print("\n ---- productBySalesWithPercentage ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to see the store's store products by sales, with their percentage over the overall sales,
     * on a certain month
     */
    public void productBySalesWithPercentageCertainMonth() {
        System.out.print("\n ---- productBySalesWithPercentageCertainMonth ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to manage the store's discounts
     */
    private void manageDiscounts() {
        System.out.print("\n ---- manageDiscounts ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to manage the store's parameters
     */
    private void manageParameters() throws IOException {
        System.out.print("\n ---- manageParameters ---- \n"); // Es para debug, borrar
        Manager manager = (Manager) currentUser;
        System.out.println("What do you wish to change? (enter the nº)");
        System.out.println("\t[1] Items per page: [" + Parameter.getParam().getItemsPerPage() + "]");
        System.out.println(
                "\t[2] Score a parameter: [" + Parameter.getParam().getScoreAParam() + "] <- scoreWeight = " +
                "a*<score> + b");
        System.out.println(
                "\t[3] Score b parameter: [" + Parameter.getParam().getScoreBParam() + "] <- scoreWeight = " +
                "a*<score> + b");
        System.out.println("\t[4] Offer time: [" + Parameter.getParam().getOfferTime() + "]");
        System.out.println("\t[5] Order time: [" + Parameter.getParam().getOrderTime() + "]");
        System.out.println("\t[6] Valuation cost: [" + Parameter.getParam().getValuationCost() + "]");
        System.out.println("\t[7] <- Go back");
        System.out.println("\t[8] <<- Go to main page");
        System.out.println("\t[9] x Exit app");
        int chosenOption3 = scanner.nextInt();

        switch (chosenOption3) {
            case 1:
                System.out.println("Enter the new Items per page value:");
                int newItemsPerPage = scanner.nextInt();
                manager.changeItemsPerPage(newItemsPerPage);
                break;
            case 2:
                System.out.println("Enter the new Score a parameter value:");
                int newScoreAParam = scanner.nextInt();
                manager.changeScoreAParam(newScoreAParam);
                break;
            case 3:
                System.out.println("Enter the new Score b parameter value:");
                int newScoreBParam = scanner.nextInt();
                manager.changeScoreBParam(newScoreBParam);
                break;
            case 4:
                System.out.println("Enter the new Offer time:");
                Period newOfferTime = Period.parse(scanner.next());
                manager.changeOfferTime(newOfferTime);
                break;
            case 5:
                System.out.println("Enter the new Order time:");
                Period newOrderTime = Period.parse(scanner.next());
                manager.changeOrderTime(newOrderTime);
                break;
            case 6:
                System.out.println("Enter the new Valuation cost:");
                double newValuationCost = scanner.nextDouble();
                manager.changeValuationCost(newValuationCost);
                break;
            case 7:
                managerLoop();
                break;
            case 8:
                main();
                break;
            case 9:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                manageParameters();
                break;
        }
    }

    /**
     * It allows the manager to see its profile and change their password
     */
    private void seeProfile() throws IOException { // DUE: Que esto sea opción en todos los loops de manager:/
        System.out.print("\n ---- seeProfile ---- \n"); // Es para debug, borrar
        currentUser.getPrintInfo();

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Change my password");
        System.out.println("\t[2] <- Go back");
        System.out.println("\t[3] <<- Go to main page");
        System.out.println("\t[4] x Exit app");
        chosenOption = scanner.nextInt();
        switch (chosenOption) {
            case 1:
                System.out.println("Enter new password:");
                String newPassword = scanner.next();
                currentUser.changePassword(newPassword);
                break;
            case 7:
                managerLoop();
                break;
            case 8:
                main();
                break;
            case 9:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                seeProfile();
                break;
        }
    }
}