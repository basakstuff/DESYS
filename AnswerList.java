public class AnswerList {
  private Answer head_;
  private Answer last_;
  private int size_;

  public AnswerList() {
    size_=0;
    head_=null;
    last_=null;
  }
  
  	//adding the student's selection to the list
  public void addAnswer(char choice) {
    if(head_ == null) {
      head_ = new Answer(choice);
      last_ = head_;
      size_++;
      return;
    }
    last_.setNext(new Answer(choice));
    size_++;
    last_ = last_.getNext();
  }

  public void addAnswers(char... choice) {
    for(int i=0; i<choice.length;i++){
      addAnswer(choice[i]);
    }
  }

  public Answer getHead() {
    return head_;
  }

  public int getSize() {
    return size_;
  }

  public Answer getLast() {
    return last_;
  }
}
