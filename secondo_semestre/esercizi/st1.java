public static long st1( int n, int k ) { // 0 ≤ k ≤ n
    if ( k == n ) {
        return 1;
    } else if ( k == 0 ) {
        return 0;
    } else {
        return ( (n-1)*st1(n-1,k) + st1(n-1,k-1) );
    }
}