package search;

import product.Pack;
import store.Store;

import java.util.List;

/**
 * It implements the pack filter through ID
 * @author Sofía C.L.
 * @version 1.3
 * @see SearchStoreProducts
 */
public class SearchPack implements SearchID {

    Store s = Store.getInstance();

    private List<Pack> packs;
    /**
	 * Creates the class
	 *
	 * @param s, sends the store to get all the packs available
	 */
    public SearchPack(){
        this.packs = s.getPacks();
    }

    /**
	 * Searches the order that has the id sent as a parameter
	 *
	 * @param id, id of the pack the user wants to find
	 */
    @Override
    public Pack searchByID(int id){
        for(Pack p: this.packs){
            if(p.getId() == id) return p;
        }
        return null;
    }
}