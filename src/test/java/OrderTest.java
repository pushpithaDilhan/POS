import com.pos.util.DatabaseAccess;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderTest {

    @Before
    public void seedDB() throws SQLException {
        DatabaseAccess.getDatabaseAccess().clearDB("useritems");
        DatabaseAccess.getDatabaseAccess().clearDB("userorders");
        DatabaseAccess.getDatabaseAccess().addOrder("u01");
        DatabaseAccess.getDatabaseAccess().addItemtoOrder("u01",1,2,1);
        DatabaseAccess.getDatabaseAccess().addItemtoOrder("u01",1,3,2);
    }


    @Test
    public void testGetOrders(){
        Response response = RestAssured.get("http://localhost:8080/orders/");
        JsonPath resp = response.jsonPath();
        int status = response.getStatusCode();
        Assert.assertEquals(status, 200);
        HashMap order = (HashMap)(((ArrayList)resp.get("orders")).get(0));
        Assert.assertEquals(order.get("order_id"),"1");
        Assert.assertEquals(order.get("order_total"),"1780.0");
    }

    @Test
    public void testGetItemsOfOrder(){
        Response response = RestAssured.get("http://localhost:8080/order/1");
        JsonPath resp = response.jsonPath();
        int status = response.getStatusCode();
        Assert.assertEquals(status, 200);

        HashMap item = (HashMap)(((ArrayList)resp.get("items")).get(0));
        Assert.assertEquals(item.get("item_id"),"2");
        Assert.assertEquals(item.get("item_price"),980);
        Assert.assertEquals(item.get("item_name"),"Hot chillie chicken Pizza");
        Assert.assertEquals(item.get("quantity"),1);

        HashMap item2 = (HashMap)(((ArrayList)resp.get("items")).get(1));
        Assert.assertEquals(item2.get("item_id"),"3");
        Assert.assertEquals(item2.get("item_price"),400);
        Assert.assertEquals(item2.get("item_name"),"Chicken Burger");
        Assert.assertEquals(item2.get("quantity"),2);
    }

    // Create a new order
    @Test
    public void testCreateNewOrder(){
        RestAssured
                .given()
                .urlEncodingEnabled(true)
                .post("http://localhost:8080/order")
                .then()
                .statusCode(200);

        Response response = RestAssured.get("http://localhost:8080/orders/");
        JsonPath resp = response.jsonPath();
        int status = response.getStatusCode();
        Assert.assertEquals(status, 200);
        HashMap order = (HashMap)(((ArrayList)resp.get("orders")).get(1));
        Assert.assertEquals(order.get("order_id"),"2");
        Assert.assertEquals(order.get("order_total"),"0.0");
    }

    // Add item to a list
    @Test
    public void testAddItemToOrderList(){
        RestAssured
                .given()
                .urlEncodingEnabled(true)
                .param("item_id", 4)
                .param("quantity", 1)
                .post("http://localhost:8080/order/1")
                .then()
                .statusCode(200);

        Response response = RestAssured.get("http://localhost:8080/order/1");
        JsonPath resp = response.jsonPath();
        int status = response.getStatusCode();
        Assert.assertEquals(status, 200);
        HashMap item = (HashMap)(((ArrayList)resp.get("items")).get(2));
        // check whether item has added
        Assert.assertEquals(item.get("item_id"),"4");
        Assert.assertEquals(item.get("item_price"),380);
        Assert.assertEquals(item.get("item_name"),"Beef Burger");
        // check added quantity
        Assert.assertEquals(item.get("quantity"),1);

        // check order total
        Response response2 = RestAssured.get("http://localhost:8080/orders/");
        JsonPath resp2 = response2.jsonPath();
        int status2 = response2.getStatusCode();
        Assert.assertEquals(status2, 200);
        HashMap order = (HashMap)(((ArrayList)resp2.get("orders")).get(0));
        Assert.assertEquals(order.get("order_id"),"1");
        Assert.assertEquals(order.get("order_total"),"2160.0");
    }

    // Delete order
    @Test
    public void testDeleteOrder(){
        RestAssured
                .given()
                .delete("http://localhost:8080/order/1")
                .then()
                .statusCode(200);

        Response response = RestAssured.get("http://localhost:8080/orders/");
        JsonPath resp = response.jsonPath();
        int status = response.getStatusCode();
        Assert.assertEquals(status, 200);
        ArrayList order = (ArrayList)resp.get("orders");
        Assert.assertEquals(order.size(),0);
    }

    // Delete item from an order
    @Test
    public void testDeleteItemFromOrder(){
        RestAssured
                .given()
                .delete("http://localhost:8080/order/1/3")
                .then()
                .statusCode(200);

        Response response = RestAssured.get("http://localhost:8080/order/1");
        JsonPath resp = response.jsonPath();
        int status = response.getStatusCode();
        Assert.assertEquals(status, 200);
        HashMap item = (HashMap)(((ArrayList)resp.get("items")).get(0));
        Assert.assertEquals( ((ArrayList) resp.get("items")).size() ,1);
        // check whether item has deleted
        Assert.assertNotSame(item.get("item_id"),"3");
        Assert.assertNotSame(item.get("item_price"),400);
        Assert.assertNotSame(item.get("item_name"),"Chicken Burger");
        // check added quantity
        Assert.assertNotSame(item.get("quantity"),2);

        // check order total
        Response response2 = RestAssured.get("http://localhost:8080/orders/");
        JsonPath resp2 = response2.jsonPath();
        int status2 = response2.getStatusCode();
        Assert.assertEquals(status2, 200);
        HashMap order = (HashMap)(((ArrayList)resp2.get("orders")).get(0));
        Assert.assertEquals(order.get("order_id"),"1");
        Assert.assertEquals(order.get("order_total"),"980.0");
    }

    // Update item quantity of an item
    @Test
    public void testUpdateItem(){
        RestAssured
                .given()
                .param("quantity",3)
                .put("http://localhost:8080/order/1/2")
                .then()
                .statusCode(200);

        Response response = RestAssured.get("http://localhost:8080/order/1");
        JsonPath resp = response.jsonPath();
        int status = response.getStatusCode();
        Assert.assertEquals(status, 200);
        HashMap item = (HashMap)(((ArrayList)resp.get("items")).get(0));
        // check whether item has updated
        Assert.assertEquals(item.get("item_id"),"2");
        Assert.assertEquals(item.get("item_price"),980);
        Assert.assertEquals(item.get("item_name"),"Hot chillie chicken Pizza");
        // check added quantity
        Assert.assertEquals(item.get("quantity"),3);

        // check order total
        Response response2 = RestAssured.get("http://localhost:8080/orders/");
        JsonPath resp2 = response2.jsonPath();
        int status2 = response2.getStatusCode();
        Assert.assertEquals(status2, 200);
        HashMap order = (HashMap)(((ArrayList)resp2.get("orders")).get(0));
        Assert.assertEquals(order.get("order_id"),"1");
        Assert.assertEquals(order.get("order_total"),"3740.0");
    }

}
