import huffman_toolkit.*;
/**
 * Aggiungi qui una descrizione della classe EsempiIO
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class EsempiIO
{
    public static int copiaFile(String src, String dst){
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out= new OutputTextFile(dst);
        
        int count = 0;
        
        for(int i=0; i<704; i++){
            int b =in.readBit();
            out.writeBit(b);            
        }
        
        while(in.textAvailable()){
        //while(in.bitsAvailable()){
            
            //String linea=in.readTextLine();
            //out.writeTextLine(linea);
            char c= in.readChar();
            out.writeChar(c);
            //int b=in.readBit();
            //out.writeBit(b);
            //String cod =in.readCode(7);
            //out.writeCode(cod);
             
            count++;
        }
        in.close();
        out.close();
        
        return count;
    }
}
