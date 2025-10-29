
public class Test
{
    public static int gFlavio(int n){
        TavRotonda tv = new TavRotonda(n);
        while(tv.numCavalieri() > 1){
            tv.serve();
            tv.passa();
        }
        return tv.cavConBrocca();
    }
}
