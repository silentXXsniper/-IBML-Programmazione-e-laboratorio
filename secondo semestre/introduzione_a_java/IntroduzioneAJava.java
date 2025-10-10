public class IntroduzioneAJava
{

//    (define sup-tot-cilindro  ;val:reale
//     (lambda (r h)            ;r,h:reali positivi
//       (* (* 2 pi r)(+ r h))
//     ))

    public static double suptotCilindro(double r,double h){
        return (2*Math.PI*r)*(r+h);
    
    }
  
//   (define plurale-sm
//    (lambda (sm)
//      (string-append (radice-sost sm)"i")
//    ))

//    (define radice-sost
//     (lambda (s)
//       (substring s 0 (- (string-length s)1))
//     ))

     public static String pluraleSm(String sm){
           return radiceSost(sm)+"i";
     }
        
     public static String radiceSost(String s){
           return s.substring(0,s.length()-1);
     }

    
//   (define plurale-sf
//    (lambda (sf)
//      (string-append (radice-sost sf)"i")
//    ))

//    (define radice-sost
//     (lambda (s)
//       (substring s 0 (- (string-length s)1))
//     ))

     public static String pluraleSf(String sf){
           return radiceSost(sf)+"e";
     }
     
//    (define femminile?
//     (lambda (s)
//       (string=? (substring s (- (string-length s)1)"a")
//    )))

    public static boolean femminile(String s){
        return (s.substring(s.length()-1)).equals("a");
    }
     
//    (define femminile?
//     (lambda (s)
//       (string=? (substring s (- (string-length s)1)"a")
//    )))

     public static boolean femminile2(String s){
         return (s.charAt(s.length()-1))=='a'; 
     }
     
     /*
      (define plurale
       (lambda (s)
        (if (femminile? s)
            (plurale-sf s)
            (plurale-sm s)
        )))
      */
     public static String plurale(String s){
         if (femminile(s)){
             return pluraleSf(s);
            }else{
             return pluraleSm(s);
            }
        }
    // oppure
    public static String plurale2(String s){
     return (femminile(s)? pluraleSf(s) : pluraleSm(s));
    }
    
    /*
     dimensione foglio an
     (define
     (lambda (k)
     (if (< k 2)
          (if (= k 0) S0 S1)
          (/ (S (- k 2)) 2)
          )))
     (define S0 (* 100 (expt 2 1/4)))
     (define S1 (* 100 (expt 2 -1/4)))
     
    */
    public static double s(int k){ //int indica che la variabile è un numero intero
     if (k<2){
        if(k==0){
         return S0;
        }else{
         return S1;
        }
        }else{
        return s(k-2)/2;
        }
    }
    //oppure
    public static double s2(int k){
    if (k==0){
     return S0;
    }else if(k==1){
     return S1;
    }else{
     return s(k-2)/2;
    }
    }
    
    public static final double S0 = 100 * Math.pow(2, 0.25);
    public static final double S1 = 100 * Math.pow(2, -0.25);
    
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
     int k = btr.length()-1;
     if(k==0){
        return btdVal(btr);
        }else{
        return (3* btdVal(btr.substring(0,k)))+(btdVal(btr.substring(k)));
      }
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
     (define fibonacci
     (lambda (n)
       (if (< n 2)
           1
           (+ (fibonacci (- n 2)) (fibonacci (- n 1)))
           )))
     */
    public static int fibonacci(int n){ //sarebbe meglio usare "long" perchè cresce velocemente
                              // long si usa quando l'intero supera i 2 miliardi, sennò uso int
     if(n<2){
        return 1;
        }else{
        return fibonacci(n-2)+fibonacci(n-1); 
        }
    }
  }