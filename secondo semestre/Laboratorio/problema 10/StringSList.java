public class StringSList {
    private final String head;
    private final StringSList tail;
    public static final StringSList empty = new StringSList();
    public StringSList() {
        this.head = null;
        this.tail = null;
    }
    public StringSList(String s, StringSList l) {
        this.head = s;
        this.tail = (l == null) ? empty : l;
    }
    public boolean isNull() {
        return this == empty;
    }
    public String car() {
        return head;
    }
    public StringSList cdr() {
        return tail;
    }
    public StringSList cons(String s) {
        return new StringSList(s, this);
    }
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (!(obj instanceof StringSList)) 
            return false;
            StringSList other = (StringSList) obj;
        if (this.isNull() && other.isNull()) 
            return true;
        if (this.isNull() || other.isNull()) 
            return false;
        return this.car().equals(other.car()) && this.cdr().equals(other.cdr());
    }

    public int length() {
        if (isNull()) return 0;
        return 1 + cdr().length();
    }

    public String listRef(int index) {
        if (isNull()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            return car();
        }
        return cdr().listRef(index - 1);
    }

    public StringSList append(StringSList l) {
        if (isNull()) return l;
        return new StringSList(car(), cdr().append(l));
    }

    public StringSList reverse() {
        return reverseAux(empty);
    }

    private StringSList reverseAux(StringSList acc) {
        if (isNull()) {
            return acc;
        }
        return cdr().reverseAux(acc.cons(car()));
    }

    public String toString() {
        if (isNull()) return "()";

        StringBuilder sb = new StringBuilder("(");
        sb.append(car());
        StringSList temp = cdr();
        while (!temp.isNull()) {
            sb.append(" ").append(temp.car());
            temp = temp.cdr();
        }
        sb.append(")");
        return sb.toString();
    }
}
