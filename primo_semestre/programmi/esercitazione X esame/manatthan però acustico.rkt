;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |manatthan però acustico|) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
(define low
  (lambda (l)
    (if (null? l) '()
        (cons (string-append (mod (string-ref (car l) 0)) (substring (car l) 1))
              (low (cdr l))))))

(define mod
  (lambda (c)
    (if (< (char->integer c) 97)
        (string
         (integer->char (+ (char->integer c) 32)))
        (string c)
        )))

(low '("Abete" "Betulla" "Faggio" "Quercia" "Tiglio"))
;("abete" "betulla" "faggio" "quercia" "tiglio")
(low '("BiancoSpino" "pervinca" "Primula" "RODODENDRO" "Viola"))
;("biancoSpino" "pervinca" "primula" "rODODENDRO" "viola")