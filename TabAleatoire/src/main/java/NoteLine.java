public class NoteLine  {
    private int numLigne;
    private String[] noteTab;

    public NoteLine(int numLigne) {
        if(numLigne>0)
            this.numLigne = numLigne;
        else {
            System.out.println(" NumLigne :"+ numLigne + "<0");
        }
        noteTab = new String[100];
    }




}
