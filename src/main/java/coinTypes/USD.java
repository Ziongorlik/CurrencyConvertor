package coinTypes;

import java.io.Serializable;

/***
 * The class represent United States dollar
 * {@link Coin}
 */
public class USD extends Coin implements Serializable {
    private final double value = 3.52; // 1 ILS = 3.52 USD

    /***
     * Returns the hard coded value of USD amount that equals 1 ILS.
     * @return returns the hard coded value.
     */
    @Override
    double getValue() {
        return this.value;
    }

    /***
     * Calculates the multiplication of a value by quantity
     * @param amount amount of ILS needed to convert
     * @return returns the result of the value multiplied by the amount
     */
    @Override
    public double calculate(double amount) {
        return getValue() * amount;
    }
}
