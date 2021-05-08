package techproedturkish01.techproedturkish01api;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class BookingDates {
	
	/*
	 POJO'da olması gerekenler;
	 1)JSON'da key olanlar icin variable olusturun ve access modifier'lerini private yapın
	 2)Her variable icin getter ve setter metodları olsuturun.
	 3)Parametresiz variable'ları parametre kabul eden parametreli constructor olusturun(icinde super() olmasın)
	 4)Olusturdugunuz variable'ları parametreli kabul eden parametreli constuctor olsuturun(icinde super() olmasın)
	 5)toString() metodu olusturun 
	 */

	@JsonProperty("checkin")
	private String checkin;
	@JsonProperty("checkout")
	private String checkout;
	

	@JsonProperty("checkin")
	public String getCheckin() {
	return checkin;
	}

	@JsonProperty("checkin")
	public void setCheckin(String checkin) {
	this.checkin = checkin;
	}

	@JsonProperty("checkout")
	public String getCheckout() {
	return checkout;
	}

	@JsonProperty("checkout")
	public void setCheckout(String checkout) {
	this.checkout = checkout;
	}

	public BookingDates(String checkin, String checkout) {
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	public BookingDates() {
		
	}

	@Override
	public String toString() {
		return "BookingDates [checkin=" + checkin + ", checkout=" + checkout + "]";
	}
}
