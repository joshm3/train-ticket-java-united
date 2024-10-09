package modules.tstravel2service.travel2.repository;
import java.util.ArrayList;
import modules.tscommon.edu.fudan.common.entity.TripId;
import modules.tstravel2service.travel2.entity.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author fdse
 */
@Repository
public interface TripRepository extends CrudRepository<Trip, TripId> {
    Trip findByTripId(TripId tripId);

    void deleteByTripId(TripId tripId);

    @Override
    ArrayList<Trip> findAll();

    ArrayList<Trip> findByRouteId(String routeId);
}