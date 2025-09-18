import java.util.*;

class Main{
    public static int n;
    public static int[] dp = new int[1000];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n=sc.nextInt();
        dp[0] = 1;
        int i2=0,i3=0,i5=0,next2=2,next3=3,next5=5;
        for(int i = 1 ; i < n ; i++){
            dp[i] = Math.min(next2, Math.min(next3,next5));
            if(next2 == dp[i]){
                i2++;
                next2 = dp[i2] * 2;
            }
            if(next3 == dp[i]){
                i3++;
                next3 = dp[i3] * 3;
            }
            if(next5 == dp[i]){
                i5++;
                next5 = dp[i5] * 5;
            }
        }

        System.out.println(dp[n-1]);
    }
}
