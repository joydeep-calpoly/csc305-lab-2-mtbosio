package task1;

import com.fasterxml.jackson.annotation.*;

final class Award {
	
	final private String name;
	final private Integer year;

	@JsonCreator
	protected Award(@JsonProperty("name") String name, @JsonProperty("year") Integer year) {
		this.name = name;
		this.year = year;
	}
	
	public String GetName() {
		return name;
	}
	
	public Integer GetYear() {
		return year;
	}


}
