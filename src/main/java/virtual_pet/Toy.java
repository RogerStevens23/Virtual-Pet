package virtual_pet;

public class Toy
{
    // Fields
    private String name;
    private int boredomCure;

    // Getters
    public String getName() {return this.name;}
    public int getBoredomCure() {return this.boredomCure;}

    // Constructor
    public Toy(String name, int boredomCure)
    {
        this.name = name;
        this.boredomCure = boredomCure;
    }
}
