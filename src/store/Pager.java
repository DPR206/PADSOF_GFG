package store;

import discount.Discount;
import product.*;
import user.Employee;
import user.RegisteredClient;

import java.util.List;

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
public class Pager {
    private static final Pager PARAM = new Pager();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The pager's constructor
     */
    Pager() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    // DUE: Idealmente, no se le pasan listas directamente desde los loops si no que se invocan esto métodos desde la
    //  clase que posea dicha lista, si sobrevivo al resto lo implemento <- Estoy en ello

    /**
     * It gets the pager's instance
     * @return the store's pager
     */
    public static Pager getInstance() {
        return PARAM;
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param reviewList the list of reviews
     * @param pageNum    the desired page's number
     */
    public void printReviewListPage(List<Review> reviewList, int pageNum) {
        if (reviewList == null || reviewList.isEmpty()) {
            System.out.println("There are no reviews");
            return;
        }

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
        if (storeProductList == null || storeProductList.isEmpty()) {
            System.out.println("There are no store products");
            return;
        }

        List<StoreProduct> productListPage = pageStoreProductList(storeProductList, pageNum);
        int i = 1;
        for (StoreProduct product : productListPage) {
            System.out.println(i++ + ". " + product.smallPrintInfo());
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param clients the clients
     * @param pageNum the desired page's number
     */
    public void printRegisteredClientListPage(List<RegisteredClient> clients, int pageNum) {
        if (clients == null || clients.isEmpty()) {
            System.out.println("There are no registered clients");
            return;
        }

        List<RegisteredClient> registeredClientPage = pageRegisteredClientList(clients, pageNum);
        int i = 1;

        for (RegisteredClient client : registeredClientPage) {
            System.out.println(i++ + ". " + client.getUserName());
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param employees the employees
     * @param pageNum   the desired page's number
     */
    public void printEmployeeListPage(List<Employee> employees, int pageNum) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("There are no employees");
            return;
        }

        List<Employee> employeeListPage = pageEmployeeList(employees, pageNum);
        int i = 1;

        for (Employee employee : employeeListPage) {
            System.out.println(i++ + ". " + employee.getUserName());
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param packList the desired pack list
     * @param pageNum  the desired page's number
     */
    public void printPackListPage(List<Pack> packList, int pageNum) {
        if (packList == null || packList.isEmpty()) {
            System.out.println("There are no packs");
            return;
        }

        List<Pack> packListPage = pagePackList(packList, pageNum);
        int i = 1;

        for (Pack pack : packListPage) {
            System.out.println(i++ + ". " + pack.getPrintInfo());
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param discountList the discount list
     * @param pageNum      the desired page's number
     */
    public void printDiscountListPage(List<Discount> discountList, int pageNum) {
        if (discountList == null || discountList.isEmpty()) {
            System.out.println("There are no discounts");
            return;
        }

        List<Discount> discountListPage = pageDiscountList(discountList, pageNum);
        int i = 1;

        for (Discount discount : discountListPage) {
            System.out.println(i++ + ". " + discount.getPrintInfo());
        }
    }

    /**
     * It prints a sub-list of categories according to the desired page
     * @param categoryList the category list
     * @param pageNum      the desired page's number
     */
    public void printCategoryListPage(List<Category> categoryList, int pageNum) {
        if (categoryList == null || categoryList.isEmpty()) {
            System.out.println("There are no categories");
            return;
        }

        List<Category> categoryListPage = pageCategoryList(categoryList, pageNum);
        int i = 1;

        for (Category category : categoryList) {
            System.out.println(i++ + ". " + category.getName());
        }
    }

    /**
     * It gets the category listed as n.º categoryNum in a certain page
     * @param categoryListPage the category list page
     * @param pageNum          the desired page's number
     * @param categoryNum      the desired category's n.º
     * @return the desired category
     */
    public Category selectCategoryFromPage(List<Category> categoryListPage, int pageNum, int categoryNum) {
        return categoryListPage.get(categoryNum - 1);
    }

    /**
     * It gets the discount listed as n.º discountNum in a certain page
     * @param discountListPage the discount list page
     * @param pageNum          the desired page's number
     * @param discountNum      the desired discount's n.º
     * @return the desired discount
     */
    public Discount selectDiscountFromPage(List<Discount> discountListPage, int pageNum, int discountNum) {
        return discountListPage.get(discountNum - 1);
    }

    /**
     * It gets the employee listed as n.º clientNum in a certain page
     * @param employees the employees
     * @param pageNum   the desired page's number
     * @param clientNum the desired employee's n.º
     * @return the desired employee
     */
    public Employee selectEmployeeFromPage(List<Employee> employees, int pageNum, int clientNum) {
        List<Employee> employeeListPage = pageEmployeeList(employees, pageNum);
        return employeeListPage.get(clientNum - 1);
    }

    /**
     * It gets the pack listed as n.º packNum in a certain page
     * @param packList the desired pack list
     * @param pageNum  the desired page's number
     * @param packNum  the desired pack's n.º
     * @return the desired pack
     */
    public Pack selectPackFromPage(List<Pack> packList, int pageNum, int packNum) {
        List<Pack> packListPage = pagePackList(packList, pageNum);
        return packListPage.get(packNum - 1);
    }

    /**
     * It gets the registered client listed as n.º clientNum in a certain page
     * @param clients   the clients
     * @param pageNum   the desired page's number
     * @param clientNum the desired client's n.º
     * @return the desired registered client
     */
    public RegisteredClient selectRegisteredClientFromPage(List<RegisteredClient> clients, int pageNum, int clientNum) {
        List<RegisteredClient> registeredClientListPage = pageRegisteredClientList(clients, pageNum);
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

    /**
     * It gets a sub-list of categories according to the desired page
     * @param categoryList the category list
     * @param pageNum      the desired page's number
     * @return the desired page
     */
    public List<Category> pageCategoryList(List<Category> categoryList, int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }

        return categoryList.subList(getFrom(pageNum), getTo(pageNum, categoryList.size()));
    }

    /**
     * It gets a sub-list of discounts according to the desired page
     * @param discountList the discount list
     * @param pageNum      the desired page's number
     * @return the desired page
     */
    public List<Discount> pageDiscountList(List<Discount> discountList, int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }

        return discountList.subList(getFrom(pageNum), getTo(pageNum, discountList.size()));
    }

    /**
     * It gets a sub-list of employees according to the desired page
     * @param employeeList the employee list
     * @param pageNum      the desired page's number
     * @return the desired page
     */
    public List<Employee> pageEmployeeList(List<Employee> employeeList, int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }

        return employeeList.subList(getFrom(pageNum), getTo(pageNum, employeeList.size()));
    }

    /**
     * It gets a sub-list of packs according to the desired page
     * @param packList the desired pack list
     * @param pageNum  the desired page's number
     * @return the desired page
     */
    public List<Pack> pagePackList(List<Pack> packList, int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }

        return packList.subList(getFrom(pageNum), getTo(pageNum, packList.size()));
    }

    /**
     * It gets a sub-list of registered clients according to the desired page
     * @param registeredClientList the registered client list
     * @param pageNum              the desired page's number
     * @return the desired page
     */
    public List<RegisteredClient> pageRegisteredClientList(List<RegisteredClient> registeredClientList, int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }

        return registeredClientList.subList(getFrom(pageNum), getTo(pageNum, registeredClientList.size()));
    }

    /**
     * It gets a sub-list of another according to the desired page
     * @param reviewList the list fo reviews
     * @param pageNum    the desired page's number
     * @return the desired page
     */
    public List<Review> pageReviewList(List<Review> reviewList, int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }

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
        if (pageNum < 1) {
            pageNum = 1;
        }

        return storeProductList.subList(getFrom(pageNum), getTo(pageNum, storeProductList.size()));
    }

    private int maxPageNum(int size) {
        if (size % Parameter.getParam().getItemsPerPage() == 0) {
            return size / Parameter.getParam().getItemsPerPage();
        }
        return (size / Parameter.getParam().getItemsPerPage()) + 1;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the maximum number of pages that can be obtained from the store's categories list
     * @param categoryList the category list
     * @return the maximum number of pages that can be obtained from the store's categories list
     */
    public int getCategoryMaxPageNum(List<Category> categoryList) {
        return maxPageNum(categoryList.size());
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's discounts list
     * @param discountList the discount list
     * @return the maximum number of pages that can be obtained from the store's discounts list
     */
    public int getDiscountMaxPageNum(List<Discount> discountList) {
        return maxPageNum(discountList.size());
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's employees list
     * @param employeeList the employee list
     * @return the maximum number of pages that can be obtained from the store's employees list
     */
    public int getEmployeeMaxPageNum(List<Employee> employeeList) {
        return maxPageNum(employeeList.size());
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
     * It gets the maximum number of pages that can be obtained from the store's packs list
     * @param packList the pack list
     * @return the maximum number of pages that can be obtained from the store's packs list
     */
    public int getPackMaxPageNum(List<Pack> packList) {
        return maxPageNum(packList.size());
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
     * It gets the maximum number of pages that can be obtained from the registered client's list
     * @param registeredClientList the registered client list
     * @return the maximum number of pages that can be obtained from the registered client's list
     */
    public int getRegisteredClientMaxPageNum(List<RegisteredClient> registeredClientList) {
        return maxPageNum(registeredClientList.size());
    }

    /**
     * It gets the maximum number of pages that can be obtained from a product's list of reviews
     * @param storeProduct the desired product
     * @return the maximum number of pages that can be obtained from a product's list of reviews
     */
    public int getReviewMaxPageNum(StoreProduct storeProduct) {
        return maxPageNum(storeProduct.getReviewsList().size());
    }

    /**
     * It gets the index belonging to a store product with a certain id in a list
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
     * It gets the maximum number of pages that can be obtained from a list of products
     * @param storeProductList the desired product list
     * @return the maximum number of pages that can be obtained from a list of products
     */
    public int getStoreProductMaxPageNum(List<StoreProduct> storeProductList) {
        return maxPageNum(storeProductList.size());
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