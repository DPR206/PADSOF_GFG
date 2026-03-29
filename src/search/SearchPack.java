package search;

import product.*;

public class SearchPack extends SearchID{

    private List<Pack> packs;

    public SearchPack(List<Pack> p){
        this.packs = p;
    }

    @Override
    public Pack searchByID(int id){
        for(Pack p: this.packs){
            if(p.getId() == id) return p;
        }
        return null;
    }
}