;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |Problema  7 - analisi lista|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; RISOLTO

;; Procedure "belong?" v.2.0

(define belong?             ; value: boolean
  (lambda (element theList) ; element: integer, theList: list
    (if 
     (if (not (= (length theList) 1))
         (if (= (car theList) element) true (belong? element (cdr theList)))
         (if (= (car theList) element) true false)
         )
     true
     false
     )
    ))


;; Procedure "position" verifies the position of a value "element" in a list "theList"

(define position            ; value: integer
  (lambda (element theList) ; element: integer, theList: list
    (if (belong? element theList)
        (+
         (if (not (= (length theList) 1))
             (if (= (car theList) element)
                 -1
                 (position element (cdr theList)))
             -1
             )
         1)
        0
        )
    ))


; TEST: ESITO POSITIVO

(define the-list (list 1 2 3 5 6))
;(belong? 1 theList)
;(belong? 4 theList)
;(belong? 6 theList)
;(position 6 theList)

; inserimento ordinato
(define sorted-ins       ; val: list
  (lambda (joker a-list) ; joker: integer, a-list: list
    (if (= (length a-list) 1)
        ; T: CASO BASE 1: ultimo elemento della lista, lista lunghezza = 1
        (if (> (car a-list) joker)
            ; se l'ultimo elemento è maggiore di joker
            ; inserire joker e poi l'ultimo elemento (il resto della lista)
            (cons joker (a-list))
            (if (= joker (car a-list))
                ; CASO BASE 3: joker è già nella lista
                a-list
                ; siccome sono arrivato alla fine della lista e l'if è falso
                ; joker è il numero maggiore e va inserito come ultimo:
                (cons (car a-list) (cons joker '()))
                )
            )
        ; F: PASSO RICORSIVO e CASO BASE 2
        ; controllo che non siano uguali
        (if (not (= (car a-list) joker))
            ; se non lo sono, controllo se Joker è minore dell'elemento esaminato
            (if (> (car a-list) joker)
                ; se lo è, CASO BASE 2, inserisco joker e il resto della lista
                (cons joker a-list)
                ; altrimenti PASSO RICORSIVO
                (cons (car a-list)(sorted-ins joker (cdr a-list)))
                )
            ; CASO BASE 3: joker già nella lista, restituisco la lista
            a-list
            )
        )
    ))

; TEST: ESITO POSITIVO
;(sorted-ins -1 the-list)
;(sorted-ins 1 the-list)
;(sorted-ins 3 the-list)
;(sorted-ins 4 the-list)
;(sorted-ins 6 the-list)
;(sorted-ins 7 the-list)

; ordinamento lista
(define sorted-list
  (lambda (initial-list)
    (sorted-list-supp
     initial-list
     (sorted-ins
      (car initial-list)
      (remove (car initial-list) initial-list))
     )
    ))

; supporto
(define sorted-list-supp
  (lambda (shifted-list prev-sorted-list)
    (if (= (length shifted-list) 1)
        ; True: end of list
        (sorted-ins
         (car shifted-list)
         (remove (car shifted-list) prev-sorted-list))
        ; False: recursive steps
        (sorted-list-supp
         (cdr shifted-list)
         (sorted-ins
          (car shifted-list)
          (remove (car shifted-list) prev-sorted-list))
         )
        )
    ))

; test-list
(define test-list (list 35 8 41 24 7))

; TEST: ESITO POSITIVO
(sorted-list test-list)