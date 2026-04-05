package store;

import discount.Discount;
import product.Category;
import product.Pack;
import product.Review;
import product.StoreProduct;
import productT.*;
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
        [x] Employee
        [x] Pack
        [x] Discount
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
        List<Employee> employeeListPage = pageEmployeeList(pageNum);
        int i = 1;

        for (Employee employee : employeeListPage) {
            System.out.println(i + ". " + employee.getUserName());
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param pageNum the desired page's number
     */
    public void printPackListPage(int pageNum) {
        List<Pack> packListPage = pagePackList(pageNum);
        int i = 1;

        for (Pack pack : packListPage) {
            System.out.println(i + ". " + pack.getPrintInfo());
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param pageNum the desired page's number
     */
    public void printDiscountListPage(int pageNum) {
        List<Discount> discountListPage = pageDiscountList(pageNum);
        int i = 1;

        for (Discount discount : discountListPage) {
            System.out.println(i + ". " + discount.getPrintInfo());
        }
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
        [x] Discount
        [x] Employee
        [x] Pack
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
        List<Category> categoryListPage = pageCategoryList(pageNum);
        return categoryListPage.get(categoryNum - 1);
    }

    /**
     * It gets the discount listed as n.º discountNum in a certain page
     * @param pageNum     the desired page's number
     * @param discountNum the desired discount's n.º
     * @return the desired discount
     */
    public Discount selectDiscountFromPage(int pageNum, int discountNum) {
        List<Discount> discountListPage = pageDiscountList(pageNum);
        return discountListPage.get(discountNum - 1);
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
        List<Pack> packListPage = pagePackList(pageNum);
        return packListPage.get(packNum - 1);
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
        [x] Discount
        [x] Employee
        [x] Pack
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
        List<Discount> discountList = Store.getInstance().getDiscounts();
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        int from = (itemsPerPage - 1) * pageNum;
        int to = Math.min(discountList.size(), (pageNum * itemsPerPage) - 1);
        return discountList.subList(from, to);
    }

    /**
     * It gets a sub-list of employees according to the desired page
     * @param pageNum the desired page's number
     * @return the desired page
     */
    public List<Employee> pageEmployeeList(int pageNum) {
        List<Employee> employeeList = new ArrayList<>(Store.getInstance().getEmployees().values());
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        int from = (itemsPerPage - 1) * pageNum;
        int to = Math.min(employeeList.size(), (pageNum * itemsPerPage) - 1);
        return employeeList.subList(from, to);
    }

    /**
     * It gets a sub-list of packs according to the desired page
     * @param pageNum the desired page's number
     * @return the desired page
     */
    public List<Pack> pagePackList(int pageNum) {
        List<Pack> packList = Store.getInstance().getPacks();
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        int from = (itemsPerPage - 1) * pageNum;
        int to = Math.min(packList.size(), (pageNum * itemsPerPage) - 1);
        return packList.subList(from, to);
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

    /* MAX GETTERS:
        [x] Category
        [x] Discount
        [x] Employee
        [x] Pack
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
        // DUE: Revisar esto
        List<Discount> discountList = Store.getInstance().getDiscounts();
        if (discountList.size() % Parameter.getParam().getItemsPerPage() == 0) {
            return discountList.size() / Parameter.getParam().getItemsPerPage();
        }
        return (discountList.size() / Parameter.getParam().getItemsPerPage()) + 1;
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's employees list
     * @return the maximum number of pages that can be obtained from the store's employees list
     */
    public int getEmployeeMaxPageNum() {
        // DUE: Revisar esto
        List<Employee> employeeList = new ArrayList<>(Store.getInstance().getEmployees().values());
        if (employeeList.size() % Parameter.getParam().getItemsPerPage() == 0) {
            return employeeList.size() / Parameter.getParam().getItemsPerPage();
        }
        return (employeeList.size() / Parameter.getParam().getItemsPerPage()) + 1;
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's packs list
     * @return the maximum number of pages that can be obtained from the store's packs list
     */
    public int getPackMaxPageNum() {
        // DUE: Revisar esto
        List<Pack> packList = Store.getInstance().getPacks();
        if (packList.size() % Parameter.getParam().getItemsPerPage() == 0) {
            return packList.size() / Parameter.getParam().getItemsPerPage();
        }
        return (packList.size() / Parameter.getParam().getItemsPerPage()) + 1;
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