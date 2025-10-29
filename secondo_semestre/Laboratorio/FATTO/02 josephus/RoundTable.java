public class RoundTable {
  // Number of knights currently at the table
  private final int num;
  // Knight who currently holds the jug
  private final int jug;
  // Head of the list: clockwise order starting after the jug
  private final IntSList head;
  // Tail of the list: clockwise order ending before the jug (stored reversed)
  private final IntSList tail;

  // Constructor: initializes the table with 'n' knights
  public RoundTable(int n) {
    if (n < 2) throw new IllegalArgumentException("n must be >= 2");
    num = n;         // total knights
    jug = 1;         // jug starts at position 1
    head = range(2, n); // the knights 2 to n are stored in head (clockwise after jug)
    tail = IntSList.NULL_INTLIST; // initially, the tail is empty (no elements before jug)
  }

  // Private constructor used internally when updating state
  private RoundTable(int n, int j, IntSList h, IntSList t) {
    num = n;
    jug = j;
    head = h;
    tail = t;
  }

  // Returns the number of knights at the table
  public int numberOfKnights() {
    return num;
  }

  // Returns the two knights who can still serve or be served (A and B)
  public IntSList servingKnights() {
    if (num < 2) {
      // Only one knight remains (special case)
      return new IntSList(jug, IntSList.NULL_INTLIST);
    }
    IntSList full = fullList(); // get complete circular list starting at jug
    int aIndex = fullIndex(jug, full); // index of the knight with the jug (should be 0)
    int b = full.listRef((aIndex + 1) % full.length()); // next knight clockwise
    return new IntSList(jug, new IntSList(b, IntSList.NULL_INTLIST));
  }

  // Removes the third knight (C) and returns new table state
  public RoundTable serveNeighbour() {
    if (num < 3) {
      return this; // nothing to serve if fewer than 3 knights
    }
    IntSList full = fullList(); // full circular list (pre-removal)
    int aIndex = fullIndex(jug, full); // index of A
    int cIndex = (aIndex + 2) % full.length(); // index of C (third in circle)
    int c = full.listRef(cIndex); // actual value (label) of C
    IntSList newList = remove(c, full); // remove C from the flat circular list
    return fromList(num - 1, jug, newList); // return new RoundTable built from flat list
  }

  // Pass the jug to the knight to the left of the one just served
  public RoundTable passJug() {
    if (num < 3) {
      return this; // nothing to do if fewer than 3 knights
    }
    IntSList full = fullList(); // current full circular list (should reflect current state)
    int aIndex = fullIndex(jug, full);
    // CORRECT: pick the element two steps ahead in the current list (A -> B -> D)
    int newJugIndex = (aIndex + 2) % full.length();
    int newJug = full.listRef(newJugIndex); // label of new jug holder
    return fromList(num, newJug, full); // updated table with same number but new jug
  }

  // Helper: builds a RoundTable from a flat list
  private RoundTable fromList(int n, int j, IntSList full) {
    IntSList left = IntSList.NULL_INTLIST;
    IntSList temp = full;
    boolean found = false;

    // Split list into head and tail around new jug holder
    // left accumulates elements BEFORE j (in reversed order because we cons)
    while (!temp.isNull()) {
      int val = temp.car();
      if (val == j) {
        found = true;
        temp = temp.cdr(); // temp now points to elements after j => becomes head
        break;
      }
      left = left.cons(val);
      temp = temp.cdr();
    }

    if (found) {
      // IMPORTANT FIX: store left (which currently holds elements before j in reversed order)
      // tail is expected (by fullList()) to be stored reversed, so we store 'left' directly.
      return new RoundTable(n, j, temp, left);
    } else {
      // Shouldn't happen, but fall back to full
      return new RoundTable(n, j, full, IntSList.NULL_INTLIST);
    }
  }

  // Helper: returns index of value x in list l
  private int fullIndex(int x, IntSList l) {
    int i = 0;
    IntSList temp = l;
    while (!temp.isNull()) {
      if (temp.car() == x) {
        return i;
      }
      temp = temp.cdr();
      i++;
    }
    return -1; // not found (should not happen)
  }

  // Helper: removes the first occurrence of x from list l
  private IntSList remove(int x, IntSList l) {
    if (l.isNull()) {
      return l;
    } else if (l.car() == x) {
      return l.cdr();
    } else {
      return remove(x, l.cdr()).cons(l.car());
    }
  }

  // Helper: builds the full list (jug + head + reversed tail)
  private IntSList fullList() {
    // tail is stored reversed (prefix-before-j reversed); so tail.reverse() gives prefix-before-j in correct clockwise order
    return append(new IntSList(jug, head), tail.reverse());
  }

  // Helper: appends two IntSLists
  private IntSList append(IntSList l1, IntSList l2) {
    if (l1.isNull()) {
      return l2;
    } else {
      return append(l1.cdr(), l2).cons(l1.car());
    }
  }

  // Helper: builds a list from inf to sup (inclusive)
  private static IntSList range(int inf, int sup) {
    if (inf > sup) {
      return IntSList.NULL_INTLIST;
    } else {
      return range(inf + 1, sup).cons(inf);
    }
  }

  // Main simulation method: returns last two knights (Josephus-style)
  public static IntSList josephus(int n) {
    RoundTable rt = new RoundTable(n);
    while (rt.numberOfKnights() > 2) {
      rt = rt.serveNeighbour(); // C leaves
      rt = rt.passJug();        // jug passed
    }
    return rt.servingKnights(); // return last two knights
  }

  // convenience to inspect state
  @Override
  public String toString() {
    return fullList().toString();
  }

  // test driver
  public static void main(String[] args) {
    int[] tests = {2,3,4,5,6,7,8,12,1500};
    for (int t : tests) {
      IntSList res = RoundTable.josephus(t);
      System.out.printf("josephus(%d) -> %s%n", t, res.toString());
    }
  }
}
