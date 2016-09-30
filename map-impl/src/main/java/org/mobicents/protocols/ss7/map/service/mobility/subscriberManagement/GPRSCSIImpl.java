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

import java.io.IOException;
import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.GPRSCSI;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.GPRSCamelTDPData;
import org.mobicents.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.mobicents.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class GPRSCSIImpl extends SequenceBase implements GPRSCSI {

    public static final int _TAG_gprsCamelTDPDataList = 0;
    public static final int _TAG_camelCapabilityHandling = 1;
    public static final int _TAG_extensionContainer = 2;
    public static final int _TAG_notificationToCSE = 3;
    public static final int _TAG_csiActive = 4;

    private ArrayList<GPRSCamelTDPData> gprsCamelTDPDataList;
    private Integer camelCapabilityHandling;
    private MAPExtensionContainer extensionContainer;
    private boolean notificationToCSE;
    private boolean csiActive;

    public GPRSCSIImpl() {
        super("GPRSCSI");
    }

    public GPRSCSIImpl(ArrayList<GPRSCamelTDPData> gprsCamelTDPDataList, Integer camelCapabilityHandling,
            MAPExtensionContainer extensionContainer, boolean notificationToCSE, boolean csiActive) {
        super("GPRSCSI");
        this.gprsCamelTDPDataList = gprsCamelTDPDataList;
        this.camelCapabilityHandling = camelCapabilityHandling;
        this.extensionContainer = extensionContainer;
        this.notificationToCSE = notificationToCSE;
        this.csiActive = csiActive;
    }

    @Override
    public ArrayList<GPRSCamelTDPData> getGPRSCamelTDPDataList() {
        return this.gprsCamelTDPDataList;
    }

    @Override
    public Integer getCamelCapabilityHandling() {
        return this.camelCapabilityHandling;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    public boolean getNotificationToCSE() {
        return this.notificationToCSE;
    }

    @Override
    public boolean getCsiActive() {
        return this.csiActive;
    }

    @Override
    protected void _decode(AsnInputStream asnIS, int length) throws MAPParsingComponentException, IOException, AsnException {

        this.gprsCamelTDPDataList = null;
        this.extensionContainer = null;
        this.camelCapabilityHandling = null;
        this.notificationToCSE = false;
        this.csiActive = false;

        AsnInputStream ais = asnIS.readSequenceStreamData(length);

        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            switch (ais.getTagClass()) {
                case Tag.CLASS_CONTEXT_SPECIFIC: {
                    switch (tag) {
                        case _TAG_gprsCamelTDPDataList:
                            if (ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".gprsCamelTDPDataList: Parameter is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.gprsCamelTDPDataList = new ArrayList<GPRSCamelTDPData>();

                            AsnInputStream ais2 = ais.readSequenceStream();

                            while (true) {
                                if (ais2.available() == 0)
                                    break;

                                int tag2 = ais2.readTag();

                                if (tag2 != Tag.SEQUENCE || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || ais2.isTagPrimitive())
                                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                            + ": bad tag or tagClass or is primitive when decoding gprsCamelTDPDataList",
                                            MAPParsingComponentExceptionReason.MistypedParameter);

                                GPRSCamelTDPDataImpl elem = new GPRSCamelTDPDataImpl();
                                ((GPRSCamelTDPDataImpl) elem).decodeAll(ais2);
                                this.gprsCamelTDPDataList.add(elem);

                                if (this.gprsCamelTDPDataList.size() < 1 || this.gprsCamelTDPDataList.size() > 10)
                                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                            + " : gprsCamelTDPDataList elements count must be from 1 to 10, found: "
                                            + this.gprsCamelTDPDataList.size(),
                                            MAPParsingComponentExceptionReason.MistypedParameter);
                            }
                            break;
                        case _TAG_camelCapabilityHandling:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".camelCapabilityHandling: Parameter is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.camelCapabilityHandling = (int) ais.readInteger();
                            break;
                        case _TAG_extensionContainer: // forwardingData
                            if (ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".forwardingData: Parameter is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.extensionContainer = new MAPExtensionContainerImpl();
                            ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                            break;
                        case _TAG_notificationToCSE:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".notificationToCSE: Parameter is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.notificationToCSE = true;
                            ais.readNull();
                            break;
                        case _TAG_csiActive:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".csiActive: Parameter is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.csiActive = true;
                            ais.readNull();
                            break;
                        default:
                            ais.advanceElement();
                            break;
                    }
                }
                    break;
                default:
                    ais.advanceElement();
                    break;
            }
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOs) throws MAPException {
        try {

            if (this.gprsCamelTDPDataList != null
                    && (this.gprsCamelTDPDataList.size() < 1 || this.gprsCamelTDPDataList.size() > 10))
                throw new MAPException("Error while encoding" + _PrimitiveName
                        + ": gprsCamelTDPDataList size must be from 1 to 10, found: " + this.gprsCamelTDPDataList.size());

            if (this.gprsCamelTDPDataList != null) {
                asnOs.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_gprsCamelTDPDataList);
                int pos = asnOs.StartContentDefiniteLength();
                for (GPRSCamelTDPData be : this.gprsCamelTDPDataList) {
                    GPRSCamelTDPDataImpl bee = (GPRSCamelTDPDataImpl) be;
                    bee.encodeAll(asnOs);
                }
                asnOs.FinalizeContent(pos);
            }

            if (this.camelCapabilityHandling != null)
                asnOs.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_camelCapabilityHandling, this.camelCapabilityHandling);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC,
                        _TAG_extensionContainer);

            if (this.notificationToCSE)
                asnOs.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_notificationToCSE);

            if (this.csiActive)
                asnOs.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_csiActive);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.gprsCamelTDPDataList != null) {
            sb.append("gprsCamelTDPDataList=[");
            boolean firstItem = true;
            for (GPRSCamelTDPData be : this.gprsCamelTDPDataList) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(be.toString());
            }
            sb.append("], ");
        }

        if (this.camelCapabilityHandling != null) {
            sb.append("camelCapabilityHandling=");
            sb.append(this.camelCapabilityHandling.toString());
            sb.append(", ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer.toString());
            sb.append(", ");
        }

        if (this.notificationToCSE) {
            sb.append("notificationToCSE, ");
        }

        if (this.csiActive) {
            sb.append("csiActive ");
        }

        sb.append("]");

        return sb.toString();
    }
}
