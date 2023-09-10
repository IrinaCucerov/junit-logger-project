import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    void whenConstructorArgIsNullThrowException() {
        Throwable exc = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exc.getMessage());
    }

    @Test
    void whenConstructorArgIsEmptyThrowException() {
        Throwable exc = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exc.getMessage());
    }

    @Test
    void getHorsesReturnCorrectList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("horseName", i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void methodMoveForEachHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerReturnHorseWithMaxDistance() {
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("Name1", 2.2, 5.8);
        Horse horse2 = new Horse("Name2", 2.0, 4.9);
        Horse horse3 = new Horse("Name3", 2.7, 6.1);
        Horse horse4 = new Horse("Name4", 2.4, 4.5);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));

        hippodrome.getWinner();

        assertSame(horse3, hippodrome.getWinner());
    }
}


