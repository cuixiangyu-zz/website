package com.cxy.website.common.util;

/**
 * @program: website
 * @description: 冒泡
 * @author: CuiXiangYu
 * @create: 2020-06-23 12:27
 **/
public class Loop {

    public static void main(String[] args){
        int[] arr =new int[] {1,2,5,3,9,4,6,8,2,12};
        int[] buble = buble(arr);
        System.out.println(buble.toString());
    }

    public static int[] buble(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0;j<=i;j++){
                if(arr[i]>arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }


}
