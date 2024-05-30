
import java.util.HashSet;
import java.util.Set;
public class WordReducer {

        private final Set<String> dict;
        private final Set<String> wordsToCheck;


        public WordReducer(Set<String> dict, Set<String> wordsToCheck) {
            this.dict = dict;
            this.wordsToCheck = wordsToCheck;
        }

    /**
     * loop over the words and check if they meet the condition for match
     * @return number of matched words
     */
    public int reduce(){
           return wordsToCheck.stream().reduce(
                    new HashSet<>(),
                    (set,word) -> {
                        if(canReduce(word)){
                            set.add(word);
                        }
                        return set;
                    },
                    (set1, set2) -> {     // Combiner: combine two sets
                        set1.addAll(set2);
                        return set1;
                    }
            ).size();

        }

    /**
     * Check if word match the riddle condition
     * @param word - word to check
     * @return boolean if the condition is met or not
     */
    public boolean canReduce(String word) {
            // Base case: if the word is empty, return true
            if (word.isEmpty()) {
                return true;
            }

            // Check if the current word is in the dictionary
            if (!dict.contains(word)) {
                return false;
            }

            // Try removing each character from the word and recursively check the resulting word
            for (int i = 0; i < word.length(); i++) {
                String reducedWord = word.substring(0, i) + word.substring(i + 1);
                if (canReduce(reducedWord)) {
                    return true;
                }
            }

            return false;
        }


}



