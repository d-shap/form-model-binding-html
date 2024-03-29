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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link HtmlDocumentBindingSourceImpl}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlDocumentBindingSourceImplTest {

    /**
     * Test class constructor.
     */
    public HtmlDocumentBindingSourceImplTest() {
        super();
    }

    /**
     * {@link HtmlDocumentBindingSourceImpl} class test.
     */
    @Test
    public void getDocumentTest() {
        String html = UrlHandler.getHtml4();
        Document sourceDocument = Jsoup.parse(html);
        HtmlBindingSource htmlBindingSource = new HtmlDocumentBindingSourceImpl(sourceDocument);
        Document resultDocument = htmlBindingSource.getDocument();
        Assertions.assertThat(resultDocument).isNotNull();
        Assertions.assertThat(resultDocument).isSameAs(sourceDocument);
    }

}
