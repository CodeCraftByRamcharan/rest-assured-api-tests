package in.at;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;
import java.util.Map;

public class ParseWithJsonPath {

    @Test
    public void parseEmployeeJson(){

        JsonPath jsonPath = new JsonPath(JsonProvider.singleEmployeeJson());

        Assert.assertEquals(jsonPath.getString("employee.id"), "101");
        Assert.assertEquals(jsonPath.getString("employee.name"), "Alice Johnson");
        Assert.assertEquals(jsonPath.getInt("employee.age"), 30);
        Assert.assertEquals(jsonPath.getString("employee.department"), "Engineering");
        Assert.assertTrue(jsonPath.get("employee.isPermanent"));
        Assert.assertEquals(jsonPath.getString("employee.contact.email"),"alice.johnson@example.com");
        Assert.assertEquals(jsonPath.getString("employee.contact.phone"),"123-456-7890");

        List<String> skillsList = jsonPath.getList("employee.skills");

    //    System.out.println(jsonPath.getList("employee.skills").size());
        Assert.assertEquals(jsonPath.getList("employee.skills").size(),4);
        Map<String, String> contactDetails = jsonPath.get("employee.contact");
        System.out.println(contactDetails);
        List<Map<String, String>> projects  =  jsonPath.get("employee.projects");
        System.out.println(projects);
    }


    @Test
    public void parseOrderDetails(){

        JsonPath jsonPath = new JsonPath(JsonProvider.orderJson());

        // Print Payment Details for the order
        Map<String, Object> paymentDetails = jsonPath.getMap("order.payment");
        System.out.println(paymentDetails);

        //Print the items in the Order
        List<Map<String, Object>> itemsList = jsonPath.getList("order.items");
        System.out.println(itemsList);

        //Print order details
        Map<String, Object> orderDetails = jsonPath.getMap("$");
        System.out.println(orderDetails);

        Map<String, Object> orderDtls = jsonPath.getMap("order");
        System.out.println(orderDetails);
    }

    @Test
    public void parseProductJson(){
        JsonPath jsonPath = new JsonPath(JsonProvider.productListJson());

        List<Map<String, Object>> productDetails = jsonPath.getList("$");
        System.out.println(productDetails);

        //Print Tags and rating info for each product
        productDetails.forEach(product ->{
            System.out.println("************************************************");
            System.out.println("Product Name : "+product.get("name"));
            List<String> tagsList = (List<String>) product.get("tags");
            System.out.println("Tags : " +tagsList);
            Map<String, Object> rating = (Map<String, Object>) product.get("rating");
            System.out.println("Average : " +rating.get("average"));
            System.out.println("Count : "+rating.get("count"));
        });
    }

    @Test
    public void parseMapJson(){
        JsonPath jsonPath = new JsonPath(JsonProvider.complexMapJson());
        Map<String, Object> objectMap = jsonPath.getMap("$");
        System.out.println(objectMap);
    }
}
