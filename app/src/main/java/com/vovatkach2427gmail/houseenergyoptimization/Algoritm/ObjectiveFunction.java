package com.vovatkach2427gmail.houseenergyoptimization.Algoritm;

import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

public class ObjectiveFunction {

	/*
	 * Початкові по координатах будуть рівні 24 години для всіх приладів. З цим
	 * кроком будумемо шукати розв'язки. Тобто стартові рух по координатах часу
	 * буде дельта годин, з кожною ітрацією він буде зменшуватися аж до
	 * мінімального числа (1 година).
	 */
	public final static int DELTA = 10;

	/* Параметр алгоритму, зазвичай вибирають рівним 2 */
	public final static int LAMBDA = 2;
	/*
	 * Прирости будемо зменшувати допоки вони не будуть менші від наперед
	 * заданого еписилон, яке рівне 1 година.
	 */
	public final static int EPSILON = 1;
	/*
	 * Наш список (списків наборів), він же буде розв'язком основної задачі. Ми
	 * його наповнюватимемо розв'язками, для яких відхилення від початкового
	 * плану не значне, а саме вірізняється на CONST відсотків від вхідного
	 * параметра powerConsumptionValue.
	 */
	public final static double CONST = 0.1;

	public List<List<Suit>> findTheOptimalSuite(final List<Device> listOfDevices, final double powerConsumptionValue) {

		/* Сортуємо вхідний набір по пріорітету приладів. */
		sortDevicesByPrioryty(listOfDevices);

		/* Стоврюємо опорний план наборів. */
		List<Suit> initialSuitsPlan = new ArrayList<Suit>();

		/* Прирости, які будуть зменшуватися. Початкове рівне сталій. */
		int delta = DELTA;

		/* Допустиме відхилення від powerConsumptionValue */
		double deviation = CONST * powerConsumptionValue;

		/*
		 * Формуємо початковий план і одразу ініціалізуємо тимчасовий план. Він
		 * буде складатися з приладів для яких час роботи дорівнює мінімально
		 * можливому.
		 */
		for (Device device : listOfDevices) {
			initialSuitsPlan.add(new Suit(device, device.gettMin()));
		}
		/*
		 * Наш список (списків наборів), він же буде розв'язком основної задачі.
		 * Ми його наповнюватимемо розв'язками, для яких відхилення від
		 * початкового плану не значне, а саме вірізняється на стале число А.
		 */
		List<List<Suit>> listOfSulutions = new ArrayList<>();

		/* Перевірка умови на кроці 5 математичного методу: */
		while (delta >= EPSILON) {
			/*
			 * Кожна ітерація пошуку відділена цими симловами
			 */
			System.out.println("********************************************************");

			/* Значення кроку при кожній ітерації */
			System.out.println("DELTA=" + delta);
			System.out.println("___________________________________________________________");

			/* Базовий план */
			System.out.println("initialSuitPlan:" + initialSuitsPlan);
			/* Крок 2. */
			List<Suit> betterSuitsPlan = findBetterSuitPlan(initialSuitsPlan, powerConsumptionValue, delta);
			/* Покращений план, в теорії X^(k+1) */
			System.out.println("betterSuitPlan:" + betterSuitsPlan);
			/* Крок 4. */
			/*
			 * На Кроці 4 нам необхідно знайти "зразковий" план (в теорії він
			 * позначений X_zr) і план X_dp
			 */
			// Знаходимо X_zr

			List<Suit> zrSuitsPlan = new ArrayList<Suit>();
			for (int i = 0; i < betterSuitsPlan.size(); i++) {
				Suit tempSuit = betterSuitsPlan.get(i).clone();
				double timeOfWorkingTempSuit = initialSuitsPlan.get(i).getTimeOfWorknig() + LAMBDA
						* (betterSuitsPlan.get(i).getTimeOfWorknig() - initialSuitsPlan.get(i).getTimeOfWorknig());
				if ((timeOfWorkingTempSuit >= initialSuitsPlan.get(i).getDevice().gettMin())
						&& (timeOfWorkingTempSuit <= initialSuitsPlan.get(i).getDevice().gettMax())) {
					tempSuit.setTimeOfWorknig(initialSuitsPlan.get(i).getTimeOfWorknig() + LAMBDA
							* (betterSuitsPlan.get(i).getTimeOfWorknig() - initialSuitsPlan.get(i).getTimeOfWorknig()));
				}
				zrSuitsPlan.add(tempSuit);
			}
			/* Друкуємо значення x^(zr) */
			System.out.println("zrSuitPlan:" + zrSuitsPlan);
			// Знаходимо X_dp
			List<Suit> dPSuitsPlan = findBetterSuitPlan(zrSuitsPlan, powerConsumptionValue, delta);
			/* Друкуємо значення x^(dp) */
			System.out.println("dpSuitPlan:" + dPSuitsPlan);
			/*
			 * Перевірямо виконання умови в кроці 4. Якщо X_zr != X_Dp, то X_dp
			 * стає новим планом (новим базовим планом) у іншому випадку x_(k+1)
			 * -- новий план. У наших позначеннях x_(k+1) це betterSuitPlan.
			 */
			if (!areEqualTwoListsOfSuits(zrSuitsPlan, dPSuitsPlan)) {
				System.out.println("zr != dp (План за зразком не дорівнює плану покращеному за зразком)");
				initialSuitsPlan = dPSuitsPlan;

			} else {
				initialSuitsPlan = betterSuitsPlan;
			}

			System.out.println("OPTIMAL SUITPLAN " + initialSuitsPlan);
			System.out.println("Сost of OPTIMAL SUITPLAN  = " + getCostOfSuits(initialSuitsPlan));
			System.out.println("Priority of OPTIMAL SUITPLAN  = " + getPriorityOfSuits(initialSuitsPlan));
			if (Math.abs(valueOfObjectiveFunction(initialSuitsPlan) - powerConsumptionValue) < deviation) {

				/*
				 * Перевіримо, чи такий план є вже в списку наших розв'язків:
				 */
				boolean isInside = false;
				for (List<Suit> listSuits : listOfSulutions) {
					if (areEqualTwoListsOfSuits(listSuits, initialSuitsPlan)) {
						isInside = true;
					}
				}
				if (!isInside) {
					listOfSulutions.add(initialSuitsPlan);
				}
			}

			/* В кінці кожної ітерації зменшуємо параметр кроку дельта. */
			delta = delta - 1;
		}

		/*
		 * Сортуємо список розв'язків по пріоорітету для користувача. Його
		 * рахуємо за нехитрою формулою: Пріорітет = сума по всіх девейсах
		 * добутку їх пріорітетів на час роботи.
		 */
		sortByPriority(listOfSulutions);

		return listOfSulutions;
	}

