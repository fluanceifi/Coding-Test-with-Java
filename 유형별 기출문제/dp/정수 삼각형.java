import java.util.*;

class Main{

    public static int n;
    public static int[][] dp = new int[500][500];

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j <= i ; j++){
                dp[i][j] = sc.nextInt();
            }
        }

        for(int i = 1 ; i < n ; i++){
            for(int j = 0 ; j <= i ; j++){
                int right, left;

                if(j == 0) left = 0; //맨 왼쪽이면 막혀서 못감
                else left = dp[i-1][j-1]; //가능하면 계산

                if(i == j) right = 0; //맨오른쪽이면 막혀서 못감
                else right = dp[i-1][j]; //가능하면 계산

                dp[i][j] = dp[i][j] + Math.max(left, right);
            }
        }
        int result = 0;

        for(int i = 0 ; i < n ; i++){
            result = Math.max(result, dp[n-1][i]);
        }

        System.out.println(result);

    }
}
