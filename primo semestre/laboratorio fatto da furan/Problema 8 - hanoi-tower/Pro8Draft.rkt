;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Pro8Draft) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
; procedure di partenza -------------------
; hanoi-moves restituisce una lista di coppie che indicano il palo da cui
; viene rimosso un disco e quello in cui il suddetto viene spostato (-1, +1).
; Da qui traggo il fatto di poter sfruttare proprio la lista restituita da hanoi-moves
; per fare incrementi e decrementi. Se il primo elemento è 1, 2 o 3, sottraggo 1 dal contatore di
; disco rispettivamente del palo 1, 2 o 3;
; Se il secondo elemento è 1, 2 o 3, al contrario, sommo 1.
; Inoltre mi è indicato il fatto che su n dischi, la posizione del disco maggiore è restituita dalla
; mossa (ceiling 2^(n-1)), ossia la mossa esattamente a metà del totale di mosse per risolvere la torre.
; Si tratterebbe quindi dell'elemento (ceiling 2^(n-1)) sul totale di elementi della lista di coppie restituita
; da hanoi-moves (len = 2^n-1).
(define hanoi-moves
  ; val: lista di coppie
  (lambda (n)
    ; n > 0 intero
    (hanoi-rec n 1 2 3)
    ))

(define hanoi-rec
  ; val: lista di coppie
  (lambda (n s d t) ; n intero, s, d, t: posizioni
    (if (= n 1)
        (list (list s d))
        (let ((m1 (hanoi-rec (- n 1) s t d))
              (m2 (hanoi-rec (- n 1) t d s))
              )
          (append m1 (cons (list s d) m2))
          ))
    ))

; -----------------------------------------
(define hanoi-disks
  (lambda (n k)
    (if (< k (expt 2 (- n 1)))
        (if (= k 0)
            ; 1° caso base: nessuna mossa
            (list (list 1 n) (list 2 0) (list 3 0))
            ; fuori dai casi base
            (...)
            )
        (if (= k (expt 2 (- n 1)))
            ; 2° caso base: mossa nel mezzo
            (list (list 1 0) (list 2 1) (list 3 (- n 1)))
            (if (= k (- (expt 2 n) 1))
                ; 3° caso base: ultima mossa
                (list (list 1 0) (list 2 n) (list 3 0))
                ; fuori dai casi base
                (...)
                )
            )
        )
    ))

(define hanoi-disks-2
  (lambda (n k)
    (hanoi-disks-rec-2 n k 1 3 2 0 0 0)
    ))

(define hanoi-disks-rec-2
  (lambda (n k s t d a b c)
    (let ((x (expt 2 (- n 1))))
      (cond ((= n 0) (list (list s a) (list t b) (list d c))) ; (= k 0)
            ((= k 0) (list (list s (+ a n)) (list t b) (list d c)))
            ((>= k x)(hanoi-disks-rec-2 (- n 1) (- k x) t s d b a (+ c 1)))
            ((< k x) (hanoi-disks-rec-2 (- n 1) k s d t (+ a 1) c b)))
      )
    ))

(define hanoi-picture
  (lambda (n k)
    (hanoi-picture-rec n k (towers-background n) n 1 3 2 0 0 0)
    ))

(define hanoi-picture-rec
  (lambda (n k img tot s t d a b c) ;n numero dischi;  k posizione; img imagine generata; n grandezza disco
    (let ((x (expt 2 (- n 1))))
      (cond ((= n 0) img) ; (= k 0)
            ; ((= k 1) (above (hanoi-picture-rec (- n 1) k (disk-image k n s a) s t d (+ a 1) b c) img))
            ((>= k x)(hanoi-picture-rec (- n 1) (- k x) (above (disk-image n tot d c) img) tot t s d b a (+ c 1)))
            ((< k x) (hanoi-picture-rec (- n 1) k (above (disk-image n tot s a) img) tot s d t (+ a 1) c b) )
      ))
    ))

(hanoi-picture 3 0)
(hanoi-picture 15 19705)