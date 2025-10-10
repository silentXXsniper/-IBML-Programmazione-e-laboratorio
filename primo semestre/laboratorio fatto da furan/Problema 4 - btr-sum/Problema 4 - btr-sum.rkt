;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Pr4Draft) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; RISOLTO

; normalized-btr
(define normalized-btr
  (lambda (btr-str)
    (if (= (string-length btr-str) 1)
        btr-str
        (if (char=? (string-ref btr-str 0) #\.)
            (normalized-btr (substring btr-str 1))
            btr-str
            )
        )
    ))

; least-significant-digit
(define lsd
  (lambda (btr-str)
    (if (= (string-length btr-str) 0)
        #\.
        (string-ref btr-str (- (string-length btr-str) 1))
        )
    ))

; head
(define head
  (lambda (btr-str)
    (if (= (string-length btr-str) 0)
        ""
        (substring btr-str 0 (- (string-length btr-str) 1))
        )
    ))

; btr-digit-sum
(define btr-digit-sum                    ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\.)
                        ((char=? c #\.)  ; - - .
                         #\+)
                        ((char=? c #\+)  ; - - +
                         #\-)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\+)
                        ((char=? c #\.)  ; - . .
                         #\-)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  c)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\+)
                        ((char=? c #\.)  ; . - .
                         #\-)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  c)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\+)
                        ((char=? c #\+)  ; . + +
                         #\-)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  c)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\+)
                        ((char=? c #\+)  ; + . +
                         #\-)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\+)
                        ((char=? c #\.)  ; + + .
                         #\-)
                        ((char=? c #\+)  ; + + +
                         #\.)))))
          )))

; btr-carry
(define btr-carry                        ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\-)
                        ((char=? c #\.)  ; - - .
                         #\-)
                        ((char=? c #\+)  ; - - +
                         #\.)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\-)
                        ((char=? c #\.)  ; - . .
                         #\.)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  #\.)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\-)
                        ((char=? c #\.)  ; . - .
                         #\.)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  #\.)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\.)
                        ((char=? c #\+)  ; . + +
                         #\+)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  #\.)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\.)
                        ((char=? c #\+)  ; + . +
                         #\+)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\.)
                        ((char=? c #\.)  ; + + .
                         #\+)
                        ((char=? c #\+)  ; + + +
                         #\+)))))
          )))

; btr-carry-sum
(define btr-carry-sum
  (lambda (addend-1 addend-2 carry-in)
    (if (and (<= (string-length addend-1) 1) (<= (string-length addend-2) 1))
        ; Caso base: tutte e due le stringhe finite
        (string-append
         (string (btr-carry (lsd addend-1) (lsd addend-2) carry-in))
         (string (btr-digit-sum (lsd addend-1) (lsd addend-2) carry-in)))
        ; Caso ricorsivo:
        (string-append
         (btr-carry-sum (head addend-1) (head addend-2) (btr-carry (lsd addend-1) (lsd addend-2) carry-in))
         (string (btr-digit-sum (lsd addend-1) (lsd addend-2) carry-in)))
        )
    ))
        

; btr-sum
(define btr-sum
  (lambda (addend-1 addend-2)
    (normalized-btr (btr-carry-sum addend-1 addend-2 #\.))
    ))

; TEST: ESITO POSITIVO
(btr-sum "-+--" "+") ; -+-.
(btr-sum "-+--" "-") ; -.++
(btr-sum "+-.+" "-+.-") ; .
(btr-sum "-+--+" "-.--") ; --++.
(btr-sum "-+-+." "-.-+") ; -.-.+
(btr-sum "+-+-." "+.+-") ; +.+.-