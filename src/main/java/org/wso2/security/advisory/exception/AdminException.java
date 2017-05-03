package org.wso2.security.advisory.exception;

/**
 * Used to wrap the actual exception with a pdf creation.
 *
 */
public class AdminException extends Exception {

    public AdminException(String msg) {
        super(msg);
    }

    public AdminException(String msg, Throwable e) {
        super(msg, e);
    }
}
