public class IntroduzioneAJava {


    public static void main(String[] args){
        Ststem.out.println(IntroduzioneAJava.supTotcilindro(5, 8))
    }
    //questo è un commento

    /*
   (define sup-tot-cildinro
   (lambda (r h)
   (*(* 2 pi r)(+ r h))
   ))
    */
  public static double supTotcilindro(double r, double h ) { //public serve a far funzionare la cosa anche all'interno di altre classi
    return(2 * Math.PI * r)*(r + h);
  }  

  public static String pluraleSm(String sm){

    radiceSost(sm) + "i";
  }

  public static String radiceSost( Sring s){
    return s.substring (0, s.length()- 1);
  }

}