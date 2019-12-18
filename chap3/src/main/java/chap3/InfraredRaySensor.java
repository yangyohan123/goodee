package chap3;

public class InfraredRaySensor {
	private String name;
	private boolean objectFounded;
	public InfraredRaySensor(String name) {
		this.name = name;
	}
	public void foundObject() {
		this.objectFounded = true;
	}
	public String getName() {
		return name;
	}
	public boolean isObjectFounded() {
		return objectFounded;
	}
	@Override
	   public String toString() {
	      return "InfraredRaySensor [name=" + name + ", objecFounded=" + objectFounded + "]";
	   }
}
