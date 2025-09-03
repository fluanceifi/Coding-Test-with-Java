import java.util.*;

class Student implements Comparable<Student>{
    private String name;
    private int korean;
    private int english;
    private int math;

    public Student(String n, int k, int e, int m){
        this.name = n;
        this.korean =k;
        this.english=e;
        this.math=m;
    }
    public String getName(){
        return this.name;
    }

    //compareTo()는 무조건 "<"를 기준한다.
    // 첫번째 파라미터와 두번째 파라미터의 순서만 생각해서 넣으면
    // this < other인지, other < this 인지에 따라 오름차순과 내림차순이 결정된다.
    // this가 기준이 되어 더 작으면 오름차순이고, other가 기준이되어 오름차순이 되면 this는 자연스럽게 내림차순으로 정렬이 됨.
    @Override
    public int compareTo(Student other) {
        if(this.korean == other.korean && this.english == other.english && this.math == other.math){
            return this.name.compareTo(other.name);
        }
        else if(this.korean == other.korean && this.english == other.english){
            return Integer.compare(other.math, this.math);
        }
        else if(this.korean == other.korean){
            return Integer.compare(this.english, other.english);
        }
        else{
            return Integer.compare(other.korean, this.korean);
        }
    }
}

class Main{
    public static int n;
    public static ArrayList<Student> s = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for(int i = 0 ; i < n; i++){
            String name = sc.next();
            int kor = sc.nextInt();
            int eng = sc.nextInt();
            int math = sc.nextInt();
            s.add(new Student(name, kor, eng, math));
        }
        Collections.sort(s);

        for(Student stu : s){
            System.out.println(stu.getName());
        }

    }
}
