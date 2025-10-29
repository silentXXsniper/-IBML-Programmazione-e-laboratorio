public class ProgrammazioneImperativa
{
    /*massimo comun divisore
    (define mcd 
    (lambda (x y)
       (cond ((= x y)
               x)
              ((< x y)
                (mcd x (- y x))
                (else   : x>y
                  (mcd (- x y) y))
                  ))))
    */
    
   public static int mcd (int x, int y){ // x,y>0
      while(x != y){
         if (x<y){
            y = y-x;
            }else{
            x = x-y;
            }
        }
        return x;
    }
    
    /*
     minimo comun multiplo
     */
    public static int mcm(int x, int y){
        int m = x;
        while(m % y >0){
          m = m + x;
        }
        return m;
    }
    
    /*
     (define primo?
      (lambda (n)
       (if (even? n)
           (= n 2)
           ...
       
     (define divisori-dispari-in?
      (lambda (n a b)
        (cond ((> a b)
               false)
               ((= (remainder n a) 0)
               true)
               (else
                 (divisori-dispari-in? n (+ a 2) b))
        )
        ))
     */
    public static boolean primo(int n){
         if(n % 2 == 0){ //n pari
             return (n == 2);          
            }else{
             int k = 3;
             while(k*k <= n){
                  if(n % k == 0){ //non primo
                     return false; 
                    }else{
                     k = k + 2;
                    }
                }
                return true;
            }
    }   
    
    //list primi
    public static void listaPrimi(int max){
      for(int n=2; n<=max; n=n+1){
          if(primo(n)){
            System.out.print("  "+ n);
            }
        }
    }
    
    /*
     (define btr-val
     (lambda (btr)
      (let (
            (k (- (string-length btr) 1))
            )
        (if (= k 0)
            (btd-val btr)
            (+(* 3 (btr-val (substring btr 0 k)))
              (btd-val (substring btr k))
              ))
        )))
        
     (define btd-val
     (lambda (btd)
      (cond ((string=? btd "-") -1)
            ((string=? btd ".") 0)
            ((string=? btd "+") +1)
             )))
     */
    public static int btrVal(String btr){
      int n = btr.length();
      int val = 0;
      for(int i=0; i<n; i=i+1){
         val = 3 * val + btdVal(btr.substring(i, i+1));
        }
        return val;
    }
    
    public static int btdVal(String btd){
     if(btd.equals("-")){
        return -1;
        }else if(btd.equals(".")){
        return 0;
        }else{
        return +1;
        }
    } 
    
    /* 
     (define ufo
     (lambda (x)
        (cond ((= x 1)
                1)
                ((even? x)
                (- (* 2 (ufo (quotient x 2))) 1))
                (else
                    (+ (* 2 (ufo (quotient x 2))) 1)))))
                    
     |-| 1| 1| 3| 1| 3| 5| 7| 1| 3| 5| 7| 9|
      0  1  2  3  4  5  6  7  8  9 10  11 12
     */
    
    public static int ufo(int n){ //n>0
      int[] u = new int[n+1]; //int[]=array(lista)di numeri interi
                              //new= per creare un nuovo array
                              //n+1 =numero di caselle da mettere se voglio rapppresentare 
                              //     n numeri (perchè c'è ancche lo 0)
      u[1]=1;  //1= indice della casella che prendo in considerazione
      for(int x=2; x<=n; x=x+1){
         if(x%2==0){
            u[x]= 2* u[x/2]-1;
            }else{
            u[x]= 2* u[x/2]+1;
            }
        }
        return u[n];
    }
}