import java.util.*;

class Node implements Comparable<Node>{

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

    @Override
    public int compareTo(Node other){
        if(this.distance < other.distance){
            return -1;
        }
        return 1;
    }
}


public class Main {
    public static final int INF = (int)1e9;

    //노드 = n , 간선 = m, 시작 노드 = start
    private static int n;
    private static int m;
    private static int start;

    //각 노드에 연결되어 있는 노드에 대한 정보를 담은 배열
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

    //최단 거리 테이블 만들기
    public static int[] d = new int[100001];

    public static void dijkstra(int start){
        d[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int dist = node.getDistance();
            int currIdx = node.getIndex();

            //이미 처리된(!INF) || 현재보다 더 긴 거리는 제외
            if(d[currIdx] < dist) continue;

            //현재 노드와 연결된 인접한 노드들 확인
            for(int i = 0 ; i < graph.get(currIdx).size() ; i++){
                //현재 노드까지의 거리와 연결된 인접한 노드들의 거리을 합산
                int cost = d[currIdx] + graph.get(currIdx).get(i).getDistance();

                //현재 노드를 거쳐서, 다른노드로 이동하는 거리가 기존의 거리보다 더 짧으면 갱신한다.
                if(cost < d[graph.get(currIdx).get(i).getIndex()]){
                    d[graph.get(currIdx).get(i).getIndex()] = cost;
                    pq.offer(new Node(graph.get(currIdx).get(i).getIndex(), cost));
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        start = sc.nextInt();

        for(int i = 0 ; i <= n ; i++){
            graph.add(new ArrayList<Node>());
        }

        for(int i = 0 ; i < m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            graph.get(a).add(new Node(b, c));
        }

        Arrays.fill(d, INF);

        dijkstra(start);

        //모든 노드로 가기 위한 최단 거리를 출력
        for(int i = 1 ; i <= n ; i++){
            //도달할 수 없는경우는 INF출력
            if(d[i] == INF){
                System.out.println("INF");
            }
            else{
                System.out.println(d[i]);
            }
        }
    }
}
