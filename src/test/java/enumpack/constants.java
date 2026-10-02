package enumpack;

public enum constants {
	
	addplaceapi("/maps/api/place/add/json"),
	deleteplaceapi("/maps/api/place/delete/json"),
	getplaceapi("/maps/api/place/get/json");

	private String resource;

	constants(String resource)
	{
		this.resource=resource;
	}
	
	public String getresourceString() {
		return resource;
	}

}
