import java.util.*;

public class Main{
    //v : node, e : edge
    //max node = 100,000
    public static int v, e;
    //모든 노드에 대한 진입차수는 0으로 초기화
    public static int[] indegree = new int[100001];
    //연결리스트로 그래프의 내용물인 노드<>와 간선<<>> 구현
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    public static void topologySort(){
        //알고리즘 결과(순서)를 담을 ArrayList 구현
        ArrayList<Integer> result = new ArrayList<Integer>();

        //1. 최초에 진입차수가 0인 노드를 삽입하고
        //2. 그 뒤로 진입차수가 하나 밖에 없는 노드만 삽입할 Queue 생성
        Queue<Integer> q = new LinkedList<>();

        //1
        for(int i = 1 ; i <= v ; i++){
            if(indegree[i] == 0) q.offer(i);
        }

        //2
        while(!q.isEmpty()){
            //원소꺼내고 결과(순서)용 리스트에 삽입.
            int now  = q.poll();
            result.add(now);

            //현재 노드와 연결된 간선의 개수만큼 반복한다.
            for(int i = 0 ; i < graph.get(now).size() ; i++){
                indegree[graph.get(now).get(i)]--;
                // 해당 노드의 진입차수가 0이면 결과에 삽입함.
                if(indegree[graph.get(now).get(i)] == 0)
                    q.offer(graph.get(now).get(i));
            }
        }

        for(int i = 0 ; i < result.size() ; i++){
            System.out.print(result.get(i) + " ");
        }
    }




    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();


        //노드 구현
        for(int i = 0 ; i <= v ; i++){
            graph.add(new ArrayList<Integer>());
        }

        //방향그래프 간선 입력
        for(int i = 0 ; i < e ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b); //a -> b
            indegree[b]++;
        }

        topologySort();

    }
}
