package org.example.Assignment;

import org.example.exceptions.FailToSendSAPInvoiceException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SAP_BasedInvoiceSenderTest {

    @Test
    void testWhenLowInvoicesSent() {

        SAP sap = mock(SAP.class);
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP_BasedInvoiceSender sapSender = new SAP_BasedInvoiceSender(filter, sap);

        List<Invoice> listInvoice = new ArrayList<>();
        listInvoice.add(new Invoice("Gabe", 99));
//        listInvoice.add(new Invoice("Emelyn", 100));

        when(filter.lowValueInvoices()).thenReturn(listInvoice);

        sapSender.sendLowValuedInvoices();
        verify(sap, times(1)).send(any(Invoice.class));

    }

    @Test
    void testWhenNoInvoices() {

        SAP sap = mock(SAP.class);
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP_BasedInvoiceSender sapSender = new SAP_BasedInvoiceSender(filter, sap);

        List<Invoice> listInvoice = new ArrayList<>();
//        listInvoice.add(new Invoice("Gabe", 99));
//        listInvoice.add(new Invoice("Emelyn", 100));

        when(filter.lowValueInvoices()).thenReturn(listInvoice);

        sapSender.sendLowValuedInvoices();
        verify(sap, times(0)).send(any(Invoice.class));

    }

    @Test
    void testThrowExceptionWhenBadInvoice() {

        SAP sap = mock(SAP.class);
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP_BasedInvoiceSender sapSender = new SAP_BasedInvoiceSender(filter, sap);
        Invoice badInvoice = new Invoice("Emelyn", 100);

        List<Invoice> listInvoice = new ArrayList<>();
        List<Invoice> badInvoices = new ArrayList<>();
        listInvoice.add(new Invoice("Gabe", 99));
        listInvoice.add(badInvoice);
        badInvoices.add(badInvoice);

        when(filter.lowValueInvoices()).thenReturn(listInvoice);

        doThrow(new FailToSendSAPInvoiceException("Bad invoice")).when(sap).send(badInvoice);
        List<Invoice> failedValueInvoices = sapSender.sendLowValuedInvoices();

        assertEquals(badInvoices, failedValueInvoices);


    }

}