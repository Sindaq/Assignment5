package org.example.Assignment;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilterInvoiceTest {

    @Test
    void filterInvoiceTest() {
        Database db = new Database();
        QueryInvoicesDAO dao = new QueryInvoicesDAO(db);
        FilterInvoice filterInvoice = new FilterInvoice(dao);
        Invoice i1 = new Invoice("Gabe", 99);
        Invoice i2 = new Invoice("Emelyn", 100);

        List<Invoice> listInvoice = new ArrayList<>();
        listInvoice.add(i1);

        dao.save(i1);
        dao.save(i2);

        assertEquals(listInvoice, filterInvoice.lowValueInvoices());
    }

    @Test
    void filterInvoiceStubbedTest() {

//        Database db = mock(Database.class);
        QueryInvoicesDAO dao = mock(QueryInvoicesDAO.class);

        Invoice i1 = new Invoice("Gabe", 99);
        Invoice i2 = new Invoice("Emelyn", 100);

        List<Invoice> listInvoice = new ArrayList<>();
        List<Invoice> expected = new ArrayList<>();
        listInvoice.add(i1);
        listInvoice.add(i2);
        expected.add(i1);

        dao.save(i1);
        dao.save(i2);

        when(dao.all()).thenReturn(listInvoice);
        FilterInvoice filterInvoice = new FilterInvoice(dao);

        List<Invoice> mockList = filterInvoice.lowValueInvoices();

        assertEquals(expected, mockList);
    }

}