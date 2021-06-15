import java.util.ArrayList;
import java.util.List;

public class Range {
    private String range;

    public Range(String range){
        this.range = range;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public static boolean isInclusiveStart(String range){
        return range.startsWith("(");
    }

    public static boolean isInclusiveEnd(String range){
        return range.endsWith(")");
    }

    public boolean checkNotContains(List<Integer> firstRangeList, List<Integer> secondRangeList){
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

    public boolean notContains(String otherRange){
        int firstNumber = getFirstNumber(range);
        int secondNumber = getLastNumber(range);

        int otherRangeFirst = getFirstNumber(otherRange);
        int otherRangeSecond = getLastNumber(otherRange);

        List<Integer> rangeList = createListOfNumber(range,firstNumber, secondNumber);
        List<Integer> otherRangeList = createListOfNumber(otherRange, otherRangeFirst, otherRangeSecond);

        return checkNotContains(rangeList, otherRangeList);
    }

    public static void main(String[] args) {

        boolean newRange = new Range("[2,6]").notContains("[3,6)");
        System.out.println(newRange);
    }
}
