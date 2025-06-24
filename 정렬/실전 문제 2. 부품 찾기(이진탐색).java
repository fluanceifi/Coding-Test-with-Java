import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static int binary_search(Integer[] arr, int key, int start, int end){
        if(start > end) return -1;
        
        int mid = (start + end) / 2;
        if(arr[mid] == key) return mid;
        
        if(arr[mid] > key) return binary_search(arr, key , start, mid - 1);
        else return binary_search(arr, key , mid + 1, end);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        Integer[] stock = new Integer[n];
        for(int i = 0 ; i < n ; i++) stock[i] = sc.nextInt();
        Arrays.sort(stock);

        int m = sc.nextInt();
        Integer[] req = new Integer[m];
        for(int i = 0 ; i < m ; i++) req[i] = sc.nextInt();

        for(int key : req){
            if(binary_search(stock, key, 0, n - 1) == -1) System.out.println("no");
            else System.out.println("yes");
        }
        
    }
}
