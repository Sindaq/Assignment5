package org.example.Assignment;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterInvoiceTest {

    @Test
    void filterInvoiceTest() {
        Database db = new Database();
        QueryInvoicesDAO dao = new QueryInvoicesDAO(db);
        FilterInvoice filterInvoice = new FilterInvoice();
        Invoice i1 = new Invoice("Gabe", 99);
        Invoice i2 = new Invoice("Emelyn", 100);

        List<Invoice> listInvoice = new ArrayList<>();
        listInvoice.add(i1);

        dao.save(i1);
        dao.save(i2);

        assertEquals(listInvoice, filterInvoice.lowValueInvoices());
    }

}