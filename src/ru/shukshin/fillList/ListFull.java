package ru.shukshin.fillList;

import java.util.List;
public class ListFull {
    public static void fillList(List<? super Integer> list){
        list.clear();
        for(int i=1;i<=100;i++){
            list.add(i);
        }
    }
    public static String toString(List<?> list){
        if(list==null){
            return "Пусто";
        }
        String result="[";
        for(int i=0;i<list.size();i++){
            result+=list.get(i);
            if(i<99){
                result+=", ";
            }
        }
        result+="]";
        return result;
    }
}
