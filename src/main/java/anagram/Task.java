package anagram;

import anagram.Words.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Properties;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONObject;
import org.json.JSONArray;

public class Task {

    /**
     * Generates all permutations of the input string.
     *
     * @param s The input string for which permutations are generated.
     * @return A set containing all permutations of the input string.
     */
    public static Set<String> generatePermutations(String s) {
        if (s == null || s.isEmpty()) {
            return new TreeSet<>();
        }
        s = s.toLowerCase();
        Set<String> set = new TreeSet<>();
        generatePermutations("", s, set);
        return set;
    }

    /**
     * Helper method to recursively generate permutations.
     *
     * @param prefix The current prefix being built.
     * @param s      The remaining characters.
     * @param set    The set to store the permutations.
     */
    private static void generatePermutations(String prefix, String s, Set<String> set) {
        int n = s.length();
        if (n == 0) {
            set.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                generatePermutations(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, n), set);
            }
        }
    }

    /**
     * Gets the API key from the application.properties file.
     *
     * @return The API key.
     */
    private static String getApiKey() {
        Properties prop = new Properties();
        try (InputStream input = Task.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new FileNotFoundException("unable to find application.properties");
            }
            prop.load(input);
            return prop.getProperty("apiKey");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Creates an HTTP request for a given word using the wordsAPI endpoint.
     *
     * @param word The word for which the request is created.
     * @return The HTTP request object.
     */
    private static Request createRequest(String word) {
        String apiKey = getApiKey();
        return new Request.Builder()
                .url("https://wordsapiv1.p.rapidapi.com/words/" + word)
                .get()
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
                .build();
    }

    /**
     * Executes an HTTP request and returns the response.
     *
     * @param request The HTTP request to be executed.
     * @return The HTTP response object.
     * @throws IOException If an I/O error occurs during the request execution.
     */
    private static Response executeRequest(Request request) throws IOException {
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }

    /**
     * Parses the JSON response and creates a Word object.
     *
     * @param response The HTTP response containing JSON data.
     * @return The Word object created from the JSON data.
     * @throws IOException If an I/O error occurs during response parsing.
     */
    private static JSONObject parseResponse(Response response) throws IOException {
        return new JSONObject(response.body().string());
    }

    /**
     * Creates a Word object from a JSON object.
     *
     * @param jsonObject The JSON object containing word data.
     * @return The Word object created from the JSON object.
     */
    private static Word createWordFromJson(JSONObject jsonObject) {
        String word = jsonObject.getString("word");
        JSONArray results = jsonObject.getJSONArray("results");
        List<Meaning> meanings = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            String definition = result.getString("definition");
            String partOfSpeech = result.getString("partOfSpeech");

            Object pronunciationObject = jsonObject.opt("pronunciation");
            String pronunciation;

            if (pronunciationObject instanceof JSONObject) {
                JSONObject pronunciationJsonObject = (JSONObject) pronunciationObject;
                if ("noun".equals(partOfSpeech)) {
                    pronunciation = pronunciationJsonObject.optString("noun",
                            pronunciationJsonObject.optString("all", "No pronunciation available"));
                } else if ("verb".equals(partOfSpeech)) {
                    pronunciation = pronunciationJsonObject.optString("verb",
                            pronunciationJsonObject.optString("all", "No pronunciation available"));
                } else if ("adjective".equals(partOfSpeech)) {
                    pronunciation = pronunciationJsonObject.optString("adjective",
                            pronunciationJsonObject.optString("all", "No pronunciation available"));
                } else {
                    pronunciation = pronunciationJsonObject.optString("all", "No pronunciation available");
                }
            } else {
                pronunciation = pronunciationObject != null ? pronunciationObject.toString()
                        : "No pronunciation available";
            }
            meanings.add(new Meaning(definition, partOfSpeech, pronunciation));
        }
        return new Word(word, meanings);
    }

    /**
     * Handles the case where no matching word is found.
     *
     * @param word The word for which no matching word is found.
     */
    private static void handleNotFoundWord(String word) {
        // System.out.println("Error: No matching word for '" + word + "'");
    }

    /**
     * Retrieves word data for a given word using the wordsAPI.
     *
     * @param word The word for which data is retrieved.
     * @return The Word object containing data for the given word.
     */
    public static Word getWordData(String word) {
        Request request = createRequest(word);
        try {
            Response response = executeRequest(request);
            JSONObject jsonObject = parseResponse(response);
            if (!jsonObject.has("results")) {
                handleNotFoundWord(word);
                return null;
            }
            Word newWord = createWordFromJson(jsonObject);
            return newWord;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Finds all valid permutations of a given word.
     *
     * @param word The word for which valid permutations are found.
     * @return A list of Word objects containing data for all valid permutations of
     *         the word.
     */
    public static List<Word> findValidPermutations(String word) {
        if (word == null || word.isEmpty()) {
            return new ArrayList<>();
        }
        word = word.toLowerCase();
        Set<String> permutations = generatePermutations(word);
        return findValidPermutations(permutations);
    }

    /**
     * Finds all valid permutations of a given word.
     *
     * @param permutations The set of all permutations of the word.
     * @return A list of Word objects containing data for all valid permutations of
     *         the word.
     */
    public static List<Word> findValidPermutations(Set<String> permutations) {
        List<Word> validWords = new ArrayList<>();

        for (String permutation : permutations) {
            if (!permutation.matches("[a-zA-Z]+")) {
                break;
            }

            Word word = getWordData(permutation);
            if (word != null) {
                validWords.add(word);
            }
        }

        return validWords;
    }

    public static void main(String[] args) {
        System.out.println("Permutations: ");
        Set<String> permutations = generatePermutations("dog");
        for (String permutation : permutations) {
            System.out.println(permutation);
        }

        /*
         * System.out.println("Valid permutations: ");
         * List<Word> validWords = findValidPermutations(permutations);
         * for (Word word : validWords) {
         * System.out.println(word.toString());
         * }
         */

        System.out.println("Valid permutations: ");
        List<Word> validWords2 = findValidPermutations("dog");
        for (Word word : validWords2) {
            System.out.println(word.toString());
        }
    }
}