package order;

import product.*;
import user.RegisteredClient;

import java.time.*;
import java.util.*;

import discount.DiscountType;
import discount.QuantityDiscount;

/**
 * Cart, array of products and packs the user has not paid yet
 * @author Sofía C.L. and Ana O.R.
 * @version 1.2
 */

public class Cart {
    private HashMap<StoreProduct, Integer> sp = new HashMap<>();
    private HashMap<Pack, Integer> packs = new HashMap<>();
    private boolean expired;
    private LocalDate modificationDate;
    private RegisteredClient owner;

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    /**
     * Creates a new cart
     * @param sp,               the store products
     * @param packs,            the packs
     * @param expired,          whether it's expired
     * @param modificationDate, the last modification date
     */
    public Cart(List<StoreProduct> sp, List<Pack> packs, boolean expired, LocalDate modificationDate) {
        for (StoreProduct product : sp) {
            addProduct(product);
        }
        for (Pack pack : packs) {
            addPack(pack);
        }
        this.expired = expired;
        this.modificationDate = modificationDate;
    }

    /**
     * Creates a new cart without content
     * @param expired,          whether it's expired
     * @param modificationDate, the last modification date
     */
    public Cart(boolean expired, LocalDate modificationDate) {
        this(Collections.emptyList(), Collections.emptyList(), expired, modificationDate);
    }

    /**
     * Creates a cart with default modification date (today)
     */
    public Cart() {
        this(false, LocalDate.now());
    }

    public boolean getExpired() {
        return this.expired;
    }

    public void setExpired(boolean exp) {
        this.expired = exp;
    }

    public LocalDate getModificationDate() {
        return this.modificationDate;
    }

    public void setModificationDate(LocalDate ld) {
        this.modificationDate = ld;
    }

    public double calculatePrice() {
        // DUE: Cálculo de descuentos sobre los productos (packs hecho)
        double aux = 0;

        for (StoreProduct spp : this.sp.keySet()) {
            aux = spp.getPrice()*this.sp.get(spp) + aux;
        }

        for (Pack p : this.packs.keySet()) {
            /* All other discounts + price */
            aux = aux + p.getPrice()*this.packs.get(p);
            /* Quantity discount */
            if (p.getDiscount().getType() == DiscountType.QUANTITY) {
                QuantityDiscount quantityDisc = (QuantityDiscount) p.getDiscount();
                if (this.packs.get(p) >= quantityDisc.getNumThreshold()) {
                    aux = aux - quantityDisc.getNumThreshold();
                }
            }
        }

        return aux;
    }

    public void cancelProduct(StoreProduct toCancel) {
        if (this.sp.containsKey(toCancel)) {
            if (this.sp.get(toCancel) == 1) {
                this.sp.remove(toCancel);
            } else {
                this.sp.put(toCancel, this.sp.get(toCancel) - 1);
            }
            toCancel.decreaseStock(1);
        }
    }

    public void cancelPack(Pack p) {
        if (this.packs.containsKey(p)) {
            if (this.packs.get(p) == 1) {
                this.packs.remove(p);
            } else {
                this.packs.put(p, this.packs.get(p) - 1);
            }
            p.decreaseStock();
        }
    }

    public void addProduct(StoreProduct wanted) {

        if (wanted.getStock() == 0) {
            return;
        }

        if (this.sp.containsKey(wanted)) {
            this.sp.put(wanted, this.sp.get(wanted) + 1);
        } else {
            this.sp.put(wanted, 1);
        }
        wanted.decreaseStock(1);
        wanted.setAddedDate(LocalDate.now());
    }

    public boolean addPack(Pack wanted) {
        //this.packs.add(wanted); <- creo que mejor después
        List<StoreProduct> products = wanted.getProducts();

        for (StoreProduct sp : products) {
            if (sp.getStock() == 0) {
                return false;
            }
        }

        if (this.packs.containsKey(wanted)) {
            this.packs.put(wanted, this.packs.get(wanted) + 1);
        } else {
            this.packs.put(wanted, 1);
        }

        wanted.decreaseStock();

        return true;
    }

    public int getProductAmount() {
        return this.sp.size();
    }

    public boolean payOrder() {
        double price = this.calculatePrice();

        Scanner sc = new Scanner(System.in);
        String numeroTarjeta, CCV, fechaCad;
        RegisteredClient rc;

        try {
            System.out.print("Introduce tu número de tarjeta: ");
            numeroTarjeta = sc.next();
            System.out.print("Introduce tu CCV: ");
            CCV = sc.next();
            System.out.print("Introduce tu fecha de caducidad de tarjeta: ");
            fechaCad = sc.next();

            Order order = new Order(price, OrderState.PAID, new ArrayList<>(this.sp.keySet()),
                    new ArrayList<>(this.packs.keySet()), this.owner);
            this.owner.getOrderHistory().addOrder(order);

            /* Para después del lunes, comprobar si el formato es correcto, y si no, retorno false */
        } catch (InputMismatchException e) {
            System.out.println("Error: El tipo de dato introducido no es válido.");
        } finally {
            sc.close();
        }

        this.packs.clear();
        this.sp.clear();

        return true;
    }

    /**
     * It prints a cart's info
     */
    public void getPrintInfo() {
        System.out.print("Products: ");
        this.printProducts();
        System.out.print("Packs: ");
        this.printPacks();
    }

    /**
     * It prints a cart's product's basic info
     */
    public void printProducts() { // DUE: Page / wrap this
        int i = 0;
        for (StoreProduct product : this.sp.keySet()) {
            System.out.print(i++ + ". " + product.getName() + " x" + this.sp.get(product));
        }
    }

    /**
     * It prints a cart's pack's basic info
     */
    public void printPacks() { // DUE: Page / wrap this
        int i = 0;
        for (Pack pack : this.packs.keySet()) {
            System.out.print(i++ + ". [" + pack.getPrintProducts() + "] x" + this.sp.get(pack));
        }
    }

    /**
     * It gets the cart's products
     * @return the cart's products
     */
    public List<StoreProduct> getProducts(){
        return new ArrayList<>(this.sp.keySet());
    }
}