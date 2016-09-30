/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.map.datacoding;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class GSMCharsetDecodingData {

    protected int totalSeptetCount;
    protected int leadingSeptetSkipCount;
    protected Gsm7EncodingStyle encodingStyle;

    /**
     * constructor
     *
     * @param totalSeptetCount Length of a decoded message in characters (for SMS case)
     * @param leadingSeptetSkipCount Count of leading septets to skip
     */
    public GSMCharsetDecodingData(Gsm7EncodingStyle encodingStyle, int totalSeptetCount, int leadingSeptetSkipCount) {
        this.totalSeptetCount = totalSeptetCount;
        this.leadingSeptetSkipCount = leadingSeptetSkipCount;
        this.encodingStyle = encodingStyle;
    }
}
