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
    // DUE: Revisar cosas del Pager para que sea POO

    /** This loop's instance */
    private static ManagerLoop INSTANCE;
    /** The store's filtered list of products */
    private List<StoreProduct> filteredStore = null;

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
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< managerLoop >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage packs");
            System.out.println("\t[" + i++ + "] Manage store products");
            System.out.println("\t[" + i++ + "] Add store products");
            System.out.println("\t[" + i++ + "] Manage employees");
            System.out.println("\t[" + i++ + "] Generate statistics");
            System.out.println("\t[" + i++ + "] Manage discounts");
            System.out.println("\t[" + i++ + "] Manage parameters");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Manage packs */
                    managePacks();
                    break;
                case 2: /* Manage store products */
                    manageStoreProducts();
                    break;
                case 3: /* Add store product */
                    addStoreProduct();
                    break;
                case 4: /* Manage employees */
                    manageEmployees();
                    break;
                case 5: /* Generate statistics */
                    generateStatistics();
                    break;
                case 6: /* Manage discounts */
                    manageDiscounts();
                    break;
                case 7: /* Manage parameters */
                    manageParameters();
                    break;
                case 8: /* See profile */
                    seeProfile();
                    break;
                case 9: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to manage the store's packs
     */
    private void managePacks() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< nombre >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage a pack");
            System.out.println("\t[" + i++ + "] Add a pack");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Manage a pack */
                    System.out.print("\n <<<<<<<<<< seeStoreProduct >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Do you wish to select it via: ID or list number?");
                    System.out.println("[1] List number");
                    System.out.println("[2] ID");
                    int chosenOption2 = scanner.nextInt();

                    switch (chosenOption2) {
                        case 1:
                            System.out.println("Enter the number of the desired pack:");
                            this.itemNum = scanner.nextInt();
                            leavePagedScreen();
                            managePack();
                            break;
                        case 2:
                            System.out.println("Enter the ID of the desired ###:");
                            int packID = scanner.nextInt();
                            int index = Store.getInstance().getPackIndex(packID);
                            if (index != -1) {
                                currentScreenPageNum = Pager.getInstance().getPageNumFromIndex(index);
                                itemNum = Pager.getInstance().getItemNumFromIndex(index);

                                leavePagedScreen();
                                managePack();
                                break;
                            }
                            System.out.println("The ID wasn't valid");
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 2: /* Add a pack */
                    double price;
                    ArrayList<StoreProduct> products = new ArrayList<>();
                    boolean stop = false;
                    while (!appExited && !stop) {
                        // DUE: Podría ser un bucle de visualización paginado aparte
                        System.out.println("Enter the desired store product's id (type \"stop\" to use the ones " +
                                           "entered so far):");
                        String productId = scanner.next();
                        if (productId.equals("stop")) {
                            stop = true;
                        }
                        StoreProduct product = Store.getInstance().getStoreProductFromId(productId);
                        if (product == null) {
                            System.out.println("A category which such a name doesn't exist, reloading...");
                        } else {
                            products.add(product);
                        }
                    }
                    System.out.println("Enter the desired price:");
                    price = scanner.nextDouble();
                    ((Manager) currentUser).addPack(price, products, LocalDate.now());
                    System.out.println("The pack was added to the store");
                    break;
                case 3: /* See profile */
                    seeProfile();
                    break;
                case 4: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to manage a certain pack
     */
    private void managePack() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< nombre >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage price");
            System.out.println("\t[" + i++ + "] Add a product");
            System.out.println("\t[" + i++ + "] Remove a product");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Manage price */
                    // DUE
                    break;
                case 2: /* Add a product */
                    // DUE
                    break;
                case 3: /* Remove a product */
                    // DUE
                    break;
                case 4: /* See profile */
                    seeProfile();
                    break;
                case 5: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to manage the store's store products
     */
    private void manageStoreProducts() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!exitLoop) {
            System.out.print("\n <<<<<<<<<< manageStoreProducts >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);

            if (filteredStore == null) { // Si no hay filtros se muestran todos los productos
                this.filteredStore = ((Manager) currentUser).searchStoreProducts();
            }
            Pager.getInstance().printStoreProductListPage(filteredStore, currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Add a filter to the search");
            System.out.println("\t[" + i++ + "] Manage a product");
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Filter search */
                    this.filteredStore = filterSearch();
                    System.out.println("Applying filters...");
                    break;
                case 2:
                    System.out.print("\n <<<<<<<<<< manageProduct >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Enter the number of the desired product:");
                    int productNum = scanner.nextInt();

                    StoreProduct product = Pager.getInstance()
                                                .selectStoreProductFromPage(filteredStore, currentScreenPageNum,
                                                        productNum);
                    ProductType type = product.getType();
                    leavePagedScreen();

                    switch (type) {
                        case COMIC:
                            manageComic((Comic) product);
                        case GAME:
                            assert product instanceof Game;
                            manageGame((Game) product);
                        case FIGURINE:
                            assert product instanceof Figurine;
                            manageFigurine((Figurine) product);
                        default: /* Go back */ // Este NO debería saltar nunca, lo pongo por si acaso
                            System.out.println("You shouldn't be able to see this :(");
                            break;
                    }

                    break;
                case 3: /* Previous page */
                    previousPage();
                    break;
                case 4: /* Next page */
                    nextPageStoreProduct(filteredStore);
                    break;
                case 5: /* See profile */
                    seeProfile();
                    break;
                case 6: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to filter the store's store products
     * @return the filtered list of store products
     * @throws IOException the io exception
     */
    public List<StoreProduct> filterSearch() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose which filter you wish to apply next (those already applied will be reset)");
        int i = 1;
        List<Category> categories = new ArrayList<>();
        System.out.println("\t[" + i++ + "] Categories");
        System.out.println("\t[" + i++ + "] Price");
        System.out.println("\t[" + i++ + "] Review Score");
        System.out.println("\t[" + i++ + "] Change search order");
        basicLoopPrinter(i);
        chosenOption = scanner.nextInt();

        boolean stop = false;
        switch (chosenOption) {
            case 1: /* Filter by categories */
                while (!appExited && !stop) {
                    System.out.println("Enter the desired category (type \"stop\" to apply the ones entered so far):");
                    String categoryName = scanner.next();
                    if (categoryName.equals("stop")) {
                        stop = true;
                    }
                    Category category = Store.getInstance().getCategoryFromName(categoryName);
                    if (category == null) {
                        System.out.println("A category which such a name doesn't exist, reloading...");
                    } else {
                        categories.add(category);
                    }
                }
                break;
            case 2: /* Filter by price */
                while (!appExited && !stop) {
                    System.out.println("Enter the minimum desired price:");
                    int minPrice = scanner.nextInt();
                    System.out.println("Enter the maximum desired price:");
                    int maxPrice = scanner.nextInt();
                    if (maxPrice < minPrice) {
                        System.out.println("Maximum price must be greater or equal to minimum price, reloading...");
                    } else {
                        currentUser.addPriceFilter(minPrice, maxPrice);
                        stop = true;
                    }
                }
                break;
            case 3: /* Filter by review score */
                while (!appExited && !stop) {
                    System.out.println("Enter the minimum desired review score:");
                    int minScore = scanner.nextInt();
                    System.out.println("Enter the maximum desired review score:");
                    int maxScore = scanner.nextInt();
                    if (maxScore < minScore) {
                        System.out.println("Maximum score must be greater or equal to minimum score, reloading...");
                    } else {
                        currentUser.addPunctuationFilter(minScore, maxScore);
                        stop = true;
                    }
                }
                break;
            case 4: /* Change search order */
                currentUser.toggleSearchOrder();
            case 5: /* Exit */
                exit();
                break;
            default: /* Go back */
                break;
        }

        return ((Manager) currentUser).searchStoreProductByCategory(categories.toArray(new Category[0]));
    }

    /**
     * It allows the manager to tweak a comic's information
     * @param comic the desired comic
     * @throws IOException the io exception
     */
    private void manageComic(Comic comic) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< manageComic >>>>>>>>>> \n"); // Es para debug, borrar
            comic.printAllInfo();

            System.out.println("What do you wish to change? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Name");
            System.out.println("\t[" + i++ + "] Description");
            System.out.println("\t[" + i++ + "] Price");
            System.out.println("\t[" + i++ + "] Photo");
            System.out.println("\t[" + i++ + "] Stock");
            System.out.println("\t[" + i++ + "] Categories");
            System.out.println("\t[" + i++ + "] Number of pages");
            System.out.println("\t[" + i++ + "] Author");
            System.out.println("\t[" + i++ + "] Editorial");
            System.out.println("\t[" + i++ + "] Publishing year");
            basicLoopPrinter(i);
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
                case 11: /* See profile */
                    seeProfile();
                    break;
                case 12: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to tweak a game's information
     * @param game the desired game
     * @throws IOException the io exception
     */
    private void manageGame(Game game) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< manageGame >>>>>>>>>> \n"); // Es para debug, borrar
            game.printAllInfo();

            System.out.println("What do you wish to change? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Name");
            System.out.println("\t[" + i++ + "] Description");
            System.out.println("\t[" + i++ + "] Price");
            System.out.println("\t[" + i++ + "] Photo");
            System.out.println("\t[" + i++ + "] Stock");
            System.out.println("\t[" + i++ + "] Categories");
            System.out.println("\t[" + i++ + "] Number of players");
            System.out.println("\t[" + i++ + "] Age range");
            System.out.println("\t[" + i++ + "] Game Style");
            basicLoopPrinter(i);
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
                    System.out.println(
                            "Enter the product's new Game Style (" + GameStyle.CARDS.getFormatedName() + "/" +
                            GameStyle.DICE.getFormatedName() + "/" + GameStyle.GAMEBOARD.getFormatedName() + "/" +
                            GameStyle.MINIATURE.getFormatedName() + "):");
                    GameStyle newGameStyle = GameStyle.valueOf(scanner.next());
                    game.setGameStyle(newGameStyle);
                    break;
                case 10: /* See profile */
                    seeProfile();
                    break;
                case 11: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to tweak a figurine's information
     * @param figurine the desired figurine
     * @throws IOException the io exception
     */
    private void manageFigurine(Figurine figurine) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< manageFigurine >>>>>>>>>> \n"); // Es para debug, borrar
            figurine.printAllInfo();

            System.out.println("What do you wish to change? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Name");
            System.out.println("\t[" + i++ + "] Description");
            System.out.println("\t[" + i++ + "] Price");
            System.out.println("\t[" + i++ + "] Photo");
            System.out.println("\t[" + i++ + "] Stock");
            System.out.println("\t[" + i++ + "] Categories");
            System.out.println("\t[" + i++ + "] Brand");
            System.out.println("\t[" + i++ + "] Material");
            System.out.println("\t[" + i++ + "] Dimensions");
            basicLoopPrinter(i);
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
                case 10: /* See profile */
                    seeProfile();
                    break;
                case 11: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows a manager to tweak a store product's categories
     * @param storeProduct the desired store product
     */
    private void categoryChanger(StoreProduct storeProduct) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            String categoryName, newCategoryName;
            Category category, newCategory;
            System.out.print("\n <<<<<<<<<< categoryChanger >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Replace an existing product's category");
            System.out.println("\t[" + i++ + "] Add a new category to the product");
            System.out.println("\t[" + i + "] Remove a product's category");
            chosenOption = scanner.nextInt();
            switch (chosenOption) {
                case 1:
                    System.out.println("Which category do you want to change? (enter its name):");
                    categoryName = scanner.next();
                    category = Store.getInstance().getCategoryFromName(categoryName);
                    if (category == null) {
                        //System.out.println("A category which such a name doesn't exist, reloading...");
                        //categoryChanger(storeProduct);
                        exit(); // idk anymore
                        break;
                    }
                    System.out.println("Enter the replacement category's name:");
                    newCategoryName = scanner.next();
                    newCategory = Store.getInstance().getCategoryFromName(newCategoryName);
                    if (newCategory == null) {
                        //System.out.println("A category which such a name doesn't exist, reloading...");
                        //categoryChanger(storeProduct);
                        exit(); // idk anymore
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
                        //System.out.println("A category which such a name doesn't exist, reloading...");
                        //categoryChanger(storeProduct);
                        exit(); // idk anymore
                        break;
                    }
                    storeProduct.addCategory(category);
                    break;
                case 3:
                    System.out.println("Which category do you want to add? (enter its name):");
                    categoryName = scanner.next();
                    category = Store.getInstance().getCategoryFromName(categoryName);
                    if (category == null) {
                        //System.out.println("A category which such a name doesn't exist, reloading...");
                        //categoryChanger(storeProduct);
                        exit(); // idk anymore
                        break;
                    }
                    storeProduct.removeCategory(category);
                    break;
                case 4: /* See profile */
                    seeProfile();
                    break;
                case 5: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to add a store product to the store
     */
    private void addStoreProduct() {
        System.out.print("\n <<<<<<<<<< addStoreProduct >>>>>>>>>> \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to manage the store's employees
     */
    private void manageEmployees() {
        System.out.print("\n <<<<<<<<<< manageEmployees >>>>>>>>>> \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to generate several statistics
     * @throws IOException the io exception
     */
    private void generateStatistics() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< generateStatistics >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Which statistic do you wish to generate? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] List of store products by sales");
            System.out.println("\t[" + i++ + "] List of clients by orders");
            System.out.println("\t[" + i++ + "] List of clients by exchanges");
            System.out.println("\t[" + i++ + "] List of revenue by month");
            System.out.println("\t[" + i++ + "] List of categories by revenue");
            System.out.println(
                    "\t[" + i++ + "] List of store products by sales with percentage regarding total revenues");
            System.out.println(
                    "\t[" + i++ + "] List of store products by sales with percentage regarding total revenues on a " +
                    "certain month");
            System.out.println("\t[" + i++ + "] Store's total revenue");
            System.out.println("\t[" + i++ + "] Store's total valuation's revenue");
            System.out.println("\t[" + i++ + "] A certain category's revenue");
            // No sé si es útil-> System.out.println("\t[11] A certain store product's revenue");
            // No sé si es útil-> System.out.println("\t[12] A certain client's number of orders");
            // No sé si es útil-> System.out.println("\t[13] A certain client's number of exchanges");
            basicLoopPrinter(i);
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
                        //generateStatistics();
                        break;
                    }
                    System.out.println("The " + categoryName + " category's total revenue is " +
                                       Statistics.getINSTANCE().getRevenueByCategory(categoryName) + "€");
                    break;
                case 11: /* See profile */
                    seeProfile();
                    break;
                case 12: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to see the store's store products by their sales
     * @throws IOException the io exception
     */
    public void productBySales() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< productBySales >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);
            List<StoreProduct> products = Statistics.getINSTANCE().getProductsBySales();
            Pager.getInstance().printStoreProductListPage(products, currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1:
                    previousPage();
                    //productBySales();
                    break;
                case 2:
                    nextPageStoreProduct(products);
                    //productBySales();
                    break;
                case 3: /* See profile */
                    seeProfile();
                    break;
                case 4: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }

        }
    }

    /**
     * It allows the manager to see the store's registered clients by number of orders
     * @throws IOException the io exception
     */
    public void clientsByOrders() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< clientsByOrders >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);
            Store.getInstance().printRegisteredClientListPage(currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1:
                    previousPage();
                    //clientsByOrders();
                    break;
                case 2:
                    nextPageRegisteredClient();
                    //clientsByOrders();
                    break;
                case 3: /* See profile */
                    seeProfile();
                    break;
                case 4: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;

            }
        }
    }

    /**
     * It allows the manager to see the store's registered clients by number of exchanges
     * @throws IOException the io exception
     */
    public void clientsByExchanges() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< clientsByExchanges >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);
            Store.getInstance().printRegisteredClientListPage(currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1:
                    previousPage();
                    //clientsByExchanges();
                    break;
                case 2:
                    nextPageRegisteredClient();
                    //clientsByExchanges();
                    break;
                case 3: /* See profile */
                    seeProfile();
                    break;
                case 4: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }

        }
    }

    /**
     * It allows the manager to see the store's revenue by month
     */
    public void revenueByMonth() {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< revenueByMonth >>>>>>>>>> \n"); // Es para debug, borrar
            HashMap<Month, Double> revenueByMonth = Statistics.getINSTANCE().getRevenueByMonth();
            for (Map.Entry<Month, Double> entry : revenueByMonth.entrySet()) {
                System.out.printf(entry.getKey() + ": " + entry.getValue() + "€");
            } // DUE
        }
    }

    /**
     * It allows the manager to see the store's categories by revenue
     */
    public void categoriesByRevenue() {
        System.out.print("\n <<<<<<<<<< categoriesByRevenue >>>>>>>>>> \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to see the store's store products by sales, with their percentage over the overall sales
     */
    public void productBySalesWithPercentage() {
        System.out.print("\n <<<<<<<<<< productBySalesWithPercentage >>>>>>>>>> \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to see the store's store products by sales, with their percentage over the overall sales,
     * on a certain month
     */
    public void productBySalesWithPercentageCertainMonth() {
        System.out.print(
                "\n <<<<<<<<<< productBySalesWithPercentageCertainMonth >>>>>>>>>> \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to manage the store's discounts
     */
    private void manageDiscounts() {
        System.out.print("\n <<<<<<<<<< manageDiscounts >>>>>>>>>> \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It allows the manager to manage the store's parameters
     */
    private void manageParameters() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< manageParameters >>>>>>>>>> \n"); // Es para debug, borrar
            Manager manager = (Manager) currentUser;
            System.out.println("What do you wish to change? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Items per page: [" + Parameter.getParam().getItemsPerPage() + "]");
            System.out.println("\t[" + i++ + "] Score a parameter: [" + Parameter.getParam().getScoreAParam() +
                               "] <- scoreWeight = " + "a*<score> + b");
            System.out.println("\t[" + i++ + "] Score b parameter: [" + Parameter.getParam().getScoreBParam() +
                               "] <- scoreWeight = " + "a*<score> + b");
            System.out.println("\t[" + i++ + "] Offer time: [" + Parameter.getParam().getOfferTime() + "]");
            System.out.println("\t[" + i++ + "] Order time: [" + Parameter.getParam().getOrderTime() + "]");
            System.out.println("\t[" + i++ + "] Valuation cost: [" + Parameter.getParam().getValuationCost() + "]");
            basicLoopPrinter(i);
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
                case 7: /* See profile */
                    seeProfile();
                    break;
                case 8: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to see its profile and change their password
     */
    private void seeProfile() throws IOException { // DUE: Que esto sea opción en todos los loops de manager:/
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< seeProfile >>>>>>>>>> \n"); // Es para debug, borrar
            currentUser.getPrintInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Change my password");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();
            if (chosenOption == 1) {
                System.out.println("Enter new password:");
                String newPassword = scanner.next();
                currentUser.changePassword(newPassword);
            } else {
                exit();
            }
        }

    }
}