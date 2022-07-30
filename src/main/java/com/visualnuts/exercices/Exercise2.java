package com.visualnuts.exercices;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Exercise2 {

	public static int countCountries(List<Country> countriesList) {
		Stream<Country> allCountries = countriesList.stream();
		return (int) allCountries.distinct().count();
	}

	public static String findCountryMostOfficialLanguageAndSpeakGerman(List<Country> countriesList) {
		final String GERMAN = "de";
		List<Country> countriesSpeakGerman = filterByLanguage(countriesList, GERMAN);
		return findCountryWithHighestNumberOfLanguages(countriesSpeakGerman);
	}

	private static List<Country> filterByLanguage(List<Country> countriesList, String language) {
		List<Country> filteredCountries = new ArrayList<Country>();
		for (Country c : countriesList) {
			if (c.getLanguages().contains(language)) {
				filteredCountries.add(c);
			}
		}
		return filteredCountries;
	}

	private static List<String> getAllLanguages(List<Country> countriesList) {
		List<String> allLanguages = new ArrayList<String>();
		for (Country c : countriesList) {
			allLanguages.addAll(c.getLanguages());
		}
		return allLanguages;
	}

	public static int countOfficialLanguages(List<Country> countriesList) {
		return (int) getAllLanguages(countriesList).stream().distinct().count();
	}

	public static String findCountryWithHighestNumberOfLanguages(List<Country> countriesList) {
		Optional<Country> country = Optional.of(countriesList.get(0));
		int highestNumberOfOfficialLanguages = country.get().getLanguages().size();
		String countryWithHighestNumberOfOfficialLanguages = country.get().getNome();

		for (Country c : countriesList) {
			if (c.getLanguages().size() >= highestNumberOfOfficialLanguages) {
				highestNumberOfOfficialLanguages = c.getLanguages().size();
				countryWithHighestNumberOfOfficialLanguages = c.getNome();
			}
		}
		return countryWithHighestNumberOfOfficialLanguages;
	}

	public static String findMostCommonLanguages(List<Country> countriesList) {
		
		int biggestCount = 0;
		Map<String, Integer> countLanguageCountry = new HashMap<String, Integer>();
		for (Country c : countriesList) {
			List<String> languages = c.getLanguages();
			for (String language : languages) {
				if (countLanguageCountry.get(language) == null) {
					countLanguageCountry.put(language, 1);
				} else {
					Optional<Integer> actualCount = Optional.of(countLanguageCountry.get(language));
					int nextCount = actualCount.get() + 1;
					countLanguageCountry.put(language, nextCount);
					if (nextCount > biggestCount) {
						biggestCount = nextCount;
					}
				}
			}
		}

		 Optional<String> mostCommonLanguages = Optional.of(getKeysByValue(countLanguageCountry, biggestCount).get(0));		 
		 return mostCommonLanguages.get();
	}
	
    private static List<String> getKeysByValue(Map<String, Integer> map, Integer value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

	public static void main(String[] args) {
		String fileName = "src\\main\\resources\\files\\countries.json";
		List<Country> countriesList = getCountriesList(fileName);

		System.out.println(countCountries(countriesList));
		System.out.println(findCountryMostOfficialLanguageAndSpeakGerman(countriesList));
		System.out.println(countOfficialLanguages(countriesList));
		System.out.println(findCountryWithHighestNumberOfLanguages(countriesList));
		System.out.println(findMostCommonLanguages(countriesList));

	}

	@SuppressWarnings("unchecked")
	public static List<Country> getCountriesList(String fileName) {
		List<Country> countriesList = new ArrayList<Country>();

		Object ob;
		JSONArray countries;
		
		try {
			
			ob = (JSONArray) new JSONParser().parse(new FileReader(fileName));
			countries = (JSONArray) ob;
			for (int i = 0; i < countries.size(); i++) {
				JSONObject country = (JSONObject) countries.get(i);
				List<String> languages = (List<String>) country.get("languages");

				Country c = new Country();
				c.setNome(country.get("country").toString());
				c.setLanguages(languages);

				countriesList.add(c);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo Json não encontrado.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo Json.");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Arquivo Json mal formatado.");
			e.printStackTrace();
		}

		return countriesList;
	}

}
