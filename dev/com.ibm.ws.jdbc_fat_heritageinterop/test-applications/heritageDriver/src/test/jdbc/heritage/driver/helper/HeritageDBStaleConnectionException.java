/*******************************************************************************
 * Copyright (c) 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package test.jdbc.heritage.driver.helper;

import java.sql.SQLException;

import com.ibm.websphere.ce.cm.StaleConnectionException;

/**
 * A custom exception class for testing.
 */
public class HeritageDBStaleConnectionException extends StaleConnectionException {
    private static final long serialVersionUID = 1L;

    public HeritageDBStaleConnectionException() {
        super();
    }

    public HeritageDBStaleConnectionException(SQLException x) {
        super(x);
    }

    public HeritageDBStaleConnectionException(String message) {
        super(message);
    }

    public HeritageDBStaleConnectionException(String message, String sqlState, int errCode) {
        super(message, sqlState, errCode);
    }
}