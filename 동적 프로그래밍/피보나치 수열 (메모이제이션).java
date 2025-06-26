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
        //100을 입력시 101은 있어야 출력가능
        int[] arr = new int[n+1];

        //100번째를 출력하고 싶다면, 100-1을 해야 100번째가 출력됨 why? index는 0부터 시작
        System.out.println(fibonachi(arr, n-1));
        

        
    }
