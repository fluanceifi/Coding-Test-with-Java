import java.util.*;


class Node implements Comparable<Node>{
    private int y;
    private int x;
    private int dist;

    public Node(int y, int x, int dist){
        this.y = y;
        this.x = x;
        this.dist = dist;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getDist(){
        return dist;
    }

    @Override
    public int compareTo(Node other){
        if(this.dist < other.dist)
            return -1;
        return 1;
    }
    
}

class Main {
    public static int INF = (int)1e9;
    public static int n, t;
    public static int[][] graph = new int[125][125];
    public static int[][] d = new int[125][125];
    public static int[] dx = {0, 1, 0, -1}; //상 우 하 좌
    public static int[] dy = {-1, 0, 1, 0};
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();

        //t번 반복
        for(int tc = 0 ; tc < t ; tc++){
            n = sc.nextInt();
            
            //그래프 입력
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    graph[i][j] = sc.nextInt();
                }
            }
        
            //최단 거리 테이블은 무한으로 초기화
            for(int i = 0 ; i < n ; i++)
                Arrays.fill(d[i], INF);

            //시작 위치 = (0,0) , 목표 위치 = (n-1, n-1)
            int y = 0, x = 0; 
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(x, y, graph[y][x]));
            d[y][x] = graph[y][x];

            while(!pq.isEmpty()){
                Node now = pq.poll();
                y = now.getY();
                x = now.getX();
                int dist = now.getDist();

                //현재 노드가 이미 처리되었거나, 이전 처리과정이 더 짧은 거리라면 무시
                if(d[y][x] < dist) continue;

                //현재 노드와 연결된 다른 인접한 노드들을 확인
                for(int i = 0 ; i < 4 ; i++){
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    
                    if(ny < 0 || ny >= n || nx < 0 || nx >= n) continue;

                    int cost = dist + graph[ny][nx];

                    //현재 cost가 d[ny][nx]보다 작은비용이라면 갱신되고 pq에 추가된다.
                    if(cost < d[ny][nx]){
                        d[ny][nx] = cost;
                        pq.offer(new Node(ny, nx, cost));
                    }
                        
                }
            }
            System.out.println(d[n-1][n-1]);

        }
    
    
    }
}
