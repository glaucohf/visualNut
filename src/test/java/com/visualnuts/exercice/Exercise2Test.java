package com.visualnuts.exercice;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.visualnuts.exercices.Country;
import com.visualnuts.exercices.Exercise2;

public class Exercise2Test {
		String fileName = "src\\main\\resources\\files\\countries.json";
		List<Country> countriesList = Exercise2.getCountriesList(fileName);	

	    @Test
	    void checkNumberOfCoutries() throws Exception {
	    	int expectedResult = 5;
	    	int result = Exercise2.countCountries(countriesList);
			Assert.assertEquals(expectedResult, result);
	    }
	
	    @Test
	    void checkCountryMostOfficialLanguageAndSpeakGerman() throws Exception {
	    	String expectedResult = "BE";
	        String result =	Exercise2.findCountryMostOfficialLanguageAndSpeakGerman(countriesList);    
	        Assert.assertEquals(expectedResult, result);
	    }
	    
	    @Test
	    void checkCountOfficialLanguages() throws Exception {
	    	int expectedResult = 6;
	    	int result = Exercise2.countOfficialLanguages(countriesList);
	    	Assert.assertEquals(expectedResult, result);
	    }
	    
	    @Test
	    void checkCountryWithHighestNumberOfLanguages() throws Exception {
	    	String expectedResult = "BE";
	        String result =	Exercise2.findCountryWithHighestNumberOfLanguages(countriesList);    
	        Assert.assertEquals(expectedResult, result);
	    }
	    
	    @Test
	    void checkMostCommonLanguages() throws Exception {
	    	String expectedResult = "de";
	        String result =	Exercise2.findMostCommonLanguages(countriesList);    
	        Assert.assertEquals(expectedResult, result);
	    }
}
