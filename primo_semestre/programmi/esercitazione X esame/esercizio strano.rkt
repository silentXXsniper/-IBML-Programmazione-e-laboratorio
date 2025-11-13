;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio strano|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks") (lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks") (lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
(define R-B-tessellations
  (lambda (n)
    (cond (( = n 0)
           '())
          ((= n 1)
           '((#\R) (#\B)))
           (else
            (append
             (map (add-leftmost-tiles '(#\R #\B))
                  (R-B-tessellations (- n 2))
                  )
             (map (add-leftmost-tiles '(#\B))
                  (R-B-tessellations ( - n 1))
                  )
             ))
           )))

  (define add-leftmost-tiles
      (lambda (t)
        (lambda (l)
           (append t l)
          )))