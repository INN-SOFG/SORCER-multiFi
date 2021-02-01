package futurecoffeemaker;

import edu.pjatk.inn.futurecoffemaker.impl.CoffeeMakerUI;
import org.junit.Test;
import sorcer.service.*;

import static org.junit.Assert.assertEquals;
import static sorcer.ent.operator.ent;
import static sorcer.eo.operator.*;
import static sorcer.mo.operator.value;
import static sorcer.so.operator.exert;

public class CoffeeMakerUITest {

    @Test
    public void testChoosePurchaseBeverageOption() throws MogramException, SignatureException {
        Routine cmt = task(sig("choosePurchaseBeverageOption", CoffeeMakerUI.class), context(ent("any", "any")));
        Context out = context(exert(cmt));
        assertEquals(true, value(out, "coffeeMakerUI/optionsReturned"));
    }
    @Test
    public void testChooseDrinkAndSugar() throws MogramException, SignatureException {
        Routine cmt = task(sig("chooseDrinkAndSugar", CoffeeMakerUI.class),
                context(ent("chooseDrink", "espresso"), ent("chooseSugar", 10)));
        Context out = context(exert(cmt));
        assertEquals("espresso", value(out, "coffeeMakerUI/chosenDrink"));
        assertEquals(10, value(out, "coffeeMakerUI/chosenSugar"));

    }

    @Test
    public void testPayForDrink() throws MogramException, SignatureException {
        Routine cmt = task(sig("payForDrink", CoffeeMakerUI.class), context(ent("amountPaid", 10)));
        Context out = context(exert(cmt));
        assertEquals(10, value(out, "coffeeMakerUI/amountPaid"));
    }
}
