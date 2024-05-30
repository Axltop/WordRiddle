import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.stream.Collectors;

public class EnglishDictionary  {
    private final Set<String> dict;


    public EnglishDictionary() throws IOException, URISyntaxException {
        dict = loadAllWords();
    }

    public Set<String> get() {
        return dict;
    }

    /**
     * Get all english words from URI
     * @return Set of all english words
     * @throws IOException when the file was not read correctly
     * @throws URISyntaxException on URL wrong
     */
    private Set<String> getEnglishDictionary() throws IOException, URISyntaxException {
        URI wordsUrl = new URI("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");

        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                wordsUrl.toURL().openConnection().getInputStream()
                        )
                )
        ) {
            return br.lines().skip(2).collect(Collectors.toSet());
        }

    }

    /**
     * Add valid one lettered words to the dictionary in accordance with the task description
     * @param dict dictionary to add one-lettered words to
     */
    private void addValidOneLetterWords(Set<String> dict) {
        dict.add("I");
        dict.add("A");
    }

    /**
     * Get all words
     * @return Set of all english words with added one-letter words
     */
    private Set<String> loadAllWords() throws IOException, URISyntaxException {
        Set<String> res = getEnglishDictionary();
        addValidOneLetterWords(res);
        return res;
    }

}
