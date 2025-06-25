import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static int binary_search(Integer[] arr, int key, int start, int end){
        int result = 0;
        
        while(start <= end){
            int mid = (start + end) / 2;
            int sum = 0;
            
            for(int num : arr) 
                if(num > mid)
                    sum += (num - mid);
                    
            //while문이 반복되면서 mid값이 커질 수 밖에 없는 구조이다. 만약, if문을 만족 못한다면 줄어들 것이고 점차 fit하게 값을 찾아간다.
            if(sum >= key) {
                result = mid;
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }   
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();

        Integer[] riceCake = new Integer[n];
        
        for(int i = 0 ; i < n ; i++) riceCake[i] = sc.nextInt();

        int key = m;
        Arrays.sort(riceCake);
        
        int result = binary_search(riceCake, key, 0, riceCake[n-1]);

        if(result != -1) System.out.println(result);


        sc.close();
    }
}
