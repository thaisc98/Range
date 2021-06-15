import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RangeTest {

    @Test
    public void check_Range_Is_Inclusive_Start(){
        Assert.assertTrue(Range.isInclusiveStart("(3,6]"));
    }

    @Test
    public void check_Range_Is_Inclusive_End(){
        Assert.assertTrue(Range.isInclusiveEnd("[3,6)"));
    }

    @Test
    public void cut_Range_Of_First_Number(){
        Assert.assertEquals(3, getFirstNumber("[3,6)"));
    }

    @Test
    public void cut_Range_Of_Last_Number(){
        Assert.assertEquals(6, getLastNumber("[3,6)"));
    }

    @Test
    public void build_List_of_Range(){
        String range = "[3,6)";
        int firstNumber = getFirstNumber(range);
        int secondNumber = getLastNumber(range);

        Assert.assertEquals(3, createListOfNumber(range, firstNumber, secondNumber).size());
    }

    @Test
    public void check_Not_Contains_of_Range(){
        //[2,6] -> 2,3,4,5,6   otherRange = (3,6) => 4 -> false
        String firstRange = "[2,6]"; // -> 5
        int firstNumberRange = getFirstNumber(firstRange);
        int secondNumberRange = getLastNumber(firstRange);

        String otherRange = "[3,6)"; // 3
        int otherRangeFirst = getFirstNumber(otherRange);
        int otherRangeSecond = getLastNumber(otherRange);

        List<Integer> firstRangeList = createListOfNumber(firstRange, firstNumberRange, secondNumberRange);
        List<Integer> secondRangeList = createListOfNumber(otherRange, otherRangeFirst, otherRangeSecond);
        Assert.assertFalse(checkNotContains(firstRangeList, secondRangeList));
    }

    public boolean checkNotContains(List<Integer> firstRangeList,  List<Integer> secondRangeList){
        int count = 0;
        for(int x = firstRangeList.get(0); x <= firstRangeList.get(firstRangeList.size() - 1); x++){
            for(int y = secondRangeList.get(0); y <= secondRangeList.get(secondRangeList.size() - 1); y++){
                if(x == y){
                    count++;
                }
            }
        }
        return count == 0 ? true : false;
    }

    public List<Integer> createListOfNumber(String range, int firstNumber, int lastNumber){
        int newFirstNumber = firstNumber;
        int newSecondNumber = lastNumber;

        List<Integer> numbers = new ArrayList<>();

        if(Range.isInclusiveStart(range)){
            newFirstNumber = firstNumber + 1;
        }

        if(Range.isInclusiveEnd(range)){
            newSecondNumber = newSecondNumber - 1;
        }

        for(int x = newFirstNumber ; x <= newSecondNumber; x++){
            numbers.add(x);
        }

        return numbers;
    }

    public int getFirstNumber(String range){
        String value = range.replaceAll("[^0-9]", ""); // 36
        return Integer.parseInt(value.substring(0, 1));
    }

    public int getLastNumber(String range){
        String value = range.replaceAll("[^0-9]", "");
        return Integer.parseInt(value.substring(value.length() - 1));
    }

}
