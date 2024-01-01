package anagram.Words;

import java.util.List;

/**
 * A class representing a word and its associated meanings.
 */
public class Word {
    private String word;
    private List<Meaning> meanings;

    /**
     * Constructs a Word object with the specified word and list of meanings.
     *
     * @param word     The word to represent.
     * @param meanings The list of meanings associated with the word.
     */
    public Word(String word, List<Meaning> meanings) {
        this.word = word;
        this.meanings = meanings;
    }

    /**
     * Gets the word represented by this object.
     *
     * @return The word represented by this object.
     */
    public String getWord() {
        return word;
    }

    /**
     * Gets the list of meanings associated with the word.
     *
     * @return The list of meanings associated with the word.
     */
    public List<Meaning> getMeanings() {
        return meanings;
    }

    /**
     * Generates a string representation of the Word object.
     *
     * @return A string representation of the Word object, including word,
     *         definitions, part of speech, and pronunciation.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word: ").append(word).append("\n");
        for (Meaning meaning : meanings) {
            sb.append("Definition: ").append(meaning.getDefinition()).append("\n");
            sb.append("Part of Speech: ").append(meaning.getPartOfSpeech()).append("\n");
            sb.append("Pronunciation: ").append(meaning.getPronunciation()).append("\n");
            sb.append("\n");
        }
        return sb.toString();
    }
}
