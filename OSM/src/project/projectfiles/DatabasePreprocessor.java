package project.projectfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabasePreprocessor {
    private List<CarModel> data;
    public DatabasePreprocessor(List<CarModel> a)
    {
        this.data = a;
    }
   public Map processData(String key1, String key2)
    {
      
        int aggregateNumberOfCars = this.data.size();
        double averageSpeedOfCars = 0.0;

        for(CarModel a: this.data)
        {
            double speed = a.getSpeed();
            averageSpeedOfCars+=speed;
        }

        averageSpeedOfCars/=aggregateNumberOfCars;
        Map<String, Number> processedData = new HashMap<String, Number>();
        processedData.put(key1, aggregateNumberOfCars);
        processedData.put(key2, averageSpeedOfCars);
        return processedData;

    }

}
