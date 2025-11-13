;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |es 7 opt|) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
(define belong?
  (lambda (x y)
    (cond ((null? y)#false)
          ((= x (car y))#true)
          (else (belong? x (cdr y) ))
          )
    )
  )

(define position
  (lambda (x y)
    (position-ric x y 0)
    ))

(define position-ric
  (lambda (x y ric)
    (cond ((null? y)"inserisci un numero appartenente alla lista")
          ((= x (car y))ric)
          (else (position-ric x (cdr y)(+ ric 1) ))
          )
    )
  )

(define sorted-list
  (lambda (x)
    (sorted-ric (pulisci x) '() 0)
    ))

(define sorted-ric
  (lambda (x y ric)
    (cond ((=(length x)(length y)) (rv y) )
           ((belong? ric x ) (sorted-ric x (cons ric y) (+ ric 1) ))
           (else (sorted-ric x y (+ ric 1)))
           )
          ))
  
(define rv
  (lambda (x)
    (reverse-rec x '())
    ))

(define reverse-rec
  (lambda (x y)
    (cond ((null? x)y)
          (else(reverse-rec(cdr x)(cons (car x)y)))
           )
    ))

(define sorted-ins
  (lambda (x y)
    (sorted-list(cons x y))
    ))

(define pulisci
  (lambda (x)
    (pulisci-rec x '()
                 ))
  )

(define pulisci-rec
  (lambda (x y)
    (cond ( (null? x)(rv y))
           ((belong? (car x) (cdr x))(pulisci-rec (cdr x) y))
           (else (pulisci-rec (cdr x)(cons (car x) y)))

           )
          ))

;per eseguire rapidamente tutti gli esempi usare il comando esempi

(define esempi
  (list
"esempi:"
""
"--------------------------------------"
""
"1° PUNTO"
""
"(belong? 18 '()) "(belong? 18 '())
"(belong? 18 '(5 7 10 18 23))"(belong? 18 '(5 7 10 18 23))
"(belong? 18 '(5 7 10 12 23))"(belong? 18 '(5 7 10 12 23))
""
"--------------------------------------"
""
"2° PUNTO"
""

"(position 7 '(7 8 24 35 41)) "(position 7 '(7 8 24 35 41)) 
"(position 35 '(7 8 24 35 41))"(position 35 '(7 8 24 35 41))
"(position 41 '(7 8 24 35 41))"(position 41 '(7 8 24 35 41))
""
"--------------------------------------"
""
"3° PUNTO"
""
"(sorted-ins 24 '())"(sorted-ins 24 '())
"(sorted-ins 5 '(7 8 24 35 41))"(sorted-ins 5 '(7 8 24 35 41))
"(sorted-ins 24 '(7 8 24 35 41))"(sorted-ins 24 '(7 8 24 35 41))
"(sorted-ins 27 '(7 8 24 35 41))"(sorted-ins 27 '(7 8 24 35 41))
""
"--------------------------------------"
""
"4° PUNTO"
""
"(sorted-list '(35 8 41 24 7))"(sorted-list '(35 8 41 24 7))
"(sorted-list '(35 24 8 41 24 7))"(sorted-list '(35 24 8 41 24 7))

))