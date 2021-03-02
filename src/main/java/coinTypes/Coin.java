package coinTypes;

import coinUtils.ICalculate;

/***
 * An abstract class represents a coin of currency
 */
public abstract class Coin implements ICalculate {
    abstract double getValue();

    @Override
    public double calculate(double amount) {
        return 0;
    }
}