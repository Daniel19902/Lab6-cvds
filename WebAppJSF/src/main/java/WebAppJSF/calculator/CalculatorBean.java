package WebAppJSF.calculator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation") // @ManagedBean JavaServerFaces
@ManagedBean(name = "calculatorBean", eager = true) // javax:javaee-api:8.0.1
@ViewScoped
public class CalculatorBean implements Serializable {

    private static final long serialVersionUID;

    static {
        serialVersionUID = 1L;
    }

    private String inputField;
    private double mean;
    private double standardDeviation;
    private double variance;
    private String modes;

    private List<Double> doubleList;
    private List<String> record = new ArrayList<>();

    public CalculatorBean() {}

    public void viewMean() {
        if (inputIsValid(getInputField())) setMean(calculateMean());
    }

    public void viewStandardDeviation() {
        if (inputIsValid(getInputField())) setStandardDeviation(calculateStandardDeviation());
    }

    public void viewVariance() {
        if (inputIsValid(getInputField())) setVariance(calculateVariance());
    }

    public void viewModes() {
        if (inputIsValid(getInputField())) setModes(calculateModes().toString());
    }

    public double calculateMean() {
        double sum = 0;
        for (double number : getDoubleList()) {
            sum += number;
        }
        return sum / getDoubleList().size();
    }

    public double calculateStandardDeviation() {
        final double tempMean = calculateMean();
        double squaredDifferencesSummation = 0;
        for (double number : getDoubleList()) {
            squaredDifferencesSummation += Math.pow((number - tempMean), 2);
        }
        final double meanSquaredDifferences = squaredDifferencesSummation / getDoubleList().size();
        return Math.sqrt(meanSquaredDifferences);

    }

    public double calculateVariance() {
        final double tempStandardDeviation = calculateStandardDeviation();
        return Math.pow(tempStandardDeviation, 2);
    }

    public List<Double> calculateModes() {
        final List<Double> modes = new ArrayList<>();
        final Map<Double, Integer> countMap = new HashMap<>();

        int max = -1;

        for (final double n : getDoubleList()) {
            int count;

            if (countMap.containsKey(n)) count = countMap.get(n) + 1;
            else count = 1;

            countMap.put(n, count);

            if (count > max) {
                max = count;
            }
        }

        for (final Map.Entry<Double, Integer> tuple : countMap.entrySet()) {
            if (tuple.getValue() == max) {
                modes.add(tuple.getKey());
            }
        }

        return modes;
    }

    public void restart() {
        setInputField("");
        this.record.clear();
    }

    private boolean inputIsValid(String input) {
        try {
            setDoubleList(stringToDoubleList(getInputField()));
            if (!this.record.contains(getDoubleList().toString())) {
                this.record.add(getDoubleList().toString());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private List<Double> stringToDoubleList(String input) {
        String[] numbers = input.split(";");
        List<Double> doubleList = new ArrayList<>();
        for (String number : numbers) {
            doubleList.add(Double.parseDouble(number));
        }
        return doubleList;
    }

    public String getInputField() {
        return inputField;
    }

    public void setInputField(String inputField) {
        this.inputField = inputField;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public List<Double> getDoubleList() {
        return doubleList;
    }

    public void setDoubleList(List<Double> doubleList) {
        this.doubleList = doubleList;
    }

    public List<String> getRecord() {
        return record;
    }

    public void setRecord(List<String> record) {
        this.record = record;
    }
}
