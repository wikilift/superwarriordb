package Model;

public class SuperWarrior {
	
	private String name, description;
	private int wind,water,fire;
	public SuperWarrior(String name, String description, int wind, int water, int fire) {
		super();
		this.name = name;
		this.description = description;
		this.wind = wind;
		this.water = water;
		this.fire = fire;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getWind() {
		return wind;
	}
	public void setWind(int wind) {
		this.wind = wind;
	}
	public int getWater() {
		return water;
	}
	public void setWater(int water) {
		this.water = water;
	}
	public int getFire() {
		return fire;
	}
	public void setFire(int fire) {
		this.fire = fire;
	}
	
	
	
}