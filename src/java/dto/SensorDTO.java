package dto;

import java.io.Serializable;

/**
 *
 * @author ksoff
 */
public class SensorDTO implements Serializable {
    
    private double temperature;
    private double humidity;

    public SensorDTO() {
    }

    public SensorDTO(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    
    
}
