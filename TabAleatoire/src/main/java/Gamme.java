import java.util.ArrayList;

public class Gamme {
    //Classe qui permet de faire respecter la gamme
    // la note de la corde permet de décaler la gamme pour que les cases correspondent avec les notes
        /*
        EXEMPLE :
        pour la corde LA (A), la note SI (B) est à la case 2 (LA, LA#, <SI>)
        une case = un demi ton = la différence entre LA et LA#
        donc la note SI est numérotée 2 pour la corde LA
        par contre, pour la corde SI, elle est notée 0 etc
        On se sert de la note de la corde pour pouvoir décaler les cases
        ATTENTION : il n'y a pas de MI# et de SI#
        donc entre MI et FA il y a un demi ton ou une case
        pareil entre SI et DO
         */

    static final String[] notesFr =                 {"DO", "DO#","RE", "RE#",  "MI",  "FA","FA#", "SOL","SOL#", "LA", "LA#","SI"};
    static final String[] notesEng =                {"C",   "C#", "D", "D#",   "E",   "F",  "F#",  "G",  "G#", "A",   "A#", "B"};    //liste les notes

    static final String silence = "$";


    private static final boolean[] chromatique =    {true, true,  true, true,  true, true, true,  true, true,  true, true,  true};//on stocke les gammes
    private static final boolean[] naturelleMaj =   {true, false, true, false, true, true, false, true, false, true, false, true};
    private static final boolean[] naturelle_m =    {true, false, true, true, false, true, false, true, true, false, true, false};
    private static final boolean[] harmonic_m =    {true, false, true, true, false, true, false, true, true, false, false, true};
    private static final boolean[] melodic_m =     {true, false, true, true, false, true, false, true, false, true, false, true};
    private static final boolean[] mixolydian =     {true, false, true, false, true, true, false, true, false, true, true, false};
    private static final boolean[] phrygian =      {true, true, false, false, true, true, false, true, false, true, true, false};
    private static final boolean[] pentatonicMaj = {true, false, true, false, true, false, false, true, false, true, false, false};
    private static final boolean[] pentatonic_m = {true, false, false, true, false, true, false, true, false, false, true, false};
    private static final boolean[] bluesMaj =     {true, false, true, true, false, false, false, true, false, true, false, false};
    private static final boolean[] blues_m =      {true, false, false, true, false, true, false, true, false, false, true, false};

    private static final boolean[][] tabGammes={chromatique, naturelleMaj, naturelle_m, harmonic_m,
                                                  melodic_m, mixolydian, phrygian, pentatonicMaj, pentatonic_m, bluesMaj, blues_m};
    //on regroupe les gammes dans un tableau
    //pour pouvoir les choisir


    static ArrayList<Integer> boolVersListeCase(int note_corde, int tonalite, boolean[] gamme, int case_min, int case_max) {
        //convertit un tableau de bool en cases
        //permet de ne regrouper que les notes qui appartiennent à la gamme
        //cela facilite la génération aléatoire
        //comparée à l'utilisation d'un tableau booléen
        //mais le tableau booléen est plus facile à utiliser pour rentrer une gamme
        //cela permet donc de faciliter l'écriture des gammes
        //tout en facilitant le code
        if (case_max<case_min)
        {
            System.out.println("WARNING boolVersListeCase a reçu une case_max>case_min");
            if (case_min>12) {

                case_min = case_min - 12;
                System.out.println("case_min vaut maintenant" + case_min);
            }
            else { case_max=case_max+12;
            System.out.println("case_max vaut maintenant"+case_max);}
        }

        ArrayList<Integer> gammeCase = new ArrayList<>(); //liste qu'on va retourner, convertie en numéro de case
        int cases = 0;

        while (cases-12-note_corde+tonalite<case_max) //dépasse la case 12 pour commencer par la première note
                                                    //de la corde qui correspond à la gamme
                                                        // permet d'avoir toutes les notes du tableau bien répartie
        {
            if ((cases - note_corde+tonalite) >= 12+case_min)
            {
                if (gamme[cases % 12])//ajoute la note si elle est présente dans la gamme
                    //on prend modulo 12 car la note est la même à l'octave au-dessus, juste plus aigue
                    //un octave=12 demi tons = 12 cases
                {
                    gammeCase.add((cases - note_corde+tonalite-12) % 24); //ajoute la case jusqu'à 11
                                                                        // plus tard 23, plus proche du nombre total
                                                                        //de case d'une guitare
                }
            }
            cases++;
        }
        return gammeCase;
        }


    public static String[] getNotesFr() {
        return notesFr;
    }

    static ArrayList<Integer> getGammeEnCase(int note_corde, int tonalite, int type_gamme, int case_min, int case_max)
    {   //Permet de renvoyer une gamme choisie dans la tonalité choisie avec l'étendue choisie

        return boolVersListeCase(note_corde, tonalite, tabGammes[type_gamme], case_min, case_max);
    }
    static String caseToNoteEng(int note_corde, String note_case)
    {

        if(note_case.equals("$"))
            return"";
        else {int num_case = Integer.parseInt(note_case);
               int num_note = num_case + note_corde;
                return notesEng[num_note%12]; }
    }

    public static String getNotesFr(int num_note) {
        return notesFr[num_note];
    }
    public static String getNotesEng(int num_note) {
        return notesEng[num_note];
    }
    public static String getSilence() {
        return silence;
    }

}