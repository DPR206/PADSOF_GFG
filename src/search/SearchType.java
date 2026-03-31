package search;

public enum SearchType{
    S_STORE("store"),
    S_ORDER("order"),
    S_EXCHANGE("exchange"),
    S_PACK("pack"),
    S_EMPLOYEE("employee"),
    S_SECOND_HAND("second hand"),
    S_VALORATION_PROD("valoration prod");

    private String theType;

    private SearchType(String theType){
        this.theType = theType;
    }
    public String getType(){
        return this.theType;
    }
}