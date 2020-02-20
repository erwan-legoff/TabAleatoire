public class MainTab {
    public static void main(String[] args) {
        //ajouts dans le futur proche : ajout d'ID permettant de retrouver la tablature anciennement générée
        //ajouts futur plus lointain: ajout d'un mode d'écoute des tablatures
        //exemple d'utilisation du programme

        Tab tablature =new Tab(6,50); // choisir un nombre de cordes entre 1 et 6
        int case_min=0; int case_max=12; double proba_silence=0.5;
        // case_min et case_max permettent de définir l'écart acceptée dans la tablature
        // plus la proba_silence est élevée (entre 0 et 1) plus la tablature est "aérée"
        tablature.setEstMelodie(true);//définit si c'est une mélodie ou non
                                    // si la génération est une mélodie, les notes ne se chevaucheront jamais
        tablature.setProba_silence(proba_silence);

        tablature.randomGamme(case_min,case_max, 8,1); //génère une tablature selon les paramètres définis
        /*
        la tonalité = 8 permet de dire que la gamme est en SOL# (ou G#  en anglais )
        {"C",   "C#", "D", "D#",   "E",   "F",  "F#",  "G",  "G#", "A",   "A#", "B"}
        le numéro de gamme permet de choisir la gamme dans le tableau de gammes de la classe Gammes
        Cette gamme en l'occurence est gammeNaturelleMajeure, "DO RE MI FA SOL LA SI"
        il n'y a que celle là et la gamme chromatique (0) qui est composée d'absolument toutes les notes
        */

        System.out.println("min = "+case_min + " max = "+case_max+"\n"+ tablature);//affichage de la tablature





    }


}
