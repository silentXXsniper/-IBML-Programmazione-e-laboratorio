;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio esame funzionante|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks")) #f)))
(define q
  (lambda (n a b g)
    (cond [(= n 0)'()]
          [(= n 1) (list a)]
          [(= n 2) (list a b)]
          [else (rip (- n 2)(list a b) a b g)])))

(define rip
  (lambda (n A1 a b g)
    (if (= n 0) A1
        (rip (- n 1)(append A1 (list(g a b))) b (g a b) g)
        )))