/*
 * Copyright (c) 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.ibm.ws.jsf22.fat.PI46218Flow2.flows;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;

public class FlowFactory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Produces
    @FlowDefinition
    public Flow defineTestFlow(@FlowBuilderParameter FlowBuilder flowBuilder) {
        final String flowId = "test-flow-2";
        flowBuilder.id("", flowId);

        flowBuilder.viewNode("flow1", "/" + flowId + "/flow1.xhtml").markAsStartNode();
        flowBuilder.viewNode("flow2", "/" + flowId + "/flow2.xhtml");
        flowBuilder.returnNode("return-node").fromOutcome("home");

        return flowBuilder.getFlow();
    }
}
