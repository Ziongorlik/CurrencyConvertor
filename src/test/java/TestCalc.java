import coinTypes.Coin;
import coinUtils.CoinFactory;
import coinUtils.Coins;
import main.FileManager;
import main.Result;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/***
 * The class test the currency convertor
 */
public class TestCalc {
    /***
     * Test the conversion value
     */
    @Test
    public void convertValue() {
        Coin ilsCoin = CoinFactory.getCoinInstance(Coins.ILS);
        System.out.println(ilsCoin.calculate(5.5));
    }

    /***
     * Test to assert if the result is correct.
     */
    @Test
    public void assertResult() {
        double ilsValue = CoinFactory.getCoinInstance(Coins.ILS).calculate(5);
        Assert.assertEquals(ilsValue, 5 * 0.28);
    }

    /***
     * Test the content of the result file
     */
    @Test
    public void checkResultFile() {
        try {
            List<Result> resultList = FileManager.read();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}