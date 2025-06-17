import java.util.*;

public class Main{
    public static int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};
    public static void main(String[] args){

        int temp;

        for(int i = 0 ; i < arr.length ; i++){
            int min = i;
            for(int j = i+1 ; j < arr.length ; j++){
                if(arr[min] > arr[j]){
                    min = j;
                }
            }
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
            for(int num : arr) System.out.print(num);
            System.out.println();
        }


    }
}
