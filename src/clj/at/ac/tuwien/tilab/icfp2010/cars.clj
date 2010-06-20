(ns at.ac.tuwien.tilab.icfp2010.cars
  (:use at.ac.tuwien.tilab.icfp2010.ternary)
  (:import [at.ac.tuwien.tilab.icfp2010 Fuel]))

(defn long-arr [a]
  (into-array (. Long TYPE) a))

(def long-array-type (type (long-arr [1 2 3])))

(defn make-java-fuel [matrix]
  (Fuel. (count matrix)
	 (into-array long-array-type (map long-arr matrix))))

(defn unjava-fuel [fuel]
  (map seq (map seq (.contents fuel))))

(def sample-car [{:upper [0 0 0] :is-main true :lower [0 1 0]}])
(def sample-fuels (map make-java-fuel [[[1 2]
					[1 1]]
				       [[1 0]
					[1 1]]]))
(def test-car [{:upper [0 1 0 1 0 0], :is-main true, :lower [1 0 0 1 0 0 1]}])
(def car-2685 (second (parse-car "1222200001010000022220011001000010")))
(def car-2685-fuels (map #(make-java-fuel (apply map list %)) '(((1 0 1 3) (0 0 0 0) (0 2 0 6) (0 0 0 0)) ((4 8 0 17) (0 2 1 0) (1 8 0 12) (0 2 0 5)))))

(def car-13252 (second (parse-car "2222001100222222000000220101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010111022222200000022112121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212122000022222200000022222001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012222000010111222000220011022220000101112220002200122222200000022110101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101022222200000022212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121011122222200000100022001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200122001220012200110122000")))

(def random-6-fuels (map (fn [_] (Fuel/randomFuel (java.util.Random.) 2 5)) (range 6)))

(defn car-tanks [car]
  (if (empty? car)
    0
    (apply max (map (fn [chamber]
		      (let [upper (:upper chamber)
			    lower (:lower chamber)]
			(max (if (empty? upper) 0 (inc (apply max upper)))
			     (if (empty? lower) 0 (inc (apply max lower))))))
		    car))))

(defn car-sections [car]
  (if (empty? car)
    0
    (apply max (map (fn [chamber]
		      (max (count (:upper chamber)) (count (:lower chamber))))
		    car))))

(defn fuels-ingredients [fuels]
  (.numIngredients (first fuels)))

(defn run-pipe [pipe fuels]
  (if (empty? pipe)
    (Fuel/defaultFuel (fuels-ingredients fuels))
    (loop [fuel (nth fuels (first pipe))
	   pipe (rest pipe)]
      (if (empty? pipe)
	fuel
	(recur (.multiply (nth fuels (first pipe)) fuel)
	       (rest pipe))))))

(defn run-chamber [chamber fuels]
  (let [upper (run-pipe (:upper chamber) fuels)
	lower (run-pipe (:lower chamber) fuels)
	diff (.subtract upper lower)]
    diff))

(defn car-fuels-score [car fuels]
  (let [chamber-scores (map (fn [chamber]
			      (.simpleScore (run-chamber chamber fuels)
					    (:is-main chamber)))
			    car)]
    (apply min chamber-scores)))

(defn random-pipe [random num-tanks max-sections]
  (apply vector (map (fn [_]
		       (.nextInt random num-tanks))
		     (range (.nextInt random (inc max-sections))))))

(defn random-chamber [random num-tanks max-sections]
  {:upper (random-pipe random num-tanks max-sections)
   :is-main true
   :lower (random-pipe random num-tanks max-sections)})

(defn mutate-pipe [random pipe num-tanks max-sections]
  (let [sects (count pipe)]
    (case (.nextInt random 3)
	  0 (if (zero? sects)
	      pipe
	      (let [i (.nextInt random sects)
		    tank (.nextInt random num-tanks)]
		(assoc pipe i tank)))
	  1 (if (zero? sects)
	      pipe
	      (let [i (.nextInt random sects)]
		(apply vector (concat (take i pipe) (drop (inc i) pipe)))))
	  2 (if (= sects max-sections)
	      pipe
	      (let [i (.nextInt random (inc sects))
		    tank (.nextInt random num-tanks)]
		(apply vector (concat (take i pipe) [tank] (drop i pipe))))))))

(defn mutate-chamber [random chamber num-tanks max-sections]
  (let [bit (.nextInt random 2)
	upper (:upper chamber)
	upper (if (zero? bit) (mutate-pipe random upper num-tanks max-sections) upper)
	lower (:lower chamber)
	lower (if (zero? bit) lower (mutate-pipe random lower num-tanks max-sections))]
    {:upper upper
     :is-main (:is-main chamber)
     :lower lower}))
