;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 1|) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
(define p
  (lambda (f x y k)
   (if (= k 0)
       null
       (cons x (p f y (f x y)(- k 1)))
                  )
             ))

(define q
  (lambda (f x y k)
    (letrec
        ((helper
          (lambda (i s)
            (if (= i k)
                s
                (helper
                 (+ i 1)
                 (append s (list (f (list-ref s (- i 2)) (list-ref s (- i 1)))))
                        ))
                ))
          )
    (cond ((= k 0)
           null)
          ((= k 1)
          (list x))
          (else
           (helper 2 (list x y)))
           ))
          
      ))