package com.massmutual.Utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.testng.Assert;

/***
 * 
 * @author Azhar Ali Baig
 *
 */

public class CurrencyUtil {
	/***
	 * Converts currency string to double using NumberFormat class
	 * 
	 * @param Currency
	 * Currency string to convert
	 * 
	 * @param localeCode
	 * Locale code of currency
	 * 
	 * @return double
	 * Double value of currency string
	 */
	public static double convertCurrencyToDouble(String Currency, String localeCode) {

		final NumberFormat format = NumberFormat.getNumberInstance(getLocale(localeCode));

		if (format instanceof DecimalFormat) {

			((DecimalFormat) format).setParseBigDecimal(true);
		}

		try {
			return ((BigDecimal) format.parse(Currency.replaceAll("[^\\d.,-]", ""))).doubleValue();

		} catch (ParseException e) {

			return 0.00;
		}
	}
	
	/***
	 * Converts currency string to double by replacing all the special characters
	 * 
	 * @param Currency
	 * Currency string to convert
	 * 
	 * @return double
	 * Double value of currency string
	 */
	public static double convertCurrencyToDouble(String Currency) {

		return Double.parseDouble(Currency.replaceAll("[^\\d.,-]", ""));

	}
	/***
	 * Converts currency string to BigDecimal and verifies if currency format is correctly displayed as per Locale code
	 * 
	 * @param currency
	 * Currency String whose format need to be checked
	 * 
	 * @param localeCode
	 * Locale code of the currency
	 * 
	 * @return BigDecimal
	 * BigDecimal if currency format matches
	 * null if currency format doesn't match
	 */
	public static BigDecimal getCurrencyFormat(String currency, String localeCode) {

		BigDecimalValidator validator = CurrencyValidator.getInstance();

		Locale locale = getLocale(localeCode);

		BigDecimal amount = validator.validate(currency, locale);

		return amount;

	}

	/***
	 * Get Locale Object based on currency code of country
	 * 
	 * @param currencyCode
	 * Country code for which Locale object is required
	 * 
	 * @return Locale
	 * Locale object corresponding to the currency code
	 */
	public static Locale getLocale(String currencyCode) {

		Locale locale = null;

		switch (currencyCode.toUpperCase()) {

		case "US":

			locale = Locale.US;

			break;

		default:
			Assert.assertTrue(false, currencyCode + " is not a valid currency code.");
			break;
		}

		return locale;

	}

}
