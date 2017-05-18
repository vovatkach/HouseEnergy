package com.vovatkach2427gmail.houseenergyoptimization.Algoritm;

import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
/*
	public static void main(String[] args) {
		ObjectiveFunction obf = new ObjectiveFunction();
		List<Device> listOfDevices = new ArrayList<Device>();
		listOfDevices.add(new Device("Праска", 1.5, 1, 1, 14));
		listOfDevices.add(new Device("Пральня", 1.8, 2, 1, 10));
		listOfDevices.add(new Device("Телевізор", 0.3, 3, 1, 15));
		listOfDevices.add(new Device("Лампи", 0.5, 4, 1, 2));
		listOfDevices.add(new Device("Комп'ютер", 0.2, 10, 1, 13));
		listOfDevices.add(new Device("Порохотяг", 1.4, 5, 1, 10));
		listOfDevices.add(new Device("Мікрохвильовка", 1, 2, 1, 10));
		listOfDevices.add(new Device("Зарядні пристрої", 0.1, 2, 1, 15));
		listOfDevices.add(new Device("Холодильник", 0.3, 5, 1, 24));
		listOfDevices.add(new Device("Котел підігріву", 0.2, 5, 10, 15));
		int powerConsumption = 74;

		List<List<Suit>> extremeSuit = obf.findTheOptimalSuite(listOfDevices, powerConsumption);
		System.out.println("Нехай задано бажані витрати = " + powerConsumption);

		System.out.println();
		System.out.println("Список всіх ноборів:");
		if (extremeSuit.isEmpty()) {
			System.out.println("Нажаль не вдалося зайти список наборів (прилад+час), які б витрачали бажану"
					+ "кількість енергії. Перевірте обмеження на роботу приладів. Можливо максимально можлива "
					+ "кількість спожитої енергії значно менше від бажаних затрат.");
		} else {
			for (int i = 0; i < extremeSuit.size(); i++) {
				for (Suit suit : extremeSuit.get(i)) {
					System.out.println(suit);
				}
				System.out.println("Загальна ціна набору = " + obf.getCostOfSuits(extremeSuit.get(i)));
				System.out.println("Загальний пріорітет набору = " + obf.getPriorityOfSuits(extremeSuit.get(i)));
				System.out.println();
				System.out.println("**************** Наступний розв'язок *******************");
			}
		}

	}
	*/

}
