package miles.server.Entities.Destination;

import miles.server.Entities.Airports.Airports;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DestinationConvertor {

    private static String EUROPE = "europe";
    private static String NORTH_AMERICA = "north america";
    private static String Hawaii = "hawaii";
    private static String Central_America_Caribbean = "Central America & Caribbean";
    private static String South_America = "South America";
    private static String Middle_East = "Middle East / Caucasus /Central Africa";
    private static String Southern_Africa = "Southern Africa";
    private static String India = "India";
    private static String South_East_Asia = "South East Asia";
    private static String Central_Asia_Far_East = "Central Asia / Far East";
    private static String Australia_New_Zealand = "Australia / New Zealand";
    private static String NA = "n/a";

    public static String covertAirportToGeographicalArea(String airport) {

        String currentCountry = Airports.getInstance().getCountry(airport);

        //europe
        Optional<String> option1 = europeList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option1.isPresent()) return EUROPE;

        //Hawaii
        Optional<String> option3 = hawaiiList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option3.isPresent()) return Hawaii;

        //Central America & Caribbean
        Optional<String> option4 = centralAmericaAndCaribbeanList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option4.isPresent()) return Central_America_Caribbean;

        //north america
        Optional<String> option2 = northAmericaList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option2.isPresent()) return NORTH_AMERICA;

        //South America
        Optional<String> option5 = southAmericaList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option5.isPresent()) return South_America;

        //Middle East
        Optional<String> option6 = middleEastList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option6.isPresent()) return Middle_East;

        //Southern Africa
        Optional<String> option7 = southernAfricaList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option7.isPresent()) return Southern_Africa;

        //India
        Optional<String> option8 = IndiaList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option8.isPresent()) return India;

        //South East Asia
        Optional<String> option9 = southEastAsiaList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option9.isPresent()) return South_East_Asia;

        //Central Asia / Far East
        Optional<String> option10 = centralAsiaFarEastList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option10.isPresent()) return Central_Asia_Far_East;

        //Australia / New Zealand
        Optional<String> option11 = australiaNewZealandList.stream().filter(country -> country.equalsIgnoreCase(currentCountry)).findAny();
        if (option11.isPresent()) return Australia_New_Zealand;

        return NA;
    }


    private static List<String> southernAfricaList = Arrays.asList(
            "Angola",
            "Botswana",
            "Democratic Republic of the Congo",
            "Lesotho",
            "Madagascar",
            "Malawi",
            "Mauritius",
            "Mozambique",
            "Namibia",
            "Seychelles",
            "South Africa",
            "Swaziland",
            "Tanzania",
            "Zambia",
            "Zimbabwe"
    );

    private static List<String> centralAsiaFarEastList = Arrays.asList(
            "Mongolia",
            "China",
            "Japan",
            "North Korea",
            "South Korea",
            "Taiwan",
            "Hong Kong",
            "Macau",
            "Kazakhstan",
            "Kyrgyzstan",
            "Tajikistan",
            "Turkmenistan",
            "Uzbekistan"
    );

    private static List<String> southEastAsiaList = Arrays.asList(
            "Indonesia",
            "Thailand",
            "Philippines",
            "Malaysia",
            "Singapore",
            "Vietnam",
            "Myanmar",
            "Cambodia",
            "Laos",
            "Brunei",
            "Timor Leste"
    );

    private static List<String> centralAmericaAndCaribbeanList = Arrays.asList(
            "Belize",
            "Costa Rica",
            "El Salvador",
            "Guatemala",
            "Honduras",
            "Nicaragua",
            "Panama"
    );

    private static List<String> southAmericaList = Arrays.asList(
            "Argentina",
            "Bolivia",
            "Brazil",
            "Chile",
            "Colombia",
            "Ecuador",
            "Guyana",
            "Paraguay",
            "Peru",
            "Suriname",
            "Uruguay",
            "Venezuela"
    );

    private static List<String> australiaNewZealandList = Arrays.asList(
            "australia",
            "New Zealand");

    private static List<String> middleEastList = Arrays.asList(
            "Bahrain",
            "Cyprus",
            "Egypt",
            "Iran",
            "Iraq",
            "Israel",
            "Jordan",
            "Kuwait",
            "Lebanon",
            "Oman",
            "Qatar",
            "Saudi Arabia",
            "Syria",
            "Turkey",
            "United Arab Emirates",
            "Yemen"
    );

    private static List<String> northAmericaList = Arrays.asList(
            "Antigua and Barbuda",
            "Bahamas",
            "Barbados",
            "Bermuda",
            "Canada",
            "Cuba",
            "Dominica",
            "Dominican Republic",
            "Grenada",
            "Haiti",
            "Jamaica",
            "Mexico",
            "St. Kitts and Nevis",
            "St. Lucia",
            "St. Vincent and The Grenadines",
            "Trinidad and Tobago",
            "United States"
    );

    private static List<String> hawaiiList = Arrays.asList(
            "hawaii"
    );

    private static List<String> IndiaList = Arrays.asList(
            "India"
    );

    private static List<String> europeList = Arrays.asList(
            "Russia",
            "Germany",
            "France",
            "United Kingdom",
            "Italy",
            "Spain",
            "Ukraine",
            "Poland",
            "Romania",
            "Netherlands",
            "Belgium",
            "Greece",
            "Portugal",
            "Czech Republic",
            "Hungary",
            "Sweden",
            "Belarus",
            "Austria",
            "Switzerland",
            "Bulgaria",
            "Serbia",
            "Denmark",
            "Finland",
            "Slovakia",
            "Norway",
            "Ireland",
            "Croatia",
            "Bosnia and Herzegovina",
            "Moldova",
            "Lithuania",
            "Albania",
            "Macedonia",
            "Slovenia",
            "Latvia",
            "Kosovo",
            "Estonia",
            "Montenegro",
            "Luxembourg",
            "Malta",
            "Iceland",
            "Andorra",
            "Liechtenstein",
            "Monaco",
            "San Marino",
            "Gibraltar",
            "Vatican City"
    );
}
