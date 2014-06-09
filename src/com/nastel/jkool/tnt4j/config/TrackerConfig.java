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
package com.nastel.jkool.tnt4j.config;

import java.util.Properties;

import com.nastel.jkool.tnt4j.core.ActivityListener;
import com.nastel.jkool.tnt4j.core.Source;
import com.nastel.jkool.tnt4j.core.SourceType;
import com.nastel.jkool.tnt4j.dump.DefaultDumpSinkFactory;
import com.nastel.jkool.tnt4j.dump.DumpSinkFactory;
import com.nastel.jkool.tnt4j.format.DefaultFormatter;
import com.nastel.jkool.tnt4j.format.EventFormatter;
import com.nastel.jkool.tnt4j.format.Formatter;
import com.nastel.jkool.tnt4j.logger.Log4JEventSinkFactory;
import com.nastel.jkool.tnt4j.repository.FileTokenRepository;
import com.nastel.jkool.tnt4j.repository.TokenRepository;
import com.nastel.jkool.tnt4j.selector.DefaultTrackingSelector;
import com.nastel.jkool.tnt4j.selector.TrackingSelector;
import com.nastel.jkool.tnt4j.sink.EventSink;
import com.nastel.jkool.tnt4j.sink.EventSinkFactory;
import com.nastel.jkool.tnt4j.sink.SinkEventFilter;
import com.nastel.jkool.tnt4j.sink.SinkLogEventListener;
import com.nastel.jkool.tnt4j.tracker.DefaultTrackerFactory;
import com.nastel.jkool.tnt4j.tracker.TrackerFactory;

/**
 * <p>
 * This class consolidates all configuration for creating <code>Tracker</code> instances. Developers should use this
 * class and override default configuration with user defined elements.
 * </p>
 * 
 * <pre>
 * {@code
 * TrackerConfig config = DefaultConfigFactory.getInstance().getConfig(source);
 * TrackingLogger.register(config.build());
 * ...
 * }
 * </pre>
 * 
 * @see TokenRepository
 * @see TrackingSelector
 * @see EventFormatter
 * @see EventSinkFactory
 * 
 * @version $Revision: 9 $
 * 
 */

public class TrackerConfig {
	Source sourceHandle;

	TrackerFactory trFactory;
	EventSinkFactory defEvFactory;
	EventSinkFactory evFactory;
	DumpSinkFactory dpFactory;
	EventFormatter evFormatter;
	ActivityListener activityListener;
	SinkLogEventListener sinkLogEventListener;
	SinkEventFilter sinkFilter;

	TrackingSelector tSelector = null;
	TokenRepository tRepo = null;

	Properties props = new Properties();

	/**
	 * Create an empty configuration with a specific source name
	 * 
	 * @param source
	 *            name of the source instance associated with the configuration
	 */
	protected TrackerConfig(String source) {
		this(new Source(source));
	}

	/**
	 * Create an empty configuration with a specific source name
	 * 
	 * @param source
	 *            name of the source instance associated with the configuration
	 * @param type
	 *            source type associated with this configuration
	 */
	protected TrackerConfig(String source, SourceType type) {
		this(new Source(source, type));
	}

	/**
	 * Create an empty configuration with a specific source handle
	 * 
	 * @param source
	 *            handle instance associated with the configuration
	 */
	protected TrackerConfig(Source source) {
		sourceHandle = source;
	}

	/**
	 * Get configuration source handle
	 * 
	 * @return current source handle
	 */
	public Source getSource() {
		return sourceHandle;
	}

	/**
	 * Set configuration source handle
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setSource(Source app) {
		sourceHandle = app;
		return this;
	}

	/**
	 * Get configuration properties
	 * 
	 * @return current configuration properties
	 */
	public Properties getProperties() {
		return props;
	}

	/**
	 * Set default tracker factory to generate <code>Tracker</code> instances
	 * 
	 * @param tFactory
	 *            tracker factory instance
	 * @see TrackerFactory
	 * 
	 * @return current tracker factory
	 */
	public TrackerConfig setTrackerFactory(TrackerFactory tFactory) {
		trFactory = tFactory;
		return this;
	}

	/**
	 * Set default dump sink factory instance
	 * 
	 * @see TrackerFactory
	 * @return current tracker factory
	 */
	public TrackerFactory getTrackerFactory() {
		return trFactory;
	}

