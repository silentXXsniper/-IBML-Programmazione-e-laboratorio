;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Problema 1|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;; Problema 1 - 26/29 Ottobre 2020

;; Procedura per gestire i sostantivi (soggetto e complemento oggetto)
(define quale-articolo?  ; val: stringa
  (lambda (sostantivo)  ; arg: stringa
    (let ((ultima-lettera (string-ref sostantivo (- (string-length sostantivo) 1))))
      (cond ((char=? ultima-lettera #\a) (string-append "la " sostantivo))
            ((char=? ultima-lettera #\e) (string-append "le " sostantivo))
            ((char=? ultima-lettera #\i) (string-append "i "  sostantivo))
            ((char=? ultima-lettera #\o) (string-append "il " sostantivo))
            )
      )))

;; Procedura che dice se un sostantivo è singolare
(define singolare?     ; val: char
  (lambda (sostantivo) ; arg: stringa
    (let ((ultima-lettera (string-ref sostantivo (- (string-length sostantivo) 1))))
      (cond ((or (char=? ultima-lettera #\a) (char=? ultima-lettera #\o)) #t)
            ((or (char=? ultima-lettera #\e) (char=? ultima-lettera #\i)) #f)
            )
      )
    ))

;; Procedura per gestire predicati verbali
(define coniuga        ; val: stringa [predicato verbale coniugato]
  (lambda (vb sos)     ; verbo, sostantivo: stringhe [verbo all'infinito, sostantivo]
    (if (singolare? sos)
        (coniuga-singolare vb)
        (coniuga-plurale   vb))
    ))

(define coniuga-singolare
  (lambda (vb)
    (let ((coniugazione (substring vb (- (string-length vb) 3) (string-length vb)))
          (radice (substring vb 0 (- (string-length vb) 3))))
      (cond ((string=? coniugazione "are" ) (string-append " " radice "a "))
            ((string=? coniugazione "ere" ) (string-append " " radice "e "))
            ((string=? coniugazione "ire" ) (string-append " " radice "a ")))
      )))

(define coniuga-plurale ; val: stringa [verbo coniugato]
  (lambda (vb)          ; vb:  stringa [verbo da coniugare]
    (let ((coniugazione (substring vb (- (string-length vb) 3) (string-length vb)))
          (radice (substring vb 0 (- (string-length vb) 3))))
      (cond ((string=? coniugazione "are" ) (string-append " " radice "ano "))
            ((string=? coniugazione "ere" ) (string-append " " radice "ono "))
            ((string=? coniugazione "ire" ) (string-append " " radice "ono ")))
      )))

;; Procedura finale
(define frase        ; val: stringa [frase di senso compiuto]
  (lambda (so pv co) ; so, pv, co: stringhe [soggetto, predicato verbale, complemento oggetto]
    (let ((space " "))
      (string-append (quale-articolo? so) (coniuga pv so) (quale-articolo? co))
      )))

;; Tester
(frase "gatto" "cacciare" "topi")
(frase "mucca" "mangiare" "fieno")
(frase "sorelle" "leggere" "novella")
(frase "bambini" "amare" "favole")
(frase "musicisti" "suonare" "pianoforti")
(frase "cuoco" "friggere" "patate")
(frase "camerieri" "servire" "clienti")
(frase "mamma" "chiamare" "figlie")
















;; Procedura per gestire predicati verbali
;;(define qualeConiugazione?     ; val: stringa
;;  (lambda (verbo sostantivo)   ; arg: stringa stringa
;;    (let ((coniugazione (substring verbo (- (string-length verbo) 3) (string-length verbo))))
;;      (let ((radice (substring verbo 0 (- (string-length verbo) 3))))
;;        (cond ((and (string=? coniugazione "are" ) (singolare? sostantivo)) (string-append " " radice "a "))
;;              ((and (string=? coniugazione "ere" ) (singolare? sostantivo)) (string-append " " radice "e "))
;;              ((and (string=? coniugazione "ire" ) (singolare? sostantivo)) (string-append " " radice "a "))
;;                 
;;              ((and (string=? coniugazione "are" ) (not (singolare? sostantivo))) (string-append " " radice "ano "))
;;              ((and (string=? coniugazione "ere" ) (not (singolare? sostantivo))) (string-append " " radice "ono "))
;;              ((and (string=? coniugazione "ire" ) (not (singolare? sostantivo))) (string-append " " radice "ono "))
;;              )
;;        ))
;;    ))