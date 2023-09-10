import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MainTest {
    @Disabled
    @Test
    @Timeout(value = 22)
    void timeOfMethodMainNotMore22Sek() throws Exception {
        String[] args = {};
       Main.main(args);
    }
}