	/**
	 * Set default dump sink factory
	 * 
	 * @param dumpFactory
	 *            dump sink factory instance
	 * @see DumpSinkFactory
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setDumpSinkFactory(DumpSinkFactory dumpFactory) {
		dpFactory = dumpFactory;
		return this;
	}

	/**
	 * Set default dump sink factory instance
	 * 
	 * @see DumpSinkFactory
	 * @return current dump sink factory instance
	 */
	public DumpSinkFactory getDumpSinkFactory() {
		return dpFactory;
	}

	/**
	 * Set default sink log listener which is triggered when logging activities occur
	 * 
	 * @param snListener
	 *            activity listener instance
	 * @see SinkLogEventListener
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setSinkLogEventListener(SinkLogEventListener snListener) {
		sinkLogEventListener = snListener;
		return this;
	}

	/**
	 * Get default sink log listener which is triggered when logging activities occur
	 * 
	 * @see SinkLogEventListener
	 * @return current activity listener
	 */
	public SinkLogEventListener getSinkLogEventListener() {
		return sinkLogEventListener;
	}

	/**
	 * Set default sink filter which is triggered when logging activities occur to filter
	 * out log events.
	 * 
	 * @param filter
	 *            sink filter
	 * @see SinkEventFilter
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setSinkEventFilter(SinkEventFilter filter) {
		sinkFilter = filter;
		return this;
	}

	/**
	 * Get default sink filter which is triggered when logging activities occur to filter
	 * out log events.
	 * 
	 * @see SinkEventFilter
	 * @return current sink filter
	 */
	public SinkEventFilter getSinkEventFilter() {
		return sinkFilter;
	}

	/**
	 * Set default activity listener which is triggered any time a given activity is started or stopped.
	 * 
	 * @param acListener
	 *            activity listener instance
	 * @see ActivityListener
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setActivityListener(ActivityListener acListener) {
		activityListener = acListener;
		return this;
	}

	/**
	 * Get default activity listener which is triggered any time a given activity is started or stopped.
	 * 
	 * @see ActivityListener
	 * @return current activity listener
	 */
	public ActivityListener getActivityListener() {
		return activityListener;
	}

	/**
	 * Set configuration event sink factory. Event sink factory is used to create <code>EventSink</code> instances,
	 * where all events, activities and messages are logged.
	 * 
	 * @param evSinkFactory
	 *            event sink factory instance
	 * @see EventSinkFactory
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setEventSinkFactory(EventSinkFactory evSinkFactory) {
		evFactory = evSinkFactory;
		return this;
	}

	/**
	 * Set default event sink factory. Default Event sink factory is used to create 
	 * <code>EventSink</code> instances for all logging activities.
	 * 
	 * @param evSinkFactory
	 *            event sink factory instance
	 * @see EventSinkFactory
	 * 
	 * @return current default event sink factory
	 */
	public TrackerConfig setDefaultEventSinkFactory(EventSinkFactory evSinkFactory) {
		defEvFactory = evSinkFactory;
		return this;
	}

	/**
	 * Set configuration event formatter. Event formatter is used to format event entries to text format.
	 * 
	 * @param evformat
	 *            event formatter instance
	 * @see Formatter
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setEventFormatter(EventFormatter evformat) {
		evFormatter = evformat;
		return this;
	}

	/**
	 * Set configuration token repository. Token repository is used to hold logging tokens for conditional logging.
	 * 
	 * @param trepo
	 *            token repository instance
	 * @see TokenRepository
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setTokenRepository(TokenRepository trepo) {
		tRepo = trepo;
		return this;
	}

	/**
	 * Set configuration tracking selector. Tracking selectors allow loggers to test weather a specific sev/key/value is
	 * set. Tracking selectors use token repositories for look up.
	 * 
	 * @param tselector
	 *            tracking selector instance
	 * @see TrackingSelector
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setTrackingSelector(TrackingSelector tselector) {
		tSelector = tselector;
		return this;
	}

	/**
	 * Set configuration user defined properties.
	 * 
	 * @param pr
	 *            user defined properties
	 * @see TrackingSelector
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setProperties(Properties pr) {
		if (pr != null)
			props.putAll(pr);
		return this;
	}

	/**
	 * Obtain a property associated with the given key in this configuration instance
	 * 
	 * @param key
	 *            property key
	 * 
	 * @return value associated with the given key or null if non exist
	 */
	public Object getProperty(String key) {
		return props.getProperty(key);
	}

	/**
	 * Obtain a property associated with the given key in this configuration instance
	 * 
	 * @param key
	 *            property key
	 * @param defValue
	 *            default value if key does not exist
	 * 
	 * @return value associated with the given key or <code>defValue</code> if non exist
	 */
	public Object getProperty(String key, String defValue) {
		return props.getProperty(key, defValue);
	}

