import java.util.*;
import huffman_toolkit.*;
public class Huffman {
  private static final int CHARS = InputTextFile.CHARS;
  public static int[] charHistogram( String src ) {
    InputTextFile in = new InputTextFile( src );
    int[] freq = new int[ CHARS ];// istogramma d frequenze dei caratteri
    for ( int c=0; c<CHARS; c=c+1 ) {// inizializzazione istogramma
      freq[c] = 0;
    }
    while ( in.textAvailable() ) {// scansione: frequenze dei caratteri
      int c = in.readChar();// (ulteriore) occorrenza di c
      freq[c] = freq[c] + 1;
    }
    in.close();
    return freq;
  }
  public static Node huffmanTree( int[] freq ) {
    NodeQueue queue = new NodeQueue();
    for ( int c=0; c<CHARS; c=c+1 ) {// per ogni carattere utilizzato
      if ( freq[c] > 0 ) {
        Node n = new Node( (char) c, freq[c] );// creazione del nodo corrispondente
        queue.add( n );// e inserimento nella coda
    }}
    while ( queue.size() > 1 ) {// finche' c'e' piu' di un nodo nella coda
      Node l = queue.poll();// rimozione dei due nodi di peso minimo...
      Node r = queue.poll();
      Node n = new Node( l, r );// ...costruzione di un (sotto) albero
      queue.add( n );// e inserimento nella coda
    }
    return queue.poll();// radice dell'albero di Huffman
  }
  public static String[] huffmanCodesTable( Node root ) {
    String[] codes = new String[ CHARS ];// tabella dei codici di Huffman    
    fillTable( root, "", codes );// compilazione della tabella
    return codes;
  }
  private static void fillTable( Node n, String code, String[] codes ) {
    if ( n.isLeaf() ) {
      codes[ n.character() ] = code;// nodo foglia: code = codice del carattere
    } else {
      fillTable( n.left(),  code+"0", codes );// figlio sinistro: bit 0 nel codice
      fillTable( n.right(), code+"1", codes );// figlio destro:   bit 1 nel codice
    }
  }
  public static String flattenTree( Node n ) {
    if ( n.isLeaf() ) {// foglia: codifica del carattere
      char c = n.character();
      if ( (c == '\\') || (c == '@') ) {
        return ( "\\" + c );// caratteri speciali: \, @
      } else {
        return ( "" + c );// altri caratteri
      }
    } else {
      return ( "@"// @
             + flattenTree( n.left() )// codifica lineare del sottoalbero sinistro
             + flattenTree( n.right() )// codifica lineare del sottoalbero destro
               );
    }
  }
  public static void compress( String src, String dst ) {
    int[] freq = charHistogram( src );// istogramma delle frequenze
    Node root = huffmanTree( freq );// albero di Huffman
    int count = root.weight();// numero complessivo di caratteri
    String[] codes = huffmanCodesTable( root );// tabella (array) dei codici
    InputTextFile in = new InputTextFile( src );// documento origina 
    OutputTextFile out = new OutputTextFile( dst );// documento compresso
    out.writeTextLine( "" + count );// intestazione del documento compresso
    out.writeTextLine( flattenTree(root) );
    for ( int j=0; j<count; j=j+1 ) {// scansione: codifica dei caratteri
      char c = in.readChar();
      out.writeCode( codes[c] );
    }
    in.close();
    out.close();
  }
  public static Node restoreTree( InputTextFile in ) {
    char c = (char) in.readChar();// carattere dell'intestazione
    if ( c == '@' ) {// '@' ?
      Node left  = restoreTree( in );// sottoalbero sinistro
      Node right = restoreTree( in );// sottoalbero destro
      return new Node( left, right );// nodo genitore
    } else {
      if ( c == '\\' ) {
        c = (char) in.readChar();// carattere speciale (skip)
      }
      return new Node( c, 0 );// foglia
    }
  }
  // 7.
  // Decompressione
  // src: nome del documento compresso
  // dst: nome del documento ripristinato
  public static void decompress( String src, String dst ) {
    InputTextFile in = new InputTextFile( src );// documento compresso
    int count = Integer.parseInt( in.readTextLine() );// decodifica dell'intestazione
    Node root = restoreTree( in );
    String dummy = in.readTextLine();// codici caratteri dopo il fine-linea
    OutputTextFile out = new OutputTextFile( dst );// documento ripristinato
    for ( int j=0; j<count; j=j+1 ) {// scansione: decodifica dei caratteri
      char c = decodeNextChar( root, in );
      out.writeChar( c );
    }
    in.close();
    out.close();
  }
  // Decodifica del carattere successivo
  // root: nodo radice dell'albero di Huffman
  // in:   documento compresso
  private static char decodeNextChar( Node root, InputTextFile in ) {
    Node n = root;// percorso lungo l'albero di Huffman
    do {
      if ( in.readBit() == 0 ) {
        n = n.left();// bit 0: figlio sinistro
      } else {
        n = n.right();// bit 1: figlio destro
      }
    } while ( !n.isLeaf() );// fino al raggiungimento di una foglia
    return n.character();// carattere individuato
  }
  public static void main( String[] args ) {
    System.out.println( "huffman coding:" );
    if ( args[0].equals("compress") ) {
      System.out.println( "compressing..." );
      compress( args[1], args[2] );
    } else if ( args[0].equals("decompress") ) {
      System.out.println( "decompressing..." );
      decompress( args[1], args[2] );
    } else {
      System.out.println( "no operation specified: 1st parameter should be either 'compress' or 'decompress'" );
    }
    System.out.println( "done." );
  }
}  // class Huffman


