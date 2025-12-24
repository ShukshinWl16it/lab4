package ru.shukshin.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filter {
    private String name;
    public Filter(String name){
        this.name=name;
    }
    public static <T> List<T> filter(List<T> list, TestFilter<T> filt){
        if(list==null||list.isEmpty()){
            throw new IllegalArgumentException("Список пуст");
        }
        List<T> result=new ArrayList<>();
        for(T element:list){
            if(filt.test(element)){
                result.add(element);
            }
        }
        return result;
    }
    public static String arraysToString(List<int[]> arrays) {
        String result = "[";
        for (int i = 0; i < arrays.size(); i++) {
            result += Arrays.toString(arrays.get(i));
            if (i <arrays.size()-1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
    public String toString() {
        return "Предназначение фильтра: "+name;
    }

}
