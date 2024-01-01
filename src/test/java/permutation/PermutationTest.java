package permutation;

import anagram.Task;
import org.junit.Test;
import org.junit.Assert;

import java.util.Set;

public class PermutationTest {

    @Test
    public void testGenerate() {
        Set<String> permutations = Task.generatePermutations("abc");
        Assert.assertEquals(6, permutations.size());
        Assert.assertTrue(permutations.contains("abc"));
        Assert.assertTrue(permutations.contains("acb"));
        Assert.assertTrue(permutations.contains("bac"));
        Assert.assertTrue(permutations.contains("bca"));
        Assert.assertTrue(permutations.contains("cab"));
        Assert.assertTrue(permutations.contains("cba"));
    }

    @Test
    public void testGenerateEmpty() {
        Set<String> permutations = Task.generatePermutations("");
        Assert.assertTrue(permutations.isEmpty());
    }

    @Test
    public void testGenerateNull() {
        Set<String> permutations = Task.generatePermutations(null);
        Assert.assertTrue(permutations.isEmpty());
    }

    @Test
    public void testGenerateSingle() {
        Set<String> permutations = Task.generatePermutations("a");
        Assert.assertEquals(1, permutations.size());
        Assert.assertTrue(permutations.contains("a"));
    }

    @Test
    public void testGenerateDouble() {
        Set<String> permutations = Task.generatePermutations("ab");
        Assert.assertEquals(2, permutations.size());
        Assert.assertTrue(permutations.contains("ab"));
        Assert.assertTrue(permutations.contains("ba"));
    }

    @Test
    public void testGenerateDuplicates() {
        Set<String> permutations = Task.generatePermutations("aab");
        Assert.assertEquals(3, permutations.size());
        Assert.assertTrue(permutations.contains("aab"));
        Assert.assertTrue(permutations.contains("aba"));
        Assert.assertTrue(permutations.contains("baa"));
    }

    @Test
    public void testGenerateUpperCase() {
        Set<String> permutations = Task.generatePermutations("ABC");
        Assert.assertEquals(6, permutations.size());
        Assert.assertTrue(permutations.contains("abc"));
        Assert.assertTrue(permutations.contains("acb"));
        Assert.assertTrue(permutations.contains("bac"));
        Assert.assertTrue(permutations.contains("bca"));
        Assert.assertTrue(permutations.contains("cab"));
        Assert.assertTrue(permutations.contains("cba"));
    }

    @Test
    public void testGenerateUpperDuplicate() {
        Set<String> permutations = Task.generatePermutations("aAb");
        Assert.assertEquals(3, permutations.size());
        Assert.assertTrue(permutations.contains("aab"));
        Assert.assertTrue(permutations.contains("aba"));
        Assert.assertTrue(permutations.contains("baa"));
    }

    @Test
    public void testGenerateSpecial() {
        Set<String> permutations = Task.generatePermutations("a!b");
        Assert.assertEquals(6, permutations.size());
        Assert.assertTrue(permutations.contains("!ab"));
        Assert.assertTrue(permutations.contains("!ba"));
        Assert.assertTrue(permutations.contains("a!b"));
        Assert.assertTrue(permutations.contains("ab!"));
        Assert.assertTrue(permutations.contains("b!a"));
        Assert.assertTrue(permutations.contains("ba!"));
    }
}
