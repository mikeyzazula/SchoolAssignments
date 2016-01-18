
#lang racket

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; CSCI 301 Fall 2015 

;; Lab 8

;; 

;; Michael Zazula 

;; W00965725

;; 

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;Helper Functions 

(define omember?
  (lambda(a S)
    (cond((null? S)#f)
    (else (or(equal? a (car S))
    (omember? a (cdr S))))
    )
  )
  )
;;;;;;;;;;;;;;;;



;We need to psuedo duplicate the list because we need to make sure we always have he original list intact to compare and look for the correct ordered pair
(define (transitive? S)
  (cond
    ((transcheck S S)#t)
   
    (else #f)

    )
  )

;Here we check if the car s equals the cdr of an ordered pair in our set. when/if we find it, we call member on car c and the cdr of s are in the set
(define (transcar s C L )
  (cond 
    ((null? C) #t)
    ((eq? (car s) (car(cdr(car C)))) 
      (omember? (cons (car (car C) ) (cons (car(cdr s ))'())) L)
       )    
    (else  
     (transcar s (cdr C) L)) 
    )
  )
;(transcar '(a a) '((b b) (c c) (d d) (a b) (b a)) '((a a) (b b) (c c) (d d) (a b) (b a)))



;Here we check the cdr of our ordered pair with the car of the cdr of the original list. we then check if the ordered pair car s and the cdr of C 
(define (transcdr s C L )
  (cond 
   
    ((null? C) #t)
    
    ((eq? (car(cdr s)) (car(car C))) 
      (omember? (cons (car s ) (cons (car(cdr(car C ))) '())) L) 

       )
    
    (else  
     (transcdr s (cdr C) L)) 
    )
  )
;(transcdr '(a c) '((a c) (a b) (b c) (c d)))




;this really is the "main" function. The null check confirms that if we get all the way through each cdr and car check, S must be empty, which means we were never flagged as false so there's a trans relation.
(define (transcheck S L)
  (cond 
    ((null? S) #t)
    
    ((and (transcar (car S) (cdr S) L) (transcdr (car S) (cdr S) L) ) 
     (transcheck (cdr S) L))
    
    (else #f)
    
    
    )
  )

;(transitive? '((a a) (b b) (c c) (d d) (a b) (b a))) 
;(transitive? '((a c) (a b) (b c) (c d)))
(transitive? '('() (a b) ) )

