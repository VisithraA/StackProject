package com.visithraa23.stackproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TravelAssistantApp {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		TravelAssistantApp obj = new TravelAssistantApp();

		obj.destinations();

	}

	private void destinations() throws FileNotFoundException, IOException, ParseException {
		Stack<Map> travelDetails = new Stack<>();
		Scanner sc = new Scanner(System.in);
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(new FileReader(
				"C:\\Users\\ELCOT\\Desktop\\Git Folder\\StackProject\\src\\com\\visithraa23\\stackproject\\JsonTravel.json"));
		Map<String, Object> destinations = (Map) jsonObj;

		travelDetails.push((Map) destinations.get("destination"));

		do {
			System.out.println("\nEnter the Input:");
			destinationList(travelDetails.peek());
			System.out.println("0:Exit");
			System.out.println("-1:go Back");

			int input = sc.nextInt();
			if (input == -1) {
				if (travelDetails.size() > 1) {
					travelDetails.pop();
				}
				continue;
			} else if (input == 0) {
				System.out.println("Thank You...");
				break;
			} else {
				if (input <= travelDetails.peek().size()) {
					travelDetails.push((Map<String, String>) destinations.get(travelDetails.peek().get(input + "")));
				} else {
					System.out.println("Enter a Valid Input");
				}
			}
		} while (true);
		sc.close();
	}

	private void destinationList(Object values) {

		Map<String, Object> details = (Map) values;

		if (details != null) {
			for (Map.Entry<String, Object> map : details.entrySet()) {
				System.out.println(map.getKey() + ":" + map.getValue());
			}
		} else
			System.out.println("Wrong Input...");

	}

}