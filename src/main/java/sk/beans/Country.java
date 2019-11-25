package sk.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
	
	public String getName() {
		return name;
	}
	@JsonSetter("name")
	public void setName(String name) {
		this.name = name;
	}
	public String getFlag() {
		return flag;
	}
	@JsonSetter("flag")
	public void setFlag(String flag) {
		this.flag = flag;
	}
	private String name;
	private String flag;

}
