package modules.tsstationservice.fdse.microservice.controller;
import java.util.List;
import modules.tscommon.edu.fudan.common.util.Response;
import modules.tsstationservice.fdse.microservice.entity.Station;
import modules.tsstationservice.fdse.microservice.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.ok;
@RestController
@RequestMapping("/api/v1/stationservice")
public class StationController {
    @Autowired
    private StationService stationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StationController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader
    HttpHeaders headers) {
        return "Welcome to [ Station Service ] !";
    }

    @GetMapping("/stations")
    public HttpEntity query(@RequestHeader
    HttpHeaders headers) {
        return ok(stationService.query(headers));
    }

    @PostMapping("/stations")
    public ResponseEntity<Response> create(@RequestBody
    Station station, @RequestHeader
    HttpHeaders headers) {
        StationController.LOGGER.info("[create][Create station][name: {}]", station.getName());
        return new ResponseEntity<>(stationService.create(station, headers), HttpStatus.CREATED);
    }

    @PutMapping("/stations")
    public HttpEntity update(@RequestBody
    Station station, @RequestHeader
    HttpHeaders headers) {
        StationController.LOGGER.info("[update][Update station][StationId: {}]", station.getId());
        return ok(stationService.update(station, headers));
    }

    @DeleteMapping("/stations/{stationsId}")
    public ResponseEntity<Response> delete(@PathVariable
    String stationsId, @RequestHeader
    HttpHeaders headers) {
        StationController.LOGGER.info("[delete][Delete station][StationId: {}]", stationsId);
        return ok(stationService.delete(stationsId, headers));
    }

    // according to station name ---> query station id
    @GetMapping("/stations/id/{stationNameForId}")
    public HttpEntity queryForStationId(@PathVariable("stationNameForId")
    String stationName, @RequestHeader
    HttpHeaders headers) {
        // string
        StationController.LOGGER.info("[queryForId][Query for station id][StationName: {}]", stationName);
        return ok(stationService.queryForId(stationName, headers));
    }

    // according to station name list --->  query all station ids
    @CrossOrigin(origins = "*")
    @PostMapping("/stations/idlist")
    public HttpEntity queryForIdBatch(@RequestBody
    List<String> stationNameList, @RequestHeader
    HttpHeaders headers) {
        StationController.LOGGER.info("[queryForIdBatch][Query stations for id batch][StationNameNumbers: {}]", stationNameList.size());
        return ok(stationService.queryForIdBatch(stationNameList, headers));
    }

    // according to station id ---> query station name
    @CrossOrigin(origins = "*")
    @GetMapping("/stations/name/{stationIdForName}")
    public HttpEntity queryById(@PathVariable("stationIdForName")
    String stationId, @RequestHeader
    HttpHeaders headers) {
        StationController.LOGGER.info("[queryById][Query stations By Id][Id: {}]", stationId);
        // string
        return ok(stationService.queryById(stationId, headers));
    }

    // according to station id list  ---> query all station names
    @CrossOrigin(origins = "*")
    @PostMapping("/stations/namelist")
    public HttpEntity queryForNameBatch(@RequestBody
    List<String> stationIdList, @RequestHeader
    HttpHeaders headers) {
        StationController.LOGGER.info("[queryByIdBatch][Query stations for name batch][StationIdNumbers: {}]", stationIdList.size());
        return ok(stationService.queryByIdBatch(stationIdList, headers));
    }
}