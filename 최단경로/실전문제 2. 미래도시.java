import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    public static final int INF = (int)1e9;
    //NODE = N , EDGE = M, 방문할 회사 = X, 소개팅 위치 = K, MAX = 101
    public static int n, m, x, k;
    public static int[][] graph = new int[101][101];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        //최단 거리 테이블을 모두 무한으로 초기화
        for(int i = 0 ; i < 101 ; i++){
            Arrays.fill(graph[i], INF);
        }

        //자신의 노드를 방문하는 비용은 0으로 맞추기
        for(int a = 1 ; a <= n ; a++){
            for(int b = 1 ; b <= n ; b++){
                if(a == b) graph[a][b] = 0;
            }
        }    

        // a -> b = 1
        for(int i = 0 ; i < m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
        
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
        // x = 방문회사, k = 소개팅 
        x = sc.nextInt();
        k = sc.nextInt();

        
        for(int c = 1 ; c <= n ; c++){
            for(int a = 1 ; a <= n ; a++){
                for(int b = 1 ; b <= n ; b++){
                    graph[a][b] = Math.min(graph[a][b], graph[a][c] + graph[c][b]);
                }
            }
        }
        /*                     --TEST CODE--
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= n ; j ++){
                if(graph[i][j] >= INF) System.out.print("INF ");
                else System.out.print(graph[i][j]+" ");
            }
            System.out.println("");
        }
        */

        
        int result = graph[1][k]+graph[k][x];
        
        if(result >= INF)
            System.out.println("-1");
        else{
            System.out.println(result);
        }
        
    }
}
