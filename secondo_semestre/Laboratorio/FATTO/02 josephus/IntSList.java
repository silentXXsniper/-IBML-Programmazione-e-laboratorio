public class IntSList {
  public static final IntSList NULL_INTLIST = new IntSList();
  private final boolean empty;
  private final int first;
  private final IntSList rest;
  public IntSList() {
    empty = true;
    first = 0;
    rest = null;
}
  public IntSList(int f, IntSList r) {
    empty = false;
    first = f;
    rest = r;
  }
  public boolean isNull() {
    return empty;
  }
  public int car() {
    return first;
  }
  public IntSList cdr() {
    return rest;
  }
  public IntSList cons(int x) {
    return new IntSList(x, this);
  }
  public int length() {
    if (isNull()) {
      return 0;
    } else {
      return 1 + cdr().length();
    }
  }
  public int listRef(int k) {
    //tirorna elemento in posizione k
    if (k == 0) {
      return car();
    } else {
      return cdr().listRef(k - 1);
    }
  }
  public IntSList reverse() {
    return reverseRec(this, NULL_INTLIST);
  }
  private static IntSList reverseRec(IntSList l, IntSList r) {
    if (l.isNull()) {
      return r;
    } else {
      return reverseRec(l.cdr(), r.cons(l.car()));
    }
  }
  @Override
  public String toString() {
    String s = "(";
    if (!isNull()) {
      s += car();
      IntSList temp = cdr();
      while (!temp.isNull()) {
        s += ", " + temp.car();
        temp = temp.cdr();
      }
    }
    return s + ")";
  }
}
