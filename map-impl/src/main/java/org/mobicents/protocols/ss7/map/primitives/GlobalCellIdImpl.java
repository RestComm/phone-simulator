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

package org.mobicents.protocols.ss7.map.primitives;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentException;
import org.mobicents.protocols.ss7.map.api.primitives.GlobalCellId;

/**
*
* @author sergey vetyutnev
*
*/
public class GlobalCellIdImpl extends OctetStringBase implements GlobalCellId {

    private static final String MCC = "mcc";
    private static final String MNC = "mnc";
    private static final String LAC = "lac";
    private static final String CELL_ID = "cellId";

    private static final int DEFAULT_INT_VALUE = 0;

    public GlobalCellIdImpl() {
        super(5, 7, "GlobalCellId");
    }

    public GlobalCellIdImpl(byte[] data) {
        super(5, 7, "GlobalCellId", data);
    }

    public GlobalCellIdImpl(int mcc, int mnc, int lac, int cellId)
            throws MAPException {
        super(5, 7, "GlobalCellId");
        this.setData(mcc, mnc, lac, cellId);
    }

    public void setData(int mcc, int mnc, int lac, int cellId) throws MAPException {
        if (mcc < 1 || mcc > 999)
            throw new MAPException("Bad mcc value");
        if (mnc < 0 || mnc > 999)
            throw new MAPException("Bad mnc value");

        this.data = new byte[7];

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if (mcc < 100)
            sb.append("0");
        if (mcc < 10)
            sb.append("0");
        sb.append(mcc);

        if (mnc < 100) {
            if (mnc < 10)
                sb2.append("0");
            sb2.append(mnc);
        } else {
            sb.append(mnc % 10);
            sb2.append(mnc / 10);
        }

        AsnOutputStream asnOs = new AsnOutputStream();
        TbcdString.encodeString(asnOs, sb.toString());
        System.arraycopy(asnOs.toByteArray(), 0, this.data, 0, 2);

        asnOs = new AsnOutputStream();
        TbcdString.encodeString(asnOs, sb2.toString());
        System.arraycopy(asnOs.toByteArray(), 0, this.data, 2, 1);

        data[3] = (byte) (lac / 256);
        data[4] = (byte) (lac % 256);
        data[5] = (byte) (cellId / 256);
        data[6] = (byte) (cellId % 256);
    }

    public byte[] getData() {
        return data;
    }

    public int getMcc() throws MAPException {

        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length < 5 || data.length > 7)
            throw new MAPException("Data length must be between 5-7");

        AsnInputStream ansIS = new AsnInputStream(data);
        String res = null;
        try {
            res = TbcdString.decodeString(ansIS, 3);
        } catch (IOException e) {
            throw new MAPException("IOException when decoding GlobalCellId: " + e.getMessage(), e);
        } catch (MAPParsingComponentException e) {
            throw new MAPException("MAPParsingComponentException when decoding GlobalCellId: " + e.getMessage(), e);
        }

        if (res.length() < 5 || res.length() > 6)
            throw new MAPException("Decoded TbcdString must be equal 5 or 6");

        String sMcc = res.substring(0, 3);

        return Integer.parseInt(sMcc);
    }

    public int getMnc() throws MAPException {

        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length < 5 || data.length > 7)
            throw new MAPException("Data length must be between 5-7");

        AsnInputStream ansIS = new AsnInputStream(data);
        String res = null;
        try {
            res = TbcdString.decodeString(ansIS, 3);
        } catch (IOException e) {
            throw new MAPException("IOException when decoding GlobalCellId: " + e.getMessage(), e);
        } catch (MAPParsingComponentException e) {
            throw new MAPException("MAPParsingComponentException when decoding GlobalCellId: " + e.getMessage(), e);
        }

        if (res.length() < 5 || res.length() > 6)
            throw new MAPException("Decoded TbcdString must be equal 5 or 6");

        String sMnc;
        if (res.length() == 5) {
            sMnc = res.substring(3);
        } else {
            sMnc = res.substring(4) + res.substring(3, 4);
        }

        return Integer.parseInt(sMnc);
    }

    public int getLac() throws MAPException {

        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length < 5 || data.length > 7)
            throw new MAPException("Data length must be between 5-7");

        int res = (data[3] & 0xFF) * 256 + (data[4] & 0xFF);
        return res;
    }

    public int getCellId() throws MAPException {

        if (data == null)
            throw new MAPException("Data must not be empty");
        if (data.length < 5 || data.length > 7)
            throw new MAPException("Data length must be between 5-7");

        int res = 0;
        if (data.length == 7)
            res = (data[5] & 0xFF) * 256 + (data[6] & 0xFF);
        return res;
    }

    @Override
    public String toString() {

        int mcc = 0;
        int mnc = 0;
        int lac = 0;
        int cellId = 0;
        boolean goodData = false;

        try {
            mcc = this.getMcc();
            mnc = this.getMnc();
            lac = this.getLac();
            cellId = this.getCellId();
            goodData = true;
        } catch (MAPException e) {
        }

        StringBuilder sb = new StringBuilder();
        sb.append(this._PrimitiveName);
        sb.append(" [");
        if (goodData) {
            sb.append("MCC=");
            sb.append(mcc);
            sb.append(", MNC=");
            sb.append(mnc);
            sb.append(", Lac=");
            sb.append(lac);
            sb.append(", CellId=");
            sb.append(cellId);
        } else {
            sb.append("Data=");
            sb.append(this.printDataArr());
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<GlobalCellIdImpl> GLOBAL_CELL_ID_XML = new XMLFormat<GlobalCellIdImpl>(GlobalCellIdImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, GlobalCellIdImpl globalCellId) throws XMLStreamException {
            int mcc = xml.getAttribute(MCC, DEFAULT_INT_VALUE);
            int mnc = xml.getAttribute(MNC, DEFAULT_INT_VALUE);
            int lac = xml.getAttribute(LAC, DEFAULT_INT_VALUE);
            int cellId = xml.getAttribute(CELL_ID, DEFAULT_INT_VALUE);

            try {
                globalCellId.setData(mcc, mnc, lac, cellId);
            } catch (MAPException e) {
                throw new XMLStreamException("MAPException when deserializing GlobalCellId", e);
            }
        }

        @Override
        public void write(GlobalCellIdImpl globalCellId, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            try {
                xml.setAttribute(MCC, globalCellId.getMcc());
                xml.setAttribute(MNC, globalCellId.getMnc());
                xml.setAttribute(LAC, globalCellId.getLac());
                xml.setAttribute(CELL_ID, globalCellId.getCellId());
            } catch (MAPException e) {
                throw new XMLStreamException("MAPException when serializing GlobalCellId", e);
            }
        }
    };

}
