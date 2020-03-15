import java.util.ArrayList;
import java.util.Random;

public class RandomCase {
    private int tonalite=0;
    private int accordage=0;
    private int num_gamme=0;
    private int case_min=0;
    private int case_max=12;
    private int cordeMin=1;
    private int cordeMax=6;
    private int nbTemps=50;
    private int probaSilence=50;
    private boolean estMelodie=true;
    private Random randomizer;

    public RandomCase(int tonalite, int accordage, int num_gamme, int case_min, int case_max, int cordeMin, int cordeMax, int nbTemps, int probaSilence, boolean estMelodie, Random randomizer) {
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
    public RandomCase(Tab tablature)
    {
        this(tablature.getTonalite(),tablature.getAccordage(),tablature.getNum_gamme(),tablature.getCase_min(),tablature.getCase_max(),tablature.getCordeMin(),tablature.getCordeMax(),tablature.getNb_temps(),tablature.getProbaSilence(),tablature.getEstMelodie(), tablature.getRandomizer());
    }

    public RandomCase(Random randomizer) {
        this.randomizer=randomizer;
    }

    //permet de générer une note aléatoire dans la gamme et corde choisie
    public int getCaseRandom(Corde corde)
    {
        //crée un tableau avec toutes les notes de la gammes dans l'intervalle choisi
        ArrayList<Integer> cases_gammes = Gamme.getGammeEnCase(corde.getNoteCorde(),tonalite,num_gamme,case_min, case_max+1);
        //System.out.println("tableau case = "+cases_gammes);
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

    public ArrayList<ArrayList<String>> getListeCases()
    {


        ArrayList<ArrayList<String>> listeCorde = new ArrayList<>();


        remplirListeCaseRandom(listeCorde);


        return listeCorde;
    }

    private void remplirListeCaseRandom(ArrayList<ArrayList<String>> listeCorde) {
        initialisationListeListe(listeCorde);
        for (int temps = 0; temps < nbTemps; temps++) {
            System.out.println("temps="+temps);
            if(!estSilencieux()) {
                int numCorde = getNumCordeAleatoire();
                System.out.println("numCorde="+numCorde);
                Corde corde = new Corde(numCorde, accordage);
                int note= getCaseRandom(corde);

                listeCorde.get(numCorde-1).add(""+note);
                remplirSilence(listeCorde, numCorde);
            }
        }
    }

    private void initialisationListeListe(ArrayList<ArrayList<String>> listeCorde) {
        for (int corde = 0; corde < cordeMax-cordeMin; corde++) {
            ArrayList<String> listeNoteCorde = new ArrayList<>();
            listeCorde.add(listeNoteCorde);
        }
    }

    private int getNumCordeAleatoire() {
        return RandomR1.randomRepetable(cordeMin, cordeMax, randomizer);
    }

    public void remplirSilence(ArrayList<ArrayList<String>> listeCorde, int numCorde) {
        for (int i = 0; i < cordeMax; i++) {
            System.out.println("cordeMax="+cordeMax);
            System.out.println("i="+i+" corde="+numCorde+" tailleListe="+listeCorde.size());
            if(numCorde!=i&&estMelodie)
            {
                listeCorde.get(i).add(" ");
            }

        }
    }
}
