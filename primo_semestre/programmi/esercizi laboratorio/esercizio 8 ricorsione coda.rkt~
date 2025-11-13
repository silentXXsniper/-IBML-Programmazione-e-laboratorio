;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 8 ricorsione coda|) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
(define hanoi-moves      ; val: lista di coppie
  (lambda (n)            ; n > 0 intero
    (hanoi-rec n 1 2 3) 
    ))

(define hanoi-rec        ; val: lista di coppie
  (lambda (n s d t)      ; n intero, s, d, t: posizioni
    (if (= n 1)
        (list (list s d))
        (let ((m1 (hanoi-rec (- n 1) s t d)) 
              (m2 (hanoi-rec (- n 1) t d s))
              )
          (append m1 (cons (list s d) m2)) 
          ))
    ))

(define hanoi-disks
  (lambda (n k)
    (conta-dischi 0 0 0 0 n k)
                   )
    )
  
(define conta-dischi
  (lambda (numero-ricorsione ndd1 ndd2 ndd3 n k)  ;nms = numero di dischi
    (cond
      [(= k 0) (list (list 1 n) (list 2 0) (list 3 0))]  ;restituisce il caso base
      [else (calcolo-disco n 0 0 n k numero-ricorsione (hanoi-moves n))]
      )
    ))

(define calcolo-disco
  (lambda (ndd1 ndd2 ndd3 n k numero-ricorsione stringa-controllata)
    (if (= numero-ricorsione k) (list (list 1 ndd1) (list 2 ndd2) (list 3 ndd3))
        (calcolo-disco (cond [(=(car (car stringa-controllata)) 1) (- ndd1 1)]
                             [(=(cadr (car stringa-controllata)) 1)(+ ndd1 1)]
                             [else ndd1])
                       (cond [(=(car (car stringa-controllata)) 2) (- ndd2 1)]
                             [(=(cadr (car stringa-controllata)) 2)(+ ndd2 1)]
                             [else ndd2])
                       (cond [(=(car (car stringa-controllata)) 3) (- ndd3 1)]
                             [(=(cadr (car stringa-controllata)) 3)(+ ndd3 1)]
                             [else ndd3])
                       n k (+ numero-ricorsione 1)(cdr stringa-controllata)
                       )
        )
    ))
        
                                                                               

(define hanoi-picture
  (lambda (n t)
    (if (= t 0)(above(mini-torre n n 1 0 0)(towers-background n))
       (calcolo-spostamenti (generatore-di-stringa-iniziale n) '() '() t (hanoi-moves n) n n)
       )
  ))

(define visualizzazione-torre
  (lambda (d n p t rip-eff)
    (cond [(= d 0)(towers-background n)]
          [(= d 1)(disk-image d n p t)]
          [else (above  (visualizzazione-torre (- d 1) n 1 (+ t 1) (+ rip-eff 1))(disk-image d n p t))]
          )
    ))
  
(define mini-torre
  (lambda (d n p t rip-eff)
    (cond [(= d 0)(towers-background n)]
          [(= d 1)(disk-image d n p t)]
          [else (above  (mini-torre (- d 1) n 1 (+ t 1) (+ rip-eff 1))(disk-image d n p t))]
          )
    ))

(define calcolo-spostamenti
  (lambda (x y z seq  seq-hanoi altezza-asta n-dischi); x= rappresentazione prima asticella y= rappresentazione seconda asticella z= rappresentazione terza asticella  seq-hanoi= la lista prodotta da hanoi moves   altezza-asta= quante deve essere alta un'asta
    (if (= seq 0)(above(formazione-torre (reverse x) (reverse y) (reverse z) 0 0 0 altezza-asta n-dischi)(towers-background altezza-asta))
     (cond [(=(car(car seq-hanoi)) 1) (calcolo-spostamenti (cdr x) (if(=(car(cdr(car seq-hanoi)))2)
                                                                      (cons (car x) y)y)
                                                           (if(=(car(cdr(car seq-hanoi)))3)
                                                              (cons (car x) z)
                                                              z)
                                                           (- seq 1) (cdr seq-hanoi) altezza-asta n-dischi)]
           [(=(car(car seq-hanoi)) 2) (calcolo-spostamenti (if(=(car(cdr(car seq-hanoi)))1)
                                                              (cons (car y) x)x) (cdr y) (if(=(car(cdr(car seq-hanoi)))3)
                                                                                            (cons (car y) z)
                                                                                            z)
                                                                                 (- seq 1) (cdr seq-hanoi) altezza-asta n-dischi)]
           [(=(car(car seq-hanoi)) 3) (calcolo-spostamenti (if(=(car(cdr(car seq-hanoi)))1)
                                                              (cons (car z) x)
                                                              x) (if(=(car(cdr(car seq-hanoi)))2)
                                                                    (cons (car z) y)
                                                                    y) (cdr z) (- seq 1) (cdr seq-hanoi) altezza-asta n-dischi)]
          ))
    ))
          
       
