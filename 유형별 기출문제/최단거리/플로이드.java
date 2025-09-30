import java.util.*;

class Node {
    private int a;
    private int b;
    private int c;

    public Node(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA(){
        return this.a;
    }
    public int getB(){
        return this.b;
    }
    public int getC(){
        return this.c;
    }
}

class Main {
    public static int n, m;
    public static int[][] graph;
    public static ArrayList<Node> node = new ArrayList<>();
    public static int MAX = (int)1e9;

    public static void floyd(){
        //k라는 노드를 거치고 가는걸 고려했을 때, k가 맨 앞에와야지 모든 루트에 대해 k에 대한 계산이 이뤄질수 있다.
        for(int k = 1 ; k <= n ; k++){
            for(int i = 1 ; i <= n ; i++){
                for(int j = 1 ; j <= n ; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        //간선 입력
        for(int i = 0 ; i < m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            node.add(new Node(a, b, c));
        }

        //그래프 초기화
        graph = new int[n+1][n+1];
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1; j <= n ; j++){
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = MAX;
            }
        }

        //그래프에 간선 추가
        for(int i = 0 ; i < m ; i++){
            int a = node.get(i).getA();
            int b = node.get(i).getB();
            int c = node.get(i).getC();
            //시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
            graph[a][b] = Math.min(graph[a][b], c);
        }

        floyd();

        for(int i = 1; i <= n ; i++){
            for(int j = 1 ; j<= n ; j++){
                if(graph[i][j] == MAX) System.out.print(0 + " ");
                else System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

    }
}
