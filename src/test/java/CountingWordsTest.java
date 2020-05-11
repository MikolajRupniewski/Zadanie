import org.junit.Assert;
import org.junit.Test;

import java.util.Map;


public class CountingWordsTest {

    @Test(expected = NullPointerException.class)
    public void shouldRaiseNullPointerException_AsPathToFileIsInvalid() {
        //given
        CountingWords countingWords = new CountingWords("invalid.txt");

        //when then
        countingWords.getAllWordsWithoutRepeats();
    }

    @Test
    public void shouldCountWordsWhichAreCorrect() {
        //given
        CountingWords countingWords = new CountingWords("test.txt");

        //when
        Map<String, CountingWords.Entry> allWordsWithoutRepeats = countingWords.getAllWordsWithoutRepeats();

        //then
        Assert.assertEquals(6, allWordsWithoutRepeats.get("A").getCounting());
        Assert.assertEquals(3, allWordsWithoutRepeats.get("expression").getCounting());
        Assert.assertEquals(Integer.valueOf(6), allWordsWithoutRepeats.get("A").getIndexes().get(3));
    }
    @Test(expected = NullPointerException.class)
    public void shouldRaiseNullPointerException_AsKeyIsInvalid() {
        //given
        CountingWords countingWords = new CountingWords("test.txt");

        //when
        Map<String, CountingWords.Entry> allWordsWithoutRepeats = countingWords.getAllWordsWithoutRepeats();

        //then
        Assert.assertEquals(0, allWordsWithoutRepeats.get("CC").getCounting());
    }
}