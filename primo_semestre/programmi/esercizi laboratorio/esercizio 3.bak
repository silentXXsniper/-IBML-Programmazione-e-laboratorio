;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 3|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks")) #f)))
; problema 3
; 4 e 5 Novembre 2024
;il seguente codice permette di trasformare un imput da binario a decimale

(define bin-rep->number
  (lambda (n) ; n= imput dato
    (cond [(null? n) 0] 
          [(char=? (string-ref n 0) #\+)(dove-sta-il-punto (substring n 1 (string-length n)) (substring n 1 (string-length n)) 0 0)] ;controlla se è presente "+" nella stringa fornita, imposta "negativo?" su 0
          [(char=? (string-ref n 0) #\-)(dove-sta-il-punto (substring n 1 (string-length n)) (substring n 1 (string-length n)) 0 1)] ;controlla se è presente "-" nella stringa fornita, imposta "negativo?" su 1
          [else(dove-sta-il-punto n n 0 0)] ;controlla la posizione di . salvando in "salvato" la stringa originale che verrà poi restituita a conversione-bin-dec
  )))

(define dove-sta-il-punto
  (lambda (n salvato ric negativo?) ; n= la stringa ancora da controllare   salvato= la stringa originale   ric= numero di ricorsione   negativo?= ricorda se il numero iniziale fosse negativo o meno
    (if (string=? n "")(conversione-bin-dec salvato 0 0 negativo?)
      (if (char=? (string-ref n (- (string-length n) 1)) #\.)(conversione-bin-dec salvato 0 ric negativo?)
          (dove-sta-il-punto (substring n 0 (-(string-length n) 1)) salvato (- ric 1) negativo?)
        )))
  )

(define conversione-bin-dec  ;verifica quale sia l'ultimo carattere della stringa rimanente
  (lambda (x n ric negativo?)  ;x= la stringa ancora da controllare   n=numero convertito fino ad ora    ric= numero di ricorsione   negativo?= ricorda se il numero iniziale fosse negativo o meno
    (cond [(=(string-length x)0) (aggiungi-segno n negativo?)]  ; se il numero da convertire è lungo zero (ha finito) richiama la funzione per inserire il segno se necessario
          [(char=?(string-ref x (- (string-length x) 1))#\.)(conversione-bin-dec (substring x 0 (- (string-length x) 1)) (+ n 0.0) ric negativo?)] ;aggiunge 0.0 al munero decimale se è stato trovato 
          [else (calcolo-bin x n ric negativo?)] ;chiama la funzione che converte il numero binario in decimale

          )
    ))

(define calcolo-bin
  (lambda (x n ric negativo?)  ;x= la stringa ancora da controllare   n=numero convertito fino ad ora    ric= numero di ricorsione   negativo?= ricorda se il numero iniziale fosse negativo o meno
    (conversione-bin-dec (substring x 0 (- (string-length x) 1)) 
                         (if(char=?(string-ref x (- (string-length x) 1))#\1)(+ n (expt 2 ric))
                            n
                          )
                         (+ ric 1) negativo?))
  )

(define aggiungi-segno  ;aggiunge il segno negativo se era stato trovato all'inzio 
  (lambda (n negativo?)  ;  n= il numero convertito in decimale   negativo?= ricorda se il numero iniziale fosse negativo o meno
    (if (= negativo? 0) n
        (number->string(exact->inexact (string->number(string-append "-" (number->string n))) ))  ;per ottenere il risultato in forma decimale anzichè frazionaria
        )
    ))



;il seguente codice rielabora il programma precedente, estendendolo a qualsiasi tipo di base, fornita come stringa nell'imput


(define rep->number
  (lambda (b x) ; b= base della notazione  x= stringa da convertire in decimale
    (cond [(null? x) 0]
          ;[()]   ; andrebbe inserita una funziona per controllare se effettivamente tutti i caratteri dati appartengono alla base
          [(char=? (string-ref x 0) #\+)(dove-sta-il-punto-compl (substring x 1 (string-length x)) (substring x 1 (string-length x)) b 0 0)] ;controlla se è presente "+" nella stringa fornita, imposta "negativo?" su 0
          [(char=? (string-ref x 0) #\-)(dove-sta-il-punto-compl (substring x 1 (string-length x)) (substring x 1 (string-length x)) b 0 1)] ;controlla se è presente "-" nella stringa fornita, imposta "negativo?" su 1
          [else(dove-sta-il-punto-compl x x b 0 0)] ;controlla la posizione di . salvando in "salvato" la stringa originale che verrà poi restituita a conversione-bin-dec
    )    
  ))

(define dove-sta-il-punto-compl
  (lambda (n salvato base ric negativo?) ;    n= la stringa ancora da controllare   salvato= la stringa originale   base= la base iniziale   ric= numero di ricorsione   negativo?= ricorda se il numero iniziale fosse negativo o meno
    (if (string=? n "")(calcolo-base base salvato 0 0 0 negativo?)
      (if (char=? (string-ref n (- (string-length n) 1)) #\.)(calcolo-base base salvato 0 0 ric negativo?)
          (dove-sta-il-punto-compl (substring n 0 (-(string-length n) 1)) salvato base (- ric 1) negativo?)
        )))
  )


(define calcolo-base
  (lambda (b x lunghezza-base n ric negativo?) ; b= base   x= stringa da convertire in decimale    lunghezza-base= determina in un valore numerico il range della base   n= numero convertito fino ad ora   ric= numero della ricorsione   negativo?= ricorda se il numero iniziale fosse negativo o meno
    (conversione-in-decimali b x (string-length b) n ric negativo?)
    ))


(define conversione-in-decimali
  (lambda (b x lunghezza-base n ric negativo?) ; b= base   x= stringa da convertire in decimale    lunghezza-base= determina in un valore numerico il range della base   n= numero convertito fino ad ora   ric= numero della ricorsione   negativo?= ricorda se il numero iniziale fosse negativo o meno
    (cond [(=(string-length x)0) (aggiungi-segno n negativo?)]  ; se il numero da convertire è lungo zero (ha finito) richiama la funzione per inserire il segno se necessario
          [(char=?(string-ref x (- (string-length x) 1))#\.)(conversione-in-decimali b   (substring x 0 (- (string-length x) 1)) lunghezza-base (+ n 0.0) ric negativo?)] ;aggiunge 0.0 al munero decimale se è stato trovato 
          [else (calcolo-decim b x lunghezza-base n ric negativo?)] ;chiama la funzione che converte il numero binario in decimale
          )
    ))
    
(define calcolo-decim
  (lambda (b x lunghezza-base n ric negativo?)  ;b= base  x= la stringa ancora da controllare   n=numero convertito fino ad ora    ric= numero di ricorsione   negativo?= ricorda se il numero iniziale fosse negativo o meno
    (conversione-in-decimali b (substring x 0 (- (string-length x) 1)) lunghezza-base (+ n (* (controllo-posizione b (string-ref x  (- (string-length x) 1)) 0) (expt lunghezza-base ric)))  (+ ric 1) negativo?))
  )


(define controllo-posizione
  (lambda (b x conta) ; b= base   x= stringa da convertire in decimale  conta= posizione del carattere nella stringa (fino ad ora)
    (cond [(= (string-length b) 0) "ERRORE: inserire una stringa contenente solo i caratteri nella base"]  ;in caso si inserisca una stringa in b che non contiene caratteri inseriti in x darà come output un errore
          [(char=? (string-ref b 0) x) conta]
          [else (controllo-posizione (substring b 1 (string-length b)) x (+ conta 1))]
    )
 ))

;per eseguire rapidamente tutti gli esempi usare il comando esempi
(define esempi
  (list
"esempi prima richiesta"
""
"-------------------------"
""
"(bin-rep->number +1101)"
(bin-rep->number "+1101")
""
"(bin-rep->number 0)"
(bin-rep->number "0")
""
"(bin-rep->number 10110.011)"
(bin-rep->number "10110.011")
""
"(bin-rep->number -0.1101001)"
(bin-rep->number "-0.1101001")
""
"-------------------------"

""
""
"esempi seconda richiesta"
""
""
"-------------------------"
"(rep->number zu -uuzz)"
""
(rep->number "zu" "-uuzz")  
""
"(rep->number 0123 +21.1)"
(rep->number "0123" "+21.1")
""
"(rep->number 01234 -10.02)"
(rep->number "01234" "-10.02")
""
"(rep->number 0123456789ABCDEF 0.A)"
(rep->number "0123456789ABCDEF" "0.A")
""
"(rep->number 0123456789ABCDEF 1CF.0)"
(rep->number "0123456789ABCDEF" "1CF.0")
""
"-------------------------"
))