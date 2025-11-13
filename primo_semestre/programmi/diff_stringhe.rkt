;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname diff_stringhe) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks")) #f)))
; 06/11/2024


; Sequenza di elementi differenti più corta tra due stringhe del dna
; In caso di sostituzione l'ordine non è importante

(define string-diff    ; val: string
    (lambda (s1 s2) ; s1, s2: string (4-character alphabet)
        (cond ; Caso base
            ((and (string=? s1 "") (string=? s2 "")) "")
            ((string=? s1 "") s2)
            ((string=? s2 "") s1)
            ; Passi ricorsivi
            ((char=? (string-ref s1 0) (string-ref s2 0)) (string-diff (substring s1 1) (substring s2 1)))
            (else
                (shortest-list
                    (string-append (substring s1 0 1) (string-diff (substring s1 1) s2))
                    (string-append (substring s2 0 1) (string-diff s1 (substring s2 1)))
                )
            )
        )
    )
)

(define shortest-list  ; val: string
    (lambda (s1 s2) ; s1, s2: string
        (let (
            (m (string-length s1))
            (n (string-length s2))
            )
            (cond
                ((< m n) s1)
                ((<= n m) s2)
            )
        )
    )
)