package com.txznet.test;

public class RecursiveOrder {

    public void Perm(int list[], int k, int m) {
        if (k == m) {
            for (int i = 0; i <= m; i++)
                System.out.print(list[i]);
            System.out.println();
        } else {
            for (int i = k; i <= m; i++) {
                // 从固定的数后第一个依次交换
                Swap(list, k, i);
                Perm(list, k + 1, m);
                // 这组递归完成之后需要交换回来
                Swap(list, k, i);
            }
        }

    }
    public void Swap(int[] list, int i, int j) {
        int t = list[i];
        list[i] = list[j];
        list[j] = t;
    }



    public int jieCheng(int i){
        if (i > 2){
            return i*jieCheng(i-1);
        }else {
            return i;
        }
    }





    public static void main(String []arg){
        RecursiveOrder recursiveOrder = new RecursiveOrder();
        recursiveOrder.Perm(new int[]{1,2,3,4},0,1);

        System.out.println(recursiveOrder.jieCheng(4));
    }

}
