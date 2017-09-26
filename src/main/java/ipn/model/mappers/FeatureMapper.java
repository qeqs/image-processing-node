package ipn.model.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import ipn.model.Picture;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.MultiPartWriter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by Vadim Lygin on 9/25/2017.
 */
@Mapper
@Slf4j
public abstract class FeatureMapper {

  public static FeatureMapper MAPPER = Mappers.getMapper(FeatureMapper.class);

  private ObjectMapper mapper = new ObjectMapper();

  public File toCsv(Picture picture) {
    String filename = picture.getName() + ".csv";
    try (Writer writer = new FileWriter(filename)) {
      List<HashMap<String, Double>> listOfMap = new ArrayList<>();
      listOfMap.add(DataMapper.MAPPER.toStringKey(picture.getMatWaitOpeningChart()));
      listOfMap.add(DataMapper.MAPPER.toStringKey(picture.getMatWaitGradChart()));
      listOfMap.add(DataMapper.MAPPER.toStringKey(picture.getDeviationOpeningChart()));
      listOfMap.add(DataMapper.MAPPER.toStringKey(picture.getDeviationGradChart()));

      csvWriter(listOfMap, writer);
      return new File(filename);

    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

  private void csvWriter(List<HashMap<String, Double>> listOfMap, Writer writer)
      throws IOException {
    CsvSchema schema = null;
    CsvSchema.Builder schemaBuilder = CsvSchema.builder();
    if (listOfMap != null && !listOfMap.isEmpty()) {
      for (String col : listOfMap.get(0).keySet()) {
        schemaBuilder.addColumn(col);
      }
      schema = schemaBuilder.build().withLineSeparator(System.lineSeparator()).withHeader();
    }
    CsvMapper mapper = new CsvMapper();
    mapper.writer(schema).writeValues(writer).writeAll(listOfMap);

    writer.flush();
  }
}
