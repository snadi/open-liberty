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

import java.io.Serializable;
import java.util.ArrayList;

import javax.el.ELException;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import com.ibm.ws.jsf22.fat.backwards.utilities.faces40.MethodExpressionHelper;

/**
 * Test exception thrown by processValueChange from MethodExpressionValueChangeListener
 * and processAction from MethodExpressionActionListener.
 */
@Named
@SessionScoped
public class MethodExpressionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String word;
    private String name;
    private final ArrayList<String> result = new ArrayList<String>();

    public MethodExpressionBean() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result.toString();
    }

    /**
     * Tests whether the exception thrown by processValueChange method from MethodExpressionValueChangeListener
     * is an AbortProcessingException or not.
     *
     * In this case it should not throw an AbortProcessingException since now
     * it will not throw an AbortProcessingException for any reason if it fails.
     *
     * @param event a ValueChangeEvent
     * @throws AbortProcessingException
     */
    public void throwExceptionFromProcessValueChange(ValueChangeEvent event) throws AbortProcessingException {
        MethodExpressionHelper meh = new MethodExpressionHelper();
        try {
            meh.testProcessValueChange(event, "testMEB", "#{testMEB.setWor}");
        } catch (Exception e) {
            if ((e instanceof ELException) && !(e instanceof AbortProcessingException)) {
                result.add("Testing ExceptionFromProcessValueChange: Exception thrown is correct since it is an instance of ELException. Exception: " + e.toString());
            } else {
                result.add("Testing ExceptionFromProcessValueChange: Wrong Exception! Exception: " + e.toString());
            }
        }
    }

    /**
     * Tests whether the exception thrown in processAction method from MethodExpressionActionListener
     * is an AbortProcessingException or not.
     *
     * In this case it should not throw an AbortProcessingException since now
     * it will not throw an AbortProcessingException for any reason if it fails.
     *
     * @param event
     * @throws AbortProcessingException
     */
    public void throwExceptionFromProcessAction(ActionEvent event) throws AbortProcessingException {
        MethodExpressionHelper meh = new MethodExpressionHelper();
        try {
            meh.testProcessAction(event, "testMEB", "#{testMEB.setWor}");
        } catch (Exception e) {
            if ((e instanceof ELException) && !(e instanceof AbortProcessingException)) {
                result.add("Testing ExceptionFromProcessAction: Exception thrown is correct since it is an instance of ELException. Exception: " + e.toString());
            } else {
                result.add("Testing ExceptionFromProcessAction: Wrong Exception! Exception: " + e.toString());
            }
        }
    }

    /**
     * Tests whether the exception thrown in this method is interpreted as an AbortProcessingException.
     *
     * In this case, it should be interpreted as a NullPointerException because AbortProcessingException
     * is reserved with a special meaning. That is, an AbortProcessingException, [tells] the JSF implementation
     * that no further broadcast of this event should take place.
     *
     * @param event a ValueChangeEvent
     * @throws AbortProcessingException
     */
    public void throwNullException(ValueChangeEvent event) throws AbortProcessingException {
        MethodExpressionHelper meh = new MethodExpressionHelper();
        try {
            meh.testNullPointerException();
        } catch (Exception e) {
            if ((e instanceof NullPointerException) && !(e instanceof AbortProcessingException)) {
                result.add("Testing ELException: NullPointerException. Exception thrown is correct since it is an instance of NullPointerException. Exception: " + e.toString());
            } else {
                result.add("Testing ELException: NullPointerException. Wrong Exception! Exception: " + e.toString());
            }
        }
    }

    /**
     * @return a String containing the JSF result page
     */
    public String showResult() {
        return ("testProcessValueChangeAndProcessActionResult?faces-redirect=true");
    }
}
