package edu.pjatk.inn.futurecoffemaker.impl;

import edu.pjatk.inn.futurecoffemaker.CoffeeMaker;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public class CoffeeMakerUI implements edu.pjatk.inn.futurecoffemaker.CoffeeMakerUI {
    private CoffeeMaker coffeeMakerUI;

    public CoffeeMakerUI() {
        this.coffeeMakerUI = new CoffeeMakerImpl();
    }

    @Override
    public Context choosePurchaseBeverageOption(Context context) throws RemoteException, ContextException {
        context.putValue("coffeeMakerUI/optionsReturned", true);
        context.putValue("coffeeMakerUI/options", new String[]{"espresso", "latte", "americana"});
        return context;
    }

    @Override
    public Context chooseDrinkAndSugar(Context context) throws RemoteException, ContextException {
        String name   = null;
        Integer sugar = null;
        if (context.getValue("chooseDrink") != null) {
            name = (String) context.getValue("chooseDrink");
        }
        if (context.getValue("chooseSugar") != null) {
            sugar = (Integer) context.getValue("chooseSugar");
        }
        coffeeMakerUI.checkDrinkAvailability(context);
        context.putValue("coffeeMakerUI/chosenDrink", name);
        context.putValue("coffeeMakerUI/chosenSugar", sugar);

        return context;
    }

    @Override
    public Context payForDrink(Context context) throws RemoteException, ContextException {
        context.putValue("coffeeMakerUI/amountPaid", 10);
        return context;
    }
}
