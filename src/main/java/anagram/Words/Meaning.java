package anagram.Words;

/**
 * A class representing the meaning of a word, including its definition, part of
 * speech, and pronunciation.
 */
public class Meaning {
    private String definition;
    private String partOfSpeech;
    private String pronunciation;

    /**
     * Constructs a Meaning object with the specified definition, part of speech,
     * and pronunciation.
     *
     * @param definition    The definition of the word.
     * @param partOfSpeech  The part of speech of the word.
     * @param pronunciation The pronunciation of the word.
     */
    public Meaning(String definition, String partOfSpeech, String pronunciation) {
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        this.pronunciation = pronunciation;
    }

    /**
     * Gets the definition of the word.
     *
     * @return The definition of the word.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Gets the part of speech of the word.
     *
     * @return The part of speech of the word.
     */
    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    /**
     * Gets the pronunciation of the word.
     *
     * @return The pronunciation of the word.
     */
    public String getPronunciation() {
        return pronunciation;
    }
}
