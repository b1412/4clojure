(defn for-science
  [ipt]
  (let [format (fn [args]
                 (->> args
                      (map-indexed (fn [idx e]
                                     (map-indexed
                                      (fn [idx2 e2] {[idx idx2] (str e2)})
                                      e)))
                      (flatten)
                      (into {})))
        go-f (fn [ipt v]
               (let [idx (format ipt)]
                 (->> v
                      (map (fn [[l r]] (->> #{[(dec l) r]
                                             [(inc l) r]
                                             [l (inc r)]
                                             [l (dec r)]}
                                           (remove #(nil? (idx %)))
                                           (remove #(#{"#"} (idx %))))))
                      (apply concat v)
                      (set))))
        m-idx ((clojure.set/map-invert (format ipt)) "M")
        c-idx ((clojure.set/map-invert (format ipt)) "C")]
    (if ((last (take 20 (iterate (partial go-f ipt) #{m-idx})))
         c-idx)
      true
      false)))
