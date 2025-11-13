;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |plurale inglese|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))

"per fare il plurale scrivere (plurale ______)"
"tutti gli impuit dati vanno inseriti tra le virgolette alte"
; Funzione principale che accetta un sostantivo e lo trasforma al plurale
(define (plurale parola)
  (let* ([lunghezza-parola (string-length parola)]
         [ultimo-char (string-ref parola (- lunghezza-parola 1))]
         [penultimo-char (if (> lunghezza-parola 1) (string-ref parola (- lunghezza-parola 2)) #\space)])

    (cond
      ; Regola 1: Se finisce con "s", "x", "z", "ch" o "sh", aggiungi "es"
      [(or (string=? (substring parola (- lunghezza-parola 1)) "s")
           (string=? (substring parola (- lunghezza-parola 1)) "x")
           (string=? (substring parola (- lunghezza-parola 1)) "z")
           (and (>= lunghezza-parola 2) (string=? (substring parola (- lunghezza-parola 2)) "ch"))
           (and (>= lunghezza-parola 2) (string=? (substring parola (- lunghezza-parola 2)) "sh")))
       (string-append parola "es")]

      ; Regola 2: Se finisce con "y" preceduta da una consonante, cambia la "y" in "ies"
      [(and (char=? ultimo-char #\y) (not (member penultimo-char '(#\a #\e #\i #\o #\u))))
       (string-append (substring parola 0 (- lunghezza-parola 1)) "ies")]

      ; Regola 3: Se finisce con "f" o "fe", cambia in "ves"
      [(or (char=? ultimo-char #\f) (string=? (substring parola (- lunghezza-parola 2)) "fe"))
       (if (string=? (substring parola (- lunghezza-parola 2)) "fe")
           (string-append (substring parola 0 (- lunghezza-parola 2)) "ves")
           (string-append (substring parola 0 (- lunghezza-parola 1)) "ves"))]

      ; Regola 4: Default, aggiungi semplicemente "s"
      [else (string-append parola "s")])))