import java.util.*;




class Main{
    //노드개수 와 간선의 개수
    //노드의 개수는 최대 10000개라 가정한다.
    public static int v, e;
    public static int[] parent = new int[100001]; //부모 노드가 누군지 확인할 수 있는 테이블

    //특정 원소가 속한 집합을 "찾기"
    public static int findParent(int x){
        //루트노드가 아니라면, 루트노드를 찾을 때 까지 재귀적 호출
        if(parent[x] == x) return x;
        return findParent(parent[x]);
    }

    //두 원소가 속한 집합을 "합치기"
    public static void unionParent(int a, int b){
        a = findParent(a);
        b = findParent(b);

        if(a > b)
            parent[a] = b;
        else
            parent[b] = a;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        //부모 테이블상에서 부모를 자기 자신으로 초기화
        for(int i = 1 ; i <= v ; i++) parent[i] = i;

        //Union 연산 수행
        for(int i = 0 ; i < e ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            unionParent(a, b);
        }

        //각 원소가 속한 집합 출력
        System.out.println("각 원소가 속한 집합: ");
        for(int i = 1 ; i <= v ; i++) System.out.print(findParent(i) +" ");
        System.out.println();

        //부모 테이블 내용 출력하기
        System.out.println("부모 테이블: ");
        for(int i = 1 ; i <= v ; i++){
            System.out.print(parent[i] + " ");
        }
        System.out.println();

    }
}
