///////////////////////////////////////////////////////////////////////////////////////////////////
// Form model html binding is a form model binding implementation for HTML.
// Copyright (C) 2018 Dmitry Shapovalov.
//
// This file is part of Form model html binding.
//
// Form model html binding is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Form model html binding is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.formmodel.binding.html;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

/**
 * HTTP server implementation.
 *
 * @author Dmitry Shapovalov
 */
public final class NanoHttpdImpl extends NanoHTTPD {

    private static final int PORT = 43472;

    private final String _html;

    /**
     * Create new object.
     *
     * @param html the HTML to serve.
     */
    public NanoHttpdImpl(final String html) {
        super(PORT);
        _html = html;
    }

    /**
     * Get the URL.
     *
     * @return the URL.
     *
     * @throws IOException IO exception.
     */
    public String getUrl() throws IOException {
        return "http://" + getHostname() + ":" + getListeningPort();
    }

    @Override
    public Response serve(final IHTTPSession session) {
        return newFixedLengthResponse(_html);
    }

}
