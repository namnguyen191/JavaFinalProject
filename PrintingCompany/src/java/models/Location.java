/*

 */
package models;

public class Location {
    protected int id;
    protected String locationName;
    protected int distributionCapacity;

    public Location(){     
    }
    
    public Location(int id) {
        this.id = id;
    }
    
    public Location(int id, String locationName, int distributionCapacity) {
        this(locationName,distributionCapacity);
        this.id = id;
        
    }
    
    public Location(String locationName, int distributionCapacity) {
        this.locationName = locationName;
        this.distributionCapacity = distributionCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getDistributionCapacity() {
        return distributionCapacity;
    }

    public void setDistributionCapacity(int distributionCapacity) {
        this.distributionCapacity = distributionCapacity;
    }
    
}
