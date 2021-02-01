package edu.pjatk.inn.futurecoffemaker.impl;

import edu.pjatk.inn.futurecoffemaker.CoffeeMaker;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public class CoffeeMakerDBImpl implements CoffeeMaker {
    @Override
    public Context checkDrinkAvailability(Context context) throws RemoteException, ContextException {
        return context;
    }

    @Override
    public Context payForDrink(Context context) throws RemoteException, ContextException {
        return context;
    }
}
