;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio L terzo|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks")) #f)))
(define (L-tessellation L)
  (cond [(or(= L 1)(< L 0)) L-tile]
        [(= L 2) (L-media L)]
        [(= L 4) (calcolo-L-4 L)]
        [(= L 8) (calcolo-L-8 L)]
        [else (calcolo-L-ric L 8)]
        ))

(define (L-media x)  
    (glue-tiles
     (glue-tiles
      (glue-tiles L-tile (shift-right (quarter-turn-right L-tile) 1))
      (shift-right (shift-down L-tile 0.5) 0.5))
     (shift-down (quarter-turn-left L-tile) 1))
    )

(define (calcolo-L-4 L)
  (glue-tiles
  (glue-tiles
  (glue-tiles (L-media 1)   
   (shift-right(quarter-turn-right (L-media 1))2)
   )
   (shift-down(quarter-turn-left (L-media 1)) 2)
   )
  (shift-right(shift-down(L-media 1) 1)1)
  )

  )  

(define (calcolo-L-8 L)
  (glue-tiles
  (glue-tiles
  (glue-tiles (calcolo-L-4 1)   
   (shift-right(quarter-turn-right (calcolo-L-4 1))4)
   )
   (shift-down(quarter-turn-left (calcolo-L-4 1)) 4)
   )
  (shift-right(shift-down(calcolo-L-4 1) 2)2)
  )

  )

(define (calcolo-L-ric L num)
  (cond [(or(= num L)(> num L))L-tile ]        
        [(= num 8)
  (glue-tiles
  (glue-tiles
  (glue-tiles
  (glue-tiles (calcolo-L-8 1)   
   (shift-right(quarter-turn-right (calcolo-L-8 1))8)
   )
   (shift-down(quarter-turn-left (calcolo-L-8 1)) 8)
   )
  (shift-right(shift-down(calcolo-L-8 1) 4)4)
  )
  (calcolo-L-ric L (* num 2))
  )]
        [else (glue-tiles
  (glue-tiles
  (glue-tiles
  (glue-tiles (calcolo-L-8 1)   
   (shift-right(quarter-turn-right (calcolo-L-8 1)) num )
   )
   (shift-down(quarter-turn-left (calcolo-L-8 1))  num )
   )
  (shift-right(shift-down(calcolo-L-8 1) num)num )
  )
  (calcolo-L-ric L (+ num 4))
  
  )
              ]))
