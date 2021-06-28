package com.test.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    @Test
    void testGetEmployee() {
        BindingResult resultValid = mock(BindingResult.class);
        when(resultValid.hasErrors()).thenReturn(false);
    }
}
