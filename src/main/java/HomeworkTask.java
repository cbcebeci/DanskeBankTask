import java.util.*;

public class HomeworkTask {
    private static  List<String> numberList = new ArrayList<>();
    private static Integer currentOrderNumber;


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome. Please enter your single digit number between (-10,10) " +
                    "and then press <ENTER>. Press [D] when you are done!");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("d")) {
                if(checkIfTheListEmpty(numberList)){
                    System.out.println("Your list is empty. Please add some numbers.");
                }else
                    currentOrderNumber = 0;
                    checkIfTheListWinnable();
                break;
            }else if(checkIfTheInputIsSingleDigit(input)){
                System.out.println("You should enter single digit number");
            } else if (checkIfTheInputIsNumber(input)) {
                System.out.println("You should enter number.");
            } else if (checkIfTheInputIsInGivenGap(input)) {
                System.out.println("Number should be between (-10,10)");
            } else {
                numberList.add(input);
                System.out.println("Your number is added");
            }
        }
    }

    static boolean checkIfTheListEmpty(List arrList){
        return arrList.size() == 0;
    }

    static boolean checkIfTheInputIsSingleDigit(String input){
        if(input.length()>2){
            return true;
        }else return input.substring(1).matches("[^0-9]");

    }

    static boolean checkIfTheInputIsNumber(String input) {
        return input.matches("[^0-9]");
    }


    static boolean checkIfTheInputIsInGivenGap(String input) {
        int number = Integer.parseInt(input);

        return number < -9 || number > 9;

    }

    static void checkIfTheListWinnable (){
        if(checkIfTheFirstNumberIsZeroOrNegative(Integer.parseInt(numberList.get(0)))){
            System.out.println(numberList + " - Not Winnable!");

        }else if(checkIfNumberReachesEndOfTheList(Integer.parseInt(numberList.get(0)),currentOrderNumber)){
            System.out.println(numberList + " - Winnable!");

        }else if(checkOtherNumbers()){
            System.out.println(numberList + " - Winnable");

        }else{
            System.out.println(numberList +" - Not Winnable!");
        }

    }
    static boolean checkIfTheFirstNumberIsZeroOrNegative(Integer number){
        return number <= 0;
    }


    static boolean checkIfNumberReachesEndOfTheList(Integer number, Integer orderOfNumber){

        return number >= numberList.size() - (orderOfNumber + 1);

    }


    static boolean checkOtherNumbers (){

        List<Integer> possibleNumbers = new ArrayList<>();
        Map<Integer, Integer> numbersAndOrders = new HashMap<>();
        Integer maxValue;
        int orderOfNewNumber = currentOrderNumber + Integer.parseInt(numberList.get(currentOrderNumber));
        for (int i = (currentOrderNumber+1); i <= orderOfNewNumber; i++) {
            possibleNumbers.add(Integer.parseInt(numberList.get(i)));
            numbersAndOrders.put(Integer.parseInt(numberList.get(i)), i);

            maxValue = getTheMaxValue(possibleNumbers);
            if (checkIfNumberReachesEndOfTheList(maxValue, numbersAndOrders.get(maxValue))) {

                return true;
            } else {
                currentOrderNumber = numbersAndOrders.get(maxValue);
                orderOfNewNumber = currentOrderNumber + Integer.parseInt(numberList.get(currentOrderNumber));
            }
        }return false;

    }

    static int getTheMaxValue (List<Integer> arrList){

        int maxValue = arrList.get(0);
        for(int i=1;i < arrList.size(); i++){
            if(arrList.get(i) > maxValue){
                maxValue = arrList.get(i);
            }
        }
        return maxValue;
    }

}
