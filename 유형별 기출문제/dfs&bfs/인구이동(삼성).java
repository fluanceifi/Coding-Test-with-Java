import java.util.*;

class Position {
    private int y;
    private int x;

    public Position(int y, int x){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}
class Main{
    public static int n, l, r;
    public static int totalCount = 0;

    public static int[][] A = new int [51][51];
    public static int[][] unions = new int [51][51];

    public static int[] dy = {-1, 0, 1, 0}; //상 우 하 좌
    public static int[] dx = {0, 1, 0, -1};

    public static void process(int y, int x, int idx){
        //(y, x)의 주변에 존재하는 나라를 담기 위한 ArrayList
        ArrayList<Position> united = new ArrayList<>();
        //현위치를 담아두고
        united.add(new Position(y, x));

        //BFS로 진행함
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(y, x));
        unions[y][x] = idx; //연합 번호 할당
        int sum = A[y][x];
        int cnt = 1; //현재 연합의 국가 수
        //큐가 빌 때 까지 반복(bfs)
        while(!q.isEmpty()){
            Position pos = q.poll();
            y = pos.getY(); //current
            x = pos.getX();

            for(int i = 0 ; i < 4 ; i++) {

                int ny = y + dy[i];
                int nx = x + dx[i];

                if (0 <= ny && n > ny && 0 <= nx && n > nx && unions[ny][nx] == -1) {
                    int gap = Math.abs(A[y][x] - A[ny][nx]);
                    if (gap >= l && gap <= r) {
                        q.offer(new Position(ny, nx));
                        unions[ny][nx] = idx; //연합 번호 부여
                        sum += A[ny][nx]; //인구수 합산
                        cnt++; //연합의 국가 수 증가
                        united.add(new Position(ny, nx));
                    }
                }

            }
        }
        //인구분배
        for(int i = 0 ; i < united.size() ; i++){
            y = united.get(i).getY();
            x = united.get(i).getX();
            A[y][x] = sum / cnt;
        }

    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                A[i][j] = sc.nextInt();
            }
        }

        while(true){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0; j < n ; j++){
                    unions[i][j] = -1;
                }
            }

            int idx = 0;//연합 고유의 번호
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < n ; j++){
                    if(unions[i][j] == -1) {
                        process(i, j, idx);
                        idx++;
                    }
                }
            }
            if(idx == n*n) break; //모든 국가가 다 진행되었다면 멈춘다.
            totalCount += 1;
        }

        System.out.println(totalCount);



    }
}
