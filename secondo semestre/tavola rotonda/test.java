
/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class test
     */
    public static IntSlist intervallo(int inf, int sup)
    {
        // initialise instance variables
        if(inf>sup){
            return intSList.NULL_INTLIST;
        }else{
            return intervallo (inf+1, sup).con(inf);
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static int gFlavio(int n)
    {TavRotonda tav=new TavRotonda(n);
        while(tav.numCavalieri()>1){
            tav=tav.serve();
            tav=tav.passa();
        }
        return tav.cavConBrocca();
    }
}
