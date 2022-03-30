import java.util.ArrayList;
import java.util.List;

public class RangeV2 {

    private String range;

    public RangeV2(String range) {
        this.range = range;
    }

    public boolean checkNotContains(List<Integer> range, List<Integer> otherRange){
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

    public String[] transformDigit(String range){
        return range.split(",");
    }

    public boolean isInclusiveStart(String range){
        return range.startsWith("(");
    }

    public boolean isInclusiveEnd(String range){
        return range.endsWith(")");
    }

    public boolean notContains(String otherRange){
        int firstNumber = getFirstNumber(range);
        int lastNumber = getLastNumber(range);

        int firstOtherNumber = getFirstNumber(otherRange);
        int lastOtherNumber = getLastNumber(otherRange);

        List<Integer> firstRange = createListNumber(range, firstNumber, lastNumber);
        List<Integer> otherRangeList = createListNumber(otherRange,firstOtherNumber,lastOtherNumber );

        return checkNotContains(firstRange, otherRangeList);
    }

    public static void main(String[] args) {
        boolean range = new RangeV2("[3,6)").notContains("[6,10)");
        System.out.println("No contiene: " + range);
    }
}
