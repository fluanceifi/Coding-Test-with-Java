import java.util.*;

class Main{
    public static int n;
    public static int[] arr;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];

        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int result = arr[(n-1)/ 2];

        System.out.println(result);
    }
}
