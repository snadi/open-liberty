/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
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

package com.ibm.websphere.sib.api.jms;

import javax.jms.QueueConnectionFactory;

/**
 * Contains provider specific methods relating to the javax.jms.QueueConnectionFactory
 * interface. 
 * 
 * @ibm-api
 * @ibm-was-base 
 *
 */
public interface JmsQueueConnectionFactory
  extends JmsConnectionFactory, QueueConnectionFactory
{
}
