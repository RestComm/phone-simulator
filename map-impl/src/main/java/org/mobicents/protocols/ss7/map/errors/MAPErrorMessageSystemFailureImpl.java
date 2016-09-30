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
import org.mobicents.protocols.ss7.map.api.errors.AdditionalNetworkResource;
import org.mobicents.protocols.ss7.map.api.errors.MAPErrorCode;
import org.mobicents.protocols.ss7.map.api.errors.MAPErrorMessageSystemFailure;
import org.mobicents.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.mobicents.protocols.ss7.map.api.primitives.NetworkResource;
import org.mobicents.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageSystemFailureImpl extends MAPErrorMessageImpl implements MAPErrorMessageSystemFailure {

    private static final String MAP_PROTOCOL_VERSION = "mapProtocolVersion";
    private static final String NETWORK_RESOURCE = "networkResource";
    private static final String ADDITIONAL_NETWORK_RESOURCE = "additionalNetworkResource";
    private static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";
    public static final int additionalNetworkResource_TAG = 0x00;

    private long mapProtocolVersion;
    private NetworkResource networkResource;
    private AdditionalNetworkResource additionalNetworkResource;
    private MAPExtensionContainer extensionContainer;

    public MAPErrorMessageSystemFailureImpl(long mapProtocolVersion, NetworkResource networkResource,
            AdditionalNetworkResource additionalNetworkResource, MAPExtensionContainer extensionContainer) {
        super((long) MAPErrorCode.systemFailure);

        this.mapProtocolVersion = mapProtocolVersion;
        this.networkResource = networkResource;
        this.additionalNetworkResource = additionalNetworkResource;
        this.extensionContainer = extensionContainer;
    }

    public MAPErrorMessageSystemFailureImpl() {
        super((long) MAPErrorCode.systemFailure);
    }

    public boolean isEmSystemFailure() {
        return true;
    }

    public MAPErrorMessageSystemFailure getEmSystemFailure() {
        return this;
    }

    public NetworkResource getNetworkResource() {
        return this.networkResource;
    }

    public AdditionalNetworkResource getAdditionalNetworkResource() {
        return this.additionalNetworkResource;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public long getMapProtocolVersion() {
        return this.mapProtocolVersion;
    }

    public void setNetworkResource(NetworkResource networkResource) {
        this.networkResource = networkResource;
    }

    public void setAdditionalNetworkResource(AdditionalNetworkResource additionalNetworkResource) {
        this.additionalNetworkResource = additionalNetworkResource;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public void setMapProtocolVersion(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    public int getTag() throws MAPException {
        if (this.mapProtocolVersion < 3)
            return Tag.ENUMERATED;
        else
            return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        if (this.mapProtocolVersion < 3)
            return true;
        else
            return false;
    }

    public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {

        try {
            int length = ansIS.readLength();
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageSystemFailure: " + e.getMessage(),
                    e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException(
                    "AsnException when decoding MAPErrorMessageSystemFailure: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {

        try {
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageSystemFailure: " + e.getMessage(),
                    e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException(
                    "AsnException when decoding MAPErrorMessageSystemFailure: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream localAis, int length) throws MAPParsingComponentException, IOException, AsnException {

        this.networkResource = null;
        this.additionalNetworkResource = null;
        this.extensionContainer = null;

        if (localAis.getTagClass() != Tag.CLASS_UNIVERSAL)
            throw new MAPParsingComponentException("Error decoding MAPErrorMessageSystemFailure: bad tag class",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (localAis.getTag()) {
            case Tag.ENUMERATED:
                if (!localAis.isTagPrimitive())
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageAbsentSubscriberSM: ENUMERATED tag but data is not primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                int code = (int) localAis.readIntegerData(length);
                this.networkResource = NetworkResource.getInstance(code);
                if (this.networkResource == null)
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageAbsentSubscriberSM.networkResource: bad code value",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                this.mapProtocolVersion = 2;
                break;

            case Tag.SEQUENCE:
                if (localAis.isTagPrimitive())
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageAbsentSubscriberSM: SEQUENCE tag but data is primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                AsnInputStream ais = localAis.readSequenceStreamData(length);

                while (true) {
                    if (ais.available() == 0)
                        break;

                    int tag = ais.readTag();

                    switch (ais.getTagClass()) {
                        case Tag.CLASS_UNIVERSAL:
                            switch (tag) {
                                case Tag.ENUMERATED:
                                    code = (int) ais.readInteger();
                                    this.networkResource = NetworkResource.getInstance(code);
                                    if (this.networkResource == null)
                                        throw new MAPParsingComponentException(
                                                "Error decoding MAPErrorMessageAbsentSubscriberSM.networkResource: bad code value",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
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
                                case additionalNetworkResource_TAG:
                                    code = (int) ais.readInteger();
                                    this.additionalNetworkResource = AdditionalNetworkResource.getInstance(code);
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

                this.mapProtocolVersion = 3;
                break;

            default:
                throw new MAPParsingComponentException("Error decoding MAPErrorMessageSystemFailure: bad tag",
                        MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeAll(AsnOutputStream asnOs) throws MAPException {

        if (this.mapProtocolVersion < 3)
            this.encodeAll(asnOs, Tag.CLASS_UNIVERSAL, Tag.ENUMERATED);
        else
            this.encodeAll(asnOs, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
    }

    public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws MAPException {

        try {
            if (this.mapProtocolVersion < 3)
                asnOs.writeTag(tagClass, true, tag);
            else
                asnOs.writeTag(tagClass, false, tag);
            int pos = asnOs.StartContentDefiniteLength();
            this.encodeData(asnOs);
            asnOs.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageSystemFailure: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream aos) throws MAPException {

        if (this.networkResource == null && this.additionalNetworkResource == null && this.extensionContainer == null)
            return;
        if (this.networkResource == null && this.mapProtocolVersion < 3)
            return;

        try {
            if (this.mapProtocolVersion < 3) {
                aos.writeIntegerData(this.networkResource.getCode());
            } else {
                if (this.networkResource != null)
                    aos.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.networkResource.getCode());
                if (this.extensionContainer != null)
                    ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(aos);
                if (this.additionalNetworkResource != null)
                    aos.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, additionalNetworkResource_TAG,
                            this.additionalNetworkResource.getCode());
            }

        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPErrorMessageSystemFailure: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageSystemFailure: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MAPErrorMessageSystemFailure [");
        if (this.networkResource != null)
            sb.append("networkResource=" + this.networkResource.toString());
        if (this.extensionContainer != null)
            sb.append(", extensionContainer=" + this.extensionContainer.toString());
        if (this.additionalNetworkResource != null)
            sb.append(", additionalNetworkResource=" + this.additionalNetworkResource.toString());
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageSystemFailureImpl> MAP_ERROR_MESSAGE_SYSTEM_FAILURE_XML = new XMLFormat<MAPErrorMessageSystemFailureImpl>(
            MAPErrorMessageSystemFailureImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageSystemFailureImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
            errorMessage.mapProtocolVersion = xml.get(MAP_PROTOCOL_VERSION, Long.class);

            String str = xml.get(NETWORK_RESOURCE, String.class);
            if (str != null)
                errorMessage.networkResource = Enum.valueOf(NetworkResource.class, str);

            str = xml.get(ADDITIONAL_NETWORK_RESOURCE, String.class);
            if (str != null)
                errorMessage.additionalNetworkResource = Enum.valueOf(AdditionalNetworkResource.class, str);

            errorMessage.extensionContainer = xml.get(MAP_EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MAPErrorMessageSystemFailureImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
            xml.add(errorMessage.getMapProtocolVersion(), MAP_PROTOCOL_VERSION, Long.class);

            if (errorMessage.getNetworkResource() != null)
                xml.add((String) errorMessage.getNetworkResource().toString(), NETWORK_RESOURCE, String.class);

            if (errorMessage.getAdditionalNetworkResource() != null)
                xml.add((String) errorMessage.getAdditionalNetworkResource().toString(), ADDITIONAL_NETWORK_RESOURCE,
                        String.class);

            xml.add((MAPExtensionContainerImpl) errorMessage.extensionContainer, MAP_EXTENSION_CONTAINER,
                    MAPExtensionContainerImpl.class);
        }
    };
}
