import java.util.Scanner;

public class Management {
  public static void main(String[] args) {

    StudentList studentList = null;
    Department[] departments = null;
    AnswerList answer_key = null;
    DepartmentList departmentList = null;
    FileLoader fl = new FileLoader();

    String command;
    boolean loaded = false;
    Scanner scanner = new Scanner(System.in);
    System.out.print("Command >> "); //user input operation
    while ((command = scanner.nextLine()) != "exit") {
      switch (command) { //specify operations to be performed according to the entered command
        case "load": //loads txt files with "load" command
          departments = fl.loadDepartments();
          answer_key = fl.loadAnswerKey();
          studentList = fl.loadStudents();
          departmentList = fl.formDepartments();
          loaded = true;
          System.out.println("\"Student Information.txt\", \"Answer Sheet.txt\", \"Department Information.txt\" are loaded.");
          break;
        case "results": //the required functions are called together with the results command
          if (loaded) {
            if(studentList != null) {
              studentList.printTopFive();
            } else {
              System.out.println("Student List is NULL");
            }
            if(departmentList != null) {
              departmentList.printHighLowGrade();
              departmentList.printFullness();
              departmentList.printStudents();
            } else {
              System.out.println("Department List is NULL");
            }
          } else {
            System.out.println("No files are currently loaded");
          }
          break;
      }
      System.out.print("Command >> ");
    }
  }
}
