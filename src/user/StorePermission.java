package user;

import store.Store;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import product.Category;
import product.Comic;
import product.Figurine;
import product.Game;
import product.GameStyle;
import product.Pack;
import product.StoreProduct;
import productT.*;


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
     * @param gameStyle, gamestyle of said game
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
     * @param filename, name of the file that contains the products
     * 
     */
    public boolean addProductByFile(String fileName) throws IOException {
        int stock, numCat;
        String name, desc, aux, type;
        double price;
        String line;
        int i = 0;
        Category auxC;
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {

            while ((line = buffer.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                List<Category> c = new ArrayList<>();
                type = tokenizer.nextToken();
                name = tokenizer.nextToken();
                desc = tokenizer.nextToken();
                aux = tokenizer.nextToken();
                price = Double.parseDouble(aux);
                aux = tokenizer.nextToken();
                stock = Integer.parseInt(aux);
                aux = tokenizer.nextToken();
                numCat = Integer.parseInt(aux);

                for (i = 0; i < numCat; i++) {
                    aux = tokenizer.nextToken();
                    if ((auxC = this.s.getCategoryFromName(aux)) != null) {
                        c.add(auxC);
                    }
                }
                String photo = tokenizer.nextToken();
                if (type.equals("C")) {
                    aux = tokenizer.nextToken();
                    int numPages = Integer.parseInt(aux);
                    String author = tokenizer.nextToken();
                    String editorial = tokenizer.nextToken();
                    aux = tokenizer.nextToken();
                    Year year = Year.parse(aux);
                    this.addComic(price, name, desc, photo, stock, numPages, year, author, editorial,
                            c.toArray(new Category[0]));

                } else if (type.equals("G")) {
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