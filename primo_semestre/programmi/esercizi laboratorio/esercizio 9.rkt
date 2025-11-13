;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 9|) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks") (lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks") (lib "drawings.ss.txt" "installed-teachpacks")) #f)))
;problema 9
;9 e 10 dicembre 2024

(define ALFABETO  ;65-88 (no 74 75 85 87)
  (list "A" "B" "C" "D" "E" "F" "G" "H" "I" "L" "M" "N" "O" "P" "Q" "R" "S" "T" "V" "X") ;convenzione: da 0 a 19
  )
(define alfabeto  ;65-88 (no 74 75 85 87)
  (list "a" "b" "c" "d" "e" "f" "g" "h" "i" "l" "m" "n" "o" "p" "q" "r" "s" "t" "v" "x") ;convenzione: da 40 a 59
  )
(define numeri
  (list 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
        40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59
        )
  )

(define trova-pos
  (lambda (a b x)
    (cond [(null? b) a]
          [(and(member a ALFABETO)(equal? a (car b))) (cond[(and(>= x 0)(<= x 19))x]
                                                            [(and(>= x 0)(>= x 19))(trova-pos a b(- x 20))]
                                                            [(< x 0)(trova-pos a b(+ x 20))]
                                                            )]
          [(and(member a alfabeto)(equal? a (car b))) (cond[(and(>= x 40)(<= x 59))x]
                                                            [(and(>= x 40)(>= x 59))(trova-pos a b(- x 20))]
                                                            [(< x 40)(trova-pos a b(+ x 20))]
                                                            )]

          [else(trova-pos a (cdr b) (+ 1 x)
                       )]
    )))

(define cifra
  (lambda (in alf x n)
    (if (null? in) (decifra x '())
        (let* ((pos (trova-pos (car in) alf n))
               (new-x (append x (list pos))))
          (cifra (cdr in) alf new-x n)))))

(define crittazione-cesare
  (lambda (input n)
    (cifra (explode input) (append alfabeto ALFABETO) '() n)
    ))


(define decifra
  (lambda (input out)
    (if (null? input)(decodifica(reverse out))
        (decifra (cdr input)(cons (trova-pos(car input)'() 0) out))
        )
    
   ))

(define decodifica
  (lambda (a)
    (if  (null? a) ""
    (let ((x (car a)))
    (cond 
          ((not(member (car a) numeri)) (string-append (car a) (decodifica (cdr a))))
          ((and(>= x 0)(<= x 19)) (string-append (list-ref ALFABETO x)(decodifica (cdr a))))
          ((and(>= x 40)(<= x 59)) (string-append (list-ref alfabeto (- x 40))(decodifica (cdr a))) )

                 
          )
           ))
    )
  )
  
; procedura identità i
(define i
  (lambda (x)
    x
    ))

; procedura costante 0
(define z
  (lambda (x)
    0
    ))

; procedura costante 1
(define u
  (lambda (x)
    1
    ))

; procedura successore a due parametri (dipendente solo da uno)
(define s2
  (lambda (u v)
    (+ v 1)
    ))

; procedura H alto ordine
(define H
  (lambda (f g)
    (lambda (m n)
      (if (= n 0)
          (f m)
          (g m ((H f g) m (- n 1))))
    )))


(define add (H i s2))
(define mul (H z add))
(define pow (H u mul))

(define esempi
  (list "(crittazione-cesare ALEA IACTA EST IVLIVS CAESAR DIXIT 3)-->"
        (crittazione-cesare "ALEA IACTA EST IVLIVS CAESAR DIXIT" 3)
        ""
        "(crittazione-cesare GESTISCO anche le minuscole 5)-->"
        (crittazione-cesare "GESTISCO anche le minuscole" 5)
        ""
        "(crittazione-cesare e segni come .:,° (e negativi) -5)-->"
        (crittazione-cesare "e segni come .:,° (e negativi)" -5)
        ""
        "(crittazione-cesare e numeri superiori al XIX)-->"
        (crittazione-cesare "e numeri superiori al 19" 50)
        )
  )


   