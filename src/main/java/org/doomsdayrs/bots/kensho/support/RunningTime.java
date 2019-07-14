package org.doomsdayrs.bots.kensho.support;
/*
 * This file is part of Kensho.
 * Kensho is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * Kensho is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with Kensho.  If not, see <https://www.gnu.org/licenses/>.
 * ====================================================================
 * Kensho
 * 03 / 07 / 2019
 *
 * @author github.com/doomsdayrs
 */

import java.util.concurrent.TimeUnit;

public class RunningTime {

    public static final long startTime = System.currentTimeMillis();

    private static long uptime() {
        return System.currentTimeMillis() - startTime;
    }

    private static String millisToShortDHMS(long duration) {
        String res;
        long days = TimeUnit.MILLISECONDS.toDays(duration);
        long hours = TimeUnit.MILLISECONDS.toHours(duration) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
        long millis = TimeUnit.MILLISECONDS.toMillis(duration) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(duration));

        if (days == 0) res = String.format("%02d:%02d:%02d.%04d", hours, minutes, seconds, millis);
        else res = String.format("%dd %02d:%02d:%02d.%04d", days, hours, minutes, seconds, millis);
        return res;
    }

    public static String getRuntime() {
        return millisToShortDHMS(uptime());
    }

}
