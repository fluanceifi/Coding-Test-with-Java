import java.util.*;
import java.lang.*;
import java.io.*;


//해당 알고리즘 문제는 모든 노드의 경로파악이 필요한 문제는 아니므로 다익스트라로 푸는게 더 효율적이다.

class Main{
    public static final int INF = (int)1e9;
    //도시의 개수(Node) : N, 통로의 개수(Edge) : M, 메세지를 보내고자하는 도시(특정노드에서 다른 모든 노드들을 의미) = C
    public static int n, m, c;

    public static int[][] graph = new int[30002][30002];


    //도시 C에서 보낸 메세지를 받는 도시의 총 개수와 총 걸리는 시간
    public static int count, dist;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        c = sc.nextInt();

        for(int i = 0 ; i < 30002 ; i++)
            Arrays.fill(graph[i], INF);

        for(int i = 0 ; i <= n ; i++){
            graph[i][i] = 0;
        }

        //특정도시 X 에서 다른 특정 도시 Y로 이어지는 통로 생성, 메세지 전달 시간은 Z
        for(int i = 0 ; i < m ; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();

            graph[x][y] = z;
        }

        for(int k = 1 ; k <= n ; k++){
            for(int a = 1 ; a <= n ; a++){
                for(int b = 1 ; b <= n ; b++){
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        dist = 0;
        for(int i = 1 ; i <= n ; i++){
            if(graph[c][i] != INF && graph[c][i] != 0)
                count++;
            if(dist < graph[c][i] && graph[c][i] != 0)
                dist = graph[c][i];
        }


        System.out.println(count + " " + dist);

    }
}
