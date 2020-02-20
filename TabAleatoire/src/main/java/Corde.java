public class Corde {
    private String tab_corde[] = new String[1000];
    private int taille_corde =0;//ce sera la taille en nombre de temps de la génération de la partition
    private int num_corde; //1=petite corde de mi
    private int accordage=0;//demi ton par rapport au LA

    private static String nom_corde[]={"","e","B","G","D","A","E"};
    private int note_corde[]={accordage+4,accordage+11,accordage+7,accordage+2,accordage+9,accordage+4};

    public Corde(int num_corde, int accordage)
    {
        if(num_corde>0)
        {
            this.num_corde=num_corde;
        }
        else{System.out.println("Attention, numero de corde superieur a zero obligatoire");}
        this.accordage=accordage;
    }

    public Corde(Corde corde, int num_corde) // constructeur copiant une corde
    {
        this(num_corde, corde.getAccordage());//reprend la première méthode
        if (corde.taille_corde >= 0)
            System.arraycopy(corde.tab_corde, 0, this.tab_corde, 0, corde.taille_corde);

        this.taille_corde =corde.taille_corde;

    }

    public Corde(String[] tab_string,int num_corde, int accordage)
    {
        this(num_corde, accordage);
        int i =0;
        while(i<tab_string.length)
        {
            this.tab_corde[i]=tab_string[i];
            i++;
        }
        this.taille_corde =i;
    }
    //ajouts de notes
    public void addNoteFin(String note)
    {
        this.tab_corde[this.taille_corde]=note;
        this.taille_corde++;
    }
    public void addNoteFin(int note)
    {
        addNoteFin(Integer.toString(note));
    }

    //Getters
    public int getNoteCorde()
    {
        return note_corde[num_corde-1];
    }
    public int getNoteTab(int j_temps)
    {
        if (this.tab_corde[j_temps]=="")
        {
            return -1;
        }
        else
        {
            return Integer.parseInt(this.tab_corde[j_temps]);
        }
    }

    public int getAccordage() {
        return accordage;
    }

    public int getTaille()
    {
        return this.taille_corde;
    }
    public int getNum()
    {
        return this.num_corde;
    }

    public String toString()
    {
        String str="\n";

        str = str + nom_corde[this.num_corde]+"|"+"-";//affiche le nom de la corde
        for(int i = 0; i< taille_corde; i++)
        {
            if(tab_corde[i]=="")
            {
                str=str+"--";
            }
            else
            {
                str=str+this.tab_corde[i];
                if (Integer.parseInt(this.tab_corde[i])<10)
                {
                    str=str+"-";
                }
            }


            str=str+"-";
        }
        return str+"|";
    }
}
