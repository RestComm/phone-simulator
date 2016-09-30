/*
 * TeleStax, Open Source Cloud Communications  Copyright 2012.
 * and individual contributors
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

package org.mobicents.protocols.ss7.map.service.mobility.subscriberManagement;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCode;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCodeValue;
import org.mobicents.protocols.ss7.map.primitives.OctetStringLength1Base;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 *
 */
public class BearerServiceCodeImpl extends OctetStringLength1Base implements BearerServiceCode {

    private static final String BEARER_SERVICE_CODE_VALUE = "bearerServiceCodeValue";
    private static final String DATA = "data";

    public BearerServiceCodeImpl() {
        super("BearerServiceCode");
    }

    public BearerServiceCodeImpl(int data) {
        super("BearerServiceCode", data);
    }

    public BearerServiceCodeImpl(BearerServiceCodeValue value) {
        // super("BearerServiceCode", value != null ? value.getBearerServiceCode() : 0);
        super("BearerServiceCode", value != null ? value.getCode() : 0);
    }

    public int getData() {
        return data;
    }

    public BearerServiceCodeValue getBearerServiceCodeValue() {
        return BearerServiceCodeValue.getInstance(this.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");

        sb.append("Value=");
        sb.append(this.getBearerServiceCodeValue());

        sb.append(", Data=");
        sb.append(this.data);

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<BearerServiceCodeImpl> BEARER_SERVICE_CODE_XML = new XMLFormat<BearerServiceCodeImpl>(
            BearerServiceCodeImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, BearerServiceCodeImpl ssCode) throws XMLStreamException {
            ssCode.data = xml.get(DATA, Integer.class);

            String str = xml.get(BEARER_SERVICE_CODE_VALUE, String.class);
        }

        @Override
        public void write(BearerServiceCodeImpl ssCode, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.add(ssCode.getData(), DATA, Integer.class);

            if (ssCode.getBearerServiceCodeValue() != null)
                xml.add((String) ssCode.getBearerServiceCodeValue().toString(), BEARER_SERVICE_CODE_VALUE, String.class);
        }
    };
}
