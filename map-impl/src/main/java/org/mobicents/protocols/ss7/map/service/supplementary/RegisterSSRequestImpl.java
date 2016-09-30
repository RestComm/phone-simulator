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

package org.mobicents.protocols.ss7.map.service.supplementary;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPMessageType;
import org.mobicents.protocols.ss7.map.api.MAPOperationCode;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.mobicents.protocols.ss7.map.api.primitives.AddressString;
import org.mobicents.protocols.ss7.map.api.primitives.EMLPPPriority;
import org.mobicents.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.mobicents.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.mobicents.protocols.ss7.map.api.service.supplementary.RegisterSSRequest;
import org.mobicents.protocols.ss7.map.api.service.supplementary.SSCode;
import org.mobicents.protocols.ss7.map.primitives.AddressStringImpl;
import org.mobicents.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.mobicents.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class RegisterSSRequestImpl extends SupplementaryMessageImpl implements RegisterSSRequest {
    protected static final int _TAG_forwardedToNumber = 4;
    protected static final int _TAG_forwardedToSubaddress = 6;
    protected static final int _TAG_noReplyConditionTime = 5;
    protected static final int _TAG_defaultPriority = 7;
    protected static final int _TAG_nbrUser = 8;
    protected static final int _TAG_longFTN_Supported = 9;

    public static final String _PrimitiveName = "RegisterSSRequest";

    private SSCode ssCode;
    private BasicServiceCode basicService;
    private AddressString forwardedToNumber;
    private ISDNAddressString forwardedToSubaddress;
    private Integer noReplyConditionTime;
    private EMLPPPriority defaultPriority;
    private Integer nbrUser;
    private ISDNAddressString longFTNSupported;

    public RegisterSSRequestImpl() {
    }

    public RegisterSSRequestImpl(SSCode ssCode, BasicServiceCode basicService, AddressString forwardedToNumber, ISDNAddressString forwardedToSubaddress,
            Integer noReplyConditionTime, EMLPPPriority defaultPriority, Integer nbrUser, ISDNAddressString longFTNSupported) {
        this.ssCode = ssCode;
        this.basicService = basicService;
        this.forwardedToNumber = forwardedToNumber;
        this.forwardedToSubaddress = forwardedToSubaddress;
        this.noReplyConditionTime = noReplyConditionTime;
        this.defaultPriority = defaultPriority;
        this.nbrUser = nbrUser;
        this.longFTNSupported = longFTNSupported;
    }

    public MAPMessageType getMessageType() {
        return MAPMessageType.registerSS_Request;
    }

    public int getOperationCode() {
        return MAPOperationCode.registerSS;
    }

    @Override
    public SSCode getSsCode() {
        return ssCode;
    }

    @Override
    public BasicServiceCode getBasicService() {
        return basicService;
    }

    @Override
    public AddressString getForwardedToNumber() {
        return forwardedToNumber;
    }

    @Override
    public ISDNAddressString getForwardedToSubaddress() {
        return forwardedToSubaddress;
    }

    @Override
    public Integer getNoReplyConditionTime() {
        return noReplyConditionTime;
    }

    @Override
    public EMLPPPriority getDefaultPriority() {
        return defaultPriority;
    }

    @Override
    public Integer getNbrUser() {
        return nbrUser;
    }

    @Override
    public ISDNAddressString getLongFTNSupported() {
        return longFTNSupported;
    }


    @Override
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        return false;
    }

    @Override
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

    @Override
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

    private void _decode(AsnInputStream ansIS, int length) throws MAPParsingComponentException, IOException, AsnException {

        this.ssCode = null;
        this.basicService = null;
        this.forwardedToNumber = null;
        this.forwardedToSubaddress = null;
        this.noReplyConditionTime = null;
        this.defaultPriority = null;
        this.nbrUser = null;
        this.longFTNSupported = null;

        AsnInputStream ais = ansIS.readSequenceStreamData(length);
        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
            case 0:
                // ssCode
                if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.STRING_OCTET)
                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                            + ".ssCode: Parameter 0 bad tag or tag class or not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                this.ssCode = new SSCodeImpl();
                ((SSCodeImpl) this.ssCode).decodeAll(ais);
                break;

            default:
                if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                    switch (tag) {

                    case BasicServiceCodeImpl._TAG_bearerService:
                    case BasicServiceCodeImpl._TAG_teleservice:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".basicService: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.basicService = new BasicServiceCodeImpl();
                        ((BasicServiceCodeImpl) this.basicService).decodeAll(ais);
                        break;
                    case _TAG_forwardedToNumber:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".forwardedToNumber: Parameter extensionContainer is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        this.forwardedToNumber = new AddressStringImpl();
                        ((AddressStringImpl) this.forwardedToNumber).decodeAll(ais);
                        break;
                    case _TAG_forwardedToSubaddress:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".forwardedToSubaddress: Parameter extensionContainer is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        this.forwardedToSubaddress = new ISDNAddressStringImpl();
                        ((ISDNAddressStringImpl) this.forwardedToSubaddress).decodeAll(ais);
                        break;

                    case _TAG_noReplyConditionTime:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".noReplyConditionTime: Parameter is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        this.noReplyConditionTime = (int) ais.readInteger();
                        break;
                    case _TAG_defaultPriority:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".defaultPriority: Parameter is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        int i1 = (int) ais.readInteger();
                        this.defaultPriority = EMLPPPriority.getEMLPPPriority(i1);
                        break;
                    case _TAG_nbrUser:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".nbrUser: Parameter is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        this.nbrUser = (int) ais.readInteger();
                        break;
                    case _TAG_longFTN_Supported:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".longFTNSupported: Parameter extensionContainer is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                        this.longFTNSupported = new ISDNAddressStringImpl();
                        ((ISDNAddressStringImpl) this.longFTNSupported).decodeAll(ais);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                    }
                } else {
                    ais.advanceElement();
                }
                break;
            }

            num++;
        }

        if (num < 1)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": Needs at least 1 mandatory parameter, found " + num,
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOs) throws MAPException {
        this.encodeAll(asnOs, this.getTagClass(), this.getTag());
    }

    @Override
    public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws MAPException {
        try {
            asnOs.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOs.StartContentDefiniteLength();
            this.encodeData(asnOs);
            asnOs.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOs) throws MAPException {
        try {

            if (this.ssCode == null)
                throw new MAPException("ssCode parameter must not be null");

            ((SSCodeImpl) this.ssCode).encodeAll(asnOs);

            if (this.basicService != null)
                ((BasicServiceCodeImpl) this.basicService).encodeAll(asnOs);

            if (this.forwardedToNumber != null)
                ((AddressStringImpl) this.forwardedToNumber).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_forwardedToNumber);
            if (this.forwardedToSubaddress != null)
                ((ISDNAddressStringImpl) this.forwardedToSubaddress).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_forwardedToSubaddress);

            if (noReplyConditionTime != null)
                asnOs.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_noReplyConditionTime, noReplyConditionTime);
            if (defaultPriority != null)
                asnOs.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_defaultPriority, defaultPriority.getCode());
            if (nbrUser != null)
                asnOs.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_nbrUser, nbrUser);
            if (this.longFTNSupported != null)
                ((ISDNAddressStringImpl) this.longFTNSupported).encodeAll(asnOs, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_longFTN_Supported);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.ssCode != null) {
            sb.append("ssCode=");
            sb.append(ssCode);
            sb.append(", ");
        }
        if (this.basicService != null) {
            sb.append("basicService=");
            sb.append(basicService);
            sb.append(", ");
        }
        if (this.forwardedToNumber != null) {
            sb.append("forwardedToNumber=");
            sb.append(forwardedToNumber);
            sb.append(", ");
        }
        if (this.forwardedToSubaddress != null) {
            sb.append("forwardedToSubaddress=");
            sb.append(forwardedToSubaddress);
            sb.append(", ");
        }
        if (this.noReplyConditionTime != null) {
            sb.append("noReplyConditionTime=");
            sb.append(noReplyConditionTime);
            sb.append(", ");
        }
        if (this.defaultPriority != null) {
            sb.append("defaultPriority=");
            sb.append(defaultPriority);
            sb.append(", ");
        }
        if (this.nbrUser != null) {
            sb.append("nbrUser=");
            sb.append(nbrUser);
            sb.append(", ");
        }
        if (this.longFTNSupported != null) {
            sb.append("longFTNSupported=");
            sb.append(longFTNSupported);
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

}
