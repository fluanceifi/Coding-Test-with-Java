import java.util.*;

class Main{
    public static int n;
    public static int[] guild;

    //그룹원, 그룹수
    public static int count = 0, result = 0;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        
        guild = new int[n]; //100000개까지 가질 수 있겠지만, 일단 sort를하게되면 나머지 빈공간도 sort범위에 포함되기 때문에 이런식으로 초기화해야 됨.
        
        //각 길드원의 공포도를 입력받는다.
        for(int i = 0 ; i < n ; i++){
            guild[i] = sc.nextInt();
        }

        //greedy Method를 위해 오른차순으로 정렬한다.
        Arrays.sort(guild);
        for(int i = 0 ; i < n ; i++){
            count++;
            if(count >= guild[i]){
                result++;
                count = 0;
            }
        }

        System.out.println(result);
    }
}
