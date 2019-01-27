import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader {

  BufferedReader departmentFile;
  BufferedReader answerFile;
  BufferedReader studentFile;

  AnswerList answer_key;
  StudentList studentList;
  DepartmentList departmentList;
  Department[] departments;

  FileLoader() {
    answer_key = new AnswerList();
    studentList = new StudentList();
    departmentList = new DepartmentList();
  }

  public static int findIndexOfDepWithId(Department[] depts, int id) {
    for(int i=0; i<depts.length; i++) {
      if(depts[i].id_ == id) {
        return i;
      }
    }
    return -1;
  }

  public DepartmentList formDepartments() {
    Student cur = studentList.getHead();
    while (cur != null) {
      for(int i=0; i<cur.getDeptChoices().length; i++) {
        if(departments[FileLoader.findIndexOfDepWithId(departments, cur.getDeptChoices()[i])].addStudent(cur, i+1)) {
          cur.setChoiceRank(i+1);
          break;
        }
      }
      cur = cur.getNext();
    }

    for(int i=0; i<departments.length; i++) {
      departmentList.insertDepartment(departments[i]);
    }

    return departmentList;
  }

 // The process of uploading the txt file containing student information 
 //with the help of the split function
  public StudentList loadStudents() {
    try{
      studentFile = new BufferedReader(new FileReader("Student Information.txt"));
      String line;
      while ((line = studentFile.readLine()) != null) {
        String[] studentinfo = line.split(",");
        int studentNo = Integer.parseInt(studentinfo[0].trim());
        String name = studentinfo[1];
        int school_grade = Integer.valueOf(studentinfo[2]);
        int[] choices = new int[3];
        for (int i=3; i<6 ;i++) {
          choices[i-3] = Integer.parseInt(studentinfo[i]);
        }
        char[] answers = new char[studentinfo.length - 6];
        for (int i = 6; i < studentinfo.length; i++) {
          answers[i - 6] = studentinfo[i].charAt(0);
        }
        Student addition = new Student(studentNo, name, school_grade);
        addition.setDeptChoices(choices);
        addition.addAnswers(answers);
        addition.calculateGrade(answer_key);
        studentList.insertStudent(addition);
      }
    } 
   // error message
    catch (IOException e) {
      System.err.println("Problem reading \"Answer Sheet.txt\"");
      System.exit(1);
    }
    return studentList;
  }
//The process of uploading the txt file containing answer information 
//with the help of the split function
  public AnswerList loadAnswerKey() {
    try{
      answerFile = new BufferedReader(new FileReader("Answer Sheet.txt"));
      String answer_line = answerFile.readLine();
      String[] answer_array = answer_line.split(",");
      char[] answer_chars = new char[answer_array.length];
      for (int i = 0; i < answer_array.length; i++) {
        answer_chars[i] = answer_array[i].charAt(0);
      }
      answer_key.addAnswers(answer_chars);
    } catch (IOException e) {
      System.err.println("Problem reading \"Answer Sheet.txt\"");
      System.exit(1);
    }
    return answer_key;
  }
//The process of uploading the txt file containing department information 
//with the help of the split function
  public Department[] loadDepartments() {
    try {
      departmentFile = new BufferedReader(new FileReader("Department Information.txt"));
      int lineCount = 0;
      while (departmentFile.readLine() != null) lineCount++;
      departmentFile.close();
      departments = new Department[lineCount];
      departmentFile = new BufferedReader(new FileReader("Department Information.txt"));
      String department_line;

      int index = 0;
      while ((department_line = departmentFile.readLine()) != null) {
        String[] department_info = department_line.split(",");
        int deptId = Integer.parseInt(department_info[0]);
        String deptName = department_info[1];
        int deptCap = Integer.parseInt(department_info[2]);
        Department dept = new Department(deptId, deptName, deptCap);
        departments[index] = dept;
        index++;
      }
    } catch (IOException e) {
      System.err.println("Problem reading \"Department Information.txt\"");
      System.exit(1);
    }
    return departments;
  }
}
