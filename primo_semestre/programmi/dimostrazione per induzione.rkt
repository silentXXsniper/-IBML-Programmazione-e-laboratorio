;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |dimostrazione per induzione|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define manh        ;val: int
  (lambda (i j)     ;i, j: interi non negativi
    (if (or (= i 0) (= j 0))
            1
            (+ (manh (- i 1) j) (manh i (- j 1)))
        )
    )
  )

; ogni i, j naturali . (manh i j) -*-> (i+j)! / (i! * j!) (G)
; dimostrazione per induzione su k = i+j
; (0,0) < (0,1), (1,0) < (0,2), (1,1), (2,0) < (0,3), (1,2), (2,1), (3,0) < ...
; (G): ogni k naturale . ogni i, j naturali tali che i+j = k . (manh i j) -*-> (i+j)! / (i! * j!)
; caso/i base:
; ogni i, j naturali tali che i+j = 0 . (manh i j) -*-> (i+j)! / (i! * j!)
; (manh 0 0) -*-> (0+0)! / (0! * 0!) = 1
; ipotesi induttiva: considero un particolare k naturale e assumo
; ogni i, j naturali tali che i+j = k . (manh i j) -*-> (i+j)! / (i! * j!)
; passo induttivo: con riferimento al k considerato sopra vorrei dimostrare che
; ogni i, j naturali tali che i+j = k+1 . (manh i j) -*-> (i+j)! / (i! * j!)
; dimostrazione del passo induttivo: ogni i, j naturali tali che i+j = k+1
; (manh i j) -*-> (if (or (= i 0) (= j 0)) ... ...)
; (a) i = 0, (b) j = 0
; (manh i j) -*-> (if (or (= i 0) (= j 0)) ... ...) -*-> 1 = (0+j)! / (0! * j!) = (i+0)! / (i! * 0!)
; (c) i, j > 0
; (manh i j) -*-> (if (or (= i 0) (= j 0)) ... ...) -*-> (+ (manh (- i 1) j) (manh i (- j 1)))
; -*-> (+ (manh i-1 j) (manh i (- j 1))) ; i-1 + j = i+j - 1 = k+1 - 1 = k
; -*-> (+ (i-1+j)! / ((i-1)! * j!) (manh i (- j 1))) ; perciò si applica l'ipotesi induttiva
; -*-> (+ (i-1+j)! / ((i-1)! * j!) (manh i j-1)) ; come sopra
; -*-> (+ (i-1+j)! / ((i-1)! * j!) (i+j-1)! / (i! * (j-1)!))
; -*-> (i-1+j)! / ((i-1)! * j!) + (i+j-1)! / (i! * (j-1)!)
