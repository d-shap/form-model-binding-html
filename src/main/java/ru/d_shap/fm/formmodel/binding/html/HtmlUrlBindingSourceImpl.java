///////////////////////////////////////////////////////////////////////////////////////////////////
// Form model HTML binding is a form model binding implementation for HTML.
// Copyright (C) 2018 Dmitry Shapovalov.
//
// This file is part of form model HTML binding.
//
// Form model HTML binding is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Form model HTML binding is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.fm.formmodel.binding.html;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import ru.d_shap.fm.formmodel.InputSourceException;

/**
 * The HTML binding source implementation, obtained from the URL.
 *
 * @author Dmitry Shapovalov
 */
final class HtmlUrlBindingSourceImpl implements HtmlBindingSource {

    private final String _url;

    HtmlUrlBindingSourceImpl(final String url) {
        super();
        _url = url;
    }

    HtmlUrlBindingSourceImpl(final URL url) {
        super();
        _url = url.toExternalForm();
    }

    @Override
    public Document getDocument() {
        try {
            return Jsoup.connect(_url).get();
        } catch (IOException ex) {
            throw new InputSourceException(ex);
        }
    }

}
