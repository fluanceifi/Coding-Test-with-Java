import java.util.*;

class Main {

    //노드
    public static int n;
    //진입차수 = 듣고자 하는 강의의 수
    public static int[] indegree = new int[501];
    //그래프
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    //각 강의 시간
    public static int[] times = new int[501];

    public static void topology() {
        int[] result = new int[501];

        for (int i = 1 ; i <= n; i++) result[i] = times[i];

        Queue<Integer> q = new LinkedList<>();

        for(int i = 1 ; i <= n ; i++)
            if(indegree[i] == 0)
                q.offer(i);

        while(!q.isEmpty()){
            //now는 현재 과목의 시간이다.
            int now = q.poll();

            for(int i = 0 ; i < graph.get(now).size() ; i++){
                //now의 다음과목은 i이다.
                //now가 선제 과목인 i번째 과목은 "계산된 i번째 과목의 시간", "현재과목 + i번째 과목의 시간" 둘 중 무엇이 더 큰 시간이 필요한지 비교하여 선택한다.
                //이런식으로 계산하면 그물처럼 이어진 방향 그래프에서 가장 큰 비용이 든 시간만을 남겨둔다.
                result[graph.get(now).get(i)] = Math.max(result[graph.get(now).get(i)], result[now] + times[graph.get(now).get(i)]);

                // 그런 다음 i 과목의 차수를 하나 제거해준다.
                indegree[graph.get(now).get(i)]--;

                //만약 i과목의 진입차수가 0이되면 큐에 삽입
                if(indegree[graph.get(now).get(i)] == 0)
                    q.offer(graph.get(now).get(i));
            }
        }

        //위상 정렬 수행결과
        for(int i = 1 ; i <= n ; i++)
            System.out.println(result[i]);
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        //그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        //방향그래프 간선정보 받기
        for (int i = 1; i <= n; i++) {
            times[i] = sc.nextInt();
            //선수과목 입력
            while (true) {
                // i는 현재 노드 | x는 -1일경우 종료, -1이 아닌 경우 선수과목(거쳐야 될 이전 노드)
                int x = sc.nextInt();
                if (x == -1) break;
                indegree[i]++;
                graph.get(x).add(i);
            }
        }

        topology();
    }
}
