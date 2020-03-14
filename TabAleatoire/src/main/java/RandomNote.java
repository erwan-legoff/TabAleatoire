import java.util.ArrayList;
import java.util.Random;

public class RandomNote {
    private int tonalite;
    private int accordage;
    private int num_gamme;
    private int case_min;
    private int case_max;
    private int cordeMin;
    private int cordeMax;
    private int nbTemps;
    private int probaSilence;
    private boolean estMelodie;
    private Random randomizer;

    public RandomNote(int tonalite, int accordage, int num_gamme, int case_min, int case_max, int cordeMin, int cordeMax, int nbTemps, int probaSilence, boolean estMelodie, Random randomizer) {
        this.tonalite = tonalite;
        this.accordage = accordage;
        this.num_gamme = num_gamme;
        this.case_min = case_min;
        this.case_max = case_max;
        this.cordeMin = cordeMin;
        this.cordeMax = cordeMax;
        this.nbTemps = nbTemps;
        this.probaSilence = probaSilence;
        this.randomizer = randomizer;
    }
    public RandomNote(Tab tablature)
    {
        this(tablature.getTonalite(),tablature.getAccordage(),tablature.getNum_gamme(),tablature.getCase_min(),tablature.getCase_max(),tablature.getCordeMin(),tablature.getCordeMax(),tablature.getNb_temps(),tablature.getProbaSilence(),tablature.getEstMelodie(), tablature.getRandomizer());
    }

    //permet de générer une note aléatoire dans la gamme et corde choisie
    public int getNoteRandom(Corde corde)
    {
        //crée un tableau avec toutes les notes de la gammes dans l'intervalle choisi
        ArrayList<Integer> cases_gammes = Gamme.getGammeEnCase(corde.getNoteCorde(),tonalite,num_gamme,case_min, case_max+1);
        //sélectionne une case au hasard dans ce tableau
        //la note sera donc obligatoirement dans la gamme
        int numero_note_random = RandomR1.randomRepetable(0,cases_gammes.size()-1, randomizer);
        return cases_gammes.get(numero_note_random);

    }

    private boolean estSilencieux()
    {
        if(probaSilence>RandomR1.randomRepetable(0,100, randomizer))
            return true;
        else return false;

    }

    private ArrayList<ArrayList<String>> getListeNote()
    {


        ArrayList<ArrayList<String>> listeCorde = new ArrayList<>();


        remplirListeNoteRandom(listeCorde);


        return listeCorde;
    }

    private void remplirListeNoteRandom(ArrayList<ArrayList<String>> listeCorde) {
        for (int temps = 0; temps < nbTemps; temps++) {
            if(!estSilencieux()) {
                int numCorde = RandomR1.randomRepetable(cordeMin, cordeMax, randomizer);
                Corde corde = new Corde(numCorde, accordage);
                int note= getNoteRandom(corde);
                listeCorde.get(numCorde).add(""+note);
                remplirSilence(listeCorde, numCorde);
            }
        }
    }

    private void remplirSilence(ArrayList<ArrayList<String>> listeCorde, int numCorde) {
        for (int i = 0; i < cordeMax; i++) {
            if(numCorde!=i&&estMelodie)
            {
                listeCorde.get(i).add(" ");
            }

        }
    }
}
