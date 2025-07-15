import java.util.*;

class Main{

    public static int v, e;
    public static int[] parent = new int[10001];

    //루트 찾기
    /*
    public static int findParent(int x){
        if(parent[x] == x) return x;
        return findParent(parent[x]);
    }
     */
    
    //경로 압축 기법
    /*
     1 <- 2 <- 3 <- 4 <- 5와 같은 선형적인 구조에서 findParent(5)를 호출했을 때,
     경로상의 모든 노드(2, 3, 4, 5)가 최종 루트인 1을 직접 가리키도록 부모 정보가 업데이트된다.
     이러면 부모테이블과 집합을 출력하는 것에 대한 차이가 사라진다.
     */
    public static int findParent(int x){
        if(parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

    //집합 합치기
    public static void unionParent(int a, int b){
        a = findParent(a);
        b = findParent(b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        for(int i = 1 ; i <= v ; i++){
            parent[i] = i; //부모 초기화
        }

        //합치기
        for(int i = 0 ; i < e ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            unionParent(a, b);
        }

        System.out.println("집합 찾기");
        for(int i = 1 ; i <= v ; i++ ){
            System.out.print(findParent(i) + " ");
        }
        System.out.println();

        System.out.println("부모 테이블");
        for(int i = 1 ; i <= v ; i++ ){
            System.out.print(parent[i] + " ");
        }
        System.out.println();




    }
}
