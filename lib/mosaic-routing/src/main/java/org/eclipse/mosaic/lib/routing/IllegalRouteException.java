/*
 * Copyright (c) 2020 Fraunhofer FOKUS and others. All rights reserved.
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.mosaic.lib.routing;

public class IllegalRouteException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public IllegalRouteException(String message) {
        super(message);
    }
    
    public IllegalRouteException(String message, Throwable cause) {
        super(message, cause);
    }

}
