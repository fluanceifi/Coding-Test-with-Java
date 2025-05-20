import java.util.*;
import java.lang.*;


//java의 그래프 구조 설계
class Main {
    
    public static boolean[] visited = new boolean[9];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

    public static void bfs(int start){
        //큐생성
        Queue<Integer> q = new LinkedList<>();
        //시작위치 삽입
        q.offer(start);
        //시작위치 방문표시
        visited[start] = true;

        while(!q.isEmpty()){
            //큐에서 출력 후 x에 대입
            int x = q.poll();
            //큐 순서대로 출력
            System.out.print(x + " "); 
            //방문 순서는 큐에 삽입된 순서대로
            for(int i = 0 ; i < graph.get(x).size() ; i++){
                //방문한 노드의 인접 노드를 y로 설정
                int y = graph.get(x).get(i);
                //인접한 노드가 방문하지않았다면
                if(!visited[y]){
                    //큐에 삽입
                    q.offer(y);
                    //그리고 방문 처리
                    visited[y] = true;
                }
            }
        }

    }
    public static void main(String[] args) {
        for(int i = 0 ; i < 9 ; i++){
            graph.add(new ArrayList<Integer>());
        }

        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(1).add(8);

        graph.get(2).add(1);
        graph.get(2).add(7);

        graph.get(3).add(1);
        graph.get(3).add(4);
        graph.get(3).add(5);

        graph.get(4).add(3);
        graph.get(4).add(5);

        graph.get(5).add(3);
        graph.get(5).add(4);

        graph.get(6).add(7);

        graph.get(7).add(2);
        graph.get(7).add(6);
        graph.get(7).add(8);

        graph.get(8).add(1);
        graph.get(8).add(7);

        bfs(1);
    }
}
