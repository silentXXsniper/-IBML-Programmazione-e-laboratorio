;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 2|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks")) #f)))
"croce"
;Procedura per realizzare la croce
(glue-tiles
 (glue-tiles
   (glue-tiles (shift-right smaller-tile 1.6) (shift-down larger-tile 0))
   (shift-down (shift-right(half-turn larger-tile) 1.6) 0.8))
   (shift-down (shift-right(half-turn smaller-tile) 1.6) 4)
)
"quadrato"
;Procedura per realizzare il quadrato
(glue-tiles
 (glue-tiles
  (glue-tiles
    (shift-down (quarter-turn-right larger-tile) 1.6)
    (shift-right(quarter-turn-left larger-tile ) 0.8))
  (shift-down (quarter-turn-right smaller-tile) 1.6))
 (shift-right(shift-down (quarter-turn-left smaller-tile) 1.6) 4)
)
        





