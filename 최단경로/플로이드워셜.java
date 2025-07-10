import java.util.*;
import java.lang.*;
import java.io.*;

//프로이드 워셜 방식의 최단거리 알고리즘은 모든 노드들이 자신을 제외한 다른 모든 노드들을 모두 방문 시도하는 방식이다.


class Main {
    public static final int INF = (int)1e9;
    //NODE = N , EDGE = M, MAX = 500
    public static int n, m;

    //이 알고리즘은 2차원 행렬을 가지고 있고, 각 요소마다 최단거리를 저장하고 갱신한다.
    public static int[][] graph = new int[501][501];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        //최단 거리 테이블을 모두 무한으로 초기화
        for(int i = 0 ; i < 501 ; i++){
            Arrays.fill(graph[i], INF);
        }

        //자신의 노드를 방문하는 비용은 0으로 맞추기
        for(int a = 1 ; a <= n ; a++){
            for(int b = 1 ; b <= n ; b++){
                if(a == b) graph[a][b] = 0;
            }
        }    

        // a -> b = c 
        for(int i = 0 ; i < m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            
            graph[a][b] = c;
        }
        //해당 알고리즘에 가장 핵심적인 로직은  min(graph[a][b], , min[a][k] + min[k][b])을 통한 직접 방문과 다른 노드를 거쳐서 방문하는 비용중 무엇이 더 작은 비용인지 비교하는 3중반복문을 핵심으로 가진다.
        //따라서 기본적인 2차원 행렬의 복잡도 n^2과 다른 노드를 통해 방문하는 것을 계산하기위한 k의 복잡도 n을 더한 O(n^3)이란 복잡도를 가진 선형탐색 방식의 동적 계획 알고리즘이다.
        for(int k = 1 ; k <= n ; k++){
            for(int a = 1 ; a <= n ; a++){
                for(int b = 1 ; b <= n ; b++){
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }
        for(int a = 1 ; a <= n ; a++){
            for(int b = 1 ; b <= n ; b++){
                if(graph[a][b] == INF){
                    System.out.print("INF ");
                }
                else{
                    System.out.print(graph[a][b]+" ");
                }
            }
            System.out.println();
        }
        
    }
}
