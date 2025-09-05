import java.util.*;

class Main{
    public static int n, x;
    public static int[] arr;

    public static int lowerBound(int key, int start, int end){
        while(start < end){
            int mid = (start + end) / 2;
            if(arr[mid] >= key) end = mid; //맨 왼쪽에 있는 key 값을 찾아야하니 >=로 탐색범위를 왼쪽으로 줄여나간다.
            else start = mid + 1;
        }
        return end;
    }

    public static int upperBound(int key, int start, int end){
        while(start < end){
            int mid = (start + end) / 2;
            if(arr[mid] > key) end = mid; //맨 오른쪽에 있는 key 값을 찾아야하니 >로 탐색범위를 오른쪽으로 줄여나간다.
            else start = mid + 1;
        }
        return end;
    }

    public static int countX(){
        int leftIdx = lowerBound(x, 0, arr.length);
        int rightIdx = upperBound(x, 0, arr.length);

        return rightIdx - leftIdx;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        x = sc.nextInt();

        arr = new int[n];

        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        int result = countX();

        System.out.println(result);

    }
}
