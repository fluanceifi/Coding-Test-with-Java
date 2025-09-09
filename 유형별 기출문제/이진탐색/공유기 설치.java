import java.util.*;

class Main{
    public static int n, c, result;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        c = sc.nextInt();

        //수에 대한 이진탐색이라서 ArrayList로 설계한다.
        ArrayList<Integer> arr = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            int num = sc.nextInt();
            arr.add(num);
        }

        Collections.sort(arr);

        int start = 1; //최소 거리
        int end = arr.get(n-1) - arr.get(0); //최대 거리

        while(start <= end){
            int mid = (start + end) / 2;
            int value = arr.get(0);
            int cnt = 1;

            for(int i = 1 ; i < n ; i++){
                if(arr.get(i) >= value + mid){
                    value = arr.get(i);
                    cnt++;
                }
            }
            //여유롭다면
            if(cnt >= c){
                start = mid + 1; //우측으로 옮겨서 더 진행해보자
                result = mid; // 중간 세이브
            }
            //부족하다면
            else{
                end = mid - 1; //왼쪽으로 옮겨서 더 좁은 간격으로 공유기 설치 개수 마저 채우기
            }
        }
    
        System.out.println(result);

    }
}
