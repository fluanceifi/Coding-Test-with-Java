import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Food implements Comparable<Food>{
    private int time;
    private int idx;

    public Food(int time, int idx){
        this.time = time;
        this.idx = idx;
    }

    public int getTime(){
        return this.time;
    }

    public int getIdx(){
        return this.idx;
    }

    @Override //짧은 시간을 가진 음식이 우선순위를 가지도록 한다.
    public int compareTo(Food other){
        return Integer.compare(this.time, other.time);
    }
}


class Solution{
    public int solution(int[] food_times, long k){

        long summary = 0;
        for(int i = 0 ; i < food_times.length ; i++){
            summary += food_times[i]; //전체 음식 먹을 횟수
        }
        if(k >= summary) //먹어야 하는 횟수보다 먹을 횟수가 더 많다면 나중에 더 이상 섭취할게 없으니 먼저 -1 반환하여 시간복잡도 최적화.
            return -1;


        //우선 순위 큐로 진행
        PriorityQueue <Food> pq = new PriorityQueue<>();

        for(int i = 0 ; i < food_times.length ; i++){
            //음식 시간, 음식 번호 형태로 생긴 객체를 pq에 삽입, 시간이 작은 음식이 우선 순위이다.
            pq.offer(new Food(food_times[i], i+1));
        }

        summary = 0; //먹기 위해 사용한 시간
        long previous = 0; //직전에 다 먹음 음식 시간
        long length = food_times.length; //남은 음식 개수

        //summary + (현재음식 시간 + 이전 음식시간) * 현재 음식 개수와 k 비교
        while(summary + (pq.peek().getTime() - previous) * length <= k){ //사용한 시간 + 현재 음식을 다 먹기 위해 필요한 시간 <= 총 먹을 시간
            int now = pq.poll().getTime();
            summary += (now - previous) * length;
            length--; //다 먹으면 음식 제외
            previous = now; //이전 음식 시간 재설정
        }
        //이걸 반복하다보면 현재 음식을 다 먹기위해 필요한 시간이 총 먹을 시간보다 커진다. 그 뜻은 더이상 다 먹을 수 없어 음식이 남는단 뜻이다.

        //남은 음식 중에서 몇 번째 음식인지 확인하여 출력
        ArrayList<Food> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(pq.poll());
        }
        
        //음식의 번호 기준으로 정렬
        Collections.sort(result, new Comparator<Food>(){
            @Override
            public int compare(Food a, Food b){
                return Integer.compare(a.getIdx(), b.getIdx());
            }
        });

        return result.get((int) ((k - summary) % length)).getIdx(); // (총 먹을 횟수 - 지금까지 먹은 횟수) % 남은 음식 개수.해당 인덱스
    }
    
}
