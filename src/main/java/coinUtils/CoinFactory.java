package coinUtils;

import coinTypes.Coin;
import coinTypes.EUR;
import coinTypes.ILS;
import coinTypes.USD;

import java.security.InvalidParameterException;

/***
 * The class creates a new instance of a coin, using the Factory design pattern.
 * {@link Coin}, {@link ILS}, {@link USD}, {@link EUR}
 */
public class CoinFactory {
    /***
     * Creates a new instance of a coin, depending on the required currency
     * @param coin the required currency
     * @return return a new instance of the required currency
     */
    public static Coin getCoinInstance(Coins coin) {
        switch (coin) {
            case ILS: {
                return new ILS();
            }

            case USD: {
                return new USD();
            }

            case EUR: {
                return new EUR();
            }

            default:
                throw new InvalidParameterException("Invalid Coin.");
        }
    }
}
