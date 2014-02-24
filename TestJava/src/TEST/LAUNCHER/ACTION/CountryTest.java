package TEST.LAUNCHER.ACTION;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CountryTest {

	private String data = "Afghanistan, Zimbabwe, India, United States, Germany, China, Israel";
    private List<String> countries;
    private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private List<String> getCountries() {
		return countries;
	}

	private void setCountries(List<String> countries) {
		this.countries = countries;
	}
//	public static void main(String[] args) {
//		CountryTest ctObject = new CountryTest();
//		ctObject.execute();
//
//	}
	
	public void execute(){
		setCountries(new ArrayList<String>());
        StringTokenizer st = new StringTokenizer(data, ",");
 
        while (st.hasMoreTokens()) {
            getCountries().add(st.nextToken().trim());
        }
        System.out.println(getCountries());
	}

}
