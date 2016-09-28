/*
 * Copyright 2014-2015 JKOOL, LLC.
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
package com.jkoolcloud.tnt4j.sink.impl;

import com.jkoolcloud.tnt4j.core.OpLevel;

/**
 * This class implements a task for handling asynchronous handling and delivery of logging
 * events to various event sinks.
 *
 *
 * @version $Revision: 1 $
 * @see PooledLogger
 */
class PooledLoggingTask implements Runnable {
	PooledLogger pooledLogger;

	protected PooledLoggingTask(PooledLogger logger) {
		pooledLogger = logger;
	}

	
    @Override
    public void run() {
    	try {
			while (true) {
				pooledLogger.processEvent(pooledLogger.takeEvent());
			}
		} catch (Throwable e) {
			PooledLogger.logger.log(OpLevel.WARNING,
					"Interrupted during processing: shutting down: error.count={0}",
					pooledLogger.exceptionCount.get(), e);
		}
    }
}