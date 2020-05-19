package app.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationServiceImplTest {
    private ValidationService service;
    private Long id;

    @Test
    public void validateIfIdIsOne() {
        service = new ValidationServiceImpl();
        id = 1L;
        boolean response = service.validate(id);
        assertTrue(response);
    }

    @Test
    public void validateIfIdIsNotOne() {
        service = new ValidationServiceImpl();
        id = 2L;
        boolean response = service.validate(id);
        assertFalse(response);
    }

    @Test(expected = NullPointerException.class)
    public void validateIfIdIsNull() {
        service = new ValidationServiceImpl();
        id = null;
        service.validate(id);
    }
}