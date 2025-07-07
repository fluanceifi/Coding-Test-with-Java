import java.util.*;
import java.lang.*;
import java.io.*;

class Node{
    private int index;
    private int distance;

    public Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }

    public int getIndex(){
        return this.index;
    }

    public int getDistance(){
        return this.distance;
    }
}

class Main {
    public static final int inf = (int)1e9;
    
    //node = n , edge = m, start = start
    //node's maxium number is 100,000
    public static int n;
    public static int m;
    public static int start;

    //nodes's connection info Array
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    
    //방문 확인용 배열
    public static boolean[] visited = new boolean[100001];

    //최단 거리 테이블 만들기
    public static int[] d= new int[100001];

    public static int getSmallestNode(){
        int min = inf;
        int index = 0; //최단 거리가 가장 짧은 노드

        for(int i = 1 ; i <= n ; i++){
            if(d[i] < min && !visited[i]){
                min = d[i];
                index = i;
            }  
        }
        return index;
    }

    public static void dijkstra(int start){
        //시작 노드에 대해서 초기화
        d[start] = 0;
        visited[start] = true;
                                //2차원 배열이라서 그렇다.
        for(int j = 0 ; j < graph.get(start).size() ; j++){
            //get = [], get(start).get(j) = [start][j]  | start기준으로 d[](최단 거리 테이블)을 생성하는 것
            d[graph.get(start).get(j).getIndex()] = graph.get(start).get(j).getDistance();
        }
        //시작 노드를 제외한 전체 n - 1개의 노드에 대해 반복
        for(int i = 0 ; i < n - 1 ; i++){
            //현재 최단 거리가 가장 짧은 노드를 꺼내서 방문처리
            int now = getSmallestNode();
            visited[now] = true;

            //현재 노드와 연결된 다른 노드를 확인
            for(int j = 0 ; j < graph.get(now).size() ; j++){
                //현재 방문한 노드에서 방문가능한 노드의 비용을 순서대로 확인 후 비교하는 과정

                //해당 cost는 현재 방문 노드에서 연결된 노드의 비용과 더한 값
                int cost = d[now] + graph.get(now).get(j).getDistance();

                //여기서 비교가 이루어지고, 더 짧은 경우엔 최단 거리 테이블이 갱신됨.
                //cost와 [now][j]와 연결된 노드(index)의 거리를 비교한다고 생각하면 된다.
                if(cost < d[graph.get(now).get(j).getIndex()]){
                    d[graph.get(now).get(j).getIndex()] = cost;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //n개의 노드, m개의 엣지, start의 시작점
        n = sc.nextInt();
        m = sc.nextInt();
        start = sc.nextInt();

        //그래프 초기화 (0을 미사용하기 위한 "<= n")
        for(int i = 0 ; i <= n ; i++){
            graph.add(new ArrayList<Node>());
        }

        //모든 간선 정보 입력
        for( int i = 0 ; i < m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            //a -> b 의 비용은 c로 b는 index, c는 distance를 저장한다.
            graph.get(a).add(new Node(b, c));
        }

        //최단 거리 테이블 초기화
        Arrays.fill(d, inf);

        dijkstra(start);

        //모든 노드로 가기위한 최단 거리를 출력
        for(int i = 1 ; i <=n ; i++){
            //도달할 수 없는 경우 inf라고출력
            if(d[i] == inf) System.out.println("INF!");
            else System.out.println(d[i]);
        }
    }
}
