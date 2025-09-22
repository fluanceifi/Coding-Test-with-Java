import java.util.*;


/**
 * 삭제는 [i-1][j]이라고하면 i부터 문자가 다르니깐 j번째 문자를 삭제하는 경우의 수 (+1)
 *
 * 삽입은 [i][j-1]이라고하면 i까지 j-1과 같으니 j번째 문자 추가하는 경우의 수 (+1)
 *
 * 일치는 dp[i][j] = [i-1][j-1]이라고하면 i-1부터 j-1까지 같으니 i, j도 같으면 그냥 넘어가는 경우의 수 (+0)
 *
 * 대체는 [i-1][j-1]이라고하면 i-1부터 j-1까지 같지만 i,j는 다르니 바꿔주는 경우의수 (+1)
 */

class Main{
    public static String A, B;

    public static int editDistance(String A, String B){
        int n = A.length();
        int m = B.length();
        
        int[][] dp = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) dp[i][0] = i;
        for(int j = 1 ; j <= m ; j++) dp[0][j] = j;
        
        for(int i = 1; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(A.charAt(i-1) == B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                  dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }

        return dp[n][m];
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        A = sc.nextLine();
        B = sc.nextLine();
        
        System.out.println(editDistance(A,B));

    }

}
