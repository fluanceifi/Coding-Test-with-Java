import java.util.*;

class Main{
    public static int fibonachi(int[] arr, int n){
        if(n == 0 || n == 1){
            return 1;
        }

        if(arr[n] != 0){
            return arr[n];
        }

        arr[n] = fibonachi(arr, n-1) + fibonachi(arr, n-2);

        return arr[n];
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+1];

        System.out.println(fibonachi(arr, n-1));
        

        
    }
