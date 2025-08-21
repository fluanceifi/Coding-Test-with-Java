import java.util.*;

class Main{
    public static int n, m , k, x;

    public static int[] dist = new int[300001];

    public static ArrayList<ArrayList<Integer>> city = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        x = sc.nextInt();

        for(int i = 0 ; i <= n ; i++){
            city.add(new ArrayList<Integer>());
            dist[i] = -1;
        }

        for(int i = 0 ; i < m ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            city.get(a).add(b); // a to b 경로저장
        }
        //START POINT
        dist[x] = 0;
        //BFS
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(x);

        while(!q.isEmpty()){
            int now = q.poll();

            for(int i = 0 ; i < city.get(now).size() ; i++ ){
                int next = city.get(now).get(i);
                if(dist[next] == -1){
                    dist[next] = dist[now] + 1;
                    q.offer(next);
                }
            }
        }

        boolean check = false;
        for(int i = 0 ; i <= n ; i++){
            if(dist[i] == k) {
                System.out.println(i);
                check = true;
            }
        }

        if(!check)
            System.out.println(-1);
    }
}
