;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |provo cose|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss.txt" "installed-teachpacks") (lib "hanoi.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss.txt" "installed-teachpacks") (lib "hanoi.ss.txt" "installed-teachpacks")) #f)))
;uso questo file per fare esempi e test di varie funzioni racket

(define str (string #\a #\b #\c))
(string-set! str 1 #\x) ; str => "axc"