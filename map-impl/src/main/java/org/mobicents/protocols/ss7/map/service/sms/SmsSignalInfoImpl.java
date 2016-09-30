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

package org.mobicents.protocols.ss7.map.service.sms;

import java.io.IOException;
import java.nio.charset.Charset;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.mobicents.protocols.ss7.map.api.service.sms.SmsSignalInfo;
import org.mobicents.protocols.ss7.map.api.smstpdu.SmsTpdu;
import org.mobicents.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.mobicents.protocols.ss7.map.smstpdu.SmsTpduImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SmsSignalInfoImpl implements SmsSignalInfo, MAPAsnPrimitive {

    public static final String _PrimitiveName = "SmsSignalInfo";

    private byte[] data;
    private Charset gsm8Charset;

    public SmsSignalInfoImpl() {
    }

    public SmsSignalInfoImpl(byte[] data, Charset gsm8Charset) {
        this.data = data;
        this.setGsm8Charset(gsm8Charset);
    }

    public SmsSignalInfoImpl(SmsTpdu tpdu, Charset gsm8Charset) throws MAPException {
        if (tpdu == null)
            throw new MAPException("SmsTpdu must not be null");

        this.setGsm8Charset(gsm8Charset);
        this.data = tpdu.encodeData();
    }

    public byte[] getData() {
        return this.data;
    }

    public Charset getGsm8Charset() {
        return gsm8Charset;
    }

    public void setGsm8Charset(Charset gsm8Charset) {
        this.gsm8Charset = gsm8Charset;
    }

    public SmsTpdu decodeTpdu(boolean mobileOriginatedMessage) throws MAPException {
        return SmsTpduImpl.createInstance(this.data, mobileOriginatedMessage, this.getGsm8Charset());
    }

    public int getTag() throws MAPException {
        return Tag.STRING_OCTET;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return true;
    }

    public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {

        try {
            int length = ansIS.readLength();
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {

        try {
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream ansIS, int length) throws IOException, AsnException {

        this.data = ansIS.readOctetStringData(length);
    }

    public void encodeAll(AsnOutputStream asnOs) throws MAPException {
        this.encodeAll(asnOs, Tag.CLASS_UNIVERSAL, this.getTag());
    }

    public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws MAPException {

        try {
            asnOs.writeTag(tagClass, true, tag);
            int pos = asnOs.StartContentDefiniteLength();
            this.encodeData(asnOs);
            asnOs.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOs) throws MAPException {

        if (this.data == null || this.data.length == 0)
            throw new MAPException("Error when encoding " + _PrimitiveName + ": data is empty");

        asnOs.writeOctetStringData(data);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("SmsSignalInfo [");

        boolean moExists = false;
        try {
            SmsTpdu tpdu = SmsTpduImpl.createInstance(this.data, true, getGsm8Charset());
            sb.append("MO case: ");
            sb.append(tpdu.toString());
            moExists = true;
        } catch (MAPException e) {
        }
        try {
            if (moExists)
                sb.append("\n");
            SmsTpdu tpdu = SmsTpduImpl.createInstance(this.data, false, getGsm8Charset());
            sb.append("MT case: ");
            sb.append(tpdu.toString());
        } catch (MAPException e) {
        }

        sb.append("]");

        return sb.toString();
    }
}
