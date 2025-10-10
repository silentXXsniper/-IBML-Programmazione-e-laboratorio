;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Prova scritta d’esame del 2 Settembre 2024|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define iter-fun
  (lambda (f x k)
     (iter f x k '())
    ))

(define iter
  (lambda (f x k a)
    (if (> k 0)
        (iter f (f x) (- k 1) (append (list x) a)
              )
        a
        ))
  )

"------------------------------------"

(define comb-count ; val: intero
 (lambda (s x y) ; s: lista ordinata di interi positivi (diversi fra loro), x, y: interi
 (if (or (null? s) (> (car s) y))
 0
 (let ((e (car s)))
 (let ((n (+ (comb-count (cdr s) (- x e) (- y e)) ; e “candiato” per la somma
 (comb-count (cdr s) x y) ; e scartato
 )))
 (if (and (<= x e) (<= e y)) (+ 1 n) n)
 ))
 )))

(define comb ; val: lista di liste ordinate
 (lambda (s x y) ; s: lista ordinata di interi positivi (diversi fra loro), x, y: interi
  (if (or (null? s) (> (car s) y))
      '()
     (let ((e (car s)))
 (let ((u (car
 (map ............
 (comb (cdr s) (- x e) (- y e))
 )
 (comb (cdr s) x y)
 )))
 (if (and (<= x e) (<= e y))
 ............
 ............
 )
 ))
 )))