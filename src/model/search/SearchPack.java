package model.search;

import model.product.Pack;
import model.store.Store;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * This class implements a search mechanism to find packs by their ID.
 *
 * <p>It retrieves all available packs from the {@link Store} and
 * performs a search to locate a specific pack using its identifier.</p>
 *
 * @author Sofía C.L.
 * @version 1.4
 * @see SearchStoreProducts
 */
public class SearchPack implements SearchID, Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */

    Store s = Store.getInstance();

    private List<Pack> packs;

    /**
     * Creates the class and initializes the list of packs
     * obtained from the store.
     */
    public SearchPack(){
        this.packs = s.getPacks();
    }

    /**
     * Searches for a pack by its ID.
     *
     * <p>Iterates through the list of packs and returns the one
     * whose ID matches the given parameter. If no pack is found,
     * {@code null} is returned.</p>
     *
     * @param id the ID of the pack to search for
     * @return the {@link Pack} with the given ID, or {@code null} if it does not exist
     */
    @Override
    public Pack searchByID(int id){
        for(Pack p: this.packs){
            if(p.getId() == id) return p;
        }
        return null;
    }
}