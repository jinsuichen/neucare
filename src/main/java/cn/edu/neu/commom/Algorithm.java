package cn.edu.neu.commom;

import java.util.List;

public class Algorithm {

    public static <E extends Comparable<E>> int binarySearch(List<E> list, E key){
        int low = 0;
        int high = list.size() - 1;
        int mid;

        while(low <= high ){
            mid = low + ((high - low)>>1);
            if(list.get(mid).compareTo(key) == 0 ){
                return mid;
            }
            if(list.get(mid).compareTo(key) > 0 ){
                high = mid -1;
            }
            if(list.get(mid).compareTo(key) < 0 ){
                low = mid + 1;
            }
        }
        return -1;
    }
}
