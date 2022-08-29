package virtual_pet;
import java.util.Random;
import java.util.Scanner;

public class VirtualPetApplication
{
    // Main
    public static void main(String[] args)
    {
        // Game Variables
        boolean exit = false, tick = false;
        Animal animalType;
        VirtualPet pet;
        String name;
        Food[] petsFood;
        Toy[] petsToys;

        // Start
        System.out.println(stringWrapper("Welcome To Virtual Pet!", 1));
        animalType = selectAnimalType();
        name = nameAnimal();
        pet = new VirtualPet(name, animalType);
        petsFood = animalFoodList(animalType);
        petsToys = animalToyList(animalType);

        // Game Loop
        while (!exit)
        {
            int mainMenuSelection = mainMenu(pet);
            if(mainMenuSelection == 1 && pet.getHunger() == 0)
            {
                System.out.println(stringWrapper(pet.getName() + " is currently full!", 0 ));
            }
            else if (mainMenuSelection == 1 && pet.getHunger() > 0)
            {
                tick = true;
                Food consumedFood = foodSelection(petsFood);
                pet.feed(consumedFood);
                System.out.println(stringWrapper(pet.getName() + " has eaten the " + consumedFood.getName() + "!", 0));
            }
            else if (mainMenuSelection == 2 && pet.getThirst() == 0)
            {
                System.out.println(stringWrapper(pet.getName() + " is currently not thirsty!", 0));
            }
            else if (mainMenuSelection == 2 && pet.getThirst() > 0)
            {
                tick = true;
                pet.water();
                System.out.println(stringWrapper(pet.getName() + " has drank some water!", 0));
            }
            else if (mainMenuSelection == 3 && pet.getBoredom() == 0)
            {
                System.out.println(stringWrapper(pet.getName() + " is currently not bored!", 0));
            }
            else if (mainMenuSelection == 3 && pet.getBoredom() > 0)
            {
                tick = true;
                Toy usedToy = toySelection(petsToys);
                pet.play(usedToy);
                System.out.println(stringWrapper(pet.getName() + " has played with the " + usedToy.getName() + "!", 0));
            }
            else if (mainMenuSelection == 4 && pet.getTiredness() == 0)
            {
                System.out.println(stringWrapper(pet.getName() + " is currently not tired!", 0));
            }
            else if (mainMenuSelection == 4 && pet.getTiredness() > 0)
            {
                tick = true;
                pet.sleep();
                System.out.println(stringWrapper(pet.getName() + " has gotten some sleep!", 0));
            }
            else if (mainMenuSelection == 5 && pet.getSickness() == 0)
            {
                System.out.println(stringWrapper(pet.getName() + " is currently not sick!", 0));
            }
            else if (mainMenuSelection == 5 && pet.getSickness() > 0)
            {
                tick = true;
                pet.vet();
                System.out.println(stringWrapper(pet.getName() + " has been treated by the vet!", 0));
            }
            else if (mainMenuSelection == 6)
            {
                tick = true;
                System.out.println(stringWrapper(pet.getName() + " is ashamed of you!", 0));
            }
            else if (mainMenuSelection == 7)
            {
                if (confirmExit())
                {
                    exit = true;
                    System.out.println(stringWrapper("Your pet survived for " + pet.getTicksSurvived() + " hours!\nGOODBYE!", 0));
                }
            }
            if (tick)
            {
                pet.tick();
                tick = false;
            }
        }
    }

    // Utility Methods
    public static int getRandomNumber(int bound)
    {
        Random randomNum = new Random();
        return randomNum.nextInt(bound);
    }

