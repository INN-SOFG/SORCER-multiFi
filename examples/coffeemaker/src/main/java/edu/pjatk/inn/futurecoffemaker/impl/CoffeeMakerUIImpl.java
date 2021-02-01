package edu.pjatk.inn.futurecoffemaker.impl;

import edu.pjatk.inn.futurecoffemaker.CoffeeMaker;
import edu.pjatk.inn.futurecoffemaker.CoffeeMakerUI;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

import static sorcer.mo.operator.value;

public class CoffeeMakerUIImpl implements CoffeeMakerUI {
    private CoffeeMaker coffeeMaker;

    public CoffeeMakerUIImpl() {
        this.coffeeMaker = new CoffeeMakerImpl();
    }

    @Override
    public Context choosePurchaseBeverageOption(Context context) throws RemoteException, ContextException {
        context.putValue("coffeeMakerUI/optionsReturned", true);
        context.putValue("coffeeMakerUI/options", new String[]{"espresso", "latte", "americana"});
        return context;
    }

    @Override
    public Context chooseDrinkAndSugar(Context context) throws RemoteException, ContextException {
        String name           = null;
        if (context.getValue("chooseDrink") != null) {
            name = (String) context.getValue("chooseDrinkAndSugar");
        }
        coffeeMaker.checkDrinkAvailability(context);
        context.putValue("coffeeMakerUI/chosenDrink", name);
        return context;
    }

    @Override
    public Context payForDrink(Context context) throws RemoteException, ContextException {
        context.putValue("coffeeMakerUI/amountPaid", 10);
        return context;
    }
}
