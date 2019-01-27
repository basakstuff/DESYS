public class StudentList {
  private Student head_;
  private Student tail_;
  private int size_;

  public StudentList() {
    head_ = null;
    tail_ = null;
    size_ = 0;
  }

  //Adding students to the list taking into account school scores
  public void insertStudent(Student addition) {
    Student current = head_;
    if(current == null) {
      head_ = addition;
      tail_ = addition;
      addition.setNext(null);
      addition.setPrev(null);
    } else {
      while(current.getNext() != null) {
        if((addition.getExamGrade() > current.getNext().getExamGrade())||(addition.getExamGrade() == current.getNext().getExamGrade() && addition.getSchoolGrade()>=current.getNext().getSchoolGrade())){
          break;
        }
        current = current.getNext();
      }
      addition.setNext(current.getNext());
      addition.setPrev(current);
      if (addition.getNext() != null) {
        addition.getNext().setPrev(addition);
      }
      current.setNext(addition);
    }
    size_++;
    updateTail();
  }

  public void updateTail() {
    Student cur = head_;
    while (cur.getNext() != null) {
      cur=cur.getNext();
    }
    tail_ = cur;
  }
  public void printList() {
    Student cur = head_;
    while (cur!=null) {
      cur.print();
      cur = cur.getNext();
    }
  }

  public void prinListReverse() {
    Student cur = tail_;
    while (cur!=null) {
      cur.print();
      cur = cur.getPrev();
    }
  }

  public Student getHead() {
    return head_;
  }

  public Student getTail() {
    return tail_;
  }

  public int getSize() {
    return size_;
  }

  public void printTopFive() {
    System.out.println("--- Top 5 students ordered by their exam scores:\n");
    Student s = getHead();
    for(int i=0; i<5; i++) {
      System.out.println(s.getId()+" "+s.getName());
      s = s.getNext();
    }
    System.out.println();
  }
}
