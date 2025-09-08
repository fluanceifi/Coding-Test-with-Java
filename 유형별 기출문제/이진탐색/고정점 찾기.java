import java.util.*;

class Main{
    public static int n;
    public static int[] arr;

    public static int binSearch(int start, int end){
        while(start <= end){ //start와 end가 같아질 때 마지막 원소 1개만 남는다.
            int mid = (start + end) / 2;

            if(arr[mid] < mid){
                start = mid + 1;
            }
            else if(arr[mid] > mid){
                end = mid - 1;
            }
            else{
                return mid; //같은걸 찾았다면 mid 반환
            }
        }
        return -1; //못 찾으면 -1
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];

        for(int i = 0 ; i < n ; i++){
            arr[i]= sc.nextInt();
        }

        System.out.println(binSearch(0, n-1));
    }
}
