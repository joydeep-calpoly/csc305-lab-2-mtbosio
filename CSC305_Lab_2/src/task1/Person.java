package task1;
import com.fasterxml.jackson.annotation.*;

final class Person {
	
	final private String name;
	final private String[] knownFor;
	final private Award[] awards;

	@JsonCreator
	protected Person( @JsonProperty("name") String name, @JsonProperty("knownFor") String[] knownFor, @JsonProperty("awards") Award[] awards) {
		this.name = name;
		this.knownFor = knownFor.clone();
		this.awards = awards.clone();
	}
	
	public void PrintPerson() {
		System.out.println(name);
		System.out.println("\nKnown For:");
		
		for(String k : knownFor) {
			System.out.println("\t" + k);
		}
		
		System.out.println("\nAwards:");
		
		for(Award a : awards) {
			System.out.println("\t" + a.GetName() + ", " + a.GetYear());
		}
		
		System.out.println("");
	}
}
