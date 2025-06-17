import java.util.*;

public class Main{
    public static int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};
    public static void   main(String[] args){

        int size = arr.length;

        for(int i = 1 ; i < size ; i++){
            for(int num : arr) System.out.print(num + " ");
            System.out.println();
            int key = arr[i];
            int j;
            for(j = i - 1 ; j >= 0 && arr[j] > key ; j--){
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = key;
        }
        for(int num : arr)System.out.print(num + " ");
    }
}
/*
7 5 9 0 3 1 6 2 4 8 
5 7 9 0 3 1 6 2 4 8 
5 7 9 0 3 1 6 2 4 8 
0 5 7 9 3 1 6 2 4 8 
0 3 5 7 9 1 6 2 4 8 
0 1 3 5 7 9 6 2 4 8 
0 1 3 5 6 7 9 2 4 8 
0 1 2 3 5 6 7 9 4 8 
0 1 2 3 4 5 6 7 9 8 
0 1 2 3 4 5 6 7 8 9 %  
*/
