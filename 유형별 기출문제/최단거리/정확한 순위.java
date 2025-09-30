import java.util.*;

class Main {
    public static int n, m;
    public static int INF = (int)1e9;
    public static int result;
    public static int[][] graph = new int[501][501];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <=n ; j++){
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }

        for(int i = 0 ; i < m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = 1;
        }

        //플로이드 워셜로 거쳐서 갈 수 있는 경우 이어준다.
        for(int k = 1 ; k <= n ; k++){
            for(int i = 1 ; i <= n ; i++){
                for(int j = 1 ; j <= n ; j++){
                    graph[i][j] =
                            Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        for(int i = 1 ; i <= n ; i++){
            int cnt = 0;
            for(int j = 1 ; j <= n ; j++){
                //비교가 가능하다면
                if(graph[i][j] != INF || graph[j][i] != INF){
                    cnt++;
                }
            }
            //비교 가능한 횟수가 전체 학생 수와 같다면 그 학생의 정확한 성적 순위를 알 수 있다.
            if(cnt == n) result++;
        }

        System.out.println(result);
    }
}
