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

package org.mobicents.protocols.ss7.tools.simulator.common;

import java.util.Hashtable;

import org.mobicents.protocols.ss7.map.api.primitives.AddressNature;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AddressNatureType extends EnumeratedBase {

    private static final long serialVersionUID = -7761620813108174227L;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(AddressNature.unknown.getIndicator(), AddressNature.unknown.toString());
        intMap.put(AddressNature.international_number.getIndicator(), AddressNature.international_number.toString());
        intMap.put(AddressNature.national_significant_number.getIndicator(),
                AddressNature.national_significant_number.toString());
        intMap.put(AddressNature.network_specific_number.getIndicator(), AddressNature.network_specific_number.toString());
        intMap.put(AddressNature.subscriber_number.getIndicator(), AddressNature.subscriber_number.toString());
        intMap.put(AddressNature.reserved.getIndicator(), AddressNature.reserved.toString());
        intMap.put(AddressNature.abbreviated_number.getIndicator(), AddressNature.abbreviated_number.toString());
        intMap.put(AddressNature.reserved_for_extension.getIndicator(), AddressNature.reserved_for_extension.toString());

        stringMap.put(AddressNature.unknown.toString(), AddressNature.unknown.getIndicator());
        stringMap.put(AddressNature.international_number.toString(), AddressNature.international_number.getIndicator());
        stringMap.put(AddressNature.national_significant_number.toString(),
                AddressNature.national_significant_number.getIndicator());
        stringMap.put(AddressNature.network_specific_number.toString(), AddressNature.network_specific_number.getIndicator());
        stringMap.put(AddressNature.subscriber_number.toString(), AddressNature.subscriber_number.getIndicator());
        stringMap.put(AddressNature.reserved.toString(), AddressNature.reserved.getIndicator());
        stringMap.put(AddressNature.abbreviated_number.toString(), AddressNature.abbreviated_number.getIndicator());
        stringMap.put(AddressNature.reserved_for_extension.toString(), AddressNature.reserved_for_extension.getIndicator());
    }

    public AddressNatureType() {
    }

    public AddressNatureType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public AddressNatureType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public AddressNatureType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static AddressNatureType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new AddressNatureType(AddressNature.unknown.getIndicator());
        else
            return new AddressNatureType(i1);
    }

    @Override
    protected Hashtable<Integer, String> getIntTable() {
        return intMap;
    }

    @Override
    protected Hashtable<String, Integer> getStringTable() {
        return stringMap;
    }
}
