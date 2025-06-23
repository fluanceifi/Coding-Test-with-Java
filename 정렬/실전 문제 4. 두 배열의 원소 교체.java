import java.util.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int[] a = new int[n];
        Integer[] b = new Integer[n];

        int k = sc.nextInt();

        for(int i = 0 ; i < n ; i++){
            a[i] = sc.nextInt();
        }

        for(int i = 0 ; i < n ; i++){
            b[i] = sc.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b, Collections.reverseOrder());
        
        
        for(int i = 0 ; i < k ; i++){
            if(a[i] < b[i]) a[i] = b[i];
        }

        int result = 0;
        for(int num : a) result += num;

        System.out.println(result);
    }
}
