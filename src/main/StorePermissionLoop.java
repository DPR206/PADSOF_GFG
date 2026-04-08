package main;

import product.*;
import store.Store;
import user.Manager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

/**
 * It implements the store permission's loop
 * @author Ana O.R.
 * @version 1.0
 */
public class StorePermissionLoop extends Loop {
    /** This loop's instance */
    private static StorePermissionLoop INSTANCE;
    /** The store's filtered list of products */
    private List<StoreProduct> filteredStore = null;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Store Permission loop's constructor
     */
    StorePermissionLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Store Permission loop's instance
     * @return the Store Permission loop's instance
     */
    protected static StorePermissionLoop getInstance() {
        if (StorePermissionLoop.INSTANCE == null) {
            StorePermissionLoop.INSTANCE = new StorePermissionLoop();
        }
        return INSTANCE;
    }

    /**
     * The store permission's main loop
     */
    public void storePermissionLoop() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< storePermissionLoop >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage packs");
            System.out.println("\t[" + i++ + "] Manage store products");
            System.out.println("\t[" + i++ + "] Add store products");
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
                case 4: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 5: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
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
                case 3: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 4: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
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
                case 4: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 5: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
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
                case 5: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 6: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
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
                case 11: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 12: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
                    break;
                case 13: /* Exit */
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
                case 10: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 11: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
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
                case 10: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 11: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
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
                case 4: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 5: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
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
                case 6: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 7: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
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
}