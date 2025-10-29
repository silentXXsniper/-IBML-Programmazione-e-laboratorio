public class BtrRange{

    public static String btrRange(String btr,int n){
        return calcoloSerie(btr, n, "(" );
    }


    public static String calcoloSerie(String a, int count, String b){
        if(count==0)
            return b+")";
        if(b.equals("("))    
        return calcoloSerie((aggiungi1(a)),count-1,b+a);
        else 
        return calcoloSerie((aggiungi1(a)),count-1,b+" , "+a);
    }

    public static String aggiungi1(String a){
        if(!(a=="")){
            if((a.substring(a.length()-1)).equals("-"))
                return a.substring(0, a.length()-1) + ".";
            else if((a.substring(a.length()-1)).equals("."))
                return a.substring(0, a.length()-1) + "+";
            else
                return aggiungi1(a.substring( 0,a.length()-1))+ "-";
        }else return "+";
    }
    
    
    
    
    
    
    /*codice vecchio non utilizzato
        public static int btrRec(String a){
        return convertiNorm(StringReverse(a),0);
    }

    //importante!! ric++ é post-incremento
    // quindi significa che in un caso ricorsivo bisogna utilizzare ric+1
    public static int convertiNorm(String a, int ric){
        if(!(a == "")){
            String controllo=a.substring(0, 1);
            if(controllo.equals("+"))
                return convertiNorm(a.substring(1), ric+1)+(int) Math.pow(3, ric) * 1;   
            else if(controllo.equals("-"))
                return convertiNorm(a.substring(1), ric+1)+(int) Math.pow(3, ric) * -1;
            else
                return convertiNorm(a.substring(1), ric+1); 
        }else return 0;
    }

    public static String StringReverse(String a){
        return StringReverseRec(a,""); 
    }

    public static String StringReverseRec(String a, String b){

        if(!(a == ""))
            return StringReverseRec(a.substring(0, a.length()-1),b+a.substring(a.length()-1));
        else
            return b;
    }
    //ora il primo input è convertito in numero naturale

    public static String ConvertiBase (String a, String b){ //sempre usare .equals
        if(!(a=="")){
            String primo=a.substring(0,1);
            if(primo.equals("+"))
                return ConvertiBase(a.substring(1), b +"2");
            else if(primo.equals("."))
                return ConvertiBase(a.substring(1), b +"1");
            else
                return ConvertiBase(a.substring(1), b +"0");
        }else return b;
    }

    public static String ConvertiBase2 (String a, String b){
        if(!(a=="")){
            String primo=a.substring(0,1);
            if(primo.equals("2"))
                return ConvertiBase2(a.substring(1), b +"+");
            else if(primo.equals("1"))
                return ConvertiBase2(a.substring(1), b +".");
            else
                return ConvertiBase2(a.substring(1), b +"-");
        }else return b;
    }
    */
}