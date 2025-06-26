import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] fibo = new int[n];

        fibo[0] = 1;
        fibo[1] = 1;
        
        //반복문을 이용한 bottom-up 방식
        for(int i = 2 ; i < n ; i++)
            fibo[i] = fibo[i-1] + fibo[i-2];

        System.out.println(fibo[n-1]);
    }
}
