import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tab {
    private int tab_parametre;
    private int accordage = 0;
    private int case_min;
    private int case_max;
    private int tonalite;
    private int num_gamme;
    private String id_tab;//permet de sauvegarder un échantillon aléatoire
    private int idRandom;//permet de répéter la série aléatoire, mais doit être complété par les autres paramètres
                        // pour être utile, et donc devenir l'id_tab
    private int nb_corde=1;
    private boolean estMelodie =true;//demande si c'est une mélodie
    private int nb_temps;//longueur de la tablature
    private int repartition =50;
    private boolean[] dejaUtilise=new boolean[10000];//stocke les emplacements déjà pris
    private Random randomizer = new Random();


    private Corde[] tab_corde;

    public Tab(int nbCorde, int nb_temps) //création d'une tablature à remplir, en fonction de la taille demandée
    {
        this.nb_corde=nbCorde;
        this.nb_temps=nb_temps;
        case_min=0;
        case_max=12;
        System.out.println("il y a "+this.nb_corde+" cordes");
        System.out.println("il y a "+ nb_temps+" temps" );
        this.tab_corde=new Corde[this.nb_corde];
        for (int i = 0; i < nb_corde ; i++) {//initialise la tablature à vide
            tab_corde[i]=new Corde(i+1,accordage);//création de corde vide
        }

        //initialisation de dejaUtilise
        initialiserDejaUtilise();

    }
    public  Tab(String[][] tableauTablature)
    {
        this(tableauTablature.length,tableauTablature[0].length);
        for (int num_corde_actuelle = 0; num_corde_actuelle < tableauTablature.length ; num_corde_actuelle++) {
            for (int temps_actuel = 0; temps_actuel < tableauTablature[0].length; temps_actuel++) {
                tab_corde[num_corde_actuelle].addNoteFin(tableauTablature[num_corde_actuelle][temps_actuel]);
            }
        }
    }




    public void tabFromID_tab(String id_tab) //problème : les notes sont à un endroit identique sur les cordes, mais le numéro des cases changent...
    {   //
        String[] id_tab_decode=extracteurDeParametres(id_tab);
        assignerParametreFromID(id_tab_decode);
        generateurRandomAvecID(idRandom);
    }

    private void assignerParametreFromID(String[] id_tab_decode) {
        if(id_tab_decode[0].equals("1"))
            this.estMelodie = true;
        else
        {estMelodie=false;}
        idRandom= Integer.parseInt(id_tab_decode[1]);
        nb_corde= Integer.parseInt(id_tab_decode[2]);
        nb_temps= Integer.parseInt(id_tab_decode[3]);
        repartition =Integer.parseInt(id_tab_decode[4]);
        case_min     =Integer.parseInt(id_tab_decode[5]);
        case_max     =Integer.parseInt(id_tab_decode[6]);
        tonalite     =Integer.parseInt(id_tab_decode[7]);
        accordage    =Integer.parseInt(id_tab_decode[8]);
        num_gamme    =Integer.parseInt(id_tab_decode[9]);
    }

    public void newRandom()
    {
        //devra effacer la tab pour en refaire une nouvelle
        generateurRandomCustom(case_min,case_max,tonalite,num_gamme);
    }

    public void generateurRandomCustom(int caseMin, int caseMax, int ton, int numGamme)
    {
        int idRand=randomReelMinMax(0,1000); //génération d'un IDrand Aléatoire
        this.case_min=caseMin; this.case_max=caseMax; this.tonalite=ton; this.num_gamme=numGamme;
        generateurRandomAvecID(idRand);
    }


    private void generateurRandomAvecID(int idRand) { //Fonction centrale : permet de générer une tabature aléatoire
        // à partir d'une gamme et d'un idRand donnés
        this.idRandom=idRand;
        generateurID();
        this.randomizer.setSeed(idRandom);
        for (int num_corde_actuelle = 0; num_corde_actuelle < nb_corde; num_corde_actuelle++)//parcours les cordes
        {
            double proba_silence_corde = getProba_silence_corde(num_corde_actuelle);//détermine la proba de silence
                                                                                    // pour cette corde
            Corde corde_actuelle = tab_corde[num_corde_actuelle]; //on définit la corde sur laquelle on travaille
            for (int temps_actuel = 0; temps_actuel < nb_temps; temps_actuel++) //parcourt tous les temps de cette corde
            {
                if(proba_silence_corde> randomRepetableMinMax(0,100)||(dejaUtilise[temps_actuel] && estMelodie))
                    corde_actuelle.addNoteFin(""); //ajoute un silence aléatoirement ou si déjà utilisée
                                                    // et qu'on veut bien une mélodie
                else
                    {   //sinon on tire au sort une note à ajouter
                        //appel de la fonction noteRandom
                        int note_random = noteRandom(corde_actuelle);
                        //Ajout de la note à la fin de la corde
                        corde_actuelle.addNoteFin(note_random);
                        //En cas de mélodie, on ne pourra plus utiliser ce temps sur aucune des cordes pour mettre une note
                        dejaUtilise[temps_actuel]=true;
                    }
            }
        }
    }


    //permet de changer la probabilité d'avoir un silence en fonction de la corde
    //n'est pas parfait, mais permet de mieux répartir les notes sur toutes les cordes
    private double getProba_silence_corde(int i_corde) {
        return (0.45* repartition *nb_corde)/(i_corde+1);
    }


    //permet de générer une note aléatoire dans la gamme et corde choisie
    private int noteRandom(Corde corde)
    {
        //crée un tableau avec toutes les notes de la gammes dans l'intervalle choisi
        ArrayList<Integer> cases_gammes = Gamme.getGammeEnCase(corde.getNoteCorde(),tonalite,num_gamme,case_min, case_max+1);
        //sélectionne une case au hasard dans ce tableau
        //la note sera donc obligatoirement dans la gamme
        int numero_note_random = randomRepetableMinMax(0,cases_gammes.size()-1);
        return cases_gammes.get(numero_note_random);
    }

    //fonction random utilisée
    //permet de générer un identifiant
    //pour retrouver la même tablature random
    private static int randomReelMinMax(int min, int max) {
        return (int)(Math.random() * ((max - (min)) + 1));
    }
    //permet de retrouver la tablature en rentrant de nouveau l'ID_RAND
    private int randomRepetableMinMax(int min, int max)
    {
        return randomizer.nextInt(max+min)+min;
    }
    private void generateurID()
    {
        String melody;
        if (estMelodie)
        {
            melody="1";
        }
        else {melody="0";}

        id_tab=melody+"-"+idRandom+"-"+nb_corde+"-"+nb_temps+"-"+ repartition +"-"
                +case_min+"-"+case_max+"-"+tonalite+"-"+accordage+"-"+num_gamme;

    }
    private String[] extracteurDeParametres(String id_tab) //extrait les paramètres à partir de l'ID
    {
        int compteur=0;
        String[] id_tab_decode = new String[10];
        for (int i = 0; i < 10; i++) {
            id_tab_decode[i]="";
        }

        for (int i = 0; i < id_tab.length() ; i++) {

            if('-' != id_tab.charAt(i))
            {
                id_tab_decode[compteur]=id_tab_decode[compteur]+id_tab.charAt(i);
            }
            else
            { compteur++; }
        }
        System.out.println(Arrays.toString(id_tab_decode));
        return id_tab_decode;

    }

    public void setMinMax(int case_min, int case_max)
    {
        this.case_min=case_min;
        this.case_max=case_max;
    }

    public void setTonalite(int tonalite) {
        this.tonalite = tonalite;
    }

    public void setAccordage(int accordage)
    {
        this.accordage=accordage;
    }


    public void genererGamme(int num_gamme)//pas vraiment utile en l'état,
    // génère une tablature avec toutes les notes d'une gammes
    {
        initialiserDejaUtilise();
        for (int num_corde = nb_corde-1; num_corde >=0; num_corde--)
        {
            Corde corde_actuelle=tab_corde[num_corde];

            ArrayList<Integer> listeNoteCordeActuelle = Gamme.getGammeEnCase(corde_actuelle.getNoteCorde(),tonalite,num_gamme,(tonalite+7)%24,(tonalite+12)%24);
            System.out.println("note de la corde "+corde_actuelle.getNoteCorde()+" : "+listeNoteCordeActuelle);
            int temps=0;
            while (dejaUtilise[temps])
            {corde_actuelle.addNoteFin("");
            temps++;}

            for (Integer integer : listeNoteCordeActuelle) {
                corde_actuelle.addNoteFin(integer);

                dejaUtilise[temps]=true;
                temps++;
            }
        }
    }
    private void initialiserDejaUtilise()
    {
        Arrays.fill(dejaUtilise, false);

    }

    public void setRepartition(int repartition) {
        this.repartition = repartition;
    }

    public void setEstMelodie(boolean estMelodie) {
        this.estMelodie = estMelodie;
    }

    public void setNum_gamme(int num_gamme) {
        this.num_gamme = num_gamme;
    }

    public String getId_tab() {
        return id_tab;
    }

    public Corde[] getTab_corde() {
        return tab_corde;
    }

    public String toString()
    {
        String str="";
        str="Tonalité : "+ Gamme.getNotesFr(tonalite);
        for (int i=0;i<this.nb_corde ;i++ )
        {
            str=str+tab_corde[i];
        }
        System.out.println("id_tab : " + id_tab);
        System.out.println("id_random : " + idRandom);
        return str;
    }


}
