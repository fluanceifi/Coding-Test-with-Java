import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] d = new int[n];
        
        //2x1일땐  2x1 타일만 가능 -> 1, 2x2일 땐 2x1 * 2, 1x2 * 2, 2x2 타일 가능 -> 3
        d[1] = 1;
        d[2] = 3;

        //점화식이니깐  “이전까지 깔아둔 경우의 수” + “그걸 어떻게 새로 확장하는지 규칙"이 전해진다.
        //d[i-1] = 2x1을 까는 경우이고, d[i-2]는 2x2를 하나 두던가 1x2를 두개 두던가 하는 경우의 수이다.
        //이해하기 힘들면 재귀식에서 트리로 나뉠때 "return f(i-1)"가 계속 2x1타일만 사용하는 경우의 수라 생각하면 쉬움.
        for(int i = 3 ; i < n ; i++){
            d[i] = (d[i-1] + 2 * d[i-2] % 796796); 
        }
        System.out.println(d[n-1]);
    }
}
