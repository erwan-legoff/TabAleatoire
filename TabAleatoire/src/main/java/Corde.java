public class Corde {
    private final String silence = "$";
    private boolean estTablature =true;
    private String tab_corde[] = new String[50];
    private int taille_corde =0;//ce sera la taille en nombre de temps de la génération de la corde
    private int num_corde; //1=petite corde de mi
    private int accordage=0;//demi ton par rapport au LA
                            //utile que dans des cas d'accordage spécifiques

    private static String[] nom_corde ={"","e","B","G","D","A","E"};
    private int[] note_corde ={accordage+4,accordage+11,accordage+7,accordage+2,accordage+9,accordage+4};

    public Corde(int num_corde)
    {
        if(num_corde>0 && num_corde<=6)
        {
            this.num_corde=num_corde;
        }
        else{System.out.println("Attention, numero de corde entre 1 et 6 obligatoire");}
    }
    public Corde(int num_corde, int accordage)
    {
        this(num_corde);

        this.accordage=accordage;
    }


    public Corde(Corde corde, int num_corde) // constructeur copiant une corde
    {
        this(num_corde, corde.getAccordage());//reprend la première méthode
        if (corde.taille_corde >= 0)
            System.arraycopy(corde.tab_corde, 0, this.tab_corde, 0, corde.taille_corde);

        this.taille_corde =corde.taille_corde;

    }

    public Corde(String[] tab_string,int num_corde) //permet de forcer un tableau de string
    {
        this(num_corde, 0);
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
        System.out.println("ajout de la note "+ note +" à "+nom_corde[num_corde]);
        this.taille_corde++;
    }
    public void setNoteTemps(int num_temps, String note)
    {
        tab_corde[num_temps]=note;
    }
    public void addNoteFin(int note)
    {
        addNoteFin(Integer.toString(note));
    } //permet d'évite de caster les in en String


    //Getters
    public int getNoteCorde()
    {
        return note_corde[num_corde-1];
    }//retourne la note de la corde
                                                                // utile pour la gamme
    public String getNoteTemps(int temps)
    {
        return this.tab_corde[temps];
    }
    public int getNoteTabInt(int temps)
    {
        if (getNoteTemps(temps).equals(silence))
        {
            return -1;
        }
        else
        {
            return Integer.parseInt(getNoteTemps(temps));
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
    public void setFalseEstTablature()
    {
        estTablature=false;
    }


    public String toString()//génère le visuel d'une corde
    {
        String str="\n";
        if (estTablature)
            str = str + nom_corde[this.num_corde]+"|"+"-";//affiche le nom de la corde
        else
            str = str + "||-";
        for(int i = 0; i< taille_corde; i++)
        {
            if(tab_corde[i]==silence)
            {
                str=str+"--";
            }
            else
            {
                str=str+this.tab_corde[i];
                if (!estTablature||estTablature&&Integer.parseInt(this.tab_corde[i])<10)
                {
                    str=str+"-";
                }
            }


            str=str+"-";
        }
        return str+"|";
    }

}
