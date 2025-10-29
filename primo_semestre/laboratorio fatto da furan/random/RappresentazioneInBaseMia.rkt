;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname RappresentazioneInBaseMia) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;; Rappresentazione numerica posizionale in base b
;; del valore intero non negativo n, data la sequenza
;; (arbitraria) di cifre -- da cui si evince la base

(define representation  ; val: stringa
  (lambda (n digits)    ; n >= 0 intero, digits: stringa
    (let ((b (string-length digits)))  ; b: base
      (let ((q (quotient n b)) (r (remainder n b)))
        (if (= q 0)
            (substring digits r (+ r 1))
            (string-append
             (representation q digits)
             (substring digits r (+ r 1))
             )
            )))
    ))

;; Testing ground

(representation 5 "01")
(representation 5 "0123456789")
