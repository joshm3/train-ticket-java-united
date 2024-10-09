package modules.tsstationfoodservice.food.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;
import modules.tscommon.edu.fudan.common.entity.Food;
import org.hibernate.annotations.GenericGenerator;
@Data
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(indexes = { @Index(name = "station_store_idx", columnList = "station_name, store_name", unique = true) })
public class StationFoodStore {
    @Id
    @Column(name = "store_id")
    private String id;

    @NotNull
    @Column(name = "station_name")
    private String stationName;

    @Column(name = "store_name")
    private String storeName;

    private String telephone;

    private String businessTime;

    private double deliveryFee;

    @ElementCollection(targetClass = Food.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "station_food_list", joinColumns = @JoinColumn(name = "store_id"))
    private List<Food> foodList;

    public StationFoodStore() {
        // Default Constructor
        this.stationName = "";
    }
}