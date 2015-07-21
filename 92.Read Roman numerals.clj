(fn [s]
  (let [m {"I" 1
           "V" 5
           "X" 10
           "L" 50
           "C" 100
           "D" 500
           "M" 1000}
        f #(->> % (str) (m))]
    (first (reduce (fn [[v pre] cur]
                     (if (<= (f cur) (f pre))
                       [(+ (f cur) v) cur]
                       [(- (+ (f cur) v) (* 2 (f pre))) cur])) [0 'M] s))))
