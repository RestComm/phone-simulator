/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2016, Telestax Inc and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */


package org.mobicents.protocols.ss7.tools.simulator.tests.lcs;

import org.mobicents.protocols.ss7.tools.simulator.common.AddressNatureType;
import org.mobicents.protocols.ss7.tools.simulator.level3.NumberingPlanMapType;

/**
 * @author falonso@csc.com
 *
 */
public interface TestMapLcsClientManMBean {

    // Operations

    String sendRoutingInfoForLCSRequest(String addressIMSI);

    String subscriberLocationReportRequest();

    String performSendRoutingInfoForLCSRequest(String addressIMSI);

    String performSubscriberLocationReportRequest();

    // Attributes

    AddressNatureType getAddressNature();

    void setAddressNature(AddressNatureType val);

    String getNumberingPlan();

    void setNumberingPlan(String numPlan);

    NumberingPlanMapType getNumberingPlanType();

    void setNumberingPlanType(NumberingPlanMapType val);

    String getNetworkNodeNumberAddress();

    void setNetworkNodeNumberAddress(String val);

    // Other

    String getCurrentRequestDef();
}
