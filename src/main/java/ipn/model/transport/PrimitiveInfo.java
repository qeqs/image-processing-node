package ipn.model.transport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Vadim Lygin on 10/26/2017.
 */
@Getter
@Setter
public class PrimitiveInfo {
  private static final int WIDTH = 2;
  private static final int HEIGHT = 2;
  private static final int TYPE = Imgproc.MORPH_RECT;

  private Integer height;
  private Integer width;
  private Integer type;

  public PrimitiveInfo(){
    this(HEIGHT, WIDTH, TYPE);
  }

  public PrimitiveInfo(Integer height, Integer width, Integer type){
    this.type = type;
    this.height = height;
    this.width = width;
    if(width == null) this.width = WIDTH;
    if(height == null) this.height = HEIGHT;
    if(type == null) this.type = TYPE;
  }

  public void increment(int i){
    height+=i;
    width+=i;
  }

}
