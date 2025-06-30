import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        int[] d = new int[m+1];
        Arrays.fill(d, 10001);
        d[0]= 0;

        //j가 만들고 싶은 금액, arr[i]가 현재 화폐 단위이다.
        //만약 현재 i = 0, j = 2 라고 하면, d[2] = Math.min(10001, d[2-2]+1)이다. d[2-2] = d[0] = 0 , 0 + 1 = 1이다. 즉 우측을 선택함.
        //그 뒤로 i=0 -> '2'인 상태에서 m(목표 금액)까지 도달하는 과정에서 화폐를 만들 수 있는지 없는지를 판단 및 개수 적용 후 다음 화폐를 같은 방식으로 돌리면,
        //결국 필요한 화폐의 최소개수가 나온다.
        for(int i = 0 ; i < n ; i++){
            for(int j = arr[i] ;  j <= m ; j++){
                d[j] = Math.min(d[j], d[j - arr[i]] + 1);
            }
        }

        if(d[m] == 10001){
            System.out.println("-1");
        }
        else{
            System.out.println(d[m]);
        }
        
        
    }
}
