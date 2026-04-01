package order;

import product.*;
import user.RegisteredClient;

import java.time.*;
import java.util.*;

/**
 * Class Name: Cart
 * <p>
 * Description: Cart, array of products and packs the user has not paid yet
 * @author Sofía C.L.
 * @version 1.0
 */

public class Cart {
    private List<StoreProduct> sp = new ArrayList<>();
    private List<Pack> packs = new ArrayList<>();
    private boolean expired;
    private LocalDate modificationDate;
    private RegisteredClient owner;

    /**
     * Creates a new cart
     * @param sp,               the store products
     * @param packs,            the packs
     * @param expired,          whether it's expired
     * @param modificationDate, the last modification date
     */
    public Cart(List<StoreProduct> sp, List<Pack> packs, boolean expired, LocalDate modificationDate) {
        this.sp = sp;
        this.packs = packs;
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
        double aux = 0;

        for (StoreProduct spp : this.sp) {
            aux = spp.getPrice() + aux;
        }

        for (Pack p : this.packs) {
            aux = aux + p.getPrice();
        }

        return aux;
    }

    public void cancelProduct(StoreProduct toCancel) {
        int i = 0;

        if (this.sp.contains(toCancel) == true) {
            for (StoreProduct p : this.sp) {
                if (p == toCancel) {
                    break;
                }
                i++;
            }

            this.sp.remove(i);
            toCancel.setStock(toCancel.getStock() + 1);
        }
    }

    public void cancelPack(Pack p) {
        int i = 0;

        if (this.packs.contains(p) == true) {
            for (Pack pack : this.packs) {
                if (p == pack) {
                    break;
                }
                i++;
            }

            this.packs.remove(i);
            p.decreaseStock();
        }
    }

    public void addProduct(StoreProduct wanted) {

        if (wanted.getStock() == 0) {
            return;
        }

        this.sp.add(wanted);
        wanted.setStock(wanted.getStock() - 1);
        wanted.setAddedDate(LocalDate.now());
    }

    public boolean addPack(Pack wanted) {
        this.packs.add(wanted);
        List<StoreProduct> products = wanted.getProducts();

        for (StoreProduct sp : products) {
            if (sp.getStock() == 0) {
                return false;
            }
        }

        packs.add(wanted);
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

            Order order = new Order(price, OrderState.PAID, this.sp,this.packs);
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
     * It gets the cart's products
     * @return the cart's products
     */
    public List<StoreProduct> getProducts(){
        return this.sp;
    }
}