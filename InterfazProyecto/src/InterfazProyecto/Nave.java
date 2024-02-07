package InterfazProyecto;

public class Nave {
	private String name = "";
	private String model = "";
	private String manufacturer = "";
	private String length = "";
	private String crew = "";
	private String cost_in_credits = "";
	private String passengers = "";
	private String cargo_capacity = "";
	private String starship_class = "";
	private String image = "";
	
	public Nave() {
		super();
	}

	public Nave(String name, String model, String manufacturer, String length, String crew, String cost_in_credits,
			String passengers, String cargo_capacity, String starship_class, String image) {
		super();
		this.name = name;
		this.model = model;
		this.manufacturer = manufacturer;
		this.length = length;
		this.crew = crew;
		this.cost_in_credits = cost_in_credits;
		this.passengers = passengers;
		this.cargo_capacity = cargo_capacity;
		this.starship_class = starship_class;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getCost_in_credits() {
		return cost_in_credits;
	}

	public void setCost_in_credits(String cost_in_credits) {
		this.cost_in_credits = cost_in_credits;
	}

	public String getPassengers() {
		return passengers;
	}

	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}

	public String getCargo_capacity() {
		return cargo_capacity;
	}

	public void setCargo_capacity(String cargo_capacity) {
		this.cargo_capacity = cargo_capacity;
	}

	public String getStarship_class() {
		return starship_class;
	}

	public void setStarship_class(String starship_class) {
		this.starship_class = starship_class;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	
}
