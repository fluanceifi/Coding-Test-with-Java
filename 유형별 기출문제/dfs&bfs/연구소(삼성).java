import java.util.*;

class Main {
    public static int n, m, cnt, result;
    public static int[][] arr = new int[8][8];
    public static int[][] temp = new int[8][8];

    //상 좌 하 우
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};

    public static void virus(int y, int x){
        for(int i = 0 ; i < 4 ; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny >= 0 && nx >= 0 && ny < n && nx < m){
                if(temp[ny][nx] == 0){ temp[ny][nx] = 2;
                virus(ny, nx);
                }
            }

        }
    }

    public static int getCount(){
        int score = 0;

        for(int i = 0; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(temp[i][j] == 0) score++;
            }
        }

        return score;
    }

    public static void dfs(int cnt) {
        //원본에서 모든 벽을 다 세웠다면,,,
        if (cnt == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = arr[i][j]; //원본을 시뮬레이션용 배열에 복사
                }
            }

            //각각의 바이러스에서 전파 실행
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (temp[i][j] == 2) virus(i, j);
                }
            }
            result = Math.max(result, getCount());
            return;
        }
        //아직 벽을 다 못 세웠다면,,,
        else{
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j < m ; j++){
                    if(arr[i][j] == 0){
                        arr[i][j] = 1; //벽을 세운다.
                        cnt++; //벽 개수를 업데이트한다.
                        dfs(cnt); //재귀를 한다.
                        arr[i][j] = 0; //재귀가 끝났으면 다음 위치를 위해 벽을 뽑는다.
                        cnt--; //벽 개수를 업데이트한다.
                    }
                }
            }
        }
    }






    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        //start input
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        //end input

        //start algorithm
        cnt = 0;
        dfs(cnt);
        System.out.println(result);
        //end algorithm
    }
}
