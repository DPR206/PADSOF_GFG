package model.user;

import model.product.*;
import model.product.*;
import model.search.SearchStoreProducts;
import model.search.Searcher;
import model.store.Store;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

import model.discount.Discount;

/**
 * It implements the store permission of the store
 * @author Sofía C.L.
 * @version 1.3
 * @see Employee
 */
public class StorePermission {
    private Store s; //creo que debería tener acceso al store si la va a modificar
    /** The permission's searcher */
    private Searcher searching;

    /**
     * Constructor of the class
     *
     */
    public StorePermission() {
        this.s = Store.getInstance();
        this.searching = new Searcher(new SearchStoreProducts(true));
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
     * @param author the comic's author
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
     * @return true if the products were added false if else
     * @throws IOException error while reading the file
     */
    @SuppressWarnings("unused")
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
     * @return the store
     */
    public Store getStore() {
        return this.s;
    }

    /**
     * Searches for the store products
     * @return the store product based on the filters
     */
    public List<StoreProduct> searchStoreProducts(){
        return this.searching.searchStoreProducts();
    }

    /**
     * Changes the price of a pack
     *
     *
     * @param p, the pack to search
     * @param price, new price of the pack
     *
     */
    public void setPackPrice(Pack p, double price) {
    	List<Pack> packs = s.getPacks();
    	for(Pack pack: packs) {
    		if(pack.equals(p)) {
    			p.setPrice(price);
    		}
    	}
    }

    /**
     * Changes the discount of a pack
     *
     *
     * @param p, the pack to search
     * @param discount, new discount of the pack
     *
     */
    public void setPackDiscount(Pack p, Discount discount) {
    	List<Pack> packs = s.getPacks();
    	for(Pack pack: packs) {
    		if(pack.equals(p)) {
    			p.setDiscount(discount);
    		}
    	}
    }

    /**
     * Adds a product to the pack
     *
     *
     * @param p, the pack to search
     * @param sp, the store product to add
     *
     */
    public void PackAddProduct(Pack p, StoreProduct sp) {
    	List<Pack> packs = s.getPacks();
    	for(Pack pack: packs) {
    		if(pack.equals(p)) {
    			p.addProduct(sp);
    		}
    	}
    }

    /**
     * Adds a list of products to the pack
     *
     *
     * @param p, the pack to search
     * @param lsp, the list of store product to add
     *
     */
    public void PackAddListProducts(Pack p, ArrayList<StoreProduct> lsp) {
    	List<Pack> packs = s.getPacks();
    	for(Pack pack: packs) {
    		if(pack.equals(p)) {
    			p.addArrayProducts(lsp);
    		}
    	}
    }

    /**
     * Deletes a product from a pack
     *
     * @param p the pack to delete from
     * @param sp the store product to delete
     */
    public void DeleteProductFromPack(Pack p, StoreProduct sp) {
    	List<Pack> packs = s.getPacks();
    	for(Pack pack: packs) {
    		if(pack.equals(p)) {
    			p.eliminateProduct(sp);
    		}
    	}
    }

    /**
     * Deletes a list of store products from the pack
     *
     *
     * @param p, the pack to search
     * @param sp, the list of store products to delete
     *
     */
    public void DeleteListOfProductFromPack(Pack p, ArrayList<StoreProduct> sp) {
    	List<Pack> packs = s.getPacks();
    	for(Pack pack: packs) {
    		if(pack.equals(p)) {
    			p.eliminateArrayProducts(sp);
    		}
    	}
    }

    /**
     * Sets the list of products of the pack
     *
     *
     * @param p, the pack to search
     * @param sp, the list of store products to add
     *
     */
    public void setPackProducts(Pack p, ArrayList<StoreProduct> sp) {
    	List<Pack> packs = s.getPacks();
    	for(Pack pack: packs) {
    		if(pack.equals(p)) {
    			p.setProducts(sp);
    		}
    	}
    }

    /**
     * Searches for the store products based on the category
     * @param c, the categories we want our searched products to belong to
     * @return the store product based on the filters
     */
    public List<StoreProduct> searchStoreProductByCategory(Category... c){
        return this.searching.searchByCategory(c);
    }

    /**
     * Sets the categories of a product.
     *
     * @param sp the product to update
     * @param categories the categories to assign
     */
    public void setCategories(StoreProduct sp, HashMap<String, Category> categories) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.setCategories(categories);
            }
        }
    }

    /**
     * Adds categories to a product.
     *
     * @param sp the product to update
     * @param categories the categories to add
     */
    public void addCategories(StoreProduct sp, Category... categories) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.addCategory(categories);
            }
        }
    }

    /**
     * Removes categories from a product.
     *
     * @param sp the product to update
     * @param categories the categories to remove
     */
    public void removeCategories(StoreProduct sp, Category... categories) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.removeCategory(categories);
            }
        }
    }

    /**
     * Sets the description of a product.
     *
     * @param sp the product to update
     * @param desc the new description
     */
    public void setDescription(StoreProduct sp, String desc) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.setDescription(desc);
            }
        }
    }

    /**
     * Sets the discount of a product.
     *
     * @param sp the product to update
     * @param disc the discount to apply
     */
    public void setDiscount(StoreProduct sp, Discount disc) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.setDiscount(disc);
            }
        }
    }

    /**
     * Sets the name of a product.
     *
     * @param sp the product to update
     * @param name the new name
     */
    public void setName(StoreProduct sp, String name) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.setName(name);
            }
        }
    }

    /**
     * Sets the photo of a product.
     *
     * @param sp the product to update
     * @param photo the photo reference
     */
    public void setPhoto(StoreProduct sp, String photo) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.setPhoto(photo);
            }
        }
    }

    /**
     * Sets the price of a product.
     *
     * @param sp the product to update
     * @param price the new price
     */
    public void setPrice(StoreProduct sp, double price) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.setPrice(price);
            }
        }
    }

    /**
     * Sets the total sales of a product.
     *
     * @param sp the product to update
     * @param sales the number of sales
     */
    public void setSales(StoreProduct sp, int sales) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.setSales(sales);
            }
        }
    }

    /**
     * Sets the monthly sales of a product.
     *
     * @param sp the product to update
     * @param sales the sales by month
     */
    public void setSalesByMonth(StoreProduct sp, HashMap<Month, Integer> sales) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.setSalesByMonth(sales);
            }
        }
    }

    /**
     * Sets the stock of a product.
     *
     * @param sp the product to update
     * @param stock the available stock
     */
    public void setStock(StoreProduct sp, int stock) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.setStock(stock);
            }
        }
    }

    /**
     * Sets the type of a product.
     *
     * @param sp the product to update
     * @param pt the product type
     */
    public void setType(StoreProduct sp, ProductType pt) {
        List<StoreProduct> lsp = s.getStoreProductList();
        for(StoreProduct spp: lsp) {
            if(spp.equals(sp)) {
                sp.setType(pt);
            }
        }
    }

}