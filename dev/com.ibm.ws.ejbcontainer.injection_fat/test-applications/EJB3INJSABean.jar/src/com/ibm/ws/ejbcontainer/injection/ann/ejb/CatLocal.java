/*******************************************************************************
 * Copyright (c) 2006, 2018 IBM Corporation and others.
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

package com.ibm.ws.ejbcontainer.injection.ann.ejb;

/**
 * Local interface for CatBean
 **/
public interface CatLocal extends AnimalLocal {
    /**
     * Returns "I am a cat."
     */
    @Override
    public String whatAmI();

    /**
     * Returns "Give me milk and tuna."
     */
    @Override
    public String careInst();

    /**
     * Returns "Just a ball of string."
     */
    @Override
    public String favToy();

    /**
     * Returns "Cat: any of several carnivores of the family Felidae."
     */
    public String catDef();

    /**
     * Clean up the bean if it is a SFSB
     */
    public void finish();
}
