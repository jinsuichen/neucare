package cn.edu.neu.commom;

import java.util.List;

/**
 * 包含通用的算法
 */
public class Algorithm {

    /**
     * 二分查找
     * @param list 待查找的集合
     * @param key 查找的目标
     * @param <E> 查找对象的类型
     * @return 目标值的索引。当未找到时返回-1
     */
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
