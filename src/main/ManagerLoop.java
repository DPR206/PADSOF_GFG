package main;

import discount.*;
import product.*;
import store.*;
import user.*;

import java.io.IOException;
import java.time.*;
import java.util.*;

/**
 * It implements the manager's loop
 * @author Ana O.R.
 * @version 1.0
 */
public class ManagerLoop extends Loop {
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
            System.out.println("\n <<<<<<<<<< managePacks >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage a pack");
            System.out.println("\t[" + i++ + "] Add a pack");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Manage a pack */
                    System.out.print("\n <<<<<<<<<< managePack >>>>>>>>>> \n"); // Es para debug, borrar
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
                            System.out.println("Enter the ID of the desired pack:");
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
            System.out.println("\n <<<<<<<<<< managePack >>>>>>>>>> \n"); // Es para debug, borrar

            Pack pack = Pager.getInstance()
                             .selectPackFromPage(Store.getInstance().getPacks(), currentScreenPageNum, itemNum);
            pack.bigPrintInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage price");
            System.out.println("\t[" + i++ + "] Add a product");
            System.out.println("\t[" + i++ + "] Remove a product");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            String id;
            switch (chosenOption) {
                case 1: /* Manage price */
                    System.out.println("Enter the new price:");
                    double price = scanner.nextDouble();
                    pack.setPrice(price);
                    break;
                case 2: /* Add a product */
                    System.out.println("Enter the new product's id:");
                    id = scanner.next();
                    pack.addProduct(Store.getInstance().getStoreProductFromId(id));
                    break;
                case 3: /* Remove a product */
                    System.out.println("Enter the new product's id:");
                    id = scanner.next();
                    pack.eliminateProduct(Store.getInstance().getStoreProductFromId(id));
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
            Category category = null, newCategory = null;
            System.out.print("\n <<<<<<<<<< categoryChanger >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Replace an existing product's category");
            System.out.println("\t[" + i++ + "] Add a new category to the product");
            System.out.println("\t[" + i + "] Remove a product's category");
            chosenOption = scanner.nextInt();

            boolean stop = false;
            switch (chosenOption) {
                case 1:
                    while (!appExited && !stop) {
                        System.out.println("Which category do you want to change? (enter its name):");
                        categoryName = scanner.next();
                        category = Store.getInstance().getCategoryFromName(categoryName);
                        if (category == null) {
                            System.out.println("A category which such a name doesn't exist, reloading...");
                        } else {
                            stop = true;
                        }
                    }
                    stop = false;
                    while (!appExited && !stop) {
                        System.out.println("Enter the replacement category's name:");
                        newCategoryName = scanner.next();
                        newCategory = Store.getInstance().getCategoryFromName(newCategoryName);
                        if (newCategory == null) {
                            System.out.println("A category which such a name doesn't exist, reloading...");
                        } else {
                            stop = true;
                        }
                    }
                    storeProduct.removeCategory(category);
                    storeProduct.addCategory(newCategory);
                    break;
                case 2:
                    while (!appExited && !stop) {
                        System.out.println("Which category do you want to add? (enter its name):");
                        categoryName = scanner.next();
                        category = Store.getInstance().getCategoryFromName(categoryName);
                        if (category == null) {
                            System.out.println("A category which such a name doesn't exist, reloading...");
                        } else {
                            stop = true;
                        }
                    }
                    storeProduct.addCategory(category);
                    break;
                case 3:
                    while (!appExited && !stop) {
                        System.out.println("Which category do you want to add? (enter its name):");
                        categoryName = scanner.next();
                        category = Store.getInstance().getCategoryFromName(categoryName);
                        if (category == null) {
                            System.out.println("A category which such a name doesn't exist, reloading...");
                        } else {
                            stop = true;
                        }
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
    private void addStoreProduct() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< addStoreProduct >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Add a comic manually");
            System.out.println("\t[" + i++ + "] Add a game manually");
            System.out.println("\t[" + i++ + "] Add a figurine manually");
            System.out.println("\t[" + i++ + "] Add products via file upload");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            double price;
            int stock;
            String name, description, photo;
            boolean stop = false;
            List<Category> categories = new ArrayList<>();
            switch (chosenOption) {
                case 1: /* Add a comic manually */
                    System.out.println("Enter the comic's price:");
                    price = scanner.nextDouble();
                    System.out.println("Enter the comic's name:");
                    name = scanner.next();
                    System.out.println("Enter the comic's description:");
                    description = scanner.next();
                    System.out.println("Enter the comic photo's path:");
                    photo = scanner.next();
                    System.out.println("Enter the comic's stock:");
                    stock = scanner.nextInt();
                    System.out.println("Enter the comic's number of pages:");
                    int numPages = scanner.nextInt();
                    System.out.println("Enter the comic's year:");
                    Year year = Year.parse(scanner.next());
                    System.out.println("Enter the comic's author:");
                    String author = scanner.next();
                    System.out.println("Enter the comic's editorial:");
                    String editorial = scanner.next();
                    while (!appExited && !stop) {
                        System.out.println(
                                "Enter the comic's category (type \"stop\" to apply the ones entered so far):");
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
                    ((Manager) currentUser).addComic(price, name, description, photo, stock, numPages, year, author,
                            editorial, categories.toArray(new Category[0]));
                    break;
                case 2: /* Add a game manually */
                    System.out.println("Enter the game's price:");
                    price = scanner.nextDouble();
                    System.out.println("Enter the game's name:");
                    name = scanner.next();
                    System.out.println("Enter the game's description:");
                    description = scanner.next();
                    System.out.println("Enter the game photo's path:");
                    photo = scanner.next();
                    System.out.println("Enter the game's stock:");
                    stock = scanner.nextInt();
                    System.out.println("Enter the game's number of players:");
                    int numPlayers = scanner.nextInt();
                    System.out.println("Enter the game's age range:");
                    String ageRange = scanner.next();
                    System.out.println("Enter the product 's Game Style (" + GameStyle.CARDS.getFormatedName() + "/" +
                                       GameStyle.DICE.getFormatedName() + "/" + GameStyle.GAMEBOARD.getFormatedName() +
                                       "/" + GameStyle.MINIATURE.getFormatedName() + "):");
                    GameStyle gameStyle = GameStyle.valueOf(scanner.next());
                    while (!appExited && !stop) {
                        System.out.println(
                                "Enter the game's category (type \"stop\" to apply the ones entered so far):");
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
                    ((Manager) currentUser).addGame(price, name, description, photo, stock, numPlayers, ageRange,
                            gameStyle, categories.toArray(new Category[0]));
                    break;
                case 3: /* Add a figurine manually */
                    System.out.println("Enter the figurine's price:");
                    price = scanner.nextDouble();
                    System.out.println("Enter the figurine's name:");
                    name = scanner.next();
                    System.out.println("Enter the figurine's description:");
                    description = scanner.next();
                    System.out.println("Enter the figurine photo's path:");
                    photo = scanner.next();
                    System.out.println("Enter the figurine's stock:");
                    stock = scanner.nextInt();
                    System.out.println("Enter the figurine's dimensions:");
                    String dimensions = scanner.next();
                    System.out.println("Enter the figurine's brand:");
                    String brand = scanner.next();
                    System.out.println("Enter the figurine's material:");
                    String material = scanner.next();
                    while (!appExited && !stop) {
                        System.out.println(
                                "Enter the figurine's category (type \"stop\" to apply the ones entered so far):");
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
                    ((Manager) currentUser).addFigurine(price, name, description, photo, stock, dimensions, brand,
                            material, categories.toArray(new Category[0]));
                    break;
                case 4:
                    System.out.println("Enter the file's name (without the extension):");
                    String fileName = scanner.next();
                    fileName = "resources\\" + fileName + ".csv";
                    ((Manager) currentUser).addProductFromFile(fileName);
                    break;
                case 6: /* See profile */
                    seeProfile();
                    break;
                case 7: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to manage the store's employees
     */
    private void manageEmployees() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< manageEmployees >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage an employee");
            System.out.println("\t[" + i++ + "] Add an employee");
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Manage an employee */
                    System.out.print("\n <<<<<<<<<< manageEmployee >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Do you wish to select it via: ID or list number?");
                    System.out.println("[1] List number");
                    System.out.println("[2] Username");
                    int chosenOption2 = scanner.nextInt();

                    switch (chosenOption2) {
                        case 1:
                            System.out.println("Enter the number of the desired employee:");
                            this.itemNum = scanner.nextInt();
                            leavePagedScreen();
                            manageEmployee();
                            break;
                        case 2:
                            System.out.println("Enter the username of the desired employee:");
                            String username = scanner.next();
                            int index = Store.getInstance().getEmployeeIndex(username);
                            if (index != -1) {
                                currentScreenPageNum = Pager.getInstance().getPageNumFromIndex(index);
                                itemNum = Pager.getInstance().getItemNumFromIndex(index);

                                leavePagedScreen();
                                manageEmployee();
                                break;
                            }
                            System.out.println("The ID wasn't valid");
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 2: /* Add an employee */
                    System.out.println("Enter the employee's password:");
                    String password = scanner.next();
                    System.out.println("Enter the employee's username:");
                    String username = scanner.next();
                    System.out.println("Enter the employee's Permission (" + Permission.EXCHANGE.getMeaning() + "/" +
                                       Permission.ORDER.getMeaning() + "/" + Permission.STORE.getMeaning() + "):");
                    Permission permission = Permission.valueOf(scanner.next());
                    ((Manager) currentUser).addEmployee(password, username, permission);
                    break;
                case 3: /* Previous page */
                    previousPage();
                    break;
                case 4: /* Next page */
                    nextPageEmployee();
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
     * It allows the manager to manage a certain employee
     */
    private void manageEmployee() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< manageEmployee 2 >>>>>>>>>> \n"); // Es para debug, borrar
            Employee employee = Store.getInstance().selectEmployeeFromPage(currentScreenPageNum, itemNum);
            employee.printInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Change password");
            System.out.println("\t[" + i++ + "] Add a permission");
            System.out.println("\t[" + i++ + "] Remove a permission");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Change password */
                    System.out.println("Enter the employee's new password:");
                    String newPassword = scanner.next();
                    employee.changePassword(newPassword);
                    break;
                case 3: /* Add a permission */
                    System.out.println(
                            "Enter the employee's new Permission (" + Permission.EXCHANGE.getMeaning() + "/" +
                            Permission.ORDER.getMeaning() + "/" + Permission.STORE.getMeaning() + "):");
                    Permission permission = Permission.valueOf(scanner.next());
                    switch (permission) {
                        case EXCHANGE:
                            employee.setEp(new ExchangePermission());
                            break;
                        case ORDER:
                            employee.setOp(new OrderPermission(true));
                            break;
                        case STORE:
                            employee.setSp(new StorePermission());
                            break;
                        default:
                            System.out.println("Invalid permission");
                            break;
                    }
                    break;
                case 4: /* Remove a permission */
                    signer();
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
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* List of store products by sales */
                    productBySales();
                    break;
                case 2: /* List of clients by orders */
                    clientsByOrders();
                    break;
                case 3: /* List of clients by exchanges */
                    clientsByExchanges();
                    break;
                case 4: /* List of revenue by month */
                    revenueByMonth();
                    break;
                case 5: /* List of categories by revenue */
                    categoriesByRevenue();
                    break;
                case 6: /* List of store products by sales with percentage regarding total revenues */
                    productBySalesWithPercentage();
                    break;
                case 7: /* List of store products by sales with percentage regarding total revenues on a certain
                month */
                    productBySalesWithPercentageCertainMonth();
                    break;
                case 8: /* Store's total revenue */
                    System.out.println("The store's total revenue is " + Statistics.getTotal_revenue() + "€");
                    break;
                case 9: /* Store's total valuation's revenue */
                    System.out.println("The store's total revenue from valuations is " +
                                       Statistics.getINSTANCE().getRevenue_valuation() + "€");
                    break;
                case 10: /* A certain category's revenue */
                    boolean stop = false;
                    String categoryName = null;
                    Category category;
                    while (!stop) {
                        System.out.println("Which category do you want to see? (enter its name):");
                        categoryName = scanner.next();
                        category = Store.getInstance().getCategoryFromName(categoryName);
                        if (category == null) {
                            System.out.println("A category which such a name doesn't exist, reloading...");
                            break;
                        } else {
                            stop = true;
                        }
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
                case 1: /* Previous page */
                    previousPage();
                    break;
                case 2: /* Next page */
                    nextPageStoreProduct(products);
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
            List<RegisteredClient> users = new ArrayList<>(Statistics.getINSTANCE().getUsersMostOrders());
            Pager.getInstance().printRegisteredClientListPage(users, currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Previous page */
                    previousPage();
                    break;
                case 2: /* Next page */
                    nextPageRegisteredClient(users);
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
            List<RegisteredClient> users = new ArrayList<>(Statistics.getINSTANCE().getUsersMostExchanges());
            Pager.getInstance().printRegisteredClientListPage(users, currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Previous page */
                    previousPage();
                    break;
                case 2: /* Next page */
                    nextPageRegisteredClient(users);
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
     * @throws IOException the io exception
     */
    public void revenueByMonth() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< revenueByMonth >>>>>>>>>> \n"); // Es para debug, borrar
            HashMap<Month, Double> revenueByMonth = Statistics.getINSTANCE().getRevenueByMonth();
            for (Map.Entry<Month, Double> entry : revenueByMonth.entrySet()) {
                System.out.printf(entry.getKey() + ": " + entry.getValue() + "€");
            }

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* See profile */
                    seeProfile();
                    break;
                case 2: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the manager to see the store's categories by revenue
     * @throws IOException the io exception
     */
    public void categoriesByRevenue() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< categoriesByRevenue >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);
            HashMap<Category, Double> categories = Statistics.getINSTANCE().getRevenueAllCategories();
            Pager.getInstance().printCategoryHashMapPage(categories, currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Previous page */
                    previousPage();
                    break;
                case 2: /* Next page */
                    nextPageCategory(new ArrayList<>(categories.keySet()));
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
     * It allows the manager to see the store's store products by sales, with their percentage over the overall sales
     * @throws IOException the io exception
     */
    public void productBySalesWithPercentage() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< productBySalesWithPercentage >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);
            HashMap<StoreProduct, String> products = Statistics.getINSTANCE().getProductsTotalPercentage();
            Pager.getInstance().printStoreProductHashMapPage(products, currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Previous page */
                    previousPage();
                    break;
                case 2: /* Next page */
                    nextPageStoreProduct(new ArrayList<>(products.keySet()));
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
     * It allows the manager to see the store's store products by sales, with their percentage over the overall sales,
     * on a certain month
     * @throws IOException the io exception
     */
    public void productBySalesWithPercentageCertainMonth() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print(
                    "\n <<<<<<<<<< productBySalesWithPercentageCertainMonth >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);

            System.out.println("Which month do you want to see? (January/February...)");
            Month month = Month.valueOf(scanner.next());

            HashMap<StoreProduct, String> products = Statistics.getINSTANCE().getProductsTotalPercentage(month);
            Pager.getInstance().printStoreProductHashMapPage(products, currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Previous page */
                    previousPage();
                    break;
                case 2: /* Next page */
                    nextPageStoreProduct(new ArrayList<>(products.keySet()));
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
     * It allows the manager to manage the store's discounts
     * @throws IOException the io exception
     */
    private void manageDiscounts() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< manageDiscounts >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage a discount");
            System.out.println("\t[" + i++ + "] Add a discount");
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Manage an employee */
                    System.out.print("\n <<<<<<<<<< manageDiscount >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Do you wish to select it via: ID or list number?");
                    System.out.println("[1] List number");
                    System.out.println("[2] Username");
                    int chosenOption2 = scanner.nextInt();

                    switch (chosenOption2) {
                        case 1:
                            System.out.println("Enter the number of the desired discount:");
                            this.itemNum = scanner.nextInt();
                            leavePagedScreen();
                            manageDiscount();
                            break;
                        case 2:
                            System.out.println("Enter the ID of the desired discount:");
                            String discountId = scanner.next();
                            int index = Store.getInstance().getDiscountIndex(discountId);
                            if (index != -1) {
                                currentScreenPageNum = Pager.getInstance().getPageNumFromIndex(index);
                                itemNum = Pager.getInstance().getItemNumFromIndex(index);

                                leavePagedScreen();
                                manageDiscount();
                                break;
                            }
                            System.out.println("The ID wasn't valid");
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 2: /* Add a discount */
                    System.out.println("Enter the discount's start date: (example: 2007-12-03T10:15:30.)");
                    LocalDateTime startDate = LocalDateTime.parse(scanner.next());
                    System.out.println("Enter the discount's end date: (example: 2008-12-03T10:15:30.)");
                    LocalDateTime endDate = LocalDateTime.parse(scanner.next());

                    System.out.println("What type of discount do you want to create?");
                    int j = 1;
                    System.out.println("\t[" + j++ + "] Fixed percentage");
                    System.out.println("\t[" + j++ + "] Gift");
                    System.out.println("\t[" + j++ + "] Quantity");
                    System.out.println("\t[" + j + "] Volume");
                    int chosenOption3 = scanner.nextInt();

                    double percentage, spendingThreshold, deduction;
                    int numThreshold;
                    StoreProduct gift;
                    switch (chosenOption3) {
                        case 1: /* Fixed percentage */
                            System.out.println("Enter the discount's percentage:");
                            percentage = scanner.nextDouble();

                            switch (askDiscountCoverage()) {
                                case PRODUCT:
                                    System.out.println("Will the discount affect the whole store? (y/n)");
                                    String answer = scanner.next();
                                    if (answer.equalsIgnoreCase("y")) {
                                        new ProductFixedPercentage(startDate, endDate, percentage, true);
                                    } else {
                                        new ProductFixedPercentage(startDate, endDate, percentage,
                                                askDiscountProducts().toArray(new StoreProduct[0]));
                                    }
                                    break;
                                case PACK:
                                    new PackFixedPercentage(startDate, endDate, percentage,
                                            askDiscountPacks().toArray(new Pack[0]));
                                case CATEGORY:
                                    new CategoryFixedPercentage(startDate, endDate, percentage,
                                            askDiscountCategories().toArray(new Category[0]));
                                default:
                                    System.out.println("Invalid option");
                            }
                        case 2: /* Gift */
                            System.out.println("Enter the discount's spending threshold");
                            spendingThreshold = scanner.nextDouble();
                            System.out.println("Enter the gift's Id");
                            String giftId = scanner.next();
                            gift = Store.getInstance().getStoreProductFromId(giftId);

                            switch (askDiscountCoverage()) {
                                case PRODUCT:
                                    System.out.println("Will the discount affect the whole store? (y/n)");
                                    String answer = scanner.next();
                                    if (answer.equalsIgnoreCase("y")) {
                                        new ProductGift(startDate, endDate, spendingThreshold, gift, true);
                                    } else {
                                        new ProductGift(startDate, endDate, spendingThreshold, gift,
                                                askDiscountProducts().toArray(new StoreProduct[0]));
                                    }
                                    break;
                                case PACK:
                                    new PackGift(startDate, endDate, spendingThreshold, gift,
                                            askDiscountPacks().toArray(new Pack[0]));
                                case CATEGORY:
                                    new CategoryGift(startDate, endDate, spendingThreshold, gift,
                                            askDiscountCategories().toArray(new Category[0]));
                                default:
                                    System.out.println("Invalid option");
                            }
                        case 3: /* Quantity */
                            System.out.println("Enter the discount's number of units threshold:");
                            numThreshold = scanner.nextInt();
                            System.out.println("Enter the discount's deduction:");
                            deduction = scanner.nextDouble();

                            switch (askDiscountCoverage()) {
                                case PRODUCT:
                                    System.out.println("Will the discount affect the whole store? (y/n)");
                                    String answer = scanner.next();
                                    if (answer.equalsIgnoreCase("y")) {
                                        new ProductQuantity(startDate, endDate, numThreshold, deduction, true);
                                    } else {
                                        new ProductQuantity(startDate, endDate, numThreshold, deduction,
                                                askDiscountProducts().toArray(new StoreProduct[0]));
                                    }
                                    break;
                                case PACK:
                                    new PackQuantity(startDate, endDate, numThreshold, deduction,
                                            askDiscountPacks().toArray(new Pack[0]));
                                case CATEGORY:
                                    new CategoryQuantity(startDate, endDate, numThreshold, deduction,
                                            askDiscountCategories().toArray(new Category[0]));
                                default:
                                    System.out.println("Invalid option");
                            }
                        case 4: /* Volume */
                            System.out.println("Enter the discount's spending threshold:");
                            spendingThreshold = scanner.nextDouble();
                            System.out.println("Enter the discount's deduction:");
                            deduction = scanner.nextDouble();

                            switch (askDiscountCoverage()) {
                                case PRODUCT:
                                    System.out.println("Will the discount affect the whole store? (y/n)");
                                    String answer = scanner.next();
                                    if (answer.equalsIgnoreCase("y")) {
                                        new ProductVolume(startDate, endDate, spendingThreshold, deduction, true);
                                    } else {
                                        new ProductVolume(startDate, endDate, spendingThreshold, deduction,
                                                askDiscountProducts().toArray(new StoreProduct[0]));
                                    }
                                    break;
                                case PACK:
                                    new PackVolume(startDate, endDate, spendingThreshold, deduction,
                                            askDiscountPacks().toArray(new Pack[0]));
                                case CATEGORY:
                                    new CategoryVolume(startDate, endDate, spendingThreshold, deduction,
                                            askDiscountCategories().toArray(new Category[0]));
                                default:
                                    System.out.println("Invalid option");
                            }
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 3: /* Previous page */
                    previousPage();
                    break;
                case 4: /* Next page */
                    nextPageDiscount();
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
     * It asks and returns the discount coverage that a discount will have
     * @return the discount coverage that a discount will have
     */
    private DiscountCoverage askDiscountCoverage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which discount coverage do you want to use?");
        int i = 1;
        System.out.println("\t[" + i++ + "] Category");
        System.out.println("\t[" + i++ + "] Pack");
        System.out.println("\t[" + i + "] Store Product");

        return DiscountCoverage.valueOf(scanner.next());
    }

    /**
     * It asks and returns the store products that a discount will be placed over
     * @return the store products that a discount will be placed over
     */
    private List<StoreProduct> askDiscountProducts() {
        List<StoreProduct> products = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
            System.out.println("Enter the desired store product's id (\"stop\" to apply those already selected):");
            String id = scanner.next();
            if (id.equalsIgnoreCase("stop")) {
                stop = true;
            }
            StoreProduct product = Store.getInstance().getStoreProductFromId(id);
            if (product == null) {
                System.out.println("A store product with that id doesn't exist, reloading...");
            } else {
                products.add(product);
                System.out.println("Product added to the selection");
            }
        }
        return products;
    }

    /**
     * It asks and returns the categories that a discount will be placed over
     * @return the categories that a discount will be placed over
     */
    private List<Category> askDiscountCategories() {
        List<Category> categories = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
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
        return categories;
    }

    /**
     * It asks and returns the packs that a discount will be placed over
     * @return the packs that a discount will be placed over
     */
    private List<Pack> askDiscountPacks() {
        List<Pack> packs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
            System.out.println("Enter the desired pack's id (-1 to apply those already selected):");
            int id = scanner.nextInt();
            if (id == -1) {
                stop = true;
            }

            boolean found = false;
            for (Pack pack : Store.getInstance().getPacks()) {
                if (pack.getId() == id) {
                    found = true;
                    packs.add(pack);
                    System.out.println("Product added to the selection");
                }
            }
            if (!found) {
                System.out.println("A store product with that id doesn't exist, reloading...");
            }

        }
        return packs;
    }

    /**
     * It allows a manager to manage a discount
     * @throws IOException Error while scanning
     */
    public void manageDiscount() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< manageDiscount >>>>>>>>>> \n"); // Es para debug, borrar

            Discount discount = Store.getInstance().selectDiscountFromPage(currentScreenPageNum, itemNum);
            discount.bigPrintInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage start date");
            System.out.println("\t[" + i++ + "] Manage end date");
            System.out.println("\t[" + i++ + "] Manage a discount type-specific attribute");
            System.out.println("\t[" + i++ + "] Manage a discount coverage-specific attribute");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            double percentage, spendingThreshold, deduction;
            int numThreshold;
            StoreProduct gift;
            switch (chosenOption) {
                case 1: /* Manage start date */
                    System.out.println("Enter the new start date:");
                    LocalDateTime newStartDate = LocalDateTime.parse(scanner.next());
                    discount.setStartDate(newStartDate);
                    break;
                case 2: /* Manage end date */
                    System.out.println("Enter the new end date:");
                    LocalDateTime newEndDate = LocalDateTime.parse(scanner.next());
                    discount.setEndDate(newEndDate);
                    break;
                case 3: /* Manage a discount type-specific attribute */
                    String answer;
                    switch (discount.getType()) {
                        case FIXED_PERCENTAGE:
                            System.out.println("Do you want to change the discount's percentage? (y/n)");
                            answer = scanner.next();
                            if (answer.equalsIgnoreCase("y")) {
                                System.out.println("Enter the discount's percentage:");
                                percentage = scanner.nextDouble();
                                ((FixedPercentageDiscount) discount).setPercentage(percentage);
                            }
                            break;
                        case GIFT:
                            System.out.println("Do you want to change the discount's spending threshold? (y/n)");
                            answer = scanner.next();
                            if (answer.equalsIgnoreCase("y")) {
                                System.out.println("Enter the discount's spending threshold");
                                spendingThreshold = scanner.nextDouble();
                                ((GiftDiscount) discount).setSpendingThreshold(spendingThreshold);
                            }
                            System.out.println("Do you want to change the discount's gift? (y/n)");
                            answer = scanner.next();
                            if (answer.equalsIgnoreCase("y")) {
                                System.out.println("Enter the gift's Id");
                                String giftId = scanner.next();
                                gift = Store.getInstance().getStoreProductFromId(giftId);
                                ((GiftDiscount) discount).setGift(gift);
                            }
                            break;
                        case QUANTITY:
                            System.out.println("Do you want to change the discount's number of units threshold? (y/n)");
                            answer = scanner.next();
                            if (answer.equalsIgnoreCase("y")) {
                                System.out.println("Enter the discount's number of units threshold:");
                                numThreshold = scanner.nextInt();
                                ((QuantityDiscount) discount).setNumThreshold(numThreshold);
                            }
                            System.out.println("Do you want to change the discount's deduction? (y/n)");
                            answer = scanner.next();
                            if (answer.equalsIgnoreCase("y")) {
                                System.out.println("Enter the discount's deduction:");
                                deduction = scanner.nextDouble();
                                ((QuantityDiscount) discount).setDeduction(deduction);
                            }
                            break;
                        case VOLUME:
                            System.out.println("Do you want to change the discount's spending threshold? (y/n)");
                            answer = scanner.next();
                            if (answer.equalsIgnoreCase("y")) {
                                System.out.println("Enter the discount's spending threshold:");
                                spendingThreshold = scanner.nextDouble();
                                ((VolumeDiscount) discount).setSpendingThreshold(spendingThreshold);
                            }
                            System.out.println("Do you want to change the discount's deduction? (y/n)");
                            answer = scanner.next();
                            if (answer.equalsIgnoreCase("y")) {
                                System.out.println("Enter the discount's deduction:");
                                deduction = scanner.nextDouble();
                                ((VolumeDiscount) discount).setDeduction(deduction);
                            }
                            break;
                        default:
                            System.out.println("Invalid type");
                    }
                    break;
                case 4: /* Manage a discount coverage-specific attribute */
                    switch (discount.getCoverage()) {
                        case CATEGORY:
                        case PACK:
                        case PRODUCT:
                        default:
                            System.out.println("Invalid coverage");
                    }
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
     * It allows the manager to manage the store's parameters
     * @throws IOException the io exception
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
            System.out.println(
                    "\t[" + i++ + "] Recommendation's k limit: [" + Parameter.getParam().getkRecommend() + "]");
            basicLoopPrinter(i);
            int chosenOption3 = scanner.nextInt();

            switch (chosenOption3) {
                case 1: /* Items per page */
                    System.out.println("Enter the new Items per page value:");
                    int newItemsPerPage = scanner.nextInt();
                    manager.changeItemsPerPage(newItemsPerPage);
                    break;
                case 2: /* Score a parameter */
                    System.out.println("Enter the new Score a parameter value:");
                    int newScoreAParam = scanner.nextInt();
                    manager.changeScoreAParam(newScoreAParam);
                    break;
                case 3: /* Score b parameter */
                    System.out.println("Enter the new Score b parameter value:");
                    int newScoreBParam = scanner.nextInt();
                    manager.changeScoreBParam(newScoreBParam);
                    break;
                case 4: /* Offer time */
                    System.out.println("Enter the new Offer time:");
                    Period newOfferTime = Period.parse(scanner.next());
                    manager.changeOfferTime(newOfferTime);
                    break;
                case 5: /* Order time */
                    System.out.println("Enter the new Order time:");
                    Period newOrderTime = Period.parse(scanner.next());
                    manager.changeOrderTime(newOrderTime);
                    break;
                case 6: /* Valuation cost */
                    System.out.println("Enter the new Valuation cost:");
                    double newValuationCost = scanner.nextDouble();
                    manager.changeValuationCost(newValuationCost);
                    break;
                case 7: /* K recommendation limit */
                    System.out.println("Enter the new K recommendation limit:");
                    int newKRecommend = scanner.nextInt();
                    manager.changeKRecommend(newKRecommend);
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
     * It allows the manager to see its profile and change their password
     * @throws IOException the io exception
     */
    private void seeProfile() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< seeProfile >>>>>>>>>> \n"); // Es para debug, borrar
            currentUser.printInfo();

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