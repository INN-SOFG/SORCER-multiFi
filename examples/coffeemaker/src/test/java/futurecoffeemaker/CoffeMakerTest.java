package futurecoffeemaker;

import edu.pjatk.inn.futurecoffemaker.CoffeeMaker;
import org.junit.Test;
import sorcer.service.Context;
import sorcer.service.MogramException;
import sorcer.service.Routine;
import sorcer.service.SignatureException;

import static org.junit.Assert.assertEquals;
import static sorcer.eo.operator.*;
import static sorcer.mo.operator.value;
import static sorcer.so.operator.exert;

public class CoffeMakerTest {
    @Test
    public void testCheckDrinkAvailability() throws MogramException, SignatureException {
        Routine cmt = task(sig("checkDrinkAvailability", CoffeeMaker.class), "espresso");
        Context out = context(exert(cmt));
        assertEquals(value(out, "coffeeMaker/drinkAvailable"), true);
    }

    @Test
    public void testPayForDrink() throws MogramException, SignatureException {
        Routine cmt = task(sig("payForDrink", CoffeeMaker.class), 10);
        Context out = context(exert(cmt));
        assertEquals(value(out, "coffeeMaker/amountPaid"), 10);
    }

    @Test
    public void testPrepareBeverage() throws MogramException, SignatureException {
        Routine cmt = task(sig("prepareBeverage", CoffeeMaker.class), "espresso");
        Context out = context(exert(cmt));
        assertEquals(value(out, "coffeeMaker/beverage"), "espresso");
    }
}
