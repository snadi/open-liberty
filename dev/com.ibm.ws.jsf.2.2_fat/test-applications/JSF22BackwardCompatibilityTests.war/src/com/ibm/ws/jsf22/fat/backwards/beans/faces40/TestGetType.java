/*
 * Copyright (c) 2015, 2023 IBM Corporation and others.
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
package com.ibm.ws.jsf22.fat.backwards.beans.faces40;

import java.util.Map;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * This bean tests the method getType from ELResolver class by invoking it
 * for each composite component attribute to be tested.
 */
@Named
@RequestScoped
public class TestGetType {
    private String test;

    /**
     * Get the expression type (class) from the composite component attributes map
     *
     * @param attrs a Map containing all the composite component attributes
     * @return a String that contains the attribute and the type
     */
    public String from(Map<String, Object> attrs) {
        FacesContext context = FacesContext.getCurrentInstance();
        ELContext elContext = context.getELContext();
        Class<?> type = context.getApplication().getELResolver().getType(elContext, attrs, test);
        return String.format("Attribute: %s, Type: %s", test, type == null ? null : type.getSimpleName());
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
