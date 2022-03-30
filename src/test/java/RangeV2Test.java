import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RangeV2Test {

    @Test // ( )
    public void range_Is_Inclusive_In_The_Start(){
        Assert.assertTrue(isInclusiveStart("(3,6)"));
    }

    @Test // ( )
    public void range_Is_Inclusive_Int_The_End(){
        Assert.assertTrue(isInclusiveEnd("(3,6)"));
    }

    @Test // (3
    public void transform_Digit_Start_Of_Range(){
        Assert.assertEquals("(3",transformDigit("(3,6)")[0]);
    }

    @Test // (6
    public void transform_Digit_End_Of_Ranger(){
        Assert.assertEquals("6)",transformDigit("(3,6)")[1]);
    }

    @Test // 3
    public void get_First_Number_Of_Range(){
        Assert.assertEquals(3,getFirstNumber("(3,6)"));
    }

    @Test // 3
    public void get_Last_Number_Of_Range(){
        Assert.assertEquals(6,getLastNumber("(3,6)"));
    }

    @Test // 3 - 6  [4,5]
    public void create_List_Number(){
        String range = "(3,6)";
        int firstNumber = getFirstNumber(range);
        int lastNumber = getLastNumber(range);

        Assert.assertEquals(2,createListNumber("(3,6)",firstNumber,lastNumber).size());
    }

    @Test // 3 - 6  [4,5] // [7,10)
    public void check_Not_Contains(){
        String range = "(3,8)";
        int firstNumber = getFirstNumber(range);
        int lastNumber = getLastNumber(range);

        String otherRange = "[7,10)";
        int firstOtherNumber = getFirstNumber(otherRange);
        int lastOtherNumber = getLastNumber(otherRange);

        List<Integer> rangeList = createListNumber(range,firstNumber,lastNumber);
        List<Integer> otherRangeList = createListNumber(otherRange,firstOtherNumber,lastOtherNumber);
        Assert.assertFalse(notContains(rangeList,otherRangeList));
    }

    @Test // [2,5) = 2,3,4 // [3,10) = 3,4,5,6,7,8,9
    public void check_Not_Contains_Range_1(){
        String range = "[2,5)";
        int firstNumber = getFirstNumber(range);
        int lastNumber = getLastNumber(range);

        String otherRange = "[3,10)";
        int firstOtherNumber = getFirstNumber(otherRange);
        int lastOtherNumber = getLastNumber(otherRange);

        List<Integer> rangeList = createListNumber(range,firstNumber,lastNumber);
        List<Integer> otherRangeList = createListNumber(otherRange,firstOtherNumber,lastOtherNumber);
        Assert.assertFalse(notContains(rangeList,otherRangeList));
    }

    @Test // [3,5) = 3,4 // [2,10)= 2,3,4,...
    public void check_Not_Contains_Range_2(){
        String range = "[3,5)";
        int firstNumber = getFirstNumber(range);
        int lastNumber = getLastNumber(range);

        String otherRange = "[2,10)";
        int firstOtherNumber = getFirstNumber(otherRange);
        int lastOtherNumber = getLastNumber(otherRange);

        List<Integer> rangeList = createListNumber(range,firstNumber,lastNumber);
        List<Integer> otherRangeList = createListNumber(otherRange,firstOtherNumber,lastOtherNumber);
        Assert.assertFalse(notContains(rangeList,otherRangeList));
    }

    @Test // [3,5) = 3,4 // [5,9)= 5,6,7,8
    public void check_Not_Contains_Range_4(){
        String range = "[3,5)";
        int firstNumber = getFirstNumber(range);
        int lastNumber = getLastNumber(range);

        String otherRange = "[5,9)";
        int firstOtherNumber = getFirstNumber(otherRange);
        int lastOtherNumber = getLastNumber(otherRange);

        List<Integer> rangeList = createListNumber(range,firstNumber,lastNumber);
        List<Integer> otherRangeList = createListNumber(otherRange,firstOtherNumber,lastOtherNumber);
        Assert.assertFalse(notContains(rangeList,otherRangeList));
    }


    public boolean notContains(List<Integer> range, List<Integer> otherRange){
        for(int x = range.get(0); x <= range.get(range.size() - 1); x++){
            for(int y = otherRange.get(0); y <= otherRange.get(otherRange.size() - 1); y++){
                if(x == y){
                    return false;
                }
            }
        }
        return true;
    }

    public List<Integer> createListNumber(String range, int firstNumber, int lastNumber){
        List<Integer> newListRange = new ArrayList<>();
        int newFirstNumber = isInclusiveStart(range) ? firstNumber + 1 : firstNumber;
        int newLastNumber = isInclusiveEnd(range) ? lastNumber - 1 : lastNumber;

        for(int x = newFirstNumber; x <= newLastNumber; x++){
            newListRange.add(x);
        }
        return newListRange;
    }

    public int getFirstNumber(String range){
        String newRange = transformDigit(range)[0];
        String value = newRange.replaceAll("[^0-9]", "");
        int number = value.length() >= 2 ? 2 : 1;
        return Integer.parseInt(value.substring(0, number));
    }

    public int getLastNumber(String range){
        String newRange = transformDigit(range)[1];
        String value = newRange.replaceAll("[^0-9]", "");
        int number = value.length() >= 2 ? 2 : 1;
        return Integer.parseInt(value.substring(value.length() - number));
    }

    // (3,6) =  (3 [0]
    public String[] transformDigit(String range){
        return range.split(",");
    }

    public boolean isInclusiveStart(String range){
        return range.startsWith("(");
    }

    public boolean isInclusiveEnd(String range){
        return range.endsWith(")");
    }

}
