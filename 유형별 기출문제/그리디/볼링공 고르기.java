import java.util.*;

class Main{
    public static int n, m, result;
    public static int[] arr = new int[11];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); m = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            int x = sc.nextInt();
            arr[x]++;
        }

        for(int i = 1 ; i <= m ; i++){
            //전체 공의 개수 중 자신의 무게는 제외하고
            n -= arr[i];
            //무게가 i인 공의 개수 * 다른 공의 개수 = i 무게에서 나올 수 있는 조합
            //(1,2) 조합을 했다면 (2,1)은 이제 선택안됨.
            result += arr[i] * n;
        }

    }
}
