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
 *         This exception is thrown on a Recurly API failure. It wraps the the
 *         original cause of the exception (i.e. an IOException etc...)
 *
 */
public class RecurlyAPIException extends RecurlyException {
	private static final long serialVersionUID = -3892249624742324713L;

	private int errorCode = -1;

	public RecurlyAPIException() {
		super();
	}

	public RecurlyAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RecurlyAPIException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecurlyAPIException(String message) {
		super(message);
	}

	public RecurlyAPIException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public RecurlyAPIException(Throwable cause) {
		super(cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

}
