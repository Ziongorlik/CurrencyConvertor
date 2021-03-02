package coinTypes;

import java.io.Serializable;

/***
 * The class represents Israeli new shekel
 * {@link Coin}
 */
public class ILS extends Coin implements Serializable {
    private final double value = 0.28; // 1 USD = 0.28 ILS

    /***
     * Returns the hard coded value of ILS amount that equals 1 USD.
     * @return returns the hard coded value.
     */
    @Override
    double getValue() {
        return this.value;
    }

    /***
     * Calculates the multiplication of a value by quantity
     * @param amount amount of USD needed to convert
     * @return returns the result of the value multiplied by the amount
     */
    @Override
    public double calculate(double amount) {
        return getValue() * amount;
    }
}