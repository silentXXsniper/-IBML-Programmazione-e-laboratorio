;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 7|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks")) #f)))
(define belong?
  (lambda (x ins)
   (cond [(and (null? x)(null? (ins))) #true]
         [(and (not(null? x))(null? ins)) #false]
         ; [(char=? x (car ins)) #true] nel caso si volesse usare con parole e non numeri
         [(= x (car ins)) #true]
         [else (belong? x (cdr ins))]
   )
  )
 )

(define position
  (lambda (x ins)
    (if (not(belong? x ins)) "inserire un numero apartenente alla lista data"
        (trova-pos x ins 0)        
        )
    )
  )

(define trova-pos
  (lambda (x ins c)
    (cond [(null? ins) "errore"] ;doppio ceck
          [(= x (car ins)) c]
          [else(trova-pos x (cdr ins) (+ c 1))]
          )
    )
  )

(define sorted-ins
  (lambda (x ins)
    (if (belong? x ins)(conta-lunghezza x '() ins 0)
    (conta-lunghezza x '()(cons x ins) 0)
    )))

(define conta-lunghezza
  (lambda (x ins ins-ord c)
    (if (null? ins-ord) (ordinamento 0 ins ins-ord c)  
        (conta-lunghezza 0 (cons (car ins-ord)ins)(cdr ins-ord)(+ c 1))  ;conta quanti elementi ci sono nell'insieme trasferendo tutti gli elementi da ins-ord a ins
        ))
    )
    

(define ordinamento
  (lambda (rip-eff ins ins-ord c)
    (cond [(= c 0) (inverti ins-ord '())]  ;controlla se gli elementi da trovare dell'insieme sono 0, in tal caso da l'insieme ordinato
          [(belong? rip-eff ins) (ordinamento (+ rip-eff 1)  ins (cons rip-eff ins-ord)(- c 1))] ;controlla se 0 ( poi 1, 2 ,3 ...) appartiene all'insieme, in tal caso lo inserisce nell'insieme ordinato, riducendo di 1 c, che determina quanti elementi dell'insieme devono essere ancora trovati
          [else (ordinamento (+ rip-eff 1) ins ins-ord  c )] ;non è stato trovato rip-eff (o 1, 2, 3...) quindi riprova con rip-eff + 1. mantiene c uguale perchè il numero di elementi da trovare non è cambiato
          )
    ))

(define sorted-list  ;come prima cosa dobbiamo controllare eventuali ripetizioni
  (lambda (x)
    (controllo-ripetizioni (car x) (cdr x) '() x)
    ))
    

(define controllo-ripetizioni
  (lambda (x ins ins-contrl ins-org)
    (cond [(null? ins) (conta-lunghezza 0 '() (cons x ins-contrl) 0)]
          [(belong? x ins)(controllo-ripetizioni (car ins)(cdr ins) ins-contrl ins-org)];controlla se il n° numero è ripetuto nell'insieme, in caso affermativo lo elimina e continua la ricorsione
          [else(controllo-ripetizioni (car ins) (cdr ins) (cons x ins-contrl) ins-org)] ;se l'n° numero non è ripetuto, continua a controllare salvandolo in ins-contrl aggiungendolo alla lista di n precedenti
          )
    ))

(define inverti
  (lambda (x risul)
    (if (null? x) risul  ;inverte l'ordine di un insieme, serve perchè la funzione "ordinamento" restituisce gli elementi in ordine decrescente
        (inverti (cdr x)(cons (car x)risul))
        )))


;per eseguire rapidamente tutti gli esempi usare il comando esempi

(define esempi
  (list
"esempi:"
""
"--------------------------------------"
""
"1° PUNTO"
""
"(belong? 18 '()) "(belong? 18 '())
"(belong? 18 '(5 7 10 18 23))"(belong? 18 '(5 7 10 18 23))
"(belong? 18 '(5 7 10 12 23))"(belong? 18 '(5 7 10 12 23))
""
"--------------------------------------"
""
"2° PUNTO"
""

"(position 7 '(7 8 24 35 41)) "(position 7 '(7 8 24 35 41)) 
"(position 35 '(7 8 24 35 41))"(position 35 '(7 8 24 35 41))
"(position 41 '(7 8 24 35 41))"(position 41 '(7 8 24 35 41))
""
"--------------------------------------"
""
"3° PUNTO"
""
"(sorted-ins 24 '())"(sorted-ins 24 '())
"(sorted-ins 5 '(7 8 24 35 41))"(sorted-ins 5 '(7 8 24 35 41))
"(sorted-ins 24 '(7 8 24 35 41))"(sorted-ins 24 '(7 8 24 35 41))
"(sorted-ins 27 '(7 8 24 35 41))"(sorted-ins 27 '(7 8 24 35 41))
""
"--------------------------------------"
""
"4° PUNTO"
""
"(sorted-list '(35 8 41 24 7))"(sorted-list '(35 8 41 24 7))
"(sorted-list '(35 24 8 41 24 7))"(sorted-list '(35 24 8 41 24 7))

))