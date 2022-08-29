package virtual_pet;

public class Food
{
    // Fields
    private String name;
    private int hunger, thirst;

    // Getters
    public String getName() {return this.name;}
    public int getHunger() {return this.hunger;}
    public int getThirst() {return this.thirst;}

    // Constructor
    public Food(String name, int hunger, int thirst)
    {
        this.name = name; this.hunger = hunger; this.thirst = thirst;
    }
}