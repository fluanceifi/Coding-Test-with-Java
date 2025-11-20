import java.util.*;

public class Main{
    public static String string1;
    public static String string2;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        string1 = sc.nextLine();
        string2 = sc.nextLine();
        int n = string1.length();
        int m = string2.length();

        int[][] dp = new int[n+1][m+1];
        for(int i = 0 ; i <= n ; i++){
            dp[i][0] = i;
        }
        
        for(int j = 0 ; j <= m ; j++){
            dp[0][j] = j;
        }
        
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(string1.charAt(i-1) == string2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{//삭제, 삽입, 교체
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }

    
        System.out.println(dp[n][m]);

    }
}
