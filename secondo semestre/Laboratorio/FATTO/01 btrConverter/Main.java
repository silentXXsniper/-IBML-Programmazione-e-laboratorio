public class Main {
    public static void main(String[] args) {
        // Costruisci lista BTR a partire da "+-" con 5 elementi
        StringSList btrs = BtrConverter.btrRange("+-", 5);
        System.out.println("btrs = " + btrs);
        // Output previsto: ( +- +. ++ +-- +-. )
        StringSList test1 = btrs.append(btrs.reverse().cdr());
        System.out.println("btrs.append(btrs.reverse().cdr()) = " + test1);
        //( +- +. ++ +-- +-. +-- ++ +. +- )
        // Test 2: btrs.append( btrRange(btrs.listRef(btrs.length()-1), btrs.length()+1).cdr() )
        String last = btrs.listRef(btrs.length() - 1);
        StringSList newRange = BtrConverter.btrRange(last, btrs.length() + 1).cdr();
        StringSList test2 = btrs.append(newRange);
        System.out.println("btrs.append(btrRange(last, btrs.length()+1).cdr()) = " + test2);
        // Output previsto: ( +- +. ++ +-- +-. +.. ++- ++. +++ +--- +--. )
    }
}
