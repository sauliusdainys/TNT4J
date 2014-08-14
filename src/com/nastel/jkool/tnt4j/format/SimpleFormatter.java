/*
 * Copyright 2014 Nastel Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nastel.jkool.tnt4j.format;

import java.util.List;
import java.util.TimeZone;

import com.nastel.jkool.tnt4j.core.Property;
import com.nastel.jkool.tnt4j.core.PropertySnapshot;
import com.nastel.jkool.tnt4j.core.UsecTimestamp;
import com.nastel.jkool.tnt4j.tracker.TrackingActivity;
import com.nastel.jkool.tnt4j.tracker.TrackingEvent;

/**
 * <p>
 * Simple implementation of <code>Formatter</code> interface provides simple/minimal formatting of
 * <code>TrackingActvity</code> and <code>TrackingEvent</code> as well as any object passed to <code>format()</code>
 * method call. Event entries are formatted as follows:
 * <code>event-text-msg {event-tracking-info}</code>
 * where <code>event-tracking-info</code> consists of <code>"name: value"</code> pairs.
 * </p>
 * 
 * 
 * @version $Revision: 1 $
 * 
 * @see DefaultFormatter
 * @see TrackingActivity
 * @see TrackingEvent
 */

public class SimpleFormatter extends DefaultFormatter {
		
	/**
	 * Create a simple event formatter with default setting.
	 * Format: "{2} | {1} | {0}", TimeZone: UTC.
	 *
	 */
	public SimpleFormatter() {
	}
	
	/**
	 * Create a simple event formatter instance with a given format
	 *
	 * @param format string (e.g. "{2} | {1} | {0}")
	 */
	public SimpleFormatter(String format) {
		super(format);
	}
	
	/**
	 * Create a simple event formatter instance with a given format, timezone
	 *
	 * @param format string (e.g. "{2} | {1} | {0}")
	 * @param tz time zone
	 */
	public SimpleFormatter(String format, TimeZone tz) {
		super(format, tz);
	}
	
	@Override
	public String format(TrackingEvent event) {
		StringBuffer msg = new StringBuffer(1024);
		msg.append(event.getMessage()).append(" ");
		msg.append("{time: '").append(UsecTimestamp.getTimeStamp(timeZone)).append("'").append(separator);
		msg.append("sev: '").append(event.getSeverity()).append("'").append(separator);
		msg.append("type: '").append(event.getOperation().getType()).append("'").append(separator);
		msg.append("name: '").append(event.getOperation().getResolvedName()).append("'").append(separator);
		msg.append("ccode: '").append(event.getOperation().getCompCode()).append("'").append(separator);
		if (event.getOperation().getReasonCode() != 0) {
			msg.append("rcode: '").append(event.getOperation().getReasonCode()).append("'").append(separator);
		}
		if (event.getOperation().getElapsedTime() != 0) {
			msg.append("usec: '").append(event.getOperation().getElapsedTime()).append("'").append(separator);
		}
		if (event.getMessageAge() != 0) {
			msg.append("age.usec: '").append(event.getMessageAge()).append("'").append(separator);
		}
		if (event.getOperation().getWaitTime() != 0) {
			msg.append("wait.usec: '").append(event.getOperation().getWaitTime()).append("'").append(separator);
		}
		if (event.getTag() != null) {
			msg.append("tag: '").append(event.getTag()).append("'").append(separator);
		}
		if (event.getOperation().getCorrelator() != null) {
			msg.append("corr-id: '").append(event.getOperation().getCorrelator()).append("'").append(separator);
		}
		if (event.getOperation().getLocation() != null) {
			msg.append("location: '").append(event.getOperation().getLocation()).append("'").append(separator);
		}
		if (event.getOperation().getThrowable() != null) {
			msg.append("error: '").append(event.getOperation().getExceptionString()).append("'").append(separator);
		}
		if (event.getParentId() != null) {
			msg.append("parent-id: '").append(event.getParentId()).append("'").append(separator);
		}
		msg.append("track-id: '").append(event.getTrackingId()).append("'");
		msg.append("}");
		return msg.toString();
	}

	@Override
	public String format(TrackingActivity activity) {
		StringBuffer msg = new StringBuffer(1024);
		msg.append("{status: '").append(activity.getStatus()).append("'").append(separator);
		msg.append("time: '").append(UsecTimestamp.getTimeStamp(timeZone)).append("'").append(separator);
		msg.append("sev: '").append(activity.getSeverity()).append("'").append(separator);
		msg.append("type: '").append(activity.getType()).append("'").append(separator);
		msg.append("name: '").append(activity.getResolvedName()).append("'").append(separator);
		if (activity.getElapsedTime() != 0) {
			msg.append("usec: '").append(activity.getElapsedTime()).append("'").append(separator);
		}
		if (activity.getWaitTime() != 0) {
			msg.append("wait.usec: '").append(activity.getWaitTime()).append("'").append(separator);
		}
		if (activity.getStartTime() != null) {
			msg.append("start.time: '").append(activity.getStartTime()).append("'").append(separator);
		}
		if (activity.getEndTime() != null) {
			msg.append("end.time: '").append(activity.getEndTime()).append("'").append(separator);
		}
		if (activity.getLocation() != null) {
			msg.append("location: '").append(activity.getLocation()).append("'").append(separator);
		}
		if (activity.getThrowable() != null) {
			msg.append("error: '").append(activity.getExceptionString()).append("'").append(separator);
		}
		msg.append("pid: '").append(activity.getPID()).append("'").append(separator);
		msg.append("tid: '").append(activity.getTID()).append("'").append(separator);
		msg.append("id-count: '").append(activity.getIdCount()).append("'").append(separator);
		msg.append("snap-count: '").append(activity.getSnapshotCount()).append("'").append(separator);
		msg.append("source: '").append(activity.getSource().getFQName()).append("'").append(separator);
		if (activity.getParentId() != null) {
			msg.append("parent-id: '").append(activity.getParentId()).append("'").append(separator);
		}
		msg.append("track-id: '").append(activity.getTrackingId()).append("'");
		if (activity.getSnapshotCount() > 0) {
			List<PropertySnapshot> snapshots = activity.getSnapshots();
			for (PropertySnapshot snap : snapshots) {
				msg.append("\n\tSnapshot(").append(snap.getName()).append("@").append(snap.getCategory()).append(") {");
				for (Property prop : snap) {
					msg.append("\n\t\t").append(prop.getKey()).append(": ").append(prop.getValue());
				}
				msg.append("\n\t}");
			}
		}
		msg.append("}");
		return msg.toString();
	}		
}
