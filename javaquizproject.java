import java.util.Scanner;
import java.util.Stack;

class MCQ
{
    private String question;
    private String[] options;
    private int correctoption;

    MCQ(String question,String[] options,int correctoption){
        this.question=question;
        this.options=options;
        this.correctoption=correctoption;
    }

    String getQuestion(){
        return question;
    }

    void getoptions(){
        int i;
        for(i=0;i<4;i++){
            System.out.println("option "+(i+1)+"= "+options[i]);
        }
    }

    int getcorrect(){
        return correctoption;
    }
}
class Admin
{
    public void createquiz() {
        Scanner sc=new Scanner(System.in);
        System.out.println("HELLO ADMIN, come lets create a quiz!");
        System.out.println("\nREMEMBER\n enter\n 1.add multiple choice questions to quiz.\n 2.complete creating a quiz\n\n");
        for(;;){
            System.out.print("Enter your choice Admin: ");
            int choice=sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\nEnter the Multiple choice question");
                    String question = sc.nextLine();

                    String[] options = new String[4];
                    System.out.println("\nEnter the 4 options for Multiple choice question");
                    for (int i = 0; i < 4; i++) {
                        options[i] = sc.nextLine();
                    }

                    System.out.println("\nenter the correct option index");
                    int correctoption = sc.nextInt();

                    javaquizproject.stack.push(new MCQ(question, options, correctoption));
                    break;
                case 2:System.out.println("\n\nHey quiz creation is completed\n");
                    return;
            }
        }
    }
}

interface Quiz
{
    public void Startquiz();
    public void Endquiz();
}

class Student implements Quiz
{
    private String name;
    public int correctsum=0;
    public int wrongsum=0;
    public int total=0;

    Student(String n){
        this.name=n;
    }

    String getname(){
        return name;
    }

    public void Startquiz()
    {
        for (MCQ m : javaquizproject.stack) {
            total++;
            System.out.print("\nQUESTION "+(total)+": ");
            System.out.println(m.getQuestion());
            m.getoptions();
            System.out.print("Enter the correct option: ");
            Scanner sc=new Scanner(System.in);
            int ans=sc.nextInt();
            sc.nextLine();
            if(ans==m.getcorrect()){
                System.out.println("\nHEY! "+name +" thats the right answer");
                correctsum++;
            }
            else{
                System.out.println("\nHEY! "+name +" thats the wrong answer");
                wrongsum++;
            }
            System.out.println("The correct option is: "+m.getcorrect());
            System.out.println("\n");

        }
    }

    public void Endquiz()
    {
        System.out.println("\n This completes your Quiz "+name+" Hope you enjoyed and satisfied with results\n");
    }
    int getcorrectanswers()
    {
        return correctsum;
    }

    int getwronganswers()
    {
        return wrongsum;
    }

}



public class javaquizproject {
    static  Stack<MCQ> stack = new Stack<>();
    public static void main(String[] args) {
        System.out.println("Welcome!! NO quiz to be hosted without creation!\n");
        Admin a =new Admin();
        a.createquiz();

        System.out.println("Hey its the time now for students to take the Quiz"+"\n NOTE ONLY 10 STUDENTS CAN TAKE THIS QUIZ"+"\n\n");

        for(int i=1;i<=10;i++)
        {   Scanner sc=new Scanner(System.in);
            System.out.print("Enter yes to continue with quiz,enter no to stop quiz");
            String cont=sc.nextLine();
            if (cont.equals("no")){
                System.exit(0);
            }
            System.out.print("Enter the Student Name: ");
            String n=sc.nextLine();
            Student obj=new Student(n);
            System.out.println("Hey "+obj.getname()+" Let's start your quiz\n");
            Quiz obj1=new Student(n);
            obj.Startquiz();
            System.out.println("\n" + obj.getname() + ": " + "\nAttempted questions= " + obj.total + "\nCorrect Answers= " + obj.getcorrectanswers() + "\nWrong answers= " + obj.getwronganswers() + "\n");
            obj1.Endquiz();
        }

    }
}