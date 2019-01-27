public class Department {
  public int id_;
  public int capacity_;
  public String name_;
  public Department next_highest_grade; 
  public Department next_lexicographic; //sorting by alphabetical order
  public Department next_fullnessratio;
  public StudentList admitted_;

  Department(int id,String name, int capacity) {
    id_ = id;
    capacity_ = capacity;
    name_ = name;
    next_highest_grade = null;
    next_lexicographic = null;
    next_fullnessratio = null;
    admitted_ = new StudentList();
  }

  //placement in a department 
  //by evaluating school and exam grade according to the preference order of the student
  public boolean addStudent(Student admitee, int choiceRank) {
    if (admitted_.getSize() + 1 <= capacity_) {
      Student student = new Student(admitee.getId(), admitee.getName(), admitee.getSchoolGrade());
      student.setExamGrade(admitee.getExamGrade());
      student.setDeptChoices(admitee.getDeptChoices());
      student.setAnswer_list(admitee.getAnswer_list());
      student.setChoiceRank(choiceRank);
      admitted_.insertStudent(student);
      return true;
    } else {
      return false;
    }
  }
}
