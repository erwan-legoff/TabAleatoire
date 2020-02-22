public class NotesFlow {
    private int octaveMin = 0;
    private int octaveMax = 6;
    private Corde[] tab_corde;
    private Tab tablature;

    public NotesFlow(Tab tablature)
    {
        this.tablature=tablature;
        this.tab_corde=case_vers_note();
    }

    private Corde[] case_vers_note()
    {
        Corde[] tab_corde_conversion = tablature.getTab_corde();
        for (int num_corde_actuelle = 0; num_corde_actuelle <tab_corde_conversion.length ; num_corde_actuelle++) {
            Corde corde_actuelle = tab_corde_conversion[num_corde_actuelle];
            corde_actuelle.setFalseEstTablature();
            for (int temps = 0; temps < corde_actuelle.getTaille() ; temps++) {
                String case_actuelle = corde_actuelle.getNoteTab(temps);
                String note=Gammes.caseToNoteEng(corde_actuelle.getNoteCorde(),case_actuelle);
                corde_actuelle.setNoteTemps(temps,note);
            }
        }
        return tab_corde_conversion;
    }

    @Override
    public String toString() {
        String str ="";
        for (int num_corde_actuelle = 0; num_corde_actuelle < tab_corde.length; num_corde_actuelle++) {
            str=str+tab_corde[num_corde_actuelle];
        }
        return "NotesFlow converti à partir de la tablature |"+  tablature.getId_tab()+ "| :"+
                str;
    }
}
