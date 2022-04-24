package com.barcellos.function;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

import org.junit.Test;

public class SupplierTest {

    @Test
    public void test() {
        Supplier<LocalDate> supplierLocalDate = LocalDate::now;

        assertEquals(LocalDate.now(), supplierLocalDate.get());
    }

    @Test
    public void testDateFormated() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Supplier<String> supplier = () -> dateTimeFormatter.format(LocalDateTime.now());

        assertEquals(dateTimeFormatter.format(LocalDateTime.now()), supplier.get());
    }
}
