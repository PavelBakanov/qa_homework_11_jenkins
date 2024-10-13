package params.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemParamsTests {

    @Test
    @Tag("params")
    void systemParamsTest() {
        String browser = System.getProperty("browser");
    }
}
