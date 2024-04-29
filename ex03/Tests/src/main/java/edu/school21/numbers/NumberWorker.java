package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException();
        }
        boolean isNumPrime = true;
        if (number == 2) {
            return true;
        } else {
            for(int i = 2; i * i <= number; i++){
                if(number % i == 0) {
                    isNumPrime = false;
                    break;
                }
            }
        }
        return isNumPrime;
    }

    public int digitsSum(int number){
        number = Math.abs(number);
        int res = 0;
        while(number > 0){
            res = res + (number % 10);
            number = number / 10;
        }
        return res;
    }



    public class IllegalNumberException extends RuntimeException{
        public IllegalNumberException() {
            super("Error: number can't be less than 2");
        }
    }
}

