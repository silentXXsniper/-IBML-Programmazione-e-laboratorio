 public static int combCountIter( IntSList s ) {

 int count = 0; // contatore delle soluzioni
 int x = 0;
 int y = 0;

 Stack<IntSList> stack = new Stack<IntSList>();
 IntSList frame = s.cons(y).cons(x);
 stack.push( frame );

 do {
 frame = ;
 s = ;
 x = ;
 y = ;

 if ( s.isNull() ) {


 } else {
 int e = s.car();
 s = s.cdr();
 stack.push( );
 ;
 }
 } while ( );

 return count;
 }