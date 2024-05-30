import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

public class RiddleSolver {

    public static void main(String[] args) throws IOException, URISyntaxException {
        long startGet = System.nanoTime();
        Set<String> dictionary= new EnglishDictionary().get();
        long endGet = System.nanoTime();

        long startTime = System.nanoTime();
        WordReducer wordReducer = new WordReducer(dictionary,getAll9letterWords(dictionary));

        System.out.printf("Words found : %d %n",wordReducer.reduce());

        long endTime = System.nanoTime();

        double getDuration =  (endGet-startGet) / 1_000_000.0;
        double duration = (endTime-startTime) / 1_000_000.0;

        System.out.printf("Get Request time:%f milliseconds %n", getDuration);
        System.out.printf("Execution time: %f milliseconds %n", duration);

    }

    /**
     * Get only 9 letters words from the dictionary to check
     * @param dict - source dictionary
     * @return nine letters words from the dictionary
     */
    public static Set<String> getAll9letterWords(Set<String> dict){


        return  dict.stream()
                .reduce(
                        new HashSet<>()   ,   // Identity: an empty HashSet
                        (set, word) -> {
                            if (  word.length() == 9) { // Accumulator: add each word to the set
                                set.add(word);
                            }
                            return set;
                        },
                        (set1, set2) -> {     // Combiner: combine two sets
                            set1.addAll(set2);
                            return set1;
                        }
                );

    }
}
