public class BtrConverter {
    // Genera una lista di n stringhe consecutive in notazione BTR, a partire da btr
    public static StringSList btrRange(String btr, int n) {
        StringSList result = StringSList.empty;
        for (int i = 0; i < n; i++) {
            result = result.cons(btr);
            btr = incrementBtr(btr);
        }
        return result.reverse();
    }
    // Incrementa un numero in BTR
    private static String incrementBtr(String btr) {
        StringBuilder sb = new StringBuilder(btr);
        int i = sb.length() - 1;
        while (i >= 0) {
            switch (sb.charAt(i)) {
                case '-':
                    sb.setCharAt(i, '.');
                    return sb.toString();
                case '.':
                    sb.setCharAt(i, '+');
                    return sb.toString();
                case '+':
                    sb.setCharAt(i, '-');
                    i--;
                    break;
            }
        }
        //overflow quindi aggiungo +
        return "+" + sb;
    }
}