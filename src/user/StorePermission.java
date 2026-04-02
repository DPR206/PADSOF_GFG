package user;

import product.*;
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
        this.s = s.getInstance();
    }

    public void addComic(double price, String name, String description, String photo, int stock, int numPages,
                         Year year, String author, String editorial, Category... categories) {
        Comic c = new Comic(price, name, description, photo, stock, numPages, year, author, editorial, categories);
    }

    public void addGame(double price, String name, String description, String photo, int stock, int numPlayers,
                        String ageRange, GameStyle gameStyle, Category... categories) {
        Game g = new Game(price, name, description, photo, stock, numPlayers, ageRange, gameStyle, categories);
    }

    public void addFigurine(double price, String name, String description, String photo, int stock, String dimensions,
                            String brand, String material, Category... categories) {
        Figurine f = new Figurine(price, name, description, photo, stock, dimensions, brand, material, categories);
    }

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


    /* TYPE(C/G/F);NAME;DESCRIPTION;PRICE;STOCK;number_of_categories;CATEGORIES; photo;(HASTA AQUI)
        PAGES;AUTHOR;EDITORIAL;YEAR;(COMICS)
        (JUEGO)PLAYERS;AGE;STYLE(Cards/Dice/GameBoard/Miniature);
        (FIGURITA)BRAND;MATERIAL;DIMENSION */
    public void addPack(double price, ArrayList<StoreProduct> products, LocalDate date) {
        Pack p = new Pack(price, products, date);
        s.addPack(p);
    }

    public Store getStore() {
        return this.s;
    }
}