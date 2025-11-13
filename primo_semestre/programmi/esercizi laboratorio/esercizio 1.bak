;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |esercizio 1|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define frase ;mette assieme i vari elementi della frase
  (lambda (a b c)
    (string-append (soggetto a) " " (verbo a b) " " (compl-ogg c))
    ))

(define femminile-s? ;determina se sia maschile singola, femminile singolare o maschile o femminile plurale
  (lambda(s)
    (char=? (string-ref s(- (string-length s) 1)) #\a)
            ))

(define femminile-pl?
  (lambda(s)
    (char=? (string-ref s(- (string-length s) 1)) #\e)
            ))

(define maschile-s?
  (lambda(s)
    (char=? (string-ref s(- (string-length s) 1)) #\o)
            ))

(define maschile-pl?
  (lambda(s)
    (char=? (string-ref s(- (string-length s) 1)) #\i)
            ))

(define soggetto ;assegna l'elaborazione del soggetto al codice che elebora sia soggetto che complemento oggetto
  (lambda (s)
    (determinatore s)))
    
(define determinatore ; controlla tutte le possibili combinazioni di maschile, femminile con singolare e plurale e assegna il relativo articolo
  (lambda (s)
        (if (femminile-s? s)
            (articolo-f-s s)
            (if (femminile-pl? s)
                (articolo-f-pl s)
                (if (maschile-s? s)
                 (articolo-m-s s)                    
                 (articolo-m-pl s)
                    )))
    ))
    
(define articolo-f-s ;aggiunge il relativo articolo al genere corretto
  (lambda (af)
         (string-append "la " af)
         ))
(define articolo-f-pl
  (lambda (af)
         (string-append "le " af)
         ))
(define articolo-m-s
  (lambda (af)
         (string-append "il " af)
         ))
(define articolo-m-pl
  (lambda (af)
         (string-append "i " af)
         ))
            
(define verbo ; il codice di una compagna è stato preso come ispirazione per questa funzione
  (lambda (sog verb)
    (cond
      ((and(verb-are verb)(or(maschile-pl? sog)(femminile-pl? sog)))(string-append(rad-verb verb)"ano"));essendo ere ed ide uguali non necessita di ripetizione
      ((and(verb-are verb)(or(maschile-s? sog)(femminile-s? sog)))(string-append(rad-verb verb)"a"))    ;viene riportato sotto comunque il codice nel caso si volesse implementare -ire
      ((and(verb-ere verb)(or(maschile-pl? sog)(femminile-pl? sog)))(string-append(rad-verb verb)"ono"));((and(verb-ire verb)(or(maschile-pl? sog)(femminile-pl? sog)))(string-append(rad-verb verb)"ono"))
      ((and(verb-ere verb)(or(maschile-s? sog)(femminile-s? sog)))(string-append(rad-verb verb)"e"))    ;((and(verb-ire verb)(or(maschile-s? sog)(femminile-s? sog)))(string-append(rad-verb verb)"e"))
    )
  ))

(define verb-are
  (lambda (are)
    (string=?(substring are(-(string-length are)3)(string-length are))"are")))
(define verb-ere
  (lambda (ere)                                                                  ; ere ed ire utilizzano la medesima declinazione con la terza persona
    (or (string=?(substring ere(-(string-length ere)3)(string-length ere))"ere") ; viene riportato sotto comunque il codice nel caso si volesse implementare -ire
        (string=?(substring ere(-(string-length ere)3)(string-length ere))"ire") ; (define verb-ire
    )))                                                                          ; (lambda (ire)
                                                                                 ; (string=?(substring ire(-(string-length ire)3)(string-length ire))"ire")))                                                                                
(define rad-verb
  (lambda (inf)
    (substring inf 0(-(string-length inf)3))
    ))

(define compl-ogg  ; esegue la medesima procedura del soggetto
  (lambda (s)
    (determinatore s)))

;per eseguire tutti gli esempi usare il comando esempi
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
