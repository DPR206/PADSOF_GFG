package user;

import product.*;
import store.Store;

import java.io.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

/**
 * It implements the store permission of the store
 * @author Sofía C.L.
 * @version 1.3
 * @see Employee
 */
public class StorePermission {
    private Store s; //creo que debería tener acceso al store si la va a modificar

    /**
     * Constructor of the class
     *
     */
    public StorePermission() {
        this.s = Store.getInstance();
    }

    /**
     * Adds a new comic to the store
     *
     * @param price, price of the comic
     * @param name, name of the comic to add
     * @param description, description of the comic
     * @param photo, link of the photo
     * @param stock, number of items available on the store
     * @param numPages, number of pages of the comic
     * @param year, the year it was published
     * @param editorial, the editorial that edited the comic
     * @param categories, all the categories it belongs too
     *
     */
    public void addComic(double price, String name, String description, String photo, int stock, int numPages,
                         Year year, String author, String editorial, Category... categories) {
        Comic c = new Comic(price, name, description, photo, stock, numPages, year, author, editorial, categories);
        this.s.addStoreProduct(c);
    }

    /**
     * Adds a new game to the store
     *
     * @param price, price of the comic
     * @param name, name of the comic to add
     * @param description, description of the comic
     * @param photo, link of the photo
     * @param stock, number of items available on the store
     * @param numPlayers, number of players the game allows
     * @param ageRange, the ages allowed to play this game
     * @param gameStyle, game style of said game
     * @param categories, all the categories it belongs too
     *
     */
    public void addGame(double price, String name, String description, String photo, int stock, int numPlayers,
                        String ageRange, GameStyle gameStyle, Category... categories) {
        Game g = new Game(price, name, description, photo, stock, numPlayers, ageRange, gameStyle, categories);
        this.s.addStoreProduct(g);
    }

    /**
     * Adds a new figurine to the store
     *
     * @param price, price of the comic
     * @param name, name of the comic to add
     * @param description, description of the comic
     * @param photo, link of the photo
     * @param stock, number of items available on the store
     * @param dimensions, the values of the dimensiones of the figurine (height x width x depth)
     * @param brand, brand it belongs to
     * @param material, material of the figurine
     * @param categories, all the categories it belongs too
     *
     */
    public void addFigurine(double price, String name, String description, String photo, int stock, String dimensions,
                            String brand, String material, Category... categories) {
        Figurine f = new Figurine(price, name, description, photo, stock, dimensions, brand, material, categories);
        this.s.addStoreProduct(f);
    }

    /**
     * Adds new products to the store reading them from a file
     *
     * @param fileName, name of the file that contains the products
     *
     */
    public boolean addProductByFile(String fileName) throws IOException {
        int stock;
        String name, desc, aux, type;
        double price;
        String line;
        Category auxC;
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            // Skip header line
            buffer.readLine();

            while ((line = buffer.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                List<Category> c = new ArrayList<>();
                type = tokenizer.nextToken();
                // Skip ID for now, as name includes it or something, but actually in CSV name is ID;name
                String id = tokenizer.nextToken();
                name = tokenizer.nextToken();
                desc = tokenizer.nextToken();
                aux = tokenizer.nextToken();
                price = Double.parseDouble(aux);
                aux = tokenizer.nextToken();
                stock = Integer.parseInt(aux);
                aux = tokenizer.nextToken(); // categories
                String[] cats = aux.split(",");
                for (String cat : cats) {
                    if ((auxC = this.s.getCategoryFromName(cat.trim())) != null) {
                        c.add(auxC);
                    }
                }
                String photo = ""; // No photo in CSV
                if (type.equals("C")) {
                    aux = tokenizer.nextToken();
                    int numPages = Integer.parseInt(aux);
                    String author = tokenizer.nextToken();
                    String editorial = tokenizer.nextToken();
                    aux = tokenizer.nextToken();
                    Year year = Year.parse(aux);
                    this.addComic(price, name, desc, photo, stock, numPages, year, author, editorial,
                            c.toArray(new Category[0]));

                } else if (type.equals("J")) { // Note: CSV uses J for Game, but code uses G? Wait, CSV has J, but code checks "G"
                    // Wait, CSV has J for Game, but code has "G", inconsistency.
                    // Assuming CSV is wrong or change to "J"
                    aux = tokenizer.nextToken();
                    int numPlayers = Integer.parseInt(aux);
                    String ageRange = tokenizer.nextToken();
                    String style = tokenizer.nextToken();
                    GameStyle gs = GameStyle.valueOf(style);
                    this.addGame(price, name, desc, photo, stock, numPlayers, ageRange, gs, c.toArray(new Category[0]));

                } else if (type.equals("F")) {
                    String brand = tokenizer.nextToken();
                    String material = tokenizer.nextToken();
                    String dimension = tokenizer.nextToken();

                    this.addFigurine(price, name, desc, photo, stock, dimension, brand, material,
                            c.toArray(new Category[0]));
                } else {
                    System.out.println("Tipo desconocido: " + type);
                }

            }
        }

        return true;
    }

    /**
     * Adds a new pack to the store
     *
     *
     * @param price, price of the pack
     * @param products, list of products that the pack contains
     * @param date, date when the pack was created
     *
     */
    public void addPack(double price, ArrayList<StoreProduct> products, LocalDate date) {
        Pack p = new Pack(price, products, date);
        s.addPack(p);
    }

    /**
     * Gets the store instance
     *
     */
    public Store getStore() {
        return this.s;
    }
}