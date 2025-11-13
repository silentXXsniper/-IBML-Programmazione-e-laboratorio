;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 6|) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
(define diff-pre
  (lambda (text1 text2)
    (inner-loop text1 text2 1 1 '())))


(define inner-loop
      (lambda (t1 t2 i j result)
        (cond
          [(and (null? t1) (null? t2)) result]

          [(null? t1)
           (inner-loop t1 (cdr t2) i (+ j 1)
                       (cons (list i 'a j (car t2)) result))]

          [(null? t2)
           (inner-loop (cdr t1) t2 (+ i 1) j
                       (cons (list i 'd j (car t1)) result))]

          [(string=? (car t1) (car t2))
           (inner-loop (cdr t1) (cdr t2) (+ i 1) (+ j 1) result)]

          [else
           (let ((delete (list i 'd j (car t1)))
                 (add    (list i 'a j (car t2))))
             (inner-loop (cdr t1) (cdr t2) (+ i 1) (+ j 1)
                         (append
                          (if (not (null? t1)) (list delete) '())
                          (if (not (null? t2)) (list add) '())
                          result)))])
      ))



;; Funzione di comodo per invertire l'output
(define diff
  (lambda (tx1 tx2)
    (reverse (diff-pre tx1 tx2))))

;; Esempio: confronto tra due versioni di codice (lcs_v1 e lcs_v2)
(define esempio
  (diff
   ;; Versione 1
   (list
    ""
    ";; Longest Common Subsequence (LCS)"
    ";; Algoritmo ricorsivo"
    ""
    "(define lcs      ; val:  stringa"
    "  (lambda (u v)  ; u, v: stringhe"
    "    (cond ((or (= (string-length u) 0) (= (string-length v) 0))"
    "           (string ))  ; stringa vuota"
    "          ((char=? (string-ref u 0) (string-ref v 0))"
    "           (string-append"
    "            (string (string-ref u 0)) (lcs (substring u 1) (substring v 1))))"
    "          (else"
    "           (longer (lcs (substring u 1) v) (lcs u (substring v 1))))"
    "          )))"
    ""
    "(define longer   ; val:  stringa"
    "  (lambda (u v)  ; u, v: stringhe"
    "    (let ((m (string-length u)) (n (string-length v)))"
    "      (if (< m n)"
    "          v"
    "          u))"
    "    ))"
    ""
    )
   ;; Versione 2
   (list
    ""
    ";; Longest Common Subsequence (LCS)"
    ";; Algoritmo ricorsivo"
    ""
    "(define lcs      ; val:  stringa"
    "  (lambda (u v)  ; u, v: stringhe"
    "    (cond ((or (= (string-length u) 0) (= (string-length v) 0))"
    "           (string ))  ; stringa vuota"
    "          ((char=? (string-ref u 0) (string-ref v 0))"
    "           (string-append"
    "            (substring u 0 1) (lcs (substring u 1) (substring v 1))))"
    "          (else"
    "           (longer (lcs (substring u 1) v) (lcs u (substring v 1))))"
    "          )))"
    ""
    ";;  Stringa piu' lunga"
    ""
    "(define longer   ; val:  stringa"
    "  (lambda (u v)  ; u, v: stringhe"
    "    (let ((m (string-length u)) (n (string-length v)))"
    "      (cond ((< m n) v)"
    "            ((> m n) u)"
    "            ((= (random 2) 0) v)"
    "            (else u)))"
    "    ))"
    ""
    )))

;; Per visualizzare l’output, basta scrivere:
;; esempio
