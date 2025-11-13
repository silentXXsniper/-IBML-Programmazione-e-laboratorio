;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es_6wrk) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks") (lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks") (lib "drawings.ss.txt" "installed-teachpacks")) #f)))
(define lcs
  (lambda (xs ys)
    ;; naive recursive LCS returning the sequence (list) of matching elements
    (cond
      [(or (null? xs) (null? ys)) '()]
      [(string=? (car xs) (car ys))
       (cons (car xs) (lcs (cdr xs) (cdr ys)))]
      [else
       (let ((a (lcs xs (cdr ys)))
             (b (lcs (cdr xs) ys)))
         (if (> (length a) (length b)) a b))])))

(define make-edits
  (lambda (xs ys common i j acc)
    ;; xs, ys: remaining parts of the two texts
    ;; common: remaining LCS sequence (list)
    ;; i: current index in text1 (1-based)
    ;; j: current index in text2 (1-based)
    ;; acc: accumulator for edits (will be reversed at the end)
    (cond
      ;; both texts finished -> done
      [(and (null? xs) (null? ys)) (reverse acc)]

      ;; LCS exhausted -> everything left in xs are deletions, everything left in ys are additions
      [(null? common)
       (cond
         [(not (null? xs))
          ;; delete head of xs
          (make-edits (cdr xs) ys common (+ i 1) j (cons (list i 'd j (car xs)) acc))]
         [(not (null? ys))
          ;; add head of ys
          (make-edits xs (cdr ys) common i (+ j 1) (cons (list i 'a j (car ys)) acc))]
         [else (reverse acc)])]

      ;; common non-empty -> align to next common element
      [else
       (let ((c (car common)))
         (cond
           ;; if head of xs is not the next common element -> delete it
           [(and (not (null? xs)) (not (string=? (car xs) c)))
            (make-edits (cdr xs) ys common (+ i 1) j (cons (list i 'd j (car xs)) acc))]

           ;; if head of ys is not the next common element -> add it
           [(and (not (null? ys)) (not (string=? (car ys) c)))
            (make-edits xs (cdr ys) common i (+ j 1) (cons (list i 'a j (car ys)) acc))]

           ;; both heads equal the next common -> consume both and move forward
           [else
            (make-edits (cdr xs) (cdr ys) (cdr common) (+ i 1) (+ j 1) acc)]))])))

(define diff
  (lambda (text1 text2)
    (let ((common (lcs text1 text2)))
      (make-edits text1 text2 common 1 1 '())))
  )

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
