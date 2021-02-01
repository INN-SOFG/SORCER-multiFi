package futurecoffeemaker;

import edu.pjatk.inn.futurecoffemaker.impl.CoffeeMakerFuture;
import org.junit.Test;
import sorcer.service.Context;
import sorcer.service.MogramException;
import sorcer.service.Routine;
import sorcer.service.SignatureException;

import static org.junit.Assert.assertEquals;
import static sorcer.eo.operator.*;
import static sorcer.mo.operator.value;
import static sorcer.so.operator.exert;

public class CoffeeMakerFutureTest {
    @Test
    public void testCheckDrinkAvailability() throws MogramException, SignatureException {
        Routine cmt = task(sig("checkDrinkAvailability", CoffeeMakerFuture.class), "espresso");
        Context out = context(exert(cmt));
        assertEquals(value(out, "coffeeMaker/drinkAvailable"), true);
    }

    @Test
    public void testPayForDrink() throws MogramException, SignatureException {
        Routine cmt = task(sig("payForDrink", CoffeeMakerFuture.class), 10);
        Context out = context(exert(cmt));
        assertEquals(value(out, "coffeeMaker/amountPaid"), 10);
    }

    @Test
    public void testPrepareBeverage() throws MogramException, SignatureException {
        Routine cmt = task(sig("prepareBeverage", CoffeeMakerFuture.class), "espresso");
        Context out = context(exert(cmt));
        assertEquals(value(out, "coffeeMaker/beverage"), "espresso");
    }
}
