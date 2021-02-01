package futurecoffeemaker;

import edu.pjatk.inn.futurecoffemaker.CoffeeMakerUI;
import org.junit.Test;
import sorcer.service.*;

import static org.junit.Assert.assertEquals;
import static sorcer.eo.operator.*;
import static sorcer.mo.operator.value;
import static sorcer.so.operator.exert;

public class CoffeeMakerUITest {

    @Test
    public void testChoosePurchaseBeverageOption() throws MogramException, SignatureException {
        Routine cmt = task(sig("choosePurchaseBeverageOption", CoffeeMakerUI.class));
        Context out = context(exert(cmt));
        assertEquals(true, value(out, "coffeeMakerUI/optionsReturned"));
    }
    @Test
    public void testChooseDrink() throws MogramException, SignatureException {
        Routine cmt = task(sig("chooseDrink", CoffeeMakerUI.class), "espresso");
        Context out = context(exert(cmt));
        assertEquals("espresso", value(out, "coffeeMakerUI/chosenDrink"));
    }

    @Test
    public void testPayForDrink() throws MogramException, SignatureException {
        Routine cmt = task(sig("payForDrink", CoffeeMakerUI.class), 10);
        Context out = context(exert(cmt));
        assertEquals(10, value(out, "coffeeMakerUI/amountPaid"));
    }
}
