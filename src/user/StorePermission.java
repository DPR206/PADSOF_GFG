package user;

import product.*;
import store.Store;
import java.util.StringTokenizer.*;


import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;


/**
 * Class name: StorePermission
 * <p>
 * Description: It implements the store permission of the store
 * @author Sofía C.L.
 * @version 1.3
 * @see Employee
 */
public class StorePermission {
    private Store s; //creo que debería tener acceso al store si la va a modificar

    /**
     * Constructor of the class
     * @param s the order's status
     */
    public StorePermission(Store s) {
        this.s = s;
    }

    public void addComic(double price, String name, String description, String photo, int stock, int numPages,
                         Year year, String author, String editorial, Category... categories) {
        Comic c = new Comic(price, name, description, photo, stock, numPages, year, author, editorial, categories);
        this.s.addStoreProduct(c); // DUE
    }

    public void addGame(double price, String name, String description, String photo, int stock, int numPlayers,
                        String ageRange, GameStyle gameStyle, Category... categories) {
        Game g = new Game(price, name, description, photo, stock, numPlayers, ageRange, gameStyle, categories);
        s.addProduct(g);
    }

    public void addFigurine(double price, String name, String description, String photo, int stock, String dimensions,
                            String brand, String material, Category... categories) {
        Figurine f = new Figurine(price, name, description, photo, stock, dimensions, brand, material, categories);
        s.addProduct(f);
    }

    public boolean addProductByFile(String fileName) throws IOException{
       BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("file.txt")));
       String line;
       int id;
       Strind desc, categories,

       while ((line = buffer.readLine()) != null){

       }
    }

    
    /* TYPE(C/G/F);ID;NAME;DESCRIPTION;PRICE;STOCK;number_of_categories;CATEGORIES;(HASTA AQUÍ)PAGES;AUTHOR;EDITORIAL;YEAR;(CÓMICS)
        (JUEGO)PLAYERS;AGE;STYLE(Cards/Dice/GameBoard/Miniature);
        (FIGURITA)BRAND;MATERIAL;DIMENSION */
    public void addPack(double price, ArrayList<StoreProduct> products, LocalDate date) {
        Pack p = new Pack(price, products, date);
        s.addProduct(p);
    }
}