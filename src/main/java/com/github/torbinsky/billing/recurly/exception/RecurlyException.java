/*
 * Copyright 2013 Torben
 *
 * Torben Werner licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.github.torbinsky.billing.recurly.exception;

/**
 * @author torben
 *
 * Generic Recurly exception.
 */
public class RecurlyException extends RuntimeException {
	private static final long serialVersionUID = 5415139450940352679L;

	public RecurlyException() {
		super();
	}

	public RecurlyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RecurlyException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecurlyException(String message) {
		super(message);
	}

	public RecurlyException(Throwable cause) {
		super(cause);
	}

}
