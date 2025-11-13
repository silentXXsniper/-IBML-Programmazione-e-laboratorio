;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |calcolo superficie totale cilindro|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define sup-tot-cilindro
  (lambda (r h)
    (*(*(* 2 3.14159)r)(+ r h))
      ))

"per calcoalre la superficie totale del cilindro, scrivere (sup-tot-cilindro __ __) con il primo numero r, il secondo h"

(define plurale-sm
  (lambda (sm)
         (string-append (substring sm 0 (- (string-length sm) 1))"i")
         ))

(define plurale-sf
  (lambda (sm)
         (string-append (substring sm 0 (- (string-length sm) 1))"e")
         ))

(define femminile?
  (lambda(s)
    (char=? (string-ref s(- (string-length s) 1)) #\a)
            ))
(define radice-sost
  (lambda (s)
         (substring s 0 (- (string-length s) 1))
         ))

(define plurale
  (lambda (s)
        (if (femminile? s)
            (plurale-sf s)
            (plurale-sm s)
            )
    ))

"per calcolare il plurale scrivere (plurale ______)"
; (if (C) E1 E2) C? E1 : E2

; il seguente codice trasforma qualsiasi verbo infinito in forma participio passato
;il codice è eseguito usando il comando (participio "verbo")
;rileva automaticamente la coniugazione e esegue il programma relativo
;non è in grado di applicare le varie eccezioni alla regola standard, quindi non sempre è preciso
(define part-passato-1-3
  (lambda (inf13)
    (string-append (radice-verb inf13)
                   (substring inf13(- (string-length inf13)4)(-(string-length inf13)3))
                   "ato")
    ))

(define part-passato-1-2
  (lambda (inf12)
    (string-append (radice-verb inf12)
                   (substring inf12(- (string-length inf12)4)(-(string-length inf12)3))
                   "uto")
    ))
(define part-passato-1-1
  (lambda (inf11)
    (string-append (radice-verb inf11)
                   (substring inf11(- (string-length inf11)4)(-(string-length inf11)3))
                   "ito")
    ))

(define radice-verb
  (lambda (inf)
    (substring inf 0 (- (string-length inf) 4))
    ))

(define are?
  (lambda(inf)
    (char=? (string-ref inf(- (string-length inf) 3)) #\a)
            ))
(define ere?
  (lambda(inf)
    (char=? (string-ref inf(- (string-length inf) 3)) #\e)
            ))
(define ire?
  (lambda(inf)
    (char=? (string-ref inf(- (string-length inf) 3)) #\i)
            ))

(define participio
  (lambda (inf)
         (if (are? inf)
             (part-passato-1-3 inf)
             (if (ere? inf)
             (part-passato-1-2 inf)
             (part-passato-1-1 inf)
             ))
         ))
"per scrivere il participio passato di un verbo all'infinito, srivere (participio _____)"
"tutti gli impuit dati non numerici vanno inseriti tra le virgolette alte"