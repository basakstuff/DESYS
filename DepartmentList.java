public class DepartmentList {
  private Department dummy_head_;

  public DepartmentList() {
    dummy_head_ = new Department(-1, "", -1);
  }

  public Department setDummy_head() {
    return dummy_head_;
  }
  public void getDummy_head(Department dummy_head) {
    this.dummy_head_ = dummy_head;
  }

  //placement in departments according to fullness and scores
  public void insertDepartment(Department dept) {
    if(dummy_head_.next_lexicographic == null) {
      dummy_head_.next_lexicographic = dept;
    } else {
      Department cur = dummy_head_.next_lexicographic;
      while(cur.next_lexicographic != null && cur.next_lexicographic.name_.compareTo(dept.name_) < 0) {
        cur = cur.next_lexicographic;
      }
      dept.next_lexicographic = cur.next_lexicographic;
      cur.next_lexicographic = dept;
    }

    if(dummy_head_.next_highest_grade == null) {
      dummy_head_.next_highest_grade = dept;
    } else {
      Department cur = dummy_head_.next_highest_grade;
      while(cur.next_highest_grade != null && cur.next_highest_grade.admitted_.getHead().getExamGrade() > dept.admitted_.getHead().getExamGrade()) {
        cur = cur.next_highest_grade;
      }
      dept.next_highest_grade = cur.next_highest_grade;
      cur.next_highest_grade = dept;
    }

    if(dummy_head_.next_fullnessratio == null) {
      dummy_head_.next_fullnessratio = dept;
    } else {
      Department cur = dummy_head_.next_fullnessratio;
      while (cur.next_fullnessratio != null && (((double)cur.next_fullnessratio.admitted_.getSize())/cur.next_fullnessratio.capacity_) > ((double)dept.admitted_.getSize()/dept.capacity_)){
        cur = cur.next_fullnessratio;
      }
      dept.next_fullnessratio = cur.next_fullnessratio;
      cur.next_fullnessratio = dept;
    }
  }
  
  //printing by alphabetical order
  public void print_lexicographic() {
    Department d = dummy_head_.next_lexicographic;
    while (d!=null){
      System.out.println(d.name_);
      d = d.next_lexicographic;
    }
  }

   //printing fullness
  public void print_fullness() {
    Department d = dummy_head_.next_fullnessratio;
    while (d!=null){
      System.out.println(d.name_ + " - " + ((double)d.admitted_.getSize())/d.capacity_*100.0);
      d = d.next_fullnessratio;
    }
  }
  
  //printing the highest score
  public void print_highestGrade() {
    Department d = dummy_head_.next_highest_grade;
    while (d!=null){
      System.out.println(d.name_ + " - " + d.admitted_.getHead().getExamGrade());
      d = d.next_highest_grade;
    }
  }

  
  //printing students with the highest and lowest scores
  public void printHighLowGrade() {
    System.out.println("--- List of departments with highest and lowest grades of accepted students\n");
    Department d = dummy_head_.next_highest_grade;
    while (d!=null) {
      Student s = d.admitted_.getHead();
      int highestGrade = s.getExamGrade();
      while (s.getNext() != null) s=s.getNext();
      int lowestGrade =  s.getExamGrade();
      System.out.println(d.name_+": "+highestGrade+" "+lowestGrade);
      d = d.next_highest_grade;
    }
    System.out.println();
  }

  public void printFullness() {
    System.out.println("--- List of departments with their fullness ratio\n");
    Department d = dummy_head_.next_fullnessratio;
    while (d!=null){
      double fullness = ((double)d.admitted_.getSize())/d.capacity_;
      System.out.println(String.format("%s: %.2f", d.name_, fullness));
      d = d.next_fullnessratio;
    }
    System.out.println();
  }

  public void printStudents() {
    System.out.println("--- List of students, divided by their departments,\n"
        + "    ordered by their grades in descending order\n"
        + "    and including their personal department choice rank\n");
    Department d = dummy_head_.next_lexicographic;
    while (d!=null){
      System.out.println(d.name_+": ");
      Student s = d.admitted_.getHead();
      while (s !=null){
        System.out.println("\t"+s.getName()+": "+s.getExamGrade()+" "+s.getChoiceRank());
        s = s.getNext();
      }
      d = d.next_lexicographic;
    }
  }
}
