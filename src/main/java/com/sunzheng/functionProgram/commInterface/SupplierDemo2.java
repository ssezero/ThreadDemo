package com.sunzheng.functionProgram.commInterface;

import java.util.function.Supplier;

/**
 * @ClassName SupplierDemo2
 * @Description
 * 返回数组最大值
 * @Author Neal
 * @Date 2021/8/18 15:44
 * @Version 1.0
 **/
public class SupplierDemo2 {
    static Integer getMax(Supplier<Integer> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 7, 10, 34};
        Integer max = getMax(() -> {
            int k=array[0];
            for (int i : array) {
                if(i>k){
                    k=i;
                }
            }
            return k;
        });
        System.out.println("最大值是:"+max);
    }
}
