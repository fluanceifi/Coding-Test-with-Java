import java.util.*;




class Main {
    public static int n, m;
    public static int INF = (int)1e9;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static int dist[] = new int[20001];

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();

        dist[start] = 0;
        q.offer(start);

        while(!q.isEmpty()){
            int now = q.poll();

            for(int next : graph.get(now)){
                if(dist[next] == INF){
                    dist[next] = dist[now] + 1;
                    q.offer(next);
                }
            }
            
        
        }
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();
        
        //거리가 1일 경우 다익스트라보다 bfs가 더 효율적이다.
        for(int i = 0 ; i <= n ; i++){
            graph.add(new ArrayList<Integer>());
        }
        
        
        for(int i = 0 ; i < m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            //양방향 고려
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        Arrays.fill(dist, INF);
        
        bfs(1);

        int hide = 0;
        int shortest = 0;
        int count = 0;
        
        //1번은 시작점이니 제외
        for(int i = 2 ; i <= n ; i++){
            //가장 먼 최단거리가 존재 시
            if(shortest < dist[i]){
                //갱신
                shortest = dist[i];
                //그와중 앞 숫자를 저장
                hide = i;
                //개수 초기화
                count = 1;
            }
            //동일 최단거리가 존재시
            else if(dist[i] == shortest){
                //개수 증가
                count++;
            }
            
        }

        System.out.println(hide+ " " + shortest + " " + count);
        
    }
}
