import java.util.ArrayList;

public class Tab {
    private int id_tab=0;//permet de sauvegarder un échantillon aléatoire
    private int nb_corde=1;
    private boolean melodie=true;//demande si c'est une mélodie
    private int nb_temps;//longueur de la tablature
    private double proba_silence=0.5;
    private boolean[] dejaUtilise=new boolean[1000];//stocke les emplacements déjà pris

    private String gamme="tout";
    private Corde[] tab_corde;

    public Tab(int nbCorde, int nb_temps)
    {
        this.nb_corde=nbCorde;
        this.nb_temps=nb_temps;
        System.out.println("il y a "+this.nb_corde+" cordes");
        System.out.println("il y a "+ nb_temps+" temps" );
        this.tab_corde=new Corde[this.nb_corde];
        for (int i = 0; i < nb_corde ; i++) {//initialise la tablature à vide
            tab_corde[i]=new Corde(i+1);
        }

        //initialisation de dejaUtilise
        for (int i=0; i<this.nb_temps;i++)
            this.dejaUtilise[i] = false;
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

/*
    public void random() {
        int j_temps;
        for (int i_corde = 0; i_corde<this.nb_corde ; i_corde++) //parcours les cordes
        {
            System.out.println(i_corde+"<"+this.nb_corde+"?");//debuggage

            j_temps=0;

            while (j_temps<nb_temps)
            {

                System.out.println("corde : "+i_corde+"temps : "+j_temps);//debug



                String string_aleatoire;

                int note_aleatoire;
                //insérer ID random
                double proba_silence_corde= getProba_silence_corde(i_corde);
                double tirage= (Math.random() * 100);



                if (tirage>proba_silence_corde|| this.dejaUtilise[j_temps])
                {
                    System.out.println(tirage + "<"+proba_silence_corde+
                            " \n ou le temps est déjà utilisé:"+this.dejaUtilise[j_temps]+
                            "donc ajout de silence");

                    string_aleatoire="";//pour faire du vide
                }
                else
                {

                    note_aleatoire=(case_min) + randomMinMax(case_min, case_max); //génère la note aléatoire entre les bornes

                    System.out.println(tirage + ">"+proba_silence_corde+" donc ajout de " + note_aleatoire );

                    string_aleatoire=""+note_aleatoire;// conversion du nb aleatoire en string
                    this.dejaUtilise[j_temps]=true;
                }
                this.tab_corde[i_corde].addNoteFin(string_aleatoire);
                j_temps++;


            }


        }
    }

 */

    private double getProba_silence_corde(int i_corde) {
        return (45*proba_silence*nb_corde)/(i_corde+1);
    }

    private static int randomMinMax(int case_min, int case_max) {
        return (int)(Math.random() * ((case_max - (case_min)) + 1));
    }

    public void randomGamme(int case_min, int case_max, int tonalite, int num_gamme)
    {
        for (int num_corde_actuelle = 0; num_corde_actuelle < nb_corde; num_corde_actuelle++)
        {
            double proba_silence_corde = getProba_silence_corde(num_corde_actuelle);

            Corde corde_actuelle = tab_corde[num_corde_actuelle];
            for (int temps_actuel = 0; temps_actuel < nb_temps; temps_actuel++)
            {
                if(proba_silence_corde>randomMinMax(0,100)||(dejaUtilise[temps_actuel] &&melodie))
                    corde_actuelle.addNoteFin("");
                else
                    {
                        int note_random = noteRandom(corde_actuelle, tonalite, case_min, case_max + 1, num_gamme);
                        corde_actuelle.addNoteFin(note_random);
                        dejaUtilise[temps_actuel]=true;
                    }
            }

        }
    }
    private int noteRandom(Corde corde, int tonalite, int case_min, int case_max, int num_gamme)
    {
        ArrayList<Integer> cases_gammes = Gammes.getGammeEnCase(corde.getNoteCorde(),tonalite,num_gamme,case_min, case_max);
        int numero_note_random = randomMinMax(0,cases_gammes.size()-1);
        return (int) cases_gammes.get(numero_note_random);
    }


    public void genererGamme(int case_min, int case_max, int tonalite, int num_gamme)
    {
        for (int num_corde = 1; num_corde < nb_corde; num_corde++) { //initialisation d'une corde
            Corde corde_actuelle=new Corde(num_corde);
            ArrayList<Integer> gamme = Gammes.getGammeEnCase(corde_actuelle.getNoteCorde(),tonalite,num_gamme,case_min,case_max);
            for (int note_actuelle = 0; note_actuelle < gamme.size(); note_actuelle++) {
                corde_actuelle.addNoteFin(gamme.get(note_actuelle));
            }
            tab_corde[num_corde]=corde_actuelle;


        }


    }

    public void setProba_silence(double proba_silence) {
        this.proba_silence = proba_silence;
    }

    public void setMelodie(boolean melodie) {
        this.melodie = melodie;
    }

    public String toString()
    {
        String str="";
        for (int i=0;i<this.nb_corde ;i++ )
        {
            str=str+tab_corde[i];
        }
        return str;
    }
}
