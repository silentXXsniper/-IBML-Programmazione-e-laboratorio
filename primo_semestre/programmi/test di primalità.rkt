;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |test di primalità|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks")) #f)))
"scrivere (primo? x) per determinare se un numero sia primo o meno"

(define primo?
  (lambda (n)
    (if (even? n)
        (= n 2)
        (not (divisori-dispari-in? n 3 (sqrt n)))
        )

    ))

;: n ha divisori nell'intervallo [a, b] ?

(define divisori-dispari-in?  ; val: booleano
  (lambda (n a b)     ; n, a, b: interi positivi
    (cond ((> a b)
           false)
          ((= (remainder n a) 0)
           true)
          (else
           (divisori-dispari-in? n (+ a 2) b))
          )
    ))

"scrivere (lista-primi x y) per ottenere una lista dei numeri primi da x a y"

(define (lista-primi a b)        
    (cond [(> a b)
           null]
          [(primo? a)
           (cons a (lista-primi (+ a 1) b))]
          [else
           (lista-primi (+ a 1) b)]
          ))

(define xlcs
  (lambda (u v)
    (xlcs-rec u v 0 0)
    ))
    
(define xlcs-rec
  (lambda (u v i j)
    (cond [(or (string=? u "")(string=? v ""))
           null
           ]
          [(char=? (string-ref u 0)(string-ref v 0))
                   (cons
                    (list (substring u 0 1) i j)
                    (xlcs-rec (substring u 1)(substring v 1)(+ i 1)(+ j 1))
                    )]
          [else
           (xlonger
            (xlcs-rec (substring u 1) v (+ i 1) j)
            (xlcs-rec u (substring v 1) i (+ j 1)))
           ])          
    ))

(define xlonger
  (lambda (s t) ; u, v: liste di terne
    (if (< (length s)(length t))
        t
        s)
    ))

(define molt-russa
  (lambda (m n)
    (cond [(= n 0)
           0]
          [(even? n)
           (molt-russa (* 2 m)(quotient n 2))]
          [else
           (+ m (molt-russa (* 2 m)(quotient n 2)))]
          )))

(define molt-rec
  (lambda (m n p)
    (cond ((= n 0)
           p)
          ((even? n)
           (molt-rec (* 2 m) (quotient n 2) p))
          (else
           (molt-rec (* 2 m) (quotient n 2)(+ m p)))
          )
    ))

(define nul
  (lambda (m n)
    (cond [(= n 0)
           0]
          [(even? n)
           (nul (* 2 m)(quotient n 2))]
          [else
           (+ m (nul (* 2 m)(quotient n 2)))]
          )
    ))