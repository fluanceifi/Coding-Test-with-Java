import java.util.*;

class Student implements Comparable<Student>{
    private String name;
    private int score;

    Student(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return this.name;
    }
    
    public int getScore(){
        return this.score;
    }

    @Override
    public int compareTo(Student other){
        if(this.score < other.score){
            return -1;
        }
        return 1;
    }
    
}


class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        int n = sc.nextInt();
        

        for(int i = 0 ; i < n ; i++){
            String name = sc.next();
            int score = sc.nextInt();
            students.add(new Student(name, score));
        }

        Collections.sort(students); //오름차순
        //Collections.sort(students, Collections.reverseOrder()); 내림차순 


        for(Student student : students){
            System.out.print(student.getName() + " ");
        }
    }
}
        
