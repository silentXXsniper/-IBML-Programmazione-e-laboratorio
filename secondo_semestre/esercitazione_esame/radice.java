public class radice { 
public static int f( int n ) { // Pre: n ≥ 0

 int x = 0  ;
 int y = 1  ;
 int z = y  ;

 while ( z <= n ) { // Inv: 0 ≤ x2 ≤ n, y = 2x + 1, z = x2 + y
 // Term:
 x = x + 1;
 y = y + 2;
 z = y + z;
 }

 return x; // Post: x = ⎣√ n ⎦
 }
}