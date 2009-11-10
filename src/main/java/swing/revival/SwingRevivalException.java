/**
 * swing-revival:
 * Swing Revival Toolkit
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Nov 10, 2009
 */
package swing.revival;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public class SwingRevivalException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -4964998414445635431L;

    /**
     * Constructs a new SwingRevivalException with the specified causse, detail message format and message arguments.
     * 
     * @param cause
     *        the cause (which is saved for later retrieval by the {@link #getCause()} method). (A null value is
     *        permitted, and indicates that the cause is nonexistent or unknown.)
     * @param message
     *        the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param args
     *        optional arguments to the message format
     */
    public SwingRevivalException(final Throwable cause, final String message, final Object... args) {
        super(String.format(message, args), cause);
    }

    /**
     * Constructs a new SwingRevivalException with the specified detail and cause.
     * 
     * @param message
     *        the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param args
     *        optional arguments to the message format
     */
    public SwingRevivalException(final String message, final Object... args) {
        super(String.format(message, args));
    }

    /**
     * Constructs a new SwingRevivalException with the specified detail cause.
     * 
     * @param cause
     *        the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public SwingRevivalException(final Throwable cause) {
        super(cause.getMessage(), cause);
    }

}
