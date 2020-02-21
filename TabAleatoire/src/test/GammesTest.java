import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GammesTest {
    @BeforeEach
    public void initialisateur()
    {

    }

    @Test
    public void test_gamme_chromatique_DO_sur_corde_DO()
    {
        boolean egal=true;

       ArrayList<Integer> listeNotesGammes = Gammes.getGammeEnCase(0,0,0,0,12);
       int[] tableauNotesVerif={0,1,2,3,4,5,6,7,8,9,10,11};
        for (int i = 0; i <12 ; i++) {
            if (tableauNotesVerif[i]!=listeNotesGammes.get(i))
                egal=false;

        }
        assertTrue(egal);
    }
    @Test
    public void test_gamme_chromatique_DO_sur_corde_mi()
    {
        boolean egal=true;

        ArrayList<Integer> listeNotesGammes = Gammes.getGammeEnCase(4,0,0,0,12);
        int[] tableauNotesVerif={0,1,2,3,4,5,6,7,8,9,10,11};
        for (int i = 0; i <12 ; i++) {
            if (tableauNotesVerif[i]!=listeNotesGammes.get(i))
                egal=false;

        }
        assertTrue(egal);
    }

    @Test
    public void test_gamme_chromatique_MI_sur_corde_mi()
    {
        boolean egal=true;

        ArrayList<Integer> listeNotesGammes = Gammes.getGammeEnCase(4,4,0,0,12);
        int[] tableauNotesVerif={0,1,2,3,4,5,6,7,8,9,10,11};
        for (int i = 0; i <12 ; i++) {
            if (tableauNotesVerif[i]!=listeNotesGammes.get(i))
                egal=false;

        }
        assertTrue(egal);
    }

    @Test
    public void test_gamme_naturelle_do_sur_corde_do()
    {
        boolean egal=true;

        ArrayList<Integer> listeNotesGammes = Gammes.getGammeEnCase(0,0,1,0,12);
        //boolean[] naturelleM =  {true, false, true, false, true, true, false, true, false, true, false, true};
        int[] tableauNotesVerif={0,            2,          4,    5,            7,           9,            11};
        for (int i = 0; i <7 ; i++) {
            if (tableauNotesVerif[i]!=listeNotesGammes.get(i))
                egal=false;
        }

        assertTrue(egal);
    }

    @Test
    public void test_gamme_naturelle_do_sur_corde_mi()
    {
        boolean egal=true;

        ArrayList<Integer> listeNotesGammes = Gammes.getGammeEnCase(4,0,1,0,12);
        //boolean[] naturelleM =  {true, false, true, false, true, true, false, true, false, true, false, true};
        int[] tableauNotesVerif={0,1,3,5,7,8,10};
        for (int i = 0; i <7 ; i++) {
            if (tableauNotesVerif[i]!=listeNotesGammes.get(i))
                egal=false;
        }

        assertTrue(egal);
    }

}