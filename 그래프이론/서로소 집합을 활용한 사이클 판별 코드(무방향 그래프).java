import java.util.*;

class Main{
    //노드 v, 간선 e
    //노드의 개수는 최대 100,000개
    public static int v, e;
    public static int[] parent = new int[100001];


    //특정 원소가 속한 집합 찾기
    public static int findParent(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }

    //두 원소가 속한 집합을 합치기
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

        //부모 테이블상에서, 부모를 자기 자신으로 초기화
        for(int i = 1 ; i <= v ; i++){
            parent[i] = i;
        }

        //사이클 여부 확인 용 변수
        boolean cycle = false;

        for(int i = 0 ; i < e ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            //이미 같은 집합내에 있는 노드끼리 연결 시도 할 경우
            if(findParent(a) == findParent(b)){
                cycle = true;
                break;
            }
            else {
                unionParent(a, b);
            }
        }

        if(cycle){
            System.out.println("Cycle 발생");
        }
        else{
            System.out.println("Cycle 미발생");
        }
    }
}
