import java.util.*;

class Node implements Comparable<Node> {
    private int x;
    private int y;
    private int stuff;

    public Node(int x , int y, int stuff){
        this.x = x;
        this.y = y;
        this.stuff = stuff;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getStuff(){
        return this.stuff;
    }

    @Override
    public int compareTo(Node other){
        if(this.x == other.x && this.y == other.y){
            return Integer.compare(this.stuff, other.stuff); //stuff가 3순위
        }
        if(this.x == other.x){
            return Integer.compare(this.y, other.y); //y가 2순위
        }

        return Integer.compare(this.x, other.x); //x가 1순위

    }
}

class Solution {

    public boolean possible(ArrayList<ArrayList<Integer>> answer){
        for(int i = 0 ; i < answer.size() ; i++){
            int x = answer.get(i).get(0);
            int y = answer.get(i).get(1);
            int stuff = answer.get(i).get(2);

            if(stuff == 0){ //설치된 것이 기둥일 경우
                boolean check = false;
                //바닥 위라면 정상
                if(y == 0) check = true; //바닥이라면 정상
                //'보의 한 쪽 끝 부분 위' 혹은 '다른 기둥 위'라면 정상
                for(int j = 0 ; j < answer.size() ; j++){
                    if(x - 1 == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)){
                        check = true; //보의 왼쪽 끝일 경우
                    }
                    if(x == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)){
                        check = true; //보의 오른쪽 끝일 경우
                    }
                    if(x == answer.get(j).get(0) && y - 1 == answer.get(j).get(1) && 0 == answer.get(j).get(2)){
                        check = true; //설치할 기둥 아래에 기둥이 존재할 경우
                    }
                }
                if(!check) return false;
            }
            else if(stuff == 1){
                boolean check = false;
                boolean left = false;
                boolean right = false;
                //'한쪽 끝부분이 기둥 위' 혹은 '양쪽 끝부분이 다른 보와 동시에 연결' 이라면 성장
                for(int j = 0 ; j < answer.size() ; j++){
                    if(x == answer.get(j).get(0) && y - 1 == answer.get(j).get(1) && 0 == answer.get(j).get(2)){
                        check = true;//왼쪽 끝 부분이 기둥일 경우
                    }
                    if(x + 1 == answer.get(j).get(0) && y - 1 == answer.get(j).get(1) && 0 == answer.get(j).get(2)){
                        check = true; //오른쪽 끝 부분이 기둥일 경우
                    }
                    if(x - 1 == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)){
                        left = true;//왼쪽 끝부분이 보 일 경우
                    }
                    if(x + 1 == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)){
                        right = true; //오른쪽 끝 부분이 보 일 경우
                    }
                }
                if(left && right) check = true; //왼쪽 , 오른쪽 둘 다 보여서 연결 가능하면 check = true;
                if(!check) return false;
            }
        }
        return true;
    }


    public int[][] solution(int n, int[][] build_frame) {
        ArrayList<ArrayList<Integer>>  answer = new ArrayList<ArrayList<Integer>>();
        //작업(frame)의 개수는 최대 1000개 -> n^3가능
        for(int i = 0 ; i < build_frame.length ; i++){
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int stuff = build_frame[i][2]; //0이면 기둥, 1이면 보
            int operate = build_frame[i][3];
            //삭제하는 경우
            if(operate == 0){
                //일단 삭제해 본다.
                int index = 0;
                for(int j = 0 ; j < answer.size() ; j++){
                    if(x == answer.get(j).get(0) && y == answer.get(j).get(1) && stuff == answer.get(j).get(2)){ //x좌표랑 y좌표 그리고 자재가 일치한다면
                        index = j; //j번을 고른다.
                    }
                }
                ArrayList<Integer> erased = answer.get(index); //지운건 따로 저장해둠
                answer.remove(index);//그리고 지움
                if(!possible(answer)){ //지워도 가능한 구조물인지 확인
                    answer.add(erased); //가능한 구조물이 아니라면 롤백
                }
            }
            //설치하는 경우
            if(operate == 1){
                //일단 설치를 해본다.
                ArrayList<Integer> inserted = new ArrayList<Integer>();
                inserted.add(x);
                inserted.add(y);
                inserted.add(stuff);
                answer.add(inserted);
                if(!possible(answer)){
                    answer.remove(answer.size() - 1); //size와 index는 1차이
                }
            }
        }

        ArrayList<Node> ans = new ArrayList<Node>();
        for(int i = 0 ; i < answer.size() ; i++){
            ans.add(new Node(answer.get(i).get(0), answer.get(i).get(1), answer.get(i).get(2)));
        }
        Collections.sort(ans);

        int[][] res = new int[ans.size()][3];
        for(int i = 0 ; i < ans.size() ; i++) {
            res[i][0] = ans.get(i).getX();
            res[i][1] = ans.get(i).getY();
            res[i][2] = ans.get(i).getStuff();
        }

        return res;
    }
}
