import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Test
    void whenNameIsNullThrowException(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, ()-> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", throwable.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings ={" ", "\t", "" })
    void whenNameNotCorrectThrowException(String name){
        Throwable throwable = assertThrows(IllegalArgumentException.class, ()-> new Horse(name, 1.0));
        assertEquals("Name cannot be blank.", throwable.getMessage());
    }

    @Test
    void whenSpeedIsNegativeNumberThrowException(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, ()-> new Horse("Name", -0.2));
        assertEquals("Speed cannot be negative.", throwable.getMessage());
    }

    @Test
    void whenDistanceIsNegativeNumberThrowException(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, ()-> new Horse("Name", 1.0, -0.1));
        assertEquals("Distance cannot be negative.", throwable.getMessage());
    }

    @Test
    void getNameReturnCorrectName(){
        Horse horse = new Horse("Pegasus", 2.9);
        assertEquals("Pegasus", horse.getName());
    }
    @Test
    void getSpeedReturnCorrectSpeed(){
        Horse horse = new Horse("Pegasus", 2.9);
        assertEquals(2.9, horse.getSpeed());
    }
    @Test
    void getDistanceReturnCorrectDistance() {
        Horse horse = new Horse("Pegasus", 2.9, 2.5);
        assertEquals(2.5, horse.getDistance());
    }
    @Test
    void getDistanceReturnNull() {
        Horse horse = new Horse("Pegasus", 2.9);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void methodMoveUseStaticMethodRandomWithArgs(){
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Pegasus", 2.9);
            horse.move();
            horseMockedStatic.verify(() ->Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.2, 0.8, 0.5})
    void theDistanceCalculatedByFormula(double distanceTest){
         try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
             horseMockedStatic.when(()->Horse.getRandomDouble(0.2, 0.9)).thenReturn(distanceTest);
             Horse horse = new Horse("Pegasus", 2.9);
             double distanceResult = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
             horse.move();
             assertEquals(distanceResult, horse.getDistance());
         }
    }
}
