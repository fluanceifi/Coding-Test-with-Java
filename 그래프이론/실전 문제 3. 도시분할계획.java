import java.util.*;

class Edge implements Comparable<Edge>{
    private int a;
    private int b;
    private int dist;

    public Edge(int a, int b, int dist){
        this.a = a;
        this.b = b;
        this.dist = dist;
    }

    public int getA(){
        return this.a;
    }
    public int getB(){
        return this.b;
    }

    public int getDist(){
        return this.dist;
    }

    @Override
    public int compareTo(Edge other){
        if(this.dist < other.dist){
            return -1;
        }
        return 1;
    }
}

class Main{

    public static int v, e;

    public static ArrayList<Edge> edges = new ArrayList<Edge>();
    public static int[] parent = new int[100001];

    public static int result = 0;
    public static int last = 0;

    public static int findParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

    public static void unionParent(int node1, int node2){
        node1 = findParent(node1);
        node2 = findParent(node2);

        if(node1 < node2) parent[node2] = node1;
        else parent[node1] = node2;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        for(int i = 1 ; i <= v ; i++){
            parent[i] = i;
        }

        for(int i = 0 ; i < e ; i++){
            int a = sc.nextInt();
            int b= sc.nextInt();
            int dist = sc.nextInt();

            //[node: a->b | cost: dist]
            edges.add(new Edge(a, b, dist));
        }

        Collections.sort(edges);


        //Q. i < edges.size() - 1 하면 안될까?
        //A. 비용이 가장 높은 간선이 Cycle을 발생시킨다면 이전 간선이 가장 높은 비용을 가지기 때문이다. (MST 특성을 생각)
        for(int i = 0 ; i < edges.size() ; i++){
            int node1 = edges.get(i).getA();
            int node2 = edges.get(i).getB();
            int dist = edges.get(i).getDist();

            if(findParent(node1)==findParent(node2))
                continue;
            else{
                unionParent(node1, node2);
                result += dist;
                last = dist;
            }
        }

        System.out.println(result - last);
    }
}
