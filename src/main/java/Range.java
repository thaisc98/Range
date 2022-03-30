import java.util.ArrayList;
import java.util.List;

public class Range {
    private String range;

    public Range(String range) {
        this.range = range;
    }


    public boolean isInclusiveStart() {
        return this.range.startsWith("(");
    }

    public boolean isInclusiveEnd() {
        return this.range.endsWith(")");
    }

    public boolean checkNotContains(List<Integer> firstRangeList, List<Integer> secondRangeList) {
        for (int x = firstRangeList.get(0); x <= firstRangeList.get(firstRangeList.size() - 1); x++) {
            for (int y = secondRangeList.get(0); y <= secondRangeList.get(secondRangeList.size() - 1); y++) {
                if (x == y) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Integer> createListOfNumber(String range, int firstNumber, int lastNumber) {
        List<Integer> numbers = new ArrayList<>();

        int newFirstNumber = new Range(range).isInclusiveStart() ? firstNumber + 1 : firstNumber;
        int newSecondNumber = new Range(range).isInclusiveEnd() ? lastNumber + 1 : lastNumber;

        for (int x = newFirstNumber; x <= newSecondNumber; x++) {
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

    public String[] transformDigits(String range) {
        return range.split(",");
    }

    public boolean notContains(String otherRange) {

        int firstNumber = getFirstNumber(range);
        int secondNumber = getLastNumber(range);

        int otherRangeFirst = getFirstNumber(otherRange);
        int otherRangeSecond = getLastNumber(otherRange);

        List<Integer> rangeList = createListOfNumber(range, firstNumber, secondNumber);
        List<Integer> otherRangeList = createListOfNumber(otherRange, otherRangeFirst, otherRangeSecond);

        return checkNotContains(rangeList, otherRangeList);
    }

    public static void main(String[] args) {
        boolean newRange = new Range("[2,5)").notContains("[7,10)");
        System.out.println(newRange);
    }
}
