package validPermutation;

import anagram.Task;
import anagram.Words.Word;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.Set;

public class ValidTest {

    @Test
    public void testValid() {
        Set<String> permutations = Task.generatePermutations("dog");
        List<Word> validWords = Task.findValidPermutations(permutations);
        Assert.assertEquals(2, validWords.size());
        Assert.assertEquals("dog", validWords.get(0).getWord());
        Assert.assertEquals("god", validWords.get(1).getWord());
    }

    @Test
    public void testValidEmpty() {
        List<Word> validWords = Task.findValidPermutations("");
        Assert.assertTrue(validWords.isEmpty());
    }

    @Test
    public void testValidNull() {
        List<Word> validWords = Task.findValidPermutations((String)null);
        Assert.assertTrue(validWords.isEmpty());
    }

    @Test
    public void testValidNoPronunciation() {
        List<Word> validWords = Task.findValidPermutations("asdf");
        Assert.assertEquals(2, validWords.size());
        Assert.assertEquals("fda", validWords.get(1).getWord());
        Assert.assertEquals("No pronunciation available", validWords.get(1).getMeanings().get(0).getPronunciation());
    }

    @Test
    public void testValidZeroValid() {
        List<Word> validWords = Task.findValidPermutations("qw");
        Assert.assertTrue(validWords.isEmpty());
    }

    @Test
    public void testValidSpecialCharacters() {
        List<Word> validWords = Task.findValidPermutations("a#b");
        Assert.assertTrue(validWords.isEmpty());
    }

    @Test
    public void testValidFromPermutation() {
        Set<String> permutations = Task.generatePermutations("dog");
        List<Word> validWords = Task.findValidPermutations(permutations);
        Assert.assertEquals(2, validWords.size());
        Assert.assertEquals("dog", validWords.get(0).getWord());
        Assert.assertEquals("god", validWords.get(1).getWord());
    }

    @Test
    public void testValidMultipleCalls() {
        List<Word> validWords = Task.findValidPermutations("cat");
        validWords = Task.findValidPermutations("dog");
        Assert.assertEquals(2, validWords.size());
        Assert.assertEquals("dog", validWords.get(0).getWord());
        Assert.assertEquals("god", validWords.get(1).getWord());
    }
}
