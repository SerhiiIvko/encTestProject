package app.service;

import app.constants.ApplicationConstants;
import org.springframework.stereotype.Service;

@Service
public class PathVariableValidationServiceImpl implements PathVariableValidationService {

    @Override
    public boolean validate(Long id) {
        return id.equals(ApplicationConstants.ID_CONST);
    }
}
