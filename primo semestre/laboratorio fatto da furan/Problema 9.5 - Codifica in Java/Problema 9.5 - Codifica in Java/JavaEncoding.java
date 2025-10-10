
/**
 * FATTO
 * 
 * Codifica Java delle procedure btr-succ e ones-complement definite
 * nel file recursion_strings.scm
 * 
 * btr-succ: data in input una stringa che rappresenta un intero
 * in btr, restituisce la stringa btr rappresentante il numero successivo.
 * 
 * ones-complement: data in input una stringa binaria restituisce
 * il complemento a uno.
 * 
 */
public class JavaEncoding
{
    public static void main(String[] args) 
    {
        System.out.println("Complemento a 1 di 010010: " + onesComplement("010010"));
        System.out.println("Rappresentazione del numero successivo a +-: " + btrSucc("+-"));
    }

    // btr-succ
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
    // bit-complement
    private static String bitComplement(String bit)
    {
        if(bit.equals("1"))
            return "0";
        else
            return "1";
    }
    // ones-complement
    /*
     * (define ones-complement  ; val: stringa di 0/1
          (lambda (bin)          ; bin: stringa di 0/1
            (if (string=? bin "")
                ""
                (string-append
                 (ones-complement (substring bin 0 (- (string-length bin) 1)))
                 (bit-complement (substring bin (- (string-length bin) 1)))
                 ))
            ))
       */
    private static String onesComplement(String bin)
    {
        if(bin.equals(""))
            return "";
        else 
        {
            return onesComplement(bin.substring(0, (bin.length()-1))) + bitComplement(bin.substring(bin.length()-1));
        }
    }
}
