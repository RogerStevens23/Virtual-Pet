package virtual_pet;

import static virtual_pet.VirtualPetApplication.getRandomNumber;

public class VirtualPet
{
    // Private Fields
    private final String name;
    private final Animal animalType;
    private boolean isDead = false;
    private int ticksSurvived = 0, health = 20;
    private int hunger, thirst, boredom, tiredness, sickness;

    // Getters
    public String getName() {return this.name;}
    public Animal getAnimalType() {return this.animalType;}
    public int getHunger() {return this.hunger;}
    public int getThirst() {return this.thirst;}
    public int getBoredom() {return this.boredom;}
    public int getTiredness() {return this.tiredness;}
    public int getHealth() {return this.health;}
    public int getSickness() {return this.sickness;}
    public int getTicksSurvived() {return this.ticksSurvived;}
    public boolean getIsDead(){return this.isDead;}
    public int rangeCheck(int attribute)
    {
        if(attribute >= 100)
            return 100;
        else if(attribute <= 0)
            return 0;
        else return attribute;
    }

    // Constructors
    public VirtualPet(String name, Animal animalType)
    {
        this.name = name;
        this.animalType = animalType;
        this.hunger = 0; this.thirst = 0; this.boredom = 0; this.tiredness = 0; this.sickness = 0;
    }
    public VirtualPet(String name, Animal animalType, int hunger, int thirst, int boredom, int tiredness, int sickness)
    {
        this.name = name;
        this.animalType = animalType;
        this.hunger = hunger; this.thirst = thirst;
        this.boredom = boredom; this.tiredness = tiredness; this.sickness = sickness;
    }

    // Action Methods
    public void water() {this.thirst = rangeCheck(thirst -= 25);}
    public void play(Toy toy) {this.boredom = rangeCheck(boredom -= toy.getBoredomCure());}
    public void vet() {this.sickness = 0;}
    public void sleep() {this.tiredness = 0;}
    public void feed(Food food)
    {
        this.hunger = rangeCheck(hunger -= food.getHunger());
        this.thirst = rangeCheck(thirst -= food.getThirst());
    }
    public void raiseAttributesBasedOnMultiplier(int randomMultiplier)
    {
        this.hunger = rangeCheck(hunger + (10 * randomMultiplier));
        this.thirst = rangeCheck(thirst + (10 * randomMultiplier));
        this.boredom = rangeCheck(boredom + (10 * randomMultiplier));
        this.tiredness = rangeCheck(tiredness + (10 * randomMultiplier));
        this.sickness = rangeCheck(sickness + (10 * randomMultiplier));
    }
    public void determineHealthStatus()
    {
        if (hunger == 100 || thirst == 100 || boredom == 100 || tiredness == 100 || sickness == 100)
            health--;
        if(health == 0)
            isDead = true;
    }
    public void tick()
    {
        raiseAttributesBasedOnMultiplier(getRandomNumber(3));
        determineHealthStatus();
        ticksSurvived++;
    }

    // String Methods
    @Override
    public String toString()
    {
        if(!isDead)
        {
            if(animalType == Animal.Cat)
                return "   ____         " + name + " the " + animalType + "         Hours Lived: " + ticksSurvived +
                        "\n  (.   \\        " + "Health: " + health +
                        "\n    \\  |  \n" +
                        "     \\ |___(\\--/)\n" +
                        "   __/    (  . . )\n" +
                        "  \"'._.    '-.O.'\n" +
                        "       '-.  \\ \"|\\\n" +
                        "          '.,,/'.,,";
            else if(animalType == Animal.Dog)
                return "   _          " + name + " the " + animalType + "         Hours Lived: " + ticksSurvived +
                        "\n  _V.-o       " + "Health: " + health +
                        "\n / |`-'\n" +
                        "(7_\\\\";
            else if (animalType == Animal.Bunny)
                return "  //       " + name + " the " + animalType + "         Hours Lived: " + ticksSurvived +
                        "\n ('>       " + "Health: " + health +
                        "\n /rr\n" +
                        "*\\))_";
            else if (animalType == Animal.Hamster)
                return "  q-p     " + name + " the " + animalType + "         Hours lived: " + ticksSurvived +
                        "\n /\\\"/\\    " + "Health: " + health +
                        "\n(`=*=') \n" +
                        " ^---^`-._";
            else
                return "";
        }
        else
            return "your " + animalType + " has died :(";
    }
    public String getStatusBar()
    {
        return "[Hunger: " + hunger + "][Thirst: " + thirst + "][Boredom: " + boredom
                + "][Tiredness: " + tiredness + "][Sickness: " + sickness + "]";
    }
}