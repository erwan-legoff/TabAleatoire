import java.util.ArrayList;

public class Gammes {


    static final String[] notes =                {"C",   "C#", "D", "D#",   "E",   "F",  "F#",  "G",  "G#", "A",   "A#", "B"};    //liste les notes

    private static final boolean[] chromatique = {true, true,  true, true,  true, true, true,  true, true,  true, true,  true};//on stocke les gammes
    private static final boolean[] naturelleM =  {true, false, true, false, true, true, false, true, false, true, false, true};
    private static final boolean[][] tabGammes={chromatique,naturelleM};//on regroupe les gammes dans un tableau
                                                                        //pour pouvoir les choisir


    static ArrayList<Integer> boolVersListeCase(int note_corde, int tonalite, boolean[] gamme, int case_min, int case_max) {//convertit un tableau
                                                                                            // de bool en cases
        ArrayList<Integer> gammeCase = new ArrayList<>(); //liste qu'on va retourner, convertie en numéro de case
        int cases = 0;

        while (cases-12+note_corde+tonalite<case_max)
        {
            if ((cases + note_corde+tonalite) >= 12+case_min)
            {
                if (gamme[cases % 12])//ajoute la note si elle est présente dans la gamme
                {

                    gammeCase.add((cases + note_corde+tonalite) % 12);
                }
            }
            cases++;
        }
        return gammeCase;
        }



    static ArrayList<Integer> getGammeEnCase(int note_corde, int tonalite, int type_gamme, int case_min, int case_max)
    {   //Permet de renvoyer une gamme choisie dans la tonalité choisie avec l'étendue choisie
        return boolVersListeCase(note_corde, tonalite, tabGammes[type_gamme], case_min, case_max);
    }




}