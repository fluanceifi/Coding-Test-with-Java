import java.util.*;
import java.lang.*;
import java.io.*;

//Bottom-up 방식은 작은 문제부터 차례대로 해결해 나가며, 그 해결 결과를 기반으로 더 큰 문제를 푸는 방식.
//따라서 반복문(for문)을 사용하여 작은 문제부터 큰 문제로 점진적으로 접근 
        
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int x = sc.nextInt();
        int[] arr = new int[x+1];

        //현재 수를 1로 만들 때, [이전 수를 구하는 방식에서 1을 빼는 경우]와 [2, 3, 5로 나누어 떨어질 경우]의 몫에 대한 연산 횟수를 비교하여 최소값을 선택한다.
        for(int i = 2 ; i < x + 1 ; i++){
            //이전 수를 구하는 방식에서 1을 빼는 경우
            arr[i] = arr[i-1] + 1; // i가 2일 때 2-1 = 1 , arr[1] == 0, 0+1 = 1 arr[2] = 1 => 즉, 2라는 수는 연산횟수가 1만필요함. 
            //현재 수에서 2로 나누어 떨어지는 경우
            if(i % 2 == 0)
                arr[i] = Math.min(arr[i], arr[i/2]+1);
            //현재 수에서 3로 나누어 떨어지는 경우
            if(i % 3 == 0)
                arr[i] = Math.min(arr[i], arr[i/3]+1);
            //현재 수에서 5로 나누어 떨어지는 경우
            if(i % 5 == 0)
                arr[i] = Math.min(arr[i], arr[i/5]+1);
        }

        System.out.println(arr[x]);
    }
}
