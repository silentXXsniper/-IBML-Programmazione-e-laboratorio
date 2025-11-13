;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname cesare_base) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
(define codA (char->integer #\A))
(define codZ (char->integer #\Z))

(define caesar-cipher
  (lambda (rot)
    (lambda (letter)
      (let ((c (+ (char->integer letter) rot)))
        (if (> c codZ)
            (integer->char (- c 26))
            (integer->char c))))))

(define composition
  (lambda (g f)
    (lambda (x) (g (f x)))))

(define iterate
  (lambda (f i)
    (if (= i 0)
        (lambda (x) x)
        (composition f (iterate f (- i 1))))))

(define some-rule (composition (iterate (caesar-cipher 1) 7) (caesar-cipher 19)))

(define encryption
  (lambda (message rule)
    (if (= (string-length message) 0)
        ""
        (string-append
         (string (rule (string-ref message 0)))
         (encryption (substring message 1) rule)))))

(encryption "PROGRAMMAZIONE" some-rule)
(define caesar3 (caesar-cipher 3))