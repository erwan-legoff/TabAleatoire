import java.util.Random;

public  class  RandomR1 {
    //fonction random utilisée
    //permet de générer un identifiant
    //pour retrouver la même tablature random
    public static int randomReel(int min, int max) {
        return (int)(Math.random() * ((max - (min)) + 1));
    }
    //permet de retrouver la tablature en rentrant de nouveau l'ID_RAND
    public static int randomRepetable(int min, int max, Random randomizer)
    {
        return randomizer.nextInt(max+min+1)+min;
    }

}

