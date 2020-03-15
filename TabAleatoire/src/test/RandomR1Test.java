import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RandomR1Test {
    @Test
    public void test_randomRepetable_entre_bornes() {
        Random randomizer = new Random();
        randomizer.setSeed(10);
        boolean test = true;
        for (int i = 0; i < 100000; i++) {

            int random = RandomR1.randomRepetable(0, 4, randomizer);
            System.out.println(random);
            if (random < 0 || random > 4)
                test = false;

        }
        assertTrue(test);
    }

        @Test
        public void test_randomRepetable_est_inclusif()
        {
            Random randomizer = new Random();
            randomizer.setSeed(10);
            boolean test=false;
            for (int i = 0; i <100 ; i++) {

                int random = RandomR1.randomRepetable(0,4,randomizer);
                System.out.println(random);
                if(random==4)
                    test=true;
            }
            assertTrue(test);
    }

}