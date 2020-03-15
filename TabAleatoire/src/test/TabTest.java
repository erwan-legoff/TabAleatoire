import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabTest {

    Tab tab = new Tab(6,50);

    @Test
    void getCase_max_egal_6() {
        assertEquals(tab.getCordeMax(),6);
    }

    @Test
    void getCase_min_egal_1() {
        assertEquals(tab.getCordeMin(),1);
    }

}