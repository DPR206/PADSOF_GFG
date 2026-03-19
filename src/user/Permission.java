package user;

public enum Permission{
    STORE("store"),
    ORDER("order"),
    EXCHANGE("exchange");

    private String meaning;

    private Permission(String meaning){
        this.meaning = meaning;
    }

    public String getMeaning(){
        return this.meaning;
    }
}