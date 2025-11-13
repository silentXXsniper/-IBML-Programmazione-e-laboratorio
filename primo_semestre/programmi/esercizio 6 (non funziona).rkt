;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 6 (non funziona)|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks")) #f)))
(define diff
  (lambda (text1 text2)
    (let ((cycle ((t1 text1) 
                (t2 text2) 
                (i 1) 
                (j 1) 
                (result '()))))
      (cond
        ;; Caso: entrambe le liste sono vuote
        ((and (null? t1) (null? t2)) 
         (reverse result)) ; restituiamo il risultato accumulato, invertito

        ;; Caso: `text1` è finito, ma `text2` ha elementi
        ((null? t1) 
         (cycle t1 (cdr t2) 
                i (+ j 1) 
                (cons (list i 'a j (car t2)) result))) ; aggiungi l'elemento di `text2`

        ;; Caso: `text2` è finito, ma `text1` ha elementi
        ((null? t2) 
         (cycle (cdr t1) t2 
                (+ i 1) j 
                (cons (list i 'd j (car t1)) result))) ; cancella l'elemento di `text1`

        ;; Caso: le righe sono uguali, nessuna azione necessaria
        ((string=? (car t1) (car t2)) 
         (cycle (cdr t1) (cdr t2) (+ i 1) (+ j 1) result))

        ;; Caso generale: le righe sono diverse
        (else
         (let ((delete (list i 'd j (car t1)))  ; cancella da `text1`
               (add (list i 'a j (car t2))))    ; aggiungi da `text2`
           (cycle (cdr t1) (cdr t2) 
                  (+ i 1) (+ j 1)
                  (append 
                   (if (not (null? t1)) (list delete) '()) ; aggiungi `delete` se valido
                   (if (not (null? t2)) (list add) '())   ; aggiungi `add` se valido
                   result))))))))