	/*
	 * Тут реалізовано алгоритм кроку 2. Пошук кращого плану
	 */
	List<Suit> findBetterSuitPlan(final List<Suit> initialSuits, final double powerConsumptionValue, final int delta) {

		List<Suit> betterSuits = new ArrayList<Suit>();

		for (Suit suit : initialSuits) {
			betterSuits.add(suit.clone());

		}
		List<List<Suit>> listOfTempSuits = new ArrayList<List<Suit>>();
		listOfTempSuits.add(initialSuits);

		for (int i = 0; i < betterSuits.size(); i++) {

			Suit swapSuit = betterSuits.get(i).clone();
			Suit oldSuit = betterSuits.get(i).clone();

			if (swapSuit.getTimeOfWorknig() + delta <= swapSuit.getDevice().gettMax()) {
				swapSuit.setTimeOfWorknig(swapSuit.getTimeOfWorknig() + delta);
				betterSuits.add(i, swapSuit);
				betterSuits.remove(i + 1);

				if (module(valueOfObjectiveFunction(betterSuits), powerConsumptionValue) < module(
						valueOfObjectiveFunction(initialSuits), powerConsumptionValue)) {
					List<Suit> tempList = new ArrayList<>();
					for (Suit suit : betterSuits) {
						tempList.add(suit.clone());
					}
					listOfTempSuits.add(tempList);
				} else {
					betterSuits.add(i, oldSuit);
					betterSuits.remove(i + 1);
				}

			}

			swapSuit = betterSuits.get(i).clone();
			oldSuit = betterSuits.get(i).clone();

			if ((swapSuit.getTimeOfWorknig() - delta > 0)
					&& (swapSuit.getTimeOfWorknig() - delta >= swapSuit.getDevice().gettMin())) {
				swapSuit.setTimeOfWorknig(swapSuit.getTimeOfWorknig() - delta);
				betterSuits.add(i, swapSuit);
				betterSuits.remove(i + 1);

				if (module(valueOfObjectiveFunction(betterSuits), powerConsumptionValue) < module(
						valueOfObjectiveFunction(initialSuits), powerConsumptionValue)) {
					List<Suit> tempList = new ArrayList<>();
					for (Suit suit : betterSuits) {
						tempList.add(suit.clone());
					}
					listOfTempSuits.add(tempList);
				} else {
					betterSuits.add(i, oldSuit);
					betterSuits.remove(i + 1);
				}
			}
		}

		for (List<Suit> tempList : listOfTempSuits) {
			if (module(valueOfObjectiveFunction(tempList),
					powerConsumptionValue) < module(valueOfObjectiveFunction(betterSuits), powerConsumptionValue)) {
				betterSuits = tempList;
			}

		}
		return betterSuits;
	}

