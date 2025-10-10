public class path{
 public static StringSList paths( String p, int i, int j ) {
    if ( (i > 0) || (j > 0) ) {
      char c = p.charAt( p.length()-i-j );
      StringSList x = ((i==0) || (c=='R')) ? NULL_LIST : map("D", paths(p,i-1,j));
      StringSList y = ((j==0) || (c=='D')) ? NULL_LIST : map("R", paths(p,i,j-1));
      return x.append( y );
    } else {
      return NULL_LIST.cons( "" );
    }
  }
  private static final StringSList NULL_LIST = new StringSList();
  
  private static StringSList map( String m, StringSList u ) {
    if ( u.isNull() ) {
      return NULL_LIST;
    } else {
      return map( m, u.cdr() ).cons( m+u.car() );
    }
  }
  
public static StringSList pathsDP(String p, int i, int j) {
    // tabella DP: ogni cella è una lista di stringhe
    StringSList[][] mem = new StringSList[i+1][j+1];

    for (int u = 0; u <= i; u++) {
        for (int v = 0; v <= j; v++) {
            if (u == 0 && v == 0) {
                // caso base: lista con stringa vuota
                mem[u][v] = NULL_LIST.cons("");
            } else {
                char c = p.charAt(p.length() - u - v);
                StringSList res = NULL_LIST;

                if (u > 0 && c != 'R' && mem[u-1][v] != null) {
                    res = res.append(map("D", mem[u-1][v]));
                }
                if (v > 0 && c != 'D' && mem[u][v-1] != null) {
                    res = res.append(map("R", mem[u][v-1]));
                }

                mem[u][v] = res;
            }
        }
    }

    return mem[i][j];
}



       }