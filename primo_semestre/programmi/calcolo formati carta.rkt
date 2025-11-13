;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |calcolo formati carta|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define s
  (lambda (k)
    (cond ((= k 0)
            s0)
          ((= k 1)
           s1)
          ((> k 1)
           (/ (s(- k 2)) 2))
          )
    ))

(define s0 (* 100 (expt 2 1/4)))
(define s1 (* 100 (expt 2 -1/4)))

(define compl-a-1
  (lambda (seq)
    (let((k(quotient(string-length seq)2)))
      (if (= k 0))
    (if (=(string-length seq)1)
    (bit-compl seq))
    
    (string-append
     (compl-a-1 (substring seq 0 k))
     (compl-a-1 (substring seq k))
     ))
      )
    )

(define bit-compl
  (lambda (bit)
    (if (string=? bit "0")
        "1"
        "0"
        )
    ))