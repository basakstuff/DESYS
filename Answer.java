public class Answer {
  private char choice_;
  private Answer next_;
  public Answer(char choice){
    next_ = null;
    choice_ = choice;
  }

  public Answer getNext() {
    return next_;
  }

  public char getChoice() {
    return choice_;
  }

  public void setNext(Answer next) {
    next_ = next;
  }
}