    public static int getUserInt()
    {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    public static String getUserString()
    {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    public static String stringWrapper(String str, int style)
    {
        if(style == 1)
            return "-----------------------------------------------------------------------\n" + str;
        else if (style == 2)
            return str + "\n-----------------------------------------------------------------------";
        else
            return "-----------------------------------------------------------------------\n" + str
                    + "\n-----------------------------------------------------------------------";
    }

    // Control Methods
    public static Animal selectAnimalType()
    {
        Animal result = Animal.Other;
        boolean exit = false;
        while(!exit)
        {
            System.out.println(stringWrapper("Please select an animal type:\n[1]: Cat\n[2]: Dog\n[3]: Bunny\n[4]: Hamster", 0));
            int animalSelection = getUserInt();

            if(animalSelection == 1)
                result = Animal.Cat;
            else if(animalSelection == 2)
                result = Animal.Dog;
            else if(animalSelection == 3)
                result = Animal.Bunny;
            else if(animalSelection == 4)
                result = Animal.Hamster;

            System.out.println(stringWrapper("You have selected '" + result + "', is this correct?\nY/N",0));
            String input = getUserString();
            if(input.equalsIgnoreCase("y"))
            {
                exit = true;
            }
        }
        return result;
    }
    public static String nameAnimal()
    {
        boolean exit = false;
        String result = "";
        while (!exit)
        {
            System.out.println(stringWrapper("Please enter a name for your pet:", 0));
            result = getUserString();
            System.out.println(stringWrapper("You have entered '" + result + "', is this correct?\nY/N", 0));
            String confirm = getUserString();
            if(confirm.equalsIgnoreCase("y"))
                exit = true;
        }
        return result;
    }
    public static int mainMenu(VirtualPet pet)
    {
        String selectionMenuString = "[1]: Feed\n" + "[2]: Water\n" + "[3]: Play\n" + "[4]: Sleep\n"
                + "[5]: Vet\n" + "[6]: Nothing\n" + "[7]: Exit";

        System.out.println(stringWrapper(pet.toString(), 0));
        if(!pet.getIsDead())
        {
            System.out.println(stringWrapper(pet.getStatusBar(), 2));
            System.out.println(stringWrapper("What would you like to do?",2));
            System.out.println(stringWrapper(selectionMenuString ,2));
            return getUserInt();
        }
        else return 7;
    }
    public static boolean confirmExit()
    {
        System.out.println(stringWrapper("Are you sure you wish to exit?\nY/N",0));
        String input = getUserString();
        return input.equalsIgnoreCase("y");
    }
    public static Food foodSelection(Food[] foods)
    {
        boolean exit = false;
        int foodSelection = 0;
        while (!exit)
        {
            StringBuilder displayFood = new StringBuilder();
            System.out.println(stringWrapper("Please select a food:", 0));
            for(int i = 0; i <= foods.length - 1; i++)
            {
                displayFood.append("[").append(i).append("][").append(foods[i].getName()).append("]").append("[Hunger:").append(foods[i].getHunger()).append("]")
                        .append("[Thirst:").append(foods[i].getThirst()).append("]").append("\n");
            }
            displayFood.deleteCharAt(displayFood.length() - 1); // Removes last new line.
            System.out.println(stringWrapper(displayFood.toString(), 2));
            foodSelection = getUserInt();
            if(foodSelection <= foods.length - 1)
                exit = true;
            else
                System.out.println(stringWrapper("Please enter correct number!", 0));
        }
        return foods[foodSelection];
    }
    public static Toy toySelection(Toy[] toys)
    {
        boolean exit = false;
        int toySelection = 0;
        while (!exit)
        {
            StringBuilder displayToy = new StringBuilder();
            System.out.println(stringWrapper("Please select a toy:", 0));
            for(int i = 0; i <= toys.length - 1; i++)
            {
                displayToy.append("[").append(i).append("][").append(toys[i].getName()).append("]")
                        .append("[Boredom Cure:").append(toys[i].getBoredomCure()).append("]").append("\n");
            }
            displayToy.deleteCharAt(displayToy.length() - 1); // Removes last new line.
            System.out.println(stringWrapper(displayToy.toString(), 2));
            toySelection = getUserInt();
            if(toySelection <= toys.length - 1)
                exit = true;
            else
                System.out.println(stringWrapper("Please enter correct number!", 0));
        }
        return toys[toySelection];
    }
    public static Food[] animalFoodList(Animal animalType)
    {
        Food[] result = new Food[0];
        switch (animalType)
        {
            case Cat:
                result = new Food[]{new Food("Friskies", 20, 20), new Food("Purina", 5, -5),
                new Food("Meow Mix", 25, -5), new Food("Fancy Feast", 15, 10)}; break;
            case Dog:
                result = new Food[]{new Food("Blue Buffalo", 15, -5), new Food("Pedigree", 25, -5),
                        new Food("Cesar", 15, 20), new Food("Wellness Dog Food", 10, 15)}; break;
            case Bunny:
                result = new Food[]{new Food("Full Cheeks", 10, -5), new Food("Oxbow", 25, -5),
                        new Food("DuMOR", 15, 10), new Food("Wild Harvest", 10, 10)}; break;
            case Hamster:
                result = new Food[]{new Food("Full Cheeks", 15, -5), new Food("Higgins Sunburst", 10, -5),
                        new Food("Tiny Friends", 10, 5), new Food("Kaytee Pro", 25, 5)}; break;
        }
        return result;
    }
    public static Toy[] animalToyList(Animal animalType)
    {
        Toy[] result = new Toy[0];
        switch (animalType)
        {
            case Cat:
                result = new Toy[]{new Toy("Catnip", 50), new Toy("Laser Pointer", 30), new Toy("Play Rod", 20),
                        new Toy("Scratching Post", 15)}; break;
            case Dog:
                result = new Toy[]{new Toy("The Kong", 20), new Toy("Tennis Ball", 50), new Toy("Frisbee", 20),
                        new Toy("Rope", 15)}; break;
            case Bunny:
                result = new Toy[]{new Toy("Plastic Ball", 50), new Toy("Play Rod", 20), new Toy("Chewing Straw", 30),
                        new Toy("Rattles", 15)}; break;
            case Hamster:
                result = new Toy[]{new Toy("Tunnels", 50), new Toy("Hamster Wheel", 30), new Toy("Chewing Cubes", 20),
                        new Toy("Hamster Ball", 15)}; break;
        }
        return result;
    }
}