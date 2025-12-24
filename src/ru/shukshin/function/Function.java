package ru.shukshin.function;

import java.util.*;

public class Function {
    private String name;
    public Function(String name){
        this.name=name;
    }
    public static <T,P> List<P> map(List<T> list, ApplyFunction<T,P> function){
        if(list==null||list.isEmpty()){
            throw new IllegalArgumentException("Списка не существует");
        }
        List<P> result=new ArrayList<>();
        for(T element:list){
            if (element != null) {
                result.add(function.apply(element));
            } else {
                result.add(null); // или можно пропустить null значения
            }
        }
        return result;
    }

    public static String arraysToString(List<int[]> arrays) {
        String result = "[";
        for (int i = 0; i < arrays.size(); i++) {
            result += Arrays.toString(arrays.get(i));
            if (i < arrays.size()-1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
    public String toString() {
        return "Предназначение функции: "+name;
    }
}
