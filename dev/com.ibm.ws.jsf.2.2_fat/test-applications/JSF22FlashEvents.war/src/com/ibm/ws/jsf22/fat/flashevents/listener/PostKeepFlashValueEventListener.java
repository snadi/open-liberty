/*
 * Copyright (c) 2015, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 */
package com.ibm.ws.jsf22.fat.flashevents.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/*
 * This is a custom SystemEventListener which is configured in the faces-config to
 * be called during a javax.faces.event.PostKeepFlashValueEvent event.
 * It contains a counter which will be checked after each test to ensure it was called the expected amount of times
 */

public class PostKeepFlashValueEventListener implements SystemEventListener {

    private int counter;

    public PostKeepFlashValueEventListener() {
        counter = 0;
    }

    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException {
        counter++;
        FacesContext.getCurrentInstance().getExternalContext().log("PostKeepFlashValueEvent processEvent - counter: " + counter);
    }

    @Override
    public boolean isListenerForSource(Object value) {
        return true;
    }
}
