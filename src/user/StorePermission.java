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
       BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
       String line;
       int id;
       String desc, name, desc, categories, aux, stock, numCat, type;
       double price;
       int i = 0;
       Category auxC;
       List<Category> c = new ArrayList<>()

       while ((line = buffer.readLine()) != null){
            StringTokenizer tokenizer = new StringTokenizer(line, ";"); 
            type = tokenizer.nextToken();
            aux = tokenizer.nextToken();
            id = Integer.parseInt(aux);
            aux = tokenizer.nextToken();
            name = tokenizer.nextToken();
            desc = tokenizer.nextToken();
            aux = tokenizer.nextToken();
            price = Double.parseDouble(aux);
            aux = tokenizer.nextToken();
            stock = Integer.parseInt(aux);
            aux = tokenizer.nextToken();
            numCat = Integer.parseInt(aux);

            for(i=0; i < numCat; i++){
                aux = tokenizer.nextToken();
                if((auxC = this.s.getCategoryFromName(aux)) != null){
                    c.add(auxC);
                }
            }
            String photo = tokenizer.nextToken();
            if(type == "C"){
                aux = tokenizer.nextToken();
                int numPages = Integer.parseInt(aux);
                String author = nextToken();
                String editorial = tokenizer.nextToken();
                aux = tokenizer.nextToken();
                int year = Integer.parseInt(aux);
                this.addComic(price, name, desc, photo, stock, numPages, year, author, editorial, c);

            } else if(type == "G"){
                aux = tokenizer.nextToken();
                int numPlayers = Integer.parseInt(aux);
                aux = tokenizer.nextToken();
                int age = Integer.parseInt(aux);
                String style = tokenizer.nextToken();
                this.addGame(price, name, desc, photo, stock, numPlayers, age, style, c);

            } else if(type == "F"){
                String brand = tokenizer.nextToken()
            }

       }
    }

    
    /* TYPE(C/G/F);ID;NAME;DESCRIPTION;PRICE;STOCK;number_of_categories;CATEGORIES; photo;(HASTA AQUÍ)
        PAGES;AUTHOR;EDITORIAL;YEAR;(CÓMICS)
        (JUEGO)PLAYERS;AGE;STYLE(Cards/Dice/GameBoard/Miniature);
        (FIGURITA)BRAND;MATERIAL;DIMENSION */
    public void addPack(double price, ArrayList<StoreProduct> products, LocalDate date) {
        Pack p = new Pack(price, products, date);
        s.addProduct(p);
    }
}