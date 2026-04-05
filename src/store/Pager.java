package store;

import discount.Discount;
import product.*;
import user.Employee;
import user.RegisteredClient;

import java.util.ArrayList;
import java.util.List;

/**
 * It implements the store's pager, used for dividing big lists into small sub-lists of a desired size (lines-wise),
 * helpful when browsing (check main)
 * @author Ana O.R.
 * @version 1.0
 * @see Review
 * @see StoreProduct
 */
public class Pager {
    private static final Pager PARAM = new Pager();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A Pager's constructor
     */
    Pager() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Obtains the pager
     * @return the store's pager
     */
    public static Pager getInstance() {
        return PARAM;
    }

    /* PRINTS:
        [x] Review
        [x] Store Product
        [x] Registered Client
        [ ] Employee
        [ ] Pack
        [ ] Discount
        [x] Category
     */

    /**
     * It prints a sub-list of another according to the desired page
     * @param reviewList the list of reviews
     * @param pageNum    the desired page's number
     */
    public void printReviewListPage(List<Review> reviewList, int pageNum) {
        List<Review> reviewListPage = pageReviewList(reviewList, pageNum);
        int i = 1;

        for (Review review : reviewListPage) {
            System.out.print(
                    i++ + ". " + "[" + review.getScoring() + "/5]" + review.getAuthor().getUserName() + " says" + ":");
            System.out.print("   " + review.getComment());
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param storeProductList the list of store products
     * @param pageNum          the desired page's number
     */
    public void printStoreProductListPage(List<StoreProduct> storeProductList, int pageNum) {
        List<StoreProduct> productListPage = pageStoreProductList(storeProductList, pageNum);
        int i = 1;

        for (StoreProduct product : productListPage) {
            System.out.println(i + ". " + product.smallPrintInfo());
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param pageNum the desired page's number
     */
    public void printRegisteredClientListPage(int pageNum) {
        List<RegisteredClient> registeredClientPage = pageRegisteredClientList(pageNum);
        int i = 1;

        for (RegisteredClient client : registeredClientPage) {
            System.out.println(i + ". " + client.getUserName());
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param pageNum the desired page's number
     */
    public void printEmployeeListPage(int pageNum) {
        // DUE
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param pageNum the desired page's number
     */
    public void printPackListPage(int pageNum) {
        // DUE
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param pageNum the desired page's number
     */
    public void printDiscountListPageint(int pageNum) {
        // DUE
    }

    /**
     * It prints a sub-list of categories according to the desired page
     * @param pageNum the desired page's number
     */
    public void printCategoryListPage(int pageNum) {
        List<Category> categoryPage = pageCategoryList(pageNum);
        int i = 1;

        for (Category category : categoryPage) {
            System.out.println(i + ". " + category.getName());
        }
    }

    /* SELECTS:
        [x] Category
        [ ] Discount
        [ ] Employee
        [ ] Pack
        [x] Registered Client
        [x] Review
        [x] Store Product
     */

    /**
     * It gets the category listed as n.º categoryNum in a certain page
     * @param pageNum     the desired page's number
     * @param categoryNum the desired category's n.º
     * @return the desired category
     */
    public Category selectCategoryFromPage(int pageNum, int categoryNum) {
        List<Category> categoryList = pageCategoryList(pageNum);
        return categoryList.get(categoryNum - 1);
    }

    /**
     * It gets the discount listed as n.º discountNum in a certain page
     * @param pageNum     the desired page's number
     * @param discountNum the desired discount's n.º
     * @return the desired discount
     */
    public Discount selectDiscountFromPage(int pageNum, int discountNum) {
        return null; // DUE
    }

    /**
     * It gets the employee listed as n.º clientNum in a certain page
     * @param pageNum   the desired page's number
     * @param clientNum the desired employee's n.º
     * @return the desired employee
     */
    public Employee selectEmployeeFromPage(int pageNum, int clientNum) {
        List<Employee> employeeListPage = pageEmployeeList(pageNum);
        return employeeListPage.get(clientNum - 1);
    }

    /**
     * It gets the pack listed as n.º packNum in a certain page
     * @param pageNum the desired page's number
     * @param packNum the desired pack's n.º
     * @return the desired pack
     */
    public Pack selectPackFromPage(int pageNum, int packNum) {
        return null; // DUE
    }

    /**
     * It gets the registered client listed as n.º clientNum in a certain page
     * @param pageNum   the desired page's number
     * @param clientNum the desired client's n.º
     * @return the desired registered client
     */
    public RegisteredClient selectRegisteredClientFromPage(int pageNum, int clientNum) {
        List<RegisteredClient> registeredClientListPage = pageRegisteredClientList(pageNum);
        return registeredClientListPage.get(clientNum - 1);
    }

    /**
     * It gets the review listed as n.º reviewNum in a certain page
     * @param reviewList the list fo reviews
     * @param pageNum    the desired page's number
     * @param reviewNum  the desired review's n.º
     * @return the desired review
     */
    public Review selectReviewFromPage(List<Review> reviewList, int pageNum, int reviewNum) {
        List<Review> reviewListPage = pageReviewList(reviewList, pageNum);
        return reviewListPage.get(reviewNum - 1);
    }

    /**
     * It gets the store product listed as n.º productNum in a certain page
     * @param storeProductList the list of store products
     * @param pageNum          the desired page's number
     * @param productNum       the desired product's n.º
     * @return the desired store product
     */
    public StoreProduct selectStoreProductFromPage(List<StoreProduct> storeProductList, int pageNum, int productNum) {
        List<StoreProduct> productListPage = pageStoreProductList(storeProductList, pageNum);
        return productListPage.get(productNum - 1); // Las listas se imprimen empezando por 1
    }

    /* PAGERS:
        [x] Category
        [ ] Discount
        [ ] Employee
        [ ] Pack
        [x] Registered Client
        [x] Review
        [x] Store Product
     */

    /**
     * It gets a sub-list of categories according to the desired page
     * @param pageNum the desired page's number
     * @return the desired page
     */
    public List<Category> pageCategoryList(int pageNum) {
        List<Category> categoryList = new ArrayList<>(Store.getInstance().getCategories().values());
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        int from = (itemsPerPage - 1) * pageNum;
        int to = Math.min(categoryList.size(), (pageNum * itemsPerPage) - 1);
        return categoryList.subList(from, to);
    }

    /**
     * It gets a sub-list of discounts according to the desired page
     * @param pageNum the desired page's number
     * @return the desired page
     */
    public List<Discount> pageDiscountList(int pageNum) {
        return null; // DUE
    }

    /**
     * It gets a sub-list of employees according to the desired page
     * @param pageNum the desired page's number
     * @return the desired page
     */
    public List<Employee> pageEmployeeList(int pageNum) {
        return null; // DUE
    }

    /**
     * It gets a sub-list of packs according to the desired page
     * @param pageNum the desired page's number
     * @return the desired page
     */
    public List<Pack> pagePackList(int pageNum) {
        return null; // DUE
    }

    /**
     * It gets a sub-list of registered clients according to the desired page
     * @param pageNum the desired page's number
     * @return the desired page
     */
    public List<RegisteredClient> pageRegisteredClientList(int pageNum) {
        List<RegisteredClient> registeredClientList =
                new ArrayList<>(Store.getInstance().getRegisteredClients().values());
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        int from = (itemsPerPage - 1) * pageNum;
        int to = Math.min(registeredClientList.size(), (pageNum * itemsPerPage) - 1);
        return registeredClientList.subList(from, to);
    }

    /**
     * It gets a sub-list of another according to the desired page
     * @param reviewList the list fo reviews
     * @param pageNum    the desired page's number
     * @return the desired page
     */
    public List<Review> pageReviewList(List<Review> reviewList, int pageNum) {
        int itemsPerPage = Parameter.getParam().getItemsPerPage() / 2; // Cada review ocupa dos líneas
        int from = (itemsPerPage - 1) * pageNum;
        int to = Math.min(reviewList.size(), (pageNum * itemsPerPage) - 1);
        return reviewList.subList(from, to);
    }

    /**
     * It gets a sub-list of another according to the desired page
     * @param storeProductList the list fo reviews
     * @param pageNum          the desired page's number
     * @return the desired page
     */
    public List<StoreProduct> pageStoreProductList(List<StoreProduct> storeProductList, int pageNum) {
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        int from = (itemsPerPage - 1) * pageNum;
        int to = Math.min(storeProductList.size(), (pageNum * itemsPerPage) - 1);
        return storeProductList.subList(from, to);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /* MAXERS:
        [x] Category
        [ ] Discount
        [ ] Employee
        [ ] Pack
        [x] Registered Client
        [x] Review
        [x] Store Product
     */

    /**
     * It gets the maximum number of pages that can be obtained from the store's categories list
     * @return the maximum number of pages that can be obtained from the store's categories list
     */
    public int getCategoryMaxPageNum() {
        // DUE: Revisar esto
        List<Category> categoryList = new ArrayList<>(Store.getInstance().getCategories().values());
        if (categoryList.size() % Parameter.getParam().getItemsPerPage() == 0) {
            return categoryList.size() / Parameter.getParam().getItemsPerPage();
        }
        return (categoryList.size() / Parameter.getParam().getItemsPerPage()) + 1;
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's discounts list
     * @return the maximum number of pages that can be obtained from the store's discounts list
     */
    public int getDiscountMaxPageNum() {
        return 0; // DUE
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's employees list
     * @return the maximum number of pages that can be obtained from the store's employees list
     */
    public int getEmployeeMaxPageNum() {
        return 0; // DUE
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's packs list
     * @return the maximum number of pages that can be obtained from the store's packs list
     */
    public int getPackMaxPageNum() {
        return 0; // DUE
    }

    /**
     * It gets the maximum number of pages that can be obtained from the registered client's list
     * @return the maximum number of pages that can be obtained from the registered client's list
     */
    public int getRegisteredClientMaxPageNum() {
        // DUE: Revisar esto
        List<RegisteredClient> registeredClientList =
                new ArrayList<>(Store.getInstance().getRegisteredClients().values());
        if (registeredClientList.size() % Parameter.getParam().getItemsPerPage() == 0) {
            return registeredClientList.size() / Parameter.getParam().getItemsPerPage();
        }
        return (registeredClientList.size() / Parameter.getParam().getItemsPerPage()) + 1;
    }

    /**
     * It gets the maximum number of pages that can be obtained from a product's list of reviews
     * @param storeProduct the desired product
     * @return the maximum number of pages that can be obtained from a product's list of reviews
     */
    public int getReviewMaxPageNum(StoreProduct storeProduct) {
        // DUE: Revisar esto
        List<Review> reviews = storeProduct.getReviewsList();
        if (reviews.size() % (Parameter.getParam().getItemsPerPage() / 2) == 0) {
            return reviews.size() / (Parameter.getParam().getItemsPerPage() / 2);
        }
        return (reviews.size() / (Parameter.getParam().getItemsPerPage() / 2)) + 1;
    }

    /**
     * It gets the maximum number of pages that can be obtained from a list of products
     * @param storeProductList the desired product list
     * @return the maximum number of pages that can be obtained from a list of products
     */
    public int getStoreProductMaxPageNum(List<StoreProduct> storeProductList) {
        // DUE: Revisar esto
        if (storeProductList.size() % Parameter.getParam().getItemsPerPage() == 0) {
            return storeProductList.size() / Parameter.getParam().getItemsPerPage();
        }
        return (storeProductList.size() / Parameter.getParam().getItemsPerPage()) + 1;
    }
}