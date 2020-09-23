/*
 * Copyright (c) 2020 Fraunhofer FOKUS and others. All rights reserved.
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.mosaic.fed.sumo.traci.commands;

import static org.junit.Assert.assertEquals;

import org.eclipse.mosaic.fed.sumo.traci.complex.SumoLaneChangeMode;
import org.eclipse.mosaic.fed.sumo.traci.complex.VehicleSubscriptionResult;
import org.eclipse.mosaic.fed.sumo.traci.junit.SumoRunner;
import org.eclipse.mosaic.lib.enums.LaneChangeMode;
import org.eclipse.mosaic.rti.TIME;

import com.google.common.collect.Iterables;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SumoRunner.class)
public class VehicleSetLaneTest extends AbstractTraciCommandTest {



    @Test
    public void execute() throws Exception {
        // SETUP
        new VehicleSubscribe(traci.getTraciConnection()).execute(traci.getTraciConnection(), "1", 0L, 100 * TIME.SECOND);

        // PRE-ASSERT
        VehicleSubscriptionResult vehInfo =
                (VehicleSubscriptionResult) Iterables.getOnlyElement(simulateStep.execute(traci.getTraciConnection(), 6 * TIME.SECOND));
        assertEquals("1_1_2_1_0", vehInfo.laneId);
        assertEquals(5d, vehInfo.position.getX(), 5d);
        assertEquals(11d, vehInfo.position.getY(), 5d);

        // RUN
        new VehicleSetLane().execute(traci.getTraciConnection(), "1", 1, 4000);

        // ASSERT
        vehInfo = (VehicleSubscriptionResult) Iterables.getOnlyElement(simulateStep.execute(traci.getTraciConnection(), 9 * TIME.SECOND));
        assertEquals("1_1_2_1_1", vehInfo.laneId);
    }

    @Test
    public void executeWithLaneChangeModeOff() throws Exception {
        // SETUP
        simulateStep.execute(traci.getTraciConnection(), 4 * TIME.SECOND);

        // RUN
        new VehicleSetLaneChangeMode()
                .execute(traci.getTraciConnection(), "1", SumoLaneChangeMode.translateFromEnum(LaneChangeMode.OFF).getAsInteger());

        // ASSERT
        execute();
    }

}