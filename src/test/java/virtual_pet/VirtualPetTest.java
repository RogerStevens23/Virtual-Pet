package virtual_pet;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VirtualPetTest
{
    VirtualPet underTest = new VirtualPet("Gilbert", Animal.Cat);
    @Test
    public void virtualPetIsCreatedWithNameAndStartingAttributes()
    {
        assertEquals("Gilbert", underTest.getName());
        assertEquals(Animal.Cat, underTest.getAnimalType());
        assertEquals(0,underTest.getHunger());
        assertEquals(0,underTest.getThirst());
        assertEquals(0,underTest.getBoredom());
        assertEquals(0,underTest.getTiredness());
        assertEquals(0,underTest.getSickness());
    }
    @Test
    public void virtualPetIsCreatedWithNameAndAssignedStartingAttributes()
    {
        VirtualPet underTest = new VirtualPet("Gilbert", Animal.Cat, 50, 50, 25, 75, 100);
        assertEquals("Gilbert", underTest.getName());
        assertEquals(Animal.Cat, underTest.getAnimalType());
        assertEquals(50,underTest.getHunger());
        assertEquals(50,underTest.getThirst());
        assertEquals(25,underTest.getBoredom());
        assertEquals(75,underTest.getTiredness());
        assertEquals(100,underTest.getSickness());
    }
    @Test
    public void hungerValueCanBeLowered()
    {
        VirtualPet underTest = new VirtualPet("Gilbert", Animal.Cat, 50, 50, 0, 0, 0);
        underTest.feed(new Food("Friskies", 25, 25));
        assertEquals(25, underTest.getHunger());
        assertEquals(25, underTest.getThirst());
    }
    @Test
    public void thirstValueCanBeLowered()
    {
        VirtualPet underTest = new VirtualPet("Gilbert", Animal.Cat, 0, 50, 0, 0, 0);
        underTest.water();
        assertEquals(25, underTest.getThirst());
    }
    @Test
    public void negativeFoodAndWaterValuesRaiseAttributes()
    {
        underTest.feed(new Food("Garbage", -25, -25));
        assertEquals( 25,underTest.getHunger());
        assertEquals(25, underTest.getThirst());
    }
    @Test
    public void allAttributesCanBeLowered()
    {
        VirtualPet underTest = new VirtualPet("Gilbert", Animal.Cat, 50, 50, 50, 50, 50);
        underTest.play(new Toy("Scratching Post", 25));
        underTest.vet();
        underTest.sleep();
        assertEquals(25, underTest.getBoredom());
        assertEquals(0, underTest.getTiredness());
        assertEquals(0, underTest.getSickness());
    }
    @Test
    public void virtualPetAttributesAreInRange0To100()
    {
        VirtualPet underTest = new VirtualPet("Gilbert", Animal.Cat, 50, 50, 50, 50,50);
        underTest.feed(new Food("TestFood", 100, 10));
        assertEquals(0,underTest.getHunger());
        assertEquals(40,underTest.getThirst());
        assertEquals(50,underTest.getBoredom());
        assertEquals(50,underTest.getTiredness());
        assertEquals(50,underTest.getSickness());
    }
    @Test
    public void allAttributesAreRaisedWithTickAndInRange()
    {
        underTest.tick();
        assertTrue(underTest.getHunger() >= 0 && underTest.getHunger() <= 100);
        assertTrue(underTest.getThirst() >= 0 && underTest.getThirst() <= 100);
        assertTrue(underTest.getBoredom() >= 0 && underTest.getBoredom() <= 100);
        assertTrue(underTest.getTiredness() >= 0 && underTest.getTiredness() <= 100);
        assertTrue(underTest.getSickness() >= 0 && underTest.getSickness() <= 100);
        assertTrue(underTest.getHealth() >= 0 && underTest.getHealth() <= 100);
    }
    @Test
    public void healthIsLoweredBy1IfAnyAttributeIs100DuringTick()
    {
        underTest.raiseAttributesBasedOnMultiplier(10);
        assertEquals(100, underTest.getHunger());
        underTest.tick();
        assertEquals(19, underTest.getHealth());
        underTest.tick();
        assertEquals(18, underTest.getHealth());
    }
}