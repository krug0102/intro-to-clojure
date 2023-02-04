(ns start) ; a required namespace declaration

;; Introduction to Clojure expressions and functions.

;************************************************************
;**** Introduction to Clojure expressions and functions *****
;************************************************************

;; Clojure uses prefix notation: a function call is before its arguments,
;; the entire expression is in parentheses.

;; Also note that, unlike in most other languages,
;; +, -, *, etc are functions, not operators

(+ 2 3)

(+ 2 3 4 5 6) ; you can apply many arithmetic functions to as many arguments as you'd like

(* (+ 2 3) (- 4 2)) ; nesting of expressions

(def x 5) ; define a variable (note: cannot change, the language is purely functional)

(+ (/ 3 x))

;; Task 1 (3 points): convert the following infix expressions to Clojure;
;; define missing variables as needed:
;; 5 + x * 3 * (y - 3) + y / 2
;; 7 / ((x + z) / 2)
;; (x < 8) = (8 <= x)

(def y 2)
(def z 4)

(+ (+ 5 (* x (* 3 (- y 3)))) (/ y 2))

(/ 7 (/ (+ x z) 2))

(= (< x 8) (<= 8 x))

(defn f [x] (+ x 2))

(f -4)

;; Task 2 (3 points): define and test (by calling on different inputs) a function
;; that takes a number x and returns (x * x) - 1.

(defn mult_and_sub [x]
  (- (* x x) 1))

(mult_and_sub 2)

(defn g [x y] (* (+ x 1) (- y 1)))

(g 2 3)

;; Task 3 (3 points): Define and test a function that takes two numbers
;; and returns the sum of their squares


(defn addSquares [x y]
  (+ (* x x) (* y y))
  )

(addSquares 2 4)

;**************************************
;**** Simple predefined functions *****
;**************************************

(inc 1)

(dec 1)

(odd? 2)

(neg? -3)

;; Task 4 (2 points): find two more simple functions or predicates
;; of one variable in clojuredocs (see the lab page)
;; and show examples of their use.

(char 97)
;; \a

(number? 5)
;; true


;**************************************
;**** Data Structures (Collections) ***
;**************************************

;; vectors
(def v1 [1 2 3])

(def v2 (vector 1 "apple" \a "day"))

;; lists

(def list1 '(1 2 3))

;; common functions for collections

(first v1)

(first v2)

(first list1)

(rest v1) ; what's the type of it? why?
;; clojure.lang.PersistentVector$ChunkedSeq
;; rest returns a sequence.

(rest v2)

(rest list1)

(nth v1 2)

(take 2 v1)

(drop 2 v1)

(drop 3 v1)

(empty? (drop 3 v1))

;; Task 5 (4 points): What happens when you call these functions on empty collections?
;; Test them and write down your answers

;; first returns nil
;; rest returns an empty sequence
;; nth gives an Execution error (IndexOutOfBoundsException) and returns null
;; take returns an empty sequence
;; drop returns an empty sequence
;; empty returns true

;; adding elements to vectors and lists

(conj v1 5) ; note: new vector, v1 hasn't changed - test it!
;; [1 2 3 5]

(conj list1 5) ; note: new list, list1 hasn't changed - test it!
;; (5 1 2 3)

;; lazy sequences
(take 5 (range))
;; (0 1 2 3 4)


;****************************************
;********* Higher Order Functions *******
;****************************************

(map inc v1)

(map odd? list1)

; Using map for more than one vector
(def v2 [5 -3 7])

(map + v1 v2)

; Task 6 (3 points): given two vectors, make a new sequence that contains the smaller
; of the two elements at any position. For instance, given vectors [1 5 3]
; and [2 2 3], it will return a sequence (1 2 3). The reason it's a sequence
; and not a vector is that map returns a sequence.
(def a [1 5 3])
(def b [2 2 3])

(map min a b)


(filter odd? list1)

(filter neg? v1)

(def v3 [3 "apple" [] -7.5])

; Task 7 (2 points): make a sequence of only numbers in v3
(filter number? v3)

(reduce + v1)

;; Task 8 (2 points) : find the product of all numbers in v1

(reduce * v1)

;; higher order functions with anonymous parameters

;; fn syntax:
(map (fn [x] (* x 3)) v1)

;; function literal:

(map #(* % 3) v1)

;; Task 9 (2 points): filter a vector by keeping only numbers greater than 1
(def vec1 [-1 3 1 0])
(filter #(> % 1) vec1)

;; Task 10 (2 points): compute the sum of squares of the first 10 natural numbers (hint: use range)

(reduce + (map #(* % %) (range 1 11)))

;; Task 11 (2 points): compute the product of 10 even numbers starting at 2 (hint: use range)

(reduce * (take 10 (drop 1 (filter even? (range)))))

;****************************************
;************ Conditionals  *************
;****************************************

(if (< x 5) 1 2)

(cond (< x 5) 1
      (= x 5) 2
      (> x 5) 3)

;; Task 12 (3 points) Write and test a function that takes a number and returns 1 if it's
;; divisible by 2 (but not 3), returns 2 if it's divisible by 3 (but not 2),
;; 3 if it's divisible by both, and 4 if it's divisible by neither.
(defn divisible? [n divisor]
  (zero? (mod n divisor)))

(defn divisible [x]
  (cond (and (divisible? x 2) (not (divisible? x 3))) 1
        (and (not (divisible? x 2)) (divisible? x 3)) 2
        (and (divisible? x 2) (divisible? x 3)) 3
        (and (not (divisible? x 2)) (not (divisible? x 3))) 4))

(= (divisible 1) 4)
(= (divisible 2) 1)
(= (divisible 3) 2)
(= (divisible 6) 3)
(= (divisible 12) 3)

;****************************************
;************* Recursion  ***************
;****************************************

; recursive sum:
(defn sum[coll]
  (if (empty? coll)
      0
      (+ (first coll) (sum (rest coll)))))

(sum v1)

;; Task 13 (3 points): write and test a recursive function that computes factorial of n.
(defn factorial [n]
  (if (= n 0)
    1
    (* n (factorial (- n 1)))))

(= (factorial 5) 120)
(= (factorial 0) 1)
(= (factorial 1) 1)

;; Task 14 (5 points): write and test a recursive function that takes a list and returns
;; the same list with the last element replaced by 5. The empty list should remain unchanged.

(defn replaceLast [l]
  (if (empty? l)
    l
    (if (empty? (rest l))
      '(5)
      (cons (first l) (replaceLast (rest l))))))

;; Task 15 (8 points): read about Clojure maps and sets and write at least 4 examples
;; of using each of them, demonstrating different operations. Explain in comments what happens.
