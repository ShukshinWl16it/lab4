package ru.shukshin.comparable;

public class MyNumber implements Comparable<MyNumber> {
    private Number value;
    public MyNumber(Number value){
        this.value=value;
    }
    @Override
    public int compare(MyNumber other){
        return Double.compare(this.value.doubleValue(),other.value.doubleValue());
    }
    @Override
    public String toString(){
        return "Число:"+value;
    }

}
