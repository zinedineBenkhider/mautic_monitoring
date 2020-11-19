package com.example.mautic_mobile.api.entities;
import java.util.Map;
public class ContactDetail {
    private int id;
    private int points;
    private String color;
    private Map<String,IpAddress> ipAddresses;
    private Fields fields;

    public ContactDetail(int id, int points,Map<String,IpAddress> ipAddresses,String color, Fields fields) {
        this.id = id;
        this.points = points;
        this.color = color;
        this.ipAddresses = ipAddresses;
        this.fields = fields;
    }

    public String getFirstName(){
        return fields.getCore().get("firstname").getValue();
    }

    public String getEmail(){
        return fields.getCore().get("email").getValue();
    }
    public String getMobileNumber(){
        return fields.getCore().get("mobile").getValue();
    }

    public String getLastName(){
        return fields.getCore().get("lastname").getValue();
    }

    public String getCity(){
        return ipAddresses.get(ipAddresses.keySet().iterator().next()).getIpDetails().getCity();
    }

    public String getCountry(){
        return ipAddresses.get(ipAddresses.keySet().iterator().next()).getIpDetails().getCountry();
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

   public Map<String, IpAddress> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(Map<String, IpAddress> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    public class Fields{

        private Map<String, Core> core;

        Fields( Map<String, Core> core) {
            this.core=core;
        }

        public Map<String, Core> getCore() {
            return core;
        }

        public void setCore(Map<String, Core> core) {
            this.core = core;
        }
    }
    public class IpAddress{
        private IpDetail ipDetails;

        public IpAddress(IpDetail ipDetails) {
            this.ipDetails = ipDetails;
        }

        public IpDetail getIpDetails() {
            return ipDetails;
        }

        public void setIpDetails(IpDetail ipDetails) {
            this.ipDetails = ipDetails;
        }
    }
    public class IpDetail{
        private String city;
        private String region;
        private String country;
        public IpDetail(String city, String region, String country) {
            this.city = city;
            this.region = region;
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
    public class Core{
        private int id;
        private String value;
        Core(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
