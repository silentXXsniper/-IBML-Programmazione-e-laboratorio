import java.util.ArrayList;
import java.util.Scanner;
/**
 * Dati in input:
 *  String btr = rappresentazione di un numero intero non negativo in btr
 *  int    n   = numero interno non negativo
 * Restituisce in output la lista di n numeri consecutivi a partire dal numero rappresentato da btr;
 * 
 * Se n = 5 e btr rappresenta 3, restituire una lista (in btr) di 3, 4, 5, 6, 7.
 */
public class Btr
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Inserisci il numero in btr: ");
        String inputBtr = input.nextLine();
        
        System.out.println("Inserisci n: ");
        int inputN = input.nextInt();
        
        // Risultato corretto
        System.out.println(btrConsecutives(inputBtr, inputN));
        
        // Verifica sperimentale del funzionamento delle procedure pubbliche di StringSList
        ArrayList<String> list1 = btrConsecutives("+-", 5);
        ArrayList<String> list2 = btrConsecutives("++", 3);
        ArrayList<String> list3 = btrConsecutives("+..-", 2);
        
        // Costruttori e toString()
        StringSList null_stringslist = new StringSList();
        System.out.println("null string: " + null_stringslist.toString());
        
        StringSList stringsList1 = new StringSList(list1.get(1), null_stringslist);
        System.out.println("stringsList1 (with something): " + stringsList1.toString());
        
        // isNull()
        null_stringslist.isNull();
        System.out.println("is null_stringslist null? " + null_stringslist.isNull());
        StringSList stringsList2 = new StringSList(list2.get(2), null_stringslist);
        System.out.println("is stringsList2 null? " + stringsList2.isNull());
        
        // car() e cdr()
        System.out.println("null.car(): " + null_stringslist.car());
        System.out.println("null.cdr(): " + null_stringslist.cdr());
        System.out.println("some.car(): " + stringsList1.car());
        System.out.println("some.cdr(): " + stringsList1.cdr());
        
        // cons(...)
        StringSList stringsList3 = stringsList1.cons(stringsList2.toString());
        System.out.println("cons of " + stringsList1.toString() + " with " + stringsList2.toString()+ ": " + stringsList3.toString());
        
        // length(...)
        System.out.println("length of previously created stringsList: " + stringsList3.length());
        
        // listRef(...)
        System.out.println("element at index 1 of previous stringsList: " + stringsList3.listRef(1));
        
        // equals(...)
        System.out.println("a = a?: " + stringsList2.equals(stringsList2));
        System.out.println("a = b?: "+ stringsList2.equals(stringsList3));
        
        // append(...)
        System.out.println("append stringsList3 to stringsList2: " + stringsList2.append(stringsList3));
        
        // reverse()
        StringSList stringsList2reverse = stringsList2.reverse();
        System.out.println("reversed stringsList2: " + stringsList2reverse.toString());
    }

    private static ArrayList<String> btrConsecutives(String btr, int n)
    {
        return btrConsecutivesSupp(btr, n, new ArrayList<String>());
    }
    
    private static ArrayList<String> btrConsecutivesSupp(String btr, int n, ArrayList<String> list)
    {
        if(n == 1)
        {
            list.add(btr);
            return list;
        }
        else
        {
            list.add(btr);
            return btrConsecutivesSupp(btrSucc(btr), (n - 1), list);
        }
    }
    
    private static String btrSucc(String btr)
    {
        int n = btr.length();
        String lsd = btr.substring(n-1);
        
        if(n == 1)
        {
            if(lsd.equals("+"))
                return "+-";
            else
                return "+";
        }
        else
        {
            String pre = btr.substring(0, (n-1));
            
            if(lsd.equals("+"))
                return btrSucc(pre)+"-";
            else
                return pre+(lsd.equals("-") ? 
                "." : "+");
        }
    }
}
