/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sinjinsong.minirest.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;


public interface Resource extends InputStreamSource {

	
	URL getURL() throws IOException;

	
	URI getURI() throws IOException;

	
	File getFile() throws IOException;


	long contentLength() throws IOException;

	
	long lastModified() throws IOException;

	
	String getFilename();

}
