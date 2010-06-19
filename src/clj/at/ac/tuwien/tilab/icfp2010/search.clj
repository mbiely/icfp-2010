(ns at.ac.tuwien.tilab.icfp2010.search
  (:use at.ac.tuwien.tilab.icfp2010.circuit
	at.ac.tuwien.complang.distributor.vsc)
  (:import [at.ac.tuwien.tilab.icfp2010 Permuter IPermutationConsumer Simulator
	    SimulationPermutationConsumer SearchSimulationConsumer ISimulationConsumer PrefixPermutationConsumer]
	   [java.util Random]))

(def integer-array-type (type (into-array (. Integer TYPE) [1 2 3])))

(defn- int-arr [a]
  (into-array (. Integer TYPE) a))

(defn java-simulate [n circuit input-index input-stream]
  (doall (seq (Simulator/simulate n circuit input-index (int-arr input-stream)))))

(defn all-subpermutations [n coll]
  (if (zero? n)
    [[]]
    (mapcat (fn [x]
	      (map #(conj % x)
		   (all-subpermutations (dec n) (remove #(= % x) coll))))
	    coll)))

(defn search-prefixes [num-gates len]
  (all-subpermutations len (range (inc (* num-gates 2)))))

(defn make-simulation-permutation-consumer [n search-streams pred results-atom]
  (let [print-simulation-consumer (reify
				   ISimulationConsumer
				   (consumeSimulation [this n circuit input-index input-stream output-stream]
						      (let [output (apply vector output-stream)]
							(when (pred n circuit input-index output)
							  (swap! results-atom conj {:circuit (apply vector circuit)
										    :input-index input-index
										    :output output})))))
	search-simulation-consumer (SearchSimulationConsumer. (into-array integer-array-type (map int-arr search-streams))
							      print-simulation-consumer)]
    (SimulationPermutationConsumer. n (int-arr default-input) search-simulation-consumer)))

(defn search-circuits-with-prefix [n search-streams prefix pred]
  (let [rest-seq (remove (fn [x] (some #(= % x) prefix)) (range (inc (* n 2))))
	results (atom [])
	simulation-permutation-consumer (make-simulation-permutation-consumer n search-streams pred results)
	prefix-permutation-consumer (PrefixPermutationConsumer. (int-arr prefix) simulation-permutation-consumer)]
    (Permuter/permuteArray (int-arr rest-seq) prefix-permutation-consumer)
    @results))

(defn search-circuits [n search-streams pred]
  (let [results (atom [])
	simulation-permutation-consumer (make-simulation-permutation-consumer n search-streams pred results)]
    (Permuter/permuteRange (inc (* n 2)) simulation-permutation-consumer)
    @results))

(defn all-inputs [n]
  (if (zero? n)
    [[]]
    (let [all-sub (all-inputs (dec n))]
      (mapcat (fn [i]
		(map #(conj % i)
		     all-sub))
	      [0 1 2]))))

(defn random-inputs [n len]
  (let [rand (Random.)]
    (map (fn [x]
	   (map (fn [i]
		  (.nextInt rand 3))
		(range len)))
	 (range n))))

(def all-inputs-6 (all-inputs 6))
(def some-random-inputs (random-inputs 50 1000))

(defn check-solution [pred]
  (fn [n circuit input-index output]
    (and (pred default-input output)
	 (every? (fn [input]
		   (let [result (java-simulate n circuit input-index input)]
		     (pred input result)))
		 all-inputs-6)
	 (every? (fn [input]
		   (let [result (java-simulate n circuit input-index input)]
		     (pred input result)))
		 some-random-inputs))))

(defn simple-gen? [n circuit input-index output]
  (and (not (= (first output) 0))
       (= (second output) 0)
       (apply = (rest output))
       (= (java-simulate n circuit input-index the-key) output)
       (= (java-simulate n circuit input-index [2 1 2 0 2 0 1 2 2 0 1 1 0 1 1 1 0]) output)
       (every? (fn [input]
		 (let [result (java-simulate n circuit input-index input)]
		   ;;(println result)
		   (= result (take (count input) output))))
	       all-inputs-6)
       ;;(println "beidel")
       (every? (fn [input]
		 (let [result (java-simulate n circuit input-index input)]
		   (and (= (take (count output) result) output)
			(apply = (rest result)))))
	       some-random-inputs)))

(defn is-key? [n circuit input-index output]
  (= output the-key))

(defn increment-stream [s n]
  (map #(mod (+ % n) 3) s))

(defn is-incrementer? [x]
  (let [default-input-increment ]
    (check-solution (fn [input result]
		      (= result (increment-stream input x))))))

(def is-identity? (check-solution =))

(defn is-constant-x? [x]
  (let [proper? (fn [s]
		  (and (= (first s) x)
		       (apply = s)))]
    (check-solution (fn [input output] (proper? output)))))

(defn decode-wish [name arg]
  (case name
	'incrementer [(is-incrementer? arg) [(increment-stream default-input arg)]]
	'constant [(is-constant-x? arg) []]))

(vsc-fn search-wish-with-prefix 1 [n prefix name arg]
	(let [[pred outputs] (decode-wish name arg)]
	  (search-circuits-with-prefix n outputs prefix pred)))
