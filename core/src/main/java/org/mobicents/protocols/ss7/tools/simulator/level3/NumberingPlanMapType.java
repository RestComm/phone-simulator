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

package org.mobicents.protocols.ss7.tools.simulator.level3;

import java.util.Hashtable;

import org.mobicents.protocols.ss7.map.api.primitives.NumberingPlan;
import org.mobicents.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class NumberingPlanMapType extends EnumeratedBase {

    private static final long serialVersionUID = 1L;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(NumberingPlan.unknown.getIndicator(), NumberingPlan.unknown.toString());
        intMap.put(NumberingPlan.ISDN.getIndicator(), NumberingPlan.ISDN.toString());
        intMap.put(NumberingPlan.spare_2.getIndicator(), NumberingPlan.spare_2.toString());
        intMap.put(NumberingPlan.data.getIndicator(), NumberingPlan.data.toString());
        intMap.put(NumberingPlan.telex.getIndicator(), NumberingPlan.telex.toString());
        intMap.put(NumberingPlan.spare_5.getIndicator(), NumberingPlan.spare_5.toString());
        intMap.put(NumberingPlan.land_mobile.getIndicator(), NumberingPlan.land_mobile.toString());
        intMap.put(NumberingPlan.spare_7.getIndicator(), NumberingPlan.spare_7.toString());
        intMap.put(NumberingPlan.national.getIndicator(), NumberingPlan.national.toString());
        intMap.put(NumberingPlan.private_plan.getIndicator(), NumberingPlan.private_plan.toString());
        intMap.put(NumberingPlan.reserved.getIndicator(), NumberingPlan.reserved.toString());

        stringMap.put(NumberingPlan.unknown.toString(), NumberingPlan.unknown.getIndicator());
        stringMap.put(NumberingPlan.ISDN.toString(), NumberingPlan.ISDN.getIndicator());
        stringMap.put(NumberingPlan.spare_2.toString(), NumberingPlan.spare_2.getIndicator());
        stringMap.put(NumberingPlan.data.toString(), NumberingPlan.data.getIndicator());
        stringMap.put(NumberingPlan.telex.toString(), NumberingPlan.telex.getIndicator());
        stringMap.put(NumberingPlan.spare_5.toString(), NumberingPlan.spare_5.getIndicator());
        stringMap.put(NumberingPlan.land_mobile.toString(), NumberingPlan.land_mobile.getIndicator());
        stringMap.put(NumberingPlan.spare_7.toString(), NumberingPlan.spare_7.getIndicator());
        stringMap.put(NumberingPlan.national.toString(), NumberingPlan.national.getIndicator());
        stringMap.put(NumberingPlan.private_plan.toString(), NumberingPlan.private_plan.getIndicator());
        stringMap.put(NumberingPlan.reserved.toString(), NumberingPlan.reserved.getIndicator());
    }

    public NumberingPlanMapType() {
    }

    public NumberingPlanMapType(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public NumberingPlanMapType(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public NumberingPlanMapType(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static NumberingPlanMapType createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new NumberingPlanMapType(NumberingPlan.unknown.getIndicator());
        else
            return new NumberingPlanMapType(i1);
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
