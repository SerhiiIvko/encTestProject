package app.controller;

import app.constants.ApplicationConstants;
import app.encryptor.EncryptionService;
import app.service.ValidationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/")
public class TestRestController {
    private final Logger logger= Logger.getLogger(TestRestController.class);
    private final EncryptionService encryptionService;
    private final ValidationService validationService;

    @Autowired
    public TestRestController(EncryptionService encryptionService, ValidationService validationService) {
        this.encryptionService = encryptionService;
        this.validationService = validationService;
    }


    @PostMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> postEncryptionMessage(@PathVariable("id") Long id, String fio) {
        String response = createResponse(id);
        fio = "fio";
        logResponse(response);
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put(fio, response);
        return ResponseEntity.status(200).body(resp);
    }

    private String createResponse(Long id) {
        if (validationService.validate(id)) return ApplicationConstants.FIO;
        return null;
    }

    private void logResponse(String response){
        logger.info(response != null ? ApplicationConstants.ENCRYPTION_MSG + encryptionService.encrypt(response) : ApplicationConstants.ENCRYPTION_MSG + response);
        logger.info(response != null ? ApplicationConstants.DECRYPTION_MSG + encryptionService.decrypt(response) : ApplicationConstants.DECRYPTION_MSG + response);
    }
}
