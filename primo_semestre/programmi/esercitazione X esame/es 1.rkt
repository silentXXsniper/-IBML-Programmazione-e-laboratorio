;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |es 1|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks") (lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks") (lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
;es 1 simulazione d'esame
#; (Completa la procedura match che, date due stringhe di lettere u e v, restituisce la stringa delle corrispondenze w così
definita: w ha la lunghezza della stringa più corta (fra u e v); se in una certa posizione u e v contengono la stessa lettera,
allora anche w contiene quella lettera nella posizione corrispondente; se invece u e v contengono lettere diverse, w
contiene il simbolo “asterisco” nella posizione corrispondente. Per esempio, il valore dell’espressione Scheme
(match "astrazione" "estremi") è rappresentato dalla stringa delle corrispondenze "*str**i")

#;(define match
(lambda (u v)
(if ( (string=? u "") (string=? v ""))
""
(let ( (uh (string-ref u 0))
(s )
)
(if ( uh vh)
(string-append s)
(string-append "*" s)
))
)))

(define match
 (lambda (u v)
   (if (or(string=? u "") (string=? v ""))
    ""
       (let ( (uh (string-ref u 0))(vh (string-ref v 0))
              (s (match (substring u 1 (string-length u)) (substring v 1 (string-length v))))
            )
          (if (char=? uh vh)
              (string-append  (substring u 0 1) s)
              (string-append "*" s)
              ))
    )))