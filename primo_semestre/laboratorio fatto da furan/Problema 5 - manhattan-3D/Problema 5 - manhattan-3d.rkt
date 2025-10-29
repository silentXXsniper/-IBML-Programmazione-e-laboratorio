;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Prob5Draft) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; RISOLTO

; Manhattan-2D a supporto di Manhattan-3D per questioni di pulizia del codice e suddivisione funzionale del problema
(define manhattan-2d
  (lambda (i j)
    (if (and (= i 0) (= j 0))
        0
        (if (= i 0)
            1
            (if (= j 0)
                1
                (+ (manhattan-2d i (- j 1)) (manhattan-2d (- i 1) j)))
            )
        )
    ))

(define manhattan-3d
  (lambda (i j k)
    (cond
      ; Caso base (tutte le cooridnate nulle)
      [(and (= i 0) (= j 0) (= k 0))
        0]
      ; Passo ricorsivo 2D e incremento
      [(= i 0)
          (if (= j 0)
              ; i = 0, j = 0; +1
              1
              (if (= k 0)
                  ; i = 0, k = 0; +1
                  1
                  ; solo i = 0 quindi manhattan-2d (j k)
                  (manhattan-2d j k)
                  )
              )]
      ; i != 0
      [(= j 0) 
          (if (= k 0)
              ; j = 0, k = 0; +1
              1
              (manhattan-2d i k))]
      [(= k 0) 
          (manhattan-2d i j)]
      ; Passo ricorsivo 3D (nessuna coordinata nulla)
      [else (+ (manhattan-3d (- i 1) j k) (manhattan-3d i (- j 1) k) (manhattan-3d i j (- k 1)))]
      )
    ))

; TEST: ESITO POSITIVO
(manhattan-3d 0 0 7) ; 1
(manhattan-3d 2 0 2) ; 6
(manhattan-3d 1 1 1) ; 6
(manhattan-3d 1 1 5) ; 42