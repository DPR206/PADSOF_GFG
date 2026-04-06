package order;

import store.Parameter;
import user.RegisteredClient;

import java.time.*;
import java.util.*;

import es.uam.eps.padsof.telecard.*;
import notification.*;
import discount.DiscountType;
import discount.QuantityDiscount;
import product.Pack;
import product.StoreProduct;

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
    private LocalDate creationDate;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Creates a new cart
     * @param assignedProducts,               the store products
     * @param assignedPacks,            the packs
     * @param assignedExpired,          whether it's expired
     * @param assignedModificationDate, the last modification date
     */
    public Cart(List<StoreProduct> assignedProducts, List<Pack> assignedPacks, boolean assignedExpired,
                LocalDate assignedModificationDate) {
        for (StoreProduct product : assignedProducts) {
            addProduct(product);
        }
        for (Pack pack : assignedPacks) {
            addPack(pack);
        }
        this.expired = assignedExpired;
        this.modificationDate = assignedModificationDate;
        this.creationDate = LocalDate.now();
    }

    /**
     * Creates a new cart without content
     * @param assignedExpired,          whether it's expired
     * @param assignedModificationDate, the last modification date
     */
    public Cart(boolean assignedExpired, LocalDate assignedModificationDate) {
        this(Collections.emptyList(), Collections.emptyList(), assignedExpired, assignedModificationDate);
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
            aux = spp.getPrice() * this.sp.get(spp) + aux;
        }

        for (Pack p : this.packs.keySet()) {
            /* All other discounts + price */
            aux = aux + p.getPrice() * this.packs.get(p);
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
            toCancel.increaseStock(1);
        }
    }

    public void cancelPack(Pack p) {
        if (this.packs.containsKey(p)) {
            if (this.packs.get(p) == 1) {
                this.packs.remove(p);
            } else {
                this.packs.put(p, this.packs.get(p) - 1);
            }
            p.increaseStock();
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

        for (StoreProduct product : products) {
            if (product.getStock() == 0) {
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

    public boolean payOrder()
            throws InvalidCardNumberException, FailedInternetConnectionException, OrderRejectedException {

        double price = this.calculatePrice();

        Scanner sc = new Scanner(System.in);
        String numeroTarjeta, CCV, fechaCad;

        try {
            System.out.print("Introduce tu número de tarjeta: ");
            numeroTarjeta = sc.next();
            System.out.println(TeleChargeAndPaySystem.isValidCardNumber(numeroTarjeta));
            TeleChargeAndPaySystem.charge(numeroTarjeta, "Order", price, true);
            System.out.print("Introduce tu CCV: ");
            CCV = sc.next();
            System.out.print("Introduce tu fecha de caducidad de tarjeta: ");
            fechaCad = sc.next();

            Order order = new Order(price, OrderState.PAID, new ArrayList<>(this.sp.keySet()),
                    new ArrayList<>(this.packs.keySet()), this.owner);
            this.owner.getOrderHistory().addOrder(order);

            NotificationOrder notification = new NotificationOrder(LocalDateTime.now(), false, true,
                    NotificationType.ORDER);
            notification.FullNotification(order);
            this.owner.getNotificationHistory().addNotification(notification);
            this.owner.increaseNumOrders();

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
    
    public LocalDate getCreationDate(){
    	return this.creationDate;
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
            System.out.print(i++ + ". [" + pack.getPrintProducts() + "] x" + this.packs.get(pack));
        }
    }
    
    public void cleanupOldProducts() {
    	List<StoreProduct> sp = new ArrayList<>(this.getProducts());
    	List<Pack> packs = new ArrayList<>(this.packs.keySet());
    	
    	for(StoreProduct spi: sp) {
    		LocalDate expiration = this.calculateExpiredDate(spi);
    		if(expiration.isBefore(LocalDate.now())) {
    			this.cancelProduct(spi);
    		}
    	}
    	for(Pack p: packs) {
    		LocalDate expiration = this.calculateExpiredDatePacks(p);
    		if(expiration.isBefore(LocalDate.now())) {
    			this.cancelPack(p);
    		}
    	}
    }

    /**
     * It gets the cart's products
     * @return the cart's products
     */
    public List<StoreProduct> getProducts() {
        return new ArrayList<>(this.sp.keySet());
    }
    
    public LocalDate calculateExpiredDate(StoreProduct sproducts) {
    	Parameter p = Parameter.getParam();
    	Period timeToExist = p.getOrderTime();
    	
    	List<StoreProduct> products = new ArrayList<>(this.sp.keySet());
    	
    	for(StoreProduct pdct: products) {
    		if(pdct.getId().equals(sproducts.getId())) return sproducts.getAddedDate().plus(timeToExist);
        }
    	return null;
    }
    
    public LocalDate calculateExpiredDatePacks(Pack pack) {
    	Parameter p = Parameter.getParam();
    	Period timeToExist = p.getOrderTime();
    	
    	List<Pack> packs = new ArrayList<>(this.packs.keySet());
    	
    	for(Pack pck: packs) {
    		if(pck.getId()== (pack.getId())) return pck.getDateAddCart().plus(timeToExist);
        }
    	return null;
    }
    
}