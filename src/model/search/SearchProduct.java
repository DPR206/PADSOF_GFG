package model.search;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;

public class SearchProduct implements SearchID, Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */

	Store s = Store.getInstance();

    private List<StoreProduct> sp;

    public SearchProduct() {
    	this.sp = Store.getInstance().getStoreProductList();
    }

	@Override
	public StoreProduct searchByID(int id) {

		for(StoreProduct ssp: this.sp){
            if(ssp.getId().equals(id)) return ssp;
        }
        return null;
	}

}