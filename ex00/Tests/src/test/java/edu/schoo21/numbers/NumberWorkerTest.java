package edu.schoo21.numbers;


import edu.school21.numbers.NumberWorker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {
    NumberWorker numberWorker;

    @BeforeEach
    void beforeEach() {
        numberWorker = new NumberWorker();
    }

    @Tag("isPrimeForPrimes")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29})
    void isPrimeForPrimes(int number) {
        assertTrue(numberWorker.isPrime(number));
    }

    @Tag("isPrimeForNotPrimes")
    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25})
    void isPrimeForNotPrimes(int number) {
        assertFalse(numberWorker.isPrime(number));
    }

    @Tag("isPrimeForIncorrectNumbers")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -45, -56, -34234})
    void isPrimeForIncorrectNumbers(int number) {
        assertThrows(NumberWorker.IllegalNumberException.class , () -> numberWorker.isPrime(number));
    }

    @Tag("digitsSum")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void digitsSum(int number, int expected) {
        assertEquals(expected, numberWorker.digitsSum(number));
    }
}
