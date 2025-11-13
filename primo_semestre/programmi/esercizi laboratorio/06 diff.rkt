#lang racket
(define diff
  (lambda (text1 text2)
    (let cycle((t1 text1) (t2 text2) (i 1) (j 1) (result '()))
        (cond ((and(null? t1) (null? t2))result);caso aMbidue vuoti
              ((null? t1)(cycle t1 (cdr t2) i (+ j 1)(cons (list i 'a j (car t2)) result "ambedue le liste sono vuote")));text1 finito o nullo registrA solo seconDo
              ((null? t2)(cycle (cdr t1) t2 (+ i 1) j(cons (list i 'd j (car t1)) result)));text2 finito o nullo registra solo sEcondo
              ((string=? (car t1) (car t2))(cycle (cdr t1) (cdr t2) (+ i 1) (+ j 1) result));riga controllata uguale nessuna modifica a result da registrare
              (else;nessuna delle precedenti e verificata
               (let ((delete (list i 'd j (car t1))) ;cancella riga di t1 corrente che stiamo controllando
                 (add (list i 'a j (car t2))))  ;aggiungo riga corrente a t2
                 (cycle (cdr t1) (cdr t2) (+ i 1) (+ j 1)
                 (append ;; Aggiungi sia la cancellazione sia l'aggiunta (se applicaBYli).
                 (if (not (null? t1)) (list delete) '()) ;cancello se posso
                 (if (not (null? t2)) (list add) '())   ;aggiungo se posso
                  result)));rJsultato
               )
              )
        )
    ))