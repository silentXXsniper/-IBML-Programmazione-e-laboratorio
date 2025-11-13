;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname esercizioTemiEsame) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
;Esercitazione su temi d’esame 16 e 17 Dicembre 2024
(define match
  (lambda (u v)
    (if (or (string=? u "") (string=? v ""))
        ""
        (let ( (uh (string-ref u 0)) (vh (string-ref v 0))
                                     (s (match (substring u 1) (substring v 1) ))
                                     )
          (if (char=? uh vh)
              (string-append (string uh) s)
              (string-append "*" s)
              ))
        )))
;(match "astrazione" "estremi")

(define offset (char->integer #\0))

(define last-digit
  (lambda (base) (integer->char (+ (- base 1) offset)) ))

(define next-digit
  (lambda (dgt)  (string(integer->char (+ (char->integer dgt) 1)) )))

(define increment
  (lambda (num base) ; 2 <= base <= 10
    (let ((digits (string-length num)))
      (if (= digits 0)
          "1"
          (let ((dgt (string-ref num (- (string-length num)1))))
            (if (char=? dgt (last-digit base))
                (string-append (increment (substring num 0 (- (string-length num) 1))  base)
                 "0")
                (string-append (substring num 0 (- digits 1)) (next-digit dgt) )
                ))
          ))))
(increment "1011" 2)
(increment "144" 5)



(define clean-up
  (lambda(u)
    (controllo u '())))

(define controllo
  (lambda (cont da)
    (if(null? cont) da
       (if(belong?(car cont)da)
          (controllo(cdr cont) da)
          (controllo (cdr cont)(append (list(car cont)) da))
          ))
    ))

(define belong?
  (lambda (a b)
    (if (null? b)#F
        (if (string=? a (car b))#T
            (belong? a (cdr b)
                     ))
        )))


(define cleanz
  (lambda (u)
    (if (null? u)
        '()
        (if (belong? (car u) (cdr u))
            (append (cleanz (cdr u)))
            (append (list (car u)) (cleanz (cdr u)))
            ))
    ))
        
    
       