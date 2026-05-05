package model.store;

import java.util.List;

/**
 * It implements the store's pager, used for dividing big lists into small sub-lists of a desired size (lines-wise),
 * helpful when browsing (check main)
 * @author Ana O.R.
 * @version 1.0
 */
public class BetterPager<G> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The pager's constructor
     */
    public BetterPager() {

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

    public List<G> pageItemListCluster(List<G> itemList, int pageNum, int itemsPerPage) {
        if (pageNum < 1) {
            pageNum = 1;
        }

        return itemList.subList(getFromCluster(pageNum, itemsPerPage),
                getToCluster(pageNum, itemList.size(), itemsPerPage));
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

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
        return (itemsPerPage * (pageNum - 1));
    }

    public int getFromCluster(int pageNum, int itemsPerPage) {
        if (pageNum == 1) {
            return 0;
        }
        return (itemsPerPage * (pageNum - 1));
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
     * It gets the maximum number of pages that can be made from a list's certain size
     * @param itemList the list of items
     * @return the maximum number of pages that can be made from the list's certain size
     */
    public int getMaxPageNum(List<G> itemList) {
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        int size = itemList.size();
        if (size % itemsPerPage == 0) {
            return size / itemsPerPage;
        }
        return (size / itemsPerPage) + 1;
    }

    public int getMaxPageNumCluster(List<G> itemList, int itemsPerPage) {
        int size = itemList.size();
        if (size % itemsPerPage == 0) {
            return size / itemsPerPage;
        }
        return (size / itemsPerPage) + 1;
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
     * Auxiliary function to calculate at which index a page ends
     * @param pageNum the desired page number
     * @param size    the page's list's size
     * @return the index at which the page ends
     */
    public int getTo(int pageNum, int size) {
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        return Math.min(size, getFrom(pageNum) + itemsPerPage);
    }

    public int getToCluster(int pageNum, int size, int itemsPerPage) {
        return Math.min(size, getFrom(pageNum) + itemsPerPage);
    }
}