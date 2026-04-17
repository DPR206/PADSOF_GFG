package model.order;

import es.uam.eps.padsof.telecard.*;
import model.app.Pager;
import model.discount.*;
import model.notification.*;
import model.store.*;
import model.product.Pack;
import model.product.StoreProduct;
import model.user.RegisteredClient;

import java.time.*;
import java.util.*;

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
     * @param assignedProducts,         the store products
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

    /**
     * It calculates the price of the cart
     * @return the cart's price
     */
    public double calculatePrice() {
        double aux = 0;
        List<Discount> discounts = new ArrayList<>();

        for (StoreProduct spp : this.sp.keySet()) {
            aux = spp.getDiscountedPrice() * this.sp.get(spp) + aux; /* Fixed percentage */
            /* Busco el resto de descuentos */
            if (spp.getDiscount() != null && spp.getDiscount().getType() != DiscountType.FIXED_PERCENTAGE) {
                if (!discounts.contains(spp.getDiscount())) {
                    discounts.add(spp.getDiscount());
                }
            }
        }

        /* Estudio el resto de descuentos */
        for (Discount discount : discounts) {
            if (discount.getCoverage() == DiscountCoverage.PRODUCT) {
                List<StoreProduct> products = ((ProductDiscount) discount).getProducts();
                switch (discount.getType()) {
                    case QUANTITY:
                        if (calculateNumUnits(products) >= ((ProductQuantity) discount).getNumThreshold()) {
                            aux = aux - ((ProductQuantity) discount).getDeduction();
                        }
                    case VOLUME:
                        if (calculateSpentAmount(products) >= ((ProductVolume) discount).getSpendingThreshold()) {
                            aux = aux - ((ProductVolume) discount).getDeduction();
                        }
                    case GIFT:
                        if (calculateSpentAmount(products) >= ((ProductGift) discount).getSpendingThreshold()) {
                            aux = aux - ((ProductGift) discount).getGift().getDiscountedPrice();
                            if (!this.sp.containsKey(((ProductGift) discount).getGift())) {
                                this.sp.put(((ProductGift) discount).getGift(), 1);
                            } else {
                                this.sp.put(((ProductGift) discount).getGift(),
                                        this.sp.get(((ProductGift) discount).getGift()) + 1);
                            }
                        }
                }
            } else {
                List<StoreProduct> products = ((CategoryDiscount) discount).getProducts();
                switch (discount.getType()) {
                    case QUANTITY:
                        if (calculateNumUnits(products) >= ((CategoryQuantity) discount).getNumThreshold()) {
                            aux = aux - ((CategoryQuantity) discount).getDeduction();
                        }
                    case VOLUME:
                        if (calculateSpentAmount(products) >= ((CategoryVolume) discount).getSpendingThreshold()) {
                            aux = aux - ((CategoryVolume) discount).getDeduction();
                        }
                    case GIFT:
                        if (calculateSpentAmount(products) >= ((CategoryGift) discount).getSpendingThreshold()) {
                            aux = aux - ((CategoryGift) discount).getGift().getDiscountedPrice();
                            if (!this.sp.containsKey(((CategoryGift) discount).getGift())) {
                                this.sp.put(((CategoryGift) discount).getGift(), 1);
                            } else {
                                this.sp.put(((CategoryGift) discount).getGift(),
                                        this.sp.get(((CategoryGift) discount).getGift()) + 1);
                            }
                        }
                }
            }
        }

        for (Pack p : this.packs.keySet()) {
            /* All other discounts + price */
            aux = aux + p.getDiscountedPrice() * this.packs.get(p);
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

    /**
     * Calculates the amount o money spent of products that appear in a certain list
     * @param products the desired products
     * @return the amount o money spent of products that appear in a certain list
     */
    private double calculateSpentAmount(List<StoreProduct> products) {
        double spent = 0.0;
        for (StoreProduct spp : products) {
            if (this.getProducts().contains(spp)) {
                spent = spent + spp.getPrice() * this.sp.get(spp);
            }
        }
        return spent;
    }

    /**
     * Calculates the amount of bought units of products that appear in a certain list
     * @param products the desired products
     * @return the amount of bought units of products that appear in a certain list
     */
    private double calculateNumUnits(List<StoreProduct> products) {
        int units = 0;
        for (StoreProduct spp : products) {
            if (this.getProducts().contains(spp)) {
                units = units + this.sp.get(spp);
            }
        }
        return units;
    }

    /**
     * It cancels a product in the cart
     * @param toCancel the store product to cancel
     */
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

    /**
     * It cancels a pack in the cart
     * @param p the pack to cancel
     */
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

    /**
     * Adds a product to the cart
     * @param wanted the store product to add
     */
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

    /**
     * Adds a pack to the cart
     * @param wanted the pack to add
     * @return true if the pack was added, false if else
     */
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
        wanted.setDateAddCart(LocalDate.now());

        return true;
    }

    /**
     * It pays the cart
     * @return true if the cart was paid, if not a message will be printed
     * @throws InvalidCardNumberException        the card number wasn't valid
     * @throws FailedInternetConnectionException the Internet connection failed
     * @throws OrderRejectedException            the order was rejected
     */
    public boolean payOrder()
            throws InvalidCardNumberException, FailedInternetConnectionException, OrderRejectedException {

        double price = this.calculatePrice();

        Scanner sc = new Scanner(System.in);
        String numeroTarjeta;

        try {
            System.out.print("Introduce tu número de tarjeta: ");
            numeroTarjeta = sc.next();
            System.out.println(TeleChargeAndPaySystem.isValidCardNumber(numeroTarjeta));
            TeleChargeAndPaySystem.charge(numeroTarjeta, "Order", price, true);

            Statistics.getINSTANCE().addRevenue(price, RevenueType.PRODUCTS, LocalDate.now(), this.getProducts());

            Order order = new Order(price, OrderState.PAID, new ArrayList<>(this.sp.keySet()),
                    new ArrayList<>(this.packs.keySet()), this.owner);
            this.owner.getOrderHistory().addOrder(order);

            NotificationOrder notification =
                    new NotificationOrder(LocalDateTime.now(), false, true, NotificationType.ORDER);
            notification.FullNotification(order);
            this.owner.getNotificationHistory().addNotification(notification);
            this.owner.increaseNumOrders();

            NotificationEmployeeOrder notification2 =
                    new NotificationEmployeeOrder(LocalDateTime.now(), false, true, NotificationType.EMPLOYEE_ORDER);
            notification2.FullNotification(order);
            Store.getInstance().sendNotificationEmployees(notification2);

            /* Para después del lunes, comprobar si el formato es correcto, y si no, retorno false */
        } catch (InputMismatchException e) {
            System.out.println("Error: El tipo de dato introducido no es válido.");
        }

        this.packs.clear();
        this.sp.clear();

        return true;
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

    /**
     * It removes the products and packs that have expired
     */
    public void cleanupOldProducts() {
        List<StoreProduct> sp = new ArrayList<>(this.getProducts());
        List<Pack> packs = new ArrayList<>(this.packs.keySet());

        for (StoreProduct spi : sp) {
            LocalDate expiration = this.calculateExpiredDate(spi);
            if (expiration.isBefore(LocalDate.now())) {
                this.cancelProduct(spi);
                NotificationProductCart notificationP =
                        new NotificationProductCart(LocalDateTime.now(), false, true, NotificationType.PRODUCT_CART);
                notificationP.FullNotification(spi);
                this.owner.getNotificationHistory().addNotification(notificationP);
            }
        }
        for (Pack p : packs) {
            LocalDate expiration = this.calculateExpiredDatePacks(p);
            if (expiration.isBefore(LocalDate.now())) {
                this.cancelPack(p);
                NotificationPackCart notificationK =
                        new NotificationPackCart(LocalDateTime.now(), false, true, NotificationType.PACK_CART);
                notificationK.FullNotification(p);
                this.owner.getNotificationHistory().addNotification(notificationK);
            }
        }
    }

    /**
     * It calculates the expired date of the product
     * @param sproducts the store product in the cart
     * @return the date of expiration
     */
    public LocalDate calculateExpiredDate(StoreProduct sproducts) {
        Parameter p = Parameter.getParam();
        Period timeToExist = p.getOrderTime();

        List<StoreProduct> products = new ArrayList<>(this.sp.keySet());

        for (StoreProduct pdct : products) {
            if (pdct.getId().equals(sproducts.getId())) {
                return sproducts.getAddedDate().plus(timeToExist);
            }
        }
        return null;
    }

    /**
     * It calculates the expired date of the packs
     * @param pack the pack in the cart
     * @return the date of expiration
     */
    public LocalDate calculateExpiredDatePacks(Pack pack) {
        Parameter p = Parameter.getParam();
        Period timeToExist = p.getOrderTime();

        List<Pack> packs = new ArrayList<>(this.packs.keySet());

        for (Pack pck : packs) {
            if (pck.getId() == (pack.getId())) {
                return pck.getDateAddCart().plus(timeToExist);
            }
        }
        return null;
    }

    /**
     * It prints a sub-list of the cart's store products according to the desired page
     * @param pageNum the desired page's number
     */
    public void printStoreProductListPage(int pageNum) {
        Pager.getInstance().printStoreProductListPage(this.getProducts(), pageNum);
    }

    /**
     * It prints a sub-list of the cart's packs according to the desired page
     * @param pageNum the desired page's number
     */
    public void printPackListPage(int pageNum) {
        Pager.getInstance().printPackListPage(this.getPacks(), pageNum);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Obtains the creation date of the cart
     * @return the cart's creation date
     */
    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    /**
     * Obtains whether the cart is expired
     * @return true if the cart has expired, false if else
     */
    public boolean getExpired() {
        return this.expired;
    }

    /**
     * Sets whether the cart is expired
     * @param exp true if the cart has expired, false if else
     */
    public void setExpired(boolean exp) {
        this.expired = exp;
    }

    /**
     * Obtains the last modification date of the cart
     * @return the modification date
     */
    public LocalDate getModificationDate() {
        return this.modificationDate;
    }

    /**
     * Sets the modification date
     * @param ld the local date of modification
     */
    public void setModificationDate(LocalDate ld) {
        this.modificationDate = ld;
    }

    /**
     * It gets the maximum number of pages that can be obtained from the cart's pack list
     * @return the maximum number of pages that can be obtained from the cart's pack list
     */
    public int getPackMaxPageNum() {
        return Pager.getInstance().getPackMaxPageNum(this.getPacks());
    }

    /**
     * It gets the cart's packs
     * @return the cart's packs
     */
    public List<Pack> getPacks() {
        return new ArrayList<>(this.packs.keySet());
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
     * Obtains the amount of products in the cart
     * @return the number of products
     */
    public int getProductAmount() {
        return this.sp.size();
    }

    /**
     * It gets the cart's products
     * @return the cart's products
     */
    public List<StoreProduct> getProducts() {
        return new ArrayList<>(this.sp.keySet());
    }

}