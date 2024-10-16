package modules.tspriceservice.price.controller;
import java.util.List;
import modules.tspriceservice.price.entity.PriceConfig;
import modules.tspriceservice.price.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.ok;
/**
 *
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/priceservice")
public class PriceController {
    @Autowired
    PriceService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceController.class);

    @GetMapping(path = "/prices/welcome")
    public String home() {
        return "Welcome to [ Price Service ] !";
    }

    @GetMapping("/prices/{routeId}/{trainType}")
    public HttpEntity query(@PathVariable
    String routeId, @PathVariable
    String trainType, @RequestHeader
    HttpHeaders headers) {
        PriceController.LOGGER.info("[findByRouteIdAndTrainType][Query price][RouteId: {}, TrainType: {}]", routeId, trainType);
        return ok(service.findByRouteIdAndTrainType(routeId, trainType, headers));
    }

    @PostMapping("/prices/byRouteIdsAndTrainTypes")
    public HttpEntity query(@RequestBody
    List<String> ridsAndTts, @RequestHeader
    HttpHeaders headers) {
        PriceController.LOGGER.info("[findByRouteIdAndTrainType][Query price][routeId and Train Type: {}]", ridsAndTts);
        return ok(service.findByRouteIdsAndTrainTypes(ridsAndTts, headers));
    }

    @GetMapping("/prices")
    public HttpEntity queryAll(@RequestHeader
    HttpHeaders headers) {
        PriceController.LOGGER.info("[findAllPriceConfig][Query all prices]");
        return ok(service.findAllPriceConfig(headers));
    }

    @PostMapping("/prices")
    public HttpEntity<?> create(@RequestBody
    PriceConfig info, @RequestHeader
    HttpHeaders headers) {
        PriceController.LOGGER.info("[createNewPriceConfig][Create price][RouteId: {}, TrainType: {}]", info.getRouteId(), info.getTrainType());
        return new ResponseEntity<>(service.createNewPriceConfig(info, headers), HttpStatus.CREATED);
    }

    @DeleteMapping("/prices/{pricesId}")
    public HttpEntity delete(@PathVariable
    String pricesId, @RequestHeader
    HttpHeaders headers) {
        PriceController.LOGGER.info("[deletePriceConfig][Delete price][PriceConfigId: {}]", pricesId);
        return ok(service.deletePriceConfig(pricesId, headers));
    }

    @PutMapping("/prices")
    public HttpEntity update(@RequestBody
    PriceConfig info, @RequestHeader
    HttpHeaders headers) {
        PriceController.LOGGER.info("[updatePriceConfig][Update price][PriceConfigId: {}]", info.getId());
        return ok(service.updatePriceConfig(info, headers));
    }
}