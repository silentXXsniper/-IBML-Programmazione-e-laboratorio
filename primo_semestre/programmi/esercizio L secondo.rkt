;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio L secondo|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks")) #f)))
(define (L-pattern size)
  (if (= size 1)
      L-tile  ; Caso base: ritorna `L-tile` per dimensione 1
      (let ((k (/ size 2)))
        (glue-tiles
         (glue-tiles (L-pattern k) 
                     (shift-right (L-pattern k) k))
         (glue-tiles (shift-down (L-pattern k) k) 
                     (shift-right (shift-down (quarter-turn-right (L-pattern k)) k) k))))))
(L-pattern 16)