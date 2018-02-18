package ipn.model.transport;

import lombok.Data;

import java.util.List;

@Data
public class GranulationData {

    List<List<Double>> data;
}
