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

package org.mobicents.protocols.ss7.tools.simulator.tests.sms;

import java.util.Hashtable;

import org.mobicents.protocols.ss7.tools.simulator.common.EnumeratedBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SRIInformServiceCenter extends EnumeratedBase {

    private static final long serialVersionUID = -1697735373396991529L;

    public static final int MWD_NO = 1;
    public static final int MWD_mcef = 2;
    public static final int MWD_mnrf = 3;
    public static final int MWD_mcef_mnrf = 4;
    public static final int MWD_mnrg = 5;

    private static Hashtable<String, Integer> stringMap = new Hashtable<String, Integer>();
    private static Hashtable<Integer, String> intMap = new Hashtable<Integer, String>();

    static {
        intMap.put(MWD_NO, "No data in MWD file");
        intMap.put(MWD_mcef, "Memory Capacity Exceeded Flag");
        intMap.put(MWD_mnrf, "MS Not Reachable Flag");
        intMap.put(MWD_mcef_mnrf, "MS Not Reachable + Memory Capacity Exceeded Flags");
        intMap.put(MWD_mnrg, "Not Reachable for GPRS");

        stringMap.put("No data in MWD file", MWD_NO);
        stringMap.put("Memory Capacity Exceeded Flag", MWD_mcef);
        stringMap.put("MS Not Reachable Flag", MWD_mnrf);
        stringMap.put("MS Not Reachable + Memory Capacity Exceeded Flags", MWD_mcef_mnrf);
        stringMap.put("Not Reachable for GPRS", MWD_mnrg);
    }

    public SRIInformServiceCenter() {
    }

    public SRIInformServiceCenter(int val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public SRIInformServiceCenter(Integer val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public SRIInformServiceCenter(String val) throws java.lang.IllegalArgumentException {
        super(val);
    }

    public static SRIInformServiceCenter createInstance(String s) {
        Integer i1 = doCreateInstance(s, stringMap, intMap);
        if (i1 == null)
            return new SRIInformServiceCenter(MWD_NO);
        else
            return new SRIInformServiceCenter(i1);
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
