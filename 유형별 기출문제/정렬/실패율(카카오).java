import java.util.ArrayList;
import java.util.Collections;

class Node implements Comparable<Node>{
    private int stage;
    private double fail;

    public Node(int stage, double fail){
        this.stage = stage;
        this.fail = fail;
    }

    public int getStage(){
        return this.stage;
    }

    @Override
    public int compareTo(Node other){
        if(this.fail == other.fail){
            return Integer.compare(this.stage, other.stage);
        }
        return Double.compare(other.fail, this.fail);
    }
}

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        ArrayList<Node> arrayList = new ArrayList<>();
        int length = stages.length; //각 스테이지를 통과한 인원 수 : 처음엔 1 스테이지라 이렇게 초기화시킴.
        
        
        //각 스테이지(N) 확인하면서
        for(int i = 1 ; i <= N ; i++){
            int cnt = 0;
            //사람들이 해당 스테이지에 머물러 있나 비교한다.
            for(int j = 0 ; j < stages.length ; j++){
                if(stages[j] == i) cnt++; //스테이지에 도달했으나 아직 클리어하지 못한 플레이어
            }

            //스테이지마다 실패율을 계산한다.
            //실패율 : 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
            double fail = 0;
            if(length >= 1)
                fail = (double)cnt / (double)length;
            
            arrayList.add(new Node(i, fail));
            length -= cnt; //이후 스테이지에선 인원 수에서 제외
        }
        //실패율을 기준으로 내림차순 정렬, 실패율이 같다면 스테이지 순으로 오름차순
        Collections.sort(arrayList);
        
        for(int i = 0 ; i < N ; i++){
            answer[i] = arrayList.get(i).getStage();
        }


        return answer;
    }
}
