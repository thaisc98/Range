import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RangeTest {

    @Test
    public void check_Range_Is_Inclusive_Start(){
        Assert.assertTrue(new Range("(3,6]").isInclusiveStart());
    }

    @Test
    public void check_Range_Is_Inclusive_End(){
        Assert.assertTrue(new Range("[3,6)").isInclusiveEnd());
    }

    @Test
    public void cut_Range_Of_First_Number(){
        String firstNumberRange = transformDigits("[3,10)")[0];
        int firstNumber = getFirstNumber(firstNumberRange);
        Assert.assertEquals(3,firstNumber);
    }

    @Test
    public void cut_Range_Of_Last_Number(){
        String firstNumberRange = transformDigits("[3,6)")[1];
        int firstNumber = getFirstNumber(firstNumberRange);
        Assert.assertEquals(6, firstNumber);
    }

    @Test
    public void cut_Range_Of_Last_Number_Upper_Than_Ten(){
        String firstNumberRange = transformDigits("[3,10)")[0];
        int firstNumber = getFirstNumber(firstNumberRange);
        Assert.assertEquals(3, firstNumber);
    }

    @Test
    public void checkFirstNumberStartUpperThanTen() {
        String firstNumberRange = transformDigits("[3,11)")[1];
        int firstNumber = getFirstNumber(firstNumberRange);
        Assert.assertEquals(11, firstNumber);
    }


    @Test
    public void build_List_of_Range(){
        String range = "(3,6)";

        int firstNumber = getFirstNumber(range);

        int secondNumber = getLastNumber(range);

        Assert.assertEquals(3, createListOfNumber(range, firstNumber, secondNumber).size());
    }

    @Test
    public void check_Not_Contains_of_Range(){
        String firstRange = "[2,6]";
        int firstNumberRange = getFirstNumber(firstRange);
        int secondNumberRange = getLastNumber(firstRange);

        String otherRange = "(3,6)";
        int otherRangeFirst = getFirstNumber(otherRange);
        int otherRangeSecond = getLastNumber(otherRange);

        List<Integer> firstRangeList = createListOfNumber(firstRange, firstNumberRange, secondNumberRange);
        List<Integer> secondRangeList = createListOfNumber(otherRange, otherRangeFirst, otherRangeSecond);
        Assert.assertFalse(checkNotContains(firstRangeList, secondRangeList));
    }

    @Test
    public void tranform_Digits_First () {
        String string = "(3,10)";
        Assert.assertEquals("(3", transformDigits(string)[0]);
    }

    @Test
    public void tranform_Digits_Last () {
        String string = "(3,10]";
        Assert.assertEquals("10]", transformDigits(string)[1]);
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
        int newFirstNumber =   new Range(range).isInclusiveStart() ?  firstNumber + 1 : firstNumber;
        int newSecondNumber = new Range(range).isInclusiveEnd() ?  lastNumber - 1 : lastNumber;
        List<Integer> numbers = new ArrayList<>();

        for(int x = newFirstNumber ; x <= newSecondNumber; x++){
            numbers.add(x);
        }
        return numbers;
    }

    public int getFirstNumber(String range) {
        String newRange = transformDigits(range)[0];
        String value = newRange.replaceAll("[^0-9]", "");
        int number = value.length() >= 2 ? 2 : 1;
        return Integer.parseInt(value.substring(0, number));
    }

    public int getLastNumber(String range) {
        String newRange = transformDigits(range)[1];
        String value = newRange.replaceAll("[^0-9]", "");
        int newNumber = value.length() >= 2 ? 2 : 1;
        return Integer.parseInt(value.substring(value.length() - newNumber));
    }

    public  String[] transformDigits(String range){
        return range.split(",");
    }


}
