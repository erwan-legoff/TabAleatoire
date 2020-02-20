public class MainTab {
    public static void main(String[] args) {
        /*
        String tab_corde1[]={""+0,""+2,""+5,""+2};
        Corde corde1=new Corde(tab_corde1,1);

        System.out.println(corde1+" +1=");
        corde1.addNoteFin(""+1);
        System.out.println(corde1);

        tablature.random();
        System.out.println("tablature= "+tablature);

         */

        Tab tablature =new Tab(6,50);
        int case_min=0; int case_max=12; double proba_silence=0.5;
        tablature.setEstMelodie(true);
        tablature.setProba_silence(proba_silence);
        tablature.randomGamme(case_min,case_max, 8,1);
        System.out.println("min = "+case_min + " max = "+case_max+"\n"+ tablature);





    }


}
