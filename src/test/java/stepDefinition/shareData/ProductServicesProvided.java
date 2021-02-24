package stepDefinition.shareData;

import java.util.List;

public class ProductServicesProvided {
    private List<String> servicesProvidedList;

    public ProductServicesProvided(List<String> servicesProvidedList) { this.servicesProvidedList = servicesProvidedList; }

    public List<String> getServicesProvidedList() {
        return servicesProvidedList;
    }
}