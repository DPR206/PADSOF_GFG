package model.search;

import java.util.List;

import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;

public class SearchProduct implements SearchID{

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
