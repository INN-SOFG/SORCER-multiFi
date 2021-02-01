package futurecoffeemaker;

import edu.pjatk.inn.futurecoffemaker.CoffeeMakerDB;
import org.junit.Test;
import sorcer.service.Context;
import sorcer.service.MogramException;
import sorcer.service.Routine;
import sorcer.service.SignatureException;

import static org.junit.Assert.assertEquals;
import static sorcer.eo.operator.*;
import static sorcer.mo.operator.value;
import static sorcer.so.operator.exert;

public class CoffeeMakerDBTest {
    @Test
    public void testCheckRecipe() throws MogramException, SignatureException {
        Routine cmt = task(sig("checkRecipe", CoffeeMakerDB.class), "espresso");
        Context out = context(exert(cmt));
        assertEquals(value(out, "coffeeMakerDB/recipe"), true);
    }

    @Test
    public void testCheckInventory() throws MogramException, SignatureException {
        Routine cmt = task(sig("checkInventory", CoffeeMakerDB.class), "coffee");
        Context out = context(exert(cmt));
        assertEquals(value(out, "coffeeMakerDB/coffee"), 20);
    }
}
