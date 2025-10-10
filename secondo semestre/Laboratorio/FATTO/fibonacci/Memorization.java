/**
 * Write a description of class fib here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Memorization
{
    

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int fib(int n)
    {
        if (n < 2){
            return 1;
       }else{
        return (fib(n-2)+fib(n-1));
        }
    }
    
    public static long fibMem(int n){
        long[] mem = new long[n+1];
        for(int i=0; i<=n; i=i+1){
            mem[i] = UNKNOWN;
        }
        return fibRec(n,mem);
    }
    
    public static long fibRec(int n, long[] mem){
        if(mem[n]==UNKNOWN){
            if(n < 2){
                mem[n]=1;
            }else{
                mem[1]=(fibRec(n-2, mem)+fibRec(n-1,mem));
            }
        }
        return mem[n];
    }
    private static final int UNKNOWN=0;
}
