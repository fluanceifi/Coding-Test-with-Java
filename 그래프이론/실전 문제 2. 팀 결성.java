import java.util.*;

class Main{

    //0~n번을 가진 학생, m번 연산 진행
    public static int n , m;

    //m의 최대 값이 100000이라서 크기를 결정함.
    public static int[] parent = new int[100001];

    //같은 팀인지 확인하기
    public static int findParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }
    
    //팀 합치기
    public static void unionParent(int a, int b){
        a = findParent(a);
        b = findParent(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();


        for(int i = 0 ; i <= n ; i++){
            parent[i] = i;
        }

        for(int i = 0 ; i < m ; i++){
            int type = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            //type == 0 : unionParent , type == 1 : findParent
            if(type == 0){
                unionParent(a, b);
            }
            else if(type == 1){
                if(findParent(a) == findParent(b)){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }
            }
        }
    }
}
