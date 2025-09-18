import java.util.*;

class Main{

    public static int n, maxValue;

    public static int[] t = new int[15];
    public static int[] p = new int[15];
    public static int[] dp = new int[16];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            t[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }
        //dp는 시간을 뒤집어 퇴사 전날부터 계산한다.
        for(int i = n - 1 ; i >= 0 ; i--){
            int time = t[i] + i; //시간은 현재 i일차 작업의 소요 시간 + i 일차

            //만약 퇴사 전날까지 가능한 일이라면,
            if(time <= n){
                dp[i] = Math.max(maxValue, p[i] + dp[time]); //현재 최대 값, i일차 보수 + i일차 일이 끝났을 때 이어서 할 수 있는 작업의 최대보수를 비교 (점화식)
                maxValue = dp[i]; //최대값 갱신
            }
            //불가능한 일정이라면
            else{
                dp[i] = maxValue; //maxValue를 저장하고 넘어간다.
            }
        }
        //dp로 보면 첫째 날이 결과값이다.
        System.out.println(dp[0]);


    }
}
