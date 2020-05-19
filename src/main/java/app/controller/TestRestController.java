package app.controller;

import app.constants.ApplicationConstants;
import app.encryptor.EncryptionService;
import app.service.PathVariableValidationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
public class TestRestController {
    private final Logger LOGGER = Logger.getLogger(TestRestController.class);
    private final EncryptionService encryptionService;
    private final PathVariableValidationService pathVariableValidationService;

    @Value("${response.string.key}")
    private String fio;

    @Autowired
    public TestRestController(EncryptionService encryptionService, PathVariableValidationService pathVariableValidationService) {
        this.encryptionService = encryptionService;
        this.pathVariableValidationService = pathVariableValidationService;
    }

    @PostMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> postEncryptionMessage(@PathVariable("id") Long id) {
        String response = createResponse(id);
        logResponse(response);
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put(fio, response);
        return ResponseEntity.status(200).body(resp);
    }

    private String createResponse(Long id) {
        return pathVariableValidationService.validate(id) ? ApplicationConstants.FIO : null;
    }

    private void logResponse(String response){
        LOGGER.info(response != null ? ApplicationConstants.ENCRYPTION_MSG + encryptionService.encrypt(response) : ApplicationConstants.ENCRYPTION_MSG + response);
        LOGGER.info(response != null ? ApplicationConstants.DECRYPTION_MSG + encryptionService.decrypt(response) : ApplicationConstants.DECRYPTION_MSG + response);
    }
}
