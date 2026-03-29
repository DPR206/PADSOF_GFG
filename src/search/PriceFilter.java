package search;

public class PriceFilter{
    private double min;
    private double max;

    public PriceFilter(double min, double max){
        this.min = min;
        this.max = max;
    }
    public double getMin(){
        return this.min;
    }
    public double getMax(){
        return this.max;
    }
}