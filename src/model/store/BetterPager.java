package model.store;

import model.discount.Discount;
import model.exchange.Exchange;
import model.order.Order;
import model.product.*;
import model.user.Employee;
import model.user.RegisteredClient;

import java.util.List;
import java.util.Objects;

/**
 * It implements the store's pager, used for dividing big lists into small sub-lists of a desired size (lines-wise),
 * helpful when browsing (check main)
 * @author Ana O.R.
 * @version 1.0
 * @see Review
 * @see StoreProduct
 * @see RegisteredClient
 * @see Employee
 * @see Pack
 * @see Discount
 * @see Category
 */
public class BetterPager<G> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The pager's constructor
     */
    private BetterPager() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    public G selectItemFromPage(List<G> itemList, int pageNum, int itemNum) {
        List<G> itemListPage = pageItemList(itemList, pageNum);
        return itemListPage.get(itemNum - 1);
    }

    public List<G> pageItemList(List<G> itemList, int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }

        return itemList.subList(getFrom(pageNum), getTo(pageNum, itemList.size()));
    }

    /**
     * It gets the maximum number of pages that can be made from a list's certain size
     * @param size the size
     * @return the maximum number of pages that can be made from the list's certain size
     */
    private int maxPageNum(int size) {
        if (size % Parameter.getParam().getItemsPerPage() == 0) {
            return size / Parameter.getParam().getItemsPerPage();
        }
        return (size / Parameter.getParam().getItemsPerPage()) + 1;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the paged index belonging to a discount with a certain id in a list
     * @param discounts the desired list of discounts
     * @param wantedId  the id of the desired discount
     * @return the paged index belonging to a discount with a certain id in a list
     */
    public int getDiscountIndex(List<Discount> discounts, String wantedId) {
        for (int j = 0; j < discounts.size(); j++) {
            String discountId = discounts.get(j).getId();

            if (Objects.equals(wantedId, discountId)) {
                return j;
            }
        }
        return -1;
    }

    /**
     * It gets the paged index belonging to an employee with a certain username in a list
     * @param employees      the desired list of employees
     * @param wantedUsername the username of the desired employee
     * @return the paged index belonging to an employee with a certain username in a list
     */
    public int getEmployeeIndex(List<Employee> employees, String wantedUsername) {
        for (int j = 0; j < employees.size(); j++) {
            String username = employees.get(j).getUserName();

            if (Objects.equals(wantedUsername, username)) {
                return j;
            }
        }
        return -1;
    }

    /**
     * It gets the paged index belonging to an exchange with a certain id in a list
     * @param exchanges the desired list of exchanges
     * @param wantedId  the id of the desired pack
     * @return the index belonging to an order with a certain id in a list
     */
    public int getExchangeIndex(List<Exchange> exchanges, int wantedId) {
        for (int j = 0; j < exchanges.size(); j++) {
            int exchangeId = exchanges.get(j).getId();

            if (wantedId == exchangeId) {
                return j;
            }
        }
        return -1;
    }

    /**
     * Auxiliary function to calculate at which index a page starts
     * @param pageNum the desired page number
     * @return the index at which the page starts
     */
    public int getFrom(int pageNum) {
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        if (pageNum == 1) {
            return 0;
        }
        return (itemsPerPage * (pageNum - 1) - 1);
    }

    public int getItemMaxPageNum(List<G> itemList) {
        return maxPageNum(itemList.size());
    }

    /**
     * It gets the item number corresponding to a certain list's index
     * @param index the desired index
     * @return the item number corresponding to a certain list's index
     */
    public int getItemNumFromIndex(int index) {
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        return ((index % itemsPerPage) + 1);
    }

    /**
     * It gets the paged index belonging to an order with a certain id in a list
     * @param orders   the desired list of orders
     * @param wantedId the id of the desired pack
     * @return the index belonging to an order with a certain id in a list
     */
    public int getOrderIndex(List<Order> orders, int wantedId) {
        for (int j = 0; j < orders.size(); j++) {
            int orderId = orders.get(j).getId();

            if (wantedId == orderId) {
                return j;
            }
        }
        return -1;
    }

    /**
     * It gets the paged index belonging to a pack with a certain id in a list
     * @param packs    the desired list of packs
     * @param wantedId the id of the desired pack
     * @return the index belonging to a pack with a certain id in a list
     */
    public int getPackIndex(List<Pack> packs, int wantedId) {
        for (int j = 0; j < packs.size(); j++) {
            int packId = packs.get(j).getId();

            if (wantedId == packId) {
                return j;
            }
        }
        return -1;
    }

    /**
     * It gets the page number corresponding to a certain list's index
     * @param index the desired index
     * @return the page number corresponding to a certain list's index
     */
    public int getPageNumFromIndex(int index) {
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        return (index / itemsPerPage) + 1;
    }

    /**
     * It gets the paged index belonging to a second hand product with a certain id in a list
     * @param products the desired list of second hand products
     * @param wantedId the id of the desired second hand product
     * @return the index belonging to a second hand product with a certain id in a list
     */
    public int getSecondHandProductIndex(List<SecondHandProduct> products, String wantedId) {
        for (int j = 0; j < products.size(); j++) {
            String productId = products.get(j).getId();

            if (wantedId.equals(productId)) {
                return j;
            }
        }
        return -1;
    }

    /**
     * It gets the paged index belonging to a store product with a certain id in a list
     * @param products the desired list of store products
     * @param wantedId the id of the desired store product
     * @return the index belonging to a store product with a certain id in a list
     */
    public int getStoreProductIndex(List<StoreProduct> products, String wantedId) {
        for (int j = 0; j < products.size(); j++) {
            String productId = products.get(j).getId();

            if (wantedId.equals(productId)) {
                return j;
            }
        }
        return -1;
    }

    /**
     * Auxiliary function to calculate at which index a page ends
     * @param pageNum the desired page number
     * @param size    the page's list's size
     * @return the index at which the page ends
     */
    public int getTo(int pageNum, int size) {
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        return Math.min(size, getFrom(pageNum) + itemsPerPage);
    }
}