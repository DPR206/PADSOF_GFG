package search;

public class PunctuationFilter{
    private int min;
    private int max;

    public PunctuationFilter(int min, int max){
        this.min = min;
        this.max = max;
    }

    public int getMin(){
        return this.min;
    }
    public int getMax(){
        return this.max;
    }
}