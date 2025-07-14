import java.util.*;
import java.lang.*;

class Node implements Comparable<Node> {
    private int idx;
    private int dist;

    public Node(int idx, int dist){
        this.idx = idx;
        this.dist = dist;
    }

    public int getIdx(){
        return this.idx;
    }

    public int getDist(){
        return this.dist;
    }

    //짧은게 높은 우선 순위를 가지도록 한다.
    @Override
    public int compareTo(Node other){
        if(this.dist < other.dist){
            return -1;
        }
        return 1;
    }
}


class Main{
    public static final int INF = (int)1e9;
    //도시의 개수(Node) : N, 통로의 개수(Edge) : M, 메세지를 보내고자하는 도시(특정노드에서 다른 모든 노드들을 의미) = C
    public static int n, m, c;

    public static ArrayList<ArrayList<Node>> graph =  new ArrayList<ArrayList<Node>>();
    public static int[] d = new int[200002];

    //도시 C에서 보낸 메세지를 받는 도시의 총 개수와 총 걸리는 시간
    public static int count, distance;

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start, 0));
        d[start]= 0;
        while(!pq.isEmpty()){
            //최단거리 노드 꺼내오기
            Node node = pq.poll();

            int dist = node.getDist();
            int now = node.getIdx();

            if(d[now] < dist) continue;

            for(int i = 0; i < graph.get(now).size() ; i++){
                //현재 노드와 이어져 있는 다른 노드를 탐색하는 과정
                int cost = d[now] + graph.get(now).get(i).getDist();
                //만약 해당 노드가 존재한다면 INF보단 작을거임 (now와 이어진 또 다른 노드의 거리를 합친거니깐)

                //여기서 get(i)는 단순히 순서를 의미함 실제 인덱스는 getIdx()로 가져와야됨.
                if(cost < d[graph.get(now).get(i).getIdx()]){
                    d[graph.get(now).get(i).getIdx()] = cost;
                    pq.offer(new Node(graph.get(now).get(i).getIdx(), cost));
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        c = sc.nextInt(); //start

        for(int i = 0 ; i <= n ; i++){
            graph.add(new ArrayList<Node>());
        }


        //특정도시 X 에서 다른 특정 도시 Y로 이어지는 통로 생성, 메세지 전달 시간은 Z
        for(int i = 0 ; i < m ; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();

            graph.get(x).add(new Node(y, z));
        }
        //거리 초기화
        Arrays.fill(d, INF);

        dijkstra(c);

        count = 0;
        distance = 0;

        for(int i = 2 ; i <= n ; i++) {
            if(d[i] != INF && i != c)
                count++;
                distance = Math.max(distance, d[i]);
        }

        System.out.println(count + " " + distance);

    }
}
