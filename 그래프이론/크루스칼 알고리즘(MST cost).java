import java.util.*;

class Edge implements Comparable<Edge>{
    private int nodeA;
    private int nodeB;
    private int distance;

    public Edge(int a, int b, int dist){
        this.nodeA = a;
        this.nodeB = b;
        this.distance = dist;
    }

    public int getA(){
        return this.nodeA;
    }

    public int getB(){
        return this.nodeB;
    }

    public int getDist(){
        return this.distance;
    }

    @Override
    public int compareTo(Edge other){
        if(this.distance < other.distance){
            return -1;
        }
        return 1;
    }
}

public class Main{

    public static int v, e;
    public static int[] parent = new int[100001];

    public static ArrayList<Edge> edges = new ArrayList<>();
    public static int result = 0;

    public static int findParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

    public static void unionParent(int a, int b){
        a = findParent(a);
        b = findParent(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        for(int i = 1 ; i <= v ; i++) parent[i] = i;

        for(int i = 0 ; i < e ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            edges.add(new Edge(a, b, cost));
        }

        //최소 비용으로 정렬 [@Override compareTo()]
        Collections.sort(edges);

        //하나의 간선에 연결된 양쪽의 노드의 부모를 비교하여 부모가 같으면 싸이클(PASS), 같지않으면 합치기(Union)
        for(int i = 0 ; i < edges.size() ; i++){
            int node1 = edges.get(i).getA();
            int node2 = edges.get(i).getB();
            int dist = edges.get(i).getDist();

            //pass
            if(findParent(node1) == findParent(node2)) continue;

            //union
            unionParent(node1, node2);
            result += dist;
        }

        System.out.println("Minimum spanning tree cost is : " + result);
    }
}
