import static  org.junit.Assert.*;
import  org.junit.*;


public class ClientTest {
    @Test
    public void clientReturnsAnInstanceOfObject_object(){
        Client client = new Client("shiro", 20, "may", "koma");
        assertTrue(client instanceof Object);
    }
}
