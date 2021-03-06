import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoteRandomCaseTest {
    Random randomizer = new Random();
    NoteRandomCase noteRandomCase = new NoteRandomCase(randomizer);
    Corde corde = new Corde(1);
    @BeforeEach
    void initialisateur()
    {

    }
    @Test
    void test_getCaseRandom_entre_les_bornes() {
        boolean test=true;
        int caseRandom= noteRandomCase.getCaseRandom(corde);
        for (int i = 0; i <1000 ; i++) {
            System.out.println(caseRandom);
            if (caseRandom<0||caseRandom>12)
            {

                test = false;
            }
        }
        assertTrue(test);
    }
    @Test
    void test_getCaseRandom_inclusif_inferieur() {
        boolean test=false;

        for (int i = 0; i <1000 ; i++) {
            int caseRandom= noteRandomCase.getCaseRandom(corde);
            //System.out.println(caseRandom);
            if (caseRandom==0)
            {

                test = true;
            }
        }
        assertTrue(test);
    }
    @Test
    void test_getCaseRandom_inclusif_superieur() {
        boolean test=false;

        for (int i = 0; i <1000 ; i++) {
            int caseRandom= noteRandomCase.getCaseRandom(corde);
            System.out.println(caseRandom);
            if (caseRandom==12)
            {

                test = true;
            }
        }
        assertTrue(test);
    }
    @Test
    void test_frequence() {
        int nbTest = 1000000;
        boolean test=false;
        int[] somme=new int[13];
        float[] frequence=new float[13];
        for (int i = 0; i <nbTest ; i++) {
            int caseRandom= noteRandomCase.getCaseRandom(corde);

            somme[caseRandom]++;
        }
        for (int i = 0; i <13 ; i++) {
            frequence[i]=100*(((float)somme[i])/nbTest);

        }
        System.out.println("frequence="+Arrays.toString(frequence));
        assertEquals(frequence[1],100*(1/13.0),0.004);
    }

    @Test
    public void test_remplir_vide()
    {
        ArrayList<ArrayList<String>> liste_vide = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            liste_vide.add(new ArrayList<>());
        }

        noteRandomCase.remplirSilence(liste_vide,corde.getNum());
        System.out.println(liste_vide);
    }
}