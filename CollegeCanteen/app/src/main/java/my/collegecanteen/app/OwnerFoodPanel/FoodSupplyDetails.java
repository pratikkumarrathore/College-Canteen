package my.collegecanteen.app.OwnerFoodPanel;

public class FoodSupplyDetails {

    public String Dishes,Quantity,Price,Description,ImageURL,RandomUID,OwnerId;

    public FoodSupplyDetails(String dishes, String quantity, String price, String description, String imageURL, String randomUID, String ownerId) {
        Dishes = dishes;
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID = randomUID;
        OwnerId = ownerId;
    }

}
