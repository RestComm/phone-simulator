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

package org.mobicents.protocols.ss7.map.errors;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.mobicents.protocols.ss7.map.api.errors.AbsentSubscriberDiagnosticSM;
import org.mobicents.protocols.ss7.map.api.errors.MAPErrorCode;
import org.mobicents.protocols.ss7.map.api.errors.MAPErrorMessageAbsentSubscriberSM;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageAbsentSubscriberSMImpl extends MAPErrorMessageImpl implements MAPErrorMessageAbsentSubscriberSM {

    private static final String ABSENT_SUBSCRIBER_DIAGNOSTIC_SM = "absentSubscriberDiagnosticSM";
    private static final String ADDITIONAL_ABSENT_SUBSCRIBER_DIAGNOSTIC_SM = "additionalAbsentSubscriberDiagnosticSM";
    private static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";

    public static final int additionalAbsentSubscriberDiagnosticSM_TAG = 0x00;

    private AbsentSubscriberDiagnosticSM absentSubscriberDiagnosticSM;
    private MAPExtensionContainer extensionContainer;
    private AbsentSubscriberDiagnosticSM additionalAbsentSubscriberDiagnosticSM;

    public MAPErrorMessageAbsentSubscriberSMImpl(AbsentSubscriberDiagnosticSM absentSubscriberDiagnosticSM,
            MAPExtensionContainer extensionContainer, AbsentSubscriberDiagnosticSM additionalAbsentSubscriberDiagnosticSM) {
        super((long) MAPErrorCode.absentSubscriberSM);

        this.absentSubscriberDiagnosticSM = absentSubscriberDiagnosticSM;
        this.extensionContainer = extensionContainer;
        this.additionalAbsentSubscriberDiagnosticSM = additionalAbsentSubscriberDiagnosticSM;
    }

    public MAPErrorMessageAbsentSubscriberSMImpl() {
        super((long) MAPErrorCode.absentSubscriberSM);
    }

    public AbsentSubscriberDiagnosticSM getAbsentSubscriberDiagnosticSM() {
        return this.absentSubscriberDiagnosticSM;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public AbsentSubscriberDiagnosticSM getAdditionalAbsentSubscriberDiagnosticSM() {
        return this.additionalAbsentSubscriberDiagnosticSM;
    }

    public void setAbsentSubscriberDiagnosticSM(AbsentSubscriberDiagnosticSM absentSubscriberDiagnosticSM) {
        this.absentSubscriberDiagnosticSM = absentSubscriberDiagnosticSM;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public void setAdditionalAbsentSubscriberDiagnosticSM(AbsentSubscriberDiagnosticSM additionalAbsentSubscriberDiagnosticSM) {
        this.additionalAbsentSubscriberDiagnosticSM = additionalAbsentSubscriberDiagnosticSM;
    }

    public boolean isEmAbsentSubscriberSM() {
        return true;
    }

    public MAPErrorMessageAbsentSubscriberSM getEmAbsentSubscriberSM() {
        return this;
    }

    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return false;
    }

    public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {

        try {
            int length = ansIS.readLength();
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageAbsentSubscriberSM: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageAbsentSubscriberSM: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {

        try {
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageAbsentSubscriberSM: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageAbsentSubscriberSM: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream localAis, int length) throws MAPParsingComponentException, IOException, AsnException {

        this.absentSubscriberDiagnosticSM = null;
        this.additionalAbsentSubscriberDiagnosticSM = null;
        this.extensionContainer = null;

        if (localAis.getTagClass() != Tag.CLASS_UNIVERSAL || localAis.getTag() != Tag.SEQUENCE || localAis.isTagPrimitive())
            throw new MAPParsingComponentException(
                    "Error decoding MAPErrorMessageAbsentSubscriberSM: bad tag class or tag or parameter is primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        AsnInputStream ais = localAis.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (ais.getTagClass()) {
                case Tag.CLASS_UNIVERSAL:
                    switch (tag) {
                        case Tag.INTEGER:
                            this.absentSubscriberDiagnosticSM = AbsentSubscriberDiagnosticSM.getInstance((int) ais
                                    .readInteger());
                            break;

                        case Tag.SEQUENCE:
                            this.extensionContainer = new MAPExtensionContainerImpl();
                            ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                            break;

                        default:
                            ais.advanceElement();
                            break;
                    }
                    break;

                case Tag.CLASS_CONTEXT_SPECIFIC:
                    switch (tag) {
                        case additionalAbsentSubscriberDiagnosticSM_TAG:
                            this.additionalAbsentSubscriberDiagnosticSM = AbsentSubscriberDiagnosticSM.getInstance((int) ais
                                    .readInteger());
                            break;

                        default:
                            ais.advanceElement();
                            break;
                    }
                    break;

                default:
                    ais.advanceElement();
                    break;
            }
        }
    }

    public void encodeAll(AsnOutputStream asnOs) throws MAPException {

        this.encodeAll(asnOs, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
    }

    public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws MAPException {

        try {
            asnOs.writeTag(tagClass, false, tag);
            int pos = asnOs.StartContentDefiniteLength();
            this.encodeData(asnOs);
            asnOs.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageAbsentSubscriberSM: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream aos) throws MAPException {

        if (this.absentSubscriberDiagnosticSM == null && this.additionalAbsentSubscriberDiagnosticSM == null
                && this.extensionContainer == null)
            return;

        try {
            if (this.absentSubscriberDiagnosticSM != null)
                aos.writeInteger(this.absentSubscriberDiagnosticSM.getCode());
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(aos);
            if (this.additionalAbsentSubscriberDiagnosticSM != null)
                aos.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, additionalAbsentSubscriberDiagnosticSM_TAG,
                        this.additionalAbsentSubscriberDiagnosticSM.getCode());

        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPErrorMessageAbsentSubscriberSM: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageAbsentSubscriberSM: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MAPErrorMessageAbsentSubscriberSM [");
        if (this.absentSubscriberDiagnosticSM != null)
            sb.append("absentSubscriberDiagnosticSM=" + this.absentSubscriberDiagnosticSM.toString());
        if (this.extensionContainer != null)
            sb.append(", extensionContainer=" + this.extensionContainer.toString());
        if (this.additionalAbsentSubscriberDiagnosticSM != null)
            sb.append(", additionalAbsentSubscriberDiagnosticSM=" + this.additionalAbsentSubscriberDiagnosticSM.toString());
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageAbsentSubscriberSMImpl> MAP_ERROR_MESSAGE_ABSENT_SUBSCRIBER_XML = new XMLFormat<MAPErrorMessageAbsentSubscriberSMImpl>(
            MAPErrorMessageAbsentSubscriberSMImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageAbsentSubscriberSMImpl ussdMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, ussdMessage);

            String str = xml.get(ABSENT_SUBSCRIBER_DIAGNOSTIC_SM, String.class);
            if (str != null)
                ussdMessage.absentSubscriberDiagnosticSM = Enum.valueOf(AbsentSubscriberDiagnosticSM.class, str);

            str = xml.get(ADDITIONAL_ABSENT_SUBSCRIBER_DIAGNOSTIC_SM, String.class);
            if (str != null)
                ussdMessage.additionalAbsentSubscriberDiagnosticSM = Enum.valueOf(AbsentSubscriberDiagnosticSM.class, str);

            ussdMessage.extensionContainer = xml.get(MAP_EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MAPErrorMessageAbsentSubscriberSMImpl ussdMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(ussdMessage, xml);

            if (ussdMessage.getAbsentSubscriberDiagnosticSM() != null)
                xml.add((String) ussdMessage.getAbsentSubscriberDiagnosticSM().toString(), ABSENT_SUBSCRIBER_DIAGNOSTIC_SM,
                        String.class);

            if (ussdMessage.getAdditionalAbsentSubscriberDiagnosticSM() != null)
                xml.add((String) ussdMessage.getAdditionalAbsentSubscriberDiagnosticSM().toString(),
                        ADDITIONAL_ABSENT_SUBSCRIBER_DIAGNOSTIC_SM, String.class);

            xml.add((MAPExtensionContainerImpl) ussdMessage.extensionContainer, MAP_EXTENSION_CONTAINER,
                    MAPExtensionContainerImpl.class);
        }
    };
}