	/*
	 * Умовна на перевірку точності вибраного плану у порівнянні з наперед
	 * заданими витратами.
	 */
	private double module(final double valueOfObjectiveFunction, final double powerConsumptionValue) {
		return Math.pow(valueOfObjectiveFunction - powerConsumptionValue, 2);
	}

	/* Значення функції витрат енергії для конкретного набору. */
	private double valueOfObjectiveFunction(final List<Suit> consumerSuite) {
		double valueOfObjectiveFunction = 0;
		for (Suit suit : consumerSuite) {
			valueOfObjectiveFunction += suit.getTimeOfWorknig() * suit.getDevice().getPowerConsumption();
		}
		return valueOfObjectiveFunction;
	}

	/* Метод для сортування приладів по їх характеристиці priority. */
	private void sortDevicesByPrioryty(List<Device> listOfDevice) {
		Collections.sort(listOfDevice, new Comparator<Device>() {
			public int compare(Device dev1, Device dev2) {
				return dev2.getPriority() - dev1.getPriority();
			}
		});
	}

	/* Метод для перевірки рівності двох списків наборів */
	private boolean areEqualTwoListsOfSuits(final List<Suit> list1, final List<Suit> list2) {
		if (list1.size() != list2.size()) {
			return false;
		}
		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(i).getTimeOfWorknig() != list2.get(i).getTimeOfWorknig()) {
				return false;
			}
		}
		return true;
	}

	/* Метод для сортування списку по спаданню пріорітета. */
	private void sortSuitsByPrioryty(List<Suit> listOfSuits) {
		Collections.sort(listOfSuits, new Comparator<Suit>() {
			public int compare(Suit suit1, Suit suit2) {
				return (int) (suit2.getDevice().getPriority() * suit2.getTimeOfWorknig()
						- suit1.getDevice().getPriority() * suit1.getTimeOfWorknig());
			}
		});
	}

	/* Метод, який повертає значення пріорітету для ліста наборів. */
	public double getPriorityOfSuits(final List<Suit> listSuits) {
		double result = 0;
		for (Suit suit : listSuits) {
			result += suit.getPriority();
		}
		return result;
	}

	/* Метод, який повертає значення спожитої енергії для списку наборів. */
	public double getCostOfSuits(final List<Suit> listSuits) {
		double result = 0;
		for (Suit suit : listSuits) {
			result += suit.getCost();
		}
		return result;
	}

	/*
	 * Метод, який сотрує список списків наборів по пріорітету.
	 */
	private void sortByPriority(List<List<Suit>> listOfListsOfSuits) {

		/*
		 * Посортуємо кожен списко наборів. Тобто поскладаємо так, щоби прилади
		 * з найбільшим пріорітетом були перші в списку.
		 */
		for (List<Suit> list : listOfListsOfSuits) {
			sortSuitsByPrioryty(list);
		}

		Collections.sort(listOfListsOfSuits, new Comparator<List<Suit>>() {
			public int compare(List<Suit> list1, List<Suit> list2) {
				return (int) (getPriorityOfSuits(list2) - getPriorityOfSuits(list1));
			}

		});
	}
}
