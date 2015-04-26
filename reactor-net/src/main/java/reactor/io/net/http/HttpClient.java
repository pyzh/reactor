/*
 * Copyright (c) 2011-2015 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package reactor.io.net.http;

import reactor.Environment;
import reactor.core.Dispatcher;
import reactor.io.buffer.Buffer;
import reactor.io.codec.Codec;
import reactor.io.net.ReactorChannelHandler;
import reactor.io.net.ReactorClient;
import reactor.io.net.config.ClientSocketOptions;
import reactor.io.net.http.model.Method;
import reactor.rx.Promise;

/**
 * The base class for a Reactor-based Http client.
 *
 * @param <IN>  The type that will be received by this client
 * @param <OUT> The type that will be sent by this client
 * @author Jon Brisbin
 * @author Stephane Maldini
 */
public abstract class HttpClient<IN, OUT>
		extends ReactorClient<IN, OUT, HttpChannel<IN, OUT>> {

	protected HttpClient(Environment env,
	                     Dispatcher dispatcher,
	                     Codec<Buffer, IN, OUT> codec,
	                     ClientSocketOptions options) {
		super(env, dispatcher, codec, options.prefetch());
	}

	/**
	 * @param url
	 * @param handler
	 * @return
	 */
	public final Promise<? extends HttpChannel<IN, OUT>> get(String url,
	                                                         final ReactorChannelHandler<IN, OUT, HttpChannel<IN, OUT>>
			                                                         handler) {
		return request(Method.GET, url, handler);
	}


	/**
	 * @param url
	 * @return
	 */
	public final Promise<? extends HttpChannel<IN, OUT>> get(String url) {

		return request(Method.GET, url, null);
	}

	/**
	 * @param url
	 * @param handler
	 * @return
	 */
	public final Promise<? extends HttpChannel<IN, OUT>> post(String url,
	                                                          final ReactorChannelHandler<IN, OUT, HttpChannel<IN, OUT>>
			                                                          handler) {
		return request(Method.POST, url, handler);
	}


	/**
	 * @param url
	 * @param handler
	 * @return
	 */
	public final Promise<? extends HttpChannel<IN, OUT>> put(String url,
	                                                         final ReactorChannelHandler<IN, OUT, HttpChannel<IN, OUT>>
			                                                         handler) {
		return request(Method.PUT, url, handler);
	}

	/**
	 * @param url
	 * @param handler
	 * @return
	 */
	public final Promise<? extends HttpChannel<IN, OUT>> delete(String url,
	                                                            final ReactorChannelHandler<IN, OUT, HttpChannel<IN,
			                                                            OUT>> handler) {
		return request(Method.DELETE, url, handler);
	}

	public final Promise<? extends HttpChannel<IN, OUT>> delete(String url) {
		return request(Method.DELETE, url, null);
	}


	public abstract Promise<? extends HttpChannel<IN, OUT>> request(Method method, String url,
	                                                                final ReactorChannelHandler<IN, OUT, HttpChannel<IN,
			                                                                OUT>> handler);

}