	/**
	 * Sets a specific property in the current configuration
	 * 
	 * @param key
	 *            property key
	 * @param value
	 *            value associated with the key
	 * 
	 * @return current configuration instance
	 */
	public TrackerConfig setProperty(String key, String value) {
		props.setProperty(key, value);
		return this;
	}

	/**
	 * Get configuration event sink factory
	 * 
	 * @see EventSinkFactory
	 * @return current event sink factory
	 */
	public EventSinkFactory getEventSinkFactory() {
		return evFactory;
	}

	/**
	 * Get configuration default event sink factory
	 * 
	 * @see EventSinkFactory
	 * @return current default event sink factory
	 */
	public EventSinkFactory getDefaultEvenSinkFactory() {
		return defEvFactory;
	}

	/**
	 * Get configuration event formatter
	 * 
	 * @see EventSinkFactory
	 * @return current event formatter instance
	 */
	public Formatter getEventFormatter() {
		return evFormatter;
	}

	/**
	 * Get configuration token repository instance
	 * 
	 * @see TokenRepository
	 * @return current token repository instance
	 */
	public TokenRepository getTokenRepository() {
		return tRepo;
	}

	/**
	 * Get configuration tracking selector instance
	 * 
	 * @see TrackingSelector
	 * @return current tracking selector instance
	 */
	public TrackingSelector getTrackingSelector() {
		return tSelector;
	}

	/**
	 * Get event logger instance created by <code>EventSinkFactory</code>
	 * 
	 * @see EventSink
	 * @return new event logger instance created by <code>EventSinkFactory</code>
	 */
	public EventSink getEventSink() {
		return evFactory.getEventSink(sourceHandle.getName(), props, evFormatter);
	}

	/**
	 * Get event logger instance created by <code>EventSinkFactory</code>
	 * 
	 * @param frm
	 *            user defined event formatter used to format tracking events
	 * @see EventSink
	 * @see EventFormatter
	 * @return new event logger instance created by <code>EventSinkFactory</code>
	 */
	public EventSink getEventSink(EventFormatter frm) {
		return evFactory.getEventSink(sourceHandle.getName(), props, frm);
	}

	/**
	 * Clone current tracking configuration instance and return a new one
	 * 
	 * @return new <code>TrackerConfig</code> instance with cloned values from the current instance
	 */
	public TrackerConfig cloneConfig() {
		TrackerConfig config = new TrackerConfig(sourceHandle);
		config.setProperties(this.props);
		config.trFactory = this.trFactory;
		config.evFactory = this.evFactory;
		config.defEvFactory = this.defEvFactory;
		config.dpFactory = this.dpFactory;
		config.evFormatter = this.evFormatter;
		config.tRepo = this.tRepo;
		config.tSelector = this.tSelector;
		config.activityListener = this.activityListener;
		config.sinkLogEventListener = this.sinkLogEventListener;
		config.sinkFilter= this.sinkFilter;
		return config;
	}

	/**
	 * Build configuration based on specified configuration elements. This method must be called before passing
	 * configuration to initialize other objects:
	 * 
	 * @return <code>TrackerConfig</code> instance with initialized configuration elements
	 */
	public TrackerConfig build() {
		if (trFactory == null)
			trFactory = new DefaultTrackerFactory();
		if (evFactory == null) 
			evFactory = new Log4JEventSinkFactory();
		if (defEvFactory == null)
			defEvFactory = new Log4JEventSinkFactory();
		if (dpFactory == null)
			dpFactory = new DefaultDumpSinkFactory();
		if (evFormatter == null)
			evFormatter = new DefaultFormatter();
		if (tRepo == null)
			tRepo = new FileTokenRepository();
		if (tSelector == null) {
			tSelector = new DefaultTrackingSelector(tRepo);
		}
		return this;
	}

	@Override
	public String toString() {
		return super.toString() 
			+ "{" 
			+ "source: " + sourceHandle.getName()
			+ ", event.factory: " + evFactory 
			+ ", default.event.factory: " + defEvFactory 
			+ ", event.formatter: " + evFormatter 
			+ ", tracker.factory: " + trFactory 
			+ ", dump.factory: " + dpFactory 
			+ ", repository: " + tRepo 
			+ ", selector: " + tSelector 
			+ ", activity.listener: " + activityListener 
			+ ", sink.log.listener: " + sinkLogEventListener 
			+ ", sink.event.filter: " + sinkFilter 
			+ "}";
	}
}