(define formazione-torre
  (lambda (x y z hx hy hz h n-dischi)
    (if(not(null? x))
       (above(formazione-torre (cdr x) y z (+ hx 1) hy hz h n-dischi)(disk-image (car x) n-dischi 1 hx))
       (if(not(null? y))
          (above(formazione-torre  x (cdr y) z hx (+ hy 1) hz h n-dischi)(disk-image (car y) n-dischi 2 hy))
          (if(not(null? z))
             (above(formazione-torre  x y (cdr z) hx hy (+ hz 1) h n-dischi)(disk-image (car z) n-dischi 3 hz))
            (disk-image 0 1 0 0) 
       )))


    ))
                                                                      

(define generatore-di-stringa-iniziale  ;serve a restituire la lista dei dischi numerati sulla prima asta dato un numero n di dischi da generare
  (lambda (n)
    (build-list n add1)
    ))

(define hanoi-animation-out
  (lambda (x n)    
     (cons  (hanoi-picture n 0) x)               
    ))
(define hanoi-animation  ;viste le potenzialità del codice ho deciso di implementeare una funzione che visualizza graficamente tutti i passaggi necessari per arrivare alla posizione k di una torre di dischi n
  (lambda (n k)          ;il processo diventa molto intensivo esponenzialmente con imput più elevati, personalmente consiglio 7 70 come imput, inquanto producucono un risultato soddisfacente in tempi accettabili
    (hanoi-animation-process n k '() ;non funziona per k < x^n - 1
          )))
(define hanoi-animation-process
  (lambda (n k x)
         (if (not(= k 0))
             (hanoi-animation-process n (- k 1) (append x (list(hanoi-picture n k))))
     (hanoi-animation-out (reverse x) n))
             ))
                      
;Francesco Davini 173000
(define esempi ;per eseguire tutti gli esempi rapidamente basta eseguire il comando esempi
  (list 
"-----------------------------------------"
""
"alcuni esempi di hanoi-disk in funzione"
""
"(hanoi-disks 3 0)" (hanoi-disks 3 0)
""
"(hanoi-disks 3 1)"(hanoi-disks 3 1)
""
"(hanoi-disks 3 2)"(hanoi-disks 3 2)
""
"(hanoi-disks 3 3)"(hanoi-disks 3 3)
""
"(hanoi-disks 3 4)"(hanoi-disks 3 4)
""
"(hanoi-disks 3 5)"(hanoi-disks 3 5)
""
"(hanoi-disks 3 6)"(hanoi-disks 3 6)
""
"(hanoi-disks 3 7)"(hanoi-disks 3 7)
""
"(hanoi-disks 5 13)"(hanoi-disks 5 13)
""
"(hanoi-disks 15 19705)"(hanoi-disks 15 19705)
""
"(hanoi-disks 15 32767)"(hanoi-disks 15 32767)
""
"-----------------------------------------"
""
"esempio di hanoi-picture in funzione"
""
"(hanoi-picture 5 0)"
(hanoi-picture 5 0)
"(hanoi-picture 5 13)"
(hanoi-picture 5 13)
"(hanoi-picture 5 22)"
(hanoi-picture 5 22)
"(hanoi-picture 5 31)"
(hanoi-picture 5 31)
"(hanoi-picture 15 19705)"
(hanoi-picture 15 19705)
"-----------------------------------------"
))