package store;

import product.Review;
import product.StoreProduct;
import user.RegisteredClient;

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

    /**
     * It prints a sub-list of another according to the desired page
     * @param reviewList the list fo reviews
     * @param pageNum    the desired page's number
     */
    public void printReviewListPage(List<Review> reviewList, int pageNum) {
        List<Review> reviewListPage = getReviewListPage(reviewList, pageNum);
        int i = 1;

        for (Review review : reviewListPage) {
            System.out.print(
                    i++ + ". " + "[" + review.getScoring() + "/5]" + review.getAuthor().getUserName() + " says" + ":");
            System.out.print("   " + review.getComment());
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param storeProductList the list fo reviews
     * @param pageNum          the desired page's number
     */
    public void printStoreProductListPage(List<StoreProduct> storeProductList, int pageNum) {
        List<StoreProduct> productListPage = getStoreProductListPage(storeProductList, pageNum);
        int i = 1;

        for (StoreProduct product : productListPage) {
            System.out.println(i + ". " + product.smallPrintInfo());
        }
    }

    public void printRegisteredClientListPage(List<RegisteredClient> registeredClientList, int pageNum) {
        // DUE
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    /**
     * It gets the product listed as n.º productNum in a certain page
     * @param storeProductList the list of products
     * @param pageNum          the desired page's number
     * @param productNum       the desired product's n.º
     * @return the desired product
     */
    public StoreProduct getProductFromPage(List<StoreProduct> storeProductList, int pageNum, int productNum) {
        List<StoreProduct> productListPage = getStoreProductListPage(storeProductList, pageNum);
        return productListPage.get(productNum - 1); // Las listas se imprimen empezando por 1
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
    
    public int getRegisteredClientMaxPageNum(List<RegisteredClient> registeredClientList) {
        return 0;// DUE
    }

    /**
     * It gets the review listed as n.º reviewNum in a certain page
     * @param reviewList the list fo reviews
     * @param pageNum    the desired page's number
     * @param reviewNum  the desired review's n.º
     * @return the desired review
     */
    public Review getReviewFromPage(List<Review> reviewList, int pageNum, int reviewNum) {
        List<Review> reviewListPage = getReviewListPage(reviewList, pageNum);
        return reviewListPage.get(reviewNum - 1);
    }

    /**
     * It gets a sub-list of another according to the desired page
     * @param reviewList the list fo reviews
     * @param pageNum    the desired page's number
     * @return the desired page (sub-list n.º pageNum - 1)
     */
    public List<Review> getReviewListPage(List<Review> reviewList, int pageNum) {
        int itemsPerPage = Parameter.getParam().getItemsPerPage() / 2; // Cada review ocupa dos líneas
        int from = (itemsPerPage - 1) * pageNum;
        int to = Math.min(reviewList.size(), (pageNum * itemsPerPage) - 1);
        return reviewList.subList(from, to);
    }

    /**
     * It gets the maximum number of pages that can be obtained from a product's list of reviews
     * @param storeProduct the desired product
     * @return the maximum number of pages that can be obtained from a product's list of reviews
     */
    public int getReviewMaxPageNum(StoreProduct storeProduct) {
        List<Review> reviews = storeProduct.getReviewsList();
        // DUE: Revisar esto
        if (reviews.size() % (Parameter.getParam().getItemsPerPage() / 2) == 0) {
            return reviews.size() / (Parameter.getParam().getItemsPerPage() / 2);
        }
        return (reviews.size() / (Parameter.getParam().getItemsPerPage() / 2)) + 1;
    }

    /**
     * It gets a sub-list of another according to the desired page
     * @param storeProductList the list fo reviews
     * @param pageNum          the desired page's number
     * @return the desired page (sub-list n.º pageNum - 1)
     */
    public List<StoreProduct> getStoreProductListPage(List<StoreProduct> storeProductList, int pageNum) {
        /* Lista de tamaño 12, itemsPerPage = 5, Pages: 1 [0-4], 2 [5-9], 3 [10-11] */
        /* [from, to]: from = (itemsPerPage - 1) * pageNum ,
        to = (size < (pageNum*itemsPerPage) - 1) ? size : (pageNum*itemsPerPage) - 1) <- Min */
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        int from = (itemsPerPage - 1) * pageNum;
        int to = Math.min(storeProductList.size(), (pageNum * itemsPerPage) - 1);
        return storeProductList.subList(from, to);
    }
}