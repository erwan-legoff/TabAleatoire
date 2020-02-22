import java.util.ArrayList;
import java.util.Arrays;

public class MainTab {
    public static void main(String[] args) {
        //ajouts dans le futur proche : ajout d'ID permettant de retrouver la tablature anciennement générée
        //ajouts futur plus lointain: ajout d'un mode d'écoute des tablatures
        //exemple d'utilisation du programme

        /*Tab tabCopie =new Tab(6,20); // choisir un nombre de cordes entre 1 et 6
        int case_min=0; int case_max=12; int proba_silence=50;
        // case_min et case_max permettent de définir l'écart acceptée dans la tablature
        // plus la proba_silence est élevée (entre 0 et 1) plus la tablature est "aérée"
        tabCopie.setEstMelodie(true);//définit si c'est une mélodie ou non
                                    // si la génération est une mélodie, les notes ne se chevaucheront jamais
        tabCopie.setProba_silence(proba_silence);

        //tablature.generateurRandomCustom(case_min,case_max, 8,1); //génère une tablature selon les paramètres définis
        /*
        la tonalité = 8 permet de dire que la gamme est en SOL# (ou G#  en anglais )
        {"C",   "C#", "D", "D#",   "E",   "F",  "F#",  "G",  "G#", "A",   "A#", "B"}
        le numéro de gamme permet de choisir la gamme dans le tableau de gammes de la classe Gammes
        Cette gamme en l'occurence est gammeNaturelleMajeure, "DO RE MI FA SOL LA SI"
        il n'y a que celle là et la gamme chromatique (0) qui est composée d'absolument toutes les notes
        /*


        Tab tablatureOrigine = new Tab(3,50);
        tablatureOrigine.generateurRandomCustom(0,6,0,1);
        //System.out.println("origine:" + tablatureOrigine);
        String id_tab_origine=tablatureOrigine.getId_tab();
        //tabCopie.tabFromID_tab(id_tab_origine);
       // System.out.println("copie:"+ tabCopie);

         */


        Tab gamme = new Tab(3,50);
        gamme.setMinMax(5,10);
        gamme.setEstMelodie(true);
        gamme.setNum_gamme(1);
        gamme.newRandom();
        System.out.println(gamme);
        NotesFlow gammeConvertie = new NotesFlow(gamme);
        System.out.println(gammeConvertie);

        String[] tableau_tab_id={"1-615-3-50-50-5-10-0-0-1"};


        //tabCopie.tabFromID_tab("1-65-6-20-50-0-12-8-0");

        //System.out.println("min = "+case_min + " max = "+case_max+"\n"+ tabCopie);//affichage de la tablature


        /*
        DESCRIPTION RESULTAT
        chaque paire de "-" désigne un temps (pour l'instant, c'est simplifié)
        ainsi une note est notée par un chiffre, qui correspond à une case sur une corde.
        un 0 sur la corde de LA fait un LA
        un 2 sur une corde de mi fait un FA# (MI,FA,FA#)
        car le MI n'a pas de #
        la suite 3--7 veut dire que l'on doit d'abord joué 3 puis 7
        un chevauchement de plusieurs notes sur des cordes différentes veut dire qu'il faut les jouer en même temps
        La tablature suivante:
               B ------11
               G ---10---
               D 10------
        Veut dire qu'il faut d'abord joué la 10eme case sur la corde D puis sur la corde G puis la 11 sur B

        Exemple de tablature attendue en mode mélodie:
e|-------------------------------------------------------------| //petite corde de mi
B|----------------------7--------11----7--9--------11-0--------|
G|-8--------3--7--------------10-------------------------------|
D|-------7--------0--10----10-------10-------10----------------|
A|----10----------------------------------------------------9--| //corde de LA
E|-------------------------------------------------------------| // grosse corde de MI

         */






    }


}
