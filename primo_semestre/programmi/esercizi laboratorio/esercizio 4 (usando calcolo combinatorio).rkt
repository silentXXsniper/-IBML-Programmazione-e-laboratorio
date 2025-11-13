;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 4 (usando calcolo combinatorio)|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))

; Funzione per calcolare il fattoriale di un numero n
(define (fattoriale n)
  (if (= n 0)
      1
      (* n (fattoriale (- n 1)))))

; Funzione per calcolare il numero di percorsi di Manhattan in 3D
(define (manhattan-3d i j k)
    (/ (fattoriale (+ i j k))
       (* (fattoriale i) (fattoriale j) (fattoriale k))))


; Esempio di utilizzo
(define esempi
  (list
   "manhattan-3d 0 0 7--->"
   (manhattan-3d 0 0 7)
   "manhattan-3d 2 0 2--->"
    (manhattan-3d 2 0 2)
    "manhattan-3d 1 1 1--->"
    (manhattan-3d 1 1 1)
    "manhattan-3d 1 1 5--->"
    (manhattan-3d 1 1 5)
    "manhattan-3d 2 3 1--->"
    (manhattan-3d 2 3 1)
    "manhattan-3d 2 3 3--->"
    (manhattan-3d 2 3 3)
    ))

;per eseguire tutti gli esempi usare il comando esempi