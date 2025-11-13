;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 8 ottimizzato|) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
(define hanoi-picture
  (lambda (n k) 
    (h-d   n n k    '() '() '()     (list 1 0) (list 2 0) (list 3 0))))

(define h-d
  (lambda (nFisso n k     a b c     a1 b1 c1) ; n-numero di dischi, k-iterazione, 
    (if (= n 0)
        (compila nFisso a1 b1 c1 a b c)
        (if (< k (expt 2 (- n 1))) ;k < 2^(n-1)
            (h-d nFisso (- n 1) k                        (cons (disk-image n nFisso (car a1) (cadr a1)) a) c b     (list (car a1) (+ 1 (cadr a1)))  c1 b1)
            (h-d nFisso (- n 1) (- k (expt 2 (- n 1)))    c (cons (disk-image n nFisso (car b1) (cadr b1)) b) a       c1 (list (car b1) (+ 1 (cadr b1))) a1)
            ))))


(define compila
  (lambda (n a1 b1 c1 a b c)
    (above
     (if  (null? a)
          (disk-image 0 1 0 0)
          (if (null? (cdr a))
              (car a)
              (above (car a) (compila n a1 b1 c1 (cdr a) b c))))
     (above
      (if  (null? b)
           (disk-image 0 1 0 0)
           (if (null? (cdr b))
               (car b)
               (above (car b) (compila n a1 b1 c1 a (cdr b) c))))

      (above
       (if  (null? c)
            (disk-image 0 1 0 0)
            (if (null? (cdr c))
                (car c)
                (above (car c) (compila n a1 b1 c1 a b (cdr c)))))

       (towers-background n) )))))
(define esempi
  (list 
(hanoi-picture 5 0)
(hanoi-picture 5 13)
(hanoi-picture 5 22)
(hanoi-picture 5 31)
;(hanoi-picture 15 19705) rimossa perchè impiega troppo tempo 
  ))