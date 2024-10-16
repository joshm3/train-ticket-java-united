package modules.tstrainservice.train.controller;
import java.util.List;
import modules.tscommon.edu.fudan.common.util.Response;
import modules.tstrainservice.train.entity.TrainType;
import modules.tstrainservice.train.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.ok;
@RestController
@RequestMapping("/api/v1/trainservice")
public class TrainController {
    @Autowired
    private TrainService trainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainController.class);

    @GetMapping(path = "/trains/welcome")
    public String home(@RequestHeader
    HttpHeaders headers) {
        return "Welcome to [ Train Service ] !";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/trains")
    public HttpEntity create(@RequestBody
    TrainType trainType, @RequestHeader
    HttpHeaders headers) {
        TrainController.LOGGER.info("[create][Create train][TrainTypeId: {}]", trainType.getId());
        boolean isCreateSuccess = trainService.create(trainType, headers);
        if (isCreateSuccess) {
            return ok(new Response(1, "create success", null));
        } else {
            return ok(new Response(0, "train type already exist", trainType));
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/trains/{id}")
    public HttpEntity retrieve(@PathVariable
    String id, @RequestHeader
    HttpHeaders headers) {
        TrainController.LOGGER.info("[retrieve][Retrieve train][TrainTypeId: {}]", id);
        TrainType trainType = trainService.retrieve(id, headers);
        if (trainType == null) {
            return ok(new Response(0, "here is no TrainType with the trainType id: " + id, null));
        } else {
            return ok(new Response(1, "success", trainType));
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/trains/byName/{name}")
    public HttpEntity retrieveByName(@PathVariable
    String name, @RequestHeader
    HttpHeaders headers) {
        TrainController.LOGGER.info("[retrieveByName][Retrieve train][TrainTypeName: {}]", name);
        TrainType trainType = trainService.retrieveByName(name, headers);
        if (trainType == null) {
            return ok(new Response(0, "here is no TrainType with the trainType name: " + name, null));
        } else {
            return ok(new Response(1, "success", trainType));
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/trains/byNames")
    public HttpEntity retrieveByName(@RequestBody
    List<String> names, @RequestHeader
    HttpHeaders headers) {
        TrainController.LOGGER.info("[retrieveByNames][Retrieve train][TrainTypeNames: {}]", names);
        List<TrainType> trainTypes = trainService.retrieveByNames(names, headers);
        if (trainTypes == null) {
            return ok(new Response(0, "here is no TrainTypes with the trainType names: " + names, null));
        } else {
            return ok(new Response(1, "success", trainTypes));
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/trains")
    public HttpEntity update(@RequestBody
    TrainType trainType, @RequestHeader
    HttpHeaders headers) {
        TrainController.LOGGER.info("[update][Update train][TrainTypeId: {}]", trainType.getId());
        boolean isUpdateSuccess = trainService.update(trainType, headers);
        if (isUpdateSuccess) {
            return ok(new Response(1, "update success", isUpdateSuccess));
        } else {
            return ok(new Response(0, "there is no trainType with the trainType id", isUpdateSuccess));
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/trains/{id}")
    public HttpEntity delete(@PathVariable
    String id, @RequestHeader
    HttpHeaders headers) {
        TrainController.LOGGER.info("[delete][Delete train][TrainTypeId: {}]", id);
        boolean isDeleteSuccess = trainService.delete(id, headers);
        if (isDeleteSuccess) {
            return ok(new Response(1, "delete success", isDeleteSuccess));
        } else {
            return ok(new Response(0, "there is no train according to id", null));
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/trains")
    public HttpEntity query(@RequestHeader
    HttpHeaders headers) {
        TrainController.LOGGER.info("[query][Query train]");
        List<TrainType> trainTypes = trainService.query(headers);
        if ((trainTypes != null) && (!trainTypes.isEmpty())) {
            return ok(new Response(1, "success", trainTypes));
        } else {
            return ok(new Response(0, "no content", trainTypes));
        }
    }
}