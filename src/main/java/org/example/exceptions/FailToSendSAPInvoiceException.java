package org.example.exceptions;

public class FailToSendSAPInvoiceException extends RuntimeException {
    public FailToSendSAPInvoiceException(String message) {
        super(message);
    }
}
