public class NotesFlow {
    private int octaveMin = 0;
    private int octaveMax = 6;
    private Corde[] tab_corde;
    private Tab tablature;

    public NotesFlow(Tab tablature)
    {
        this.tablature=tablature;
        tab_corde = tablature.getTab_corde();
        conversionTableauCordeEnNotesEng(tab_corde);
    }

    private void conversionTableauCordeEnNotesEng(Corde[] tab_corde)
    {
        for (int i_corde = 0; i_corde <tab_corde.length ; i_corde++)
        {
            Corde corde = tab_corde[i_corde];
            corde.setFalseEstTablature();
            convesionCordeEnNotesEng(corde);
        }
    }



    private void convesionCordeEnNotesEng(Corde corde) {
        for (int temps = 0; temps < corde.getTaille() ; temps++)
        {
            String nCase = corde.getNoteTemps(temps);
            String note= Gamme.caseToNoteEng(corde.getNoteCorde(),nCase);
            corde.setNoteTemps(temps,note);
        }
    }

    @Override
    public String toString() {
        String str ="NotesFlow converti à partir de la tablature |"+  tablature.getId_tab()+ "| :";

        for (int c = 0; c < tab_corde.length; c++) {
            str=str+tab_corde[c];
        }
        return str;
    }
}
