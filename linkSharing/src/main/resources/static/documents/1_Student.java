
/*  Q5) Write a program to sort the Student objects based on Score , if the score are same then sort on First Name .
        Class Student{ String Name; Double Score; Double Age
        */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Question5 implements Comparator <Student>{

    @Override
    public int compare(Student s1,Student s2)
    {
	System.out.println(s1.score+" :"+s2.score);
	System.out.println(s1.score==s2.score);
	System.out.println("equals : " + s1.score.equals(s2.score));
        if(s1.score.compareTo(s2.score)==0)
	//if(s1.score==s2.score)
        {

            return s1.name.compareTo(s2.name);
        }
        else
        { 
            return s1.score.compareTo(s2.score);
        }
    }
}

class Student{
    String name;
    Double score;
    Double age;

    public Student(  String name,Double age,Double score)
    { this.name=name;
      this.score=score;
      this.age=age;
    }
    public static void main(String[] args) {
            List<Student> list=new ArrayList<Student>();
            list.add(new Student("Pooja",21.0,99.1));   // Equal score
            list.add(new Student("Anmol",21.0,99.1));   // Equal score
            list.add(new Student("Pawan",22.0,91.0));
            list.add(new Student("Riya",23.0,92.0));
            list.add(new Student("Jalaj",23.0,100.0));
            list.add(new Student("Jai",23.0,99.4));      // Equal score

        System.out.println("Before sorting-\n"+list);
            Collections.sort(list,new Question5());
            System.out.println("After sorting-\n"+list);

    }
    @Override
    public String toString()
    {
        return "\n"+this.name+" "+this.age+" "+this.score;
    }

}
