import com.pos.models.Item;
import com.pos.models.Order;
import com.pos.util.DataRepository;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class OrderTest {

    @Before
    public void seedDB(){
        DataRepository.clearRepository();
        Order testorder1  = new Order(Integer.toString(DataRepository.getLastOrder()));
        testorder1.addItem(new Item("1", "Dhal","160.00"));
        testorder1.addItem(new Item("2", "Rice","105.00"));
        testorder1.addItem(new Item("3", "Soda","40.00"));
        testorder1.addItem(new Item("4", "Beans","80.00"));
        testorder1.addItem(new Item("5", "Carrot","75.00"));
        DataRepository.addOrder(testorder1, 5);

        Order testorder2  = new Order(Integer.toString(DataRepository.getLastOrder()));
        testorder2.addItem(new Item("1", "Soya","45.00"));
        testorder2.addItem(new Item("2", "Chicken","750.00"));
        testorder2.addItem(new Item("3", "Butter","190.00"));
        testorder2.addItem(new Item("4", "Soda","40.00"));
        DataRepository.addOrder(testorder2, 4);
    }

    @Test
    public void testGetOrders(){
        Response response = RestAssured.get("http://localhost:8080/orders/");
        JsonPath resp = response.jsonPath();
        int status = response.getStatusCode();
        Assert.assertEquals(status, 200);
        Assert.assertEquals(resp.get("1").toString(), "460.0");
        Assert.assertEquals(resp.get("2").toString(), "1145.0");
    }

    @Test
    public void testGetItemsOfOrder(){
        Response response = RestAssured.get("http://localhost:8080/orderlist/1");
        int status = response.getStatusCode();
        List<HashMap<String, Object>> itemlist = response.jsonPath().getList("itemlist");
        Assert.assertEquals(status, 200);
        Assert.assertEquals(itemlist.get(0).get("item_name"), "Dhal");
        Assert.assertEquals(itemlist.get(1).get("item_name"), "Rice");
        Assert.assertEquals(itemlist.get(2).get("item_name"), "Soda");
        Assert.assertEquals(itemlist.get(3).get("item_name"), "Beans");
        Assert.assertEquals(itemlist.get(4).get("item_name"), "Carrot");
    }

}
