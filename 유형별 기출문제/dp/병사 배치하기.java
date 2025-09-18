import java.util.*;


class Main{

    public static int n;
    public static ArrayList<Integer> v = new ArrayList<>();
    public static int[] dp = new int [2000];

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            v.add(sc.nextInt());
            dp[i] = 1; //DP는 최대 병사의 수이다. (각 병사 한 명만 있어도 길이는 1이므로 1로 초기화)
        }

        // '가장 긴 감소하는 부분 수열(LDS)'을 찾기 위해 배열을 뒤집어, '가장 긴 증가하는 부분 수열(LIS)' 문제로 변환한다.
        Collections.reverse(v);


        // i번째 인덱스에서 끝나는 최대 길이를 찾는 문제 (LIS)
        for(int i = 1 ; i < n ; i ++){
            for(int j = 0 ; j < i ; j++){
                if(v.get(j) < v.get(i)){
                    dp[i] = Math.max(dp[i], dp[j] + 1); //기존 dp[i]와 (j로 끝나는 LIS 길이 + 1) 중 더 긴 값을 선택
                }
            }
        }

        int maxValue = 0;
        for(int i = 0 ; i < n ; i++){
            maxValue = Math.max(maxValue, dp[i]); //dp에서 최대 수를 찾는다.
        }
        System.out.println(n - maxValue); //전체 병사 수 - 최대 병사 수 = 열외 인원

    }
}
