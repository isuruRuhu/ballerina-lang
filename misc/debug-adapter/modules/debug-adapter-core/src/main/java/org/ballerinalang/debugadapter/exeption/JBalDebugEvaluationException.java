package org.ballerinalang.debugadapter.exeption;

/**
 * Error type implementation for debug expression evaluation related exceptions.
 */
public class JBalDebugEvaluationException extends Exception {

    public JBalDebugEvaluationException(String message, Throwable cause) {
        super(message, cause);
    }
}
