/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
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
package io.openliberty.microprofile.metrics.internal.cdi30.helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.metrics.Tag;

/**
 *
 */
public class Utils {

    public static Tag[] tagsToTags(String[] tags) {
        List<Tag> tagsList = new ArrayList<Tag>();

        //convert to Tag object -
        if (tags != null) {
            for (String tag : tags) {
                tagsList.add(new Tag(tag.substring(0, tag.indexOf("=")), tag.substring(tag.indexOf("=") + 1)));
            }
        }
        return tagsList.toArray(new Tag[0]);
    }
}
