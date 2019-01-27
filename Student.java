public class Student {
  private int id_;
  private String name_;
  private AnswerList answer_list_;
  private int[] dept_choices_;
  private int school_grade_;
  private int exam_grade_;
  private Student next_;
  private Student prev_;
  private int choice_rank_;

  public Student() {
    id_ = 1;
    name_ = "NoName";
    answer_list_ = new AnswerList();
    school_grade_ = 0;
    exam_grade_ = 0;
    dept_choices_ = new int[3];
    next_ = null;
    prev_ = null;
  }

  public Student(int id, String name, int school_grade) {
    id_ = id;
    name_ = name;
    answer_list_ = new AnswerList();
    school_grade_ = school_grade;
    exam_grade_ = 0;
    dept_choices_ = new int[3];
    next_ = null;
    prev_ = null;
  }
  //the answer of the student and the answer sheet are compared and the score is calculated accordingly. 
  //the student's highest score is determined as the actual score
  public void calculateGrade(AnswerList trueAnswers) {
    int result;
    for(int i = -1; i<trueAnswers.getSize() -1; i++) {
      Answer key = trueAnswers.getHead();
      Answer student_answer = answer_list_.getHead();
      result = 0;
      int index = 0;
      while (student_answer != null) {
        if(index == i) {
          student_answer = student_answer.getNext();
        } else {
          if(student_answer.getChoice() == key.getChoice()) {
            result += 5; //five points are added for each correct answer of the student
          }
          key = key.getNext();
          student_answer = student_answer.getNext();
        }
        index++;
      }
      if(exam_grade_ < result) {
        exam_grade_ = result;
      }
    }
  }

  public void addAnswer(char choice) {
    answer_list_.addAnswer(choice);
  }

  public void addAnswers(char... choices) {
    for(int i = 0; i<choices.length; i++) {
      answer_list_.addAnswer(choices[i]);
    }
  }

  public void print() {
    System.out.println("Id: "+getId()+", Name: "+getName()+", "+getExamGrade()+", "+getSchoolGrade());
  }
  //department control chosen by the student
  public void setDeptChoices(int[] dept_choices) {
    for(int i =0; i<3; i++) {
      dept_choices_[i] = dept_choices[i];
    }
  }

  public int[] getDeptChoices() {
    return dept_choices_;
  }
  public int getId() {
    return id_;
  }
  public void setId(int id) {
    this.id_ = id;
  }
  public String getName() {
    return name_;
  }
  public void setName(String name) {
    this.name_ = name;
  }
  public void setAnswer_list(AnswerList list) {
    answer_list_ = list;
  }
  public AnswerList getAnswer_list() {
    return answer_list_;
  }
  public int getSchoolGrade() {
    return school_grade_;
  }
  public void setSchoolGrade(int school_grade) {
    school_grade_ = school_grade;
  }
  public int getExamGrade() {
    return exam_grade_;
  }
  public void setExamGrade(int exam_grade) {
    exam_grade_ = exam_grade;
  }
  public void setNext(Student next) {
    next_ = next;
  }
  public void setPrev(Student prev) {
    prev_ = prev;
  }
  public Student getNext() {
    return next_;
  }
  public Student getPrev() {
    return prev_;
  }

  public void setChoiceRank(int i) {
    choice_rank_ = i;
  }
  public int getChoiceRank() {
    return choice_rank_;
  }
}
