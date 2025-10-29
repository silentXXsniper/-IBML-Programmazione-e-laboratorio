;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Problema 3.2 - rep to dec|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; procedura di supporto
; rimuove il punto dalla stringa se presente
; mi è necessaria per fornire una stringa senza punto
; alla procedura sopra, bin-conv.
(define absolute
  (lambda (str)
    (if (= (string-length str) 1)
        (substring str 0 1)
        (if (string=? (substring str 0 1) ".")
            (absolute (substring str 1))
            (string-append (substring str 0 1) (absolute (substring str 1)))
            )
        )
    ))

; procedura di supporto
; rimuove segno se presente.
(define signless
  (lambda (str)
    (if (or (char=? (string-ref str 0) #\-) (char=? (string-ref str 0) #\+))
        (substring str 1)
        str
        )
    ))


; procedura che determina l'esponente di 2 la cui potenza risultante
; dovrà dividere il risultato dell'operazione bin-absolute
; per ottenere il risultato corretto.
(define negative-exponent ; val: integer
  (lambda (str)    ; str: String
    (negative-exponent-supp str 0)
    ))


; procedura di supporto
(define negative-exponent-supp ; val: integer
  (lambda (str acc)     ; str, acc: String, integer
    (if (= (string-length str) 0)
        0
        (if (char=? (string-ref str 0) #\.)
            acc
            (negative-exponent-supp (substring str 1)(+ acc 1))
            )
        )
    ))


; procedura determinazione segno
(define sign?
  (lambda (str)
    (if (char=? (string-ref str 0) #\-)
        -1
        +1
        )
    ))

; procedura di assemblaggio complessivo
;(define bin-assembler
; (lambda (str)
;  (*
;  (bin-sign str)
; (/
; (bin-conv (bin-absolute (bin-signless str)))
;(expt 2 (bin-divide (bin-signless str))))
;)
;))

; procedura di "facciata"
;(define bin->dec
; (lambda (str)
;  (bin-assembler str)
; ))


;---------- NUOVE ---------- NUOVE ---------- NUOVE ---------- NUOVE ---------- NUOVE -------------------

; inverte le stringhe
(define string-flip ; val: String
  (lambda (str)     ; str: String
    (list->string (reverse (string->list str)))
    ))

; digit-conversion-supp
(define digit-conversion-supp      ; val: integer
  (lambda (dictionary digit value) ; dictionary, digit, value: String, char, integer
    (if (char=? digit (string-ref dictionary 0))
        value
        (digit-conversion-supp (substring dictionary 1) digit (+ value 1))
        )
    ))

; digit-conversion
(define digit-conversion
  (lambda (dictionary digit)
    (digit-conversion-supp dictionary digit 0)
    ))

; actual-digit-value
(define actual-digit-value
  (lambda (dictionary coded-string base position)
    (*
     (expt base position)
     (digit-conversion dictionary (string-ref coded-string 0))
     )
    ))

; rep->dec-supp
(define rep->dec-conversion-supp
  (lambda (dictionary coded-string position)
    (let ((base (string-length dictionary)))
      (if (= (string-length coded-string) 1)
          (actual-digit-value dictionary coded-string base position)
          (+
           ; attuale digit convertita
           (actual-digit-value dictionary coded-string base position)
           ; chiamata ricorsiva per le prossime digit
           (rep->dec-conversion-supp dictionary (substring coded-string 1) (+ position 1))
           )
          )
      )
    ))

; rep->dec
(define rep->dec-conversion
  (lambda (dictionary coded-string)
    (rep->dec-conversion-supp dictionary coded-string 0)
    ))

; procedura di assemblaggio complessivo
(define rep->dec-assembler
  (lambda (dictionary coded-string)
    (let ((base (string-length dictionary)))
      (*
       (sign? coded-string)
       (/
        (rep->dec-conversion dictionary (string-flip (absolute (signless coded-string))))
        (expt base (negative-exponent (string-flip (signless coded-string)))))
       ))
    ))

; facciata
(define rep->dec
  (lambda (dictionary coded-string)
    (rep->dec-assembler dictionary coded-string)
    ))

; ---------- FAKE BIN-REP->NUMBER --------------------------------------
(define bin-rep->number
  (lambda (binary-string)
    (rep->dec "01" binary-string)
    ))

; TEST 1 : esito positivo
;(rep->dec "zu" "-uuzz") ; - 12
;(rep->dec "0123" "+21.1") ; 9.25
;(rep->dec "01234" "-10.02") ; -5.08
;(rep->dec "0123456789ABCDEF" "0.A") ; 0.625
;(rep->dec "0123456789ABCDEF" "1CF.0") ; 463

; TEST 2 : esito positivo
;(bin-rep->number "+1101")
;(bin-rep->number "0")
;(bin-rep->number "10110.011")
;(bin-rep->number "-0.1101001")