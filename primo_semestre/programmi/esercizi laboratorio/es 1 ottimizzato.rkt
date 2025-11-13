;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |es 1 ottimizzato|) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks") (lib "drawings.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks") (lib "drawings.ss.txt" "installed-teachpacks")) #f)))
(define frase
  (lambda (s v o) ; s = soggetto,   v= verbo,  o = oggetto
    (string-append (articolo s) " " s " " (declina-verbo s v)" " (articolo o) " " o)
    ))

(define trova-gen
  (lambda (s)
    (cond((char=? (string-ref s (- (string-length s) 1)) #\a )"fem-sing")
         ((char=? (string-ref s (- (string-length s) 1)) #\e )"fem-pl")
         ((char=? (string-ref s (- (string-length s) 1)) #\o )"ms-sing")
         ((char=? (string-ref s (- (string-length s) 1)) #\i )"ms-pl")
         (else "error")
    )))

(define trova-verb
  (lambda (v)
    (cond((char=? (string-ref v (- (string-length v) 3)) #\a )"are")
         ((char=? (string-ref v (- (string-length v) 3)) #\e )"ere")
         ((char=? (string-ref v (- (string-length v) 3)) #\i )"ire")
         (else "error")
         )))

(define cut
  (lambda (v)
    (substring v 0(- (string-length v) 3) )
    ))

(define declina-verbo
  (lambda (s v)
    (if(or (string=? "fem-sing"(trova-gen s)) (string=? "ms-sing"(trova-gen s)) )
         (cond ((string=? (trova-verb v) "are")  (string-append(cut v)"a") )
                (else (string-append(cut v)"e"))

         )  
         (cond ((string=? (trova-verb v) "are")  (string-append(cut v)"ano") )
                (else (string-append(cut v)"ono"))

                )
     )
  ))


(define articolo
  (lambda (x)
    (let ((y (trova-gen x)))
            (cond((string=? y "fem-sing") "la")
                 ((string=? y "fem-pl") "le")
                 ((string=? y "ms-sing") "il")
                 ((string=? y "ms-pl") "i")
                 (else "error")
          
))))

(define esempi
  (list
(frase "gatto" "cacciare" "topi") ;esempi
(frase "mucca" "mangiare" "fieno")
(frase "sorelle" "leggere" "novella")
(frase "bambini" "amare" "favole")
(frase "musicisti" "suonare" "pianoforti")
(frase "cuoco" "friggere" "patate")
(frase "camerieri" "servire" "clienti")
(frase "mamma" "chiamare" "figlie")
))
    