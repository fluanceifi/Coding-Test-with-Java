import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        //결과를 위한 배열 생성
        int[] d = new int[n];


        //처음과, 두번째를 비교하여 큰 값을 d[1]로 결정 -> 처음 값이 크다면 한칸 그 이후의 값을 비교 가능
        d[0] = arr[0];
        d[1] = Math.max(arr[0], arr[1]);
        
        for(int i = 2 ; i < n ; i++){
            d[i] = Math.max(d[i-1], d[i-2] + arr[i]);
        }
        System.out.println(d[n-1]);
    }
}
