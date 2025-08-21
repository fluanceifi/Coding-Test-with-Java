import java.util.*;

class Virus implements Comparable<Virus>{
    private int idx;
    private int s;
    private int y;
    private int x;

    public Virus(int i, int s, int y, int x){
        this.idx = i;
        this.s = s;
        this.y = y;
        this.x = x;
    }

    public int getIdx(){
        return idx;
    }

    public int getSec(){
        return s;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public int compareTo(Virus other){
        if(this.idx < other.idx){
            return -1;
        }
        return 1;
    }

}



class Main{
    public static int n, k;
    public static int[][] arr = new int[201][201];
    public static ArrayList<Virus> virues = new ArrayList<Virus>();

    public static int[] dy = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    public static int[] dx = {0, 1, 0, -1};

    public static void main(String[] ars){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        for(int i = 0 ; i  < n ; i++){
            for(int j = 0 ; j < n ; j++){
                arr[i][j] = sc.nextInt();
                if(arr[i][j] != 0)
                    virues.add(new Virus(arr[i][j], 0, i, j));
            }
        }
        Collections.sort(virues);

        Queue<Virus> q = new LinkedList<>();
        for(Virus v : virues){
            q.offer(v);
        }

        int s, x, y;
        s = sc.nextInt();
        y = sc.nextInt();
        x = sc.nextInt();


        //bfs
        while(!q.isEmpty()){
            Virus virus = q.poll();

            if(virus.getSec() >= s) break;

            for(int i = 0 ; i < 4 ; i++){
                int ny = virus.getY() + dy[i];
                int nx = virus.getX() + dx[i];

                if(ny < n && ny >= 0 && nx < n && nx >= 0){
                    if(arr[ny][nx] == 0) {
                        arr[ny][nx] = virus.getIdx();
                        q.offer(new Virus(arr[ny][nx], virus.getSec() + 1, ny, nx));
                    }
                }
            }
        }

        System.out.println(arr[y-1][x-1]);
    }
}
