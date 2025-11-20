import java.util.*;


class Main {
    public static int n, result;
    public static int[] soldiers;
    public static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();

        soldiers = new int[n];
        dp = new int[n];

        for(int i = 0 ; i < n ; i++){
            soldiers[i]=sc.nextInt();
            dp[i]=1;
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < i ; j++){
                //만약 나(i)보다 전투력이 더 높은 상대(j)가 있다면, 상대방 뒤에 배치가 된다.
                //그렇다면 내 기준으로 줄(dp[i])은 상대의 줄 길이(dp[j]) + 1 이 된다.
                //하지만 만약 내 기존 줄보다 더 짧다면 포기할 것이다. (병사 수 가 최대 수가 되고 싶기 때문이다.)
                if(soldiers[j] > soldiers[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //최대값 갱ㅇ신
            result = Math.max(result, dp[i]);
        }

        System.out.println(n - result);
    }
}
