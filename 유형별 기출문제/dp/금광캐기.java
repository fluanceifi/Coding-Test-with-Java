import java.util.*;

class Main {

    public static int n, m, t;
    public static int[][] arr = new int[20][20];
    public static int[][] dp = new int[20][20];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        t = sc.nextInt();


        for (int tc = 0; tc < t; tc++) {
            n = sc.nextInt();
            m = sc.nextInt();

            //배열 초기화 및 dp 배열에 복사
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = sc.nextInt();
                    dp[i][j] = arr[i][j];
                }
            }

            //금광 캐기는 i행에 있는 모든 j열 요소를 모두 계산해 나아간다.
            for(int j = 1 ; j < m ; j++){
                for(int i = 0 ; i < n ; i++){
                    //금광캐기는 현재 2차원 인덱스에서 오른쪽 위, 오른쪽, 오른쪽 아래로 나아간다.
                    //DP는 점화식을 통해 현재 칸(i, j)에서 나아가려면 다음 열의 위치를 변수로 선언한다.
                    int leftDown, left, leftUp; //(?, j+1)가 위치한 곳의 값

                    //오른쪽 위
                    if(i == 0) leftUp = 0; //배열의 공간을 넘어가지 않도록 한다.
                    else leftUp = dp[i-1][j-1];

                    //오른쪽 아래
                    if(i == n-1) leftDown = 0;
                    else leftDown = dp[i+1][j-1];

                    //오른쪽
                    left = dp[i][j-1];

                    //가장 큰 값을 선택하고 값을 더해 중간 과정을 기록한다.
                    dp[i][j] = dp[i][j] + Math.max(left, Math.max(leftUp, leftDown));
                }
            }

            int result = 0;

            for(int i = 0 ; i < n ; i++){
                result = Math.max(result, dp[i][m-1]); //마지막 열의 값들을 모두 계산하여 가장 큰 값을 구한다.
            }

            System.out.println(result);
        }
    }
}
