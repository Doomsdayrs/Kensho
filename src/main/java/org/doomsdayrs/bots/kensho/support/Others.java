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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Others {
	public static final Date date = new Date();
	public static final String path = Config.getExecDir();
	public static final Random gen = new Random();
	public static final DateFormat dateFormat = new SimpleDateFormat(" yyyy MM dd HH mmm ss");
	public static final DateFormat dateFormatForFile = new SimpleDateFormat(" yyyy-MM-dd-HH-mm");
	public static int commandsExecuted;
	private static int unknown = 1;

	public static String parseID(String id) {
		return id.replace("<@", "").replace(">", "").replace("!", "");
	}

	public static int newInt() {
		unknown++;
		return unknown;
	}
}
