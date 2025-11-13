;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |inverti binario|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
"per calcolare il contrario di un binario, scrivere (compl-a-uno _____)"
"per invertire l'ordine dei caratteri di un imput scrivere (string-reverse _____)"
"tutti gli ____ dati vanno inseriti tra le virgolette alte"

;programma che inverte gli 0 e gli 1 in una stringa binaria

(define compl-a-uno
  (lambda (seq)
    (if (>(string-length seq)1) 
     (string-append
     (bit-compl (substring seq 0 1))
     (compl-a-uno (substring seq 1))
    )
     ;esattamente un bit
     (bit-compl seq)
      )
    ))

(define bit-compl
  (lambda (bit)
    (if (string=? bit "0")
        "1"
        "0"
        )
    ))

;programma che inverte l'ordine dei carattare dati come imput

(define string-reverse
  (lambda (str)
    (if (string=? str"") ;(= (string-length str) 0)
        ""
        (string-append
        (string-reverse (substring str 1))
        (substring str 0 1)
        )
     )
   ))