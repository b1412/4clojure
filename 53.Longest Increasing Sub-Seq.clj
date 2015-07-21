(fn [coll]
  (->> (reduce (fn [s e] (let [c (last (last s))]
                          (if (= c (dec e))
                            (vec (concat (take (dec (count s)) s) [(conj (last s) e)]))
                            (conj s [e])))) [] coll)
       (reverse)
       (apply max-key count)
       (#(if (> (count %) 1) % []))))
