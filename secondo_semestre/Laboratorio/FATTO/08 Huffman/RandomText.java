import huffman_toolkit.*;
public class RandomText {
    public static void randomDocument ( String name ) {
        OutputTextFile out = new OutputTextFile( name );
        int length = 5000;
        for ( int i = 0; i < length; i++ ) {
            out.writeChar( (char) (128 * Math.random()) );
        }
        out.close();
    }
}
