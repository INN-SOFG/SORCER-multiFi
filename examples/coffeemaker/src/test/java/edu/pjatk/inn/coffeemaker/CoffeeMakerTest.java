package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;
import sorcer.service.Routine;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static sorcer.eo.operator.*;
import static sorcer.so.operator.eval;
import static sorcer.so.operator.exec;

/**
 * @author Mike Sobolewski
 */
@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class CoffeeMakerTest {
	private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerTest.class);

	private CoffeeMaker coffeeMaker;
	private Inventory inventory;
	private Recipe espresso, mocha, macchiato, americano, espresso1;

	@Before
	public void setUp() throws ContextException {
		coffeeMaker = new CoffeeMaker();
		inventory = coffeeMaker.checkInventory();

		espresso = new Recipe();
		espresso.setName("espresso");
		espresso.setPrice(50);
		espresso.setAmtCoffee(6);
		espresso.setAmtMilk(1);
		espresso.setAmtSugar(1);
		espresso.setAmtChocolate(0);

		mocha = new Recipe();
		mocha.setName("mocha");
		mocha.setPrice(100);
		mocha.setAmtCoffee(8);
		mocha.setAmtMilk(1);
		mocha.setAmtSugar(1);
		mocha.setAmtChocolate(2);

		macchiato = new Recipe();
		macchiato.setName("macchiato");
		macchiato.setPrice(40);
		macchiato.setAmtCoffee(7);
		macchiato.setAmtMilk(1);
		macchiato.setAmtSugar(2);
		macchiato.setAmtChocolate(0);

		americano = new Recipe();
		americano.setName("americano");
		americano.setPrice(40);
		americano.setAmtCoffee(7);
		americano.setAmtMilk(1);
		americano.setAmtSugar(2);
		americano.setAmtChocolate(0);
	}

	@Test
	public void testAddRecipe() {
		assertTrue(coffeeMaker.addRecipe(espresso));
	}

	@Test
	public void testContextCofee() throws ContextException {
		assertTrue(espresso.getAmtCoffee() == 6);
	}

	@Test
	public void testContextMilk() throws ContextException {
		assertTrue(espresso.getAmtMilk() == 1);
	}

	@Test
	public void addRecepie() throws Exception {
		coffeeMaker.addRecipe(mocha);
		assertEquals(coffeeMaker.getRecipeForName("mocha").getName(), "mocha");
	}

	@Test
	public void addContextRecepie() throws Exception {
		coffeeMaker.addRecipe(Recipe.getContext(mocha));
		assertEquals(coffeeMaker.getRecipeForName("mocha").getName(), "mocha");
	}

	@Test
	public void addServiceRecepie() throws Exception {
		Routine cmt = task(sig("addRecipe", coffeeMaker),
						context(types(Recipe.class), args(espresso),
							result("recipe/added")));

		logger.info("isAdded: " + exec(cmt));
		assertEquals(coffeeMaker.getRecipeForName("espresso").getName(), "espresso");
	}

	@Test
	public void addRecipes() throws Exception {
		coffeeMaker.addRecipe(mocha);
		coffeeMaker.addRecipe(macchiato);
		coffeeMaker.addRecipe(americano);
		coffeeMaker.addRecipe(espresso);

		assertEquals(coffeeMaker.getRecipeForName("mocha").getName(), "mocha");
		assertEquals(coffeeMaker.getRecipeForName("macchiato").getName(), "macchiato");
		assertEquals(coffeeMaker.getRecipeForName("americano").getName(), "americano");
		assertNull(coffeeMaker.getRecipeForName("espresso"));
	}

	@Test
	public void makeCoffee() throws Exception {
		coffeeMaker.addRecipe(espresso);
		int chocolate = coffeeMaker.checkInventory().getChocolate();
		int sugar = coffeeMaker.checkInventory().getSugar();
		int coffee = coffeeMaker.checkInventory().getCoffee();
		int milk = coffeeMaker.checkInventory().getMilk();

		assertEquals(coffeeMaker.makeCoffee(espresso, 200), 150);
		assertEquals(coffeeMaker.checkInventory().getChocolate(), chocolate - espresso.getAmtChocolate());
		assertEquals(coffeeMaker.checkInventory().getSugar(), sugar - espresso.getAmtSugar());
		assertEquals(coffeeMaker.checkInventory().getCoffee(), coffee - espresso.getAmtCoffee());
		assertEquals(coffeeMaker.checkInventory().getMilk(), milk - espresso.getAmtMilk());
	}

	@Test
	public void deleteRecipe () {
		assertTrue(coffeeMaker.addRecipe(espresso));
		boolean isAdded = false;
		for (int i = 0; i < coffeeMaker.getRecipes().length; i++) {
			if (coffeeMaker.getRecipes()[i].equals(espresso)) {
				isAdded = true;
			}
		}
		assertTrue(isAdded);
		assertTrue(coffeeMaker.deleteRecipe(espresso));
		boolean isDeleted = true;
		for (int i = 0; i < coffeeMaker.getRecipes().length; i++) {
			if (espresso.equals(coffeeMaker.getRecipes()[i])) {
				isDeleted = false;
			}
		}
		assertTrue(isDeleted);
	}

	@Test
	public void deleteRecipes () {
		coffeeMaker.deleteRecipes();
		for (int i = 0; i < coffeeMaker.getRecipes().length; i++) {
			assertTrue(coffeeMaker.getRecipes()[i].equals(new Recipe()));
		}
	}

	@Test
	public void editRecipe () {
		coffeeMaker.addRecipe(mocha);
		boolean mochaExists = false;
		for (int i = 0; i < coffeeMaker.getRecipes().length; i++) {
			if (coffeeMaker.getRecipes()[i].equals(mocha)) {
				mochaExists = true;
			}
		}
		assertTrue(mochaExists);
		coffeeMaker.editRecipe(mocha, espresso);
		boolean espressoExists = false;
		for (int i = 0; i < coffeeMaker.getRecipes().length; i++) {
			assertFalse(coffeeMaker.getRecipes()[i].equals(mocha));
			if (coffeeMaker.getRecipes()[i].equals(espresso)) {
				espressoExists = true;
			}
		}
		assertTrue(espressoExists);
	}

	@Test
	public void addInventory () {
		assertTrue(coffeeMaker.addInventory( 1, 1, 1, 1));
		assertFalse(coffeeMaker.addInventory( -1, 0, 0, 0));
		assertFalse(coffeeMaker.addInventory( 0, -1, 0, 0));
		assertFalse(coffeeMaker.addInventory( 0, 0, -1, 0));
		assertFalse(coffeeMaker.addInventory( 0, 0, 0, -1));

		assertEquals(coffeeMaker.checkInventory().getChocolate(), 16);
		assertEquals(coffeeMaker.checkInventory().getCoffee(), 16);
		assertEquals(coffeeMaker.checkInventory().getMilk(), 16);
		assertEquals(coffeeMaker.checkInventory().getSugar(), 16);
	}

}

