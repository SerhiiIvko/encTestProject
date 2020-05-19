package app.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathVariableValidationServiceImplTest {
    private PathVariableValidationService service;
    private Long id;

    @Test
    public void validateIfIdIsOne() {
        //GIVEN:
        service = new PathVariableValidationServiceImpl();
        id = 1L;

        //WHEN:
        boolean response = service.validate(id);

        //THEN:
        assertTrue(response);
    }

    @Test
    public void validateIfIdIsNotOne() {
        //GIVEN:
        service = new PathVariableValidationServiceImpl();
        id = 2L;

        //WHEN:
        boolean response = service.validate(id);

        //THEN:
        assertFalse(response);
    }

    @Test(expected = NullPointerException.class)
    public void validateIfIdIsNull() {
        //GIVEN:
        service = new PathVariableValidationServiceImpl();
        id = null;

        //WHEN:
        service.validate(id);
    }
}