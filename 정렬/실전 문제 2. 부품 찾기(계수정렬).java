import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    // public static Integer[] stock = new Integer[1000001]; Integer은 wrapper class라서 콜랙션 자료형이라 null로 초기화임.
    public static int[] stock = new int[1000001];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        for(int i = 0 ; i < n ; i++) {
            int temp = sc.nextInt();
            stock[temp]++;
        }
        

        int m = sc.nextInt();
        Integer[] req = new Integer[m];
        for(int i = 0 ; i < m ; i++) req[i] = sc.nextInt();

        //for(int i = 0 ; i < m ; i++){
        //    if(stock[req[i]] != 0) System.out.println("yes");
        //    else System.out.println("no");
        //}
        
        for(int index : req){
            if(stock[index] != 0) System.out.println("yes");
            else System.out.println("no");
        }
        
    }
}
